package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class AccessTag {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
//		y.getAccessTag("MiskUIrpzluycDCn");
//		y.postAccessTag("name", "desc");
		y.putAccessTag("hJNK19CGXWjlvy2z", "neuertag");
	}

}
