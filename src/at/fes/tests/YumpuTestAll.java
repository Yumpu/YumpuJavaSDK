package at.fes.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import at.fes.service.Yumpu;

public class YumpuTestAll {
	private Yumpu y = new Yumpu("plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
	private String progress_id, progress_state, document_id, hotspot_id,
			collection_id, section_id, embed_id, member_id, access_tag_id, subscription_id;
	JSONObject control = new JSONObject();

	public static void main(String[] args) throws IOException, JSONException,
			InterruptedException {
		YumpuTestAll ya = new YumpuTestAll();
		ya.getCountries();
	}

	 public void getCountries() throws IOException, JSONException {
	 y.getCountries();
	 System.out.println("get Countries response Code: " + y.responseCode);
	 control.put("getCountries", y.responseCode);
	 getCategories();
	 }
	
	 public void getCategories() throws IOException, JSONException {
	 y.getCategories();
	 System.out.println("get Categories response Code: " + y.responseCode);
	 control.put("getCategories", y.responseCode);
	 getLanguages();
	 }
	
	 public void getLanguages() throws IOException, JSONException {
	 y.getLanguages();
	 try {
		System.out.println("get Languages response Code: " + y.responseCode);
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
				"title=Diplomarbeit asdvon Stefan" };
		String res = y.postDocumentUrl(body).toString();
		JSONObject j = new JSONObject(res);
		progress_id = j.getString("progress_id");
		System.out.println("post document URL response Code: " + y.responseCode);
		control.put("postDocumentURL", y.responseCode);
		getDocumentProgress();
	}

	public void getDocumentProgress() throws IOException, JSONException,
			InterruptedException {
		String[] params = {};
		String returnFields[] = {};
		String res = y.getDocumentProgress(
				progress_id, params,
				returnFields).toString();
		System.out.println("get Document Progress response Code: " + y.responseCode);
		control.put("getDocumentProgress", y.responseCode);
		JSONObject j = new JSONObject(res);
		try {
			String doc = (String) j.get("document").toString();
			JSONObject jdoc = new JSONObject(doc);
			progress_state = jdoc.getString("state");
			System.out.println(progress_state);
		} catch (Exception e) {
			progress_state = "done";
		}
		if (progress_state.equals("rendering_in_progress")) {
			TimeUnit.SECONDS.sleep(5);
			getDocumentProgress();
		} else {
			String doc = (String) j.get("document").toString();
			JSONArray jarr = new JSONArray(doc);
			for (int i = 0; i < jarr.length(); ++i) {
				JSONObject rec = jarr.getJSONObject(i);
				document_id = rec.getString("id");
			}
			getDocuments();
		}
	}

	private void getDocuments() throws IOException, JSONException {
		String[] params = { "limit=1" };
		String[] returnFields = {};
		y.getDocuments(params, returnFields);
		System.out.println("get Documents response Code: " + y.responseCode);
		control.put("getDocuments", y.responseCode);
		getDocument();
	}

	private void getDocument() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = { "url" };
		y.getDocument(document_id, params, returnFields);
		System.out.println("get Document response Code: " + y.responseCode);
		control.put("getDocument", y.responseCode);
		putDocument();
	}

	private void putDocument() throws IOException, JSONException {
		String[] body = { "id=" + document_id, "title=newer title" };
		y.putDocument(body);
		System.out.println("put Document response Code: " + y.responseCode);
		control.put("putDocument", y.responseCode);
		getDocumentHotspots();
	}

