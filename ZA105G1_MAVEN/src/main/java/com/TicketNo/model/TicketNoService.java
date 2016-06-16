package com.TicketNo.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TicketNoService {
	private TicketNoDAO_interface dao;
	
	public TicketNoService(){
		dao = new TicketNoDAO();
	}
	
	public TicketNoVO addTicketNo(String tickets_no,Integer tickets_no_status,Integer tickets_type_no){
		TicketNoVO ticketnovo = new TicketNoVO();
		
		ticketnovo.setTickets_no(tickets_no);
		ticketnovo.setTickets_no_status(tickets_no_status);
		ticketnovo.setTickets_type_no(tickets_type_no);
		dao.insert(ticketnovo);
		
		return ticketnovo;		
	}
	
	public TicketNoVO updateCashTr(String tickets_no,Integer tickets_no_status,Integer tickets_type_no){
		TicketNoVO ticketnovo = new TicketNoVO();
		
		ticketnovo.setTickets_no(tickets_no);
		ticketnovo.setTickets_no_status(tickets_no_status);
		ticketnovo.setTickets_type_no(tickets_type_no);

		dao.update(ticketnovo);
		
		return ticketnovo;	
	}
	
	public void deleteCashTr(String tickets_no){
		dao.delete(tickets_no);
	}

	public TicketNoVO getOneCashTr(String tickets_no){
		return dao.findByPrimaryKey(tickets_no);
	}

	public List<TicketNoVO> getAll(){
		return dao.getAll();
	}
	public List<TicketNoVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	public List<TicketNoVO> getAllState(Integer tickets_type_no,Integer tickets_no_status){
		return dao.getAllstate(tickets_type_no,tickets_no_status);
	}
	
	public Integer select_ticket_num(Integer tickets_type_no,Integer tickets_no_status){
		Map<String,String[]> map = new TreeMap<String,String[]>();
		String tickets_type_no_string = tickets_type_no.toString();
		String tickets_no_status_string = tickets_no_status.toString();
		map.put("tickets_type_no",new String[]{tickets_type_no_string});
		map.put("tickets_no_status",new String[]{tickets_no_status_string});
		return dao.select_state(map);
	}
}
