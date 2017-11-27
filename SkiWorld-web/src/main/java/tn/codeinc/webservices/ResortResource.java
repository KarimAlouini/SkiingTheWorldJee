package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.codeinc.persistance.Resort;
import tn.codeinc.services.EstablishmentInterfaceLocal;

@Path("/resort")
@RequestScoped
public class ResortResource {

	@EJB
	EstablishmentInterfaceLocal RT;
	
	
    
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResort() {
		return Response.ok().entity(RT.getResort()).build();
	}
	
	

	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addC(Resort r){
		RT.addResort(r);
		
    }
		
	
}
