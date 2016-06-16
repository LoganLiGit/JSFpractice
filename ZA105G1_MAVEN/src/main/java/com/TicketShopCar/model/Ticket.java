package com.TicketShopCar.model;

public class Ticket implements java.io.Serializable {
	
	public Ticket(){
		tickets_type_no = 0;
		tickets_type_name = "A";
		store_name = "A";
		price = 0;
		quantity = 0;
	}
	
	private int tickets_type_no; //團購劵編號
	private String tickets_type_name; //團購劵名稱
	private String store_name; //團購劵店名
	private int quantity;	//團購劵購買數量
	private int price;	//團購劵價格
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	

	public int getTickets_type_no() {
		return tickets_type_no;
	}
	public void setTickets_type_no(int tickets_type_no) {
		this.tickets_type_no = tickets_type_no;
	}
	public String getTickets_type_name() {
		return tickets_type_name;
	}
	public void setTickets_type_name(String tickets_type_name) {
		this.tickets_type_name = tickets_type_name;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
