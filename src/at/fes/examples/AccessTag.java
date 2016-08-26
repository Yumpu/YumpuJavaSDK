package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class AccessTag {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = {"id=BVinDRHKNhl1ev56", "name=newTagName"};
		JSONObject json = new JSONObject(y.putAccessTag(body));
		
//		String[] params = {};
//		String[] returnFields = { "id,name" };
//		String[] body = {"name=jhjklk", "description=hallolo", "default=y"};
//		y.getAccessTag("pWDAKmcyUHR2o04T", params, returnFields);
//		y.postAccessTag(body);
//		y.putAccessTag("BVinDRHKNhl1ev56", "qweqw");
	}

}
