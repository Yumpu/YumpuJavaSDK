package at.fes.examples;

import java.io.IOException;

import at.fes.service.Yumpu;

public class DocumentCategories {

	public static void main(String[] args) throws IOException, Exception {
		Yumpu y = new Yumpu("your access token");
		System.out.println(y.getCategories());
	}

}
