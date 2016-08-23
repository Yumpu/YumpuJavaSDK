package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class SectionDocument {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents[] = {"55865447"};
		y.postSectionDocument("SyQPxIYetzpAhOgK_9r58Udahn0zFjQ23", documents);
	}

}
