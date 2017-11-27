package tn.codeinc.webservices;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.RechargingCouponLocal;
import tn.codeinc.util.ResponseMessage;

@RequestScoped
@Path("/secured/coupons")
public class RechargingCouponSecureService {
	@Inject
	RechargingCouponLocal service;

	@Inject
	CurrentUserLocal user;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		if (user.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		return Response.ok().entity(service.getAll()).build();
	}
	
	@Path("/paginated")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllPaginated(@HeaderParam("page") int page) {
		if (page == 0 )
			page = 1;
		if (user.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		return Response.ok().entity(service.getAllPaginated(page)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response generate(@HeaderParam("count") Integer count, @HeaderParam("amount") Integer amount) {
		if (user.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		service.generate(count, amount);
		return Response.ok().entity(new ResponseMessage(0, "Coupons generated ! ")).build();
	}

}
