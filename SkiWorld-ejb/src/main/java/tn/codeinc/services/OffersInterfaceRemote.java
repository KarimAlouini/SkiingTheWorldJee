package tn.codeinc.services;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;

import tn.codeinc.persistance.*;

@Remote
public interface OffersInterfaceRemote  {
	
	public List<Offer> getOffers() ;
	public Offer findOffersById(int id);
	public String seeDetailsAboutOffers(int id);
	
	////////////////////////////////

	public List<Lodging> getLodging();
	public Lodging findLodgingById(int id);
		
}
