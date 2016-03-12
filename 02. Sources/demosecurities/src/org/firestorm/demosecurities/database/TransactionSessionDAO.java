package org.firestorm.demosecurities.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionSessionDAO {
	public static List<TransactionSession> formatTransactionLogs(
			List<TransactionLog> logs) {
		List<TransactionSession> result = new ArrayList<>();
		if (logs == null || logs.size() == 0) {

			return result;
		}

		while (logs.size() > 0) {
			TransactionLog log = logs.get(0);
			Date signingTime = log.getSigningTime();

			int count = 0;
			TransactionSession transactionSession = new TransactionSession();
			transactionSession.setSigningTime(signingTime);
			transactionSession.setSignedFileUrl(log.getTransactionLogFileUrl());

			Date time = log.getSigningTime();
			List<TransactionLog> tranLog = new ArrayList<>();
			while (time.compareTo(signingTime) == 0) {
				count++;
				transactionSession.setCount(count);
				tranLog.add(log);
				logs.remove(0);
				if (logs.size() == 0) {
					break;
				} else {
					log = logs.get(0);
					time = log.getSigningTime();
				}
			}
			transactionSession.setCount(count);
			transactionSession.setLogs(tranLog);
			result.add(transactionSession);
		}
		return result;
	}
}
