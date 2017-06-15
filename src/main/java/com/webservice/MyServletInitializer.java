package com.webservice;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class MyServletInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {
	 @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[]{};
	    }

	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return new Class[]{};
	    }

}
