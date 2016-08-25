package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

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
		String[] body = {"id=55875793",
				 "title=newtitles"};
//		y.getDocument("55875413", params, returnFields);
//		 y.postDocumentUrl(body);
		 y.putDocument(body);
//		y.deleteDocument("10671");
	}

}
