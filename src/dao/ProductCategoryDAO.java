package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.ProductCategory;
import bean.Staffs;;

public class ProductCategoryDAO {
	DataAccess instanceSQL = DataAccess.getInstance();
	
	public List<ProductCategory> getListCategory() throws SQLException {
		ArrayList<ProductCategory> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM product_category";
			result = stmt.executeQuery(query);
			ProductCategory category = null;
			while (result.next()) {
				category = new ProductCategory();
				category.setId(result.getString("id"));
				category.setName(result.getString("name"));
				category.setDescription(result.getString("description"));
				category.setCreatedAt(result.getString("created_at"));
				list.add(category);
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

	public ProductCategory getCategory(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		ProductCategory category = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM product_category WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				category = new ProductCategory();
				category.setId(result.getString("id"));
				category.setName(result.getString("name"));
				category.setDescription(result.getString("description"));
				category.setCreatedAt(result.getString("created_at"));
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
		return category;
	}

	public boolean insertCategory(String name, String description, String created_at) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO product_category(name, description, created_at) VALUES(?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, name);
			preStm.setString(2, description);
			preStm.setString(3, created_at);

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

	public boolean updateCategory(String id, String name, String description, String created_at) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE product_category SET name = ?, description = ?, created_at = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, name);
			preStm.setString(2, description);
			preStm.setString(3, created_at);
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

	public boolean deleteCategory(String id) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM product_category WHERE id = ?";

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
		ProductCategoryDAO dao = new ProductCategoryDAO();
		try {
			//dao.insertCategory("Bia", "Bia Heneiken lạnh cực ngon", "11/01/2019");
			dao.deleteCategory("1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
