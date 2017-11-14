package tn.codeinc.webservices;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.persistance.Establishment;
import tn.codeinc.services.EstablishmentInterfaceLocal;

@Path("/secured/etab")
@RequestScoped
public class EstablishmentSecureResource {

	@EJB
	EstablishmentInterfaceLocal AMR;
	@Inject
	//CurrentUserLocal currentUser;
    
	@GET
	
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEstablishment() {
		
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return Response.status(Status.UNAUTHORIZED).build();  }
		
		return Response.ok().entity(AMR.getEstablishment()).build();
	}   
	
	@GET
	@Path("/{id}")
	public String seeDetailsAboutEvent(@PathParam("id") int id)
	{	
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return; }
		
		return AMR.seeDetailsAboutEstablishment(id);
	}
	
	@PUT
	@Produces("application/json")
	@Path("/update")
    public String updateEstablishment(Establishment e)
	{
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return; }
		
    	return AMR.updateEstablishment(e);
    }

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addEstablishment(Establishment e){
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return; }
		
		AMR.addEstablishment(e);
		
    }
	
	@DELETE
	@Path("delet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEstablishment(@PathParam("id") int id) {
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return Response.status(Status.UNAUTHORIZED).build();  }
		
		AMR.delete(id);
		return Response.ok("Establishment deleted").build();
	}
	
	@GET
	@Path("byname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChalet(@PathParam("name") String n) {
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return Response.status(Status.UNAUTHORIZED).build();  }
		
		return Response.status(Status.FOUND).entity(AMR.findEstablishmentByName(n)).build();
	}
	
	@GET
	@Path("byid/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEstablishmentById(@PathParam("id") int id) {
		//	System.out.println(currentUser.get());
		//	if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN) {
			//	return Response.status(Status.UNAUTHORIZED).build();  }
		
		return Response.status(Status.FOUND).entity(AMR.findEstablishmentById(id)).build();
	}
	
	
}
