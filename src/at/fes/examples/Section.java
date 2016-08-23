package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Section {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,name"};
		y.getSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", returnFields);
//		y.postSection("SyQPxIYetzpAhOgK", "section");
	}

}
