package tn.codeinc.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tn.codeinc.persistance.Test;;

/**
 * Session Bean implementation class PDFManagement
 */
@Stateless
@LocalBean
public class PDFManagement implements PDFManagementRemote, PDFManagementLocal {

    /**
     * Default constructor. 
     */
	@Inject
	PersistanceContextLocal pc;
	@Inject
	UsersManagementLocal userM;
	@Inject
	TestManagement tm;
	 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	  private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.GREEN);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    
    public PDFManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void exportPDF(int id) {
		System.out.println("PDFManagement.exportPDF() "+tm == null);
		Test test=tm.findTestByID(id);
		Document document=new Document();
		try{
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream("Test N°"+id+".pdf"));
			document.open();
			document.addTitle("Test N°"+id+".pdf");
			document.addAuthor("Dhouib Baya");
			document.addCreationDate();
			document.addCreator("Dhouib Baya");
			document.addSubject("Test export into pdf");
			document.setPageCount(1);
			
				 Paragraph preface = new  Paragraph(); 
			       // addEmptyLine(preface, 1);
				 Paragraph title = new Paragraph("Test N°:"+test.getIdTest(), catFont);
		        	title.setAlignment(Element.ALIGN_CENTER);
		        	preface.add(title);
			        //preface.add(new Paragraph("Test N°:"+test.getIdTest(), catFont));
			        //document.add(preface);
			       // addEmptyLine(preface, 1);
			        
			        for(int i=0;i<5;i++){
			        	//addEmptyLine(preface, 1);
			        	Paragraph p = new Paragraph((i+1)+".Question N°"+(i+1), catFont);
			        	p.setAlignment(Element.ALIGN_JUSTIFIED);
			        	 preface.add(p);
			        	 preface.add(new Paragraph((i+1)+".1 Statement: ", subFont));
			        	 preface.add(new Paragraph(test.getQuestions().get(i).getStatement()));
			        	 preface.add(new Paragraph((i+1)+".2 Choices: ", subFont));
			        	 addEmptyLine(preface, 1);
			        	 PdfPTable table = new PdfPTable(3);
			        	 PdfPCell c1 = new PdfPCell(new Phrase("First Choice: "));
					        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(c1);
					         c1 = new PdfPCell(new Phrase("Second Choice: "));
					         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(c1);
					        c1 = new PdfPCell(new Phrase("Third Choice: "));
					        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(c1);
					        table.setHeaderRows(1);
					        Phrase an1=new Phrase(test.getQuestions().get(i).getRightAnswer(),greenFont);
					        Phrase an2=new Phrase(test.getQuestions().get(i).getWrongAnwer1(),redFont);
					        Phrase an3=new Phrase(test.getQuestions().get(i).getWrongAnwer2(),redFont);
					        table.addCell(an1);
					        table.addCell(an2);
					        table.addCell(an3);
					        preface.add(table);
			        	/*Anchor anchor = new Anchor("Question N°"+(i+1), catFont);
				      
				     // Second parameter is the number of the chapter
				        Chapter catPart = new Chapter(new Paragraph(anchor),(i+1)); 
				        Paragraph subPara = new Paragraph("Statement: ", subFont);
				        Section subCatPart = catPart.addSection(subPara);
				        subCatPart.add(new Paragraph(test.getQuestions().get(i).getStatement()));
				        subPara = new Paragraph("Choices: ", subFont);
				        subCatPart = catPart.addSection(subPara);
				        PdfPTable table = new PdfPTable(3);
				        PdfPCell c1 = new PdfPCell(new Phrase("First Choice: "));
				        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(c1);
				         c1 = new PdfPCell(new Phrase("Second Choice: "));
				         c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(c1);
				        c1 = new PdfPCell(new Phrase("Third Choice: "));
				        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(c1);
				        table.setHeaderRows(1);
				        Phrase an1=new Phrase(test.getQuestions().get(i).getRightAnswer(),greenFont);
				        Phrase an2=new Phrase(test.getQuestions().get(i).getWrongAnwer1(),redFont);
				        Phrase an3=new Phrase(test.getQuestions().get(i).getWrongAnwer2(),redFont);
				        table.addCell(an1);
				        table.addCell(an2);
				        table.addCell(an3);
				        subCatPart.add(table);
				        document.add(catPart);
				        //document.add(preface);*/
			        }
			        document.add(preface);
			         document.setPageCount(1);
			     document.close();
			     writer.setPageCount(1);
			     writer.close();
			
	
			
		}catch(DocumentException e){
			e.printStackTrace();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void addEmptyLine(Paragraph paragraph, int number) {
		// TODO Auto-generated method stub
		 for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	}
    

}
