package org.firestorm.demosecurities.database;

import java.util.Date;

public class TransactionLog {
	private int idUser;
	private int transactionId;
	private Date signingTime;
	private String transactionType;
	private String stockName;
	private int weight;
	private int price;
	private String status;
	private String transactionLogFileUrl;
	private int client;

	public TransactionLog() {
	}

	public TransactionLog(int idUser, int tranId, Date signingTime, String type,
			String name, int weight, int price, String status, String fileUrl, int client) {
		this.idUser = idUser;
		this.transactionId = tranId;
		this.signingTime = signingTime;
		this.transactionType = type;
		this.stockName = name;
		this.weight = weight;
		this.price = price;
		this.status = status;
		this.transactionLogFileUrl = fileUrl;
		this.setClient(client);
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionLogFileUrl() {
		return transactionLogFileUrl;
	}

	public void setTransactionLogFileUrl(String transactionLogFileUrl) {
		this.transactionLogFileUrl = transactionLogFileUrl;
	}

	public Date getSigningTime() {
		return signingTime;
	}

	public void setSigningTime(Date signingTime) {
		this.signingTime = signingTime;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}
}
