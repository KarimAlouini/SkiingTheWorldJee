package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
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
	public void deletePurchaseRequest(AdAreaPurchaseRequest pr) throws ElementNotFoundException,AuthorizationException,AdAreaRequestException;
	public List<AdAreaPurchaseRequest> getPurchasesRequests(AdArea adArea)throws ElementNotFoundException;
	public List<AdAreaPurchaseRequest> getPurchaseRequestByType(AdArea adArea,AdAreaPurchaseRequestConfirmation conf) throws ElementNotFoundException;
	public List<AdAreaPurchaseRequest> getConnectedUserPurchaseRequest();
	public AdAreaPurchaseRequest getPurchaseRequest(Integer id); 
	public void acceptPurchaseRequest(AdAreaPurchaseRequest req) throws ElementNotFoundException;
	public void refusePurchaseRequest(AdAreaPurchaseRequest req) throws ElementNotFoundException;
	public List<AdAreaPurchaseRequest> getByAdArea(Integer id) throws ElementNotFoundException;

}
