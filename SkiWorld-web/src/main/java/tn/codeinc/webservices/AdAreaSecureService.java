package tn.codeinc.webservices;

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
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.AdAreaRequestException;
import tn.codeinc.exceptions.AuthorizationException;
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
	@Path("/deleteRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRequest(AdAreaPurchaseRequest pr) {
		try {
			adAreas.deletePurchaseRequest(pr);
			return Response.ok().entity(new ResponseMessage(0, "Your purchase request was deleted")).build();
		} catch (ElementNotFoundException e) {
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		} catch (AuthorizationException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();

		} catch (AdAreaRequestException e) {
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}

	}

	@Path("/myRequests")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConnectedUserPurchaseRequest() {
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		return Response.ok().entity(adAreas.getConnectedUserPurchaseRequest()).build();
	}

	@POST
	@Path("/accept")
	@Produces(MediaType.APPLICATION_JSON)
	public Response acceptPurchaseRequest(AdAreaPurchaseRequest pr) {
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			adAreas.acceptPurchaseRequest(pr);
			return Response.ok().entity(new ResponseMessage(0, "Purchase request accepted")).build();
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@POST
	@Path("/refuse")
	@Produces(MediaType.APPLICATION_JSON)
	public Response refusePurchaseRequest(AdAreaPurchaseRequest pr) {
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			adAreas.refusePurchaseRequest(pr);
			return Response.ok().entity(new ResponseMessage(0, "Purchase request refused")).build();
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@GET
	@Path("/getByArea/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByArea(@PathParam("id") Integer id) {
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			return Response.ok().entity(adAreas.getByAdArea(id)).build();
		} catch (ElementNotFoundException e) {

			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
