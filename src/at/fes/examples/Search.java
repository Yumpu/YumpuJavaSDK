package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Search {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Search s = new Search();
//		System.out.println(s.getSearch());
	}
	
	private JsonObject getSearch() throws IOException, Exception {
		String[] params = {"q=da"};
		return y.search(params);
	}
}
