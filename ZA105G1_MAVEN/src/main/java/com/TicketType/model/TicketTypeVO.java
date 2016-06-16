package com.TicketType.model;
//團購券總類ticket_type

import java.sql.Date;
import java.sql.Timestamp;;

public class TicketTypeVO {
	private Integer tickets_type_no;
	//團購券種類編號
	private String tickets_type_name;
	//團購券名稱
	private Date upper_date;
	//上架時間
	private Date lower_date;
	//下架時間
	private Timestamp application_date;
	//申請時間	
	private Integer tickets_total;
	//團購劵總數量
	private Integer tickets_quantity;
	//剩餘數量
	private Integer tickets_price;
	//團購劵單價格
	private Integer tickets_state;
	//團購劵狀態
	private Integer store_no;
	//店家編號
	private String tickets_ex;
	//團購劵說明
	private byte[] tickets_image;
	//團購劵圖片

	public Integer getTickets_type_no() {
		return tickets_type_no;
	}
	public void setTickets_type_no(Integer tickets_type_no) {
		this.tickets_type_no = tickets_type_no;
	}
	public String getTickets_type_name() {
		return tickets_type_name;
	}
	public void setTickets_type_name(String tickets_type_name) {
		this.tickets_type_name = tickets_type_name;
	}
	public Date getUpper_date() {
		return upper_date;
	}
	public void setUpper_date(Date upper_date) {
		this.upper_date = upper_date;
	}
	public Date getLower_date() {
		return lower_date;
	}
	public void setLower_date(Date lower_date) {
		this.lower_date = lower_date;
	}
	public Integer getTickets_total() {
		return tickets_total;
	}
	public void setTickets_total(Integer tickets_total) {
		this.tickets_total = tickets_total;
	}
	public Integer getTickets_quantity() {
		return tickets_quantity;
	}
	public void setTickets_quantity(Integer tickets_quantity) {
		this.tickets_quantity = tickets_quantity;
	}
	public Integer getTickets_price() {
		return tickets_price;
	}
	public void setTickets_price(Integer tickets_price) {
		this.tickets_price = tickets_price;
	}
	public Integer getTickets_state() {
		return tickets_state;
	}
	public void setTickets_state(Integer tickets_state) {
		this.tickets_state = tickets_state;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}
	public String getTickets_ex() {
		return tickets_ex;
	}
	public void setTickets_ex(String tickets_ex) {
		this.tickets_ex = tickets_ex;
	}
	public byte[] getTickets_image() {
		return tickets_image;
	}
	public void setTickets_image(byte[] tickets_image) {
		this.tickets_image = tickets_image;
	}
	public Timestamp getApplication_date() {
		return application_date;
	}
	public void setApplication_date(Timestamp application_date) {
		this.application_date = application_date;
	}
}
