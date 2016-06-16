package com.TicketNo.model;

import java.util.*;
//團購券序號明細 tickets_no_detail

public interface TicketNoDAO_interface {	
	public void insert(TicketNoVO ticketNoVo);
	public void update(TicketNoVO ticketNoVo);
	public void delete(String  tickets_no);
	public TicketNoVO findByPrimaryKey(String tickets_no);
	public List<TicketNoVO> getAll();
	
	//當團購劵 從未審核(0) 轉換成 已審核(1) 需要做的動作 (創造序號吼~~~) 不是變更自己的狀態阿~~
	public void state1_create_tickettype(Integer tickets_type_no,java.sql.Connection con);
	
	public List<TicketNoVO> getAll(Map<String, String[]> map);
	
	//查詢狀態的List
	public List<TicketNoVO> getAllstate(Integer tickets_type_no,Integer tickets_no_status);
	
	//當購買後 狀態改變
	//-------------------------------當被購買時狀態改變(貫穿最底層)------------------------------//
	public void getbuy(String  tickets_no,java.sql.Connection con);
	
	
	//SELECT tickets_type_no,count(*) FROM tickets_no_detail where tickets_no IN (SELECT tickets_no FROM order_details where order_no IN (SELECT order_no FROM tickets_order where mem_no = 777800000)) GROUP BY tickets_type_no;
	//public List<TicketNoVO> get(Integer mem_no);//傳入會員編號 做子查詢的子查詢查出list TicketNoVO
	
	public Integer select_state(Map<String, String[]> map);
}
