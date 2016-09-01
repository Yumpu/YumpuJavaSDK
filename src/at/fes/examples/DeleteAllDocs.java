package at.fes.examples;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DeleteAllDocs {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		deleteAll(y);
	}

	private static void deleteAll(Yumpu y) throws IOException,
			JSONException {
		String[] params = { "limit=100", "return_fields=id" };
		String res = y.getDocuments(params).toString();
		JSONObject json = new JSONObject(res);
		JSONArray jarr = new JSONArray(json.get("documents").toString());
		for (int i = 0; i < jarr.length(); i++) {
			JSONObject jnew = new JSONObject(jarr.get(i).toString());
			y.deleteDocument(jnew.get("id").toString());
			System.out.println("delete " + jnew.get("id").toString());
		}
	}

}
