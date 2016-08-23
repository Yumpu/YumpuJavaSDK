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
		y.putDocument("new name", 55865398);
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
		y.putSection("dpXYOxSR7Jeqw6f4_SPbrTa3UXwz5DjY6", "new ahsd");
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
		y.putMember("KUQDYspj5ZO42cw8", "new.name");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putMember("hJNK19CGXWjlvy2z", "new.name");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testPutSubscriptioon() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", 62);
		assertTrue(y.responseCode == 200);
	}
}
