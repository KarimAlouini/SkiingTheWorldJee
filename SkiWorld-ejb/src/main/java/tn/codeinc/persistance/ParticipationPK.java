package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class ParticipationPK implements Serializable {

private int courseId;
private int userID;
private int nbrPlaces;
public ParticipationPK() {
	super();
}
public ParticipationPK(int courseId, int userID, int nbrPlaces) {
	super();
	this.courseId = courseId;
	this.userID = userID;
	this.nbrPlaces = nbrPlaces;
}
public int getCourseId() {
	return courseId;
}
public void setCourseId(int courseId) {
	this.courseId = courseId;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public int getNbrPlaces() {
	return nbrPlaces;
}
public void setNbrPlaces(int nbrPlaces) {
	this.nbrPlaces = nbrPlaces;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + courseId;
	result = prime * result + nbrPlaces;
	result = prime * result + userID;
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
	ParticipationPK other = (ParticipationPK) obj;
	if (courseId != other.courseId)
		return false;
	if (nbrPlaces != other.nbrPlaces)
		return false;
	if (userID != other.userID)
		return false;
	return true;
}





}
