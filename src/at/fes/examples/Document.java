package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Document {

	/**
	 * @param args
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "url" };
		JSONObject json = new JSONObject(y.getDocument("55875934", params, returnFields));

//		String[] body = {"id=55875793","title=new title"};
//		String[] params = {};
//		String returnFields[] = { "url" };
//		y.getDocument("55875413", params, returnFields);
		
//		y.postDocumentUrl(body);
//		y.putDocument(body);
//		y.deleteDocument("10671");
	}

}
