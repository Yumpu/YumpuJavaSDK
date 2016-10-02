package at.fes.examples;

import java.io.IOException;

import at.fes.service.Yumpu;

public class Languages {

	public static void main(String[] args) throws IOException, Exception {
		Yumpu y = new Yumpu("your access token");
		System.out.println(y.getLanguages());
	}

}
