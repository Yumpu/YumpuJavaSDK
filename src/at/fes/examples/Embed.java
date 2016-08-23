package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Embed {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
//		y.getEmbed("rU3W05fqP9kZGnYw");
		y.postEmbed(55865447, 2);
	}

}
