package tn.codeinc.webservices;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.persistance.User;
import tn.codeinc.services.UsersManagementLocal;
import tn.codeinc.util.LoginResponse;
import tn.codeinc.util.ResponseMessage;

@Path("/users")
@RequestScoped
public class UserService {
	@Context
	UriInfo uriInfo;

	@Inject
	private UsersManagementLocal users;

	/**
	 * 
	 * @param user
	 * @return
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response signUp(User user) {
		try {
			users.signUp(user, uriInfo);
			return Response.ok().entity(new ResponseMessage(0, "A confirmation link has been send to your email!"))
					.build();
		} catch (AuthenticationException e) {
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}

	}

	@GET
	@Path("/confirm/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmRegistration(@PathParam("code") String code) {
		
		try{
			users.confirm(code);
			return Response.ok().entity(new ResponseMessage(0,  "Your registration has been confirmed !"))
					.build();
		}catch(AuthenticationException e){
			return Response.ok().entity(new ResponseMessage(1, e.getMessage()))
					.build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@HeaderParam("login") String login, @HeaderParam("password") String password) {

		
		try {
			return Response.ok().entity(new LoginResponse(0, users.login(login, password))).build();

		} catch (AuthenticationException e) {
			return Response.ok().entity(new LoginResponse(1, e.getMessage())).build();
		}

	}

}
