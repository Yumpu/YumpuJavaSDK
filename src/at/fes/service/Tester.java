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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
//		t.getDocuments(0, 0);
		t.postAccessTag("tagtag2", "descriptioN");
		
	}

	public void postAccessTag(String name, String description) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("description", description);
		
		JSONObject jo = postRequest(json);
		prettyJSON(jo);
	}

	public JSONObject postRequest(JSONObject json) throws JSONException {
		HttpClient httpClient = HttpClientBuilder.create().build();

		JSONObject myObject = null;
		try {
			HttpPost request = new HttpPost("http://api.yumpu.com/2.0/account/access_tag.json");
			request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
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

	public void getDocuments(int offset, int limit) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("documents/get") + "?offset=" + offset + "&limit=" + limit;
		JSONObject jo = getRequest(url);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}

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

	private String prettyJSON(JSONObject jo)
			throws MalformedURLException, IOException, ProtocolException, JSONException {

		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return prettyJson;
	}

}
