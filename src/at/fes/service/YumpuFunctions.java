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

	//generate pretty JSON output
	public String prettyJSON(JSONObject jo) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		// System.out.println(prettyJson);
		return prettyJson;
	}

	//write params into a JSON Object
	public void createBody(String[] params, JSONObject json)
			throws JSONException {
		for (String s : params) {
			String index = s.substring(0, s.indexOf("="));
			String value = s.substring(s.indexOf("=") + 1);
			json.put(index, value);
		}
	}

	//write the log in the log file
	public void log(String logText) throws IOException {
		File yumpuLog = new File(".\\yumpu_log.txt");
		FileWriter writer = new FileWriter(yumpuLog, true);
		writer.write(logText + "\n");
		writer.close();
	}
	
	//add parameter to URL
	public String addParams(String[] params, String url) {
		url = url + "?";
		for (String s : params) {
			url = url + s + "&";
		}
		return url;
	}
}
