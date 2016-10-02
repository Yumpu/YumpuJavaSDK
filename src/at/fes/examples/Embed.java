package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Embed {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Embed e = new Embed();
//		System.out.println(e.postEmbed());
//		System.out.println(e.getEmbed());
//		System.out.println(e.putEmbed());
//		System.out.println(e.deleteEmbed());
	}
	
	private JsonObject postEmbed() throws IOException, Exception {
		String[] params = {"document_id=55898128", "type=1", "start_page=2"};
		return y.postEmbed(params);
	}
	
	private JsonObject getEmbed() throws IOException, Exception {
		String[] params = {"id=kEtNKVp5xbmRjwze"};
		return y.getEmbed(params);
	}
	
	private JsonObject putEmbed() throws IOException, Exception {
		String[] params = {"id=kEtNKVp5xbmRjwze", "document_id=55898128", "type=1"};
		return y.putEmbed(params);
	}
	
	private JsonObject deleteEmbed() throws IOException, Exception {
		String id = "kEtNKVp5xbmRjwze";
		return y.deleteEmbed(id);
	}
}
