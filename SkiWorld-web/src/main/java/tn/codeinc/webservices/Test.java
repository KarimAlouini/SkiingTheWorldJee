package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tn.codeinc.client.UserCartLocal;

@Path("/test")
public class Test {
	
	@EJB
	UserCartLocal cart;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response number(){
		return Response.ok().entity(cart.getNumber()).build();
	}

	@POST
	
	public void set(){
		cart.setNumber();
	}
}
