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
		yumpuEndpoints.put("document/hotspots" , String.valueOf(config.get("endpointDomain") + "document/hotspots.json"));
		yumpuEndpoints.put("document/hotspot" , String.valueOf(config.get("endpointDomain") + "document/hotspot.json"));
		yumpuEndpoints.put("document/progress" , String.valueOf(config.get("endpointDomain") + "document/progress.json"));
		yumpuEndpoints.put("categories/get" , String.valueOf(config.get("endpointDomain") + "document/categories.json"));
		yumpuEndpoints.put("languages/get" , String.valueOf(config.get("endpointDomain") + "document/languages.json"));
		yumpuEndpoints.put("countries/get" , String.valueOf(config.get("endpointDomain") + "countries.json"));
		yumpuEndpoints.put("collections/get" , String.valueOf(config.get("endpointDomain") + "collections.json"));
	}
}
