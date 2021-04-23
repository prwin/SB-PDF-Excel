package com.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.entity.Employee;
import com.app.repo.EmployeeRepo;
import com.app.reports.EmployeeExportTOEXCEL;
import com.app.reports.EmployeeExportToPDF;
import com.lowagie.text.DocumentException;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@RequestMapping(value = "/")
	public String viewHome() {
		System.out.println("viewHome method");
		System.out.println("------------git update------------");
		return "index";

	}


	@GetMapping(value = "/ExportToPDF")
	public void listOfEmps(HttpServletResponse response) {
		System.out.println("list meth ");

		response.setContentType("application/pdf");	
		response.setHeader("content-disposition", "attachment; filename=EmployeeOffice.pdf");
		
		List<Employee> listOfEmployee2=	employeeRepo.selectAllEmployee();

		 EmployeeExportToPDF employeeExportToPDF=new EmployeeExportToPDF(listOfEmployee2);
	try {
		employeeExportToPDF.exportPDF(response);
	} catch (DocumentException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


	@GetMapping(value = "/ExportToExcel123")
	public void listOfEmps2(HttpServletResponse response) {
		System.out.println("list meth excel ");

		response.setContentType("application/octet-stream");	
		response.setHeader("content-disposition", "attachment; filename=MONU.xlsx");
		
		List<Employee> listOfEmployee3=	employeeRepo.selectAllEmployee();

		 EmployeeExportTOEXCEL employeeExportEXCL=new EmployeeExportTOEXCEL(listOfEmployee3);
	try {
		employeeExportEXCL.exportToEXCEL(response);
	} catch (DocumentException | IOException e) {
	
		e.printStackTrace();
	}
	}
}
