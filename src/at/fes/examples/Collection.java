package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Collection {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		JSONObject json = new JSONObject(y.putCollection("49cvPY1uKERtNnyZ", "new name"));
		
//		String[] params = {};
//		String returnFields[] = { "id,name" };
//		y.getCollection("9RDnBITHpkxwc3s7", params, returnFields);
//		y.postCollection("new one");
//		y.putCollection("49cvPY1uKERtNnyZ", "neydaswer");
	}
}
