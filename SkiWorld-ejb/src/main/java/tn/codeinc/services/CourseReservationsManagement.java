package tn.codeinc.services;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.CourseParticipation;
import tn.codeinc.persistance.CourseState;

/**
 * Session Bean implementation class CourseReservationsManagement
 */
@Stateless
@LocalBean
public class CourseReservationsManagement implements CourseReservationsManagementRemote {
	@Inject
	PersistanceContextLocal pc;
	@Inject
	CourseManagementLocal courseM;
	@Inject
	CurrentUserLocal currentUser;

	/**
	 * Default constructor.
	 */
	public CourseReservationsManagement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String addReservation(CourseParticipation reservation) {
		
	/*if (reservation.getCourse().getCourseState() == CourseState.AVAILABLE){
		System.out.println("boucle1");
		
			if((reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size())>0){
				System.out.println("boucle2");
				if((reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size())>reservation.getParticipationPK().getNbrPlaces()){
					System.out.println("places dispo :"+(reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size()));
					pc.getEM().persist(reservation);
					System.out.println("Reservation added succesfully");
					return "Reservation added succesfully";
				}
				else{
					System.out.println("There is no enough places to reserve");
					return "There is no enough places to reserve  ";
				}
			}
			else{
				
				reservation.getCourse().setCourseState(CourseState.FULL);
				System.out.println("This class is full");
				return "This class is full";
			}
		}else{
			System.out.println("This course is no longer available");
			return "This course is no longer available";
		}*/
	
	if (reservation.getCourse().getCourseState() == CourseState.AVAILABLE){
		if((reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size())>0){
			System.out.println("aaaaa"+reservation.getCourse().getParticipant().size());
			if(((reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size())-reservation.getParticipationPK().getNbrPlaces() )>=0){
				//course.setGuide(userM.get(course.getGuide().getId()));
				reservation.setCourse(courseM.findCourseByID(reservation.getCourse().getCourseID()));
				reservation.setUser(currentUser.get());
				System.out.println("avant"+reservation.getCourse().getParticipant().size());
				for(int i=0;i<reservation.getParticipationPK().getNbrPlaces();i++){
					(courseM.findCourseByID(reservation.getCourse().getCourseID())).getParticipant().add(reservation);
					
					pc.getEM().persist(reservation);
					
					System.out.println(reservation.getCourse().getParticipant().add(reservation));
					
				
					//reservation.getCourse().setParticipant(reservation.getCourse().getParticipant());
				}
				pc.getEM().merge(reservation);
				 CourseManagement cm=new CourseManagement();
				// cm.addP(reservation);
				System.out.println("apres"+reservation.getCourse().getParticipant().size());
				//System.out.println(reservation.getCourse().getParticipant().size());
				
				System.out.println("Reservation added succesfully");
				if(((reservation.getCourse().getMaxParticipants()-reservation.getCourse().getParticipant().size())-reservation.getParticipationPK().getNbrPlaces() )==0){
					reservation.getCourse().setCourseState(CourseState.FULL);
					System.out.println("This class is full");
					return "This class is full";
				}
				else{
					return " ";
				}
			 //return "Reservation added succesfully";
			}
			else{
				System.out.println("There is no enough places to reserve");
				return "There is no enough places to reserve  ";
			}
		}
		else{
			//reservation.getCourse().setCourseState(CourseState.FULL);
			System.out.println("This class is full");
			return "This class is full";
			
		}
	}
	else{
		System.out.println("This course is no longer available");
		return "This course is no longer available";
	}
	
	
	
		
		//pc.getEM().persist(reservation);
		//return "Reservation added succesfully";
		
	}

	@Override
	public void cancelReservation(int a,int b,int c) {
		//CourseParticipation p;
		//p=pc.getEM().createQuery("SELECT r FROM CourseParticipation courseID=:a and idP=:b and userID=:c",
			//	CourseParticipation.class).setParameter("a", a).setParameter("b", b).setParameter("c", c).getSingleResult();
		
		pc.getEM().createQuery
		("DELETE FROM CourseParticipation where courseID="+a+" and idP="+b+" and userID="+c)
			.executeUpdate();
		
		
	}

	@Override
	public List<CourseParticipation> listAllReservations() {
		String requete = "SELECT r FROM CourseParticipation r";
		return pc.getEM().createQuery(requete, CourseParticipation.class).getResultList();
	}

	@Override
	public List<CourseParticipation> listAllMyReservations(String user) {

		try {
			return pc.getEM().createQuery("SELECT r FROM CourseParticipation r WHERE r.user.firstName = :name",
					CourseParticipation.class).setParameter("name", user).getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<CourseParticipation> findPByCourse(int id) {
		
			return pc.getEM().createQuery("SELECT r FROM CourseParticipation r WHERE courseID = "+id)
					.getResultList();

	}

}
