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

}
