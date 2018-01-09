
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

import tn.codeinc.persistance.*;
import tn.codeinc.services.*;

@Path("/logding")
@RequestScoped
public class LogdingResource {

	@EJB
	OffersInterfaceLocal LI;
	@Inject
	//CurrentUserLocal currentUser;
	
    
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLodging() {
		return Response.ok().entity(LI.getLodging()).build();
	}
	

	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLodgingById(@PathParam("id") int id) {

		
		return Response.status(Status.OK).entity(LI.findLodgingById(id)).build();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addLodging(Lodging l){
		

		try {
			LI.addLodging(l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	


}
