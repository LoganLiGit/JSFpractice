package com.History.model;

import java.sql.Timestamp;

public class HistoryVO {	
	//紀錄編號
	private Integer history_no;
	//管理者編號
	private Integer admin_no;
	//會員編號
	private Integer mem_no;
	//店家編號
	private Integer store_no;
	//修改時間
	private Timestamp Update_time;
	//修改內容
	private String history_date;
	//登入時間
	private Timestamp login_in_time;
	//登出時間
	private Timestamp login_out_time;
	//紀錄狀態
	private Integer history_state;
	//登入IP
	private String login_ip;
	
	
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public Integer getHistory_no() {
		return history_no;
	}
	public void setHistory_no(Integer history_no) {
		this.history_no = history_no;
	}
	public Integer getAdmin_no() {
		return admin_no;
	}
	public void setAdmin_no(Integer admin_no) {
		this.admin_no = admin_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}
	public Timestamp getUpdate_time() {
		return Update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		Update_time = update_time;
	}
	public String getHistory_date() {
		return history_date;
	}
	public void setHistory_date(String history_date) {
		this.history_date = history_date;
	}
	public Timestamp getLogin_in_time() {
		return login_in_time;
	}
	public void setLogin_in_time(Timestamp login_in_time) {
		this.login_in_time = login_in_time;
	}
	public Timestamp getLogin_out_time() {
		return login_out_time;
	}
	public void setLogin_out_time(Timestamp login_out_time) {
		this.login_out_time = login_out_time;
	}
	public Integer getHistory_state() {
		return history_state;
	}
	public void setHistory_state(Integer history_state) {
		this.history_state = history_state;
	}
}
