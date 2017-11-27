package tn.codeinc.services;

import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.RechargingCoupon;

@Remote
public interface RechargingCouponRemote {
	public List<RechargingCoupon> getAll();
	

}
