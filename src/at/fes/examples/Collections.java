package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Collections {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Collections c = new Collections();
//		System.out.println(c.getCollection());
	}

	private JSONObject getCollection() throws IOException, JSONException {
		String[] params = {"limit=1"};
		return y.getCollections(params);
	}

}
