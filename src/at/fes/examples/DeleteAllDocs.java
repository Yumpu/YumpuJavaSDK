package at.fes.examples;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DeleteAllDocs {

	/**
	 * @param args
	 * @throws IOException
	 * @throws JSONException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		deleteAll(y);
	}

	private static void deleteAll(Yumpu y) throws IOException,
			JSONException {
		String[] params = { "limit=100" };
		String[] returnFields = { "id" };
		String res = y.getSubscriptions(params, returnFields).toString();
		JSONObject json = new JSONObject(res);
		JSONArray jarr = new JSONArray(json.get("subscriptions").toString());
		for (int i = 0; i < jarr.length(); i++) {
			JSONObject jnew = new JSONObject(jarr.get(i).toString());
			y.deleteSubscription(jnew.get("id").toString());
			System.out.println("delete " + jnew.get("id").toString());
		}
	}

}