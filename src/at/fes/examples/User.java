package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class User {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] params = {};
		String returnFields[] = { "id,name" };
		String[] body = {"gender=male", "firstname=brutasadl", "lastname=1qsadwe", "address=aljdsd 10"};
//		y.getUser(params, returnFields);
//		y.postUser(body);
		y.putUser(body);
	}

}
