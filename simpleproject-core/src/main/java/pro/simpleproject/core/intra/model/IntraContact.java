package pro.simpleproject.core.intra.model;

public class IntraContact {

	private String address;
	private int port;

	public IntraContact(String address, int port) {
		this.address = address;
		this.port = port;
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

	public boolean write(Object o) {
		return false;
	}

	public void close() {
	}

}
