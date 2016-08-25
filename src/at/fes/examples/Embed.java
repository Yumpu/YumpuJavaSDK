package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Embed {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id" };
		String[] body = {"document_id=55875854", "type=1", "start_page=2", "width=619"};
//		y.getEmbed("rU3W05fqP9kZGnYw", params, returnFields);
		 y.postEmbed(body);
		// y.putEmbed("lrQUW1EPLz5ey8bO", "55869263", "1");
	}

}
