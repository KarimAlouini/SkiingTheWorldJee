package tn.codeinc.webservices;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.UsersManagementLocal;

@Path("/secured/users")
@RequestScoped
public class UserSecureService {
	@Inject
	UsersManagementLocal users;
	@Inject
	CurrentUserLocal currentUser;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT) {
			return Response.status(Status.UNAUTHORIZED).build();
		}

		List<User> allUsers = users.getAll();
		allUsers.removeIf(u -> u.equals(currentUser.get()));
		return Response.ok().entity(allUsers).build();
	}

	

	@POST
	@Path("/ban")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ban(@FormParam("user") Integer user) {
		
		if (currentUser.get().isSuperAdmin()) {
			if (user == null) {
				return Response.status(Status.BAD_REQUEST).build();
			} else {
				User u = users.get(user);
				u.setBanned(true);
				users.update(u);
				return Response.ok().build();
			}
		} else
			return Response.status(Status.UNAUTHORIZED).build();
	}

}
