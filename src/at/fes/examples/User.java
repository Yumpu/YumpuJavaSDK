package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class User {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getUser();
//		y.postUser("cuo15642@zasod.com", "newuser80120", "hallo123");
		y.putUser("male", "Stefan", "Feurstein");
	}

}
