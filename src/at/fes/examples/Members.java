package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Members {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Members m = new Members();
//		System.out.println(m.getMembers());
	}

	private JsonObject getMembers() throws IOException, Exception {
		String[] params = {"limit=3"};
		return y.getMembers(params);
	}
}
