package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Collections {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,sections"};
		y.getCollections(returnFields);
	}

}
