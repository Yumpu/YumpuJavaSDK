package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestPUT {

	@Test
	public void testPutDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = { "id=55875793", "title=newtitles" };
		y.putDocument(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		y.putCollection("9RDnBITHpkxwc3s7", "sdfsdf");
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutSection() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[]  body = {"id=SyQPxIYetzpAhOgK_e1a8grt3i0FPMklT", "name=sasdfd", "description=aösldasdöjd"};
		y.putSection(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutUser() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"gender=male", "firstname=brutasadl", "lastname=1qsadwe", "address=aljdsd 10"};
		y.putUser(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutMember() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"id=X6Q1dLOzYf4Tgnb5", "username=newusername"};
		y.putMember(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"id=BVinDRHKNhl1ev56", "name=newTagName"};
		y.putAccessTag(body);
		assertTrue(y.responseCode == 200);
	}

	@Test
	public void testPutSubscriptioon() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] body = {"id=IE4170LQKqb9ykSi","itc_product_id=newsub2", "name=newsub2", "duration=62", "description=this is a desc"};
		y.putSubscription(body);
		assertTrue(y.responseCode == 200);
	}
}
