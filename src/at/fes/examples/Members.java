package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;

import at.fes.service.Yumpu;

public class Members {

	public static void main(String[] args) throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getMembers();
	}

}