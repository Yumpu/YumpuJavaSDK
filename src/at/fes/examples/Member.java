package at.fes.examples;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Member {

	public static void main(String[] args) throws IOException, JSONException,
			NoSuchAlgorithmException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"id=X6Q1dLOzYf4Tgnb5", "username=newusername"};
		JSONObject json = new JSONObject(y.putMember(body));
		
//		String[] params = {};
//		String returnFields[] = { "id" };
//		String[] body = {"comment=prataö"};
//		y.getMember("KUQDYspj5ZO42cw8", params, returnFields);
//		y.postMember("quallesd", "my.pwd", body);
//		y.putMember("ZSseFDtlUdAvh016", "cooleruser");
//		y.deleteMember("X6Q1dLOzYf4Tgnb5");
	}

}
