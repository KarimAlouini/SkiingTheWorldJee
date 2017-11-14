package tn.codeinc.persistance;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="course_notification")
public class CourseNotification  implements Serializable{
	@Id
	@GeneratedValue( strategy= GenerationType.AUTO )
	private int id;
	@ManyToOne
	private User user;
	private String msg;
	@OneToOne
	private Courses course;
	
	public CourseNotification() {
		super();
	}
	
	
	
	public CourseNotification(int id, User user, String msg, Courses course) {
		super();
		this.id = id;
		this.user = user;
		this.msg = msg;
		this.course = course;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	private static final long serialVersionUID = 1L;

	   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Courses getCourse() {
		return course;
	}



	public void setCourse(Courses course) {
		this.course = course;
	}



	@Override
	public String toString() {
		return "Notification id=" + id + ", User=" + user.getFirstName()+" "+user.getLastName() + ", Message=" + msg+"\n";
	}
	
	
}
