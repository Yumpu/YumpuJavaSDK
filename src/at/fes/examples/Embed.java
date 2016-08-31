package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Embed {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"id=VNEqkn2idMpc1COL", "document_id=55893346", "type=2"};
		y.putEmbed(body);

//		String[] body = {"document_id=55875854", "type=1", "start_page=2", "width=619"};
//		String[] params = {};
//		String[] returnFields = { "id" };
//		y.getEmbed("rU3W05fqP9kZGnYw", params, returnFields);
//		y.postEmbed(body);
//		y.putEmbed("lrQUW1EPLz5ey8bO", "55869263", "1");
//		y.deleteEmbed("lrQUW1EPLz5ey8bO");
	}

}
