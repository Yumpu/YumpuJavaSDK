package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class AccessTags {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
//		y.getAccessTags(params, returnFields);
	}

}
