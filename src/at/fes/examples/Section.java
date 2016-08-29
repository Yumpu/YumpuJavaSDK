package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Section {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[]  body = {"id=iX0uAxwlhKynP1pq_Yi5uT16akbf4oXBV", "name=sasdfd", "description=aösldasdöjd"};
		JSONObject json = new JSONObject(y.putSection(body));
		

//		String[]  body = {"id=SyQPxIYetzpAhOgK_e1a8grt3i0FPMklT", "name=sasdfd", "description=aösldasdöjd"};
//		String[] params = {};
//		String returnFields[] = { "id,name" };
//		y.getSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", params, returnFields)
//		y.postSection(body);
//		y.putSection(body);
//		y.deleteSection("SyQPxIYetzpAhOgK_e1a8grt3i0FPMklT");
	}

}
