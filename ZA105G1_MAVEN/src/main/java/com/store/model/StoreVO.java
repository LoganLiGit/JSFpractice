package com.store.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;



import com.group.table.model.GroupTableVO;
import com.store.pic.model.Store_picVO;

public class StoreVO implements java.io.Serializable {
	private Integer store_no;
	private String store_account;
	private String store_password;
	private Integer store_state;
	private String store_name;
	private Date store_regist_date;
	private String store_zipcode;
	private String store_city;
	private String store_district;
	private String store_address;
	private String store_phone;
	private String store_type;
	private Double store_score;
	private Integer store_balance;
	private String store_cell_registcode;
	private Integer store_violation;
	private String manager_name;
	private Integer manager_gender;
	private String manager_email;
	private String manager_id;
	private String manager_cellphone;
	private Integer manager_credit_num;
	private Integer manager_credit_expyear;
	private Integer manager_credit_expmonth;
	private Integer manager_credit_secure_num;
	private Integer tickts_limits;
	private Double store_longitude; 
	private Double store_latitude;
	private String store_introduction;
	private Integer clicks;
	private Integer store_articles;
	private Integer store_scopenum;
	private Integer store_pocketnum;

	private Set<Store_picVO> store_pics = new HashSet<Store_picVO>();
	private Set<GroupTableVO> groupTables = new HashSet<GroupTableVO>();
	public Integer getStore_pocketnum() {
		return store_pocketnum;
	}

	public void setStore_pocketnum(Integer store_pocketnum) {
		this.store_pocketnum = store_pocketnum;
	}

	
	public Set<GroupTableVO> getGroupTables() {
		return groupTables;
	}

	public void setGroupTables(Set<GroupTableVO> groupTables) {
		this.groupTables = groupTables;
	}

	public String getStore_introduction() {
		return store_introduction;
	}

	public void setStore_introduction(String store_introduction) {
		this.store_introduction = store_introduction;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public Integer getStore_articles() {
		return store_articles;
	}

	public void setStore_articles(Integer store_articles) {
		this.store_articles = store_articles;
	}

	public Integer getStore_scopenum() {
		return store_scopenum;
	}

	public void setStore_scopenum(Integer store_scopenum) {
		this.store_scopenum = store_scopenum;
	}

	
	public Set<Store_picVO> getStore_pics() {
		return store_pics;
	}

	public void setStore_pics(Set<Store_picVO> store_pics) {
		this.store_pics = store_pics;
	}


	public Integer getStore_no() {
		return store_no;
	}

	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}

	public String getStore_account() {
		return store_account;
	}

	public void setStore_account(String store_account) {
		this.store_account = store_account;
	}

	public String getStore_password() {
		return store_password;
	}

	public void setStore_password(String store_password) {
		this.store_password = store_password;
	}

	public Integer getStore_state() {
		return store_state;
	}

	public void setStore_state(Integer store_state) {
		this.store_state = store_state;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public Date getStore_regist_date() {
		return store_regist_date;
	}

	public void setStore_regist_date(Date store_regist_date) {
		this.store_regist_date = store_regist_date;
	}

	public String getStore_zipcode() {
		return store_zipcode;
	}

	public void setStore_zipcode(String store_zipcode) {
		this.store_zipcode = store_zipcode;
	}

	public String getStore_city() {
		return store_city;
	}

	public void setStore_city(String store_city) {
		this.store_city = store_city;
	}

	public String getStore_district() {
		return store_district;
	}

	public void setStore_district(String store_district) {
		this.store_district = store_district;
	}

	public String getStore_address() {
		return store_address;
	}

	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}

	public String getStore_phone() {
		return store_phone;
	}

	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}

	public String getStore_type() {
		return store_type;
	}

	public void setStore_type(String store_type) {
		this.store_type = store_type;
	}

	public Double getStore_score() {
		return store_score;
	}

	public void setStore_score(Double store_score) {
		this.store_score = store_score;
	}

	public Integer getStore_balance() {
		return store_balance;
	}

	public void setStore_balance(Integer store_balance) {
		this.store_balance = store_balance;
	}

	public String getStore_cell_registcode() {
		return store_cell_registcode;
	}

	public void setStore_cell_registcode(String store_cell_registcode) {
		this.store_cell_registcode = store_cell_registcode;
	}

	public Integer getStore_violation() {
		return store_violation;
	}

	public void setStore_violation(Integer store_violation) {
		this.store_violation = store_violation;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	public Integer getManager_gender() {
		return manager_gender;
	}

	public void setManager_gender(Integer manager_gender) {
		this.manager_gender = manager_gender;
	}

	public String getManager_email() {
		return manager_email;
	}

	public void setManager_email(String manager_email) {
		this.manager_email = manager_email;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getManager_cellphone() {
		return manager_cellphone;
	}

	public void setManager_cellphone(String manager_cellphone) {
		this.manager_cellphone = manager_cellphone;
	}

	public Integer getManager_credit_num() {
		return manager_credit_num;
	}

	public void setManager_credit_num(Integer manager_credit_num) {
		this.manager_credit_num = manager_credit_num;
	}

	public Integer getManager_credit_expyear() {
		return manager_credit_expyear;
	}

	public void setManager_credit_expyear(Integer manager_credit_expyear) {
		this.manager_credit_expyear = manager_credit_expyear;
	}

	public Integer getManager_credit_expmonth() {
		return manager_credit_expmonth;
	}

	public void setManager_credit_expmonth(Integer manager_credit_expmonth) {
		this.manager_credit_expmonth = manager_credit_expmonth;
	}

	public Integer getManager_credit_secure_num() {
		return manager_credit_secure_num;
	}

	public void setManager_credit_secure_num(Integer manager_credit_secure_num) {
		this.manager_credit_secure_num = manager_credit_secure_num;
	}

	public Integer getTickts_limits() {
		return tickts_limits;
	}

	public void setTickts_limits(Integer tickts_limits) {
		this.tickts_limits = tickts_limits;
	}
	public Double getStore_longitude() {
		return store_longitude;
	}

	public void setStore_longitude(Double store_longitude) {
		this.store_longitude = store_longitude;
	}

	public Double getStore_latitude() {
		return store_latitude;
	}

	public void setStore_latitude(Double store_latitude) {
		this.store_latitude = store_latitude;
	}
}
