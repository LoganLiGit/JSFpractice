package com.TicketOr.model;

import java.util.List;

public class TicketOrService {
	private TicketOrDAO_interface dao;
	
	public TicketOrService(){
		dao = new TicketOrDAO();
	}

	public TicketOrVO addTicketOr(Integer mem_no,java.sql.Date order_date,Integer order_money,Integer order_status){
		TicketOrVO ticketOrVO = new TicketOrVO();
		
		ticketOrVO.setMem_no(mem_no);
		ticketOrVO.setOrder_date(order_date);
		ticketOrVO.setOrder_money(order_money);
		ticketOrVO.setOrder_status(order_status);
		dao.insert(ticketOrVO);
		
		return ticketOrVO;
	}
	
	public TicketOrVO updateTicketOr(Integer order_no,Integer mem_no,java.sql.Date order_date,Integer order_money,Integer order_status){
		TicketOrVO ticketOrVO = new TicketOrVO();
		
		ticketOrVO.setOrder_no(order_no);
		ticketOrVO.setMem_no(mem_no);
		ticketOrVO.setOrder_date(order_date);
		ticketOrVO.setOrder_money(order_money);
		ticketOrVO.setOrder_status(order_status);
		dao.update(ticketOrVO);
		
		return ticketOrVO;
	}
	
	public void deleteTicketOr(Integer order_no){
		dao.delete(order_no);
	}
	
	public TicketOrVO getOneTicketOr(Integer order_no){
		return dao.findByPrimaryKey(order_no);
		
	}
	
	public List<TicketOrVO> getAll(){
		return dao.getAll();
	}
	
	public List<TicketOrVO> getUserdate(Integer mem_no){
		return dao.getUser(mem_no);
	}
	
}
