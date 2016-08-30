package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentHotspot {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");	
		JSONObject json = new JSONObject(y.postDocumentHotspot());
		
//		String[] params = {};
//		String returnFields[] = { "id" };
//		y.postDocumentHotspot();
//		y.putDocumentHotspot();		
//		y.deleteDocumentHotspot("35936979C40pnPVD");
	}

}
