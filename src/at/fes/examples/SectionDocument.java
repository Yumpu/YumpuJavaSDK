package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class SectionDocument {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		SectionDocument sd = new SectionDocument();
//		System.out.println(sd.postSectionDocument());
		System.out.println(sd.deleteSectionDocument());
	}

	private JsonObject postSectionDocument() throws IOException, Exception {
		String[] params = {"id=46gGMFOTEVf3SamD_r5y8NmRMA6CZ20XQ", "documents=55898128"};
		return y.postSectionDocument(params);
	}
	
	private JsonObject deleteSectionDocument() throws IOException, Exception {
		String id = "46gGMFOTEVf3SamD_r5y8NmRMA6CZ20XQ";
		String documents = "55898128";
		return y.deleteSectionDocument(id, documents);
	}
}
