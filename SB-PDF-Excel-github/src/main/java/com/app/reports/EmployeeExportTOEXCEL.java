package com.app.reports;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.app.entity.Employee;


public class EmployeeExportTOEXCEL {

	List<Employee> listOfEmployee11 = Collections.emptyList();
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	
		public EmployeeExportTOEXCEL(List<Employee> listOfEmployee11) {
		
		this.listOfEmployee11 = listOfEmployee11;
		workbook=new XSSFWorkbook();
	}

		

		private void excelHeader() {
			 sheet=workbook.createSheet("employees");
Row row=sheet.createRow(0);
createCell(row, 0, "id");
createCell(row, 1, "name");
createCell(row, 2, "address");

}

		private void createCell(Row row,int columnCount,Object value) {
	org.apache.poi.ss.usermodel.Cell cell=row.createCell(columnCount);
	if (value instanceof Integer) {
		cell.setCellValue((Integer) value);
	} else {
		cell.setCellValue((String) value);

	}
	
	}
	
	
	public void exportToEXCEL(HttpServletResponse httpServletResponse) throws IOException {

	excelHeader();
int rowcount=1;	
	for (Employee empl : listOfEmployee11) {
		Row row=sheet.createRow(rowcount++);
	int columnCount=0;
	createCell(row, columnCount++, empl.getId());
	createCell(row, columnCount++, empl.getName());
	createCell(row, columnCount++, empl.getAddress());

	
	}  
		
	ServletOutputStream opstream=httpServletResponse.getOutputStream();
	System.out.println(httpServletResponse.getOutputStream());
workbook.write(opstream);
	workbook.close();
	opstream.close();	
		
		
		
		
		
		
		
		
	}
}
