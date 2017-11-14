
package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.services.OffersInterfaceLocal;

@Path("/offer")
@RequestScoped
public class OffersResource {

	@EJB
	OffersInterfaceLocal OI;
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffers() {
		return Response.ok().entity(OI.getOffers()).build();
	}

	
	@GET
	@Path("/{id}")
	public String seeDetailsAboutOffers(@PathParam("id") int id)
	{
		return OI.seeDetailsAboutOffers(id);
	}
	
	
	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOffersById(@PathParam("id") int id) {
		return Response.status(Status.FOUND).entity(OI.findOffersById(id)).build();
	}
	


}