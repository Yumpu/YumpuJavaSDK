package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class DocumentHotspots {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		DocumentHotspots dh = new DocumentHotspots();
		System.out.println(dh.getDocumentHotspots());
	}
	
	private JsonObject getDocumentHotspots() throws IOException, Exception {
		String[] params = {"id=55898015", "limit=4"};
		return y.getDocumentHotspots(params);
	}
}
