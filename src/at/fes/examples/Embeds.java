package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Embeds {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Embeds e = new Embeds();
//		System.out.println(e.getEmbeds());
	}
	
	private JsonObject getEmbeds() throws IOException, Exception {
		String[] params = {"limit=11"};
		return y.getEmbeds(params);
	}
}
