package org.firestorm.demosecurities.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.firestorm.demosecurities.utils.Constant;
import org.firestorm.demosecurities.utils.HashUtil;

public class UserDAO {
	static final Logger LOGGER = Logger.getLogger(UserDAO.class);

	public static int errorCode = 0;

	public static void main(String[] args) {
//		User user = new User(0, "tuanbs", "123456", "Bùi Sĩ Tuấn",
//				"tuanbs@bkav.com", "01266207292", 2500000, 15000000, 10000000,
//				350000, 5000000);
//		insert(user);
		User user = getUser("tuanbs", "123456");
		System.out.println(user.getListCertificate().size());
	}
	
	public static void insertDefaultUser(){
		User user = new User(0, "tuanbs", "123456", "Bùi Sĩ Tuấn",
				"tuanbs@bkav.com", "01266207292", 2500000, 15000000, 10000000,
				350000, 5000000);
		insert(user);
	}

	/**
	 * Check user table is empty
	 * 
	 * @return
	 */
	public static boolean isEmpty() {
		boolean check = true;
		Connection conn = MySQLConnector.popConnection();
		PreparedStatement ps = null;
		if (conn == null) {
			LOGGER.error("CANNOT CONNECT TO DATABASE SERVER");
			return check;
		}
		try {
			ps = conn.prepareStatement("SELECT * FROM USER");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return false;
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
		return check;
	}

	/**
	 * Check exist user
	 * 
	 * @param userName
	 * @return
	 */
	public static int checkUser(String userName) {
		int result = Constant.CREATE_COMPONENT_SUCCESS;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			if (!"".equalsIgnoreCase(userName)) {
				conn = MySQLConnector.popConnection();
				if (conn == null) {
					errorCode = Constant.CANNOT_CONNECT_DB;
				}
				ps = conn.prepareStatement(
						"SELECT id FROM USER WHERE username = ?");
				ps.setString(1, userName);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					result = Constant.USERNAME_ALREADY_EXIST;
				}
				rs.close();
				ps.close();
			} else {
				result = Constant.USERNAME_NULL;
			}
		} catch (Exception ex) {
			LOGGER.error("DATABASE CONNECTION ERROR: " + ex.getMessage());
			result = Constant.CREATE_COMPONENT_FAILED;
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
		return result;
	}

	/**
	 * Remove monitor user
	 * 
	 * @param id
	 * @return
	 */
	public static int delete(int id) {
		Connection conn = MySQLConnector.popConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM USER WHERE id = ?");

			ps.setInt(1, id);
			int rs = ps.executeUpdate();

			TransactionLogDAO.deleteUserLog(id);

			return rs;
		} catch (SQLException e) {
			return 0;
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
	}

