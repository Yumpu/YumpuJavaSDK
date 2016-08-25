package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Subscription {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id,name" };
		String[] body = {"itc_product_id=bscsadr7", "name=subscr7", "duration=62", "description=213123"};
//		y.getSubscription("4INKOAYuMJzHTogW", params, returnFields);
		y.postSubscription( body);
		// y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", "62");
	}

}
