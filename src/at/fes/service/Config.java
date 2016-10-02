package at.fes.service;

import java.util.HashMap;
import java.util.Map;

public class Config {
	
	public String api_url = "http://api.yumpu.com/2.0/";
	public String api_search_url = "http://search.yumpu.com/2.0/";
	public String apptoken = "";
	Map<String, String> yumpuEndpoints;
	
	public Config(String apptoken) {
		new Config(apptoken, "");
	}
	public Config(String apptoken, String api_url) {
		
		if (MyUtils.isNotEmpty(apptoken)) {
			this.apptoken = apptoken;
		}
		if (MyUtils.isNotEmpty(api_url)) {
			this.api_url = api_url;
		}
		
		initYumpuEndpoints();
	}
	
	// define all endpoints of the yumpu api
	private void initYumpuEndpoints() {
		yumpuEndpoints = new HashMap<String, String>();
		
		if (MyUtils.isNotEmpty(this.apptoken)) yumpuEndpoints.put("apptoken" , String.valueOf(this.apptoken));
		if (MyUtils.isNotEmpty(this.api_url)) yumpuEndpoints.put("api_url" , String.valueOf(this.api_url));
		if (MyUtils.isNotEmpty(this.api_search_url)) yumpuEndpoints.put("api_search_url" , String.valueOf(this.api_search_url));
		
		yumpuEndpoints.put("documents/get" , String.valueOf(this.api_url + "documents.json"));
		yumpuEndpoints.put("document/get" , String.valueOf(this.api_url + "document.json"));
		yumpuEndpoints.put("document/post/url" , String.valueOf(this.api_url + "document/url.json"));
		yumpuEndpoints.put("document/post/file" , String.valueOf(this.api_url + "document/file.json"));
		yumpuEndpoints.put("document/delete" , String.valueOf(this.api_url + "document.json"));
		yumpuEndpoints.put("document/put" , String.valueOf(this.api_url + "document.json"));
		yumpuEndpoints.put("document/hotspots" , String.valueOf(this.api_url + "document/hotspots.json"));
		yumpuEndpoints.put("document/hotspot/get" , String.valueOf(this.api_url + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/post" , String.valueOf(this.api_url + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/put" , String.valueOf(this.api_url + "document/hotspot.json"));
		yumpuEndpoints.put("document/hotspot/delete" , String.valueOf(this.api_url + "document/hotspot.json"));
		yumpuEndpoints.put("document/progress" , String.valueOf(this.api_url + "document/progress.json"));
		yumpuEndpoints.put("categories/get" , String.valueOf(this.api_url + "document/categories.json"));
		yumpuEndpoints.put("languages/get" , String.valueOf(this.api_url + "document/languages.json"));
		yumpuEndpoints.put("countries/get" , String.valueOf(this.api_url + "countries.json"));
		yumpuEndpoints.put("collections/get" , String.valueOf(this.api_url + "collections.json"));
		yumpuEndpoints.put("collection/get" , String.valueOf(this.api_url + "collection.json"));
		yumpuEndpoints.put("collection/post" , String.valueOf(this.api_url + "collection.json"));
		yumpuEndpoints.put("collection/put" , String.valueOf(this.api_url + "collection.json"));
		yumpuEndpoints.put("collection/delete" , String.valueOf(this.api_url + "collection.json"));
		yumpuEndpoints.put("section/get" , String.valueOf(this.api_url + "collection/section.json"));
		yumpuEndpoints.put("section/post" , String.valueOf(this.api_url + "collection/section.json"));
		yumpuEndpoints.put("section/put" , String.valueOf(this.api_url + "collection/section.json"));
		yumpuEndpoints.put("section/delete" , String.valueOf(this.api_url + "collection/section.json"));
		yumpuEndpoints.put("sectionDocument/post" , String.valueOf(this.api_url + "collection/section/document.json"));
		yumpuEndpoints.put("sectionDocument/delete" , String.valueOf(this.api_url + "collection/section/document.json"));
		yumpuEndpoints.put("search/get" , String.valueOf(this.api_search_url + "search.json"));
		yumpuEndpoints.put("user/get" , String.valueOf(this.api_url + "user.json"));
		yumpuEndpoints.put("user/post" , String.valueOf(this.api_url + "user.json"));
		yumpuEndpoints.put("user/put" , String.valueOf(this.api_url + "user.json"));
		yumpuEndpoints.put("embeds/get" , String.valueOf(this.api_url + "embeds.json"));
		yumpuEndpoints.put("embed/get" , String.valueOf(this.api_url + "embed.json"));
		yumpuEndpoints.put("embed/post" , String.valueOf(this.api_url + "embed.json"));
		yumpuEndpoints.put("embed/put" , String.valueOf(this.api_url + "embed.json"));
		yumpuEndpoints.put("embed/delete" , String.valueOf(this.api_url + "embed.json"));
		yumpuEndpoints.put("members/get" , String.valueOf(this.api_url + "account/members.json"));
		yumpuEndpoints.put("member/get" , String.valueOf(this.api_url + "account/member.json"));
		yumpuEndpoints.put("member/post" , String.valueOf(this.api_url + "account/member.json"));
		yumpuEndpoints.put("member/put" , String.valueOf(this.api_url + "account/member.json"));
		yumpuEndpoints.put("member/delete" , String.valueOf(this.api_url + "account/member.json"));
		yumpuEndpoints.put("accessTags/get" , String.valueOf(this.api_url + "account/access_tags.json"));
		yumpuEndpoints.put("accessTag/get" , String.valueOf(this.api_url + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/post" , String.valueOf(this.api_url + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/put" , String.valueOf(this.api_url + "account/access_tag.json"));
		yumpuEndpoints.put("accessTag/delete" , String.valueOf(this.api_url + "account/access_tag.json"));
		yumpuEndpoints.put("subscriptions/get" , String.valueOf(this.api_url + "account/subscriptions.json"));
		yumpuEndpoints.put("subscription/get" , String.valueOf(this.api_url + "account/subscription.json"));
		yumpuEndpoints.put("subscription/post" , String.valueOf(this.api_url + "account/subscription.json"));
		yumpuEndpoints.put("subscription/put" , String.valueOf(this.api_url + "account/subscription.json"));
		yumpuEndpoints.put("subscription/delete" , String.valueOf(this.api_url + "account/subscription.json"));
	}
}
