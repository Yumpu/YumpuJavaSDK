package at.fes.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestDELETE {

	@Test
	public void test() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.deleteDocument(10669);
		assertTrue(y.responseCode == 200);
	}

}
