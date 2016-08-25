package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Documents {

	/**
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {"offset=1", "limit=1"};
		String[] returnFields = { "url" };
		JSONObject json = new JSONObject(y.getDocuments(params, returnFields));
	}

}
