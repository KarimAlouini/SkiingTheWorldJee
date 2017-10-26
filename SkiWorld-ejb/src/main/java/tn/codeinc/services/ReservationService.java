

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
public class ReservationService  implements ReservationInterfaceLocal{
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ReservationService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addReservation(Reservation r) { 
		
		em.persist(r);	
		
	}
	@Override
	public List<Reservation> getReservation() {
		
			return em.createQuery("SELECT e FROM Reservation e ",Reservation.class).getResultList();
	   

	}



}