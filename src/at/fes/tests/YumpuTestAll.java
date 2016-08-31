package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestAll {
	private Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
	private String progress_id, progress_state, document_url_id, document_file_id, hotspot_id,
			collection_id, section_id, embed_id, member_id, access_tag_id,
			subscription_id;
	JSONObject control = new JSONObject();
	private int successful, fail;

	public static void main(String[] args) throws IOException, JSONException,
			InterruptedException {
		YumpuTestAll ya = new YumpuTestAll();
		ya.getCountries();
//		ya.postDocumentFile();
//		ya.getDocuments();
	}

	public void getCountries() throws IOException, JSONException {
		y.getCountries();
		System.out.println("get Countries " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getCountries", y.responseCode);
		getCategories();
	}

	public void getCategories() throws IOException, JSONException {
		y.getCategories();
		System.out.println("get Categories " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getCategories", y.responseCode);
		getLanguages();
	}

	public void getLanguages() throws IOException, JSONException {
		y.getLanguages();
		try {
			System.out.println("get Languages " + checkStatus(y.responseCode)
					+ " " + y.responseCode);
			control.put("getLanguages", y.responseCode);
			postDocumentURL();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void postDocumentURL() throws IOException, JSONException,
			InterruptedException {
		String[] body = {
				"url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
				"title=Diplomarbeit von Stefan" };
		String res = y.postDocumentUrl(body).toString();
		JSONObject j = new JSONObject(res);
		progress_id = j.getString("progress_id");
		System.out.println("post document URL " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("postDocumentURL", y.responseCode);
		postDocumentFile();
		getDocumentProgress();
	}
	
	public void postDocumentFile() throws IOException, JSONException, InterruptedException {
		HashMap<String, String>map = new HashMap<String, String>();
		map.put("title", "Test file");
		map.put("description", "Test desc");
		map.put("page_teaser_page_range", "1-2");
		map.put("page_teaser_url", "http://www.yumpu.com/en");
		
		String path = "src\\at\\fes\\examples\\media\\yumpu.pdf";
		String imgPath = "src\\at\\fes\\examples\\media\\yumpu.png";
		y.postDocumentFile(path, imgPath, map);
		System.out.println("post document file " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("postDocumentFile", y.responseCode);
	}

	public void getDocumentProgress() throws IOException, JSONException,
			InterruptedException {
		String[] params = {};
		String returnFields[] = {};
		String res = y.getDocumentProgress(progress_id, params, returnFields)
				.toString();
		control.put("getDocumentProgress", y.responseCode);
		JSONObject j = new JSONObject(res);
		try {
			String doc = (String) j.get("document").toString();
			JSONObject jdoc = new JSONObject(doc);
			progress_state = jdoc.getString("state");
			System.out.println(progress_state);
		} catch (Exception e) {
			progress_state = "done";
			System.out.println("rendering_done");
		}
		if (progress_state.equals("rendering_in_progress")) {
			TimeUnit.SECONDS.sleep(5);
			getDocumentProgress();
		} else {
			System.out.println("get Document Progress "
					+ checkStatus(y.responseCode) + " " + y.responseCode);
			String doc = (String) j.get("document").toString();
			JSONArray jarr = new JSONArray(doc);
			for (int i = 0; i < jarr.length(); ++i) {
				JSONObject rec = jarr.getJSONObject(i);
				document_url_id = Integer.toString(rec.getInt("id"));
			}
			getDocuments();
		}
	}

	private void getDocuments() throws IOException, JSONException {
		String[] params = { "limit=2" };
		String[] returnFields = {};
		String res = y.getDocuments(params, returnFields).toString();
		JSONObject json = new JSONObject(res);
		JSONArray jarr = new JSONArray(json.get("documents").toString());
		for (int i = 0; i < jarr.length(); i++) {
			JSONObject jnew = new JSONObject(jarr.get(i).toString());
			if (i == 0)
				document_file_id = jnew.get("id").toString();
		}
		
		System.out.println("get Documents " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getDocuments", y.responseCode);
		getDocument();
	}

	private void getDocument() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = { "url" };
		y.getDocument(document_url_id, params, returnFields);
		System.out.println("get Document " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getDocument", y.responseCode);
		putDocument();
	}

	private void putDocument() throws IOException, JSONException {
		String[] body = { "id=" + document_url_id, "title=newer title" };
		y.putDocument(body);
		System.out.println("put Document " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putDocument", y.responseCode);
		postDocumentHotspot();
	}

	private void postDocumentHotspot() throws IOException, JSONException {
		String[] body = { "document_id=" + document_url_id, "type=link", "page=1" };
		String[] settings = { "x=100", "y=100", "w=50", "h=50", "name=google",
				"tooltip=google.com", "link=http://www.google.com" };
		String res = y.postDocumentHotspot(body, settings).toString();
		JSONObject j = new JSONObject(res);
		String hotspot = (String) j.get("hotspot").toString();
		JSONArray jarr = new JSONArray(hotspot);
		JSONObject rec = jarr.getJSONObject(0);
		hotspot_id = rec.getString("id");
		System.out.println("post Document hotspot "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("postDocumentHotspot", y.responseCode);
		getDocumentHotspots();
	}

	private void getDocumentHotspots() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getDocumentHotspots(document_url_id, params, returnFields).toString();
		System.out.println("get Document hotpsots "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("getDocumentHotspots", y.responseCode);
		getDocumentHotspot();
	}

	private void getDocumentHotspot() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getDocumentHotspot(hotspot_id, params, returnFields);
		System.out.println("get Document hotpsot "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("getDocumentHotspot", y.responseCode);
		putDocumentHotspot();
	}

	private void putDocumentHotspot() throws IOException, JSONException {
		String[] body = { "id=" + hotspot_id, "type=link" };
		String[] settings = { "x=100", "y=100", "w=50", "h=50", "name=google",
				"tooltip=google.com", "link=http://www.google.com" };
		y.putDocumentHotspot(body, settings).toString();
		System.out.println("put Document hotspot "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("putDocumentHotspot", y.responseCode);
		postCollection();
	}

	private void postCollection() throws IOException, JSONException {
		String res = y.postCollection("new Collection").toString();
		JSONObject j = new JSONObject(res);
		String collection = (String) j.get("collection").toString();
		JSONArray jarr = new JSONArray(collection);
		JSONObject rec = jarr.getJSONObject(0);
		collection_id = rec.getString("id");
		System.out.println("post Collection " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("postCollection", y.responseCode);
		getCollections();
	}

	private void getCollections() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getCollections(params, returnFields);
		System.out.println("get Collections " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getCollections", y.responseCode);
		getCollection();
	}

	private void getCollection() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getCollection(collection_id, params, returnFields);
		System.out.println("get Collection " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getCollection", y.responseCode);
		putCollection();
	}

	private void putCollection() throws IOException, JSONException {
		y.putCollection(collection_id, "neydaswer");
		System.out.println("put Collection " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("putCollection", y.responseCode);
		postSection();
	}

	private void postSection() throws IOException, JSONException {
		String[] body = { "id=" + collection_id, "name=section",
				"description=this is a desc" };
		String res = y.postSection(body).toString();
		JSONObject j = new JSONObject(res);
		String section = (String) j.get("section").toString();
		JSONArray jarr = new JSONArray(section);
		JSONObject rec = jarr.getJSONObject(0);
		section_id = collection_id + "_" + rec.getString("id");
		System.out.println("post Section " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("postSection", y.responseCode);
		getSection();
	}

	private void getSection() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getSection(section_id, params, returnFields);
		System.out.println("get Section " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getSection", y.responseCode);
		putSection();
	}

	private void putSection() throws IOException, JSONException {
		String[] body = { "id=" + section_id, "name=new name"};
		y.putSection(body);
		System.out.println("put Section " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putSection", y.responseCode);
		postSectionDocument();
	}

	private void postSectionDocument() throws IOException, JSONException {
		y.postSectionDocument(section_id, document_url_id);
		System.out.println("post Section document "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("postSectionDocument", y.responseCode);
		search();
	}

	private void search() throws IOException, JSONException {
		y.search("q=sports&in=title,description&views=1000-5000&language=en");
		System.out.println("search " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("search", y.responseCode);
		putUser();
	}

	private void putUser() throws IOException, JSONException {
		String[] body = { "gender=male", "firstname=Stefan",
				"lastname=nachname", "address=dahuam 10" };
		y.putUser(body);
		System.out.println("put User " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putUser", y.responseCode);
		getUser();
	}

	private void getUser() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getUser(params, returnFields);
		System.out.println("get User " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getUser", y.responseCode);
		postEmbed();
	}

	private void postEmbed() throws IOException, JSONException {
		String[] body = { "document_id=" + document_url_id, "type=1" };
		String res = y.postEmbed(body).toString();
		JSONObject j = new JSONObject(res);
		String embed = (String) j.get("embed").toString();
		JSONObject rec = new JSONObject(embed);
		embed_id = rec.getString("id");
		System.out.println("post Embed " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("postEmbed", y.responseCode);
		getEmbeds();
	}

	private void getEmbeds() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getEmbeds(params, returnFields);
		System.out.println("get Embeds " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getEmbeds", y.responseCode);
		getEmbed();
	}

	private void getEmbed() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getEmbed(embed_id, params, returnFields);
		System.out.println("get Embed " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getEmbed", y.responseCode);
		putEmbed();
	}

	private void putEmbed() throws IOException, JSONException {
		String[] body = {"id=" + embed_id, "document_id=" + document_url_id, "type=2"};
		y.putEmbed(body);
		System.out.println("put Embed " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putEmbed", y.responseCode);
		postMember();
	}

	private void postMember() {
		String[] body = {};
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String memName = "member" + sdf.format(cal.getTime());
		try {
			String res = y.postMember(memName, "my.pwd", body).toString();
			JSONObject j = new JSONObject(res);
			String member = (String) j.get("member").toString();
			JSONObject rec = new JSONObject(member);
			member_id = rec.getString("id");
			System.out.println("post Member " + checkStatus(y.responseCode)
					+ " " + y.responseCode);
			control.put("postMember", y.responseCode);
			getMembers();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getMembers() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getMembers(params, returnFields);
		System.out.println("get Members " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getMembers", y.responseCode);
		getMember();
	}

	private void getMember() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getMember(member_id, params, returnFields);
		System.out.println("get Member " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getMember", y.responseCode);
		putMember();
	}

	private void putMember() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String memName = "member" + sdf.format(cal.getTime());
		String[] body = { "id=" + member_id, "username=new" + memName };
		y.putMember(body);
		System.out.println("put Member " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putMember", y.responseCode);
		postAccessTag();
	}

	private void postAccessTag() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String accTagName = "actag" + sdf.format(cal.getTime());
		String[] body = { "name=" + accTagName, "description=this is a desc",
				"default=y" };

		String res = y.postAccessTag(body).toString();
		JSONObject j = new JSONObject(res);
		String accTag = (String) j.get("access_tag").toString();
		JSONObject rec = new JSONObject(accTag);
		access_tag_id = rec.getString("id");
		System.out.println("post AccessTag " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("postAccessTag", y.responseCode);
		getAccessTags();
	}

	private void getAccessTags() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getAccessTags(params, returnFields);
		System.out.println("get AccessTags " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getAccessTags", y.responseCode);
		getAccessTag();
	}

	private void getAccessTag() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getAccessTag(access_tag_id, params, returnFields);
		System.out.println("get AccessTag " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("getAccessTag", y.responseCode);
		putAccessTag();
	}

	private void putAccessTag() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String accTagName = "newAcT" + sdf.format(cal.getTime());
		String[] body = { "id=" + access_tag_id, "name=" + accTagName };
		y.putAccessTag(body);
		System.out.println("put AccessTag " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("putAccessTag", y.responseCode);
		postSubscription();
	}

	private void postSubscription() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String subName = "sub" + sdf.format(cal.getTime());
		String[] body = { "itc_product_id=" + subName, "name=" + subName,
				"duration=62", "description=this is a desc" };
		String res = y.postSubscription(body).toString();
		JSONObject j = new JSONObject(res);
		String sub = (String) j.get("subscription").toString();
		JSONObject rec = new JSONObject(sub);
		subscription_id = rec.getString("id");
		System.out.println("post Subscription " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("postSubscription", y.responseCode);
		getSubscriptions();
	}

	private void getSubscriptions() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getSubscriptions(params, returnFields);
		System.out.println("get Subscriptions " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getSubscriptions", y.responseCode);
		getSubscription();
	}

	private void getSubscription() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getSubscription(subscription_id, params, returnFields);
		System.out.println("get Subscription " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("getSubscription", y.responseCode);
		putSubscription();
	}

	private void putSubscription() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String subName = "newsub" + sdf.format(cal.getTime());
		String[] body = { "id=" + subscription_id, "itc_product_id=" + subName,
				"name=" + subName, "duration=62" };
		y.putSubscription(body);
		System.out.println("put Subscription " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("putSubscription", y.responseCode);
		deleteDocumentHotspot();
	}

	private void deleteDocumentHotspot() throws IOException, JSONException {
		y.deleteDocumentHotspot(hotspot_id);
		System.out.println("delete Document Hotpsot "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("deleteDocumentHotspot", y.responseCode);
		deleteSectionDocument();
	}

	private void deleteSectionDocument() throws IOException, JSONException {
		y.deleteSectionDocument(section_id, document_url_id);
		System.out.println("delete Section Document "
				+ checkStatus(y.responseCode) + " " + y.responseCode);
		control.put("deleteSectionDocument", y.responseCode);
		deleteSection();
	}

	private void deleteSection() throws IOException, JSONException {
		y.deleteSection(section_id);
		System.out.println("delete Section " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("deleteSection", y.responseCode);
		deleteCollection();
	}

	private void deleteCollection() throws IOException, JSONException {
		y.deleteCollection(collection_id);
		System.out.println("delete Collection " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("deleteCollection", y.responseCode);
		deleteEmbed();
	}

	private void deleteEmbed() throws IOException, JSONException {
		y.deleteEmbed(embed_id);
		System.out.println("delete Embed " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("deleteEmbed", y.responseCode);
		deleteMember();
	}

	private void deleteMember() throws IOException, JSONException {
		y.deleteMember(member_id);
		System.out.println("delete Member " + checkStatus(y.responseCode) + " "
				+ y.responseCode);
		control.put("deleteMember", y.responseCode);
		deleteAccessTag();
	}

	private void deleteAccessTag() throws IOException, JSONException {
		y.deleteAccessTag(access_tag_id);
		System.out.println("delete Access Tag " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("deleteAccessTag", y.responseCode);
		deleteSubscription();
	}

	private void deleteSubscription() throws IOException, JSONException {
		y.deleteSubscription(subscription_id);
		System.out.println("delete Subscription " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("deleteSubscription", y.responseCode);
		deleteDocument();
	}

	private void deleteDocument() throws IOException, JSONException {
		y.deleteDocument(document_file_id);
		y.deleteDocument(document_url_id);
		System.out.println("delete Document " + checkStatus(y.responseCode)
				+ " " + y.responseCode);
		control.put("deleteDocument", y.responseCode);
		postJSON();
	}

	private String checkStatus(int responseCode) {
		if (responseCode > 202) {
			fail++;
			if (responseCode == 401)
				System.out
						.println("you must be a premium user to use this feature");
			return "fail";
		} else {
			successful++;
			return "successful";
		}
	}

	private void postJSON() throws JSONException {
		System.out.println(control.length() + " tests done - " + successful
				+ " successful and " + fail + " failed");
	}
}
