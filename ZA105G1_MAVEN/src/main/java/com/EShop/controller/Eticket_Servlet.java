package com.EShop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.OrderDe.model.OrderDeService;
import com.OrderDe.model.OrderDeVO;
import com.TicketNo.model.TicketNoService;
import com.TicketNo.model.TicketNoVO;
import com.TicketOr.model.TicketOrService;
import com.TicketOr.model.TicketOrVO;

public class Eticket_Servlet extends HttpServlet {
	String nourl_order_list = "/front/E_My_Ticket/ETicket_Order_List.jsp";
	String nourl_order_detail = "/front/E_My_Ticket/ETicket_Order_Detail.jsp";
	String nourl_ticket_list = "/front/E_My_Ticket/ETicket_Ticket_List.jsp";
	String nourl_ticket_detail = "/front/E_My_Ticket/ETicket_Ticket_Detail.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//查詢訂單就執行這個
		if ("select_order_no".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			
			try{
				/***************************1.接收請求參數*****************************************/
				Integer order_no =  new Integer(req.getParameter("order_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				OrderDeService orderdeservice = new OrderDeService();
				List<OrderDeVO> orderlist = orderdeservice.get_Order(order_no);
				//查詢訂單詳細
				TicketNoService ticknoservice = new TicketNoService();
				HashSet sset = new HashSet();
				for (int i=0;i<orderlist.size();i++){
					String tickets_no = orderlist.get(i).getTickets_no();
					Integer tickets_type_no = ticknoservice.getOneCashTr(tickets_no).getTickets_type_no();
					sset.add(tickets_type_no);
				}
				Iterator it = sset.iterator();
				List list2 = new LinkedList();
				while (it.hasNext()) {  
					list2.add(it.next());
				}
				/*************************** 4.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("orderlist", orderlist);    // 資料庫取出的set物件,存入request
				req.setAttribute("list2", list2);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(nourl_order_detail);
				successView.forward(req, res);			
			}			
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_order_list);
				failureView.forward(req, res);				
			}
		}
		
		
		
		if("myorderticket".equals(action)){
			List list = new ArrayList();
			TicketOrService ticketorservic = new TicketOrService();
			OrderDeService orderdeservicee = new OrderDeService();
			TicketNoService ticketnoservice = new TicketNoService();
			try{
				if (req.getParameter("state")==null){
					Integer tickets_typeno =  new Integer(req.getParameter("tickets_typeno"));
					Integer mem_no=  new Integer(req.getParameter("mem_no"));
					List<TicketOrVO> list1List = ticketorservic.getUserdate(mem_no);
					for (int i=0;i<list1List.size();i++){
						Integer order_no =  list1List.get(i).getOrder_no();
						List list2 = orderdeservicee.get_Order(order_no);
						for (int j=0;j<list2.size();j++){
							String tickets_no = orderdeservicee.get_Order(order_no).get(j).getTickets_no();
							TicketNoVO ticketvo = ticketnoservice.getOneCashTr(tickets_no);
							if (tickets_typeno.equals(ticketvo.getTickets_type_no())){
									list.add(ticketvo);
							}
						}				
					}
					
				}else{
					Integer tickets_typeno =  new Integer(req.getParameter("tickets_typeno"));
					Integer state =  new Integer(req.getParameter("state"));
					Integer mem_no=  new Integer(req.getParameter("mem_no"));
					//System.out.println(tickets_typeno + "//" + state + "//" + mem_no);
					
					
					

					List<TicketOrVO> list1List = ticketorservic.getUserdate(mem_no);
					for (int i=0;i<list1List.size();i++){
						Integer order_no =  list1List.get(i).getOrder_no();
						List list2 = orderdeservicee.get_Order(order_no);
						for (int j=0;j<list2.size();j++){
							String tickets_no = orderdeservicee.get_Order(order_no).get(j).getTickets_no();
							TicketNoVO ticketvo = ticketnoservice.getOneCashTr(tickets_no);
							if (tickets_typeno.equals(ticketvo.getTickets_type_no())){
								if (state.equals(ticketvo.getTickets_no_status())){
									list.add(ticketvo);
								}
							}
						}				
					}
				}

				req.setAttribute("MyOrderTicketlist", list);
				RequestDispatcher successView = req.getRequestDispatcher(nourl_ticket_detail);
				successView.forward(req, res);	
				
			}catch (Exception e) {
				RequestDispatcher successView = req.getRequestDispatcher(nourl_ticket_list);
				successView.forward(req, res);	
			}


		}
		
	}
}
