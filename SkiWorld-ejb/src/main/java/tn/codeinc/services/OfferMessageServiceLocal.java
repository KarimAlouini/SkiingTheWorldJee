
package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import tn.codeinc.persistance.OfferMessage;



@Local
public interface OfferMessageServiceLocal {
	public String OfferMessage(OfferMessage msg);
	public List<OfferMessage> listAllOfferMessageUser(String user);
	public List<OfferMessage> listByDate(String user);
	public List<OfferMessage> getOfferMessage();
	public void deleteOfferMessage(int id);
}
