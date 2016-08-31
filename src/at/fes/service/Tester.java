package at.fes.service;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	int responseCode;

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		Config config = new Config("vEKfrHQLPlOc2sAbU5zqGwoegmSdnC1M");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf");
		map.put("title", "Das istasdsd");
		map.put("description", "das ist genial");
		map.put("category", "1");
		map.put("page_teaser_page_range", "1-2");
		map.put("page_teaser_url", "http://www.yumpu.com/en");

		String url = "http://devapi.yumpu.com/2.0/document/url.json";
		String imgPath = "src\\at\\fes\\examples\\media\\yumpu.png";
		t.postImgRequest(config, url, map, imgPath);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public JSONObject postImgRequest(Config config, String url,
			Map map, String imgPath) throws JSONException, ParseException, IOException {
		File img = new File(imgPath);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		MultipartEntity entity = new MultipartEntity();
		FileBody imageBody = new FileBody(img);
		entity.addPart("page_teaser_image", imageBody);

		Set<String> keys = map.keySet();
		for (String key : keys) {
			entity.addPart(key, new StringBody((String) map.get(key)));
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
