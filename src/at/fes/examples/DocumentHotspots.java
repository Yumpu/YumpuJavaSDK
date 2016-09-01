package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentHotspots {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		DocumentHotspots dh = new DocumentHotspots();
		System.out.println(dh.getDocumentHotspots());
	}
	
	private JSONObject getDocumentHotspots() throws IOException, JSONException {
		String[] params = {"id=55898015", "limit=4"};
		return y.getDocumentHotspots(params);
	}
}
