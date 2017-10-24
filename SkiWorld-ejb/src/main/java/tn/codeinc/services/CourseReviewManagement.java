package tn.codeinc.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.CourseReview;
import tn.codeinc.persistance.CourseState;

/**
 * Session Bean implementation class CourseReviewManagement
 */
@Stateless
@LocalBean
public class CourseReviewManagement implements CourseReviewManagementRemote{
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public CourseReviewManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String addReview(CourseReview review) {
		try {
			if(review.getCourse().getCourseState()!=CourseState.CANCELLED){
				em.persist(review);
				System.out.println("Review added succesfully");
				return "Review added succesfully";
			}
			else{
				System.out.println("This course is canceled! You can't give a review");
				return "This course is canceled! You can't give a review";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace(); 
			return String.format("Failed to add review", ex.toString());
		}
	}

	

	@Override
	public String updateReview(CourseReview review) {
		try {
			em.merge(review);
			return "Review updated with success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to update review", ex.toString());
		}
	}

	@Override
	public List<CourseReview> findReviewByCourse(String courseName) {
		
		
		 try{
		   	 return em.createQuery(
				        "SELECT r FROM CourseReview r WHERE r.course.courseName = :coursename",CourseReview.class)
				        .setParameter("coursename", courseName).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
	}

	@Override
	public List<CourseReview> findReviewByParticipant(String participantName) {
		
		
		 try{
		   	 return em.createQuery(
				        "SELECT r FROM CourseReview r WHERE r.user.firstName = :name",CourseReview.class)
				        .setParameter("name", participantName).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
		
	}

	@Override
	public List<CourseReview> findReviewByRate(int rate) {
		List<CourseReview> listReviews;

		listReviews = em.createQuery("SELECT r FROM CourseReview r  WHERE r.rate LIKE '%" + rate + "%'", CourseReview.class)
				.getResultList();
		return listReviews;
	}

	@Override
	public List<CourseReview> listAllReviews() {
		List<CourseReview> listReviews;
		listReviews = em.createQuery("Select cr from CourseReview cr").getResultList();
		return listReviews;
	}


	@Override
	public CourseReview findReviewByID(int id) {
		// TODO Auto-generated method stub
		return em.find(CourseReview.class, id);
	}

	@Override
	public void deleteReview(int a,int b,int c) {
		// TODO Auto-generated method stub
		em.createQuery
			("DELETE FROM CourseReview where courseID="+a+" and reviewID="+b+" and userID="+c)
				.executeUpdate()
				;
		//Query query = (Query) em.createQuery("DELETE c  FROM CourseReview c where c.courseID ='%" + idC + "%' and c.reviewID='%" + idR + "%' and c.userID='%" + idU + "%' ");
		//((javax.persistence.Query) query).executeUpdate();
		
		//em.remove(em.find(CourseReview.class, id));
		
	}
	

	
	

}
