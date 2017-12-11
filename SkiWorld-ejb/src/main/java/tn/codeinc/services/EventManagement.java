package tn.codeinc.services;
		
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.Interval;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.BadWordException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.EventException;
import tn.codeinc.persistance.BadWord;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.EventInvitation;
import tn.codeinc.persistance.KeyWord;

@Stateless
public class EventManagement implements EventManagementLocal,EventManagementRemote {

	@PersistenceContext
	EntityManager em;
	
	@Inject
	CurrentUserLocal currentUser;
	@Inject
	UsersManagementLocal userInvited;
	
	@Inject
	BadWordManagementLocal badWord;
	
	
	@Override
	public List<Event> getAll() {
		return em.createQuery("SELECT e FROM Event e",Event.class).getResultList();
	}

	@Override
	public Event get(int id) throws ElementNotFoundException{
		Event e = em.find(Event.class, id);
		if (e == null)
			throw new ElementNotFoundException("Event not found");
		else
			return e;
		
	}

	@Override
	public void remove(Event event) {
		em.remove(event);
		
	}

	@Override
	public void update(Event event) {
		em.merge(event);
		
	}

	@Override
	public List<Event> getByType(EventType statue) {
		
		return em.createQuery("SELECT e FROM Event e WHERE e.statue = :statue", Event.class)
				.setParameter("statue", statue).getResultList();
	
	}

	@Override
	public void create(Event event) throws BadWordException, EventException {
		event.setHost(currentUser.get());
		Interval i = new Interval(event.getStart().getTime(), event.getEnd().getTime());
		if(this.getAll().stream().filter(e -> i.contains(event.getStart().getTime()))
				.filter(e -> i.contains(event.getEnd().getTime()))
				.anyMatch(e -> e.getHost().equals(currentUser.get()) && e.getName().equals(event.getName()))){
			throw new EventException("Event already existant in this time zone!!!");
		}

		// BadWord Condition
		String[] splited = event.getDescription().toLowerCase().split("\\b+");
		for (BadWord bw : badWord.getAll()) {
			if (Arrays.asList(splited).contains(bw.getContent().toLowerCase()) == true)
				throw new BadWordException("BadWord");
		}

		em.persist(event);	

	}
	
	public void invite(EventInvitation eventInvitation) throws ElementNotFoundException{
		Event event = get(eventInvitation.getEvent().getId());
		eventInvitation.setEvent(event);
		
		eventInvitation.setUserInviter(currentUser.get());
		eventInvitation.setUserInvited(userInvited.get(eventInvitation.getUserInvited().getId()));
		event.getEventInvitations().add(eventInvitation);
		em.merge(event);
	}
	public void applyForEvent(Event event) throws ElementNotFoundException{
		event = get(event.getId());
		event.getUsers().add(currentUser.get());
		em.merge(event);
		
	}

	@Override
	public List<Event> getByKeywords(List<KeyWord> keywords) {
		Set<Event> output = new HashSet<>();
		
		for(KeyWord k:keywords){
			List<Event> ev = em.createQuery("FROM KeyWord k WHERE k.content = :content",KeyWord.class)
					.setParameter("content", k.getContent())
					.getResultList()
					.stream()
					.map(KeyWord::getEvent)
					.distinct()
					.collect(Collectors.toList());
			System.out.println("EventManagement.getByKeywords() "+ev);
			output.addAll(ev
					);
			
		}
		
		return output.stream().collect(Collectors.toList());
	}

}
