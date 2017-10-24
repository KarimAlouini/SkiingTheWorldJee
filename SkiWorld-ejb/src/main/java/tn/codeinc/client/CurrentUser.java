package tn.codeinc.client;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import tn.codeinc.persistance.User;

@Stateful
@SessionScoped
public class CurrentUser implements CurrentUserLocal,CurrentUserRemote {

	private User user;
	@Override
	public void set(User user) {
		this.user = user;
		
	}

	@Override
	public User get() {
		return this.user;
	}
	
	public CurrentUser() {
		// TODO Auto-generated constructor stub
	}

}
