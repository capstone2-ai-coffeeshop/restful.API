package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Customer;

public class CustomerDAO {

	DataAccess instanceSQL = DataAccess.getInstance();
	
	public List<Customer> getListCustomer() throws SQLException {
		ArrayList<Customer> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM customers";
			result = stmt.executeQuery(query);
			Customer customer = null;
			while (result.next()) {
				customer = new Customer();
				customer.setId(result.getString("id"));
				customer.setFullname(result.getString("full_name"));
				customer.setEmail(result.getString("email"));
				customer.setGender(result.getString("gender"));
				customer.setDateOfBirth(result.getString("date_of_birth"));
				customer.setPhone(result.getString("phone"));
				list.add(customer);
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
	
	public Customer getCustomer(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Customer customer = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM customers WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				customer = new Customer();
				customer.setId(result.getString("id"));
				customer.setFullname(result.getString("full_name"));
				customer.setEmail(result.getString("email"));
				customer.setGender(result.getString("gender"));
				customer.setDateOfBirth(result.getString("date_of_birth"));
				customer.setPhone(result.getString("phone"));
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
		return customer;
	}
	
	public boolean insertCustomer(String fullname, String email, String gender, String dateOfBirth, String phone) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO customers(full_name, email, gender, date_of_birth, phone  ) VALUES(?,?,?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, fullname);
			preStm.setString(2, email);
			preStm.setString(3, gender);
			preStm.setString(4, dateOfBirth);
			preStm.setString(5, phone);

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
	
	public boolean updateCustomer(String id, String fullname, String email, String gender, String dateOfBirth, String phone) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE customers SET full_name = ?, email = ?, gender = ?, date_of_birth = ?, phone = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, fullname);
			preStm.setString(2, email);
			preStm.setString(3, gender);
			preStm.setString(4, dateOfBirth);
			preStm.setString(5, phone);
			preStm.setString(6, id);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
