package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Members {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		Members m = new Members();
//		System.out.println(m.getMembers());
	}

	private JSONObject getMembers() throws IOException, JSONException {
		String[] params = {"limit=3"};
		return y.getMembers(params);
	}
}
