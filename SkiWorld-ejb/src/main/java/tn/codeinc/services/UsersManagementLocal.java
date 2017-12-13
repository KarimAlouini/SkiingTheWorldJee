package tn.codeinc.services;

import java.util.List;
import javax.ejb.Local;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.exceptions.UserException;
import tn.codeinc.persistance.AccessToken;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;

@Local
public interface UsersManagementLocal {
	public List<User> getAll();
	public User get(int id);
	public void remove(User user);
	public void update(User user);
	public User getByMail(String mail);
	public User getByLogin(String login);
	public User getByPhoneNumber(String phoneNumber);
	public void insert(User user);
	public User getByConfirmationCode(String confirmationCode);
	public List<User> getByRole(UserRole role);
	public List<User> getBanned();
	public List<User> getActive();
	public AccessToken login(String login,String password) throws AuthenticationException ;
	public void signUp(User user) throws AuthenticationException;
	public void confirm(String code) throws AuthenticationException;
	public void resendConfirmation(String email) throws UserException;
	
	
}
