package at.fes.tests;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class DeleteAllDocs {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {"offset=1", "limit=1"};
		String[] returnFields = { "id" };
		String res = y.getDocuments(params, returnFields).toString();
		System.out.println(res);
	}

}
