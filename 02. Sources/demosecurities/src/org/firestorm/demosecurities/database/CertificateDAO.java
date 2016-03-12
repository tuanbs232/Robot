package org.firestorm.demosecurities.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CertificateDAO {
	public static ArrayList<Certificate> listCertificate(int userId) {
		ArrayList<Certificate> tmp = new ArrayList<Certificate>();
		Connection conn = MySQLConnector.popConnection();
		PreparedStatement ps = null;
		if (conn == null) {
			return null;
		}
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM CERTIFICATE WHERE userid = ?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String subject = rs.getString("subject");
				String serialNumber = rs.getString("serialnumber");
				long notBefore = rs.getLong("notbefore");
				Date notBeforeDate = new Date(notBefore);
				boolean notYetValid = !notBeforeDate.before(new Date());

				long notAfter = rs.getLong("notafter");
				Date notAfterDate = new Date(notAfter);
				boolean expired = !notAfterDate.before(new Date());

				Certificate cert = new Certificate(id, subject, serialNumber,
						expired, notYetValid);
				tmp.add(cert);
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
