package at.fes.service;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Yumpu {
	
	private final Logger logger = Logger.getLogger(Yumpu.class);
	protected static final String TAG = Yumpu.class.getSimpleName();
	
	private Config config;
	private RequestMethods rm = new RequestMethods();
	public int responseCode;
	public String documents = null;
	public String apptoken;

	public Yumpu(String apptoken) {
		this.apptoken = apptoken;
		this.config = new Config(apptoken);
		
		logger.debug("Yumpu Class initialized! with apptoken="+apptoken);
	}
	public Yumpu(String apptoken, String api_url) {
		this.apptoken = apptoken;
		this.config = new Config(apptoken, api_url);
		
		logger.debug("Yumpu Class initialized! with " + " apptoken="+apptoken+ " api_url="+api_url);
	}
	
	/*
	 * --- SDK API functions ---  
	 */
	public JsonObject getDocuments(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("documents/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject getDocument(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postDocumentUrl(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/post/url");

		JsonObject json = MyUtils.createBody(params);

		return optionsPostUrl(json, url);
	}

	public JsonObject postDocumentFile(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/post/file");

		JsonObject json = MyUtils.createBody(params);

		return optionsPostFile(json, url);
	}

	public JsonObject putDocument(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteDocument(String id) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getDocumentHotspots(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/hotspots");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject getDocumentHotspot(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/hotspot/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postDocumentHotspot(String[] params, String[] settings)
			throws IOException, Exception {
		String url = config.yumpuEndpoints.get("document/hotspot/post");
		JsonObject json = MyUtils.createBody(params);
		JsonObject jsonSettings = MyUtils.createBody(settings);
		json.add("settings", jsonSettings);
		return optionsPost(json, url);
	}

	public JsonObject putDocumentHotspot(String[] params, String[] settings)
			throws IOException, Exception {
		String url = config.yumpuEndpoints.get("document/hotspot/put");
		JsonObject json = MyUtils.createBody(params);
		JsonObject jsonSettings = MyUtils.createBody(settings);
		json.add("settings", jsonSettings);
		return optionsPut(url, json);
	}

	public JsonObject deleteDocumentHotspot(String id) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/hotspot/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getDocumentProgress(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("document/progress");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject getCategories() throws IOException, Exception {
		String url = config.yumpuEndpoints.get("categories/get");

		return optionsGet(url);
	}

	public JsonObject getLanguages() throws IOException, Exception {
		String url = config.yumpuEndpoints.get("languages/get");

		return optionsGet(url);
	}

	public JsonObject getCountries() throws IOException, Exception {
		String url = config.yumpuEndpoints.get("countries/get");
		
		return optionsGet(url);
	}

	public JsonObject getCollections(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("collections/get");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject getCollection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("collection/get");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject postCollection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("collection/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putCollection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("collection/put");
		JsonObject json = MyUtils.createBody(params);
		return optionsPut(url, json);
	}

	public JsonObject deleteCollection(String id) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("collection/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getSection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("section/get");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject postSection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("section/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putSection(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("section/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteSection(String id) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("section/delete");

		return optionsDelete(url, id);
	}

	public JsonObject postSectionDocument(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("sectionDocument/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject deleteSectionDocument(String id, String documents)
			throws IOException, Exception {
		String url = config.yumpuEndpoints.get("sectionDocument/delete");
		String input = id + "&documents=" + documents;
		return optionsDelete(url, input);
	}

	public JsonObject search(String[] params) throws IOException, Exception {
		String url = config.yumpuEndpoints.get("search/get");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject getUser(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("user/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postUser(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("user/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putUser(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("user/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject getEmbeds(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("embeds/get");
		url = MyUtils.addParams(params, url);

		return optionsGet(url);
	}

	public JsonObject getEmbed(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("embed/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postEmbed(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("embed/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putEmbed(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("embed/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteEmbed(String id) throws IOException, Exception {
		String url = config.yumpuEndpoints.get("embed/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getMembers(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("members/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject getMember(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("member/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postMember(String username, String password,
			String[] params) throws IOException, Exception,
			NoSuchAlgorithmException {
		String url = config.yumpuEndpoints.get("member/post");
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(password.getBytes(), 0, password.length());
		password = new BigInteger(1, m.digest()).toString(16);
		
		JsonObject json = MyUtils.createBody(params);
		json.addProperty("username", username);
		json.addProperty("password", password);
		

		return optionsPost(json, url);
	}

	public JsonObject putMember(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("member/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteMember(String id) throws IOException, Exception {
		String url = config.yumpuEndpoints.get("member/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getAccessTags(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("accessTags/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject getAccessTag(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("accessTag/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postAccessTag(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("accessTag/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putAccessTag(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("accessTag/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteAccessTag(String id) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("accessTag/delete");

		return optionsDelete(url, id);
	}

	public JsonObject getSubscriptions(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("subscriptions/get");

		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject getSubscription(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("subscription/get");
		url = MyUtils.addParams(params, url);
		return optionsGet(url);
	}

	public JsonObject postSubscription(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("subscription/post");
		JsonObject json = MyUtils.createBody(params);

		return optionsPost(json, url);
	}

	public JsonObject putSubscription(String[] params) throws IOException,
			Exception {
		String url = config.yumpuEndpoints.get("subscription/put");
		JsonObject json = MyUtils.createBody(params);

		return optionsPut(url, json);
	}

	public JsonObject deleteSubscription(String id) throws IOException,
			Exception {
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
	private JsonObject optionsGet(String url) throws IOException,
			Exception, MalformedURLException, ProtocolException {
		logger.debug("get " + url);
		
		//JsonObject jo = rm.getRequest(config, url);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.getRequest(config, url);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}

	// POST request
	private JsonObject optionsPost(JsonObject json, String url)
			throws IOException, Exception, MalformedURLException,
			ProtocolException {
		logger.debug("post " + url);
		
		//JsonObject jo = rm.postRequest(config, url, json);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.postRequest(config, url, json);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}

	// POST document url request
	private JsonObject optionsPostUrl(JsonObject json, String url)
			throws IOException, Exception, MalformedURLException,
			ProtocolException {
		logger.debug("post " + url);
		
		//JsonObject jo = rm.postUrlRequest(config, url, json);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.postUrlRequest(config, url, json);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}

	// POST document file request
	private JsonObject optionsPostFile(JsonObject json, String url)
			throws IOException, Exception, MalformedURLException,
			ProtocolException {
		logger.debug("post " + url);
		
		//JsonObject jo = rm.postFileRequest(config, url, json);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.postFileRequest(config, url, json);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}

	// PUT request
	private JsonObject optionsPut(String url, JsonObject json)
			throws IOException, Exception, MalformedURLException,
			ProtocolException {
		logger.debug("put " + url);
		
		//JsonObject jo = rm.putRequest(config, url, json);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.putRequest(config, url, json);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}

	// DELETE request
	private JsonObject optionsDelete(String url, String id) 
			throws IOException,Exception {
		logger.debug("delete " + url);
		
		//JsonObject jo = rm.deleteRequest(config, url, id);
		//responseCode = rm.responseCode;
		ResponseData responseData = rm.deleteRequest(config, url, id);
		responseCode = responseData.meta.code;
		
		logger.debug( MyUtils.getGsonSimpleWithPrettyPrinting().toJson(responseData) );
		return responseData.getDataAsJson().getAsJsonObject();
	}
}
