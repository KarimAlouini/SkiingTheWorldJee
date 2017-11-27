package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;

import tn.codeinc.persistance.RechargingCoupon;

@Local
public interface RechargingCouponLocal {
	public List<RechargingCoupon> getAll();
	public void add(RechargingCoupon c);
	public void generate(Integer count,Integer amount);
	public List<RechargingCoupon> getAllPaginated(int page);

}
