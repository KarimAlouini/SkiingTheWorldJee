package tn.codeinc.services;



import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.CourseNotification;

@Remote
public interface NotificationManagementRemote {
 public void addNotif(CourseNotification notif);
 public List<CourseNotification> listAll();
}
