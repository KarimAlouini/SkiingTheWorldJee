package tn.codeinc.persistance;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Courses implements Serializable{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int courseID;
	private String courseName;
	@Enumerated(EnumType.STRING)
	private CourseLevel courseLevel;
	@Temporal(TemporalType.DATE)
	private Date date;
	private Double price;
	private int maxParticipants;
	@Enumerated(EnumType.STRING)
	private CourseState courseState;
	private String location;
	@ManyToOne(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private User guide;
	@OneToOne
	private CourseNotification notification;
	
	
	@OneToMany(mappedBy="course",fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<CourseReview> reviews;
	
	@OneToMany(mappedBy="course",fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<CourseParticipation> participant;
	public Courses() {
		super();
		participant= new ArrayList<>();
		reviews= new ArrayList<>();
	}
	
	
	public Courses(int courseID, String courseName, CourseLevel courseLevel, Date date, Double price,
			int maxParticipants, CourseState courseState, String location, User guide, CourseNotification notification,
			List<CourseReview> reviews, List<CourseParticipation> participant) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.courseLevel = courseLevel;
		this.date = date;
		this.price = price;
		this.maxParticipants = maxParticipants;
		this.courseState = courseState;
		this.location = location;
		this.guide = guide;
		this.notification = notification;
		this.reviews = reviews;
		this.participant = participant;
	}
	


	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public CourseLevel getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(CourseLevel courseLevel) {
		this.courseLevel = courseLevel;
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
	public int getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(int maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	public CourseState getCourseState() {
		return courseState;
	}
	public void setCourseState(CourseState courseState) {
		this.courseState = courseState;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public User getGuide() {
		return guide;
	}
	public void setGuide(User guide) {
		this.guide = guide;
	}
	public List<CourseReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<CourseReview> reviews) {
		this.reviews = reviews;
	}
	public List<CourseParticipation> getParticipant() {
		return participant;
	}
	public void setParticipant(List<CourseParticipation> participant) {
		this.participant = participant;
	}


	public CourseNotification getNotification() {
		return notification;
	}


	public void setNotification(CourseNotification notification) {
		this.notification = notification;
	}
	
	
	
	
}
