package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.codeinc.persistance.CourseParticipation;


@Local
public interface CourseReservationsManagementLocal {
	public String addReservation(CourseParticipation reservation);
	public void cancelReservation(int x,int y,int z);
	public List<CourseParticipation> listAllReservations();
	public List<CourseParticipation> listAllMyReservations(String user);
	public List<CourseParticipation> findPByCourse(int id);
}
