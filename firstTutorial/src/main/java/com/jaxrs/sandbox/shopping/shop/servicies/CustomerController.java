package com.jaxrs.sandbox.shopping.shop.servicies;

import com.jaxrs.sandbox.shopping.shop.domain.Customer;
import com.jaxrs.sandbox.shopping.shop.utils.Utils;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Path("/customers")
public class CustomerController {
    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger();
    private Utils utils = new Utils();

    @POST
    @Consumes("application/xml")
    public Response createCustomer(InputStream is){
        Customer customer =  Utils.convertXMLToObject(is, Customer.class);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Created customer " + customer.getId());

        return Response.created(URI.create("/customers/" + customer.getId())).build();

    }

    @GET
    @Path("/{id}")
    @Produces("application/xml")
    public StreamingOutput getCustomer(@PathParam("id") int id) {
        final Customer customer = customerDB.get(id);
        if(customer == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Utils.convertObjectToXML(customer);

    }

    @PUT
    @Path("/{id}")
    @Consumes("application/xml")
    public void updateCustomer(@PathParam("id") int id, InputStream is) {
        Customer update = utils.readCustomer(is);

        Customer current = customerDB.get(id);

        if(current == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        current.setFirstName(update.getFirstName());
        current.setLastName(update.getLastName());
        current.setStreet(update.getStreet());
        current.setState(update.getState());
        current.setZip(update.getZip());
        current.setCountry(update.getCountry());
    }


}
