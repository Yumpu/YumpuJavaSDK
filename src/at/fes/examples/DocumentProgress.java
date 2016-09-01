package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class DocumentProgress {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		DocumentProgress dp = new DocumentProgress();
//		System.out.println(dp.getDocumentProgress());
	}
	
	private JSONObject getDocumentProgress() throws IOException, JSONException {
		String[] params = {"id=6f652-51552-0c0c7-02228-c7d21-45010-ad98c-9dbde"};
		return y.getDocumentProgress(params);
	}
}
