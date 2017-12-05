package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.NoResultException;

import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.AdAreaRequestException;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;
import tn.codeinc.persistance.AdAreaPurchaseRequest.AdAreaPurchaseRequestStatus;
import tn.codeinc.persistance.AdAreaPurchaseRequestId;

@Local
public interface AdAreaManagementLocal {
	public List<AdArea> getAll();

	public AdArea get(int id);

	public void delete(AdArea a);

	public void update(AdArea a);

	public void insert(AdArea a);

	public void addPurchaseRequest(AdAreaPurchaseRequest pr)
			throws ElementNotFoundException, AdAreaRequestDuplicationException;

	public void deletePurchaseRequest(AdAreaPurchaseRequestId id);

	public List<AdAreaPurchaseRequest> getPurchasesRequests(AdArea adArea) throws ElementNotFoundException;

	public List<AdAreaPurchaseRequest> getPurchaseRequestByType(AdArea adArea, AdAreaPurchaseRequestStatus conf)
			throws ElementNotFoundException;

	public List<AdAreaPurchaseRequest> getConnectedUserPurchaseRequest();

	public AdAreaPurchaseRequest getPurchaseRequest(AdAreaPurchaseRequestId id) throws NoResultException;

	public void deletePurchaseRequest(AdAreaPurchaseRequest pr)
			throws ElementNotFoundException, AuthorizationException, AdAreaRequestException;

	public void acceptPurchaseRequest(AdAreaPurchaseRequest req) throws ElementNotFoundException;

}
