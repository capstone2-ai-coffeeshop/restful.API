package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Products;

public class ProductsDAO {
	DataAccess instanceSQL = DataAccess.getInstance();

	public List<Products> getListProduct() throws SQLException {
		ArrayList<Products> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM products";
			result = stmt.executeQuery(query);
			Products products = null;
			while (result.next()) {
				products = new Products();
				products.setId(result.getString("id"));
				products.setName(result.getString("name"));
				products.setCategoryId(result.getString("category_id"));
				products.setUnitPrice(result.getDouble("unitprice"));
				products.setDescription(result.getString("description"));
				products.setStatus(result.getString("status"));
				products.setCreatedAt(result.getString("created_at"));
				list.add(products);
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

	public Products getProduct(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Products products = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM products WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				products = new Products();
				products.setId(result.getString("id"));
				products.setName(result.getString("name"));
				products.setCategoryId(result.getString("category_id"));
				products.setUnitPrice(result.getDouble("unitprice"));
				products.setDescription(result.getString("description"));
				products.setStatus(result.getString("status"));
				products.setCreatedAt(result.getString("created_at"));
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
		return products;
	}

	public boolean insertProduct(String name, String categoryId, String unitPrice, String description, String status,
			String createdAt) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO products(name, category_id, unitprice, description, status, created_at) VALUES(?,?,?,?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, name);
			preStm.setString(2, categoryId);
			preStm.setString(3, unitPrice);
			preStm.setString(4, description);
			preStm.setString(5, status);
			preStm.setString(6, createdAt);

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

	public boolean updateProduct(String id, String name, String categoryId, String unitPrice, String description,
			String status, String createdAt) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE products SET name = ?, category_id = ?, unitprice = ?, description = ?, status = ?, created_at = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, name);
			preStm.setString(2, categoryId);
			preStm.setString(3, unitPrice);
			preStm.setString(4, description);
			preStm.setString(5, status);
			preStm.setString(6, createdAt);
			preStm.setString(7, id);
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

	public boolean deleteProduct(String id) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM products WHERE id = ?";

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
		ProductsDAO dao = new ProductsDAO();
		try {
			dao.getProduct("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
