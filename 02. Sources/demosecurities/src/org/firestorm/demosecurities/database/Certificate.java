package org.firestorm.demosecurities.database;

public class Certificate {
	private int id;
	private String subject;
	private String serialNumber;
	private boolean expired;
	private boolean notYetValid;

	public Certificate() {
	}

	public Certificate(int id, String subject, String serial, boolean expired,
			boolean notYetValid) {
		this.id = id;
		this.subject = subject;
		this.serialNumber = serial;
		this.expired = expired;
		this.notYetValid = notYetValid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isNotYetValid() {
		return notYetValid;
	}

	public void setNotYetValid(boolean notYetValid) {
		this.notYetValid = notYetValid;
	}
}
