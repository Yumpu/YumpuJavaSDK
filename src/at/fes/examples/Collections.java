package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Collections {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Collections c = new Collections();
//		System.out.println(c.getCollection());
	}

	private JsonObject getCollection() throws IOException, Exception {
		String[] params = {"limit=1"};
		return y.getCollections(params);
	}

}
