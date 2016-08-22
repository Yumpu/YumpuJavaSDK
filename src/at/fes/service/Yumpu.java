package at.fes.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.NameValuePair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.util.StringUtils;

public class Yumpu {
	private Config config = new Config();
	private String method = "GET";
	public int responseCode;

	public Yumpu() throws IOException {
		log("Yumpu Class initialized");
	}

	public JSONObject getDocuments(int offset, int limit) throws IOException,
			JSONException {
		String url = config.yumpuEndpoints.get("documents/get") + "?offset="
				+ offset + "&limit=" + limit;
		log("getDocuments from " + url);
		return prettyJSON(url);
	}

	public JSONObject getDocument(String id, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/get") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getDocumentHotspots(String id, String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspots") + "?id=" + id;
		url = addParamsToURL(true, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getDocumentHotspot(String id)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/hotspot") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getDocumentProgress(String id)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/progress") + "?id=" + id;
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getCategories()
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("categories/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getLanguages()
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("languages/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getCountries()
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("countries/get");
		log("getDocument from " + url);
		return prettyJSON(url);
	}
	
	public JSONObject getCollections(String returnFields[])
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("collections/get");
		url = addParamsToURL(false, returnFields, url);
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	private JSONObject getRequest(String url) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestProperty("X-ACCESS-TOKEN", config.config.get("token")); 

		// optional default is GET
		con.setRequestMethod(method);

		responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));

		String inputLine;
		JSONObject myObject = null;

		while ((inputLine = in.readLine()) != null) {
			myObject = new JSONObject(inputLine);
		}

		in.close();

		return myObject;
	}

	private JSONObject prettyJSON(String url) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		JSONObject jo = getRequest(url);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return jo;
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
