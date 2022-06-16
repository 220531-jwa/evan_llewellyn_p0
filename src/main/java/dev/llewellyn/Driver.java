package dev.llewellyn;

import io.javalin.Javalin;
//import io.javalin.http.Handler;
import static io.javalin.apibuilder.ApiBuilder.*;

import dev.llewellyn.controllers.AccountController;
import dev.llewellyn.controllers.ClientController;

public class Driver {
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create().start(8080);
		
		app.routes(() -> {
			path("/clients", () -> {
				get(ClientController::getAllClients);
				post(ClientController::createNewClient);
				path("/{id}", () -> {
					get(ClientController::getClientById);
					put(ClientController::updateClient);
					delete(ClientController::deleteClient);
					path("/accounts", () -> {
						post(AccountController::createNewAccount);
						get(AccountController::getAllAccounts);
						path("/{id}", () -> {
							//get by id
							//update account (put)
							//delete account
							//deposit and withdraw (patch)
							path("/transfer", () -> {
								//transfer between accounts (patch)
							});
						});
					});
				});
			});
		});
		
		app.get("/", ctx -> {
			ctx.status(200);
			ctx.result("Welcome to the Banking API!");
		});
	}
	
}
