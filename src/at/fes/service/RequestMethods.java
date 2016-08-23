package at.fes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestMethods {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";
	
	public JSONObject getRequest(String url) throws IOException, JSONException {
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestProperty("X-ACCESS-TOKEN", config.config.get("token"));

		// optional default is GET
		con.setRequestMethod(method);

		responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		JSONObject myObject = null;

		while ((inputLine = in.readLine()) != null) {
			myObject = new JSONObject(inputLine);
		}

		in.close();

		return myObject;
	}
	
	public JSONObject postRequest(String url, JSONObject json) throws JSONException {
		HttpClient httpClient = HttpClientBuilder.create().build();

		JSONObject myObject = null;
		try {
			HttpPost request = new HttpPost(url);
			request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
			StringEntity params = new StringEntity(json.toString());
			request.setEntity(params);
			request.addHeader("content-type", "application/json");
			System.out.println(json.toString());
			HttpResponse response = httpClient.execute(request);
			String jsonString = EntityUtils.toString(response.getEntity());
			myObject = new JSONObject(jsonString);
			responseCode = response.getStatusLine().getStatusCode();
		} catch (Exception ex) {
			System.out.println("error" + ex);
		}
		
		return myObject;
	}
}
