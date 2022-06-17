package dev.llewellyn.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.llewellyn.models.Account;
import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.AccountDAO;
import dev.llewellyn.repsitories.ClientDAO;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

	@InjectMocks
	private static AccountService as;

	@Mock
	private static ClientDAO mockClientDao;

	@Mock
	private static AccountDAO mockAccountDao;

	// Note: Tests work when run individually, but not when run all together
	// Some issue with the mock instance

	@Test
	public void shouldReturnNewAccount() {
		Account a = new Account(1, 3, "Jay Checking Acc", "Checkings", 100);

		when(mockAccountDao.createAccount(a)).thenReturn(a);

		assertEquals(a, as.createClient(a));
	}
	
	@Test
	public void shouldReturnAllClientAccounts() {
		Client c = new Client(3, "Jay", "Hammond");
		
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(1, 3, "Jay Checking Acc", "Checkings", 100));
		accounts.add(new Account(2, 3, "Jay Savings Acc", "Savings", 1000));

		when(mockClientDao.getClientById(3)).thenReturn(c);
		when(mockAccountDao.getAllAccounts(3)).thenReturn(accounts);

		try {
			assertEquals(accounts, as.getAllAccounts(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldReturnAllClientAccountsWithinQueryParams() {
		Client c = new Client(4, "Bruce", "Wayne");
		
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(1, 4, "Wayne Checking", "Checkings", 400));
		accounts.add(new Account(2, 4, "Wayne Fake Savings", "Savings", 1500));

		when(mockClientDao.getClientById(4)).thenReturn(c);
		when(mockAccountDao.getAllAccountsWithQueryParams(4, 300, 1800)).thenReturn(accounts);

		try {
			assertEquals(accounts, as.getAllAccountsWithQueryParams(4, 300, 1800));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnAccountById() {
		Account a = new Account(6, 4, "Elon Savings", "Savings", 1000000);

		when(mockAccountDao.getAccountById(4, 6)).thenReturn(a);

		try {
			assertEquals(a, as.getAccountById(4, 6));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnUpdateAccount() {
		Account a = new Account(3, 9, "Josh Savings", "Savings", 1500);

		when(mockAccountDao.updateAccount(a)).thenReturn(1);

		try {
			assertEquals(1, as.updateAccount(a));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnDeletedAccount() {
		Account a = new Account(2, 1, "Carly Checking", "Checkings", 20000);

		when(mockAccountDao.deleteAccount(1, 2)).thenReturn(a);

		try {
			assertEquals(a, as.deleteAccount(1, 2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnSuccessAfterWithdraw() {
		Account a = new Account(1, 3, "Ted Savings", "Savings", 2000);
		
		when(mockAccountDao.getAccountById(3, 1)).thenReturn(a);
		when(mockAccountDao.accountTransaction(1, 1800)).thenReturn(1);

		try {
			assertEquals(1, as.accountTransaction(3, 1, "withdraw", 200));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnSuccessAfterDeposit() {
		Account a = new Account(1, 3, "Ted Savings", "Savings", 2000);
		
		when(mockAccountDao.getAccountById(3, 1)).thenReturn(a);
		when(mockAccountDao.accountTransaction(1, 2200)).thenReturn(1);

		try {
			assertEquals(1, as.accountTransaction(3, 1, "deposit", 200));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnSuccessAfterTransfer() {
		Account a = new Account(4, 8, "Fred Savings", "Savings", 1000);
		Account a2 = new Account(5, 8, "Fred Checking", "Checking", 0);
		
		when(mockAccountDao.getAccountById(8, 4)).thenReturn(a);
		when(mockAccountDao.getAccountById(8, 5)).thenReturn(a2);
		when(mockAccountDao.accountTransaction(4, 850)).thenReturn(1);
		when(mockAccountDao.accountTransaction(5, 150)).thenReturn(1);

		try {
			assertEquals(1, as.accountTransfer(8, 4, 5, 150));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
