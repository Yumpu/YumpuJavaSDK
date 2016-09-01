package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Embeds {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		Embeds e = new Embeds();
//		System.out.println(e.getEmbeds());
	}
	
	private JSONObject getEmbeds() throws IOException, JSONException {
		String[] params = {"limit=11"};
		return y.getEmbeds(params);
	}
}
