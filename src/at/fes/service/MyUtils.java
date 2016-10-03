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

import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public final class MyUtils {
	
	protected static final String TAG = MyUtils.class.getSimpleName();

	// - functions copied from YumpuFunctions -  
	//add parameter to URL
	public static String addParams(String[] params, String url) {
		url = url + "?";
		for (String s : params) {
			url = url + s + "&";
		}
		return url;
	}
	//write params into a JSON Object
	public static JsonObject createBody(String[] params)
			throws Exception {
		JsonObject json = new JsonObject();
		for (String s : params) {
			String index = s.substring(0, s.indexOf("="));
			String value = s.substring(s.indexOf("=") + 1);
			json.addProperty(index, value);
		}
		
		return json;
	}
	
	
	// -> Functions from BeerStorm Utils! <- 
	public static Gson getGson() {
		return new GsonBuilder()
		    .excludeFieldsWithModifiers( new int[] { 
		    		Modifier.STATIC, Modifier.TRANSIENT 
		    		} )
		    .excludeFieldsWithoutExposeAnnotation()
		    .create();
	}
	public static Gson getGsonWithPrettyPrinting() {
		return new GsonBuilder()
		    .excludeFieldsWithModifiers( new int[] { 
		    		Modifier.STATIC, Modifier.TRANSIENT 
		    		} )
		    .excludeFieldsWithoutExposeAnnotation()
		    .setPrettyPrinting()
		    .create();
	}
	public static Gson getGsonSimple() {
		return new GsonBuilder()
		    .excludeFieldsWithModifiers( new int[] { 
		    		Modifier.STATIC, Modifier.TRANSIENT 
		    		} )
		    .create();
	}
	public static Gson getGsonSimpleWithPrettyPrinting() {
		return new GsonBuilder()
		    .excludeFieldsWithModifiers( new int[] { 
		    		Modifier.STATIC, Modifier.TRANSIENT 
		    		} )
		    .setPrettyPrinting()
		    .create();
	}

	
	public static String buildUrlParams(Map<String, String> params) {
		
		String result = "";
		
		for (Map.Entry<String, String> param : params.entrySet()) {
			result += param.getKey() +"=" + param.getValue() + "&";
		}
		if (result.contains("&")) result = result.substring(0, result.lastIndexOf("&"));
		
		return result;
	}
	
	public static boolean isNotEmpty(Object input) {
		return !isEmpty(input);
	}
	public static boolean isEmpty(Object input) {
		boolean result = false;
		
		if (input!=null) {
			
			String inputStr = ""+input;
			result = ( "".equals(inputStr.trim()) );
		}
		
		return result;
	}
	
	
	/**
	 * getShortFormattedDate 
	 * 
	 * return in string format: yyyy-MM-dd
	 */
	public static String getShortFormattedDate() {
		return getShortFormattedDate(System.currentTimeMillis());
	}
	public static String getShortFormattedDate(long millis) {
		String resp = "";
		try {
			resp = getShortFormattedDate(new Date(millis));
		} catch (Exception ex){}
		return resp;
	}
	public static String getShortFormattedDate(Date date) {
		
		String resp = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			resp = sdf.format(date);
		} catch (Exception ex){}
		return resp;
	}
	 
	/**
	 * getFormattedDate 
	 * 
	 * return in string format: yyyy-MM-dd'T'HH:mm:ssZ
	 */
	public static String getFormattedDate() {
		return getFormattedDate(System.currentTimeMillis());
	}
	public static String getFormattedDate(long millis) {
		String resp = "";
		try {
			resp = getFormattedDate(new Date(millis));
		} catch (Exception ex){}
		return resp;
	}
	public static String getFormattedDate(Date date) {
		
		String resp = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
			resp = sdf.format(date);
		} catch (Exception ex){}
		return resp;
	}
	public static String getFormattedDate(Long millis, String timeZone) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
		if (timeZone!=null && !timeZone.trim().equals("")) sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		
		return millis!=null?sdf.format( new Date(millis) ):"";
	}

    
	/* --- 
	-> adapted from CastUtils: https://github.com/apache/pig/blob/89c2e8e76c68d0d0abe6a36b4e08ddc56979796f/src/org/apache/pig/impl/util/CastUtils.java
	*/
    private static Integer mMaxInt = Integer.valueOf(Integer.MAX_VALUE);
    private static Long mMaxLong = Long.valueOf(Long.MAX_VALUE);

    public static Double stringToDouble(String str) {
	    if (str == null) {
	    	return null;
	    } else {
		    try {
		    return Double.parseDouble(str);
		    } catch (NumberFormatException e) {
		    	System.err.println(TAG + "|Unable to interpret value "
		    		    + str
		    		    + " in field being "
		    		    + "converted to double, caught NumberFormatException <"
		    		    + e.getMessage() + "> field discarded");
		    	return null;
		    }
	    }
    }
    public static Float stringToFloat(String str) {
	    if (str == null) {
	    	return null;
	    } else {
		    try {
		    	return Float.parseFloat(str);
		    } catch (NumberFormatException e) {
		    	System.err.println(TAG + "|Unable to interpret value "
		    		    + str
		    		    + " in field being "
		    		    + "converted to float, caught NumberFormatException <"
		    		    + e.getMessage() + "> field discarded");
		    	return null;
		    }
	    }
    }
    public static Integer stringToInteger(String str) {
	    if (str == null) {
	    	return null;
	    } else {
		    try {
		    	return Integer.parseInt(str);
		    } catch (NumberFormatException e) {
			    // It's possible that this field can be interpreted as a double.
			    // Unfortunately Java doesn't handle this in Integer.valueOf. So
			    // we need to try to convert it to a double and if that works
			    // then
			    // go to an int.
			    try {
				    Double d = Double.valueOf(str);
				    // Need to check for an overflow error
				    if (d.doubleValue() > mMaxInt.doubleValue() + 1.0) {
				    	System.err.println(TAG + "|Value " + d
							    + " too large for integer");
				    	return null;
				    }
				    return Integer.valueOf(d.intValue());
			    } catch (NumberFormatException nfe2) {
			    	System.err.println(TAG + "|Unable to interpret value "
						    + str
						    + " in field being "
						    + "converted to int, caught NumberFormatException <"
						    + e.getMessage()
						    + "> field discarded");
			    	return null;
			    }
		    }
	    }
    }
    public static Long stringToLong(String str) {
	    if (str == null) {
	    	return null;
	    } else {
		    try {
		    	return Long.parseLong(str);
		    } catch (NumberFormatException e) {
			    // It's possible that this field can be interpreted as a double.
			    // Unfortunately Java doesn't handle this in Long.valueOf. So
			    // we need to try to convert it to a double and if that works
			    // then
			    // go to an long.
			    try {
				    Double d = Double.valueOf(str);
				    // Need to check for an overflow error
				    if (d.doubleValue() > mMaxLong.doubleValue() + 1.0) {
				    	System.err.println(TAG + "|Value " + d
							    + " too large for long");
				    	return null;
				    }
				    return Long.valueOf(d.longValue());
			    } catch (NumberFormatException nfe2) {
			    	System.err.println(TAG + "|Unable to interpret value "
						    + str
						    + " in field being "
						    + "converted to long, caught NumberFormatException <"
						    + nfe2.getMessage()
						    + "> field discarded");
			    	return null;
			    }
		    }
	    }
    }
	// ---
}
