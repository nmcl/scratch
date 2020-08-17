package com.example.rest;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import javax.ws.rs.core.MediaType;

@Path("/stock")
public class StockClientEndpoint
{
	@GET
	@Produces("text/plain")
	public Response doGet() throws Exception
	{
	    HttpURLConnection connection = null;
	    String stockCode = "IBM";
	    
	    try {
		URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+stockCode+"&apikey=demo");
		connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

		if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
		    throw new RuntimeException("Request Failed: HTTP Error code: " + connection.getResponseCode());
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String output;
		StringBuilder response = new StringBuilder();

		while ((output = reader.readLine()) != null) {
		    response.append(output);
		}

		System.err.println("Successfully executed stock price request for: " + stockCode);

		return Response.ok("Stock quote for "+stockCode+" is "+response.toString()).build();
		
	    } finally {
		assert connection != null;
		connection.disconnect();
	    }
	}
}
