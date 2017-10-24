package tn.codeinc.persistance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	//:private string
	private String text;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="level_test",nullable=false)
	private LevelTest levelTest;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question(String text) {
		super();
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Question() {
		// TODO Auto-generated constructor stub
	}
	
	public LevelTest getLevelTest() {
		return levelTest;
	}
	
	public void setLevelTest(LevelTest levelTest) {
		this.levelTest = levelTest;
	}

}
