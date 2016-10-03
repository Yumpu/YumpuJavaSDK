/***
 *   Copyleft 2016 - BeerStorm / Rumble In The Jungle!
 * 
 *  @author: yilmaz@guleryuz.net 
 *  @see https://github.com/WareNinja | http://www.BeerStorm.net 
 *  
 *  disclaimer: I code for fun, dunno what I'm coding about!
 *  
 */

package at.fes.service;

import java.io.Serializable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;

public class ResponseData implements Serializable {
    
	private static final String TAG = ResponseData.class.getSimpleName(); 
	private static final long serialVersionUID = 1L;

	@Expose public ResponseMeta meta;// contains meta information
	@Expose public Object data;// main body: contains JsonObject or JsonArray of the response
    
    public ResponseData() {
    	meta = new ResponseMeta();
    	data = null;
    }
    
	@Override
	public String toString() {
		return TAG+" [" +
				"meta=" + meta 
				+ ", data=" + data
				+ "]";
	}
	
	public JsonElement getDataAsJson() {
		JsonElement result = new JsonObject();

		try {
			
			result = (new JsonParser()).parse( String.valueOf(this.data) );
		}
		catch (Exception ex) {
			System.err.println("error while parsing data:"+data + " | ex:"+ex.toString());
		}
		
		if (result.isJsonArray())
			return result.getAsJsonArray();
		else
			return result.getAsJsonObject();
	}
	public JsonObject toJsonObject() {
		return (new JsonParser()).parse( toJsonString() ).getAsJsonObject();
	}
    public String toJsonString() {
		return "" + MyUtils.getGson().toJson(this);
	}
	public String toJsonStringPrinting() {
		return "" + MyUtils.getGsonWithPrettyPrinting().toJson(this);
	}
	
/*
{
  "meta": {
    "code": 200,
     ...errorType and errorDetail... only IF there is any error aka code>201
  },
  "data": 
     ...results... JsonObject OR JsonArray depending on the endpoint's response
}

 */
}
