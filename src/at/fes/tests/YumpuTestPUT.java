package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestPUT {

	@Test
	public void testPutDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putDocument("55875413", "new name");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putCollection("9RDnBITHpkxwc3s7", "sdfsdf");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutSection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSection("SyQPxIYetzpAhOgK_9r58Udahn0zFjQ23", "new ahsd");
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutUser() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putUser("male", "Stefan", "Feurstein");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutMember() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putMember("BalrG1UQqyEoMP6Z", "newnasdme");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putMember("GigW7mKoaybETrxC", "newsdsfname");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutSubscriptioon() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", "62");
		assertTrue(y.responseCode == 200);
	}
}
