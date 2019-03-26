package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Account;
import library.SendMailTLS;
import library.md5;
import library.randomCharacters;

public class AccountDAO {
	DataAccess instanceSQL = DataAccess.getInstance();

	public List<Account> getListAccount() throws SQLException {
		ArrayList<Account> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM accounts";
			result = stmt.executeQuery(query);
			Account account = null;
			while (result.next()) {
				account = new Account();
				account.setId(result.getString("id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setRole(result.getString("role"));
				list.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}

	public Account getAccount(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Account account = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM accounts WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				account = new Account();
				account.setId(result.getString("id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setRole(result.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return account;
	}
	
	public boolean insertAccount(String username, String password, String role) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO accounts(username, password, role) VALUES(?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, username);
			preStm.setString(2, password);
			preStm.setString(3, role);

			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public boolean updateAccount(String username, String password, String newPassword) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE accounts SET password = ? WHERE username = ? AND password = ?"; //tại đây nó hiểu :))) vô SQL làm nó đéo hiểu
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, newPassword);
			preStm.setString(2, username);
			preStm.setString(3, password);
			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public boolean changePasswordByEmail(String email, String newPassword) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE accounts SET password = ? WHERE id = (SELECT account_id FROM staffs WHERE email = ?);";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, newPassword);
			preStm.setString(2, email);
			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public boolean deleteAccount(String id) throws SQLException { // Delete Account
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM accounts WHERE id = ?";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, id);
			if (preStm.executeUpdate() == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (preStm != null) {
				preStm.close();
			}
		}
	}

	public int countAccountLogin(String username, String password) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		int count = 0;

		try {
			stmt = con.createStatement();
			String query = "SELECT COUNT(*) AS total FROM accounts WHERE username = '" + username + "' AND password = '"
					+ md5.MD5(password) + "'";
			result = stmt.executeQuery(query);
			while (result.next()) {
				count = result.getInt("total");
			}
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		System.out.println(count);
		return count;
	}
	
	public boolean checkLogin(String username, String password) {
		try {
			if(countAccountLogin(username, password) >= 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Account getAccountByUsername(String username) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Account account = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM accounts WHERE username = " + username;
			result = stmt.executeQuery(query);

			while (result.next()) {
				account = new Account();
				account.setId(result.getString("id"));
				account.setUsername(result.getString("username"));
				account.setPassword(result.getString("password"));
				account.setRole(result.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return account;
	}

	public static void main(String[] args) {
		AccountDAO accountDAO = new AccountDAO();
		try {

			System.out.println(accountDAO.getAccountByUsername("nhanha"));
			System.out.println(accountDAO.changePasswordByEmail("trongnghianguyen1003@gmail.com",
					randomCharacters.randomString(10)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
