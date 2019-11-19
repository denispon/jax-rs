package com.jaxrs.sandbox.shopping.shop.config;

import com.jaxrs.sandbox.shopping.shop.servicies.CustomerController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/shoppingService")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration(){
        register(CustomerController.class);
        System.out.println("Initializing JerseyConfiguration");
    }
}
