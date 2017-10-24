package tn.codeinc.persistance;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private CourseLevel level;
	private Date date;
	private Double price;
	private Integer maxParticipants;
	private CourseState state;
	
	public enum CourseState {
		AVAILABLE,CANCELLED,FULL
	}
	
	public enum CourseLevel {
		DIFFICULT,MEDIUM,EASY
	}

	@ManyToOne
	@JoinColumn(name="guide",nullable=false)
	private User guide;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CourseLevel getLevel() {
		return level;
	}
	public void setLevel(CourseLevel level) {
		this.level = level;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	public CourseState getState() {
		return state;
	}
	public void setState(CourseState state) {
		this.state = state;
	}
	
	
	public Course() {
		// TODO Auto-generated constructor stub
	}
	public Course(Integer id, String name, CourseLevel level, Date date, Double price, Integer maxParticipants) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.date = date;
		this.price = price;
		this.maxParticipants = maxParticipants;
	}
	
	
	
}
