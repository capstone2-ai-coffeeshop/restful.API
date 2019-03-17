package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Tables;

public class TablesDAO {
	DataAccess instanceSQL = DataAccess.getInstance();

	public List<Tables> getListTable() throws SQLException {
		ArrayList<Tables> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM tables";
			result = stmt.executeQuery(query);
			Tables tables = null;
			while (result.next()) {
				tables = new Tables();
				tables.setId(result.getString("id"));
				tables.setQuantityOfCustomer(result.getInt("quantity_of_customer"));
				tables.setDescription(result.getString("description"));
				tables.setStatus(result.getString("status"));
				list.add(tables);
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

	public Tables getTable(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Tables tables = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM tables WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				tables = new Tables();
				tables.setId(result.getString("id"));
				tables.setQuantityOfCustomer(result.getInt("quantity_of_customer"));
				tables.setDescription(result.getString("description"));
				tables.setStatus(result.getString("status"));
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
		return tables;
	}

	public boolean insertTable(String quantityOfCustomer, String description, String status) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO staffs(quantity_of_customer, description, status) VALUES(?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, quantityOfCustomer);
			preStm.setString(2, description);
			preStm.setString(3, status);

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

	public boolean updateTable(String id, String quantityOfCustomer, String description, String status) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE tables SET quantity_of_customer = ?, description = ?, status = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, quantityOfCustomer);
			preStm.setString(2, description);
			preStm.setString(3, status);
			preStm.setString(4, id);
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

	public boolean deleteTable(String id) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM tables WHERE id = ?";

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

	public static void main(String[] args) {
		TablesDAO dao = new TablesDAO();
		try {
			dao.getTable("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
