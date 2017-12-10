package tn.codeinc.webservices;

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

import tn.codeinc.client.CurrentUser;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.JobOffer;
import tn.codeinc.persistance.User;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.JobOfferManagementLocal;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/joboffers")
@RequestScoped
public class JobOfferSecureService {
	@Inject
	CurrentUserLocal currentUser;
	@Inject
	JobOfferManagementLocal jobOfferManagementLocal;
	
	@Path("/all")
	@GET
	@Produces (MediaType.APPLICATION_JSON)

	public Response getAll() {
		
		

		List<JobOffer> allOffers = jobOfferManagementLocal.getAll();
		return Response.ok().entity(allOffers).build();
	}
	@Path("/AddOffer")
	@PUT
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response addMyOffers(JobOffer jo){
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		
		jobOfferManagementLocal.create(jo);
		return Response.ok().entity(new ResponseMessage(0,"Added Seccessfully")).build();
	}
	
	@Path("/AddOffer2")
	@PUT
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response addMyOfferss(JobOffer jo){
		if (currentUser.get().getRole() != UserRole.ROLE_AGENT)
			return Response.status(Status.UNAUTHORIZED).build();
		
		jobOfferManagementLocal.create(jo);
		return Response.ok().entity(new ResponseMessage(0,"Added Seccessfully")).build();
	}
	
	@DELETE
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response DelieteThisOffer(JobOffer jo) throws ElementNotFoundException{
	    if (currentUser.get().getRole() !=UserRole.ROLE_AGENT)
	    	return Response.status(Status.UNAUTHORIZED).build();
	    jobOfferManagementLocal.remove(jo);
	    return Response.ok().entity(new ResponseMessage(0,"This offer is Delieted seccessfully")).build();
	}
	
	@Path("/UpdateOffer")
	@PUT
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response UpdateThisOffer(JobOffer jo){
		System.out.println(jo);
	    if (currentUser.get().getRole() !=UserRole.ROLE_AGENT)
	    	return Response.status(Status.UNAUTHORIZED).build();
	    jobOfferManagementLocal.update(jo);
	    return Response.ok().entity(new ResponseMessage(0,"This offer is Updated seccessfully")).build();
	}

	@Path("/ListbyAgent")
	@PUT
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	
	public Response ListByAgent(User agent){
	    if (currentUser.get().getRole() !=UserRole.ROLE_AGENT)
	    	return Response.status(Status.UNAUTHORIZED).build();
	    ;
	    return Response.ok().entity(jobOfferManagementLocal.getByAgent(agent)).build();
	}
}
