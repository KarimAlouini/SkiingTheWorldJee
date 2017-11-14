
package tn.codeinc.webservices;

import java.text.ParseException;
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
import tn.codeinc.persistance.Lodging;
import tn.codeinc.services.OffersInterfaceLocal;

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
	
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addLodging(Lodging l){
		
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return; }
		try {
			LI.addLodging(l);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

	


}
