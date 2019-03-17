package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Products;
import dao.ProductsDAO;

public class ProductsBO {
	
	ProductsDAO dao = new ProductsDAO();

	public List<Products> getListProducts() {
		List<Products> list = new ArrayList<>();
		try {
			list = dao.getListProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Products getProduct(String id) {
		Products products = null;
		try {
			products = dao.getProduct(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public boolean insertProduct(String name, String categoryId, String unitPrice, String description, String status,
			String createdAt) {
		try {
			if (dao.insertProduct(name, categoryId, unitPrice, description, status, createdAt)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateProduct(String id, String name, String categoryId, String unitPrice, String description,
			String status, String createdAt) {
		try {
			if (dao.updateProduct(id, name, categoryId, unitPrice, description, status, createdAt)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProduct(String id) {
		try {
			if (dao.deleteProduct(id)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		ProductsBO bo = new ProductsBO();
		int id = 17;
		System.out.println(bo.deleteProduct(Integer.toString(id)));
	}
}
