package at.fes.examples;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Member {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception,
			NoSuchAlgorithmException {
		Member m = new Member();
//		System.out.println(m.postMember());
//		System.out.println(m.getMember());
//		System.out.println(m.putMember());
		System.out.println(m.deleteMember());
	}
	
	private JsonObject postMember() throws NoSuchAlgorithmException, IOException, Exception {
		String username = "newuser1299";
		String password = "mypassword";
		String[] params = {"comment=neuer user f�r mich"};
		return y.postMember(username, password, params);
	}
	
	private JsonObject getMember() throws IOException, Exception {
		String[] params = {"id=40bfG6QVywWFhoaJ", "return_fields=username"};
		return y.getMember(params);
	}
	
	private JsonObject putMember() throws IOException, Exception {
		String[] params = {"id=40bfG6QVywWFhoaJ", "username=newsuer213123"};
		return y.putMember(params);
	}
	
	private JsonObject deleteMember() throws IOException, Exception {
		String id = "40bfG6QVywWFhoaJ";
		return y.deleteMember(id);
	}
}
