package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class DocumentHotspot {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		DocumentHotspot dh = new DocumentHotspot();
//		System.out.println(dh.postDocumentHotspot());
//		System.out.println(dh.getDocumentHotspot());
//		System.out.println(dh.putDocumentHotspot());
//		System.out.println(dh.deleteDocumentHotspot());
	}

	private JsonObject postDocumentHotspot() throws IOException, Exception {
		String[] params = {"document_id=55898015", "type=link", "page=1"};
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};
		return y.postDocumentHotspot(params, settings);
	}

	private JsonObject getDocumentHotspot() throws IOException, Exception {
		String[] params = {"id=b762266fEuN7Bb5L", "return_fields=create_date,page,id"};
		return y.getDocumentHotspot(params);
	}

	private JsonObject putDocumentHotspot() throws IOException, Exception {
		String[] params = {"id=b762266fEuN7Bb5L", "type=link"};
		String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google", "tooltip=google.com", "link=http://www.google.com"};
		return y.putDocumentHotspot(params, settings);
	}

	private JsonObject deleteDocumentHotspot() throws IOException, Exception {
		String id = "b762266fEuN7Bb5L";
		return y.deleteDocumentHotspot(id);
	}
}
