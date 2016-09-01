package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Subscriptions {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		Subscriptions s = new Subscriptions();
//		System.out.println(s.getSubscriptions());
	}

	private JSONObject getSubscriptions() throws IOException, JSONException {
		String[] params = {"return_fields=id,name"};
		return y.getSubscriptions(params);
	}
}
