package dev.llewellyn.services;

import java.util.List;

import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.ClientDAO;

public class ClientService {

	private static ClientDAO clientDao;

	public ClientService(ClientDAO clientDao) {
		ClientService.clientDao = clientDao;
	}

	public Client createClient(Client c) {
		Client createdClient = clientDao.createClient(c);
		return createdClient;
	}

	public List<Client> getAllClients() {
		return clientDao.getAllClients();
	}

	public Client getClientById(int id) throws Exception {
		Client c = clientDao.getClientById(id);

		if (c == null) {
			throw new Exception("Client not found");
		}

		return c;
	}

	public int updateClient(Client uChanged) throws Exception {
		int success = clientDao.updateClient(uChanged);
		
		if (success == 0) {
			throw new Exception("Client not found");
		}
		
		return success;
	}

	public Client deleteClient(int id) throws Exception {
		Client c = clientDao.deleteClient(id);
		
		if (c == null) {
			throw new Exception("Client not found");
		}
		
		return c;
	}

}
