package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class AccessTags {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		AccessTags at = new AccessTags();
//		System.out.println(at.getAccessTags());
	}
	
	private JSONObject getAccessTags() throws IOException, JSONException {
		String[] params = {"limit=2", "return_fields=id"};
		return y.getAccessTags(params);
	}

}
