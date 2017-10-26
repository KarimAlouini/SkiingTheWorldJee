
package tn.codeinc.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.codeinc.persistance.*;

/**
 * Session Bean implementation class TestLevelManagement
 */
@Stateless
@LocalBean
public class OfferMessageService  implements OfferMessageServiceLocal{
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public OfferMessageService() {
        // TODO Auto-generated constructor stub
    }


	@Override
	public String OfferMessage(OfferMessage msg)
	{
		try {
		
			em.persist(msg);
			return "msg added succesfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return String.format("Failed to save Message", ex.toString());
		}
		
	}

	@Override
	public List<OfferMessage> listAllOfferMessageUser(String user) {
		
		try{
		   	 return em.createQuery(
				        "SELECT t FROM OfferMessage t WHERE t.user.firstName = :user",OfferMessage.class)
				        .setParameter("user", user).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
	}

	@Override
	public List<OfferMessage> listByDate(String user) {
		try{
		   	 return em.createQuery(
				        "SELECT t FROM OfferMessage t WHERE t.user.firstName = :user order by t.date",OfferMessage.class)
				        .setParameter("user", user).getResultList();
	    } catch(Exception e) {
	        return null;
	    }
	}

	@Override
	public List<OfferMessage> getOfferMessage() {
		
			return em.createQuery("SELECT e FROM OfferMessage e ",OfferMessage.class).getResultList();
	   

	}
	

	@Override
	public void deleteOfferMessage(int id) {
		
		em.remove(em.merge(em.find(OfferMessage.class, id)));		
	}


}