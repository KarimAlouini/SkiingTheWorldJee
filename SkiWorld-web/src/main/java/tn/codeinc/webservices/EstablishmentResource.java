package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

import tn.codeinc.persistance.*;
import tn.codeinc.services.*;

@Path("/etablis")
@RequestScoped
public class EstablishmentResource {

	@EJB
	EstablishmentInterfaceLocal AMR;
    
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstablishment() {
		return Response.ok().entity(AMR.getEstablishment()).build();
	}   
	
	@GET
	@Path("/{id}")
	public String seeDetailsAboutEvent(@PathParam("id") int id)
	{
		return AMR.seeDetailsAboutEstablishment(id);
	}
	
	
	@GET
	@Path("byname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChalet(@PathParam("name") String n) {
		return Response.status(Status.FOUND).entity(AMR.findEstablishmentByName(n)).build();
	}
	
	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEstablishmentById(@PathParam("id") int id) {
		return Response.status(Status.FOUND).entity(AMR.findEstablishmentById(id)).build();
	}
	
	
}
