package com.crypto.forex.mongo.documents;

//
//This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
//See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
//Any modifications to this file will be lost upon recompilation of the source schema. 
//Generated on: 2018.03.20 at 06:39:25 AM IST 
//

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
* <p>Java class for anonymous complex type.
* 
* <p>The following schema fragment specifies the expected content contained within this class.
* 
* <pre>
* &lt;complexType>
*   &lt;complexContent>
*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
*       &lt;sequence>
*         &lt;element ref="{urn:Crypto.Forex}exchange" maxOccurs="unbounded"/>
*       &lt;/sequence>
*     &lt;/restriction>
*   &lt;/complexContent>
* &lt;/complexType>
* </pre>
* 
* 
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "exchanges", namespace = "urn:Crypto.Forex")
public class Exchanges {

 @XmlElement(namespace = "urn:Crypto.Forex", required = true)
 protected List<Exchange> exchange;

 /**
  * Gets the value of the exchange property.
  * 
  * <p>
  * This accessor method returns a reference to the live list,
  * not a snapshot. Therefore any modification you make to the
  * returned list will be present inside the JAXB object.
  * This is why there is not a <CODE>set</CODE> method for the exchange property.
  * 
  * <p>
  * For example, to add a new item, do as follows:
  * <pre>
  *    getExchange().add(newItem);
  * </pre>
  * 
  * 
  * <p>
  * Objects of the following type(s) are allowed in the list
  * {@link Exchange }
  * 
  * 
  */
 public List<Exchange> getExchange() {
     if (exchange == null) {
         exchange = new ArrayList<Exchange>();
     }
     return this.exchange;
 }

}
