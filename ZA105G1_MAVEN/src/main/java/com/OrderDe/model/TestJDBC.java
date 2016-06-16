package com.OrderDe.model;

import java.util.List;

public class TestJDBC {

	public static void main(String[] args) {
		
		OrderDeJDBCDAO dao = new OrderDeJDBCDAO();
		List<OrderDeVO> vo05 = dao.findByPrimaryKey_Order(798200004);
		for (OrderDeVO vo6 : vo05){
			System.out.println(vo6.getOrder_no());
			System.out.println(vo6.getOrder_num());
			System.out.println(vo6.getOrder_status());
			System.out.println(vo6.getOrder_value());
			System.out.println(vo6.getTickets_no());
		}
		// 新增測試
//		OrderDeVO vo01 = new OrderDeVO();
//		vo01.setOrder_no(12);
//		vo01.setOrder_num(1);
//		vo01.setOrder_status(1);
//		vo01.setOrder_value(1);
//		vo01.setTickets_no("1");
//		dao.insert(vo01);
		//修改測試
//		OrderDeVO vo02 = new OrderDeVO();
//		vo02.setOrder_no(12);
//		vo02.setTickets_no("1");
//		vo02.setOrder_num(9);
//		vo02.setOrder_status(9);
//		vo02.setOrder_value(9);
//
//		dao.update(vo02);
	
		//刪除測試
//		dao.delete(12,"1");
//		//修改的編號 : 主鍵
//		System.out.print("Delete Test  OK");
//		
//		//查詢測試
//		OrderDeVO vo03 = dao.findByPrimaryKey(1,"1AA");
//		System.out.println(vo03.getOrder_no());
//		System.out.println(vo03.getOrder_num());
//		System.out.println(vo03.getOrder_status());
//		System.out.println(vo03.getOrder_value());
//		System.out.println(vo03.getTickets_no());
//		//查詢全部測試
//		List<OrderDeVO> vo05 = dao.getAll();
//		for (OrderDeVO vo6 : vo05){
//			System.out.println(vo6.getOrder_no());
//			System.out.println(vo6.getOrder_num());
//			System.out.println(vo6.getOrder_status());
//			System.out.println(vo6.getOrder_value());
//			System.out.println(vo6.getTickets_no());
//		}
//		
//		//查詢全部測試
//		List<TicketNoVO> vo05 = dao.getAll();
//		for (TicketNoVO vo06 : vo05 ){
//			System.out.println(vo06.getTickets_no());
//			System.out.println(vo06.getTickets_no_status());
//			System.out.println(vo06.getTickets_type_no());
//		}
	}
}
