package tn.codeinc.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.AdAreaRequestDuplicationException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.persistance.AdArea;
import tn.codeinc.persistance.AdAreaPurchaseRequest;
import tn.codeinc.persistance.Evaluation;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.AdAreaManagementLocal;
import tn.codeinc.services.EvaluationManagement;
import tn.codeinc.util.ResponseMessage;

@Path("/rate")
@RequestScoped
public class EvaluationResource {
	@EJB
	EvaluationManagement ma;
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rate(Evaluation evaluation) {
	
		ma.AddEvaluation(evaluation);
		return Response.status(200).build();

	}
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Evaluation> GetAllOffer() {
		return ma.findAll();
	}

}
