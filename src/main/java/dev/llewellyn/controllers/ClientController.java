package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Client;
import dev.llewellyn.services.ClientService;
import io.javalin.http.Context;

public class ClientController {

	private static ClientService us = new ClientService();

	public static void createNewClient(Context ctx) {
		ctx.status(201);
		Client clientFromReqBody = ctx.bodyAsClass(Client.class);
		Client c = us.createClient(clientFromReqBody);
		ctx.json(c);
	}
	
	public static void getAllClients(Context ctx) {
		ctx.status(200);
		List<Client> clients = us.getAllClients();
		ctx.json(clients);
	}
	
	public static void getClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Client c = us.getClientById(id);
			ctx.status(200);
			ctx.json(c);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}
	
}
