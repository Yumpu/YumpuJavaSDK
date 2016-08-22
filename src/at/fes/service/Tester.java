package at.fes.service;

import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	
	
	public static void main(String[] args) throws JSONException {
		Tester t = new Tester();
		t.getHttpConnection();
		t.createJSON();
	}

	public void getHttpConnection() {
	    HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
	    System.out.println("here");
	    try {
	        HttpPost request = new HttpPost("http://api.yumpu.com/2.0/document/url.json");
	        request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
	        StringEntity params = new StringEntity(createJSON().toString());
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);

	        System.out.println(response);
	    }catch (Exception ex) {
	        System.out.println("error");
	    } finally {
	        httpClient.getConnectionManager().shutdown(); //Deprecated
	    }
	}
	
	public JSONObject createJSON() throws JSONException {
		String message;
		JSONObject json = new JSONObject();
		json.put("url", "http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf");


		json.put("title", "asdasdasd");

		message = json.toString();
		
		System.out.println(message);
		return json;
	}
}
