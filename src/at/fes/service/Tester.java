package at.fes.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

public class Tester {
	private Config config = new Config();
	public int responseCode;
	private String method = "GET";
	private RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws ClientProtocolException, IOException, JSONException {
		Tester t = new Tester();
	}

}
