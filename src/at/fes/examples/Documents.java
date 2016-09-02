package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Documents {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Documents d = new Documents();
//		System.out.println(d.getDocuments());
	}
	
	private JSONObject getDocuments() throws IOException, JSONException {
		String[] params = {"limit=10"};
		return y.getDocuments(params);
	}

}
