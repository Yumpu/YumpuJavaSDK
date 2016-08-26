package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentHotspot {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id" };
		JSONObject json = new JSONObject(y.getDocumentHotspot("35936979C40pnPVD", params, returnFields));
		
//		y.postDocumentHotspot();
//		y.putDocumentHotspot();		
	}

}
