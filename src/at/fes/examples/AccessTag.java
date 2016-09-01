package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class AccessTag {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		 String[] body = {
				  "name=accesstag7",
				  "description=accesstag7",
				  "default=y",
				  "iap=y",
				  "kiosks=webkiosk_25"
				  };
//		JSONObject json = new JSONObject(y.deleteAccessTag("BVinDRHKNhl1ev56"));
		
//		String[] params = {};
//		String[] returnFields = { "id,name" };
//		String[] body = {"name=jhjklk", "description=hallolo", "default=y"};
//		y.getAccessTag("pWDAKmcyUHR2o04T", params, returnFields);
//		y.postAccessTag(body);
//		y.putAccessTag("BVinDRHKNhl1ev56", "qweqw");
//		y.deleteAccessTag("BVinDRHKNhl1ev56");
	}

}
