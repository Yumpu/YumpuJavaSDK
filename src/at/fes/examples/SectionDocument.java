package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class SectionDocument {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		SectionDocument sd = new SectionDocument();
//		System.out.println(sd.postSectionDocument());
		System.out.println(sd.deleteSectionDocument());
	}

	private JSONObject postSectionDocument() throws IOException, JSONException {
		String[] params = {"id=46gGMFOTEVf3SamD_r5y8NmRMA6CZ20XQ", "documents=55898128"};
		return y.postSectionDocument(params);
	}
	
	private JSONObject deleteSectionDocument() throws IOException, JSONException {
		String id = "46gGMFOTEVf3SamD_r5y8NmRMA6CZ20XQ";
		String documents = "55898128";
		return y.deleteSectionDocument(id, documents);
	}
}
