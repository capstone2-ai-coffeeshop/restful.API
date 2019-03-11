package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ProductCategory;
import dao.ProductCategoryDAO;

public class ProductCategoryBO {
	
	ProductCategoryDAO dao = new ProductCategoryDAO();
	
	public List<ProductCategory> getListCategory() {
		List<ProductCategory> list = new ArrayList<>();
		try {
			list = dao.getListCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ProductCategory getCategory(String id) {
		ProductCategory category = null;
		try {
			category = dao.getCategory(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	public boolean insertCategory(String name, String description, String created_at) {
		try {
			if (dao.insertCategory(name, description, created_at)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCategory(String id, String name, String description, String created_at) {
		try {
			if (dao.updateCategory(id, name, description, created_at)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCategory(String id) {
		try {
			if (dao.deleteCategory(id)) {
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
		// TODO Auto-generated method stub

	}

}
