package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Subscription {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Subscription s = new Subscription();
//		System.out.println(s.postSubscription());
//		System.out.println(s.getSubscription());
//		System.out.println(s.putSubscription());
//		System.out.println(s.deleteSubscription());
	}
	
	private JSONObject postSubscription() throws IOException, JSONException {
		String[] params = {"itc_product_id=newone", "name=subscr7", "duration=62", "description=213123"};
		return y.postSubscription(params);
	}
	
	private JSONObject getSubscription() throws IOException, JSONException {
		String[] params = {"id=Km5rTYdjRl4OLuBb", "return_fields=itc_product_id"};
		return y.getSubscription(params);
	}
	
	private JSONObject putSubscription() throws IOException, JSONException {
		String[] params = {"id=Km5rTYdjRl4OLuBb", "itc_product_id=newone", "name=newname", "duration=62"};
		return y.putSubscription(params);
	}
	
	private JSONObject deleteSubscription() throws IOException, JSONException {
		String id = "Km5rTYdjRl4OLuBb";
		return y.deleteSubscription(id);
	}
}
