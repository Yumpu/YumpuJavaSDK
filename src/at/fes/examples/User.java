package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class User {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		User u = new User();
//		System.out.println(u.postUser());
//		System.out.println(u.getUser());
//		System.out.println(u.putUser());
	}

	private JsonObject postUser() throws IOException, Exception {
		String[] params = {"gender=male", "username=maxmustermann2016", "password=musterpwd", "email=max.muster@email.com", "firstname=max", "lastname=mustermann", "address=dahuam10"};
		return y.postUser(params);
	}
	
	private JsonObject getUser() throws IOException, Exception {
		String[] params = {"return_fields=id,username"};
		return y.getUser(params);
	}
	
	private JsonObject putUser() throws IOException, Exception {
		String[] params = {"gender=male", "firstname=maximilian", "lastname=mustermnn", "city=Dornbirn"};
		return y.putUser(params);
	}
}
