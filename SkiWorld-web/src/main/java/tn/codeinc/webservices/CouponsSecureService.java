package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.client.CurrentUserRemote;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.CouponManagementLocal;
import tn.codeinc.services.CouponManagementRemote;

@Path("/secure/coupons")
public class CouponsSecureService {
	@EJB
	CouponManagementLocal coupons;
	
	@EJB
	CurrentUserLocal currentUser;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll(){
		if (currentUser.get().getRole() != UserRole.ROLE_SUPER_ADMIN)
			return Response.status(Status.UNAUTHORIZED).build();
		
		return Response.ok().entity(coupons.getAll()).build();
	}
	

}
