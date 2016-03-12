package org.firestorm.demosecurities.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

public class TransactionLogDAO {
	// Loger log4j
	static final Logger LOGGER = Logger.getLogger(TransactionLogDAO.class);

	/**
	 * Get all user log record
	 * 
	 * @param idUser
	 * @param date
	 * @return
	 */
	public static ArrayList<TransactionLog> listUserLog(int idUser, int index,
			int max) {
		ArrayList<TransactionLog> result = new ArrayList<TransactionLog>();

		Connection connection = MySQLConnector.popConnection();
		if (connection != null) {
			String sql = "SELECT * FROM transactionlog WHERE userid = ? ORDER BY id DESC";

			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, idUser);

				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next()) {
					TransactionLog userLog = new TransactionLog();
					userLog.setTransactionId(resultSet.getInt("transactionid"));
					userLog.setSigningTime(
							new Date(resultSet.getLong("signingtime")));
					userLog.setTransactionType(
							resultSet.getString("transactiontype"));
					userLog.setStockName(resultSet.getString("stockname"));
					userLog.setWeight(resultSet.getInt("weight"));
					userLog.setPrice(resultSet.getInt("price"));
					userLog.setStatus(resultSet.getString("status"));
					userLog.setTransactionLogFileUrl(
							resultSet.getString("transactionlogfileurl"));
					userLog.setClient(resultSet.getInt("client"));

					result.add(userLog);
				}
			} catch (SQLException e) {
				LOGGER.error("CANNOT GET LIST USER LOG FOR USER WITH ID "
						+ idUser + "\n-->" + e.getMessage());
			} finally {
				MySQLConnector.closeStatement(statement);
				MySQLConnector.putConnection(connection);
			}
		}

		return result;
	}

	/**
	 * Add new log record
	 * 
	 * @param userLog
	 * @return
	 */
	public static boolean insertUserLog(TransactionLog userLog) {
		boolean result = false;
		Connection connection = MySQLConnector.popConnection();
		if (connection != null) {
			String sql = "INSERT INTO transactionlog(userid, transactionid, "
					+ "signingtime, transactiontype, stockname, weight, price,"
					+ " status, transactionlogfileurl, client) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);

				 statement.setInt(1, userLog.getIdUser());
				 statement.setInt(2, userLog.getTransactionId());
				 statement.setLong(3, userLog.getSigningTime().getTime());
				 statement.setString(4, userLog.getTransactionType());
				 statement.setString(5, userLog.getStockName());
				 statement.setInt(6, userLog.getWeight());
				 statement.setInt(7, userLog.getPrice());
				 statement.setString(8, userLog.getStatus());
				 statement.setString(9, userLog.getTransactionLogFileUrl());
				 statement.setInt(10, userLog.getClient());

				result = statement.execute();
			} catch (SQLException e) {
				LOGGER.error("CANNOT INSERT USER LOG " + e.getMessage());
			} finally {
				MySQLConnector.closeStatement(statement);
				MySQLConnector.putConnection(connection);
			}
		}

		return result;
	}

	/**
	 * Remove all user log record
	 * 
	 * @param iduser
	 */
	public static void deleteUserLog(int iduser) {
		Connection connection = MySQLConnector.popConnection();
		if (connection != null) {
			String sql = "DELETE FROM transactionlog WHERE userid = ?";
			PreparedStatement statement = null;
			try {
				statement = connection.prepareStatement(sql);

				statement.setInt(1, iduser);

				statement.execute();
			} catch (SQLException e) {
				LOGGER.error("CANNOT DELETE USER LOG");
			} finally {
				MySQLConnector.closeStatement(statement);
				MySQLConnector.putConnection(connection);
			}
		}
	}
}
