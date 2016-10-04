package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class AccessTags {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		AccessTags at = new AccessTags();
//		System.out.println(at.getAccessTags());
	}
	
	private JsonObject getAccessTags() throws IOException, Exception {
		String[] params = {"limit=2", "return_fields=id"};
		return y.getAccessTags(params);
	}

}
