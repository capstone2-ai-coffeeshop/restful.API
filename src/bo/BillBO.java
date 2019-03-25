package bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BillItems;
import dao.BillDAO;

public class BillBO {
	BillDAO dao = new BillDAO();

	public List<BillItems> getListBill() {
		List<BillItems> list = new ArrayList<>();
		try {
			list = dao.getListBill();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean insertBill(String staff_id, String customer_id, String table_id, String status, String created_at, String total,
			String product_id, String quantity, String session, String time, String weather, String unitprice, String discount, String description) {
		try {
			if (dao.insertBill(staff_id, customer_id, table_id, status, created_at, total, product_id, quantity, session, time, weather, unitprice, discount, description)) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertBillInfo(String bill_id, String product_id, String quantity, String session, String time, String weather, String unitprice, String discount, String description) {
		try {
			if (dao.insertBillInfo(bill_id, product_id, quantity, session, time, weather, unitprice, discount, description)) {
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
