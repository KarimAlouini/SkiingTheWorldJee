package tn.codeinc.persistance;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EventIvitationId implements Serializable {
	private Integer userInviterId;
	private Integer userInvitedId;
	private Integer eventId;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((userInvitedId == null) ? 0 : userInvitedId.hashCode());
		result = prime * result + ((userInviterId == null) ? 0 : userInviterId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventIvitationId other = (EventIvitationId) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (userInvitedId == null) {
			if (other.userInvitedId != null)
				return false;
		} else if (!userInvitedId.equals(other.userInvitedId))
			return false;
		if (userInviterId == null) {
			if (other.userInviterId != null)
				return false;
		} else if (!userInviterId.equals(other.userInviterId))
			return false;
		return true;
	}
	
	

}
