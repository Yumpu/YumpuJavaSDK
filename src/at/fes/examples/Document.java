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
//		String returnFields[] = {"url"};
//		y.getDocument("55825000", returnFields);
		y.postUrl();
	}

}
