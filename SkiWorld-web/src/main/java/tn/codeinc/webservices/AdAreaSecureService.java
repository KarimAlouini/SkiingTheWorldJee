package tn.codeinc.webservices;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.AdAreaManagementLocal;
import tn.codeinc.util.ResponseMessage;


@Path("/secured/ad_areas")
@RequestScoped
public class AdAreaSecureService {
	
	

	@Inject
	private CurrentUserLocal currentUser;

	@Inject
	private AdAreaManagementLocal adAreas;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		return Response.ok().entity(adAreas.getAll()).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(AdArea adArea) {
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		adAreas.insert(adArea);
		return Response.ok().entity(new ResponseMessage(0, "Added successfully")).build();
	}

	@PUT
	@Path("/request")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRequest(AdAreaPurchaseRequest request) {
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();

		request.setUser(currentUser.get());
		try {
			adAreas.addPurchaseRequest(request);
		} catch (ElementNotFoundException e) {

			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(new ResponseMessage(1, e.getMessage())).build();
		} catch (AdAreaRequestDuplicationException e) {

			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(new ResponseMessage(1, e.getMessage())).build();
		}

		return Response.ok().entity(new ResponseMessage(0, "Added successfully")).build();
	}
	

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAdArea(AdArea adArea){
		System.out.println("AdAreaSecureService.deleteAdArea() "+adArea);
		if(currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		adAreas.delete(adArea);
		return Response.ok().entity(new ResponseMessage(0)).build();
	}
	
}
