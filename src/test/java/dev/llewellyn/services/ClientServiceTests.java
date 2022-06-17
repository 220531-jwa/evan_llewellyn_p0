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

import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.ClientDAO;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
	
	@InjectMocks
	private static ClientService cs;
	
	@Mock
	private static ClientDAO mockClientDao;
	
	// Note: Tests work when run individually, but not when run all together
	// Some issue with the mock instance
	
	@Test
	public void shouldReturnNewClient() {
		Client c = new Client(1, "Jay", "Crandal");
		
		when(mockClientDao.createClient(c)).thenReturn(c);
		
		assertEquals(c, cs.createClient(c));
	}
	
	@Test
	public void shouldReturnAllClients() {
		List<Client> clientsMock = new ArrayList<>();
		clientsMock.add(new Client(1, "Evan", "Llewellyn"));
		clientsMock.add(new Client(2, "Dan", "Felleman"));
		clientsMock.add(new Client(3, "Big", "Papi"));
		
		when(mockClientDao.getAllClients()).thenReturn(clientsMock);
		
		assertEquals(clientsMock, cs.getAllClients());
	}
	
	@Test
	public void shouldReturnClientById() {
		Client c = new Client(5, "Drake", "Bell");
		
		when(mockClientDao.getClientById(5)).thenReturn(c);
		
		try {
			assertEquals(c, cs.getClientById(5));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnSucessAfterClientUpdate() {
		Client cNew = new Client(2, "Frank", "Johnson");
		
		when(mockClientDao.updateClient(cNew)).thenReturn(1);
		
		try {
			assertEquals(1, cs.updateClient(cNew));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldReturnDeletedClient() {
		Client c = new Client(2, "Josh", "Bell");
		
		when(mockClientDao.deleteClient(2)).thenReturn(c);
		
		try {
			assertEquals(c, cs.deleteClient(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
