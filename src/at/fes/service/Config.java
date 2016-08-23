package at.fes.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class Config {
	Map<String, String> config;
	Map<String, String> yumpuEndpoints;
	
	public Config() {
		config();
		yumpuEndpoints();

	}
	
	public void config(){
		config = new HashMap<String, String>();
		
		config.put("token" , String.valueOf("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv"));
		config.put("endpointDomain" , String.valueOf("http://api.yumpu.com/2.0/"));
		config.put("endpointSearchDomain" , String.valueOf("http://search.yumpu.com/2.0/"));
	}
	
	public void yumpuEndpoints() {

		yumpuEndpoints = new HashMap<String, String>();
		yumpuEndpoints.put("documents/get" , String.valueOf(config.get("endpointDomain") + "documents.json"));
		yumpuEndpoints.put("document/get" , String.valueOf(config.get("endpointDomain") + "document.json"));
		yumpuEndpoints.put("document/post/url" , String.valueOf(config.get("endpointDomain") + "document/url.json"));
		yumpuEndpoints.put("document/hotspots" , String.valueOf(config.get("endpointDomain") + "document/hotspots.json"));
		yumpuEndpoints.put("document/hotspot/get" , String.valueOf(config.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/post" , String.valueOf(config.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/progress" , String.valueOf(config.get("endpointDomain") + "document/progress.json"));
		yumpuEndpoints.put("categories/get" , String.valueOf(config.get("endpointDomain") + "document/categories.json"));
		yumpuEndpoints.put("languages/get" , String.valueOf(config.get("endpointDomain") + "document/languages.json"));
		yumpuEndpoints.put("countries/get" , String.valueOf(config.get("endpointDomain") + "countries.json"));
		yumpuEndpoints.put("collections/get" , String.valueOf(config.get("endpointDomain") + "collections.json"));
		yumpuEndpoints.put("collection/get" , String.valueOf(config.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("collection/post" , String.valueOf(config.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("section/get" , String.valueOf(config.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("section/post" , String.valueOf(config.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("sectionDocument/post" , String.valueOf(config.get("endpointDomain") + "collection/section/document.json"));
		yumpuEndpoints.put("search/get" , String.valueOf(config.get("endpointSearchDomain") + "search.json"));
		yumpuEndpoints.put("user/get" , String.valueOf(config.get("endpointDomain") + "user.json"));
		yumpuEndpoints.put("user/post" , String.valueOf(config.get("endpointDomain") + "user.json"));
		yumpuEndpoints.put("embeds/get" , String.valueOf(config.get("endpointDomain") + "embeds.json"));
		yumpuEndpoints.put("embed/get" , String.valueOf(config.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("embed/post" , String.valueOf(config.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("members/get" , String.valueOf(config.get("endpointDomain") + "account/members.json"));
		yumpuEndpoints.put("member/get" , String.valueOf(config.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("member/post" , String.valueOf(config.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("accessTags/get" , String.valueOf(config.get("endpointDomain") + "account/access_tags.json"));
		yumpuEndpoints.put("accessTag/get" , String.valueOf(config.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/post" , String.valueOf(config.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("subscriptions/get" , String.valueOf(config.get("endpointDomain") + "account/subscriptions.json"));
		yumpuEndpoints.put("subscription/get" , String.valueOf(config.get("endpointDomain") + "account/subscription.json"));
		yumpuEndpoints.put("subscription/post" , String.valueOf(config.get("endpointDomain") + "account/subscription.json"));
	}
}
