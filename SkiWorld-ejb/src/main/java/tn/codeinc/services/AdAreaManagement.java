package tn.codeinc.services;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;


@Stateless
public class AdAreaManagement implements AdAreaManagementLocal, AdAreaManagementRemote {
	@PersistenceContext
	private EntityManager em;
	
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
				&& (i.contains(pr.getStartDate().getTime()) || i.contains(pr.getEndDate().getTime())))) {
			throw new AdAreaRequestDuplicationException("You have duplicated your ad area request");
		}
		pr.setUser(currentUser.get());
		pr.setAdArea(a);
		pr.generateId();
		em.persist(pr);
		
		System.out.println("AdAreaManagement.addPurchaseRequest() "+pr);
		

	}

	@Override
	public void deletePurchaseRequest(AdAreaPurchaseRequest pr, AdArea a) {
		a.getPurchaseRequests().remove(pr);
		em.merge(a);

	}

}
