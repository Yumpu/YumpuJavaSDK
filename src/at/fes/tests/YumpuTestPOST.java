package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestPOST {

	@Test
	public void testPostDocumentUrl() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = {
				"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"title=Diplomarbeit asdvon Stefan" };
		y.postDocumentUrl(body);
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
		String[] body = { "id=SyQPxIYetzpAhOgK", "name=section",
				"description=aösldaöjd" };
		y.postSection(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostSectionDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String documents = "55875793";
		y.postSectionDocument("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", documents);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostUser() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = { "email=idz06322@zasod.com", "username=basdcsadl",
				"password=1qsadwe", "gender=male", "firstname=stef" };
		y.postUser(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostEmbed() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = { "document_id=55875854", "type=1", "start_page=2",
				"width=619" };
		y.postEmbed(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostMember() throws IOException, JSONException,
			NoSuchAlgorithmException {
		Yumpu y = new Yumpu();
		String[] body = { "comment=prataö" };
		y.postMember("uberwasser", "my.pwd", body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = { "name=uberwasser", "description=hallolo", "default=y" };
		y.postAccessTag(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPostSubscription() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = { "itc_product_id=uberwasser", "name=uberwaser",
				"duration=62", "description=213123" };
		y.postSubscription(body);
		assertTrue(y.responseCode == 200);
	}
}
