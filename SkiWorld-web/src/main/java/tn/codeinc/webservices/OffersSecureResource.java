
package tn.codeinc.webservices;

import java.text.ParseException;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.*;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.*;

@Path("/secured/offers")
@RequestScoped
public class OffersSecureResource {

	@EJB
	OffersInterfaceLocal OI;
	@Inject
	CurrentUserLocal currentUser;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffers() {
			System.out.println(currentUser.get());
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
				return Response.status(Status.UNAUTHORIZED).build();  }
		return Response.ok().entity(OI.getOffers()).build();
	}

	
	@GET
	@Path("/{id}")
	public String seeDetailsAboutOffers(@PathParam("id") int id)
	{		
		return OI.seeDetailsAboutOffers(id);
	}
	
	@PUT
	@Produces("application/json")
	@Path("/update")
    public String updateOffers(Offer o)
	{			System.out.println(currentUser.get());
			if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
		return "error"; }
    	return OI.updateOffers(o);
    }

	@POST
	@Path("/add1")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addOffers(Offer o){
			System.out.println(currentUser.get());
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
				return; }
		OI.addOffers(o);
		
    }
	
	@DELETE
	@Path("delet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOffers(@PathParam("id") int id) {
			System.out.println(currentUser.get());
			if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
				return Response.status(Status.UNAUTHORIZED).build();  }
		OI.deleteOffers(id);
		return Response.ok("Offers deleted").build();
	}

	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOffersById(@PathParam("id") int id) {
			System.out.println(currentUser.get());
			if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
				return Response.status(Status.UNAUTHORIZED).build();  }
		return Response.status(Status.FOUND).entity(OI.findOffersById(id)).build();
	}
	


}