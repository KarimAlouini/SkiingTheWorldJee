package tn.codeinc.webservices;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.TokenManagementLocal;
import tn.codeinc.services.UsersManagementLocal;
import tn.codeinc.util.FileUpload;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/users")
@RequestScoped
public class UserSecureService {

	@Inject
	TokenManagementLocal tokens;

	private final String UPLOAD_DIR = "resources";

	@Context
	ServletContext context;

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

	@GET
	@Path("/ad_requests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAdRequests() {
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		else
			return Response.ok().entity(currentUser.get().getPurchaseRequests()).build();
	}

	@POST
	@Path("change_password")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changePassword(@HeaderParam("old_pass") String oldPassword,
			@HeaderParam("new_pass") String newPassword) {
		User u = currentUser.get();
		if (oldPassword.equals(u.getPassword())) {
			u.setPassword(newPassword);
			users.update(u);
			return Response.ok().entity(new ResponseMessage(0, "Your password has been changed successfuly")).build();

		} else {
			return Response.ok().entity(new ResponseMessage(1, "The old password is wrong")).build();
		}

	}

	@POST
	@Path("/update")
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@HeaderParam("user") String user, MultipartFormDataInput input) {

		Gson gson = new GsonBuilder().create();
		try {
			User u = gson.fromJson(user, User.class);

			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("uploadedFile");
			System.out.println("UserSecureService.update() " + inputParts == null);
			String uploadResult = FileUpload.upload(inputParts, this.context.getRealPath(UPLOAD_DIR) + "\\users");

			if (uploadResult != null)
				u.setImageName(uploadResult);
			users.update(u);
			return Response.ok().entity(new ResponseMessage(0, new GsonBuilder().create().toJson(u))).build();

		} catch (JsonSyntaxException e) {
			return Response.ok().entity(new ResponseMessage(1)).build();
		}

	}

}
