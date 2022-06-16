package dev.llewellyn.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.llewellyn.models.Client;
import dev.llewellyn.repsitories.ClientDAO;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTests {
	
	// An instance of the class we are testing - a real instance
	@InjectMocks
	private static ClientService clientService;
	
	// Since we want to test only the functionality of the UserService class
	// we will mock and dependencies the class relies on
	@Mock
	private static ClientDAO mockClientDao;
	
//	@BeforeAll
//	public static void setUp() {
//		clientService = new ClientService();
//		
//		// Dummy instance that has no actual functionality
//		mockClientDao = mock(ClientDAO.class);
//	}
	
	@Test
	public void should_ReturnAllClients() {
		// given - 3 clients in DB
		List<Client> clientsMock = new ArrayList<>();
		clientsMock.add(new Client(1, "Evan", "Llewellyn"));
		clientsMock.add(new Client(2, "Dan", "Felleman"));
		clientsMock.add(new Client(3, "Big", "Papi"));
		
		// when - ClientService's getAllClients method is called
		when(mockClientDao.getAllClients()).thenReturn(clientsMock);
		
		// then - it should return all users
		assertEquals(clientsMock, clientService.getAllClients());
	}
	
}
