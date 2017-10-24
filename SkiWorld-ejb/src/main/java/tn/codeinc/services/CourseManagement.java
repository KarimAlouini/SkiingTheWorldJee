package tn.codeinc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.CourseState;
import tn.codeinc.persistance.Courses;
import tn.codeinc.persistance.Notification;
import tn.codeinc.persistance.User;



/**
 * Session Bean implementation class CourseManagement
 */
@Stateless
@LocalBean
public class CourseManagement implements CourseManagementRemote, CourseManagementLocal{
	
	List<CourseParticipation> lst;
	List<User> user;
	NotificationManagement notifM=new  NotificationManagement();
	Notification notif= new Notification();
	@Inject
	UsersManagementLocal userM;
	
	@Inject
	PersistanceContextLocal pc;
	
    /**
     * Default constructor. 
     */
    public CourseManagement() {
        // TODO Auto-generated constructor stub
    }
	
    @Transactional
	@Override
	public String addCourse(Courses course) {

		try {
			course.setCourseState(CourseState.AVAILABLE);
			course.setReviews(null);
			course.setParticipant(null);
			course.setNotification(null);
			course.setGuide(userM.get(course.getGuide().getId()));
			pc.getEM().merge(course);
			pc.getEM().persist(course);
			
			return "Course added succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to add course", ex.toString());
		}
	}

	@Override
	public String deleteCourse(int id) {
		
		try {
			
				Courses course=	pc.getEM().find(Courses.class, id);	
				course.setCourseState(CourseState.CANCELLED);
				pc.getEM().merge(course);
			lst=pc.getEM().createQuery("SELECT r FROM CourseParticipation r WHERE courseID = "+id)
				.getResultList();
			
			System.out.println(lst.isEmpty());
			for(int i=0;i<lst.size();i++){
				System.out.println(lst.size());
				user=pc.getEM().createQuery("SELECT u FROM User u WHERE id = "+lst.get(i).getUser().getId()).getResultList();
				notif.setUser(user.get(0));
				//notif.getUser().setId(lst.get(i).getUser().getId());
				notif.setMsg("We are sorry to inform you that the course "+lst.get(i).getCourse().getCourseName()+" is cancled");
				System.out.println(notif.getUser().getId());
			System.out.println(notif.getMsg());
			pc.getEM().persist(notif);
				
			}
			return "Course deleted succesfully";
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to delete course", ex.toString());
		}
	}

	@Override
	public String updateCourse(Courses course) {
		try {
			pc.getEM().merge(course);
			return "Course updated with success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to update course", ex.toString());
		}
	}

	@Override
	public List<Courses> findCourseByLevel(String level) {
		List<Courses> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.courseLevel LIKE '%" + level + "%'", Courses.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Courses> findCourseByLocation(String location) {
		List<Courses> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.location LIKE '%" + location + "%'", Courses.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Courses> findCourseByDate(Date date) {
		
		   	 return pc.getEM().createQuery(
				        "SELECT c FROM Courses c WHERE c.date = :date",Courses.class)
				        .setParameter("date", date).getResultList(); 
	}

	@Override
	public List<Courses> findCourseByState(String state) {
		List<Courses> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c  WHERE c.courseState LIKE '%" + state + "%'", Courses.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Courses> listAllCourse() {
		String requete = "SELECT c FROM Courses c";
		return pc.getEM().createQuery(requete,Courses.class).getResultList();
		
	}

	@Override
	public Courses findCourseByID(int id) {
		// TODO Auto-generated method stub
		return pc.getEM().find(Courses.class, id);
	}

	@Override
	public List Stat() {
		List rl =new ArrayList();
		rl = pc.getEM().createQuery
//("Select o.courseName as Name ,(count(o.courseName)*100)/(select count(o.courseID) from Courses o )as nbr from Courses o where o.courseID IN (Select r.course.courseID  from CourseReview r  ) GROUP BY o.courseName ").getResultList();
		("SELECT r.course.courseName, AVG(r.rate) from CourseReview r  GROUP by r.reviewPK.courseID").getResultList();
				//("select c.courseName , (count(r.rate)*100)/(select count(r.course.courseID) from CourseReview r ) from Courses c  group by  r.course.courseID  ").getResultList();
		return rl;
		
	}

	@Override
	public void addP(CourseParticipation p) {
		
		int id= p.getCourse().getCourseID();
		Courses c= findCourseByID(id);
		
		for(int i=0; i<= p.getParticipationPK().getNbrPlaces();i++){
			c.getParticipant().add(p);
		}
		pc.getEM().merge(c);
	}

	@Override
	public List<Notification> listNotif() {
			String requete = "SELECT n FROM Notification n";
			return pc.getEM().createQuery(requete,Notification.class).getResultList();
		
	}

	
	
	

}
