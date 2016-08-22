package at.fes.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;


public class YumpuTest {
	
	@Test
	public void testGetDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"url"};
		y.getDocument("55847151", returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocuments() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getDocuments(0,0);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentHotspots() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"page"};
		y.getDocumentHotspots("55847151", returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentHotspot() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getDocumentHotspot("25808040yo5L8Fvm");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentProgress() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getDocumentProgress("0ce9d-d337a-71cc9-dd57b-f4bfb-0386a-aec35-5836c");
		assertTrue(y.responseCode == 200);
	}
}
