package dev.llewellyn.repsitories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.llewellyn.models.Client;
import dev.llewellyn.utils.ConnectionUtil;

public class ClientDAO {

	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Client createClient(Client c) {
		// default for id
		String sql = "insert into clients values (default, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Client(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Client> getAllClients() {
		List<Client> clients = new ArrayList<Client>();

		String sql = "select * from clients";

		// Try with resources - this will auto close resources we need without finally
		// block
		try (Connection conn = cu.getConnection();) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Client c = new Client(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				clients.add(c);
			}
			return clients;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Client getClientById(int id) {
		String sql = "select * from clients where id = ?"; // Question mark symbolizes and IN parameter for the statement

		try (Connection conn = cu.getConnection()) {
			// More secure this way
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); // Setting ? to be id that is passed in (not 0 based index)

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// Will need to get their accounts too (need new constructor)
				return new Client(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null; // Could use Optional Class -> can help avoid NullPointer Exceptions
	}

//	public Client updateClient(Client changedClient) {
//		String sql = "update clients set first_name = ? where id = ?";
//
//		try (Connection conn = cu.getConnection()) {
//			PreparedStatement ps = conn.prepareStatement(sql);
//			// Only if given a client id
//			ps.setInt(1, changedClient.getFirstName());
//			ps.setInt(2, changedClient.getClientId());
//
//			ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	public void deleteClient(int id) {
		String sql = "delete from clients where id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
