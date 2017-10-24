package tn.codeinc.client;

import javax.ejb.Local;

import tn.codeinc.persistance.User;
@Local
public interface CurrentUserLocal {
	public void set(User user);
	public User get();

}
