package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Account;
import dev.llewellyn.repsitories.AccountDAO;
import dev.llewellyn.repsitories.ClientDAO;
import dev.llewellyn.services.AccountService;
import io.javalin.http.Context;

public class AccountController {

	private static AccountService as = new AccountService(new AccountDAO(), new ClientDAO());

	public static void createNewAccount(Context ctx) {
		ctx.status(201);

		int clientId = Integer.parseInt(ctx.pathParam("id"));
		Account accountFromReqBody = ctx.bodyAsClass(Account.class);
		accountFromReqBody.setClientId(clientId);

		Account a = as.createClient(accountFromReqBody);
		ctx.json(a);
	}

	public static void getAllAccounts(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		String lessThan = ctx.queryParam("amountLessThan");
		String greaterThan = ctx.queryParam("amountGreaterThan");
		List<Account> accounts;

		try {
			if (lessThan == null || greaterThan == null) {
				accounts = as.getAllAccounts(id);
			} else {
				accounts = as.getAllAccountsWithQueryParams(id, Integer.parseInt(greaterThan), Integer.parseInt(lessThan));
			}
			ctx.status(200);
			ctx.json(accounts);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}

	public static void getAccountById(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountId = Integer.parseInt(ctx.pathParam("accountId"));

		try {
			Account a = as.getAccountById(clientId, accountId);
			ctx.status(200);
			ctx.json(a);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client or account not found");
		}
	}
	
	public static void updateAccount(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountId = Integer.parseInt(ctx.pathParam("accountId"));
		Account accountFromReqBody = ctx.bodyAsClass(Account.class);
		accountFromReqBody.setAccountId(accountId);
		accountFromReqBody.setClientId(clientId);
		
		try {
			as.updateAccount(accountFromReqBody);
			ctx.status(204);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client or account not found");
		}
	}
	
	public static void deleteAccount(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountId = Integer.parseInt(ctx.pathParam("accountId"));

		try {
			as.deleteAccount(clientId, accountId);
			ctx.status(205);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client or account not found");
		}
	}
	
	public static void accountTransaction(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountId = Integer.parseInt(ctx.pathParam("accountId"));
		
		String body = ctx.body();
		// This replaces everything but alphanumeric characters and colons with an empty string
		String clean = body.replaceAll("[^a-zA-Z0-9:]", "");
		
		String[] split = clean.split(":");
		
		String transactionType = split[0];
		int amount = Integer.parseInt(split[1]);
		
		try {
			as.accountTransaction(clientId, accountId, transactionType, amount);
			ctx.status(204);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("Insufficient funds")) {
				ctx.status(422);
				ctx.result(e.getMessage());
			} else {
				ctx.status(404);
				ctx.result("Client or account not found");
			}	
		}
	}
	
	public static void accountTransfer(Context ctx) {
		int clientId = Integer.parseInt(ctx.pathParam("id"));
		int accountId = Integer.parseInt(ctx.pathParam("accountId"));
		int accountId2 = Integer.parseInt(ctx.pathParam("accountId2"));
		
		String body = ctx.body();
		// This replaces everything but alphanumeric characters and colons with an empty string
		String clean = body.replaceAll("[^a-zA-Z0-9:]", "");
		
		String[] split = clean.split(":");
		
		int amount = Integer.parseInt(split[1]);
		
		try {
			as.accountTransfer(clientId, accountId, accountId2, amount);
			ctx.status(204);
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("Insufficient funds")) {
				ctx.status(422);
				ctx.result(e.getMessage());
			} else {
				ctx.status(404);
				ctx.result("Client or account not found");
			}	
		}
	}
}
