package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Collection {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		Collection c = new Collection();
//		System.out.println(c.postCollection());
//		System.out.println(c.getCollection());
//		System.out.println(c.putCollection());
//		System.out.println(c.deleteCollection());
	}

	private JSONObject getCollection() throws IOException, JSONException {
		String[] params = { "id=ZyoDkM2sPzaJnVdq" };
		return y.getCollection(params);
	}

	private JSONObject postCollection() throws IOException, JSONException {
		String[] params = { "name=newcoll3" };
		return y.postCollection(params);
	}

	private JSONObject putCollection() throws IOException, JSONException {
		String[] params = { "id=ZyoDkM2sPzaJnVdq", "name=putname" };
		return y.putCollection(params);
	}

	private JSONObject deleteCollection() throws IOException, JSONException {
		String id = "ZyoDkM2sPzaJnVdq";
		return y.deleteCollection(id);
	}
}
