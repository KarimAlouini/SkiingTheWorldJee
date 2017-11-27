package tn.codeinc.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.RechargingCoupon;

@Stateless
public class RechargingCouponManagement implements RechargingCouponLocal, RechargingCouponRemote {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<RechargingCoupon> getAll() {
		return em.createQuery("from RechargingCoupon c", RechargingCoupon.class).getResultList();
	}

	@Override
	public void add(RechargingCoupon c) {
		em.persist(c);

	}

	@Override
	public void generate(Integer count, Integer amount) {

		for (int i = 0; i < amount; i++) {
			RechargingCoupon c = new RechargingCoupon(amount);
			add(c);
		}
	}

	@Override
	public List<RechargingCoupon> getAllPaginated(int page) {
		int firstResult = 1;
		if (page != 1)
			firstResult = (page*10 + 1)-10;
		return em.createQuery("from RechargingCoupon c", RechargingCoupon.class).setMaxResults((10))
				.setFirstResult(firstResult).getResultList();
	}

}
