package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Document {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
//		String returnFields[] = {"url"};
//		y.getDocument("55847151", returnFields);
//		y.postDocumentUrl("http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf", "Diplomarbeit asdvon Stefan");
//		y.putDocument("new documentname", 55865398);
		y.deleteDocument(10663);
	}

}
