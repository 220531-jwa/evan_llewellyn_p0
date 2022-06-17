package dev.llewellyn.controllers;

import java.util.List;

import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.ClientDAO;
import dev.llewellyn.services.ClientService;
import io.javalin.http.Context;

public class ClientController {

	private static ClientService cs = new ClientService(new ClientDAO());

	public static void createNewClient(Context ctx) {
		ctx.status(201);
		Client clientFromReqBody = ctx.bodyAsClass(Client.class);
		Client c = cs.createClient(clientFromReqBody);
		ctx.json(c);
	}

	public static void getAllClients(Context ctx) {
		ctx.status(200);
		List<Client> clients = cs.getAllClients();
		ctx.json(clients);
	}

	public static void getClientById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		try {
			Client c = cs.getClientById(id);
			ctx.status(200);
			ctx.json(c);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}

	public static void updateClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Client clientFromReqBody = ctx.bodyAsClass(Client.class);
		clientFromReqBody.setClientId(id);
		
		try {
			cs.updateClient(clientFromReqBody);
			ctx.status(204);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}

	public static void deleteClient(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));

		try {
			cs.deleteClient(id);
			ctx.status(205);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(404);
			ctx.result("Client not found");
		}
	}
}
