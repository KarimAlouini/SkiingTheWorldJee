package tn.codeinc.webservices;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

@Path("/joboffers")
@RequestScoped
public class JobOfferService {
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
	
	
	@Path("/top5")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getTop() {
		List<JobOffer> allOffers = jobOfferManagementLocal.getNewestJobOffers();
		return Response.ok().entity(allOffers).build();
	}
	
	@Path("/top5Category/{cat}")
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public Response getTopCat( @PathParam("cat") String cat) {

		List<JobOffer> allOffers = jobOfferManagementLocal.getJobOffersBycategory(cat);
		return Response.ok().entity(allOffers).build();
	}
	
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") Integer id) {

		try {
			return Response.ok().entity(jobOfferManagementLocal.get(id)).build();
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}

	}
	
	
}
