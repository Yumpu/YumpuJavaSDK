package at.fes.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tester {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";
	private RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws IOException, JSONException {
		Tester t = new Tester();
		String[] params = {};
		String returnFields[] = { "url" };
		String[] body = {"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf", "title=ayxcxycsd"};
		t.postDocumentUrl(body);
	}

	public void postDocumentUrl(String[] body)
			throws IOException, JSONException {
		String url = config.yumpuEndpoints.get("document/post/url");

		JSONObject json = new JSONObject();
		createBody(body, json);
		optionsPost(json, url);
	}

	private void createBody(String[] body, JSONObject json)
			throws JSONException {
		for (String s : body) {
			String index = s.substring(0, s.indexOf("="));
			String value = s.substring(s.indexOf("=") + 1);
			json.put(index, value);
		}
	}

	private String addParams(boolean isId, String url, String[] params,
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

	private String addParamsToURL(boolean isId, String[] returnFields,
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

	private String prettyJSON(JSONObject jo) throws MalformedURLException,
			IOException, ProtocolException, JSONException {
		Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = prettyGson.toJson(jo);
		System.out.println(prettyJson);
		return prettyJson;
	}

	private void optionsPost(JSONObject json, String url) throws IOException,
			JSONException, MalformedURLException, ProtocolException {
		JSONObject jo = rm.postRequest(url, json);
		responseCode = rm.responseCode;
		prettyJSON(jo);
	}
}
