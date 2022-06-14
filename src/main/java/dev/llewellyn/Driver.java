package dev.llewellyn;

import io.javalin.Javalin;
//import io.javalin.http.Handler;
import static io.javalin.apibuilder.ApiBuilder.*;

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
				});
			});
		});
		
		app.get("/", ctx -> {
			ctx.status(200);
			ctx.result("Welcome to the Banking API!");
		});
	}
	
}