	/**
	 * Update user information
	 * 
	 * @param user
	 * @return
	 */
	public static int update(User user) {
		Connection conn = MySQLConnector.popConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE user SET pass=?, sodutienmat=?,"
					+ " fullname=?, tienungtruoc=?, email=?, taikhoangiaodich=?,"
					+ " soducothegiaodich=?, khoiluongcothemua=?, tientreogoc=? WHERE id = ?");
			ps.setString(1, user.getPassword());
			ps.setInt(2, user.getSoDuTienMat());
			ps.setString(3, user.getFullName());
			ps.setInt(4, user.getTienUngTruoc());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getPhoneNumber());
			ps.setInt(7, user.getSoDuCoTheGiaoDich());
			ps.setInt(8, user.getKhoiLuongCoTheMua());
			ps.setInt(9, user.getTienTreoGoc());

			ps.setInt(10, user.getId());
			int rs = ps.executeUpdate();

			return rs;
		} catch (SQLException e) {
			LOGGER.error(
					"CANNOT UPDATE USER INFORMATION: \n-->" + e.getMessage());
			return 0;
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
	}

	/**
	 * Add new user
	 * 
	 * @param user
	 * @return
	 */
	public static int insert(User user) {
		int result = Constant.CREATE_COMPONENT_SUCCESS;
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = MySQLConnector.popConnection();
			ps = conn.prepareStatement(
					"insert into USER(username, pass, fullname"
							+ ", email, taikhoangiaodich, sodutienmat, tienungtruoc, soducothegiaodich,"
							+ " khoiluongcothemua, tientreogoc) values(?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2,
					HashUtil.hash(user.getPassword(), HashUtil.SHA_256));
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhoneNumber());
			ps.setInt(6, user.getSoDuTienMat());
			ps.setInt(7, user.getTienUngTruoc());
			ps.setInt(8, user.getSoDuCoTheGiaoDich());
			ps.setInt(9, user.getKhoiLuongCoTheMua());
			ps.setInt(10, user.getTienTreoGoc());
			ps.executeUpdate();

			ps.close();
		} catch (Exception ex) {
			LOGGER.error("INSERT USER FAILE:\n--> " + ex.getMessage());
			result = Constant.CREATE_COMPONENT_FAILED;
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}

		return result;
	}

	/**
	 * Get user from username and password
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public static User getUser(String userName, String password) {
		Connection conn = MySQLConnector.popConnection();
		User user = null;
		PreparedStatement ps = null;
		if (conn == null) {
			errorCode = Constant.CANNOT_CONNECT_DB;
			return null;
		}
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM USER WHERE username = ? AND pass = ?");
			ps.setString(1, userName);
			ps.setString(2, HashUtil.hash(password, HashUtil.SHA_256));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("fullname");
				String email = rs.getString("email");
				String taikhoangiaodich = rs.getString("taikhoangiaodich");
				int soDuTienMat = rs.getInt("sodutienmat");
				int tienUngTruoc = rs.getInt("tienungtruoc");
				int soDuCoTheGiaoDich = rs.getInt("soducothegiaodich");
				int khoiLuongCoTheMua = rs.getInt("khoiluongcothemua");
				int tienTreoGoc = rs.getInt("tientreogoc");

				user = new User(id, userName, password, fullName, email,
						taikhoangiaodich, soDuTienMat, tienUngTruoc,
						soDuCoTheGiaoDich, khoiLuongCoTheMua, tienTreoGoc);
				
				List<Certificate> listCert = CertificateDAO.listCertificate(id);
				user.setListCertificate(listCert);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
		return user;
	}

	/**
	 * Get user from user id
	 * 
	 * @param id
	 * @return
	 */
	public static User getUser(int id) {
		Connection conn = MySQLConnector.popConnection();
		User user = null;
		PreparedStatement ps = null;
		if (conn == null) {
			errorCode = Constant.CANNOT_CONNECT_DB;
			return null;
		}
		try {
			ps = conn.prepareStatement("SELECT * FROM USER WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String userName = rs.getString("username");
				String password = rs.getString("pass");
				String fullName = rs.getString("fullname");
				String email = rs.getString("email");
				String taikhoangiaodich = rs.getString("taikhoangiaodich");
				int soDuTienMat = rs.getInt("sodutienmat");
				int tienUngTruoc = rs.getInt("tienungtruoc");
				int soDuCoTheGiaoDich = rs.getInt("soducothegiaodich");
				int khoiLuongCoTheMua = rs.getInt("khoiluongcothemua");
				int tienTreoGoc = rs.getInt("tientreogoc");

				user = new User(id, userName, password, fullName, email,
						taikhoangiaodich, soDuTienMat, tienUngTruoc,
						soDuCoTheGiaoDich, khoiLuongCoTheMua, tienTreoGoc);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}
		return user;
	}

	/**
	 * List all monitor user
	 * 
	 * @return
	 */
	public static ArrayList<User> listUser() {
		ArrayList<User> tmp = new ArrayList<User>();
		Connection conn = MySQLConnector.popConnection();
		User user = null;
		PreparedStatement ps = null;
		if (conn == null) {
			errorCode = Constant.CANNOT_CONNECT_DB;
			return null;
		}
		try {
			ps = conn.prepareStatement("SELECT * FROM USER WHERE level = 1");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("username");
				String password = rs.getString("pass");
				String fullName = rs.getString("fullname");
				String email = rs.getString("email");
				String taikhoangiaodich = rs.getString("taikhoangiaodich");
				int soDuTienMat = rs.getInt("sodutienmat");
				int tienUngTruoc = rs.getInt("tienungtruoc");
				int soDuCoTheGiaoDich = rs.getInt("soducothegiaodich");
				int khoiLuongCoTheMua = rs.getInt("khoiluongcothemua");
				int tienTreoGoc = rs.getInt("tientreogoc");

				user = new User(id, userName, password, fullName, email,
						taikhoangiaodich, soDuTienMat, tienUngTruoc,
						soDuCoTheGiaoDich, khoiLuongCoTheMua, tienTreoGoc);
				tmp.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MySQLConnector.closeStatement(ps);
			MySQLConnector.putConnection(conn);
		}

		return tmp;
	}
}
