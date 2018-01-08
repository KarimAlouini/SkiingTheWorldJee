package tn.codeinc.services;

import javax.ejb.Remote;

@Remote
public interface PDFManagementRemote {
	public void exportPDF(int id);
}
