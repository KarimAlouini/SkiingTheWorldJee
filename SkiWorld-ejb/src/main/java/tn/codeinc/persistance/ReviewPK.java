package tn.codeinc.persistance;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewPK implements Serializable{
private int courseID;
private int userID;
private int reviewID;
public ReviewPK() {
	super();
}
public ReviewPK(int courseID, int userID, int reviewID) {
	super();
	this.courseID = courseID;
	this.userID = userID;
	this.reviewID = reviewID;
}
public int getCourseID() {
	return courseID;
}
public void setCourseID(int courseID) {
	this.courseID = courseID;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public int getReviewID() {
	return reviewID;
}
public void setReviewID(int reviewID) {
	this.reviewID = reviewID;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + courseID;
	result = prime * result + reviewID;
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
	ReviewPK other = (ReviewPK) obj;
	if (courseID != other.courseID)
		return false;
	if (reviewID != other.reviewID)
		return false;
	if (userID != other.userID)
		return false;
	return true;
}


}
