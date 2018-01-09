package tn.codeinc.services;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.*;

@Remote
public interface OffersInterfaceLocal  {
	
	public void addOffers (Offer o);
	public List<Offer> getOffers() ;
	public void deleteOffers(int id)  ;
	public String updateOffers(Offer o) ;
	public Offer findOffersById(int id);
	public String seeDetailsAboutOffers(int id);
	
	////////////////////////////////
	public void addLodging (Lodging l) throws ParseException;
	public List<Lodging> getLodging();
	public String updateLodging(Lodging l);
	public Lodging findLodgingById(int id);	
}
