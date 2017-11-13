package pro.simpleproject.core.intra;

import java.util.LinkedHashMap;
import java.util.Map;

import pro.simpleproject.core.intra.server.ServerExecutor;

public class IntraModel {

	private static final Map<String, IntraContact> contacts = new LinkedHashMap<>();

	public static IntraContact setContact(IntraContact contact) {
		return contacts.put(contact.getContact(), contact);
	}

	public static IntraContact getContact(String contact) {
		return contacts.get(contact);
	}

	public static boolean hasContact(String contact) {
		return contacts.containsKey(contact);
	}

	public static void removeContact(String contact) {
		contacts.remove(contact);
	}

	public static void close() {
		for (IntraContact c : contacts.values()) {
			c.close();
		}
		ServerExecutor.close();
	}

}
