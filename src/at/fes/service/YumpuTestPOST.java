package at.fes.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.junit.Test;

public class YumpuTestPOST {

	@Test
	public void testPostDocumentUrl() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postDocumentUrl("http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"Diplomarbeit von Stefan");
		assertTrue(y.responseCode == 202);
	}

	@Test
	public void testPostCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postCollection("holidays");
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostSection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postSection("SyQPxIYetzpAhOgK", "section");
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostSectionDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents[] = { "55865398" };
		y.postSectionDocument("SyQPxIYetzpAhOgK_9r58Udahn0zFjQ23", documents);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPostUser() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postUser("cuo15642@zasod.com", "newuser80120", "hallo123");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPostEmbed() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postEmbed(55865447, 2);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPostMember() throws IOException, JSONException, NoSuchAlgorithmException {
		Yumpu y = new Yumpu();
		y.postMember("my.username", "my.pwd");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPostAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.postAccessTag("name", "desc");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPostSubscription() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getSubscription("4INKOAYuMJzHTogW");
		assertTrue(y.responseCode == 200);
	}
}
