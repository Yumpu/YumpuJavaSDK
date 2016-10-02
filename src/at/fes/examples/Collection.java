package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Collection {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Collection c = new Collection();
//		System.out.println(c.postCollection());
//		System.out.println(c.getCollection());
//		System.out.println(c.putCollection());
//		System.out.println(c.deleteCollection());
	}

	private JsonObject getCollection() throws IOException, Exception {
		String[] params = { "id=ZyoDkM2sPzaJnVdq" };
		return y.getCollection(params);
	}

	private JsonObject postCollection() throws IOException, Exception {
		String[] params = { "name=newcoll3" };
		return y.postCollection(params);
	}

	private JsonObject putCollection() throws IOException, Exception {
		String[] params = { "id=ZyoDkM2sPzaJnVdq", "name=putname" };
		return y.putCollection(params);
	}

	private JsonObject deleteCollection() throws IOException, Exception {
		String id = "ZyoDkM2sPzaJnVdq";
		return y.deleteCollection(id);
	}
}
