package tn.codeinc.persistance;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
/**
 * Entity implementation class for Entity: EventInvitation
 *
 */
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="event_invitation")
public class EventInvitation {
	
	@EmbeddedId
	private EventIvitationId id;
	
	
	@ManyToOne
	@JoinColumn(name = "eventId", referencedColumnName = "id", updatable = false, insertable = false)
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "userInviterId", referencedColumnName = "id", updatable = false, insertable = false)
	private User userInviter;
	
	@ManyToOne
	@JoinColumn(name = "userInvitedId", referencedColumnName = "id", updatable = false, insertable = false)
	private User userInvited;

	public EventIvitationId getId() {
		return id;
	}

	public void setId(EventIvitationId id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUserInviter() {
		return userInviter;
	}

	public void setUserInviter(User userInviter) {
		this.userInviter = userInviter;
	}

	public User getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(User userInvited) {
		this.userInvited = userInvited;
	}
	
	

}
