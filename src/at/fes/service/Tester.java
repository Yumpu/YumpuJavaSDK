package at.fes.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class Tester {
	int responseCode;
	public YumpuFunctions yf = new YumpuFunctions();
	public Config config = new Config("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
	public RequestMethods rm = new RequestMethods();

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		
	}
}
