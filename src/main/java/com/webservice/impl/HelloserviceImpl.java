package com.webservice.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.webservice.webserviceTest;
@Service
public class HelloserviceImpl implements webserviceTest {
	
	public void sayAnything(String name, String thing) {

			System.out.println("name:"+name+"thing:"+thing);
	}

	public String sayHello(String name) {
		return null;
	}

}
