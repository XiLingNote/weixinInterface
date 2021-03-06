
package com.webservice.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "webserviceImpl", targetNamespace = "http://impl.webservice.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebserviceImpl {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sayAnything", targetNamespace = "http://impl.webservice.com/", className = "com.webservice.impl.SayAnything")
    @ResponseWrapper(localName = "sayAnythingResponse", targetNamespace = "http://impl.webservice.com/", className = "com.webservice.impl.SayAnythingResponse")
    @Action(input = "http://impl.webservice.com/webserviceImpl/sayAnythingRequest", output = "http://impl.webservice.com/webserviceImpl/sayAnythingResponse")
    public void sayAnything(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "contextInit", targetNamespace = "http://impl.webservice.com/", className = "com.webservice.impl.ContextInit")
    @ResponseWrapper(localName = "contextInitResponse", targetNamespace = "http://impl.webservice.com/", className = "com.webservice.impl.ContextInitResponse")
    @Action(input = "http://impl.webservice.com/webserviceImpl/contextInitRequest", output = "http://impl.webservice.com/webserviceImpl/contextInitResponse")
    public void contextInit();

}
