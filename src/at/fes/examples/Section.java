package at.fes.examples;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import at.fes.service.Yumpu;

public class Section {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, JSONException {
		Section s = new Section();
		 System.out.println(s.postSection());
		// System.out.println(s.getSection());
		// System.out.println(s.putSection());
		// System.out.println(s.deleteSection());
	}

	private JSONObject postSection() throws IOException, JSONException {
		String[] params = { "id=46gGMFOTEVf3SamD", "name=newsection" };
		return y.postSection(params);
	}

	private JSONObject getSection() throws IOException, JSONException {
		String[] params = { "id=46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su" };
		return y.getSection(params);
	}

	private JSONObject putSection() throws IOException, JSONException {
		String[] params = { "id=46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su",
				"name=newpostname" };
		return y.putSection(params);
	}

	private JSONObject deleteSection() throws IOException, JSONException {
		String id = "46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su";
		return y.deleteSection(id);
	}
}
