package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Embeds {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id"};
		y.getEmbeds("0", "2", "desc", returnFields);
	}
}
