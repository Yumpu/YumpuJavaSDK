package at.fes.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestMethods {
	private Config config;
	public int responseCode;
	private String method = "GET";

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

	public JSONObject postFileRequest(Config config, String url) throws JSONException,
			ParseException, IOException {
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
		PostMethod request = new PostMethod(url);

		request.setRequestHeader("X-ACCESS-TOKEN", config.config.get("token"));

		File targetFile = new File(
				"C:\\Users\\stefan.feurstein\\Downloads\\DA.pdf");
		Part[] parts = { new FilePart("file", targetFile),
				new StringPart("title", "vom Dornbirnwe Markt") };

		request.setRequestEntity(new MultipartRequestEntity(parts, request
				.getParams()));

		responseCode = client.executeMethod(request);
		String response = request.getResponseBodyAsString();
		JSONObject myObject = new JSONObject(response);

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
		JSONObject myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}
}
