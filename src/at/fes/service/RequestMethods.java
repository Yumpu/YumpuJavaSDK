package at.fes.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class RequestMethods {
	@SuppressWarnings("unused")
	private Config config;
	public int responseCode;

	private final static int TIMEOUT = 30000;
	RequestConfig httpRequestConfig;
	public RequestMethods() {
		httpRequestConfig = RequestConfig.custom()
		        .setSocketTimeout(TIMEOUT)
		        .setConnectTimeout(TIMEOUT)
		        .build();
	}
	
	// this class is used to build the correct DELETE request
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

	// create the GET request
	public JsonObject getRequest(Config config, String url)
			throws ClientProtocolException, IOException, Exception {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		request.setConfig(httpRequestConfig);

		request.setHeader("X-ACCESS-TOKEN", config.apptoken);
		HttpResponse response = client.execute(request);

		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	// create the POST request
	public JsonObject postRequest(Config config, String url, JsonObject json)
			throws Exception, ParseException, IOException {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setConfig(httpRequestConfig);

		request.setHeader("X-ACCESS-TOKEN", config.apptoken);
		request.addHeader("content-type", "application/json");

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	// create the POST document url request
	public JsonObject postUrlRequest(Config config, String url, JsonObject json)
			throws Exception, ParseException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setConfig(httpRequestConfig);
		
		request.setHeader("X-ACCESS-TOKEN", config.apptoken);

 
		// MultipartEntity entity = new MultipartEntity();
		// MultipartEntity is deprecated -> see: http://stackoverflow.com/questions/19196292/the-type-multipartentity-is-deprecated#19196621
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		for (Map.Entry<String,JsonElement> entry : json.entrySet()) {
			
			if ("page_teaser_image".equals(entry.getKey())) {
				File img = new File(entry.getValue().getAsString());
				FileBody imageBody = new FileBody(img);
				builder.addPart("page_teaser_image", imageBody);
			} else {
				builder.addPart(entry.getKey(), new StringBody(entry.getValue().getAsString(), ContentType.TEXT_PLAIN));
			}
		}
		HttpEntity entity = builder.build();
	
		request.setEntity(entity);

		HttpResponse response = client.execute(request);
		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	// create the POST document file request
	public JsonObject postFileRequest(Config config, String url, JsonObject json)
			throws Exception, ParseException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(url);
		request.setConfig(httpRequestConfig);
		
		request.setHeader("X-ACCESS-TOKEN", config.apptoken);
		// MultipartEntity entity = new MultipartEntity();
		// MultipartEntity is deprecated -> see: http://stackoverflow.com/questions/19196292/the-type-multipartentity-is-deprecated#19196621

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		for (Map.Entry<String,JsonElement> entry : json.entrySet()) {
			
			if ("page_teaser_image".equals(entry.getKey())) {
				File img = new File(entry.getValue().getAsString());
				FileBody imageBody = new FileBody(img);
				builder.addPart("page_teaser_image", imageBody);
			}
			else if ("file".equals(entry.getKey())) {
				File file = new File(entry.getValue().getAsString());
				FileBody fileBody = new FileBody(file);
				builder.addPart("file", fileBody);
			}
			else {
				builder.addPart(entry.getKey(), new StringBody(entry.getValue().getAsString(), ContentType.TEXT_PLAIN));
			}
		}
		HttpEntity entity = builder.build();
		
		request.setEntity(entity);

		HttpResponse response = client.execute(request);
		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	// create the DELETE request
	public JsonObject deleteRequest(Config config, String url, String id)
			throws ClientProtocolException, IOException, Exception {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpDeleteWithBody request = new HttpDeleteWithBody(url);
		request.setConfig(httpRequestConfig);

		request.setHeader("X-ACCESS-TOKEN", config.apptoken);
		StringEntity inputId = new StringEntity("id=" + id);
		request.setEntity(inputId);

		HttpResponse response = client.execute(request);

		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	public JsonObject putRequest(Config config, String url, JsonObject json)
			throws ClientProtocolException, IOException, Exception {
		this.config = config;
		HttpClient client = HttpClientBuilder.create().build();
		HttpPut request = new HttpPut(url);
		request.setConfig(httpRequestConfig);

		request.setHeader("X-ACCESS-TOKEN", config.apptoken);
		request.addHeader("Content-Type", "application/json");

		StringEntity params = new StringEntity(json.toString());
		request.setEntity(params);

		HttpResponse response = client.execute(request);
		JsonObject myObject = sendResponse(response);

		return myObject;
	}

	private JsonObject sendResponse(HttpResponse response) throws IOException, Exception {
		String responseString = EntityUtils.toString(response.getEntity());
		
		JsonObject responseJson; 
		try {
			responseJson = (new JsonParser()).parse(
					responseString
					).getAsJsonObject();
		} catch (Exception ex) {
			if (responseString.contains("{")) {
				responseJson = (new JsonParser()).parse(
						responseString.substring(responseString.indexOf("{"))
						).getAsJsonObject();
			}
			else {
				throw (ex);
			}
		}
		responseCode = response.getStatusLine().getStatusCode();
		return responseJson;
	}
}
