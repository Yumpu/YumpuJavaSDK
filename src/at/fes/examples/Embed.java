package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Embed {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Embed e = new Embed();
//		System.out.println(e.postEmbed());
//		System.out.println(e.getEmbed());
//		System.out.println(e.putEmbed());
//		System.out.println(e.deleteEmbed());
	}
	
	private JSONObject postEmbed() throws IOException, JSONException {
		String[] params = {"document_id=55898128", "type=1", "start_page=2"};
		return y.postEmbed(params);
	}
	
	private JSONObject getEmbed() throws IOException, JSONException {
		String[] params = {"id=kEtNKVp5xbmRjwze"};
		return y.getEmbed(params);
	}
	
	private JSONObject putEmbed() throws IOException, JSONException {
		String[] params = {"id=kEtNKVp5xbmRjwze", "document_id=55898128", "type=1"};
		return y.putEmbed(params);
	}
	
	private JSONObject deleteEmbed() throws IOException, JSONException {
		String id = "kEtNKVp5xbmRjwze";
		return y.deleteEmbed(id);
	}
}
