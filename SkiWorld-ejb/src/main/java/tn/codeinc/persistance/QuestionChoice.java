package tn.codeinc.persistance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="question_choice")
public class QuestionChoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String text;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="question",nullable=false)
	private Question question;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public QuestionChoice(String text, Question question) {
		super();
		this.text = text;
		this.question = question;
	} 
	
	public QuestionChoice() {
		// TODO Auto-generated constructor stub
	}

}
