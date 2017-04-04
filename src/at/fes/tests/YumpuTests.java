package at.fes.tests;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class YumpuTests {
	private final Logger logger = Logger.getLogger(YumpuTests.class);
	protected static final String TAG = YumpuTests.class.getSimpleName();
	
	public String apptoken;
	public YumpuTests(String apptoken) {
		this.apptoken = apptoken;
	}
	
	/*
	 * --- functions used for executable JAR ---  
	 * e.g. testing endpoints etc
	 */
	public static void main(String[] args) throws IOException {
		
		configAssertion(args);
		
		YumpuTests yumpuTests = new YumpuTests(args[0]);
		yumpuTests.testAll();
	}
	
	String progress_url_id, progress_file_id, progress_state,
	    progress_state_file, document_url_id, document_file_id, hotspot_id,
	    collection_id, section_id, embed_id, member_id, access_tag_id,
	    subscription_id;
	Yumpu yumpu;
	void testAll() {
		// make test calls across all available endpoints
		
		yumpu = new Yumpu(apptoken);
		
		JsonObject currentResponse = null;
		String currentCall = "";
		
		try {
			logger.debug("...begin TESTing..!");
			
			currentCall = "getCountries";
			currentResponse = yumpu.getCountries();
	        logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "getCategories";
			currentResponse = yumpu.getCategories();
	        logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "getLanguages";
			currentResponse = yumpu.getLanguages();
	        logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "postDocumentUrl";
	        String[] bodyData = {
	                "url=http://www.onlinemarketing-praxis.de/uploads/pdf/suchparameter-google-uebersicht.pdf",
	                "title=postet document per url",
	                "page_teaser_image=src/at/fes/examples/media/yumpu.png",
	                "page_teaser_page_range=1-2",
	                "page_teaser_url=http://www.yumpu.com/en"};
			currentResponse = yumpu.postDocumentUrl(bodyData);
			progress_url_id = currentResponse.get("progress_id").getAsString();
	        logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        postDocumentFile();
	        getDocumentProgress();
		}
		catch (Exception ex) {
			logger.error(currentCall+" ex: "+ex.toString());
		}
	}
	
	void postDocumentFile() throws Exception {
		
		JsonObject currentResponse = null;
		String currentCall = "postDocumentFile";
		
		String[] bodyData = {
		        "file=src/at/fes/examples/media/yumpu.pdf",
		        "title=postet document per local file",
		        "page_teaser_image=src/at/fes/examples/media/yumpu.png",
		        "page_teaser_page_range=1-2",
		        "page_teaser_url=http://www.yumpu.com/en"};
		currentResponse = yumpu.postDocumentFile(bodyData);
		progress_file_id = currentResponse.get("progress_id").getAsString();
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
		getDocumentFileProgress(progress_file_id);
	}	
	void getDocumentFileProgress(String progress_file_id) throws Exception {
		
		JsonObject currentResponse = null;
		String currentCall = "getDocumentFileProgress";
		
		String[] params = {"id=" + progress_file_id};
		currentResponse = yumpu.getDocumentProgress(params);
		try {
		    JsonObject jdoc = currentResponse.get("document").getAsJsonObject();
		    progress_state_file = jdoc.get("state").getAsString();
		} catch (Exception e) {
		    progress_state_file = "done";
		}
		if (progress_state_file.equals("rendering_in_progress")) {
		    TimeUnit.SECONDS.sleep(5);
		    getDocumentFileProgress(progress_file_id);
		} else {
		    JsonArray jarr = currentResponse.get("document").getAsJsonArray();
		    for (int i = 0; i < jarr.size(); ++i) {
		        JsonObject rec = jarr.get(i).getAsJsonObject();
		        document_file_id = ""+rec.get("id").getAsInt();//Integer.toString(rec.getInt("id"));
		    }
		}
		logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
	}
	
	void getDocumentProgress() throws Exception {
		
		JsonObject currentResponse = null;
		String currentCall = "getDocumentProgress";
		
		String[] params = {"id=" + progress_url_id};
		currentResponse = yumpu.getDocumentProgress(params);
		logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
		try {
		    JsonObject jdoc = currentResponse.get("document").getAsJsonObject();
		    progress_state = jdoc.get("state").getAsString();
		    logger.debug(currentCall 
	        		+ " | progress_state: " + progress_state
	        		);
		} catch (Exception e) {
		    progress_state = "done";
		    logger.debug(currentCall 
	        		+ " | rendering_done"
	        		);
		}
		if (progress_state.equals("rendering_in_progress")) {
		    TimeUnit.SECONDS.sleep(5);
		    getDocumentProgress();
		} else {
			logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
		    JsonArray jarr = currentResponse.get("document").getAsJsonArray();
		    for (int i = 0; i < jarr.size(); ++i) {
		        JsonObject rec = jarr.get(i).getAsJsonObject();
		        document_url_id = ""+rec.get("id").getAsInt(); //Integer.toString(rec.getInt("id"));
		    }
		    getDocuments();
		}
	}
	
	void getDocuments() throws Exception {
		
		JsonObject currentResponse = null;
		String currentCall = "getDocuments";
		
        String[] params = {"limit=2"};
        currentResponse = yumpu.getDocuments(params);
		logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getDocument();
    }

    void getDocument() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getDocument";
    	
        String[] params = {"id=" + document_url_id, "return_fields=url"};
        currentResponse = yumpu.getDocument(params);
		logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putDocument();
    }
	
    void putDocument() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putDocument";
    	
        String[] body = {"id=" + document_url_id, "title=newer title"};
        currentResponse = yumpu.putDocument(body);
		logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postDocumentHotspot();
    }
	
    void postDocumentHotspot() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "postDocumentHotspot";
    	
        String[] body = {"document_id=" + document_url_id, "type=link",
                "page=1"};
        String[] settings = {"x=100", "y=100", "w=50", "h=50", "name=google",
                "tooltip=google.com", "link=http://www.google.com"};
        currentResponse = yumpu.postDocumentHotspot(body, settings);
        JsonArray jarr = currentResponse.get("hotspot").getAsJsonArray();
        JsonObject rec = jarr.get(0).getAsJsonObject();
        hotspot_id = rec.get("id").getAsString();
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getDocumentHotspots();
    }
    
    void getDocumentHotspots() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getDocumentHotspots";
    	
        String[] params = {"id=" + document_url_id};
        currentResponse = yumpu.getDocumentHotspots(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getDocumentHotspot();
    }

    void getDocumentHotspot() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getDocumentHotspot";
    	
        String[] params = {"id=" + hotspot_id};
        currentResponse = yumpu.getDocumentHotspot(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putDocumentHotspot();
    }
    
    void putDocumentHotspot() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putDocumentHotspot";
    	
        String[] body = {"id=" + hotspot_id, "type=link"};
        String[] settings = {"x=10", "y=10", "w=20", "h=20", "name=yumpu.com",
                "tooltip=yumpu.com", "link=https://www.yumpu.com"};
        currentResponse = yumpu.putDocumentHotspot(body, settings);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postCollection();
    }
    
    void postCollection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "postCollection";
    	
        String[] body = {"name=holidays"};
        currentResponse = yumpu.postCollection(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        JsonArray jarr = currentResponse.get("collection").getAsJsonArray();
        JsonObject rec = jarr.get(0).getAsJsonObject();
        collection_id = rec.get("id").getAsString();
        
        getCollections();
    }
    
    void getCollections() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getCollections";
    	
        String[] params = {};
        currentResponse = yumpu.getCollections(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getCollection();
    }

    void getCollection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getCollection";
    	
        String[] params = {"id=" + collection_id};
        currentResponse = yumpu.getCollection(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putCollection();
    }

    void putCollection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putCollection";
    	
        String[] body = {"id=" + collection_id, "name=newname"};
        currentResponse = yumpu.putCollection(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postSection();
    }
    
    void postSection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "postSection";
    	
        String[] body = {"id=" + collection_id, "name=section",
                "description=this is a desc"};
        currentResponse = yumpu.postSection(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        JsonArray jarr = currentResponse.get("section").getAsJsonArray();
        JsonObject rec = jarr.get(0).getAsJsonObject();
        section_id = collection_id + "_" + rec.get("id").getAsString();
        
        getSection();
    }

    void getSection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "getSection";
    	
        String[] params = {"id=" + section_id};
        currentResponse = yumpu.getSection(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putSection();
    }

    void putSection() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putSection";
    	
        String[] body = {"id=" + section_id, "name=new name"};
        currentResponse = yumpu.putSection(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postSectionDocument();
    }
    
    void postSectionDocument() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "postSectionDocument";
    	
        String[] params = {"id=" + section_id, "documents=" + document_url_id + "," + document_file_id};
        currentResponse = yumpu.postSectionDocument(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        search();
    }
    
    void search() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "search";
    	
        String[] params = {"q=sports"};
        currentResponse = yumpu.search(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putUser();
    }

    void putUser() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "putUser";
    	
        String[] body = {"gender=male", "firstname=Stefan",
                "lastname=nachname", "address=dahuam 10"};
        currentResponse = yumpu.putUser(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getUser();
    }

    void getUser() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getUser";
    	
        String[] params = {};
        currentResponse = yumpu.getUser(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postEmbed();
    }
    
    void postEmbed() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "postEmbed";
    	
        String[] body = {"document_id=" + document_url_id, "type=1"};
        currentResponse = yumpu.postEmbed(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        JsonObject rec = currentResponse.get("embed").getAsJsonObject();
        embed_id = rec.get("id").getAsString();
        
        getEmbeds();
    }

    void getEmbeds() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getEmbeds";
    	
        String[] params = {};
        currentResponse = yumpu.getEmbeds(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getEmbed();
    }

    void getEmbed() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getEmbed";
    	
        String[] params = {"id=" + embed_id};
        currentResponse = yumpu.getEmbed(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putEmbed();
    }

    void putEmbed() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putEmbed";
    	
        String[] body = {"id=" + embed_id, "document_id=" + document_url_id,
                "type=2"};
        currentResponse = yumpu.putEmbed(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postMember();
    }
    
    private void postMember() {

    	JsonObject currentResponse = null;
		String currentCall = "postMember";
    	
        String[] body = {};
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String memName = "member" + sdf.format(cal.getTime());
        try {
        	
        	currentResponse = yumpu.postMember(memName, "my.pwd", body);
            logger.debug(currentCall 
            		+ " | " + checkResponseStatus(yumpu.responseCode) 
            		+ " | responseCode: " + yumpu.responseCode
            		+ " | responseData: " + currentResponse
            		);
        	
            JsonObject rec = currentResponse.get("member").getAsJsonObject();
            member_id = rec.get("id").getAsString();
        } 
        catch (Exception ex) {
            logger.error(currentCall+" ex: "+ex.toString());
        }
        
        getMembers();
    }

    void getMembers() {

    	JsonObject currentResponse = null;
		String currentCall = "getMembers";
    	
        String[] params = {};
        try {
	        currentResponse = yumpu.getMembers(params);
	        logger.debug(currentCall 
	        		+ " | " + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        getMember();
        }
        catch (Exception ex) {
            logger.error(currentCall+" ex: "+ex.toString());
        }
    }

    void getMember() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getMember";
    	
        String[] params = {"id=" + member_id};
        currentResponse = yumpu.getMember(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putMember();
    }

    void putMember() throws Exception {
    	
    	JsonObject currentResponse = null;
		String currentCall = "putMember";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String memName = "member" + sdf.format(cal.getTime());
        String[] body = {"id=" + member_id, "username=new" + memName};
        currentResponse = yumpu.putMember(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postAccessTag();
    }
    
    void postAccessTag() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "postAccessTag";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String accTagName = "actag" + sdf.format(cal.getTime());
        String[] body = {"name=" + accTagName, "description=this is a desc",
                "default=y"};

        currentResponse = yumpu.postAccessTag(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        JsonObject rec = currentResponse.get("access_tag").getAsJsonObject();
        access_tag_id = rec.get("id").getAsString();
        
        getAccessTags();
    }

    void getAccessTags() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getAccessTags";
    	
        String[] params = {};
        currentResponse = yumpu.getAccessTags(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getAccessTag();
    }

    void getAccessTag() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getAccessTag";
    	
        String[] params = {"id=" + access_tag_id};
        currentResponse = yumpu.getAccessTag(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putAccessTag();
    }

    void putAccessTag() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "putAccessTag";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String accTagName = "newAcT" + sdf.format(cal.getTime());
        String[] body = {"id=" + access_tag_id, "name=" + accTagName};
        currentResponse = yumpu.putAccessTag(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        postSubscription();
    }

    void postSubscription() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "postSubscription";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String subName = "sub" + sdf.format(cal.getTime());
        String[] body = {"itc_product_id=" + subName, "name=" + subName,
                "duration=62", "description=this is a desc"};
        currentResponse = yumpu.postSubscription(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        JsonObject rec = currentResponse.get("subscription").getAsJsonObject();
        subscription_id = rec.get("id").getAsString();
        
        getSubscriptions();
    }

    void getSubscriptions() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getSubscriptions";
    	
        String[] params = {};
        currentResponse = yumpu.getSubscriptions(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        getSubscription();
    }

    void getSubscription() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "getSubscription";
    	
        String[] params = {"id=" + subscription_id};
        currentResponse = yumpu.getSubscription(params);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        putSubscription();
    }

    void putSubscription() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "putSubscription";
    	
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String subName = "newsub" + sdf.format(cal.getTime());
        String[] body = {"id=" + subscription_id, "itc_product_id=" + subName,
                "name=" + subName, "duration=62"};
        currentResponse = yumpu.putSubscription(body);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteDocumentHotspot();
    }

    void deleteDocumentHotspot() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteDocumentHotspot";
    	
		currentResponse = yumpu.deleteDocumentHotspot(hotspot_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteSectionDocument();
    }

    void deleteSectionDocument() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteSectionDocument";
    	
		currentResponse = yumpu.deleteSectionDocument(section_id, document_url_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteSection();
    }

    void deleteSection() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteSection";
    	
		currentResponse = yumpu.deleteSection(section_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteCollection();
    }

    void deleteCollection() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteCollection";
    	
		currentResponse = yumpu.deleteCollection(collection_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteEmbed();
    }

    void deleteEmbed() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteEmbed";
    	
		currentResponse = yumpu.deleteEmbed(embed_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteMember();
    }

    void deleteMember() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteMember";
    	
		currentResponse = yumpu.deleteMember(member_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteAccessTag();
    }

    void deleteAccessTag() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteAccessTag";
    	
		currentResponse = yumpu.deleteAccessTag(access_tag_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteSubscription();
    }

    void deleteSubscription() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteSubscription";
    	
		currentResponse = yumpu.deleteSubscription(subscription_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        
        deleteDocument();
    }

    void deleteDocument() throws Exception {

    	JsonObject currentResponse = null;
		String currentCall = "deleteDocument";
    	
		currentResponse = yumpu.deleteDocument(document_file_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
        currentResponse = yumpu.deleteDocument(document_url_id);
        logger.debug(currentCall 
        		+ " | " + checkResponseStatus(yumpu.responseCode) 
        		+ " | responseCode: " + yumpu.responseCode
        		+ " | responseData: " + currentResponse
        		);
    }
    
    
	static void configAssertion(String[] args) {
		
		if (args.length == 0) {
        	System.err.println(TAG+" " + "about usage... read the source Luke!!! ");
			System.err.println(TAG+" required parameters: " + "myapptoken_value");
			System.err.println(TAG+" example: " + "java -jar yumpu.jar QsYm72aC6");
			System.err.println(TAG+" plz get yourself an apptoken from https://www.yumpu.com/en/account/profile/api");
			
            System.exit(-1);
        }
	}
	static String checkResponseStatus(int responseCode) {
		String response = "";
        if (responseCode > 202) {
        	response = "fail";
            if (responseCode == 401)
                response += " | you must be a premium user to use this feature";
        } else {
            response = "successful";
        }
        return response;
    }
	
	
}
