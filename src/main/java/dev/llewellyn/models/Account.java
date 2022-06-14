package dev.llewellyn.models;

public class Account {
	
	private int accountId;
	private int clientId;
	private int amount;
	
	public Account() {}
	
	public Account(int accountId, int clientId) {
		this.accountId = accountId;
		this.clientId = clientId;
		this.amount = 0;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", clientId=" + clientId + ", amount=" + amount + "]";
	}
}
