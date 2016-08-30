package at.fes.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Tester {

	public static void main(String[] args) throws IOException, ParseException,
			JSONException {
		Tester t = new Tester();
		t.postRequest("http://api.yumpu.com/2.0/document/file.json");
	}

	public JSONObject postRequest(String url) throws JSONException,
			ParseException, IOException {

		File targetFile = new File(
				"C:\\Users\\stefan.feurstein\\Downloads\\DA.pdf");
		// StringBuilder fileData = new StringBuilder(1000);
		// BufferedReader reader = new BufferedReader(new
		// FileReader(targetFile));
		//
		// char[] buf = new char[1024];
		// int numRead = 0;
		// while ((numRead = reader.read(buf)) != -1) {
		// String readData = String.valueOf(buf, 0, numRead);
		// fileData.append(readData);
		// buf = new char[1024];
		// }
		//
		// reader.close();
		//
		// String returnStr = fileData.toString();

		com.google.gson.JsonObject gson = new com.google.gson.JsonObject();

		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(targetFile));
			gson = jsonElement.getAsJsonObject();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		JSONObject json = new JSONObject(gson);
//		json.put("title", "aökldöfkj");
		System.out.println(gson);

//		HttpClient client = HttpClientBuilder.create().build();
//		HttpPost request = new HttpPost(url);
//
//		request.setHeader("X-ACCESS-TOKEN", "plbhzBor9sTicnJf51CVZuOEY2aqe7Kv");
//		request.addHeader("Content-Type", "application/json");
//
//		StringEntity params = new StringEntity(json.toString());
//		request.setEntity(params);
//
//		HttpResponse response = client.execute(request);
//		String jsonString = EntityUtils.toString(response.getEntity());
//		JSONObject myObject = new JSONObject(jsonString);
//		int responseCode = response.getStatusLine().getStatusCode();
//		System.out.println(myObject);

		return null;
	}
}
