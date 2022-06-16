package dev.llewellyn.models;

public class Account {
	
	private int accountId;
	private int clientId; //Change to an actual client?
	private String accountName;
	private String accountType;
	private int amount;
	
	public Account() {}
	
	public Account(int accountId, int clientId, String accountName, String accountType, int amount) {
		this.accountId = accountId;
		this.clientId = clientId;
		this.accountName = accountName;
		this.accountType = accountType;
		this.amount = amount;
	}
	
	public Account(String accountName, String accountType, int amount) {
		this.accountName = accountName;
		this.accountType = accountType;
		this.amount = amount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", clientId=" + clientId + ", accountName=" + accountName
				+ ", accountType=" + accountType + ", amount=" + amount + "]";
	}
}
