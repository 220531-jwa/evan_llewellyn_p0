package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Account;
import dev.llewellyn.repsitories.AccountDAO;
import dev.llewellyn.services.AccountService;
import io.javalin.http.Context;

public class AccountController {
	
	private static AccountService as = new AccountService(new AccountDAO());

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
		List<Account> a;
		
		try {
			if (lessThan == null || greaterThan == null) {
				a = as.getAllAccounts(id);
			} else {
				a = as.getAllAccountsWithQueryParams(id, Integer.parseInt(greaterThan), Integer.parseInt(lessThan));
			}
			ctx.status(200);
			ctx.json(a);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}
}
