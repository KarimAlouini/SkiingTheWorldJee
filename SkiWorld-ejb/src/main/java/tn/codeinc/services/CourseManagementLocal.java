package tn.codeinc.services;


import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.Courses;
import tn.codeinc.persistance.CourseNotification;

@Local
public interface CourseManagementLocal {
	public String addCourse(Courses course);
	public String deleteCourse(int id);
	public String updateCourse(Courses course);
	public List<Courses> findCourseByLevel(String level);
	public Courses findCourseByID(int id);
	public List<Courses> findCourseByLocation(String level);
	public List<Courses> findCourseByDate(Date date);
	public List<Courses> findCourseByState(String state);
	public List<Courses> listAllCourse();
	public List Stat();
	public List<CourseNotification> listNotif();
	public void addP(CourseParticipation p);
}
