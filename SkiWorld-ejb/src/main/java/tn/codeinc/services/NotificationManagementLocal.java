package tn.codeinc.services;



import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.codeinc.persistance.CourseNotification;

@Local
public interface NotificationManagementLocal {
 public void addNotif(CourseNotification notif);
 public List<CourseNotification> listAll();
}
