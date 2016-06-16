package com.TicketType.model;

//團購券總類	ticket_type
import java.util.List;
import java.util.Map;

public interface TicketTypeDAO_interface {
	public void insert(TicketTypeVO ticketTypeVO);
	//新增
	
	public void update(TicketTypeVO ticketTypeVO);
	//修改(包含圖片)
	
	public void updateno(TicketTypeVO ticketTypeVO);
	//修改(不包含圖片)
	
	public void updatestate(Integer tickets_type_no,Integer tickets_state);
	//修改狀態
	
	public void delete(Integer tickets_type_no);
	//刪除
	
	public TicketTypeVO findByPrimaryKey(Integer tickets_type_no);
	//一鍵查找
	
	public List<TicketTypeVO> getAll();
	//全找
	
	public TicketTypeVO selectlastadd(Integer store_no);
	//查找最新日期
	
	public TicketTypeVO selectlastaddticketsno(Integer store_no);
	//查找最新日期的種類編號
	
	public List<TicketTypeVO> getAll(Map<String, String[]> map);
	//萬用複合查詢
	
	public List<TicketTypeVO> getshopall();
	//把上架的產品上架顯示
	
	public void stateok(Integer store_no);
	//團購劵審核通過 創造序號
		
	//-------------------------------當被購買時狀態改變(貫穿最底層)------------------------------//
	//扣除團購劵剩餘數量				團購劵種類編號	 / 扣除數量			 /連線							
	public void deductedtime(Integer tickets_type_no,Integer deducte,java.sql.Connection con);
	//扣除團購劵剩餘數量
	
	public Integer checkmoney(Integer tickets_type_no_int,Double co);
	//	結清店家的團購劵					團購劵種類編號		  /  手續費 幾% ? 0.2 0.4 0.6 0.8 之類
	
	public Integer selectnum(Integer tickets_state);
	//查詢狀態的數量有多少
	
	//public Integer select_ticket_state_num(Integer tickets_type_no ,Integer tickets_state);
	//查詢此團購劵狀態的數量有多少
	
	public Integer select_store_num(Integer store_no ,Integer tickets_state);
	//查詢店家狀態的數量有多少
		
	public Integer select_num(Map<String, String[]> map);	
}
