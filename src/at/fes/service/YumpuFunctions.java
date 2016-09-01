package at.fes.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class YumpuFunctions {

	public String prettyJSON(JSONObject jo) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		// System.out.println(prettyJson);
		return prettyJson;
	}

	public void createBody(String[] params, JSONObject json)
			throws JSONException {
		for (String s : params) {
			String index = s.substring(0, s.indexOf("="));
			String value = s.substring(s.indexOf("=") + 1);
			json.put(index, value);
		}
	}

	public void log(String logText) throws IOException {
		File yumpuLog = new File(".\\yumpu_log.txt");
		FileWriter writer = new FileWriter(yumpuLog, true);
		writer.write(logText + "\n");
		writer.close();
	}
	
	public String addParamsToURL(boolean isId, String[] returnFields,
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
	
	public String addParams(boolean isId, String url, String[] params,
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
	
	public String addParams(String[] params, String url) {
		url = url + "?";
		for (String s : params) {
			url = url + s + "&";
		}
		return url;
	}
}
