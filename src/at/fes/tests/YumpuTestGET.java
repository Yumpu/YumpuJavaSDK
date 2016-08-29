package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;


public class YumpuTestGET {
	
	@Test
	public void testGetDocument() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "url" };
		y.getDocument("55875413", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocuments() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "url" };
		y.getDocuments(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentHotspots() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = {};
		y.getDocumentHotspots("55875413", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentHotspot() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id" };
		y.getDocumentHotspot("35936979C40pnPVD", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetDocumentProgress() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = {};
		y.getDocumentProgress("0ce9d-d337a-71cc9-dd57b-f4bfb-0386a-aec35-5836c", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCategories() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		y.getCategories();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetLanguages() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		y.getLanguages();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCountries() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		y.getCountries();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCollections() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id" };
		y.getCollections(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getCollection("9RDnBITHpkxwc3s7", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSection() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testSearch() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		y.search("q=sports&in=title,description&views=1000-5000&language=en");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetUser() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getUser(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetEmbeds() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id" };
		y.getEmbeds(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetEmbed() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id" };
		y.getEmbed("WJ14dzHVSZjAyDYu", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetMembers() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {"offset=0"};
		String returnFields[] = { "id" };
		y.getMembers(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetMember() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id" };
		y.getMember("KUQDYspj5ZO42cw8", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetAccessTags() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getAccessTags(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getAccessTag("MiskUIrpzluycDCn", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSubscriptions() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getSubscriptions(params, returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSubscription() throws IOException, JSONException {
		Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
		String[] params = {};
		String returnFields[] = { "id,name" };
		y.getSubscription("4INKOAYuMJzHTogW", params, returnFields);
		assertTrue(y.responseCode == 200);
	}
}
