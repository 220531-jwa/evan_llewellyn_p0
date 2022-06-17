package dev.llewellyn.repsitories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.llewellyn.models.Account;
import dev.llewellyn.utils.ConnectionUtil;

public class AccountDAO {
	
	private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

	public Account createAccount(Account a) {
		String sql = "insert into accounts values (default, ?, ?, ?, ?) returning *";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getClientId());
			ps.setString(2, a.getAccountName());
			ps.setString(3, a.getAccountType());
			ps.setInt(4, a.getAmount());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new Account(rs.getInt("id"), rs.getInt("client_id"), rs.getString("account_name"),
						rs.getString("account_type"), rs.getInt("amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Account> getAllAccounts(int id) {
		List<Account> accounts = new ArrayList<>();
		String sql = "select * from accounts where client_id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accounts.add(new Account(rs.getInt("id"), rs.getInt("client_id"), rs.getString("account_name"), 
						rs.getString("account_type"), rs.getInt("amount")));
			}
			return accounts;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Account> getAllAccountsWithQueryParams(int id, int greaterThan, int lessThan) {
		List<Account> accounts = new ArrayList<>();
		String sql = "select * from accounts where client_id = ? and amount > ? and amount < ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, greaterThan);
			ps.setInt(3, lessThan);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				accounts.add(new Account(rs.getInt("id"), rs.getInt("client_id"), rs.getString("account_name"), 
						rs.getString("account_type"), rs.getInt("amount")));
			}
			return accounts;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Account getAccountById(int clientId, int accountId) {
		String sql = "select * from accounts where client_id = ? and id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientId);
			ps.setInt(2, accountId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Account(rs.getInt("id"), rs.getInt("client_id"), rs.getString("account_name"),
						rs.getString("account_type"), rs.getInt("amount"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public int updateAccount(Account updatedAccount) {
		String sql = "update accounts set account_name = ? where id = ? and client_id = ?";

		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, updatedAccount.getAccountName());
			ps.setInt(2, updatedAccount.getAccountId());
			ps.setInt(3, updatedAccount.getClientId());

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public Account deleteAccount(int clientId, int accountId) {
		String sql = "delete from accounts where id = ? and client_id = ? returning *";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accountId);
			ps.setInt(2, clientId);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return new Account(rs.getInt("id"), rs.getInt("client_id"), rs.getString("account_name"),
						rs.getString("account_type"), rs.getInt("amount"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void accountTransaction(int accountId, int newAmount) {
		String sql = "update accounts set amount = ? where id = ?";
		
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, newAmount);
			ps.setInt(2, accountId);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
