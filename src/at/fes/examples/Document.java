package at.fes.examples;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Document {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "url" };
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("url", "http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf");
		map.put("title", "Das istasdsd");
		map.put("description", "das ist genial");
		map.put("category", "1");
		map.put("page_teaser_page_range", "1-2");
		map.put("page_teaser_url", "http://www.yumpu.com/en");

		String imgPath = "src\\at\\fes\\examples\\media\\yumpu.png";
//		y.postDocumentUrl(imgPath, map);
		delete(y);
		
//		JSONObject json = new JSONObject(y.deleteDocument("55886141"));

//		String[] body = {"id=55875793","title=new title"};
//		String[] params = {};
//		String returnFields[] = { "url" };
//		y.getDocument("55875413", params, returnFields);
		
//		y.postDocumentUrl(body);
//		y.putDocument(body);
//		y.deleteDocument("10671");
	}

	private static void delete(Yumpu y) throws IOException, JSONException {
		y.deleteDocument("55893958");
		y.deleteDocument("55893957");
	}

}
