package tn.codeinc.webservices;

import java.util.Date;
import java.util.List;

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
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.JobApplicationDuplicationException;
import tn.codeinc.persistance.JobApply;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.JobApplyManagemenLocal;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/joboffers")
@RequestScoped
public class JobApplySecureService {
	@Inject
	CurrentUserLocal currentUser;
	@Inject
	JobApplyManagemenLocal jobApplyManagemenLocal;

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Response getAll() {
		System.out.println(currentUser.get());

		List<JobApply> allApplies = jobApplyManagemenLocal.getAll();
		return Response.ok().entity(allApplies).build();
	}

	@Path("/AddApply")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response addMyApplies(JobApply ja) {
		if (currentUser.get().getRole() != UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();

		try {
			jobApplyManagemenLocal.create(ja);
			return Response.ok().entity(new ResponseMessage(0, "Added Seccessfully")).build();
		} catch (JobApplicationDuplicationException e) {
			e.printStackTrace();
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteApplication")
	public Response DeleteJobApplication(JobApply ja) {
		if (currentUser.get().getRole() != UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();
		try {
			jobApplyManagemenLocal.remove(ja);
			return Response.ok().entity(new ResponseMessage(0, "This apply is Delieted seccessfully")).build();

		} catch (ElementNotFoundException | AuthorizationException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();

		}
	}

	@Path("/UpdateApply")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response UpdateJobApplication(JobApply ja) {
		System.out.println(ja);
		if (currentUser.get().getRole() != UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();
		try {
			jobApplyManagemenLocal.update(ja);
		} catch (ElementNotFoundException | AuthorizationException e) {
			return Response.status(Status.BAD_REQUEST).build();

			
		}
		return Response.ok().entity(new ResponseMessage(0, "This Apply is Updated seccessfully")).build();
	}

	@Path("/ListbyDate")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response ListByDate(Date jaD) {
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		jobApplyManagemenLocal.getByDate(jaD);
		return Response.ok().entity(new ResponseMessage(0, "These are your offers")).build();
	}
}
