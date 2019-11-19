package com.jaxrs.sandbox.shopping.shop.utils;

import com.jaxrs.sandbox.shopping.shop.domain.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Utils {

    public Customer readCustomer(InputStream is){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document =  builder.parse(is);
            Element root = document.getDocumentElement();

            Customer customer = new Customer();

            if(root.getAttribute("id") != null
                    && !root.getAttribute("id").trim().equals("")){
                customer.setId(Integer.valueOf(root.getAttribute("id")));
            }

            NodeList nodes = root.getChildNodes();
            for(int i = 0; i< nodes.getLength(); i++){

                Element element = (Element) nodes.item(i);
                if (element.getTagName().equals("first-name")) {
                    customer.setFirstName(element.getTextContent());
                }
                else if (element.getTagName().equals("last-name")) {
                    customer.setLastName(element.getTextContent());
                }
                else if (element.getTagName().equals("street")) {
                    customer.setStreet(element.getTextContent());
                }
                else if (element.getTagName().equals("city")) {
                    customer.setCity(element.getTextContent());
                }
                else if (element.getTagName().equals("state")) {
                    customer.setState(element.getTextContent());
                }
                else if (element.getTagName().equals("zip")) {
                    customer.setZip(element.getTextContent());
                }
                else if (element.getTagName().equals("country")) {
                    customer.setCountry(element.getTextContent());
                }

                return customer;

            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }

        return null;
    }

    public void outputCustomer(OutputStream os, Customer cust)
            throws IOException {
        PrintStream writer = new PrintStream(os);
        writer.println("<customer id=\"" + cust.getId() + "\">");
        writer.println(" <first-name>" + cust.getFirstName()
                + "</first-name>");
        writer.println(" <last-name>" + cust.getLastName()
                + "</last-name>");
        writer.println(" <street>" + cust.getStreet() + "</street>");
        writer.println(" <city>" + cust.getCity() + "</city>");
        writer.println(" <state>" + cust.getState() + "</state>");
        writer.println(" <zip>" + cust.getZip() + "</zip>");
        writer.println(" <country>" + cust.getCountry() + "</country>");
        writer.println("</customer>");
    }


}
