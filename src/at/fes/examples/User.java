package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class User {
	Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");

	public static void main(String[] args) throws IOException, JSONException {
		User u = new User();
//		System.out.println(u.postUser());
//		System.out.println(u.getUser());
//		System.out.println(u.putUser());
	}

	private JSONObject postUser() throws IOException, JSONException {
		String[] params = {"gender=male", "username=maxmustermann2016", "password=musterpwd", "email=max.muster@email.com", "firstname=max", "lastname=mustermann", "address=dahuam10"};
		return y.postUser(params);
	}
	
	private JSONObject getUser() throws IOException, JSONException {
		String[] params = {"return_fields=id,username"};
		return y.getUser(params);
	}
	
	private JSONObject putUser() throws IOException, JSONException {
		String[] params = {"gender=male", "firstname=maximilian", "lastname=mustermnn", "city=Dornbirn"};
		return y.putUser(params);
	}
}
