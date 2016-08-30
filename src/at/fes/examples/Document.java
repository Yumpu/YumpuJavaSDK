package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Document {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "url" };
		JSONObject json = new JSONObject(y.deleteDocument("55886141"));

//		String[] body = {"id=55875793","title=new title"};
//		String[] params = {};
//		String returnFields[] = { "url" };
//		y.getDocument("55875413", params, returnFields);
		
//		y.postDocumentUrl(body);
//		y.putDocument(body);
//		y.deleteDocument("10671");
	}

}
