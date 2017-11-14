package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;

import tn.codeinc.persistance.RechargingCoupon;

@Local
public interface CouponManagementLocal {
	public void add(RechargingCoupon c);
	public void delete(RechargingCoupon c);
	public RechargingCoupon get(String code);
	public List<RechargingCoupon> getAll();
	

}
