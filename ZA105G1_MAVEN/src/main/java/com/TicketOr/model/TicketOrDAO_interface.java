package com.TicketOr.model;

import java.util.*;
//團購券訂單資訊TicketOr
public interface TicketOrDAO_interface {
	public void insert(TicketOrVO ticketOrVO);
	public void update(TicketOrVO ticketOrVO);
	public void delete(Integer order_no);
	public TicketOrVO findByPrimaryKey(Integer order_no);
	public List<TicketOrVO> getAll();
	//取得該使用者的所有訂單 
	public List<TicketOrVO> getUser(Integer mem_no);
	// 購買時 交易的主鍵Table 訂單資訊 1筆		list陣列資料     /會員帳號      /計算的總金額        /折扣
	public TicketOrVO getnewtickettype_order(Vector list,int mem_no,int sal,double discount);
}
