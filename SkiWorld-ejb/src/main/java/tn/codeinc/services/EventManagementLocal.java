package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;


import tn.codeinc.exceptions.BadWordException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.EventException;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;
import tn.codeinc.persistance.EventInvitation;
import tn.codeinc.persistance.KeyWord;

@Local
public interface EventManagementLocal {
	public void create(Event event) throws BadWordException, EventException;
	public List<Event> getAll();
	public Event get(int id) throws ElementNotFoundException;
	public void remove(Event event);
	public void update(Event event);
	public List<Event> getByType(EventType statue);
	public List<Event> getByKeywords(List<KeyWord> keywords);
	void applyForEvent(Event event) throws ElementNotFoundException, EventException;
	void invite(EventInvitation eventInvitation) throws ElementNotFoundException;

}
