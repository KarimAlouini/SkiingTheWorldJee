package tn.codeinc.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.codeinc.persistance.OfferMessage;
import tn.codeinc.persistance.User;

@Local
public interface OfferMessageManagementLocal {
	public void create(OfferMessage offerMessage);
	public List<OfferMessage> getAll();
	public OfferMessage get(int id);
	public void remove(OfferMessage offerMessage);
	public void update(OfferMessage offerMessage);
	public OfferMessage getByDate(Date messageDate);
	public List<OfferMessage> getByUsers(User agent , User client);

}
