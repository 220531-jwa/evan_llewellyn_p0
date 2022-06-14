package dev.llewellyn.models;

public class Client {
	
	private int clientId;
	private String firstName;
	private String lastName;
	
	public Client() {}
	
	public Client(int clientId, String firstName, String lastName) {
		this.clientId = clientId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getClientId() {
		return clientId;
	}
	
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
