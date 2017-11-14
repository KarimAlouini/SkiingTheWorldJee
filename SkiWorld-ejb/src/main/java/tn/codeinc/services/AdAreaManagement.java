package tn.codeinc.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.AdAreaRequestException;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;
import tn.codeinc.persistance.AdAreaPurchaseRequest.AdAreaPurchaseRequestConfirmation;

@Stateless
public class AdAreaManagement implements AdAreaManagementLocal, AdAreaManagementRemote {
	@PersistenceContext
	private EntityManager em;

	@Inject
	UsersManagementLocal usersManagement;

	@Inject
	CurrentUserLocal currentUser;

	@Override
	public List<AdArea> getAll() {
		Interval i = new Interval(new Date().getTime(), new DateTime(new Date().getTime()).plusHours(2).getMillis());
		System.out.println(
				"AdAreaManagement.getAll() " + i.contains(new DateTime(new Date().getTime()).plusHours(1).getMillis()));
		return em.createQuery("SELECT a FROM AdArea a", AdArea.class).getResultList();
	}

	@Override
	public AdArea get(int id) {
		try {
			return em.createQuery("SELECT a from AdArea a where a.id = :id", AdArea.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void delete(AdArea a) {
		em.remove(a);

	}

	@Override
	public void update(AdArea a) {
		em.merge(a);

	}

	@Override
	public void insert(AdArea a) {
		em.persist(a);

	}

	@Override
	public void addPurchaseRequest(AdAreaPurchaseRequest pr)
			throws ElementNotFoundException, AdAreaRequestDuplicationException {

		AdArea a = get(pr.getAdArea().getId());

		if (a == null)
			throw new ElementNotFoundException("the given ad area doesn't exist");

		Interval i = new Interval(pr.getStartDate().getTime(), pr.getEndDate().getTime());

		if (a.getPurchaseRequests().stream().anyMatch(req -> req.getUser().equals(pr.getUser())
				&& (i.contains(req.getStartDate().getTime()) || i.contains(req.getEndDate().getTime())))) {
			throw new AdAreaRequestDuplicationException("You have duplicated your ad area request");
		}
		pr.setUser(usersManagement.get(currentUser.get().getId()));
		pr.setAdArea(a);
		// pr.generateId();
		em.persist(pr);

	}

	@Override
	public List<AdAreaPurchaseRequest> getPurchasesRequests(AdArea adArea) throws ElementNotFoundException {
		AdArea a = get(adArea.getId());
		if (a == null)
			throw new ElementNotFoundException("the given ad area doesn't exist");
		return a.getPurchaseRequests();
	}

	@Override
	public List<AdAreaPurchaseRequest> getPurchaseRequestByType(AdArea adArea, AdAreaPurchaseRequestConfirmation conf)
			throws ElementNotFoundException {
		AdArea a = get(adArea.getId());
		if (a == null)
			throw new ElementNotFoundException("the given ad area doesn't exist");
		return a.getPurchaseRequests().stream().filter(pr -> pr.getConfirmation() == conf).collect(Collectors.toList());
	}

	@Override
	public List<AdAreaPurchaseRequest> getConnectedUserPurchaseRequest() {
		return em.createQuery("SELECT pr FROM AdAreaPurchaseRequest pr WHERE pr.user = :user)",
				AdAreaPurchaseRequest.class).setParameter("user", currentUser.get()).getResultList();
	}

	@Override
	public AdAreaPurchaseRequest getPurchaseRequest(Integer id) throws NoResultException {
		return em.find(AdAreaPurchaseRequest.class, id);
	}

	@Override
	public void deletePurchaseRequest(AdAreaPurchaseRequest pr) throws ElementNotFoundException,AuthorizationException, AdAreaRequestException {
		AdAreaPurchaseRequest adAreaPurchaseRequest = getPurchaseRequest(pr.getId());
		if (adAreaPurchaseRequest == null)
			throw new ElementNotFoundException("The provided purchase request wasn't found");
		
		if(!currentUser.get().equals(adAreaPurchaseRequest.getUser())){
			throw new AuthorizationException();
		}
		
		if(adAreaPurchaseRequest.getConfirmation() == AdAreaPurchaseRequestConfirmation.ACCEPTED)
			throw new AdAreaRequestException("The request has been already confirmed");
		
		
		
	}

	@Override
	public void acceptPurchaseRequest(AdAreaPurchaseRequest req) throws ElementNotFoundException {
		AdAreaPurchaseRequest pr = getPurchaseRequest(req.getId());
		if (pr == null)
			throw new ElementNotFoundException();
		pr.setConfirmation(AdAreaPurchaseRequestConfirmation.ACCEPTED);
		em.merge(pr);
		
		
	}

	@Override
	public void refusePurchaseRequest(AdAreaPurchaseRequest req) throws ElementNotFoundException {
		
		
		AdAreaPurchaseRequest pr = getPurchaseRequest(req.getId());
		if (pr == null)
			throw new ElementNotFoundException();
		pr.setConfirmation(AdAreaPurchaseRequestConfirmation.ACCEPTED);
		em.merge(pr);
		
	}

}
