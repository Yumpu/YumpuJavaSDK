package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import at.fes.service.Yumpu;

public class DeleteAllDocs {
	private int cnt;
	private static Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, Exception {
		DeleteAllDocs dad = new DeleteAllDocs();
		dad.deleteAll(y);
	}

	private void deleteAll(Yumpu y) throws IOException,
			Exception {
		String[] params = { "limit=100", "return_fields=id" };
		String res = y.getDocuments(params).toString();
		JsonObject json = (new JsonParser()).parse(res).getAsJsonObject();
		JsonArray jsonArray = (json.has("documents"))?json.getAsJsonArray("documents") : new JsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonNew = jsonArray.get(i).getAsJsonObject();
			y.deleteDocument(jsonNew.get("id").toString());
			System.out.println("delete " + jsonNew.get("id").toString());
			cnt++;
		}
		System.out.println("deletet " + cnt + " Documents");
	}

}
