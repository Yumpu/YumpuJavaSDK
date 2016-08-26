package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Collection {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		JSONObject json = new JSONObject(y.deleteCollection("49cvPY1uKERtNnyZ"));
		
//		String[] params = {};
//		String returnFields[] = { "id,name" };
//		y.getCollection("9RDnBITHpkxwc3s7", params, returnFields);
//		y.postCollection("new one");
//		y.putCollection("49cvPY1uKERtNnyZ", "neydaswer");
//		y.deleteCollection("49cvPY1uKERtNnyZ");
	}
}
