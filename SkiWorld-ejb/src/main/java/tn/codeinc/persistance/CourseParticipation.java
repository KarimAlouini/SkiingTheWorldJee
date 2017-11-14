package tn.codeinc.persistance;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CourseParticipation  implements Serializable{


@EmbeddedId
private ParticipationPK participationPK;
@ManyToOne
@JoinColumn(name="courseId",referencedColumnName="courseID",nullable=false, insertable=false,updatable=false)
private Courses course;
@ManyToOne
@JoinColumn(name="userID",referencedColumnName="id",nullable=false,insertable=false,updatable=false)
private User user;
@Column(unique=true)
private int idP;	
public CourseParticipation() {
	super();
}

//manytoone man7otouch l fetch 7ata l cascade

public CourseParticipation(int idP, ParticipationPK participationPK, Courses course, User user) {
	super();
	this.idP = idP;
	this.participationPK = participationPK;
	this.course = course;
	this.user = user;
}

public ParticipationPK getParticipationPK() {
	return participationPK;
}
public void setParticipationPK(ParticipationPK participationPK) {
	this.participationPK = participationPK;
}
public Courses getCourse() {
	return course;
}
public void setCourse(Courses course) {
	this.course = course;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

public int getIdP() {
	return idP;
}

public void setIdP(int idP) {
	this.idP = idP;
}





}
