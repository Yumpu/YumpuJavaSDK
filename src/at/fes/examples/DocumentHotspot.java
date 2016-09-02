package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentHotspot {
	Yumpu y = new Yumpu("your access token");	

	public static void main(String[] args) throws IOException, JSONException {
		DocumentHotspot dh = new DocumentHotspot();
//		System.out.println(dh.postDocumentHotspot());
//		System.out.println(dh.getDocumentHotspot());
//		System.out.println(dh.putDocumentHotspot());
//		System.out.println(dh.deleteDocumentHotspot());
	}
	
	private JSONObject postDocumentHotspot() throws IOException, JSONException {
		String[] params = {"document_id=55898015", "type=link", "page=1"};
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};
		return y.postDocumentHotspot(params, settings);
	}
	
	private JSONObject getDocumentHotspot() throws IOException, JSONException {
		String[] params = {"id=b762266fEuN7Bb5L", "return_fields=create_date,page,id"};
		return y.getDocumentHotspot(params);
	}
	
	private JSONObject putDocumentHotspot() throws IOException, JSONException {
		String[] params = {"id=b762266fEuN7Bb5L", "type=link"};
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};
		return y.putDocumentHotspot(params, settings);
	}
	
	private JSONObject deleteDocumentHotspot() throws IOException, JSONException {
		String id = "b762266fEuN7Bb5L";
		return y.deleteDocumentHotspot(id);
	}
}
