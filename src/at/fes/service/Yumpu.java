package at.fes.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Yumpu {
	private Config config = new Config();
	private String method = "GET";
	public int responseCode;

	public Yumpu() throws IOException {
		log("Yumpu Class initialized");
	}

	public JSONObject getDocuments(int offset, int limit) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("documents/get") + "?offset=" + offset + "&limit=" + limit;
		log("getDocuments from " + url);
		return prettyJSON(url);
	}

	public JSONObject getDocument(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public void postDocument(String postUrl, String title) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/post/url");
		JSONObject json = new JSONObject();
		json.put("url", postUrl);
		json.put("title", title);
		postRequest(url, json);
		log("getDocuments from " + url);
	}

	public JSONObject getDocumentHotspots(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspots") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getDocumentHotspot(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/get") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public void postDocumentHotspot() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/post");
		JSONObject json = new JSONObject();
		json.put("document_id", 55865447);
		json.put("page", "1");
		json.put("type", "link");
		JSONObject settings = new JSONObject();
		settings.put("x", 100);
		settings.put("y", 100);
		settings.put("w", 50);
		settings.put("h", 50);
		settings.put("name", "google.com");
		settings.put("tooltip", "google.com");
		settings.put("link", "www.google.com");
		json.put("settings", settings);		
		System.out.println(json);
		postRequest(url, json);
		log("getDocuments from " + url);
	}

	public JSONObject getDocumentProgress(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/progress") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getCategories() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("categories/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getLanguages() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("languages/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getCountries() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("countries/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getCollections(String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collections/get");
		url = addParamsToURL(false, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getCollection(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		System.out.println(url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public void postCollection(String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/post");
		JSONObject json = new JSONObject();
		json.put("name", name);
		postRequest(url, json);
		log("getDocuments from " + url);
	}

	public JSONObject getSection(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public void postSection(String id, String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/post");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		postRequest(url, json);
		log("getDocuments from " + url);
	}

	public void postSectionDocument(String id, String documents[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/post");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("documents", documents);
		postRequest(url, json);
		log("getDocuments from " + url);
	}
	
	public JSONObject search(String params) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("search/get") + "?" + params;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getUser() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public void postUser(String email, String username, String password) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/post");
		JSONObject json = new JSONObject();
		json.put("email", email);
		json.put("username", username);
		json.put("password", password);
		postRequest(url, json);
		log("getDocuments from " + url);
	}

	public JSONObject getEmbeds(int offset, int limit, String sort, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embeds/get") + "?offset=" + offset + "&limit=" + limit + "&sort="
				+ sort;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getEmbed(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/get") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getMembers() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("members/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getMember(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/get") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getAccessTags() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTags/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getAccessTag(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/get") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getSubscriptions() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscriptions/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	public JSONObject getSubscription(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/get") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	private JSONObject getRequest(String url)
			throws MalformedURLException, IOException, ProtocolException, JSONException {
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

	public void postRequest(String url, JSONObject json) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost(url);
			request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
			StringEntity params = new StringEntity(json.toString());
			request.addHeader("content-type", "application/json");
			request.setEntity(params);
			HttpResponse response = httpClient.execute(request);

			System.out.println(response);
		} catch (Exception ex) {
			System.out.println("error" + ex);
		}
	}

	private JSONObject prettyJSON(String url)
			throws MalformedURLException, IOException, ProtocolException, JSONException {
		JSONObject jo = getRequest(url);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return jo;
	}

	private String addParamsToURL(boolean isId, String[] returnFields, String url) {
		if (returnFields.length > 0) {
			if (isId)
				url = url + "&return_fields=";
			else
				url = url + "?return_fields=";
			for (int i = 0; i < returnFields.length; i++) {
				url = url + returnFields[i];
				if (!(i == returnFields.length - 1)) {
					url = url + ",";
				}
			}
		}
		return url;
	}

	private void log(String logText) throws IOException {
		// File yumpuLog = new File(".\\src\\at\\fes\\log\\yumpu_log.txt");
		// FileWriter writer = new FileWriter(yumpuLog, true);
		// writer.write(logText + "\n");
		// writer.close();
	}
}
