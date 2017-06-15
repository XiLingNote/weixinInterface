package com.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface webserviceTest {
	public void sayAnything(@WebParam(name = "arg0")String name,@WebParam(name = "arg1")String thing);
	public String sayHello(String name);
}
