package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Documents {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Documents d = new Documents();
//		System.out.println(d.getDocuments());
	}
	
	private JsonObject getDocuments() throws IOException, Exception {
		String[] params = {"limit=10"};
		return y.getDocuments(params);
	}

}
