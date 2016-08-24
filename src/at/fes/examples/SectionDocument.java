package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class SectionDocument {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents = "55869256";
		y.postSectionDocument("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", documents);
	}

}
