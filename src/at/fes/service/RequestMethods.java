package at.fes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestMethods {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";

	public JSONObject getRequest(String url) throws ClientProtocolException, IOException, JSONException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());
		
		String jsonString = EntityUtils.toString(response.getEntity());
		JSONObject myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();

		return myObject;
	}
	
	public JSONObject deleteRequest(String url) throws ClientProtocolException, IOException, JSONException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);

		// add request header
		request.setHeader("X-ACCESS-TOKEN", config.config.get("devtoken"));
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : "
		                + response.getStatusLine().getStatusCode());
		
		String jsonString = EntityUtils.toString(response.getEntity());
		JSONObject myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();

		return myObject;
	}

	public JSONObject postRequest(String url, JSONObject json) throws JSONException, ParseException, IOException {
		JSONObject myObject = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		request.addHeader("content-type", "application/json");

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = httpClient.execute(request);
		String jsonString = EntityUtils.toString(response.getEntity());
		myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();

		return myObject;
	}

	public JSONObject putRequest(String url, JSONObject json)
			throws ClientProtocolException, IOException, JSONException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		JSONObject myObject = null;

		HttpPut putRequest = new HttpPut(url);
		putRequest.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		StringEntity input = new StringEntity(json.toString());
		putRequest.setEntity(input);
		putRequest.addHeader("Content-Type", "application/json");

		HttpResponse response = httpClient.execute(putRequest);
		responseCode = response.getStatusLine().getStatusCode();
		String jsonString = EntityUtils.toString(response.getEntity());
		myObject = new JSONObject(jsonString);
		
		return myObject;
	}
}
