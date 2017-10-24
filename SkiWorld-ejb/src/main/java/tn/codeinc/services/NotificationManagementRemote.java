package tn.codeinc.services;



import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.Notification;

@Remote
public interface NotificationManagementRemote {
 public void addNotif(Notification notif);
 public List<Notification> listAll();
}
