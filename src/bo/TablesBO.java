package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Tables;
import dao.TablesDAO;

public class TablesBO {

	TablesDAO dao = new TablesDAO();

	public List<Tables> getListTables() {
		List<Tables> list = new ArrayList<>();
		try {
			list = dao.getListTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Tables getTable(String id) {
		Tables tables = null;
		try {
			tables = dao.getTable(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}

	public boolean insertTable(String quantityOfCustomer, String description, String status) {
		try {
			if (dao.insertTable(quantityOfCustomer, description, status)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTable(String id, String quantityOfCustomer, String description, String status) {
		try {
			if (dao.updateTable(id, quantityOfCustomer, description, status)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteTable(String id) {
		try {
			if (dao.deleteTable(id)) {
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
		TablesBO bo = new TablesBO();
		int id = 17;
		System.out.println(bo.deleteTable(Integer.toString(id)));
	}
}
