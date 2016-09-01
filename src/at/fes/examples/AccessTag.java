package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class AccessTag {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		AccessTag at = new AccessTag();
//		System.out.println(at.postAccessTag());
//		System.out.println(at.putAccessTag());
//		System.out.println(at.getAccessTag());
//		System.out.println(at.deleteAccessTag());
	}

	private JSONObject getAccessTag() throws IOException, JSONException {
		String[] params = { "id=Kr5nmzjFkobH7wV4" };
		return y.getAccessTag(params);
	}

	private JSONObject postAccessTag() throws IOException, JSONException {
		String[] params = { "name=accesstag7", "description=accesstag7"};
		return y.postAccessTag(params);
	}

	private JSONObject putAccessTag() throws IOException, JSONException {
		String[] params = { "id=Kr5nmzjFkobH7wV4", "name=accesstag",
				"description=accesstag7" };
		return y.putAccessTag(params);
	}
	
	private JSONObject deleteAccessTag() throws IOException, JSONException {
		return y.deleteAccessTag("Kr5nmzjFkobH7wV4");
	}

}
