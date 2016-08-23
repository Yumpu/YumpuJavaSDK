package at.fes.service;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tester {

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException  {
		JSONObject json = new JSONObject();
		json.put("document_id", 55865447);
		json.put("page", "1");
		json.put("type", "link");
		JSONObject settings = new JSONObject();
		settings.put("x", 100);
		settings.put("y", 100);
		settings.put("w", 50);
		settings.put("h", 50);
		settings.put("name", "google.com");
		settings.put("tooltip", "google.com");
		settings.put("link", "www.google.com");
		json.put("settings", settings);
		
		System.out.println(json);
		postRequest(json);
		
		
	}
	
	public static void postRequest(JSONObject json){
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost request = new HttpPost("http://api.yumpu.com/2.0/document/hotspot.json");
			request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
			StringEntity params = new StringEntity(json.toString());
			request.setEntity(params);
			request.addHeader("content-type", "application/json");
			System.out.println(json.toString());
			HttpResponse response = httpClient.execute(request);

			System.out.println(response);
		} catch (Exception ex) {
			System.out.println("error" + ex);
		}
	}

}
