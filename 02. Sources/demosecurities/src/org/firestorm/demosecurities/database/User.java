package org.firestorm.demosecurities.database;

import java.util.List;

public class User {
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private String phoneNumber;
	private int soDuTienMat;
	private int tienUngTruoc;
	private int soDuCoTheGiaoDich;
	private int khoiLuongCoTheMua;
	private int tienTreoGoc;
	private List<Certificate> listCertificate;

	public User() {
	}

	public User(int id, String userName, String password, String fullName,
			String email, String phoneNumber, int soDu, int tienUng, int soDuCo,
			int khoiLuong, int tienTreo) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.soDuTienMat = soDu;
		this.tienUngTruoc = tienUng;
		this.soDuCoTheGiaoDich = soDuCo;
		this.khoiLuongCoTheMua = khoiLuong;
		this.tienTreoGoc = tienTreo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSoDuTienMat() {
		return soDuTienMat;
	}

	public void setSoDuTienMat(int soDuTienMat) {
		this.soDuTienMat = soDuTienMat;
	}

	public int getTienUngTruoc() {
		return tienUngTruoc;
	}

	public void setTienUngTruoc(int tienUngTruoc) {
		this.tienUngTruoc = tienUngTruoc;
	}

	public int getSoDuCoTheGiaoDich() {
		return soDuCoTheGiaoDich;
	}

	public void setSoDuCoTheGiaoDich(int soDuCoTheGiaoDich) {
		this.soDuCoTheGiaoDich = soDuCoTheGiaoDich;
	}

	public int getKhoiLuongCoTheMua() {
		return khoiLuongCoTheMua;
	}

	public void setKhoiLuongCoTheMua(int khoiLuongCoTheMua) {
		this.khoiLuongCoTheMua = khoiLuongCoTheMua;
	}

	public int getTienTreoGoc() {
		return tienTreoGoc;
	}

	public void setTienTreoGoc(int tienTreoGoc) {
		this.tienTreoGoc = tienTreoGoc;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Certificate> getListCertificate() {
		return listCertificate;
	}

	public void setListCertificate(List<Certificate> listCertificate) {
		this.listCertificate = listCertificate;
	}
}
