package org.firestorm.demosecurities.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class MySQLConnector {
	// Pool store free connection
	private static LinkedList<Object> pool = new LinkedList<Object>();
	// Max connection
	public static int MAX_CONNECTIONS = 10;
	// Number current connection
	public static int CURRENT_CONNECTION = 0;
	// Database name
	private static final String DATABASE_NAME = "bkavcoreca";
	// Database driver class
	private static final String DATABASE_DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private static final String DATABASE_SERVER_URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

	private static final String CREATE_DATABASE = "CREATE DATABASE "
			+ DATABASE_NAME + ";";

	private static final String CREATE_USER_TABLE = "CREATE TABLE USER("
			+ "id int PRIMARY KEY AUTO_INCREMENT,"
			+ "username VARCHAR(32) NOT NULL," + "pass VARCHAR(512) NOT NULL,"
			+ "fullname VARCHAR(32)," + "email VARCHAR(32),"
			+ "taikhoangiaodich VARCHAR(32)," + "sodutienmat int,"
			+ "tienungtruoc int," + "soducothegiaodich int,"
			+ "khoiluongcothemua int," + "tientreogoc int);";

	private static final String CREATE_TRANSACTIONLOG_TABLE = "CREATE TABLE transactionlog("
			+ "id int PRIMARY KEY AUTO_INCREMENT," + "userid int NOT NULL," + "transactionid int," + "signingtime BIGINT,"
			+ "transactiontype varchar(8)," + "stockname VARCHAR(8),"
			+ "weight int," + "price int," + "status VARCHAR(32),"
			+ "transactionlogfileurl VARCHAR(256), client int);";
	
	private static final String CREATE_CERTIFICATE_TABLE = "CREATE TABLE certificate("
			+ "id int PRIMARY KEY NOT NULL AUTO_INCREMENT,"
			+ "userid int NOT NULL,"
			+ "subject VARCHAR(255),"
			+ "serialnumber VARCHAR(32),"
			+ "notbefore long,"
			+ "notafter long);";

	// Logger log4j
	static final Logger LOGGER = Logger.getLogger(MySQLConnector.class);

	public static void main(String[] args) {
		System.out.println(popConnection());
	}

	/**
	 * Pop a connection from pool if pool size > 0 or return new connection
	 * 
	 * @return
	 */
	public static Connection popConnection() {
		Connection connection = null;
		if (pool.size() > 0) {
			connection = (Connection) pool.get(0);

			pool.remove(0);
			CURRENT_CONNECTION++;

			return connection;
		} else if (CURRENT_CONNECTION < MAX_CONNECTIONS) {
			connection = createConnection();
			if (connection != null) {
				CURRENT_CONNECTION++;
			}
		} else {
			LOGGER.error("MAX CONNECTION. 50 CONNECTION NOW");
		}

		return connection;
		// return createConnection();
	}

	/***
	 * Put connection to pool
	 * 
	 * @param conn
	 */
	public static void putConnection(Connection conn) {
		try {
			if (conn != null) {
				MySQLConnector.CURRENT_CONNECTION--;
				pool.add(conn);
			}
		} catch (Exception ex) {
			LOGGER.error("CANNOT PUT CONNECTION TO POOL: " + "\n-->"
					+ ex.getMessage());
		}
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// LOGGER.error("CANNOT CLOSE CONNECTION." + e.getMessage());
		// }
	}

	/***
	 * Truncate pool
	 */
	public static void release() {
		synchronized (pool) {
			for (Iterator<?> it = pool.iterator(); it.hasNext();) {
				Connection conn = (Connection) it.next();
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(
							"CANNOT CLOSE CONNECTION: \n-->" + e.getMessage());
				}
			}
			pool.clear();
		}
	}

	/***
	 * Close PreparedStatement
	 * 
	 * @param preparedStatement
	 */
	public static void closeStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error("CANNOT CLOSE STATEMENT: " + preparedStatement
					+ "\n-->" + e.getMessage());
		}
	}

	/***
	 * Close Statement
	 * 
	 * @param preparedStatement
	 */
	public static void closeStatement(Statement preparedStatement) {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException e) {
			LOGGER.error("CANNOT CLOSE STATEMENT: \n-->" + preparedStatement
					+ "\n-->" + e.getMessage());
		}
	}

	/***
	 * Create new connection not use pool
	 * 
	 * @return
	 */
	private static Connection createConnection() {
		if (!isDatabaseExist()) {
			createDB();
		}

		Connection connection = null;
		try {
			Class.forName(DATABASE_DRIVER_CLASS);
			connection = DriverManager.getConnection(
					DATABASE_SERVER_URL + DATABASE_NAME, USERNAME, PASSWORD);

			return connection;
		} catch (Exception e) {
			LOGGER.error(
					"CANNOT CREATE CONNECTION: " + "\n-->" + e.getMessage());
			return null;
		}
	}

	/***
	 * Check database exist or not
	 * 
	 * @return
	 */
	private static boolean isDatabaseExist() {
		try {
			Class.forName(DATABASE_DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			LOGGER.error("MYSQL DRIVER NOT FOUND.");
			return false;
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DATABASE_SERVER_URL, USERNAME,
					PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("CANNOT CREATE CONNECTION TO MYSQL SERVER");
			return false;
		}

		ResultSet resultSet;
		try {
			resultSet = conn.getMetaData().getCatalogs();

			while (resultSet.next()) {

				String databaseName = resultSet.getString(1);
				if (databaseName.equals(DATABASE_NAME)) {
					return true;
				}
			}

			resultSet.close();
		} catch (SQLException e) {
			LOGGER.error("SQL EXCEPTION " + e.getMessage());
			return false;
		}

		return false;
	}

	/***
	 * Copy sample database file from resource to created folder
	 * 
	 * @return
	 */
	private static boolean createDB() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DATABASE_SERVER_URL, USERNAME,
					PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("CANNOT CREATE CONNECTION TO MYSQL SERVER");
			return false;
		}
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(CREATE_DATABASE);
				if (!stmt.execute()) {
					createUserTable();
					createCertificateTable();
					createTransactionLogTable();
					UserDAO.insertDefaultUser();
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				LOGGER.error("SQL EXCEPTION " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean createUserTable() {
		Connection conn = popConnection();

		if (conn != null) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement(CREATE_USER_TABLE);
				return !stmt.execute();
			} catch (SQLException e) {
				LOGGER.error("SQL EXCEPTION " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean createTransactionLogTable() {
		Connection conn = popConnection();

		if (conn != null) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement(CREATE_TRANSACTIONLOG_TABLE);
				return !stmt.execute();
			} catch (SQLException e) {
				LOGGER.error("SQL EXCEPTION " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	private static boolean createCertificateTable() {
		Connection conn = popConnection();

		if (conn != null) {
			try {
				PreparedStatement stmt = conn
						.prepareStatement(CREATE_CERTIFICATE_TABLE);
				return !stmt.execute();
			} catch (SQLException e) {
				LOGGER.error("SQL EXCEPTION " + e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}
}
