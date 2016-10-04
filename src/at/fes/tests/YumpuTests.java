package at.fes.tests;

import java.io.IOException;

import org.apache.log4j.Logger;

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
	
	void testAll() {
		// make test calls across all available endpoints
		String progress_url_id, progress_file_id, progress_state,
	        progress_state_file, document_url_id, document_file_id, hotspot_id,
	        collection_id, section_id, embed_id, member_id, access_tag_id,
	        subscription_id;
		
		Yumpu yumpu = new Yumpu(apptoken);
		
		JsonObject currentResponse = null;
		String currentCall = "";
		
		try {
			logger.debug("begin testing...");
			
			currentCall = "getCountries";
			currentResponse = yumpu.getCountries();
			logger.debug(currentCall  
	    		+ " | responseCode: " + yumpu.responseCode
	    		+ " | responseData: " + currentResponse
	    		);
	        logger.debug(currentCall + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "getCategories";
			currentResponse = yumpu.getCategories();
	        logger.debug(currentCall + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "getLanguages";
			currentResponse = yumpu.getLanguages();
	        logger.debug(currentCall + checkResponseStatus(yumpu.responseCode) 
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
	        logger.debug(currentCall + checkResponseStatus(yumpu.responseCode) 
	        		+ " | responseCode: " + yumpu.responseCode
	        		+ " | responseData: " + currentResponse
	        		);
	        
	        currentCall = "getDocumentProgress";
	        String[] params = {"id=" + progress_url_id};
	        
		}
		catch (Exception ex) {
			logger.error(ex.toString());
		}
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
        if (responseCode > 202) {
            if (responseCode == 401)
                System.out
                        .println("you must be a premium user to use this feature");
            return "fail";
        } else {
            return "successful";
        }
    }
	
	
}
