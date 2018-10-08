package pro.simpleproject.core.intra.model;

import java.util.LinkedHashMap;
import java.util.Map;

import io.netty.channel.Channel;
import pro.simpleproject.core.intra.IntraContactPaneBuilder;
import pro.simpleproject.core.intra.client.Client;
import pro.simpleproject.core.intra.server.ServerExecutor;

public class IntraModel {

	private static final Map<String, IntraContact> contacts = new LinkedHashMap<>();

	public static IntraContact setContact(String addr, int port) {
		IntraContact contact = contacts.get(addr);
		if (contact != null && contact.getPort() != port) {
			contact.close();
		}
		contact = new IntraContact(addr, port) {
			private Client client;

			@Override
			public boolean write(Object o) {
				if (client == null) {
					if (getPort() == 0) {
						return false;
					}
					client = new Client(getAddress(), getPort());
				}
				return client.run(o);
			}

			@Override
			public void close() {
				if (client != null) {
					client.close();
				}
			}
		};
		contacts.put(contact.getAddress(), contact);
		return contact;
	}

	public static IntraContact setContact(Channel channel, String addr, int port) {
		IntraContact contact = contacts.get(addr);
		if (contact == null) {
			IntraContactPaneBuilder.addItem(addr);
		} else if (contact.getPort() != port) {
			contact.close();
		}
		contact = new IntraContact(addr, port) {
			private Channel ch = channel;

			@Override
			public boolean write(Object o) {
				ch.writeAndFlush(o);
				return true;
			}

			@Override
			public void close() {
				if (ch != null) {
					ch.close();
				}
			}
		};
		contacts.put(contact.getAddress(), contact);
		return contact;
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
