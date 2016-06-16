package com.TicketType.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class TicketTypeService {
	private TicketTypeDAO_interface dao;
	
	public TicketTypeService(){
		dao = new TicketTypeDAO();		
	}

	//新增
	public TicketTypeVO addTicketType(
			String tickets_type_name,
			java.sql.Date upper_date,
			java.sql.Date lower_date,
			Integer tickets_total,
			Integer tickets_quantity,
			Integer tickets_price,
			Integer tickets_state,
			Integer store_no,
			String tickets_ex,
			byte[] tickets_image
			){
		TicketTypeVO ticketTypeVO = new TicketTypeVO();
		
		ticketTypeVO.setTickets_type_name(tickets_type_name);
		ticketTypeVO.setUpper_date(upper_date);
		ticketTypeVO.setLower_date(lower_date);
		ticketTypeVO.setTickets_total(tickets_total);
		ticketTypeVO.setTickets_quantity(tickets_quantity);
		ticketTypeVO.setTickets_price(tickets_price);
		ticketTypeVO.setTickets_state(tickets_state);
		ticketTypeVO.setStore_no(store_no);
		ticketTypeVO.setTickets_ex(tickets_ex);
		ticketTypeVO.setTickets_image(tickets_image);
		dao.insert(ticketTypeVO);
		return ticketTypeVO;		
	}	
	
	//修改 (含圖片)
	public TicketTypeVO updateTicketType(			
			Integer tickets_type_no,
			String tickets_type_name,
			java.sql.Date upper_date,
			java.sql.Date lower_date,
			Integer tickets_total,
			Integer tickets_quantity,
			Integer tickets_price,
			Integer tickets_state,
			Integer store_no,
			String tickets_ex,
			byte[] tickets_image
			){
		TicketTypeVO ticketTypeVO = new TicketTypeVO();
		
		ticketTypeVO.setTickets_type_no(tickets_type_no);
		ticketTypeVO.setTickets_type_name(tickets_type_name);
		ticketTypeVO.setUpper_date(upper_date);
		ticketTypeVO.setLower_date(lower_date);
		ticketTypeVO.setTickets_total(tickets_total);
		ticketTypeVO.setTickets_quantity(tickets_quantity);
		ticketTypeVO.setTickets_price(tickets_price);
		ticketTypeVO.setTickets_state(tickets_state);
		ticketTypeVO.setStore_no(store_no);
		ticketTypeVO.setTickets_ex(tickets_ex);
		ticketTypeVO.setTickets_image(tickets_image);
		dao.update(ticketTypeVO);
		return ticketTypeVO;
	}

	//修改 (不含圖片)
	public TicketTypeVO updatenoimgTicketType(
			Integer tickets_type_no,
			String tickets_type_name,
			java.sql.Date upper_date,
			java.sql.Date lower_date,
			Integer tickets_total,
			Integer tickets_quantity,
			Integer tickets_price,
			Integer tickets_state,
			Integer store_no,
			String tickets_ex
			){
		TicketTypeVO ticketTypeVO = new TicketTypeVO();
		
		ticketTypeVO.setTickets_type_no(tickets_type_no);
		ticketTypeVO.setTickets_type_name(tickets_type_name);
		ticketTypeVO.setUpper_date(upper_date);
		ticketTypeVO.setLower_date(lower_date);
		ticketTypeVO.setTickets_total(tickets_total);
		ticketTypeVO.setTickets_quantity(tickets_quantity);
		ticketTypeVO.setTickets_price(tickets_price);
		ticketTypeVO.setTickets_state(tickets_state);
		ticketTypeVO.setStore_no(store_no);
		ticketTypeVO.setTickets_ex(tickets_ex);

		dao.updateno(ticketTypeVO);
		return ticketTypeVO;
	}

	//修改狀態
	public void updatestateTicketType(Integer tickets_type_no,Integer tickets_state){
		dao.updatestate(tickets_type_no, tickets_state);
	}
	//刪除
	public void deleteTicketType(Integer tickets_type_no){
		dao.delete(tickets_type_no);
		
	}
	
	//主鍵查詢
	public TicketTypeVO getOneTicketType(Integer tickets_type_no){
		return dao.findByPrimaryKey(tickets_type_no);
	}
	
	//查詢全部
	public List<TicketTypeVO> getAll(){
		return dao.getAll();
	}
	
	//查詢全部2
	public List<TicketTypeVO> getAll2(){
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("tickets_state",new String[]{ "2"});
		return dao.getAll(map);
	}
	
	//查找最新日期的種類編號
	public TicketTypeVO getnewticketno(Integer store_no){
		return dao.selectlastaddticketsno(store_no);
	}	
	
	//萬用複合查詢
	public List<TicketTypeVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	
	//查詢全部上架的產品
	public List<TicketTypeVO> getShopAll(){
		return dao.getshopall();
	}
	
	//把狀態 由 未審核(0)轉變成 已審核 (1) 會自動新增 total (總數量)的團購劵 ，如果沒有就不會增加
	//創造時失敗 會自動robalk 成功後才會把狀態由 未審核(0)轉變成 已審核 (1)
	public void getState0to1(Integer store_no){
		dao.stateok(store_no);
	}
	
//	結清店家的團購劵					團購劵種類編號		  /  手續費 幾% ? 0.2 0.4 0.6 0.8 之類
	public Integer getState4to6(Integer tickets_type_no_int,Double co){
		return dao.checkmoney(tickets_type_no_int, co);
	}
	
	public Integer select_all(){
		Map<String,String[]> map = new TreeMap<String,String[]>();
		return dao.select_num(map);
	}
	
	//查詢狀態的數量有多少
	public Integer select_all_state(Integer tickets_state){
		return dao.selectnum(tickets_state);
	}
	
	//查詢店家狀態的數量有多少
	public Integer select_store_state(Integer store_no,Integer tickets_state){
		return dao.select_store_num(store_no, tickets_state);
	}

}
