package at.fes.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestPUT {

	@Test
	public void testPutDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putDocument(55873028, "new name");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putCollection("49cvPY1uKERtNnyZ", "sdfsdf");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutSection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", "new ahsd");
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
		y.putMember("ZSseFDtlUdAvh016", "newnasdme");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putMember("NXBIAZm6cHC7Vne0", "newsdsfname");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutSubscriptioon() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", 62);
		assertTrue(y.responseCode == 200);
	}
}
