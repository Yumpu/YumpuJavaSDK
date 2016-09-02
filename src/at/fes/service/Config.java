package at.fes.service;

import java.util.HashMap;
import java.util.Map;

public class Config {
	Map<String, String> domainSettings;
	Map<String, String> yumpuEndpoints;
	
	public Config(String token) {
		domainSettings(token);
		yumpuEndpoints();
	}
	
	// create a map with the domains and the token
	public void domainSettings(String token){
		domainSettings = new HashMap<String, String>();
		
		domainSettings.put("token" , String.valueOf(token));
		domainSettings.put("endpointDomain" , String.valueOf("http://api.yumpu.com/2.0/"));
		domainSettings.put("endpointSearchDomain" , String.valueOf("http://search.yumpu.com/2.0/"));
	}
	
	// define all endpoints of the yumpu api
	public void yumpuEndpoints() {
		yumpuEndpoints = new HashMap<String, String>();
		
		yumpuEndpoints.put("documents/get" , String.valueOf(domainSettings.get("endpointDomain") + "documents.json"));
		yumpuEndpoints.put("document/get" , String.valueOf(domainSettings.get("endpointDomain") + "document.json"));
		yumpuEndpoints.put("document/post/url" , String.valueOf(domainSettings.get("endpointDomain") + "document/url.json"));
		yumpuEndpoints.put("document/post/file" , String.valueOf(domainSettings.get("endpointDomain") + "document/file.json"));
		yumpuEndpoints.put("document/delete" , String.valueOf(domainSettings.get("endpointDomain") + "document.json"));
		yumpuEndpoints.put("document/put" , String.valueOf(domainSettings.get("endpointDomain") + "document.json"));
		yumpuEndpoints.put("document/hotspots" , String.valueOf(domainSettings.get("endpointDomain") + "document/hotspots.json"));
		yumpuEndpoints.put("document/hotspot/get" , String.valueOf(domainSettings.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/post" , String.valueOf(domainSettings.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/put" , String.valueOf(domainSettings.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/delete" , String.valueOf(domainSettings.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/progress" , String.valueOf(domainSettings.get("endpointDomain") + "document/progress.json"));
		yumpuEndpoints.put("categories/get" , String.valueOf(domainSettings.get("endpointDomain") + "document/categories.json"));
		yumpuEndpoints.put("languages/get" , String.valueOf(domainSettings.get("endpointDomain") + "document/languages.json"));
		yumpuEndpoints.put("countries/get" , String.valueOf(domainSettings.get("endpointDomain") + "countries.json"));
		yumpuEndpoints.put("collections/get" , String.valueOf(domainSettings.get("endpointDomain") + "collections.json"));
		yumpuEndpoints.put("collection/get" , String.valueOf(domainSettings.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("collection/post" , String.valueOf(domainSettings.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("collection/put" , String.valueOf(domainSettings.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("collection/delete" , String.valueOf(domainSettings.get("endpointDomain") + "collection.json"));
		yumpuEndpoints.put("section/get" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("section/post" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("section/put" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("section/delete" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section.json"));
		yumpuEndpoints.put("sectionDocument/post" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section/document.json"));
		yumpuEndpoints.put("sectionDocument/delete" , String.valueOf(domainSettings.get("endpointDomain") + "collection/section/document.json"));
		yumpuEndpoints.put("search/get" , String.valueOf(domainSettings.get("endpointSearchDomain") + "search.json"));
		yumpuEndpoints.put("user/get" , String.valueOf(domainSettings.get("endpointDomain") + "user.json"));
		yumpuEndpoints.put("user/post" , String.valueOf(domainSettings.get("endpointDomain") + "user.json"));
		yumpuEndpoints.put("user/put" , String.valueOf(domainSettings.get("endpointDomain") + "user.json"));
		yumpuEndpoints.put("embeds/get" , String.valueOf(domainSettings.get("endpointDomain") + "embeds.json"));
		yumpuEndpoints.put("embed/get" , String.valueOf(domainSettings.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("embed/post" , String.valueOf(domainSettings.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("embed/put" , String.valueOf(domainSettings.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("embed/delete" , String.valueOf(domainSettings.get("endpointDomain") + "embed.json"));
		yumpuEndpoints.put("members/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/members.json"));
		yumpuEndpoints.put("member/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("member/post" , String.valueOf(domainSettings.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("member/put" , String.valueOf(domainSettings.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("member/delete" , String.valueOf(domainSettings.get("endpointDomain") + "account/member.json"));
		yumpuEndpoints.put("accessTags/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/access_tags.json"));
		yumpuEndpoints.put("accessTag/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/post" , String.valueOf(domainSettings.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/put" , String.valueOf(domainSettings.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/delete" , String.valueOf(domainSettings.get("endpointDomain") + "account/access_tag.json"));
		yumpuEndpoints.put("subscriptions/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/subscriptions.json"));
		yumpuEndpoints.put("subscription/get" , String.valueOf(domainSettings.get("endpointDomain") + "account/subscription.json"));
		yumpuEndpoints.put("subscription/post" , String.valueOf(domainSettings.get("endpointDomain") + "account/subscription.json"));
		yumpuEndpoints.put("subscription/put" , String.valueOf(domainSettings.get("endpointDomain") + "account/subscription.json"));
		yumpuEndpoints.put("subscription/delete" , String.valueOf(domainSettings.get("endpointDomain") + "account/subscription.json"));
	}
}
