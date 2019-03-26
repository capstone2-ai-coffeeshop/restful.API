package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectionSQL.DataAccess;
import bean.Staffs;

public class StaffsDAO {
	DataAccess instanceSQL = DataAccess.getInstance();

	public List<Staffs> getListStaff() throws SQLException {
		ArrayList<Staffs> list = new ArrayList<>();
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM staffs";
			result = stmt.executeQuery(query);
			Staffs staffs = null;
			while (result.next()) {
				staffs = new Staffs();
				staffs.setId(result.getString("id"));
				staffs.setFullname(result.getString("full_name"));
				staffs.setEmail(result.getString("email"));
				staffs.setGender(result.getString("gender"));
				staffs.setDateOfBirth(result.getString("date_of_birth"));
				staffs.setPhone(result.getString("phone"));
				staffs.setAccountId(result.getString("account_id"));
				list.add(staffs);
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

	public Staffs getStaff(String id) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		Staffs staffs = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM staffs WHERE id = " + id;
			result = stmt.executeQuery(query);

			while (result.next()) {
				staffs = new Staffs();
				staffs.setId(result.getString("id"));
				staffs.setFullname(result.getString("full_name"));
				staffs.setEmail(result.getString("email"));
				staffs.setGender(result.getString("gender"));
				staffs.setDateOfBirth(result.getString("date_of_birth"));
				staffs.setPhone(result.getString("phone"));
				staffs.setAccountId(result.getString("account_id"));
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
		return staffs;
	}

	public boolean insertStaff(String fullname, String email, String gender, String dateOfBirth, String phone,
			String accountId) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "INSERT INTO staffs(full_name, email, gender, date_of_birth, phone, account_id) VALUES(?,?,?,?,?,?)";

		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, fullname);
			preStm.setString(2, email);
			preStm.setString(3, gender);
			preStm.setString(4, dateOfBirth);
			preStm.setString(5, phone);
			preStm.setString(6, accountId);

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

	public boolean updateStaff(String id, String fullname, String email, String gender, String dateOfBirth,
			String phone) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "UPDATE staffs SET full_name = ?, email = ?, gender = ?, date_of_birth = ?, phone = ? WHERE id = ?";
		try {
			preStm = con.prepareStatement(query);
			preStm.setString(1, fullname);
			preStm.setString(2, email);
			preStm.setString(3, gender);
			preStm.setString(4, dateOfBirth);
			preStm.setString(5, phone);
			preStm.setString(6, id);
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

	public boolean deleteStaff(String id) throws SQLException {
		Connection con = null;
		PreparedStatement preStm = null;
		con = instanceSQL.createConnection();

		String query = "DELETE FROM staffs WHERE id = ?";

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

	public boolean forgotPassword(String email) throws SQLException {
		Connection con = instanceSQL.createConnection();
		ResultSet result = null;
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			String query = "SELECT * FROM staffs WHERE email = '" + email + "'";
			result = stmt.executeQuery(query);
			while (result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return false;
	}

	public static void main(String[] args) {
		StaffsDAO dao = new StaffsDAO();
		try {
			System.out.println(dao.forgotPassword("trongnghianguyen1003@gmail.com"));
			// dao.insertStaff("Nguyen Trong Nghia", "nghianguyen12@gmail.com", "1",
			// "10/03/1998", "01207198133", "2");
		} catch (SQLException e) {
			// TODO Auto-generated catch block,
			e.printStackTrace();
		}
	}
}
