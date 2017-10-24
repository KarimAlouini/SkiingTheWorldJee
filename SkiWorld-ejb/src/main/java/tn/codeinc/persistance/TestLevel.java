package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
public class TestLevel  implements Serializable{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int idTest;
private int score;
@Temporal(TemporalType.DATE)
private Date date;
@ManyToOne(fetch=FetchType.EAGER,targetEntity=Test.class)

private Test test;

@ManyToOne(fetch=FetchType.EAGER)

private User user;

public TestLevel() {
	super();
}



public TestLevel(int idTest, int score, Date date, Test test, User user) {
	super();
	this.idTest = idTest;
	this.score = score;
	this.date = date;
	this.test = test;
	this.user = user;
}



public int getIdTest() {
	return idTest;
}

public void setIdTest(int idTest) {
	this.idTest = idTest;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

public Test getTest() {
	return test;
}

public void setTest(Test test) {
	this.test = test;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}



public Date getDate() {
	return date;
}



public void setDate(Date date) {
	this.date = date;
}




}
