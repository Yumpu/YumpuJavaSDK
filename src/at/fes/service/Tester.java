package at.fes.service;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.commons.httpclient.HttpClient; 
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.httpclient.methods.multipart.Part; 
import org.apache.commons.httpclient.methods.multipart.FilePart; 
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.methods.PostMethod;

public class Tester {
	private String boundary;
	private static final String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream outputStream;
	private PrintWriter writer;

	public static void main(String[] args) throws IOException, ParseException, JSONException {
		Tester t = new Tester();
		t.postRequest("http://api.yumpu.com/2.0/document/file.json");
	}

	public JSONObject postRequest(String url)
			throws JSONException, ParseException, IOException {
		HttpClient client = new HttpClient(); 
		PostMethod request = new PostMethod(url);
		
		request.setRequestHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		
		File targetFile = new File("C:\\Users\\stefan.feurstein\\Downloads\\DA.pdf");
		Part[] parts = { new FilePart("file", targetFile), new StringPart("title", "es geht bei stefan") };
		
		request.setRequestEntity(new MultipartRequestEntity(parts, request.getParams()));

		client.executeMethod(request); 
		String response = request.getResponseBodyAsString();
		JSONObject myObject = new JSONObject(response);
		int responseCode = client.executeMethod(request);

		return myObject;
	}
}
