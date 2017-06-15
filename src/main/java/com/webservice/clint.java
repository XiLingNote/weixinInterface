package com.webservice;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.hr.definitions.HumanResource;
import com.mycompany.hr.definitions.HumanResourceService;
import com.mycompany.hr.schemas.EmployeeType;
import com.mycompany.hr.schemas.HolidayRequest;
import com.mycompany.hr.schemas.HolidayType;

import service.HumanResouceTest;

public class clint {

	public static void main(String[] args) {
		    
		 HumanResource human =new HumanResourceService().getHumanResourceSoap11();
		 HolidayRequest request=new HolidayRequest();
		 EmployeeType emp= new EmployeeType();
		 HolidayType holi= new HolidayType();
		 emp.setFirstName("lihong");
		 emp.setLastName("hhe ");
		 emp.setNumber(BigInteger.valueOf(1324));
		 request.setEmployee(emp);
		 request.setHoliday(holi);
		 human.holiday(request);
	
	}
}
