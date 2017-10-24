package tn.codeinc.services;



import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import tn.codeinc.persistance.Notification;

@Local
public interface NotificationManagementLocal {
 public void addNotif(Notification notif);
 public List<Notification> listAll();
}
