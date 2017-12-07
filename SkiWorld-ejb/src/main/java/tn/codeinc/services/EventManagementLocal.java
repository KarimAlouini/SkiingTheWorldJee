package tn.codeinc.services;

import java.util.List;
import javax.ejb.Local;

import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.Event.EventType;

@Local
public interface EventManagementLocal {
	public void create(Event event);
	public List<Event> getAll();
	public Event get(int id) throws ElementNotFoundException;
	public void remove(Event event);
	public void update(Event event);
	public List<Event> getByType(EventType statue);

}
