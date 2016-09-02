package at.fes.examples;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Document {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Document d = new Document();
//		System.out.println(d.postDocumentFile());
		System.out.println(d.postDocumentURL());
//		System.out.println(d.getDocument());
//		System.out.println(d.putDocument());
//		System.out.println(d.deleteDocument());
	}

	private JSONObject getDocument() throws IOException, JSONException {
		String[] params = { "id=55898008" };
		return y.getDocument(params);
	}

	private JSONObject postDocumentFile() throws IOException, JSONException {
		String[] params = {
				"file=src\\at\\fes\\examples\\media\\yumpu.pdf",
				"title=file from tester local"};
		return y.postDocumentFile(params);
	}
	
	private JSONObject postDocumentURL() throws IOException, JSONException {
		String[] params = {"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"title=Das ist ein file mit json"};
		return y.postDocumentUrl(params);
	}
	
	private JSONObject putDocument() throws IOException, JSONException {
		String[] params = {"id=55898008", "title=neuer titel"};
		return y.putDocument(params);
	}

	private JSONObject deleteDocument() throws IOException, JSONException {
		String id = "55898008";
		return y.deleteDocument(id);
	}
}
