package com.OrderDe.model;
//團購券訂單明細order_details

public class OrderDeVO implements java.io.Serializable {
	private Integer order_no ;
	private String tickets_no;
	private Integer order_num;
	private Integer order_value;
	private Integer order_status;
	
	public String getTickets_no() {
		return tickets_no;
	}
	public void setTickets_no(String tickets_no) {
		this.tickets_no = tickets_no;
	}	
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}
	public Integer getOrder_value() {
		return order_value;
	}
	public void setOrder_value(Integer order_value) {
		this.order_value = order_value;
	}
	public Integer getOrder_status() {
		return order_status;
	}
	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
	//團購券訂單明細
}
