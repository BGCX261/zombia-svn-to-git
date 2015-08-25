package com.buaa.project.client.dd;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BeanFarenDTO implements IsSerializable {

	public String userid;

	public String zhuguan;

	public String address;

	public String suozaiaddress;

	public String xingzhi;

	public String id;

	public String id_1;

	public String jigouid;

	public String xzzgbm;

	public String zipcode;

	public String dwwz;

	public String dwsx;

	public String hymc;

	public String xkmc;
	
	public String username;

	public String getUsername() {
		return this.username;
	}
	
	public String getUserid() {
		return this.userid;
	}

	public String getZhuguan() {
		return this.zhuguan;
	}

	public String getSuozaiaddress() {
		return this.suozaiaddress;
	}

	public String getXingzhi() {
		return this.xingzhi;
	}

	public String getId() {
		return this.id;
	}

	public String getId_1() {
		return this.id_1;
	}

	public String getJigouid() {
		return this.jigouid;
	}

	public String getXzzgbm() {
		return this.xzzgbm;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public String getAddrress() {
		return this.address;
	}

	public String getDwwz() {
		return this.dwwz;
	}

	public String getDwsx() {
		return this.dwsx;
	}

	public String getHymc() {
		return this.hymc;
	}

	public String getXkmc() {
		return this.xkmc;
	}

	public void setUserid(String userid) {

		this.userid = userid;
	}

	public void setUsername(String username) {

		this.username = username;
	}
	public void setZhuguan(String zhuguan) {

		this.zhuguan = zhuguan;
	}

	public void setAddress(String address) {

		this.address = address;
	}

	public void setXingzhi(String xingzhi) {

		this.xingzhi = xingzhi;
	}

	public void setId(String id) {

		this.id = id;
	}

	public void setId_1(String id_1) {

		this.id_1 = id_1;
	}

	public void setJigouid(String jigouid) {

		this.jigouid = jigouid;
	}

	public void setXzzgbm(String xzzgbm) {

		this.xzzgbm = xzzgbm;
	}

	public void setZipcode(String zipcode) {

		this.zipcode = zipcode;
	}

	public void setDwwz(String dwwz) {

		this.dwwz = dwwz;
	}

	public void setDwsz(String dwsx) {

		this.dwsx = dwsx;
	}

	public void setHymc(String hymc) {

		this.hymc = hymc;
	}

	public void setXkmc(String xkmc) {

		this.xkmc = xkmc;
	}

	public void setSuoziaddress(String suozaiaddress) {

		this.suozaiaddress=suozaiaddress;
	}
	public Object[] toObjectArray() {
		return new Object[] { 
				username

		};
	}
}
