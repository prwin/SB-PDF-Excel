package com.app.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionMessage.Style;

import com.app.entity.Employee;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

public class EmployeeExportToPDF {

	List<Employee> listOfEmployee11=Collections.emptyList(); 
	
	
	

	public EmployeeExportToPDF(List<Employee> listOfEmployee11) {
		super();
		this.listOfEmployee11 = listOfEmployee11;
	}




	public void exportPDF(HttpServletResponse response) throws DocumentException, IOException {
	
	Document document=new Document(PageSize.B4);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();	
	
	Font font=FontFactory.getFont(FontFactory.TIMES_ROMAN);
	
	font.setColor(Color.green);
	font.setSize(25);
	 
	Paragraph prgh_title=new Paragraph("--list of Employes--",font);
	
	document.add(prgh_title);
	
	PdfPTable table=new PdfPTable(3);
	
	table.setWidthPercentage(80f);
	table.setSpacingBefore(10);
	table.setSpacingAfter(10);
	table.setWidths(new float[] {1f,3f,3f});
	
	
	PdfPCell cell=new PdfPCell();
	//cell.setBackgroundColor(Color.green);
	cell.setBackgroundColor(Color.green);
	cell.setPadding(11f);
	cell.setBorderWidthBottom(10f);
	cell.setBorderColor(Color.RED);
	
	cell.setPhrase(new Phrase("ID"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("NAME"));
	table.addCell(cell);
	cell.setPhrase(new Phrase("-Address-"));
	table.addCell(cell);
	
	
for (Employee emp : listOfEmployee11) {
	table.addCell(String.valueOf(emp.getId()));
	table.addCell(emp.getName());
	table.addCell(emp.getAddress());
}
 	
document.add(table)	;
document.close();
	}
}
 