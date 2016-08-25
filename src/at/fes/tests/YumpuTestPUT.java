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
		String[] body = { "id=55875793", "title=newtitles" };
		y.putDocument(body);
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
		String[]  body = {"id=SyQPxIYetzpAhOgK_e1a8grt3i0FPMklT", "name=sasdfd", "description=aösldasdöjd"};
		y.putSection(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutUser() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String[] body = {"gender=male", "firstname=brutasadl", "lastname=1qsadwe", "address=aljdsd 10"};
		y.putUser(body);
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
		y.putAccessTag("BVinDRHKNhl1ev56", "sadsasss");
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutSubscriptioon() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.putSubscription("4INKOAYuMJzHTogW", "subscr7", "subscr8", "62");
		assertTrue(y.responseCode == 200);
	}
}
