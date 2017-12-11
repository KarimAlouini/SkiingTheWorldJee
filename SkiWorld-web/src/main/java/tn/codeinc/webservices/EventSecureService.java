package tn.codeinc.webservices;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import tn.codeinc.client.CurrentUserLocal;
import tn.codeinc.exceptions.BadWordException;
import tn.codeinc.exceptions.ElementNotFoundException;
import tn.codeinc.exceptions.EventException;
import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.User.UserRole;
import tn.codeinc.services.EventManagementLocal;
import tn.codeinc.util.FileUpload;
import tn.codeinc.util.ResponseMessage;

@Path("/secured/event")
@RequestScoped
public class EventSecureService {
	@Inject
	private EventManagementLocal events;
	@Inject
	CurrentUserLocal currentUser;
	
	private final String UPLOAD_DIR = "resources\\events";
	
	@Context
	private ServletContext context;
	

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response getAll() {
//		List<Event> allEvents = events.getByType(EventType.Public);
//		List<Event> es = currentUser.get().getMyEvents();
//		allEvents.addAll(currentUser.get().getMyEvents());
//		return Response.ok().entity(allEvents).build();
//	}
	
	@GET
	@Path("/private")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPrivate() {
		return Response.ok().entity(currentUser.get().getMyEvents()).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Event event) {
		if (currentUser.get().getRole()!=UserRole.ROLE_USER)
			return Response.status(Status.UNAUTHORIZED).build();
		try {
			events.create(event);
		} catch (BadWordException e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		} catch (EventException e) {
			// TODO Auto-generated catch block
			return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
		}
		return Response.ok().entity(new ResponseMessage(0,"q")).build();
	}
	@Path("/apply")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response applyForEvent(Event event){
		
			try {
				events.applyForEvent(event);
			} catch (ElementNotFoundException | EventException e) {
				// TODO Auto-generated catch block
				return Response.ok().entity(new ResponseMessage(1, e.getMessage())).build();
			};
		
		return Response.ok().entity(new ResponseMessage(0,"q")).build();
		}
//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response invite(Event event){
//		
//	}
	
	@PUT
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/uploadPics")
	
	public  Response uploadPictures(@HeaderParam("eventId") String id,MultipartFormDataInput input){
		
		try {
			Event e = events.get(Integer.parseInt(id));
			Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
			List<InputPart> inputParts = uploadForm.get("uploadedFile");
			List<String> files = FileUpload.uploadEventPictures(inputParts, this.context.getRealPath(UPLOAD_DIR), e);
			
			files.forEach(System.out::println);
			
		} catch (NumberFormatException | ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("EventSecureService.uploadPictures() event not found");
		}
		
		
		return Response.ok().build();
		
	}
	
	
}
