package at.fes.examples;

import java.io.IOException;
import java.util.HashMap;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Document {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Document d = new Document();
//		System.out.println(d.postDocumentFile());
		System.out.println(d.postDocumentURL());
//		System.out.println(d.getDocument());
//		System.out.println(d.putDocument());
//		System.out.println(d.deleteDocument());
	}

	private JsonObject getDocument() throws IOException, Exception {
		String[] params = { "id=55898008" };
		return y.getDocument(params);
	}

	private JsonObject postDocumentFile() throws IOException, Exception {
		String[] params = {
				"file=src\\at\\fes\\examples\\media\\yumpu.pdf",
				"title=file from tester local"};
		return y.postDocumentFile(params);
	}
	
	private JsonObject postDocumentURL() throws IOException, Exception {
		String[] params = {"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"title=Das ist ein file mit json"};
		return y.postDocumentUrl(params);
	}
	
	private JsonObject putDocument() throws IOException, Exception {
		String[] params = {"id=55898008", "title=neuer titel"};
		return y.putDocument(params);
	}

	private JsonObject deleteDocument() throws IOException, Exception {
		String id = "55898008";
		return y.deleteDocument(id);
	}
}
