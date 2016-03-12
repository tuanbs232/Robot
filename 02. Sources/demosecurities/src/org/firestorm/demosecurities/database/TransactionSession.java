package org.firestorm.demosecurities.database;

import java.util.Date;
import java.util.List;

public class TransactionSession {
	private int count;
	private Date signingTime;
	private String signedFileUrl;
	private List<TransactionLog> logs;

	public TransactionSession() {
	}

	public TransactionSession(int count, Date signingTime, String signedFileUrl,
			List<TransactionLog> logs) {
		this.count = count;
		this.signedFileUrl = signedFileUrl;
		this.signingTime = signingTime;
		this.logs = logs;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(Date signingTime) {
		this.signingTime = signingTime;
	}

	public String getSignedFileUrl() {
		return signedFileUrl;
	}

	public void setSignedFileUrl(String signedFileUrl) {
		this.signedFileUrl = signedFileUrl;
	}

	public List<TransactionLog> getLogs() {
		return logs;
	}

	public void setLogs(List<TransactionLog> logs) {
		this.logs = logs;
	}
}
