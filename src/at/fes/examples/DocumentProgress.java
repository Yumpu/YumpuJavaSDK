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
				"0ce9d-d337a-71cc9-dd57b-f4bfb-0386a-aec35-5836c", params,
				returnFields));
	}

}
