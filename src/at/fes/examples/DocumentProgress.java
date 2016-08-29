package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentProgress {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = {};
		JSONObject json = new JSONObject(y.getDocumentProgress(
				"b0b39-298a0-8a3b3-6cad5-4e817-ecce9-628f3-e2980", params,
				returnFields));
	}

}
