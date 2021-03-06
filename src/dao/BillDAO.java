package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.BillItems;
import bean.Bills;
import library.randomCharacters;

public class BillDAO {

	DataAccess instanceSQL = DataAccess.getInstance();
	
	public List<BillItems> getListBill() throws SQLException {
		ArrayList<BillItems> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM bill_items";
			result = stmt.executeQuery(query);
			BillItems items = null;
			while (result.next()) {
				items = new BillItems();
				items.setId(result.getString("id"));
				items.setBillId(result.getString("bill_id"));
				items.setProductId(result.getString("product_id"));
				items.setQuantity(result.getString("quantity"));
				items.setSession(result.getString("session"));
				items.setWeather(result.getString("weather"));
				items.setDiscount(result.getFloat("discount"));
				items.setDescription(result.getString("description"));
				list.add(items);
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

	public boolean insertBill(String staff_id, String customer_id, String table_id, String created_at) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();
		String query = "INSERT INTO bills(staff_id, customer_id, table_id, created_at) VALUES(?,?,?,?)";
		try {
			preStm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preStm.setString(1, staff_id);
			preStm.setString(2, customer_id);
			preStm.setString(3, table_id);
			preStm.setString(4, created_at);	
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
	
	public boolean insertBillInfo(String product_id, String quantity, String session, String weather, String discount, String description) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();
		String query = "INSERT INTO bill_items(bill_id, product_id, quantity, session, weather, discount, description) VALUES(?,?,?,?,?,?,?)";
		String sql = "SELECT MAX(id) as maxid from bills";
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);
		String bill_id = null;
		if (result != null) {
            if (result.next()) {
                 int id = result.getInt("maxid");
                 bill_id =  Integer.toString(id);
             }
            result.close();
         }
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, bill_id);
			preStm.setString(2, product_id);
			preStm.setString(3, quantity);
			preStm.setString(4, session);
			preStm.setString(5, weather);
			preStm.setString(6, discount);
			preStm.setString(7, description);

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
		BillDAO billDAO = new BillDAO();
		try {
			billDAO.insertBill("9", "1", null, "2019/04/04");
			billDAO.insertBillInfo("1", "2", "2", "20", "0", "Khong co gi");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
