package com.jaxrs.sandbox.shopping;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.*;

import sun.net.www.protocol.http.HttpURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@SpringBootTest
class ShoppingServiceAppTests {

	@Test
	void testCustomerResource() throws IOException {

		System.out.println("*** Create a new Customer ***");
		// Create a new customer
		String newCustomer = "<customer>"
				+ "<first-name>Bill</first-name>"
				+ "<last-name>Burke</last-name>"
				+ "<street>256 Clarendon Street</street>"
				+ "<city>Boston</city>"
				+ "<state>MA</state>"
				+ "<zip>02115</zip>"
				+ "<country>USA</country>"
				+ "</customer>";

		URL postUrl = new URL("http://localhost:8080/shoppingservice/customers");
		sun.net.www.protocol.http.HttpURLConnection connection = (sun.net.www.protocol.http.HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		OutputStream os = connection.getOutputStream();
		os.write(newCustomer.getBytes());
		os.flush();

		Assert.assertEquals(HttpURLConnection.HTTP_CREATED,
				connection.getResponseCode());

		System.out.println("Location : " + connection.getHeaderField("Location"));
		connection.disconnect();


	}

}
