package com.ad.model;

import java.sql.*;

public class AdVO {
	private Integer ad_no;
	private Integer store_no;
	private byte[] ad_images;
	private Date ad_date;
	private Date ad_date_ed;
	
	public Integer getAd_no() {
		return ad_no;
	}
	public void setAd_no(Integer ad_no) {
		this.ad_no = ad_no;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}
	public byte[] getAd_images() {
		return ad_images;
	}
	public void setAd_images(byte[] ad_images) {
		this.ad_images = ad_images;
	}
	public Date getAd_date() {
		return ad_date;
	}
	public void setAd_date(Date ad_date) {
		this.ad_date = ad_date;
	}
	public Date getAd_date_ed() {
		return ad_date_ed;
	}
	public void setAd_date_ed(Date ad_date_ed) {
		this.ad_date_ed = ad_date_ed;
	}
}
