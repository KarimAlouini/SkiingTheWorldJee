package tn.codeinc.services;

import javax.ejb.Local;

import com.itextpdf.text.Paragraph;

@Local
public interface PDFManagementLocal {
	public void exportPDF(int id);
	public void addEmptyLine(Paragraph paragraph, int number);
}
