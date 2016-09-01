package at.fes.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	int responseCode;
	public YumpuFunctions yf = new YumpuFunctions();
	public Config config = new Config("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
	public RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};

//		String[] params = {"document_id=55897746", "type=link", "page=1", "setting=" + "settings={\""x=100\"",\""y=100\"", \""w=50\"", \""h=50\"", \""name=google\"", \""tooltip=google.com\"", \""link=http://www.google.com\""}"};
//		System.out.println(t.postDocumentHotspot(params));
	}

	public JSONObject postDocumentHotspot(String[] params)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);
		System.out.println(json);
		return optionsPost(json, url);
	}

	public String addParams(String[] params, String url) {
		url = url + "?";
		for (String s : params) {
			url = url + s + "&";
		}
		return url;
	}
	
	private JSONObject optionsPost(JSONObject json, String url)
			throws IOException, JSONException, MalformedURLException,
			ProtocolException {
		yf.log("post " + url);
		JSONObject jo = postRequest(config, url, json);
		int responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	public JSONObject postRequest(Config config, String url, JSONObject json)
			throws JSONException, ParseException, IOException {
		this.config = config;
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

	private JSONObject sendResponse(HttpResponse response) throws IOException,
			JSONException {
		String jsonString = EntityUtils.toString(response.getEntity());
		// System.out.println(jsonString);
		JSONObject myObject;
		try {
			myObject = new JSONObject(jsonString);
		} catch (Exception e) {
			myObject = new JSONObject(jsonString.substring(jsonString
					.indexOf("{")));
		}
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}
}
