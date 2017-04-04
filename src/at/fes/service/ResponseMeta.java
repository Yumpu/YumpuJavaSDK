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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/*
 * meta part of json response
 */
public class ResponseMeta implements Serializable {

	private static final String TAG = ResponseMeta.class.getSimpleName(); 
	private static final long serialVersionUID = 1L;
	
	public ResponseMeta() {
		this.code = 200;
		this.errorType = "";
		this.errorDetail = "";
	}
	public ResponseMeta(String errorMsg) {
		//new ResponseMeta(errorMsg, false);
		this.code = 400;
		this.errorType = "general";
		this.errorDetail = errorMsg;
	}
	public ResponseMeta(String errorMsg, boolean isNotContent) {
		this.code = isNotContent?204:400;
		this.errorType = "general";
		this.errorDetail = errorMsg;
	}
	
	public Integer code;
	public String errorType;
	public String errorDetail;
	
	@Override
	public String toString() {
		return TAG+" [code=" + code + ", errorType=" + errorType
				+ ", errorDetail=" + errorDetail
				+ "]";
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
}

/*
{
  "meta": {
    "code": 400
    , "errorType": "blabla"
    , "errorDetail": "blbla"
  },
  ...
}
*/
