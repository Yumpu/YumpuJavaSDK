package at.fes.examples;

import java.io.IOException;
import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class AccessTag {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		AccessTag at = new AccessTag();
//		System.out.println(at.postAccessTag());
//		System.out.println(at.putAccessTag());
//		System.out.println(at.getAccessTag());
//		System.out.println(at.deleteAccessTag());
	}

	private JsonObject getAccessTag() throws IOException, Exception {
		String[] params = { "id=Kr5nmzjFkobH7wV4" };
		return y.getAccessTag(params);
	}

	private JsonObject postAccessTag() throws IOException, Exception {
		String[] params = { "name=accesstag7", "description=accesstag7"};
		return y.postAccessTag(params);
	}

	private JsonObject putAccessTag() throws IOException, Exception {
		String[] params = { "id=Kr5nmzjFkobH7wV4", "name=accesstag",
				"description=accesstag7" };
		return y.putAccessTag(params);
	}
	
	private JsonObject deleteAccessTag() throws IOException, Exception {
		return y.deleteAccessTag("Kr5nmzjFkobH7wV4");
	}

}