	private void getDocumentHotspots() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		String res = y.getDocumentHotspots(document_id, params, returnFields)
				.toString();
		JSONObject j = new JSONObject(res);
		String hotspots = (String) j.get("hotspots").toString();
		JSONArray jarr = new JSONArray(hotspots);
		JSONObject rec = jarr.getJSONObject(0);
		hotspot_id = rec.getString("id");
		System.out.println("get Document hotpsots response Code: "
				+ y.responseCode);
		control.put("getDocumentHotspots", y.responseCode);
		getDocumentHotspot();
	}

	private void getDocumentHotspot() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getDocumentHotspot(hotspot_id, params, returnFields);
		System.out.println("get Document hotpsot response Code: "
				+ y.responseCode);
		control.put("getDocumentHotspot", y.responseCode);
		// y.post/putHotspot
		postCollection();
	}

	private void postCollection() throws IOException, JSONException {
		String res = y.postCollection("new Collection").toString();
		JSONObject j = new JSONObject(res);
		String collection = (String) j.get("collection").toString();
		JSONArray jarr = new JSONArray(collection);
		JSONObject rec = jarr.getJSONObject(0);
		collection_id = rec.getString("id");
		System.out.println("post Collection response Code: " + y.responseCode);
		control.put("postCollection", y.responseCode);
		getCollections();
	}

	private void getCollections() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		JSONObject json = new JSONObject(y.getCollections(params, returnFields));
		System.out.println("get Collections response Code: " + y.responseCode);
		control.put("getCollections", y.responseCode);
		getCollection();
	}

	private void getCollection() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getCollection(collection_id, params, returnFields);
		System.out.println("get Collection response Code: " + y.responseCode);
		control.put("getCollection", y.responseCode);
		putCollection();
	}

	private void putCollection() throws IOException, JSONException {
		y.putCollection(collection_id, "neydaswer");
		System.out.println("put Collection response Code: " + y.responseCode);
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
		System.out.println("post Section response Code: " + y.responseCode);
		control.put("postSection", y.responseCode);
		getSection();
	}

	private void getSection() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getSection(section_id, params, returnFields);
		System.out.println("get Section response Code: " + y.responseCode);
		control.put("getSection", y.responseCode);
		putSection();
	}

	private void putSection() throws IOException, JSONException {
		String[] body = { "id=" + section_id,
				"name=sasdfd", "description=aösldasdöjd" };
		y.putSection(body);
		System.out.println("post Section response Code: " + y.responseCode);
		control.put("putSection", y.responseCode);
		postSectionDocument();
	}

	private void postSectionDocument() throws IOException, JSONException {
		y.postSectionDocument(section_id, document_id);
		System.out.println("post Section document response Code: "
				+ y.responseCode);
		control.put("postSectionDocument", y.responseCode);
		search();
	}

	private void search() throws IOException, JSONException {
		y.search("q=sports&in=title,description&views=1000-5000&language=en");
		System.out.println("search response Code: " + y.responseCode);
		control.put("search", y.responseCode);
		putUser();
	}

	private void putUser() throws IOException, JSONException {
		String[] body = { "gender=male", "firstname=Stefan",
				"lastname=nachname", "address=dahuam 10" };
		y.putUser(body);
		System.out.println("post User response Code: " + y.responseCode);
		control.put("putUser", y.responseCode);
		getUser();
	}

	private void getUser() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getUser(params, returnFields);
		System.out.println("get User response Code: " + y.responseCode);
		control.put("getUser", y.responseCode);
		postEmbed();
	}

	private void postEmbed() throws IOException, JSONException {
		String[] body = { "document_id=" + document_id, "type=1" };
		String res = y.postEmbed(body).toString();
		JSONObject j = new JSONObject(res);
		String embed = (String) j.get("embed").toString();
		JSONObject rec = new JSONObject(embed);
		embed_id = rec.getString("id");
		System.out.println("post Embed response Code: " + y.responseCode);
		control.put("postEmbed", y.responseCode);
		getEmbeds();
	}

	private void getEmbeds() throws IOException, JSONException {
		String[] params = {};
		String returnFields[] = {};
		y.getEmbeds(params, returnFields);
		System.out.println("get Embeds response Code: " + y.responseCode);
		control.put("getEmbeds", y.responseCode);
		getEmbed();
	}

	private void getEmbed() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getEmbed(embed_id, params, returnFields);
		System.out.println("get Embed response Code: " + y.responseCode);
		control.put("getEmbed", y.responseCode);
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
			System.out.println("post Member response Code: " + y.responseCode);
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
		System.out.println("get Members response Code: " + y.responseCode);
		control.put("getMembers", y.responseCode);
		getMember();
	}

	private void getMember() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getMember(member_id, params, returnFields);
		System.out.println("get Member response Code: " + y.responseCode);
		control.put("getMember", y.responseCode);
		putMember();
	}

	private void putMember() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String memName = "member" + sdf.format(cal.getTime());
		String[] body = { "id=" + member_id, "username=new" + memName };
		y.putMember(body);
		System.out.println("put Member response Code: " + y.responseCode);
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
		System.out.println("post AccessTag response Code: " + y.responseCode);
		control.put("postAccessTag", y.responseCode);
		getAccessTags();
	}

	private void getAccessTags() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getAccessTags(params, returnFields);
		System.out.println("get AccessTags response Code: " + y.responseCode);
		control.put("getAccessTags", y.responseCode);
		getAccessTag();
	}

	private void getAccessTag() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getAccessTag(access_tag_id, params, returnFields);
		System.out.println("get AccessTag response Code: " + y.responseCode);
		control.put("getAccessTag", y.responseCode);
		putAccessTag();
	}

	private void putAccessTag() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String accTagName = "newAcT" + sdf.format(cal.getTime());
		String[] body = { "id=" + access_tag_id, "name=" + accTagName };
		y.putAccessTag(body);
		System.out.println("put AccessTag response Code: " + y.responseCode);
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
		System.out.println("post Subscription response Code: " + y.responseCode);
		control.put("postSubscription", y.responseCode);
		getSubscriptions();
	}

	private void getSubscriptions() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getSubscriptions(params, returnFields);
		System.out.println("get Subscriptions response Code: " + y.responseCode);
		control.put("getSubscriptions", y.responseCode);
		getSubscription();
	}

	private void getSubscription() throws IOException, JSONException {
		String[] params = {};
		String[] returnFields = {};
		y.getSubscription(subscription_id, params, returnFields);
		System.out.println("get Subscription response Code: " + y.responseCode);
		control.put("getSubscription", y.responseCode);
		putSubscription();
	}

	private void putSubscription() throws IOException, JSONException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String subName = "newsub" + sdf.format(cal.getTime());
		String[] body = {"id=" + subscription_id,"itc_product_id=" + subName, "name=" + subName, "duration=62"};
		y.putSubscription(body);
		System.out.println("put Subscription response Code: " + y.responseCode);
		control.put("putSubscription", y.responseCode);
		postJSON();
	}

	private void postJSON() throws JSONException {
		System.out.println(control);
		if(control.has("fail")){
			System.out.println("hi");
		}
	}
}
