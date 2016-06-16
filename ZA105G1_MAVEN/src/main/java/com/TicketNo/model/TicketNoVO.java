package com.TicketNo.model;
//團購券序號明細 tickets_no_detail
public class TicketNoVO implements java.io.Serializable {
	private String tickets_no;
	private Integer tickets_type_no;
	private Integer tickets_no_status;
	
	public String getTickets_no() {
		return tickets_no;
	}
	public void setTickets_no(String tickets_no) {
		this.tickets_no = tickets_no;
	}
	public Integer getTickets_type_no() {
		return tickets_type_no;
	}
	public void setTickets_type_no(Integer tickets_type_no) {
		this.tickets_type_no = tickets_type_no;
	}
	public Integer getTickets_no_status() {
		return tickets_no_status;
	}
	public void setTickets_no_status(Integer tickets_no_status) {
		this.tickets_no_status = tickets_no_status;
	}
}
