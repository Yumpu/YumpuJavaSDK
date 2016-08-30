package at.fes.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Yumpu {
	private Config config;
	private RequestMethods rm = new RequestMethods();
	public int responseCode;
	public String documents = null;
	
	public Yumpu(String token) {
		Config config = new Config(token);
		this.config = config;
	}
	
	public Yumpu() throws IOException {
		log("Yumpu Class initialized");
	}

	public JSONObject getDocuments(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("documents/get");
		url = addParams(false, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject getDocument(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/get") + "?id=" + id;
		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postDocumentUrl(String[] body)
			throws IOException, JSONException {
		JSONObject json = new JSONObject();
		createBody(body, json);
		String url = config.yumpuEndpoints.get("document/post/url");

		return optionsPost(json, url);
	}

	public JSONObject putDocument(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteDocument(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/delete");
		
		return optionsDelete(url, id);
	}

	public JSONObject getDocumentHotspots(String id, String[] params,
			String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspots") + "?id="
				+ id;
		url = addParams(true, url, params, returnFields);

		return optionsGet(url);
	}

	public JSONObject getDocumentHotspot(String id, String[] params,
			String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/get") + "?id="
				+ id;
		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postDocumentHotspot() throws IOException, JSONException {
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
		return json;

		// Map<String, String> obj = new LinkedHashMap<String, String>();
		// obj.put("document_id", "55869263");
		// obj.put("page", "1");
		// obj.put("type", "link");
		//
		// Map<String, String> settings = new LinkedHashMap<String, String>();
		// settings.put("x", "100");
		// settings.put("y", "100");
		// settings.put("w", "50");
		// settings.put("h", "50");
		// settings.put("name", "newone");
		// settings.put("tooltip", "newone");
		// settings.put("link", "http://www.google.com");
		// obj.put("settings", settings.toString());
		//
		// Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		// String json = gson.toJson(obj, LinkedHashMap.class);
		//
		//
		// optionsPost(json, url);
	}
	
	public JSONObject deleteDocumentHotspot(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getDocumentProgress(String id, String[] params,
			String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/progress") + "?id="
				+ id;
		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject getCategories() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("categories/get");

		return optionsGet(url);
	}

	public JSONObject getLanguages() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("languages/get");

		return optionsGet(url);
	}

	public JSONObject getCountries() throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("countries/get");

		return optionsGet(url);
	}

	public JSONObject getCollections(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collections/get");
		url = addParams(false, url, params, returnFields);

		return optionsGet(url);
	}

	public JSONObject getCollection(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/get") + "?id=" + id;
		url = addParams(true, url, params, returnFields);

		return optionsGet(url);
	}

	public JSONObject postCollection(String name) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/post");
		JSONObject json = new JSONObject();
		json.put("name", name);

		return optionsPost(json, url);
	}

	public JSONObject putCollection(String id, String name) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collection/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("name", name);
		return optionsPut(url, json);
	}
	
	public JSONObject deleteCollection(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collection/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getSection(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/get") + "?id=" + id;
		url = addParams(true, url, params, returnFields);

		return optionsGet(url);
	}

	public JSONObject postSection(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/post");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putSection(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}
	
	public JSONObject deleteSection(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("section/delete");

		return optionsDelete(url, id);
	}
	
	public JSONObject postSectionDocument(String id, String documents)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/post");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("documents", documents);

		return optionsPost(json, url);
	}
	
	public JSONObject deleteSectionDocument(String id, String documents) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/delete");
		String input = id + "&documents=" + documents;
		return optionsDelete(url, input);
	}

	public JSONObject search(String params) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("search/get") + "?" + params;

		return optionsGet(url);
	}

	public JSONObject getUser(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/get");
		url = addParams(false, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postUser(String[] body)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/post");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putUser(String[] body)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("user/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}

	public JSONObject getEmbeds(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embeds/get");
		url = addParams(false, url, params, returnFields);

		return optionsGet(url);
	}

	public JSONObject getEmbed(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/get") + "?id=" + id;
		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postEmbed(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("embed/post");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putEmbed(String id, String document_id, String type)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/put");
		JSONObject json = new JSONObject();
		json.put("id", id);
		json.put("document_id", document_id);
		json.put("type", type);

		return optionsPut(url, json);
	}
	
	public JSONObject deleteEmbed(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getMembers(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("members/get");
		url = addParams(false, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject getMember(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/get") + "?id=" + id;

		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postMember(String username, String password, String[] body)
			throws IOException, JSONException, NoSuchAlgorithmException {
		String url = config.yumpuEndpoints.get("member/post");
		JSONObject json = new JSONObject();
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		password = new BigInteger(1, m.digest()).toString(16);
		json.put("username", username);
		json.put("password", password);
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putMember(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("member/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}
	
	public JSONObject deleteMember(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getAccessTags(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTags/get");
		url = addParams(false, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject getAccessTag(String id, String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/get") + "?id=" + id;

		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postAccessTag(String[] body)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putAccessTag(String[] body) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTag/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}
	
	public JSONObject deleteAccessTag(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("accessTag/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getSubscriptions(String[] params, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscriptions/get");

		url = addParams(false, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject getSubscription(String id, String[] params,
			String returnFields[]) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/get") + "?id="
				+ id;
		url = addParams(true, url, params, returnFields);
		return optionsGet(url);
	}

	public JSONObject postSubscription(String[] body) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/post");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPost(json, url);
	}

	public JSONObject putSubscription(String[] body) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/put");
		JSONObject json = new JSONObject();
		createBody(body, json);

		return optionsPut(url, json);
	}
	
	public JSONObject deleteSubscription(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("subscription/delete");

		return optionsDelete(url, id);
	}

	private JSONObject optionsGet(String url) throws IOException, JSONException,
			MalformedURLException, ProtocolException {
		log("getDocument from " + url);
		JSONObject jo = rm.getRequest(config, url);
		responseCode = rm.responseCode;
		prettyJSON(jo);
		return jo;
	}

	private JSONObject optionsDelete(String url, String id) throws IOException, JSONException {
		log("deleteDocument from " + url);
		JSONObject jo = rm.deleteRequest(config, url, id);
		responseCode = rm.responseCode;
		prettyJSON(jo);
		return null;
	}

	private JSONObject optionsPost(JSONObject json, String url) throws IOException,
			JSONException, MalformedURLException, ProtocolException {
		log("postDocuments to " + url);
		JSONObject jo = rm.postRequest(config, url, json);
		responseCode = rm.responseCode;
		prettyJSON(jo);
		return jo;
	}

	private JSONObject optionsPut(String url, JSONObject json) throws IOException,
			JSONException, MalformedURLException, ProtocolException {
		log("putDocuments to " + url);
		JSONObject jo = rm.putRequest(config, url, json);
		responseCode = rm.responseCode;
		prettyJSON(jo);
		return jo;
	}

	private String prettyJSON(JSONObject jo) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
//		System.out.println(prettyJson);
		return prettyJson;
	}

	private String addParams(boolean isId, String url, String[] params,
			String[] returnFields) {

		if (isId) {
			url = url + "&";
			for (String s : params) {
				url = url + s + "&";
			}
			url = addParamsToURL(isId, returnFields, url);
		} else {
			url = url + "?";
			for (String s : params) {
				url = url + s + "&";
			}
			url = addParamsToURL(isId, returnFields, url);
		}

		return url;
	}

	private String addParamsToURL(boolean isId, String[] returnFields,
			String url) {
		if (returnFields.length > 0) {

			url = url + "return_fields=";
			for (int i = 0; i < returnFields.length; i++) {
				url = url + returnFields[i];
				if (!(i == returnFields.length - 1)) {
					url = url + ",";
				}
			}
		}
		return url;
	}

	private void createBody(String[] body, JSONObject json)
			throws JSONException {
		for (String s : body) {
			String index = s.substring(0, s.indexOf("="));
			String value = s.substring(s.indexOf("=") + 1);
			json.put(index, value);
		}
	}

	private void log(String logText) throws IOException {
		File yumpuLog = new File(".\\yumpu_log.txt");
		FileWriter writer = new FileWriter(yumpuLog, true);
		writer.write(logText + "\n");
		writer.close();
	}
}
