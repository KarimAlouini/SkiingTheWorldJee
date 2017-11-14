package tn.codeinc.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.codeinc.persistance.RechargingCoupon;

public class CouponManagement implements CouponManagementRemote,CouponManagementLocal{

	@PersistenceContext
	EntityManager em;
	@Override
	public void add(RechargingCoupon c) {
		em.persist(c);
		
	}

	@Override
	public void delete(RechargingCoupon c) {
		em.remove(c);
		
	}

	@Override
	public RechargingCoupon get(String code) {
		return em.createQuery("from RecharginCoupon c where c.code = :code",RechargingCoupon.class)
				.setParameter("code", code)
				.getSingleResult();
	}

	@Override
	public List<RechargingCoupon> getAll() {
		return em.createQuery("from RechargingCoupon c",RechargingCoupon.class).getResultList();
	}

}
