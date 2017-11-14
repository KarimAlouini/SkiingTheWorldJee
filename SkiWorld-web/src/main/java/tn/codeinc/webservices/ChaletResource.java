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
import tn.codeinc.persistance.Chalet;
import tn.codeinc.services.EstablishmentInterfaceLocal;

@Path("/chalet")
@RequestScoped
public class ChaletResource {

	@EJB
	EstablishmentInterfaceLocal CT;
	
	
    
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getC() {
		return Response.ok().entity(CT.getChal()).build();
	}
	
	

	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addC(Chalet c){
		CT.addChalet(c);
		
    }
		
	
}
