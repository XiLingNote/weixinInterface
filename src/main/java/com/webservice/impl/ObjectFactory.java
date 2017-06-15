
package com.webservice.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.webservice.impl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayAnythingResponse_QNAME = new QName("http://impl.webservice.com/", "sayAnythingResponse");
    private final static QName _ContextInit_QNAME = new QName("http://impl.webservice.com/", "contextInit");
    private final static QName _ContextInitResponse_QNAME = new QName("http://impl.webservice.com/", "contextInitResponse");
    private final static QName _SayAnything_QNAME = new QName("http://impl.webservice.com/", "sayAnything");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.webservice.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayAnythingResponse }
     * 
     */
    public SayAnythingResponse createSayAnythingResponse() {
        return new SayAnythingResponse();
    }

    /**
     * Create an instance of {@link ContextInit }
     * 
     */
    public ContextInit createContextInit() {
        return new ContextInit();
    }

    /**
     * Create an instance of {@link ContextInitResponse }
     * 
     */
    public ContextInitResponse createContextInitResponse() {
        return new ContextInitResponse();
    }

    /**
     * Create an instance of {@link SayAnything }
     * 
     */
    public SayAnything createSayAnything() {
        return new SayAnything();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayAnythingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservice.com/", name = "sayAnythingResponse")
    public JAXBElement<SayAnythingResponse> createSayAnythingResponse(SayAnythingResponse value) {
        return new JAXBElement<SayAnythingResponse>(_SayAnythingResponse_QNAME, SayAnythingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContextInit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservice.com/", name = "contextInit")
    public JAXBElement<ContextInit> createContextInit(ContextInit value) {
        return new JAXBElement<ContextInit>(_ContextInit_QNAME, ContextInit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ContextInitResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservice.com/", name = "contextInitResponse")
    public JAXBElement<ContextInitResponse> createContextInitResponse(ContextInitResponse value) {
        return new JAXBElement<ContextInitResponse>(_ContextInitResponse_QNAME, ContextInitResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayAnything }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.webservice.com/", name = "sayAnything")
    public JAXBElement<SayAnything> createSayAnything(SayAnything value) {
        return new JAXBElement<SayAnything>(_SayAnything_QNAME, SayAnything.class, null, value);
    }

}
