package tn.codeinc.webservices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tn.codeinc.services.PDFManagement;

@Path("ExportTest")
public class PDFExportService {
	@Inject
	PDFManagement pdf;
	@GET
	@Path("/getPDF/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void exportPDF(@PathParam(value = "id") int id){
		pdf.exportPDF(id);
	}
	

}
