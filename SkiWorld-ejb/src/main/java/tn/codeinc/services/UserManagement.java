package tn.codeinc.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.core.UriInfo;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.exceptions.UserException;
import tn.codeinc.persistance.AccessToken;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;

@Stateless
public class UserManagement implements UserManagementRemote, UsersManagementLocal {

	@Inject
	TokenManagementLocal tokens;

	@Inject
	private MailSenderLocal mailSender;
	@Inject
	PersistanceContextLocal pc;

	@EJB
	CurrentUserLocal cu;

	public UserManagement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> getAll() {

		return pc.getEM().createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	@Override
	public User get(int id) {
		return pc.getEM().find(User.class, id);
	}

	@Override
	public void remove(User user) {
		pc.getEM().remove(user);

	}

	@Override
	public void update(User user) {
		pc.getEM().merge(user);

	}

	@Override
	public User getByMail(String mail) {

		try {
			return pc.getEM().createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
					.setParameter("email", mail).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public User getByLogin(String login) {
		try {
			return pc.getEM().createQuery("SELECT u FROM User u WHERE u.login = :login", User.class)
					.setParameter("login", login).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public User getByPhoneNumber(String phoneNumber) {
		try {
			return pc.getEM().createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber", User.class)
					.setParameter("phoneNumber", phoneNumber).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void insert(User user) {
		pc.getEM().merge(user);

	}

	@Override
	public User getByConfirmationCode(String confirmationCode) {
		try {
			return pc.getEM()
					.createQuery("SELECT u FROM User u WHERE u.confirmationCode = :confirmationCode", User.class)
					.setParameter("confirmationCode", confirmationCode).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<User> getByRole(UserRole role) {
		return pc.getEM().createQuery("SELECT u from User u WHERE u.role = :role", User.class)
				.setParameter("role", role).getResultList();
	}

	@Override
	public List<User> getBanned() {
		// TODO Auto-generated method stub
		return pc.getEM().createQuery("SELECT u from User u WHERE u.isBanned = true", User.class).getResultList();
	}

	@Override
	public List<User> getActive() {
		return pc.getEM().createQuery("SELECT u from User u WHERE u.isBanned = false", User.class).getResultList();
	}

	@Override
	public AccessToken login(String login, String password) throws AuthenticationException {
		if (getByLogin(login) == null && getByMail(login) == null && getByPhoneNumber(login) == null) {
			throw new AuthenticationException("Account not found !");
		}

		else {
			User u = null;
			User userByLogin = getByLogin(login);
			User userByMail = getByMail(login);
			User userByPhoneNumber = getByPhoneNumber(login);
			if (userByLogin != null)
				u = userByLogin;
			if (userByMail != null)
				u = userByMail;
			if (userByPhoneNumber != null)
				u = userByPhoneNumber;

			if (u.getPassword().equals(password)) {
				if (u.isConfirmed()) {
					if (u.isBanned()) {
						throw new AuthenticationException("You have been banned !");

					} else {

						cu.set(u);

						AccessToken t = tokens.getLastPerUser(u);

						if (t != null && t.isValid())
							return t;

						else {
							AccessToken newToken = new AccessToken();
							newToken.generate();
							newToken.setUser(u);
							tokens.add(newToken);
							System.out.println(newToken.getValue());

							return newToken;

						}
					}
				} else {
					throw new AuthenticationException("Your account isn't confirmed");
				}

			} else {
				throw new AuthenticationException("Your password seems to be incorrect");
			}

		}

	}

	@Override
	public void signUp(User user) throws AuthenticationException {

		if (getByLogin(user.getLogin()) != null) {
			throw new AuthenticationException("A user with this username is already registered");
		} else if (getByMail(user.getEmail()) != null) {
			throw new AuthenticationException("A user with this email is already registered");

		}

		else if (getByPhoneNumber(user.getPhoneNumber()) != null) {
			throw new AuthenticationException("A user with this phone number is already registered");
		}

		else {

			try {
				mailSender.sendConfirmation(user);
			} catch (RuntimeException e) {
				e.printStackTrace();
				user.setConfirmed(true);
			}
			insert(user);

		}

	}

	@Override
	public void confirm(String code) throws AuthenticationException {

		User user = getByConfirmationCode(code);

		if (user != null) {
			if (!user.isConfirmed()) {
				user.setConfirmed(true);
				user.setConfirmationCode(null);
				update(user);
			}
			else
				throw new AuthenticationException("You have been already confirmed");

		} else {
			throw new AuthenticationException("The registration link is invalid");
		}

	}

	@Override
	public void resendConfirmation(String email) throws UserException {
		User u = getByMail(email);
		if (u == null)
			throw new UserException("No account related to this email");
		else {
			mailSender.resendConfirmation(u);
		}

	}

}
