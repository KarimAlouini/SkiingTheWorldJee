package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;

import tn.codeinc.exceptions.TokenNotExistantException;
import tn.codeinc.persistance.AccessToken;
import tn.codeinc.persistance.User;

@Local
public interface TokenManagementLocal {
	public List<AccessToken> getAll();
	public void add(AccessToken at);
	public AccessToken get(String value);
	public AccessToken getLastPerUser(User u);
	public AccessToken refresh(String token) throws TokenNotExistantException;

}
