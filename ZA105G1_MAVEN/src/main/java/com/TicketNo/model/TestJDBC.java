package com.TicketNo.model;


import java.util.*;

public class TestJDBC {

	public static void main(String[] args) {
		
		TicketNoJDBCDAO dao = new TicketNoJDBCDAO();
		List<TicketNoVO> vo05 = dao.getAllstate(848400001,0);
		for (int i = 0 ; i<vo05.size();i++){
			if (i==1){
				System.out.println(vo05.get(i).getTickets_no());
			}
		}
//		for (TicketNoVO vo06 : vo05 ){
//			System.out.println(vo06.getTickets_no());
//			System.out.println(vo06.getTickets_no_status());
//			System.out.println(vo06.getTickets_type_no());
//		}
		
//		Map<String,String[]> map = new TreeMap<String,String[]>();
//		map.put("tickets_type_no",new String[]{ "848400001"});
//		TicketNoJDBCDAO vo01 = new TicketNoJDBCDAO();
//		List<TicketNoVO> list = vo01.getAll(map);
//		for (TicketNoVO vo05 : list) {
//			System.out.println(vo05.getTickets_no());
//		}
//		Map<String,String[]> map = new TreeMap<String,String[]>();
//		map.put("application_date",new String[]{ "2016-02-16"});
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		List<TicketNoVO> list = dao.getAll(map);

	

		// 新增測試
//		TicketNoVO vo01 = new TicketNoVO();
//		vo01.setTickets_type_no(1);
//		vo01.setTickets_no_status(1);
//		dao.insert(vo01);
//		System.out.print("NEW TEST OK!!");
		
		// 新增測試
//		TicketNoVO vo01 = new TicketNoVO();
//		vo01.setTickets_type_no(1);
//		vo01.setTickets_no_status(1);
//		dao.insert(vo01);
//		System.out.print("NEW TEST OK!!");
		
//		//修改測試
//		TicketNoVO vo02 = new TicketNoVO();
//		vo02.setTickets_type_no(7);
//		vo02.setTickets_no_status(8);
//		
//		vo02.setTickets_no("8473000003");
//		//修改的編號 : 主鍵
//		System.out.print(" TEST OK!!");
//		dao.update(vo02);
//		
//		//刪除測試
//		dao.delete("8473000003");
//		//修改的編號 : 主鍵
//		System.out.print("Delete Test  OK");
//		
//		//查詢測試
//		TicketNoVO vo03 = dao.findByPrimaryKey("8473000001");
//		//查詢的主鍵
//		System.out.println(vo03.getTickets_no());
//		System.out.println(vo03.getTickets_no_status());
//		System.out.println(vo03.getTickets_type_no());
		
//		//查詢全部測試
//		List<TicketNoVO> vo05 = dao.getAll();
//		for (TicketNoVO vo06 : vo05 ){
//			System.out.println(vo06.getTickets_no());
//			System.out.println(vo06.getTickets_no_status());
//			System.out.println(vo06.getTickets_type_no());
//		}
	}
}