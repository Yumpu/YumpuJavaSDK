package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentCategories {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("your access token");
		System.out.println(y.getCategories());
	}

}
