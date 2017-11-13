package pro.simpleproject.core.intra;

public class IntraContact {

	private String contact;
	private String address;
	private int port;

	public IntraContact(String address, int port) {
		this.address = address;
		this.port = port;
		this.contact = address + ":" + port;
	}

	public String getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public void close() {
		
	}

}
