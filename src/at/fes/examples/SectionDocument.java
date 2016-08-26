package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class SectionDocument {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents = "55875794,55875934";
		JSONObject json = new JSONObject(y.postSectionDocument("SyQPxIYetzpAhOgK_Vc7jXD3BEeqPto0S", documents));
	}

}
