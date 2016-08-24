package at.fes.examples;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Member {

	public static void main(String[] args) throws IOException, JSONException, NoSuchAlgorithmException {
		Yumpu y = new Yumpu();
//		y.getMember("KUQDYspj5ZO42cw8");
//		y.postMember("my.username", "my.pwd");
		y.putMember("ZSseFDtlUdAvh016", "cooleruser");
	}

}
