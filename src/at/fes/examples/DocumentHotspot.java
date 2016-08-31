package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentHotspot {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");	
//		String[] body = {"document_id=55890265", "type=link", "page=1"};
//		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};
		String[] body = {"id=d3160bf4ZAykIx2l", "type=link"};
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};

		JSONObject json = new JSONObject(y.postDocumentHotspot(body, settings));
		
//		String[] params = {};
//		String returnFields[] = { "id" };
//		y.postDocumentHotspot();
//		y.putDocumentHotspot();		
//		y.deleteDocumentHotspot("35936979C40pnPVD");
	}

}
