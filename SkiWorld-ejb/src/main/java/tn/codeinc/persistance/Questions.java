package tn.codeinc.persistance;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Questions implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int questionID;
	private String statement;
	private String rightAnswer;
	private String wrongAnwer1;
	private String wrongAnwer2;
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="questions")
	@JsonBackReference
	private List<Test> tests;
	public Questions() {
		super();
	}
	public Questions(int questionID, String statement, String rightAnswer, String wrongAnwer1, String wrongAnwer2,
			List<Test> tests) {
		super();
		this.questionID = questionID;
		this.statement = statement;
		this.rightAnswer = rightAnswer;
		this.wrongAnwer1 = wrongAnwer1;
		this.wrongAnwer2 = wrongAnwer2;
		this.tests = tests;
	}
	public int getQuestionID() {
		return questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getWrongAnwer1() {
		return wrongAnwer1;
	}
	public void setWrongAnwer1(String wrongAnwer1) {
		this.wrongAnwer1 = wrongAnwer1;
	}
	public String getWrongAnwer2() {
		return wrongAnwer2;
	}
	public void setWrongAnwer2(String wrongAnwer2) {
		this.wrongAnwer2 = wrongAnwer2;
	}
	public List<Test> getTests() {
		return tests;
	}
	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
	
	
	
	
}
