package at.fes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tester {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";
	private RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
		Tester t = new Tester();
		t.getDocuments(0, 0);
	}
	
	public void getDocuments(int offset, int limit) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("documents/get") + "?offset=" + offset + "&limit=" + limit;
		
		optionsGet(url);
	}
	
	private void optionsGet(String url) throws IOException, JSONException, MalformedURLException, ProtocolException {
		JSONObject jo = rm.getRequest(url);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}
	
	private String prettyJSON(JSONObject jo)
			throws MalformedURLException, IOException, ProtocolException, JSONException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return prettyJson;
	}

	public JSONObject deleteRequest(String url) throws ClientProtocolException, IOException, JSONException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpDelete request = new HttpDelete(url);

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
	
	
}
