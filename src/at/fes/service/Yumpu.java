package at.fes.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	Config config = new Config();
	String method = "GET";

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
		if (returnFields.length > 0) {
			url = url + "&return_fields=";
			for (int i = 0; i < returnFields.length; i++) {
				url = url + returnFields[i];
				if (!(i == returnFields.length - 1)) {
					url = url + ",";
				}
			}
		}
		log("getDocument from " + url);
		return prettyJSON(url);
	}

	private JSONObject executeRequest(String url) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add request header
		con.setRequestProperty("X-ACCESS-TOKEN", config.config.get("token"));

		// optional default is GET
		con.setRequestMethod(method);

		int responseCode = con.getResponseCode();
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
		JSONObject jo = executeRequest(url);
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return jo;
	}

	private void log(String logText) throws IOException {
		File yumpuLog = new File (".\\src\\at\\fes\\log\\yumpu_log.txt");
		FileWriter writer = new FileWriter(
				yumpuLog, true);
		writer.write(logText + "\n");
		writer.close();
	}
}
