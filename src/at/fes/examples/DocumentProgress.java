package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class DocumentProgress {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		DocumentProgress dp = new DocumentProgress();
//		System.out.println(dp.getDocumentProgress());
	}
	
	private JsonObject getDocumentProgress() throws IOException, Exception {
		String[] params = {"id=6f652-51552-0c0c7-02228-c7d21-45010-ad98c-9dbde"};
		return y.getDocumentProgress(params);
	}
}
