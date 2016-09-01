package at.fes.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	int responseCode;
	public YumpuFunctions yf = new YumpuFunctions();
	public Config config = new Config();

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		String[] body = {
				"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"title=file from tester",
				"page_teaser_image=src\\at\\fes\\examples\\media\\yumpu.png",
				"page_teaser_page_range=1-2",
				"page_teaser_url=http://www.yumpu.com/en" };

		String imgPath = "src\\at\\fes\\examples\\media\\yumpu.png";
		t.postDocumentUrl(imgPath, body);
	}

	@SuppressWarnings("rawtypes")
	public JSONObject postDocumentUrl(String imgPath, String[] body)
			throws IOException, JSONException {
		String url = "http://api.yumpu.com/2.0/document/url.json";

		JSONObject json = new JSONObject();
		yf.createBody(body, json);

		return optionsPostUrl(json, url, imgPath);
	}

	private JSONObject optionsPostUrl(JSONObject json, String url,
			String imgPath) throws IOException, JSONException,
			MalformedURLException, ProtocolException {
		yf.log("post " + url);
		JSONObject jo = postUrlRequest(config, url, json);
		yf.prettyJSON(jo);
		return jo;
	}

	public JSONObject postUrlRequest(Config config, String url,
			JSONObject json) throws JSONException,
			ParseException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);

		request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

		MultipartEntity entity = new MultipartEntity();

		Iterator keys = json.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) json.get(key);
			if (key.equals("page_teaser_image")) {
				File img = new File(value);
				FileBody imageBody = new FileBody(img);
				entity.addPart("page_teaser_image", imageBody);
			} else
				entity.addPart(key, new StringBody(value));
		}

		request.setEntity(entity);

		HttpResponse response = client.execute(request);
		JSONObject myObject = sendResponse(response);
		
		return myObject;
	}

	private JSONObject sendResponse(HttpResponse response) throws IOException,
			JSONException {
		String jsonString = EntityUtils.toString(response.getEntity());
		System.out.println(jsonString);
		JSONObject myObject;
		try {
			myObject = new JSONObject(jsonString);
		} catch (Exception e) {
			myObject = new JSONObject(jsonString.substring(jsonString
					.indexOf("{")));
		}
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}
}
