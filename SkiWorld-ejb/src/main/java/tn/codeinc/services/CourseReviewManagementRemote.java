package tn.codeinc.services;


import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.CourseReview;

@Remote
public interface CourseReviewManagementRemote {
	public String addReview(CourseReview review);
	public void deleteReview(int x,int y,int z);
	public String updateReview(CourseReview review);
	public List<CourseReview> findReviewByCourse(String courseName);
	public CourseReview findReviewByID(int id);
	public List<CourseReview> findReviewByParticipant(String participantName);
	public List<CourseReview> findReviewByRate(int rate);
	public List<CourseReview> listAllReviews();
	
}
