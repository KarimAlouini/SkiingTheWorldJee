package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.AccessToken;

@Remote
public interface TokenManagementRemote {
	public List<AccessToken> getAll();

}
