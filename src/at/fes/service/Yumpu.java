package at.fes.service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

public class Yumpu {
	private Config config;
	private RequestMethods rm = new RequestMethods();
	private YumpuFunctions yf = new YumpuFunctions();
	public int responseCode;
	public String documents = null;
	public String token;

	public Yumpu(String token) {
		this.token = token;
		Config config = new Config(token);
		this.config = config;
	}

	public Yumpu() throws IOException {
		yf.log("Yumpu Class initialized");
	}

	public JSONObject getDocuments(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("documents/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject getDocument(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postDocumentUrl(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/post/url");

		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPostUrl(json, url);
	}

	public JSONObject postDocumentFile(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/post/file");

		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPostFile(json, url);
	}

	public JSONObject putDocument(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteDocument(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getDocumentHotspots(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/hotspots");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject getDocumentHotspot(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postDocumentHotspot(String[] params, String[] settings)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);
		JSONObject jsonSett = new JSONObject();
		yf.createBody(settings, jsonSett);
		json.put("settings", jsonSett);
		return optionsPost(json, url);
	}

	public JSONObject putDocumentHotspot(String[] params, String[] settings)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);
		JSONObject jsonSett = new JSONObject();
		yf.createBody(settings, jsonSett);
		json.put("settings", jsonSett);
		return optionsPut(url, json);
	}

	public JSONObject deleteDocumentHotspot(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getDocumentProgress(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("document/progress");
		url = yf.addParams(params, url);
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

	public JSONObject getCollections(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collections/get");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject getCollection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collection/get");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject postCollection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collection/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putCollection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collection/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);
		return optionsPut(url, json);
	}

	public JSONObject deleteCollection(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("collection/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getSection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/get");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject postSection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putSection(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteSection(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("section/delete");

		return optionsDelete(url, id);
	}

	public JSONObject postSectionDocument(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject deleteSectionDocument(String id, String documents)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("sectionDocument/delete");
		String input = id + "&documents=" + documents;
		return optionsDelete(url, input);
	}

	public JSONObject search(String[] params) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("search/get");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject getUser(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("user/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postUser(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("user/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putUser(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("user/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject getEmbeds(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("embeds/get");
		url = yf.addParams(params, url);

		return optionsGet(url);
	}

	public JSONObject getEmbed(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("embed/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postEmbed(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("embed/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putEmbed(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("embed/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteEmbed(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("embed/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getMembers(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("members/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject getMember(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("member/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postMember(String username, String password,
			String[] params) throws IOException, JSONException,
			NoSuchAlgorithmException {
		String url = config.yumpuEndpoints.get("member/post");
		JSONObject json = new JSONObject();
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		password = new BigInteger(1, m.digest()).toString(16);
		json.put("username", username);
		json.put("password", password);
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putMember(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("member/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteMember(String id) throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("member/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getAccessTags(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTags/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject getAccessTag(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTag/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postAccessTag(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putAccessTag(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTag/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteAccessTag(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("accessTag/delete");

		return optionsDelete(url, id);
	}

	public JSONObject getSubscriptions(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("subscriptions/get");

		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject getSubscription(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("subscription/get");
		url = yf.addParams(params, url);
		return optionsGet(url);
	}

	public JSONObject postSubscription(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("subscription/post");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPost(json, url);
	}

	public JSONObject putSubscription(String[] params) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("subscription/put");
		JSONObject json = new JSONObject();
		yf.createBody(params, json);

		return optionsPut(url, json);
	}

	public JSONObject deleteSubscription(String id) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("subscription/delete");

		return optionsDelete(url, id);
	}

	/*
	 * END OF FUNCTIONS methods above are necessary to build a correct request
	 * post document per file and per url have both a specific option method 1.
	 * step: write into log 2. step: create a JSON Object with the required
	 * request method 3. step: get the response code 4. step: create a pretty
	 * JSON 5. step: return the result
	 */

	// GET request
	private JSONObject optionsGet(String url) throws IOException,
			JSONException, MalformedURLException, ProtocolException {
		yf.log("get " + url);
		JSONObject jo = rm.getRequest(config, url);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	// POST request
	private JSONObject optionsPost(JSONObject json, String url)
			throws IOException, JSONException, MalformedURLException,
			ProtocolException {
		yf.log("post " + url);
		JSONObject jo = rm.postRequest(config, url, json);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	// POST document url request
	private JSONObject optionsPostUrl(JSONObject json, String url)
			throws IOException, JSONException, MalformedURLException,
			ProtocolException {
		yf.log("post " + url);
		JSONObject jo = rm.postUrlRequest(config, url, json);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	// POST document file request
	private JSONObject optionsPostFile(JSONObject json, String url)
			throws IOException, JSONException, MalformedURLException,
			ProtocolException {
		yf.log("post " + url);
		JSONObject jo = rm.postFileRequest(config, url, json);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	// PUT request
	private JSONObject optionsPut(String url, JSONObject json)
			throws IOException, JSONException, MalformedURLException,
			ProtocolException {
		yf.log("put " + url);
		JSONObject jo = rm.putRequest(config, url, json);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}

	// DELETE request
	private JSONObject optionsDelete(String url, String id) throws IOException,
			JSONException {
		yf.log("delete " + url);
		JSONObject jo = rm.deleteRequest(config, url, id);
		responseCode = rm.responseCode;
		yf.prettyJSON(jo);
		return jo;
	}
}
