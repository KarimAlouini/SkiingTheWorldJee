package tn.codeinc.services;


import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.Course;
import tn.codeinc.persistance.Notification;

@Local
public interface CourseManagementLocal {
	public String addCourse(Course course);
	public String deleteCourse(int id);
	public String updateCourse(Course course);
	public List<Course> findCourseByLevel(String level);
	public Course findCourseByID(int id);
	public List<Course> findCourseByLocation(String level);
	public List<Course> findCourseByDate(Date date);
	public List<Course> findCourseByState(String state);
	public List<Course> listAllCourse();
	public List Stat();
	public List<Notification> listNotif();
	public void addP(CourseParticipation p);
}
