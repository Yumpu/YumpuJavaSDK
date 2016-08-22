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

public class Tester {

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException  {
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost post = new HttpPost("http://api.yumpu.com/2.0/document/file.json");
//		post.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
//		post.setHeader("content-type", "application/json");
//		File file = new File("C:\"Users\"stefan.feurstein.ADROM\"Downloads\"DA.pdf");
//		JSONObject json = new JSONObject();
//		json.put("file", file);
//		json.put("title", "asdsada");
//		
//		System.out.println(json);
//
//		StringEntity params = new StringEntity(json.toString());
//		
//		post.setEntity(params);
//
//		HttpResponse response = client.execute(post);
//		System.out.println(response);
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost("http://api.yumpu.com/2.0/document/file.json");
		httpPost.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

		httpPost.setHeader("Content-Type", "multipart/form-data");
        File payload = new File("C:\\Users\\stefan.feurstein.ADROM\\Downloads\\DA.pdf");

        HttpEntity entity = MultipartEntityBuilder.create()
        		.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
        		.addPart("file", new FileBody(payload))
        		.addPart("title", new StringBody("afhasfh"))
        		.build();
        
        System.out.println(entity.toString());
        httpPost.setEntity(entity);
        HttpResponse  response = client.execute(httpPost);
        System.out.println(response);
	}

}
