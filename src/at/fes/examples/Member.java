package at.fes.examples;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Member {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException,
			NoSuchAlgorithmException {
		Member m = new Member();
//		System.out.println(m.postMember());
//		System.out.println(m.getMember());
//		System.out.println(m.putMember());
		System.out.println(m.deleteMember());
	}
	
	private JSONObject postMember() throws NoSuchAlgorithmException, IOException, JSONException {
		String username = "newuser1299";
		String password = "mypassword";
		String[] params = {"comment=neuer user für mich"};
		return y.postMember(username, password, params);
	}
	
	private JSONObject getMember() throws IOException, JSONException {
		String[] params = {"id=40bfG6QVywWFhoaJ", "return_fields=username"};
		return y.getMember(params);
	}
	
	private JSONObject putMember() throws IOException, JSONException {
		String[] params = {"id=40bfG6QVywWFhoaJ", "username=newsuer213123"};
		return y.putMember(params);
	}
	
	private JSONObject deleteMember() throws IOException, JSONException {
		String id = "40bfG6QVywWFhoaJ";
		return y.deleteMember(id);
	}
}
