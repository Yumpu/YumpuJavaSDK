package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Subscription {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Subscription s = new Subscription();
//		System.out.println(s.postSubscription());
//		System.out.println(s.getSubscription());
//		System.out.println(s.putSubscription());
//		System.out.println(s.deleteSubscription());
	}
	
	private JsonObject postSubscription() throws IOException, Exception {
		String[] params = {"itc_product_id=newone", "name=subscr7", "duration=62", "description=213123"};
		return y.postSubscription(params);
	}
	
	private JsonObject getSubscription() throws IOException, Exception {
		String[] params = {"id=Km5rTYdjRl4OLuBb", "return_fields=itc_product_id"};
		return y.getSubscription(params);
	}
	
	private JsonObject putSubscription() throws IOException, Exception {
		String[] params = {"id=Km5rTYdjRl4OLuBb", "itc_product_id=newone", "name=newname", "duration=62"};
		return y.putSubscription(params);
	}
	
	private JsonObject deleteSubscription() throws IOException, Exception {
		String id = "Km5rTYdjRl4OLuBb";
		return y.deleteSubscription(id);
	}
}
