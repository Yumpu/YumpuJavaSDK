package at.fes.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Yumpu {
	private Config config = new Config();
	private RequestMethods rm = new RequestMethods();
	public int responseCode;

	public Yumpu() throws IOException {
		log("Yumpu Class initialized");
	}

	public void getDocuments(int offset, int limit) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("documents/get") + "?offset=" + offset + "&limit=" + limit;
		
		optionsGet(url);
	}

	public void getDocument(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		optionsGet(url);
	}

	public void postDocumentUrl(String postUrl, String title) throws IOException, JSONException {
		JSONObject json = new JSONObject();
		json.put("url", postUrl);
		json.put("title", title);
		String url = config.yumpuEndpoints.get("document/post/url");
		
		optionsPost(json, url);
	}
	
	public void putDocument(String name, int id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		
		optionsPut(url, json);
	}
	
	public void deleteDocument(int id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/delete") + "?id=" + id;
		
		optionsDelete(url);
	}

	public void getDocumentHotspots(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspots") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		
		optionsGet(url);
	}

	public void getDocumentHotspot(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/get") + "?id=" + id;
		
		optionsGet(url);
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
		
//		Map<String, String> obj = new LinkedHashMap<String, String>();
//		obj.put("document_id", "55869263");
//		obj.put("page", "1");
//		obj.put("type", "link");
//		
//		Map<String, String> settings = new LinkedHashMap<String, String>();
//		settings.put("x", "100");
//		settings.put("y", "100");
//		settings.put("w", "50");
//		settings.put("h", "50");
//		settings.put("name", "newone");
//		settings.put("tooltip", "newone");
//		settings.put("link", "http://www.google.com");
//		obj.put("settings", settings.toString());
//		
//		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//		String json = gson.toJson(obj, LinkedHashMap.class);
//
//		System.out.println(json);
//		
//		optionsPost(json, url);
	}

	public void getDocumentProgress(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/progress") + "?id=" + id;
		
		optionsGet(url);
	}

	public void getCategories() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("categories/get");
		
		optionsGet(url);
	}

	public void getLanguages() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("languages/get");
		
		optionsGet(url);
	}

	public void getCountries() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("countries/get");
		
		optionsGet(url);
	}

	public void getCollections(String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collections/get");
		url = addParamsToURL(false, returnFields, url);
		
		optionsGet(url);
	}

	public void getCollection(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		
		optionsGet(url);
	}

	public void postCollection(String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/post");
		JSONObject json = new JSONObject();
		json.put("name", name);
		
		optionsPost(json, url);
	}
	
	public void putCollection(String id, String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		optionsPut(url, json);
	}

	public void getSection(String id, String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		
		optionsGet(url);
	}

	public void postSection(String id, String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/post");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		
		optionsPost(json, url);
	}
	
	public void putSection(String id, String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		optionsPut(url, json);
	}

	public void postSectionDocument(String id, String documents[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/post");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("documents", documents);
		
		optionsPost(json, url);
	}

	public void search(String params) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("search/get") + "?" + params;
		
		optionsGet(url);
	}

	public void getUser() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/get");
		
		optionsGet(url);
	}

	public void postUser(String email, String username, String password) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/post");
		JSONObject json = new JSONObject();
		json.put("email", email);
		json.put("username", username);
		json.put("password", password);
		
		optionsPost(json, url);
	}
	
	public void putUser(String gender, String firstname, String lastname) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/put");
		JSONObject json = new JSONObject();
		json.put("gender", gender);
		json.put("firstname", firstname);
		json.put("lastname", lastname);
		
		optionsPut(url, json);
	}

	public void getEmbeds(int offset, int limit, String sort, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embeds/get") + "?offset=" + offset + "&limit=" + limit + "&sort="
				+ sort;
		url = addParamsToURL(true, returnFields, url);
		
		optionsGet(url);
	}

	public void getEmbed(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/get") + "?id=" + id;
		
		optionsGet(url);
	}

	public void postEmbed(int document_id, int type) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/post");
		JSONObject json = new JSONObject();
		json.put("document_id", document_id);
		json.put("type", type);
		
		optionsPost(json, url);
	}
	
	public void putEmbed(String id, int document_id, int type) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("document_id", document_id);
		json.put("type", type);
		
		optionsPut(url, json);
	}
	
	public void getMembers() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("members/get");
		
		optionsGet(url);
	}

	public void getMember(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/get") + "?id=" + id;
		
		optionsGet(url);
	}

	public void postMember(String username, String password)
			throws IOException, JSONException, NoSuchAlgorithmException {
		String url = config.yumpuEndpoints.get("member/post");
		JSONObject json = new JSONObject();
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		password = new BigInteger(1, m.digest()).toString(16);
		json.put("username", username);
		json.put("password", password);
		
		optionsPost(json, url);
	}
	
	public void putMember(String id, String username) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("username", username);
		
		optionsPut(url, json);
	}

	public void getAccessTags() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTags/get");
		
		optionsGet(url);
	}

	public void getAccessTag(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/get") + "?id=" + id;
		
		optionsGet(url);
	}
	
	public void postAccessTag(String name, String description)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("description", description);
		
		optionsPost(json, url);
	}
	
	public void putAccessTag(String id, String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		
		optionsPut(url, json);
	}

	public void getSubscriptions() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscriptions/get");
		
		optionsGet(url);
	}


	public void getSubscription(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/get") + "?id=" + id;
		
		optionsGet(url);
	}
	
	public void postSubscription(String itc_product_id, String name, int duration)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/post");
		JSONObject json = new JSONObject();
		json.put("itc_product_id", itc_product_id);
		json.put("name", name);
		json.put("duration", duration);
		
		optionsPost(json, url);
	}
	
	public void putSubscription(String id, String itc_product_id, String name, int duration) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("itc_product_id", itc_product_id);
		json.put("name", name);
		json.put("duration", duration);
		
		optionsPut(url, json);
	}

	private void optionsGet(String url) throws IOException, JSONException, MalformedURLException, ProtocolException {
		log("getDocument from " + url);
		JSONObject jo = rm.getRequest(url);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}
	
	private void optionsDelete(String url) throws IOException, JSONException {
		log("deleteDocument from " + url);
		JSONObject jo = rm.deleteRequest(url);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}
		
	private void optionsPost(JSONObject json, String url)
			throws IOException, JSONException, MalformedURLException, ProtocolException {
		log("postDocuments to " + url);
		JSONObject jo = rm.postRequest(url, json);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}
	
	private void optionsPut(String url, JSONObject json)
			throws IOException, JSONException, MalformedURLException, ProtocolException {
		log("putDocuments to " + url);
		JSONObject jo = rm.putRequest(url, json);
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
		 File yumpuLog = new File(".\\src\\at\\fes\\log\\yumpu_log.txt");
		 FileWriter writer = new FileWriter(yumpuLog, true);
		 writer.write(logText + "\n");
		 writer.close();
	}
}
