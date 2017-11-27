package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.RechargingCoupon;

@Remote
public interface CouponManagementRemote {
	public void add(RechargingCoupon c);
	public void delete(RechargingCoupon c);
	public RechargingCoupon get(String code);
	public List<RechargingCoupon> getAll();
	

}
