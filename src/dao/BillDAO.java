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
				items.setBillId(result.getString("billId"));
				items.setProductId(result.getString("productId"));
				items.setQuantity(result.getString("quantity"));
				items.setSession(result.getString("session"));
				items.setTime(result.getString("time"));
				items.setWeather(result.getString("weather"));
				items.setUnitPrice(result.getString("unitPrice"));
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

	public boolean insertBill(String staff_id, String customer_id, String table_id, String status, String created_at, String total,
			String product_id, String quantity, String session, String time, String weather, String unitprice, String discount, String description) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();
		String query = "INSERT INTO bills(staff_id, customer_id, table_id, status, created_at, total) VALUES(?,?,?,?,?,?)";
		String bill_id= null;
		try {
			preStm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preStm.setString(1, staff_id);
			preStm.setString(2, customer_id);
			preStm.setString(3, table_id);
			preStm.setString(4, status);
			preStm.setString(5, created_at);
			preStm.setString(6, total);		
			if (preStm.executeUpdate() == 1) {
				ResultSet rs = preStm.getGeneratedKeys();
				while(rs.next()){
		            bill_id = Integer.toString(rs.getInt(1));
				}
				insertBillInfo(bill_id, product_id, quantity, session, time, weather, unitprice, discount, description);
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
	
	public boolean insertBillInfo(String bill_id, String product_id, String quantity, String session, String time, String weather, String unitprice, String discount, String description) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO bill_items(bill_id, product_id, quantity, session, time, weather, unitprice, discount, description) VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, bill_id);
			preStm.setString(2, product_id);
			preStm.setString(3, quantity);
			preStm.setString(4, session);
			preStm.setString(5, time);
			preStm.setString(6, weather);
			preStm.setString(7, unitprice);
			preStm.setString(8, discount);
			preStm.setString(9, description);

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
		BillDAO billtDAO = new BillDAO();
		try {
			billtDAO.insertBill("1", "1", "1", "1", "02/03/2019", "90000", "1", "2", "4", "12:00:AM", "20", "35", "0.2", "No");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
