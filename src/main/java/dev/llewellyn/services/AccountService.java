package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.Account;
import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.AccountDAO;
import dev.llewellyn.repsitories.ClientDAO;

public class AccountService {

	private static AccountDAO accountDao;
	private static ClientDAO clientDao = new ClientDAO();
	
	public AccountService(AccountDAO accountDao) {
		AccountService.accountDao = accountDao;
	}
	
	public Account createClient(Account a) {
		Account createdAccount = accountDao.createAccount(a);
		return createdAccount;
	}

	public List<Account> getAllAccounts(int id) throws Exception {
		Client c = clientDao.getClientById(id);
		
		if (c == null) {
			throw new Exception("Client not found");
		}
		
		return accountDao.getAllAccounts(id);
	}

	public List<Account> getAllAccountsWithQueryParams(int id, int greaterThan, int lessThan) throws Exception {
		Client c = clientDao.getClientById(id);
		
		if (c == null) {
			throw new Exception("Client not found");
		}
		
		return accountDao.getAllAccountsWithQueryParams(id, greaterThan, lessThan);
	}

	public Account getAccountById(int clientId, int accountId) throws Exception {
		Account a = accountDao.getAccountById(clientId, accountId);
		
		if (a == null) {
			throw new Exception("Client or account not found");
		}
		
		return a;
	}

	public void updateAccount(Account account) throws Exception {
		int success = accountDao.updateAccount(account);
		
		if (success == 0) {
			throw new Exception("Client or account not found");
		}
	}

	public void deleteAccount(int clientId, int accountId) throws Exception {
		Account a = accountDao.deleteAccount(clientId, accountId);
		
		if (a == null) {
			throw new Exception("Client or account not found");
		}
	}

	public void accountTransaction(int clientId, int accountId, String tType, int tAmount) throws Exception {
		Account a = accountDao.getAccountById(clientId, accountId);
		
		if (a == null) {
			throw new Exception("Client or account not found");
		}
		
		int currentAmount = a.getAmount();
		
		if (tType.equals("withdraw")) {
			// Need to check if account has sufficient amount for withdraw
			if (currentAmount >= tAmount) {
				accountDao.accountTransaction(accountId, currentAmount - tAmount);
			} else {
				throw new Exception("Insufficient funds");
			}
		} else {
			// This is a deposit, don't need to check amount
			accountDao.accountTransaction(accountId, currentAmount + tAmount);
		}
	}
	
	public void accountTransfer(int clientId, int fromAccountId, int toAccountId, int tAmount) throws Exception {
		// Can make use of accountTransaction methods since a transfer consists of a withdraw and deposit
		accountTransaction(clientId, fromAccountId, "withdraw", tAmount);
		accountTransaction(clientId, toAccountId, "deposit", tAmount);
	}
}
