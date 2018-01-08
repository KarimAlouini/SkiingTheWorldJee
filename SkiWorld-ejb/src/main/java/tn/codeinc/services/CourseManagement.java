package tn.codeinc.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import tn.codeinc.persistance.Course;
import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.CourseState;
import tn.codeinc.persistance.Notification;
import tn.codeinc.persistance.User;



/**
 * Session Bean implementation class CourseManagement
 */
@Stateless
@LocalBean// no interface view 
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
	public String addCourse(Course course) {

		try {
			course.setCourseState(CourseState.AVAILABLE);
			course.setReviews(null);
			course.setParticipant(null);
			course.setNotification(null);
			//reservation.setCourse(courseM.findCourseByID(reservation.getCourse().getCourseID()));
			course.setGuide(userM.get(course.getGuide().getId()));
			//pc.getEM().merge(course);
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
			
				Course course=	pc.getEM().find(Course.class, id);	
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
	public String updateCourse(Course course) {
		try {
			pc.getEM().merge(course);
			return "Course updated with success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to update course", ex.toString());
		}
	}

	@Override
	public List<Course> findCourseByLevel(String level) {
		List<Course> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.courseLevel LIKE '%" + level + "%'", Course.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Course> findCourseByLocation(String location) {
		List<Course> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.location LIKE '%" + location + "%'", Course.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Course> findCourseByDate(Date date) {
		
		   	 return pc.getEM().createQuery(
				        "SELECT c FROM Courses c WHERE c.date = :date",Course.class)
				        .setParameter("date", date).getResultList(); 
	}

	@Override
	public List<Course> findCourseByState(String state) {
		List<Course> listCourses;

		listCourses = pc.getEM().createQuery("SELECT c FROM Courses c  WHERE c.courseState LIKE '%" + state + "%'", Course.class)
				.getResultList();
		return listCourses;
	}

	@Override
	public List<Course> listAllCourse() {
		String requete = "SELECT c FROM Courses c";
		return pc.getEM().createQuery(requete,Course.class).getResultList();
		
	}

	@Override
	public Course findCourseByID(int id) {
		// TODO Auto-generated method stub
		return pc.getEM().find(Course.class, id);
	}

	@Override
	public List Stat() {
		List rl =new ArrayList();
		rl = pc.getEM().createQuery
//("Select o.courseName as Name ,(count(o.courseName)*100)/(select count(o.courseID) from Courses o )as nbr from Courses o where o.courseID IN (Select r.course.courseID  from CourseReview r  ) GROUP BY o.courseName ").getResultList();
		("SELECT concat('CourseID:',c.courseID),concat('Course Name:', c.courseName), Concat( ( sum(r.rate))/(select (count (rate)) from CourseReview r where r.reviewPK.courseID=c.courseID ),'%') as pourcentage "
				+ "from Courses c, CourseReview r where r.reviewPK.courseID=c.courseID group by c.courseID  "
				+ "order by pourcentage").getResultList();
				//("select c.courseName , (count(r.rate)*100)/(select count(r.course.courseID) from CourseReview r ) from Courses c  group by  r.course.courseID  ").getResultList();
		return rl;
		
		
		/*SELECT  o.offer_type, CONCAT(   ((COUNT(r.id))*100)/(Select count (id) from Reclamation r),'%' ) 
		as pourcentage FROM Offer o,Reclamation r where o.IdOffer in (Select r.offre from Reclamation)
		group by o.offer_type order by pourcentage desc*/
		//"SELECT r.course.courseName, AVG(r.rate) from CourseReview r  GROUP by r.reviewPK.courseID"
		
		//"SELECT concat('CourseID:',c.courseID),concat('Course Name:', c.courseName), Concat( ( AVG(r.rate))/(select (count (rate)/10) from CourseReview r where r.reviewPK.courseID=c.courseID ),'%') as pourcentage "
		//+ "from Courses c, CourseReview r where r.reviewPK.courseID=c.courseID group by c.courseID  "
		//+ "order by pourcentage"
	}

	@Override
	public void addP(CourseParticipation p) {
		
		int id= p.getCourse().getCourseID();
		Course c= findCourseByID(id);
		
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

	@Override
	public Integer getl1() {
		List rl =new ArrayList();
		 String state="Cours_Collectifs";
		rl=  pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.courseLevel LIKE '%" + state + "%'").getResultList();
			return rl.size();        
	}

	@Override
	public Integer getl2() {
		List rl =new ArrayList();
		 String state="Atelier_Technique";
		 rl=  pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.courseLevel LIKE '%" + state + "%'").getResultList();
			return rl.size(); 
	}

	@Override
	public Integer getl3() {
		List rl =new ArrayList();
		 String state="Cours_Particuliers";
		 rl=  pc.getEM().createQuery("SELECT c FROM Courses c WHERE c.courseLevel LIKE '%" + state + "%'").getResultList();
			return rl.size(); 
	}

	
	
	

}
