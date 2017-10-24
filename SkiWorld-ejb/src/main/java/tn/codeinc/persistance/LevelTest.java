package tn.codeinc.persistance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LevelTest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer score;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="course",nullable=false)
	private Course course;
	public LevelTest(Integer score) {
		super();
		this.score = score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public LevelTest() {
		// TODO Auto-generated constructor stub
	}

}
