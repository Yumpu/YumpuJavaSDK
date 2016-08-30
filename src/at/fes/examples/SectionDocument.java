package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class SectionDocument {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String documents = "55887227";
		y.deleteSectionDocument("8ZM7alfR1UGpdwgW_aAv3jOEzDRXsCwl4", documents);
		
//		y.deleteSectionDocument("SyQPxIYetzpAhOgK_Vc7jXD3BEeqPto0S", "55875794");
//		y.postSectionDocument("SyQPxIYetzpAhOgK_Vc7jXD3BEeqPto0S", documents);
	}

}
