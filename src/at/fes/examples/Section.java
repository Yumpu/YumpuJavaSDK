package at.fes.examples;

import java.io.IOException;

import com.google.gson.JsonObject;

import at.fes.service.Yumpu;

public class Section {
	Yumpu y = new Yumpu("your access token");

	public static void main(String[] args) throws IOException, Exception {
		Section s = new Section();
		 System.out.println(s.postSection());
		// System.out.println(s.getSection());
		// System.out.println(s.putSection());
		// System.out.println(s.deleteSection());
	}

	private JsonObject postSection() throws IOException, Exception {
		String[] params = { "id=46gGMFOTEVf3SamD", "name=newsection" };
		return y.postSection(params);
	}

	private JsonObject getSection() throws IOException, Exception {
		String[] params = { "id=46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su" };
		return y.getSection(params);
	}

	private JsonObject putSection() throws IOException, Exception {
		String[] params = { "id=46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su",
				"name=newpostname" };
		return y.putSection(params);
	}

	private JsonObject deleteSection() throws IOException, Exception {
		String id = "46gGMFOTEVf3SamD_ZmUlVyovE9RMt0su";
		return y.deleteSection(id);
	}
}
