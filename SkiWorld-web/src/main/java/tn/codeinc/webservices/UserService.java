package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
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
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AuthenticationException;
import tn.codeinc.exceptions.TokenNotExistantException;
import tn.codeinc.exceptions.UserException;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.TokenManagementLocal;
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
	
	@EJB
	private TokenManagementLocal tokens;
	
	@EJB
	private CurrentUserLocal currentUser;

	/**
	 * 
	 * @param user
	 * @return
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response signUp(User user) {
		
		user.setRole(UserRole.ROLE_USER);
	
		try {
			users.signUp(user);
			return Response.ok().entity(new ResponseMessage(0, "A confirmation link has been sent to your email!"))
					.build();
		} catch (AuthenticationException e) {
			
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}

	}

	@GET
	@Path("/confirm/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response confirmRegistration(@PathParam("code") String code) {
		System.out.println("UserService.confirmRegistration() "+code);
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
	
	@POST
	@Path("/refreshToken")
	@Produces(MediaType.APPLICATION_JSON)
	public Response refresh(@HeaderParam("token") String token){
		try {
			
			return Response.ok().entity(new LoginResponse(0, tokens.refresh(token))).build();
		} catch (TokenNotExistantException e) {
			e.printStackTrace();
			return Response.ok().entity(new LoginResponse(1,e.getMessage())).build();
			
		}
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@Path("/resendConfirmation")
	public Response resendConfirmation(@HeaderParam("email")String email){
		try {
			users.resendConfirmation(email);
			return Response.ok().entity(new ResponseMessage(0, "A confirmation link has been sent to your email!")).build();
		} catch (UserException e) {
			
			e.printStackTrace();
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}
	}
	
	
	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateToken(@HeaderParam("token") String token){
		
		System.out.println("UserService.validateToken() "+token);
		
		AccessToken t = tokens.get(token);
		if (t== null){
			
			return Response.ok().entity(new ResponseMessage(1)).build();
		}
		
		else{
			if(t.isValid()){
				
				return Response.ok().entity(new ResponseMessage(0)).build();
			}
		}
		
		return Response.ok().entity(new ResponseMessage(2)).build();
		
		
	}
	

	

}
