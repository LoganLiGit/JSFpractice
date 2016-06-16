package com.CashTr.controller;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//店家結清團購金使用
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TicketType.model.TicketTypeService;

public class Back_Store_CashTr_Servlet extends HttpServlet {
	Double checkout_money = 0.6; // 回饋
	String nourl_back_cashtr_cashtr_store_check_ok = "/Back/CashTr/Back_Cashtr_Store_Check_OK.jsp";
	String nourl_back_cashtr_cashtr_store_sold_out = "/Back/Cashtr/Back_Cashtr_Store_Sold_Out.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		//假如後端按下結清
		if ("get_check".equals(action)) {
			try{
				TicketTypeService ticketTypeService = new TicketTypeService();
	
				String tickets_type_no = req.getParameter("tickets_type_no");			
				Integer tickets_type_no_int = new Integer(tickets_type_no);
				Integer addmoney = ticketTypeService.getState4to6(tickets_type_no_int,checkout_money);			
				errorMsgs.add("團購劵編號:" + tickets_type_no + "已經結清");
				errorMsgs.add("本次店家儲值金增加 :" + addmoney + " 元");
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_cashtr_cashtr_store_sold_out);
				failureView.forward(req, res);	
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			RequestDispatcher successView = req.getRequestDispatcher(nourl_back_cashtr_cashtr_store_check_ok);
			successView.forward(req, res);	
			return;
		}
		
		
	}
}
