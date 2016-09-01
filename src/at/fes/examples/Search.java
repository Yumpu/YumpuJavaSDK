package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Search {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		Search s = new Search();
//		System.out.println(s.getSearch());
	}
	
	private JSONObject getSearch() throws IOException, JSONException {
		String[] params = {"q=da"};
		return y.search(params);
	}
}
