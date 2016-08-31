package at.fes.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.client.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		
		HashMap<String, String>map = new HashMap<String, String>();
		map.put("title", "Das ist super");
		map.put("description", "das ist genial");
		map.put("category", "1");
		
		String url = "http://api.yumpu.com/2.0/document/file.json";
		String path = "C:\\Users\\stefan.feurstein\\Downloads\\DA.pdf";
		t.postFileRequest(url, path, map);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes"})
	public JSONObject postFileRequest(String url, String path, Map map) throws JSONException,
			ParseException, IOException {
		File file = new File(path);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		MultipartEntity entity = new MultipartEntity();
		FileBody body = new FileBody(file);
		entity.addPart("file", body);
		
		Set<String> keys = map.keySet();
		for (String key : keys) {
			entity.addPart(key, new StringBody((String) map.get(key)));
		}
		request.setEntity(entity);
		
		HttpResponse response = client.execute(request);
//		JSONObject myObject = sendResponse(response);

		return null;
	}
}
