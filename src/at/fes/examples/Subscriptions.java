package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Subscriptions {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Subscriptions s = new Subscriptions();
//		System.out.println(s.getSubscriptions());
	}

	private JsonObject getSubscriptions() throws IOException, Exception {
		String[] params = {"return_fields=id,name"};
		return y.getSubscriptions(params);
	}
}
