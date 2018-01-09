package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CourseReview  implements Serializable{
//@Id 
//@GeneratedValue(strategy=GenerationType.IDENTITY)
@EmbeddedId
private ReviewPK reviewPK;
private String content;
private float rate;
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="UserID",referencedColumnName="id", insertable=false,updatable=false)

private User user;
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="courseID",referencedColumnName="courseID", insertable=false,updatable=false)

private Courses course;
public CourseReview() {
	super();
}
public CourseReview(ReviewPK reviewPK, String content, float rate, User user, Courses course) {
	super();
	this.reviewPK = reviewPK;
	this.content = content;
	this.rate = rate;
	this.user = user;
	this.course = course;
}
public ReviewPK getReviewPK() {
	return reviewPK;
}
public void setReviewPK(ReviewPK reviewPK) {
	this.reviewPK = reviewPK;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public float getRate() {
	return rate;
}
public void setRate(float rate) {
	this.rate = rate;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Courses getCourse() {
	return course;
}
public void setCourse(Courses course) {
	this.course = course;
}




}
