package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.AdAreaRequestException;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.AdAreaRequestException;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;
import tn.codeinc.persistance.AdAreaPurchaseRequest.AdAreaPurchaseRequestConfirmation;


@Local
public interface AdAreaManagementLocal {
	public List<AdArea> getAll();
	public AdArea get(int id);
	public void delete(AdArea a);
	public void update(AdArea a);
	public void insert(AdArea a);
	public void addPurchaseRequest(AdAreaPurchaseRequest pr) throws ElementNotFoundException, AdAreaRequestDuplicationException;
	public void deletePurchaseRequest(AdAreaPurchaseRequest pr,AdArea a);

}
