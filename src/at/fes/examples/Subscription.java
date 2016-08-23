package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Subscription {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
//		y.getSubscription("QOjiaWfWYWd7TBHE");
		y.postSubscription("subscr7", "subscr7", 62);
	}

}
