package at.fes.service;

import java.io.IOException;

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

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		HttpResponse response = client.execute(request);
		
		JSONObject myObject = sendResponse(response);

		return myObject;
	}
	
	public JSONObject deleteRequest(String url) throws ClientProtocolException, IOException, JSONException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("devtoken"));
		HttpResponse response = client.execute(request);
		
		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	public JSONObject postRequest(String url, JSONObject json) throws JSONException, ParseException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		request.addHeader("content-type", "application/json");
		
		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	public JSONObject putRequest(String url, JSONObject json)
			throws ClientProtocolException, IOException, JSONException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut request = new HttpPut(url);
		
		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		request.addHeader("Content-Type", "application/json");
		
		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JSONObject myObject = sendResponse(response);
		
		return myObject;
	}

	private JSONObject sendResponse(HttpResponse response) throws IOException,
			JSONException {
		String jsonString = EntityUtils.toString(response.getEntity());
		JSONObject myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}
}
