package pro.simpleproject.core.intra.model;

import pro.simpleproject.core.intra.client.Client;

public class IntraContact {

	private String address;
	private int port;
	private Client client;

	public IntraContact(String address, int port) {
		this.address = address;
		this.port = port;
	}

	public IntraContact(String address) {
		this(address, 0);
	}

	public String getAddress() {
		return address;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public Client getClient() {
		if (client == null) {
			if (port == 0) {
				return null;
			}
			client = new Client(address, port);
		}
		return client;
	}

	public void close() {
		if (client != null) {
			client.close();
		}
	}

}
