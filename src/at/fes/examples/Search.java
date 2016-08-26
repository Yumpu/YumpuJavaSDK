package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Search {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		JSONObject json = new JSONObject(y.search("q=sports&in=title,description&views=1000-5000&language=en"));
	}
}
