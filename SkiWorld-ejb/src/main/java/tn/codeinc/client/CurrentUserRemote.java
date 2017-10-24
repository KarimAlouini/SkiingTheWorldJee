package tn.codeinc.client;

import tn.codeinc.persistance.User;

public interface CurrentUserRemote {
	public void set(User user);
	public User get();

}
