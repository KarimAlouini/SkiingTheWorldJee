package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Test implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int idTest;
@ManyToMany(fetch=FetchType.EAGER)
private List<Questions>  questions;
@OneToMany(mappedBy="test", fetch=FetchType.EAGER,targetEntity=TestLevel.class)
@JsonIgnore
private List<TestLevel> levelTests;

public List<Questions> getQuestions() {
	return questions;
}
public void setQuestions(List<Questions> questions) {
	this.questions = questions;
}
public int getIdTest() {
	return idTest;
}
public void setIdTest(int idTest) {
	this.idTest = idTest;
}

public List<TestLevel> getLevelTests() {
	return levelTests;
}
public void setLevelTests(List<TestLevel> levelTests) {
	this.levelTests = levelTests;
}
public Test() {
	super();
}
public Test(int idTest, List<Questions> questions, List<TestLevel> levelTests) {
	super();
	this.idTest = idTest;
	this.questions = questions;
	this.levelTests = levelTests;
}







}
