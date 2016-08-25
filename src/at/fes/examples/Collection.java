package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Collection {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getDocumentProgress("9RDnBITHpkxwc3s7", params, returnFields);
//		y.postCollection("new one");
//		y.putCollection("49cvPY1uKERtNnyZ", "neydaswer");
	}
}
