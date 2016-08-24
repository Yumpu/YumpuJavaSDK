package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class SectionDocument {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents[] = {"55869707"};
		y.postSectionDocument("dpXYOxSR7Jeqw6f4_SPbrTa3UXwz5DjY6", documents);
	}

}
