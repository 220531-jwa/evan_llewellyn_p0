package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.ClientDAO;

public class ClientService {

	private static ClientDAO clientDao = new ClientDAO();
	
	public Client createClient(Client c) {
		Client createdClient = clientDao.createClient(c);
		return createdClient;
	}

	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	public Client getClientById(int id) throws Exception {
		// this is where you could put some business logic 
		// for example checking if the client returned by clientDao.getclientById(id) is null 
		Client c = clientDao.getClientById(id);
		
		if (c == null) {
			throw new Exception("Client not found");
		}
		
		return c;
	}

	public void deleteClient(int id) {
		clientDao.deleteClient(id);
	}
	
//	public void updateclient(Client uChanged) {
//		clientDao.updateClient(uChanged);
//	}
	
}
