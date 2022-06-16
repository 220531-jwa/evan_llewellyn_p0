package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.Account;
import dev.llewellyn.repsitories.AccountDAO;

public class AccountService {

private static AccountDAO accountDao;
	
	public AccountService(AccountDAO accountDao) {
		AccountService.accountDao = accountDao;
	}
	
	public Account createClient(Account a) {
		Account createdAccount = accountDao.createAccount(a);
		return createdAccount;
	}

	public List<Account> getAllAccounts(int id) {
		return accountDao.getAllAccounts(id);
	}

	public List<Account> getAllAccountsWithQueryParams(int id, int greaterThan, int lessThan) {
		return accountDao.getAllAccountsWithQueryParams(id, greaterThan, lessThan);
	}
	
}
