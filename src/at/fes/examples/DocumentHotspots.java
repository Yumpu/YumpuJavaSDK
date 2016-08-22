package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class DocumentHotspots {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"page"};
		y.getDocumentHotspots("55847151", returnFields);
	}

}
