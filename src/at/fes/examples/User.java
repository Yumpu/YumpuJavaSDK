package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class User {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"gender=male", "firstname=brutasadl"};
		JSONObject json = new JSONObject(y.putUser(body));

		// String[] body = {"gender=male", "firstname=brutasadl",
		// "lastname=1qsadwe", "address=aljdsd 10"};
		// String[] params = {};
		// String returnFields[] = { "id,name" };
		// y.getUser(params, returnFields)
		// y.postUser(body);
		// y.putUser(body);
	}

}
