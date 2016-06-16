package com.TicketOr.model;

import java.sql.Date;
//團購券訂單資訊TicketOr
public class TicketOrVO implements java.io.Serializable {
	private Integer order_no;		//訂單編號
	private Date order_date;		//訂單日期
	private Integer mem_no;			//購買編號
	private Integer order_money;	//團購券購買金額
	private Integer order_status;	//訂單狀態
	
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getOrder_money() {
		return order_money;
	}
	public void setOrder_money(Integer order_money) {
		this.order_money = order_money;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	
}
