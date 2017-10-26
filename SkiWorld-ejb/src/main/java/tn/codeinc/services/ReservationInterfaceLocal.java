
package tn.codeinc.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import tn.codeinc.persistance.OfferMessage;
import tn.codeinc.persistance.Reservation;



@Local
public interface ReservationInterfaceLocal {
	public void addReservation(Reservation r);
	public List<Reservation> getReservation();

}
