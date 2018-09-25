package pro.simpleproject.core.intra.model;

import java.util.LinkedHashMap;
import java.util.Map;

import pro.simpleproject.core.intra.server.ServerExecutor;

public class IntraModel {

	private static final Map<String, IntraContact> contacts = new LinkedHashMap<>();

	public static IntraContact setContact(String addr, int port) {
		IntraContact contact = contacts.get(addr);
		if (contact == null) {
			contact = new IntraContact(addr, port);
			contacts.put(contact.getAddress(), contact);
		} else if (contact.getPort() == 0) {
			contact.setPort(port);
		}
		return contact;
	}

	public static IntraContact setContact(String addr) {
		IntraContact contact = new IntraContact(addr);
		return contacts.put(contact.getAddress(), contact);
	}

	public static IntraContact getContact(String addr) {
		return contacts.get(addr);
	}

	public static boolean hasContact(String addr) {
		return contacts.containsKey(addr);
	}

	public static void removeContact(String addr) {
		IntraContact c = contacts.remove(addr);
		if (c != null) {
			c.close();
		}
	}

	public static void close() {
		for (IntraContact c : contacts.values()) {
			c.close();
		}
		ServerExecutor.close();
	}

}
