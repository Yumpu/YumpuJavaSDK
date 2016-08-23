package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Subscription {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
//		y.getSubscription("4INKOAYuMJzHTogW");
//		y.postSubscription("subscr7", "subscr7", 62);
		y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", 62);
	}

}
