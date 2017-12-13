package tn.codeinc.webservices;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonSyntaxException;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AuthorizationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.JobApplicationDuplicationException;
import tn.codeinc.persistance.JobApply;
import tn.codeinc.persistance.JobApplyId;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.JobApplyManagemenLocal;
import tn.codeinc.util.FileUpload;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/joboffers")
@RequestScoped
public class JobApplySecureService {
	
	@Context
	ServletContext context;
	
	private final String UPLOAD_DIR = "resources\\ja";

	
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
	@POST
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)

	public Response addMyApplies(@HeaderParam("jobApply")String jobApply, MultipartFormDataInput input) {
		if (currentUser.get().getRole() != UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();
			System.out.println(jobApply);
		try {
			
			System.out.println(jobApply);
			
			
			JobApply ja = new ObjectMapper().readValue(jobApply, JobApply.class);
			
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("uploadedFile");
			ja.setClient(currentUser.get());
			ja.setId(new JobApplyId(ja.getClient().getId(), ja.getOffer().getId()));
			jobApplyManagemenLocal.create(ja);
			String uploadResult = FileUpload.uploadOffer(inputParts, this.context.getRealPath(UPLOAD_DIR),ja);
			ja.setFile(uploadResult);
			jobApplyManagemenLocal.update(ja);
			return Response.ok().entity(new ResponseMessage(0, "Added Seccessfully")).build();
		} catch (JobApplicationDuplicationException e) {
			e.printStackTrace();
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
		catch(JsonSyntaxException ex){
			return Response.status(Status.FORBIDDEN).build();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.MOVED_PERMANENTLY).build();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.EXPECTATION_FAILED).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.CONFLICT).build();
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

	@Path("/List")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response ListByDate(JobApplyId id) {
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		jobApplyManagemenLocal.get(id);
		return Response.ok().entity(new ResponseMessage(0, "These are your offers")).build();
	}
	@Path("/myApplications")
	@GET
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response GetMyApplications(){
	    if (currentUser.get().getRole() !=UserRole.ROLE_AGENT)
	    	return Response.status(Status.UNAUTHORIZED).build();
	    ;
	    return Response.ok().entity(jobApplyManagemenLocal.getByAgent(currentUser.get())).build();
	}
}
