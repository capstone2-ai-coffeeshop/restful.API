package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Customer;
import dao.CustomerDAO;

public class CustomerBO {

	CustomerDAO dao = new CustomerDAO();

	public List<Customer> getListCustomer() {
		List<Customer> list = new ArrayList<>();
		try {
			list = dao.getListCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Customer getCustomer(String id) {
		Customer account = null;
		try {
			account = dao.getCustomer(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public boolean insertCustomer(String fullname, String email, String gender, String dateOfBirth, String phone) {
		try {
			if (dao.insertCustomer(fullname, email, gender, dateOfBirth, phone)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCustomer(String id, String fullname, String email, String gender, String dateOfBirth, String phone) {
		try {
			if (dao.updateCustomer(id, fullname, email, gender, dateOfBirth, phone)) {
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
