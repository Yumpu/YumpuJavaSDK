package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import at.fes.service.Yumpu;


public class YumpuTestGET {
	
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
	
	@Test
	public void testGetCategories() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getCategories();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetLanguages() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getLanguages();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCountries() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getCountries();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCollections() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,sections"};
		y.getCollections(returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetCollection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,name"};
		y.getCollection("9RDnBITHpkxwc3s7", returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSection() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id,name"};
		y.getSection("9RDnBITHpkxwc3s7_HxcT6zFO9Z1nmaEL", returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testSearch() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.search("q=sports&in=title,description&views=1000-5000&language=en");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetUser() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getUser();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetEmbeds() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		String returnFields[] = {"id"};
		y.getEmbeds(0, 0, "desc", returnFields);
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetEmbed() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getEmbed("rU3W05fqP9kZGnYw");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetMembers() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getMembers();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetMember() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getMember("KUQDYspj5ZO42cw8");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetAccessTags() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getAccessTags();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetAccessTag() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getAccessTag("MiskUIrpzluycDCn");
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSubscriptions() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getSubscriptions();
		assertTrue(y.responseCode == 200);
	}
	
	@Test
	public void testGetSubscription() throws IOException, JSONException {
		Yumpu y = new Yumpu();
		y.getSubscription("4INKOAYuMJzHTogW");
		assertTrue(y.responseCode == 200);
	}
}
