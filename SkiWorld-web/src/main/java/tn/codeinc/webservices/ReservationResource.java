
package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.Lodging;
import tn.codeinc.persistance.Reservation;
import tn.codeinc.persistance.User;
import tn.codeinc.services.ReservationInterfaceLocal;

@RequestScoped
@Path("/res")
public class  ReservationResource {
	@EJB
	
	ReservationInterfaceLocal R;
	@Inject
	CurrentUserLocal currentUser;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservation() {
		return Response.ok().entity(R.getReservation()).build();
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addReservation(Reservation res) {
		Lodging l = new Lodging();
		User user= currentUser.get();
		res.setUser(user);
		if(l.getEtat().equals("libre")){ 
		R.addReservation(res);
		return Response.status(200).build();
		
		//return Response.status(Status.CREATED).entity(courseMetier.addCourse(course)).build();
	}
		return null;
	}
	
	
}
