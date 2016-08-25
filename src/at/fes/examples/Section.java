package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Section {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id,name" };
		String[]  body = {"id=SyQPxIYetzpAhOgK_e1a8grt3i0FPMklT", "name=sasdfd", "description=aösldasdöjd"};
//		y.getSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", params, returnFields);
//		y.postSection(body);
		y.putSection(body);
	}

}
