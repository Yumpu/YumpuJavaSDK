package at.fes.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
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
		t.deleteDocument("55886810");
	}

	public JSONObject deleteDocument(String id) throws IOException,
			JSONException {
		String url = "http://api.yumpu.com/2.0/document.json";

		return optionsDelete(url);
	}

	public JSONObject deleteRequest(Config config, String url)
			throws ClientProtocolException, IOException, JSONException {
		this.config = config;

        CloseableHttpClient httpclient = HttpClients.createDefault();
        
        url = "http://api.yumpu.com/2.0/document.json";
 
        HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
        httpDelete.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
        StringEntity input = new StringEntity("id=55886158");
         
        httpDelete.setEntity(input);  
 
        System.out.println("****REQUEST***************************************");
        System.out.println(url);
        Header requestHeaders[] = httpDelete.getAllHeaders();
        for (Header h : requestHeaders) {
            System.out.println(h.getName() + ": " + h.getValue());
        }
 
        CloseableHttpResponse response = httpclient.execute(httpDelete);
 
        System.out.println("****RESPONSE***************************************");
        System.out.println("----status:---------------------");
        System.out.println(response.getStatusLine());
        System.out.println("----header:---------------------");
        Header responseHeaders[] = response.getAllHeaders();
        for (Header h : responseHeaders) {
            System.out.println(h.getName() + ": " + h.getValue());
        }
        System.out.println("----content:---------------------");
        System.out.println(EntityUtils.toString(response.getEntity()));
		JSONObject myObject = null;
		return myObject;
	}

	private JSONObject sendResponse(HttpResponse response) throws IOException,
			JSONException {
		String jsonString = EntityUtils.toString(response.getEntity());
		JSONObject myObject = new JSONObject(jsonString);
		responseCode = response.getStatusLine().getStatusCode();
		return myObject;
	}

	private JSONObject optionsDelete(String url) throws IOException,
			JSONException {
		JSONObject jo = deleteRequest(config, url);
		return jo;
	}

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
}
