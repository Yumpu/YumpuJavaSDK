package at.fes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";
	private RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
		Tester t = new Tester();
		t.putDocument("new 2", 55865398);
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
		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println(jsonString);
		myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();

		return myObject;
	}

	public void putDocument(String name, int id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("title", name);
		putRequest(url, json);
	}

	public void postAccessTag(String name, String description) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("description", description);
		rm.postRequest(url, json);
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
		System.out.println(jsonString);
		myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();

		return myObject;
	}
}
