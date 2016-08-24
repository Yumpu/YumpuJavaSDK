package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Section {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,name"};
//		y.getSection("9RDnBITHpkxwc3s7_BNTizyI4S9mEdkDZ", returnFields);
//		y.postSection("9RDnBITHpkxwc3s7", "section");
		y.putSection("9RDnBITHpkxwc3s7_BNTizyI4S9mEdkDZ", "new ahsd");
	}

}
