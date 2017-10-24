package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.User;

@Remote
public interface UserManagementRemote {
	public List<User> getAll();
	public void remove(User user);

}
