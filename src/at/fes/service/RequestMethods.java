package at.fes.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class RequestMethods {
	@SuppressWarnings("unused")
	private Config config;
	public int responseCode;

	@NotThreadSafe
	class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
		public static final String METHOD_NAME = "DELETE";

		public String getMethod() {
			return METHOD_NAME;
		}

		public HttpDeleteWithBody(final String uri) {
			super();
			setURI(URI.create(uri));
		}

		public HttpDeleteWithBody(final URI uri) {
			super();
			setURI(uri);
		}

		public HttpDeleteWithBody() {
			super();
		}
	}

	public JSONObject getRequest(Config config, String url)
			throws ClientProtocolException, IOException, JSONException {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		HttpResponse response = client.execute(request);

		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	public JSONObject deleteRequest(Config config, String url, String id)
			throws ClientProtocolException, IOException, JSONException {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpDeleteWithBody request = new HttpDeleteWithBody(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));

		StringEntity inputId = new StringEntity("id=" + id);

		request.setEntity(inputId);

		HttpResponse response = client.execute(request);

		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	public JSONObject postRequest(Config config, String url, JSONObject json)
			throws JSONException, ParseException, IOException {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);

		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		request.addHeader("content-type", "application/json");

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	@SuppressWarnings({"unchecked", "rawtypes" })
	public JSONObject postFileRequest(Config config, String url, String path,
			Map map, String imgPath) throws JSONException, ParseException, IOException {
		File file = new File(path);
		File img = new File(imgPath);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		MultipartEntity entity = new MultipartEntity();
		FileBody pdfBody = new FileBody(file);
		FileBody imageBody = new FileBody(img);
		entity.addPart("file", pdfBody);
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
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public JSONObject postUrlRequest(Config config, String url,
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

	public JSONObject putRequest(Config config, String url, JSONObject json)
			throws ClientProtocolException, IOException, JSONException {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut request = new HttpPut(url);
		
		request.setHeader("X-ACCESS-TOKEN", config.config.get("token"));
		request.addHeader("Content-Type", "application/json");

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JSONObject myObject = sendResponse(response);

		return myObject;
	}

	private JSONObject sendResponse(HttpResponse response) throws IOException,
			JSONException {
		String jsonString = EntityUtils.toString(response.getEntity());
		JSONObject myObject;
		try {
			myObject = new JSONObject(jsonString);
		} catch (Exception e) {
			myObject = new JSONObject(jsonString.substring(jsonString.indexOf("{")));
		}
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}
}
