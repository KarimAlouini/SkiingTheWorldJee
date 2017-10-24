package tn.codeinc.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.client.CurrentUserRemote;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.EventInvitation;

@Stateless
public class EventManagement implements EventManagementLocal,EventManagementRemote {

	@PersistenceContext
	EntityManager em;
	
	CurrentUserLocal currentUser;
	UserManagement userInvited;
	
	
	@Override
	public List<Event> getAll() {
		return em.createQuery("SELECT e FROM Event e",Event.class).getResultList();
	}

	@Override
	public Event get(int id) {
		return em.find(Event.class, id);
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
	public void create(Event event) {
		em.persist(event);
		
	}
	
	public void invite(EventInvitation eventInvitation){
		Event event = get(eventInvitation.getEvent().getId());
		eventInvitation.setEvent(event);
		
		eventInvitation.setUserInviter(currentUser.get());
		eventInvitation.setUserInvited(userInvited.get(eventInvitation.getUserInvited().getId()));
		event.getEventInvitations().add(eventInvitation);
		em.merge(event);
	}
	public void applyForEvent(Event event){
		event = get(event.getId());
		event.getUsers().add(currentUser.get());
		em.merge(event);
		
	}

}
