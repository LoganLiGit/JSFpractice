package com.EShop.controller;
//團購劵商城的控制器

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import com.TicketOr.model.TicketOrDAO;
import com.TicketOr.model.TicketOrVO;
import com.TicketShopCar.model.Ticket;
import com.TicketType.model.TicketTypeService;
import com.TicketType.model.TicketTypeVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.store.model.StoreService;

public class Eshop_Servlet extends HttpServlet {
	String no_url_eshop = "/front/E_Shop/EShop.jsp"; //轉到商店的路徑
	String no_url_cart= "/front/E_Shop/EShop_Cart.jsp";//轉送到購物車的路徑
	String no_url_check= "/front/E_Shop/EShop_Check.jsp";//確定結帳的路徑
	String no_url_check_over= "/front/E_Shop/EShop_Check_Over.jsp";//結帳完成的路徑 
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// res.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = res.getWriter();
		double discount = 1;

		HttpSession session = req.getSession();
		Vector<Ticket> buylist = (Vector<Ticket>)session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		//以上為把錯誤訊息丟入req裡
		

		
		if (!action.equals("checkout") && !action.equals("checkoutok")) {
			
			if (action.equals("UPDATDTIME")){ //更新數量
		    	String updata_time = req.getParameter("updata_time");
		    	String time = req.getParameter("time");
		    	Integer time_intInteger = null;
		    	try{
		    		time_intInteger = new Integer(time);
		    		if (time_intInteger < 0){ // 數量不得為負數
		    			errorMsgs.add("數量不得為負數");
					     RequestDispatcher successView = req.getRequestDispatcher(no_url_cart);
					     successView.forward(req, res);	
					     return;
		    		}
		    	}catch(Exception e){
		    		errorMsgs.add("請輸入數量");
				     RequestDispatcher successView = req.getRequestDispatcher(no_url_cart);
				     successView.forward(req, res);	
				     return;
		    	}
		    	int d = Integer.parseInt(updata_time);
		    	buylist.elementAt(d).setQuantity(time_intInteger);
		    	buylist.setElementAt(buylist.elementAt(d),d);
		    	
				int total = 0;
				for (int i = 0; i < buylist.size(); i++) {
					Ticket ticket = buylist.get(i);
					int price = ticket.getPrice();
					int quantity = ticket.getQuantity();			
					total += (price * quantity);
				}
		    	
			    RequestDispatcher successView = req.getRequestDispatcher(no_url_cart);
			    successView.forward(req, res);	
			    return;
		    	
			}
			
			
			
			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				try{
					int d = Integer.parseInt(del);
					buylist.removeElementAt(d);
				}catch(Exception e){
					System.out.println("ERROR");
				}
				if (req.getParameter("url")!= null){
					session.setAttribute("shoppingcart", buylist);
					RequestDispatcher rd = req.getRequestDispatcher(no_url_eshop);
					rd.forward(req, res);
					return; // 程式結束
				}
				session.setAttribute("shoppingcart", buylist);
				RequestDispatcher rd = req.getRequestDispatcher(no_url_cart);
				rd.forward(req, res);
				return; // 程式結束
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
/*----------------------------	測試新增的數量是否為數字型態	----------------------------*/
				Integer quantity_int = 0; //購買數量
				try{
					String quantity = req.getParameter("quantity");
					quantity_int = new Integer(quantity).intValue();
					
				}catch(Exception e){
					errorMsgs.add("請輸入數量");
					RequestDispatcher failureView = req
							.getRequestDispatcher(no_url_eshop);
					failureView.forward(req, res);	
					return; //程式中斷
				}
/*--------------------------------------------------------------------------------------*/				
/*----------------------------		測試剩餘數量是否小於0		----------------------------*/

				if(quantity_int < 0){
					errorMsgs.add("數量不得小於0");
					RequestDispatcher failureView = req
							.getRequestDispatcher(no_url_eshop);
					failureView.forward(req, res);	
					return; //程式中斷
				}
/*--------------------------------------------------------------------------------------*/	
/*----------------------------		測試剩餘數量是否正確		----------------------------*/
				Integer lastqu_int = 0; //剩餘數量
				try{
					String lastqu = req.getParameter("lastqu");
					lastqu_int = new Integer(lastqu).intValue();
					//價格 剩餘數量 < 購買數量
					if ( lastqu_int < quantity_int){
						errorMsgs.add(" 剩餘不足");
						RequestDispatcher failureView = req
								.getRequestDispatcher(no_url_eshop);
						failureView.forward(req, res);	
						return;//程式中斷
					}					
				}catch(Exception e){
					errorMsgs.add("伺服器連線失敗: Error A001");
					RequestDispatcher failureView = req
							.getRequestDispatcher(no_url_eshop);
					failureView.forward(req, res);	
					return; //程式中斷
				}
/*--------------------------------------------------------------------------------------*/	

				boolean match = false;
					
				// 取得後來新增的書籍
				Ticket aticket = getTicket(req);
					
				// 新增第一本書籍至購物車時
				if (buylist == null) {
					buylist = new Vector<Ticket>();
					buylist.add(aticket);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						Ticket ticket = buylist.get(i);
							
						// 假若新增的書籍和購物車的書籍一樣時
						if (ticket.getTickets_type_no() ==  aticket.getTickets_type_no() ) {
/*-----------------------------------------		測試剩餘數量是否足夠		----------------------------*/
							//    剩餘數量 < 購買數量
							int quantity_int2 = quantity_int + ticket.getQuantity();
							if ( lastqu_int < quantity_int2){
								errorMsgs.add(" 剩餘不足，餘額沒有辦法讓您購買超過本次想購買數量，請見諒");
								RequestDispatcher failureView = req
										.getRequestDispatcher(no_url_eshop);
								failureView.forward(req, res);	
								return;//程式中斷
							}							
/*---------------------------------------------------------------------------------------------------*/
							
							ticket.setQuantity(ticket.getQuantity() + aticket.getQuantity());
							buylist.setElementAt(ticket, i);
							match = true;
							
						} // end of if name matches
						// 假若新增的書籍和購物車的書籍不一樣時
					}
					if (!match){
						buylist.add(aticket);
					}
				}				
			}
			session.setAttribute("shoppingcart", buylist);
			RequestDispatcher rd = req.getRequestDispatcher(no_url_eshop);
			rd.forward(req, res);
		}
		// 結帳，計算購物車書籍價錢總數
		else if (action.equals("checkout")) {
			if (buylist == null) {
				RequestDispatcher rd = req.getRequestDispatcher(no_url_eshop);
				rd.forward(req,res);
			}else{
				float total = 0;
				for(int i=0;i<buylist.size();i++){
						Ticket order = buylist.get(i);
						int price = order.getPrice();
						int quantity = order.getQuantity();
						total += ( price * quantity );
				}
				total = (int)(total * discount); //折扣部分
				String amount =String.valueOf(total);
				session.setAttribute("amount",amount);			
				RequestDispatcher rd = req.getRequestDispatcher(no_url_check);
				rd.forward(req,res);				
			}			
		}
		else if (action.equals("checkoutok") && session.getAttribute("shoppingcart")!=null) {
//			try{
				Vector<Ticket> buylistaTickets =(Vector<Ticket>)session.getAttribute("shoppingcart");
				int ssal = 0;
				for (int i = 0; i < buylistaTickets.size(); i++) {
					Ticket order = buylistaTickets.get(i);
					int tickets_type_no = order.getTickets_type_no();
					TicketTypeService tickSvc = new TicketTypeService();
					Integer price = tickSvc.getOneTicketType(tickets_type_no).getTickets_price(); //取出資料庫編號的價格去算

					//int price = order.getPrice();
					int qu = order.getQuantity();
/*-----------------------------------------		測試剩餘數量是否足夠		----------------------------*/
					Integer tickSvcqu =tickSvc.getOneTicketType(tickets_type_no).getTickets_quantity(); //取出資料庫編號的剩餘數量
					//------購買時 結果剩餘數量竟然是0 就要下架------//
					if (tickSvcqu == 0){ /*---購買時 結果剩餘數量竟然是0 就要下架----*/
						//已在M做 基本上控制器不太會參予到
					}					
					//------------//
					System.out.println("目前購買數量:" + qu);
					System.out.println("目前資料庫剩餘數量:" + tickSvcqu);
					if (tickSvcqu < qu ){ //假如 資料庫的剩餘數量 < 購買數量
						errorMsgs.add(" 剩餘不足，餘額沒有辦法讓您購買超過本次想購買數量，請見諒");
						
						buylist.removeElementAt(i); //並且把無法購買的 從購物車刪除
						RequestDispatcher failureView = req.getRequestDispatcher(no_url_eshop);
						failureView.forward(req, res);	
						return;//程式中斷
					}
/*---------------------------------------------------------------------------------------------------*/
					ssal += (price * qu);
				}
				ssal =(int)(ssal * discount); //折扣部分
				//ssal 此購物的金額
				String mem_account = (String)session.getAttribute("mem_account");
				MemberService memSvc = new MemberService();
				MemberVO membervo =memSvc.getOneAccount(mem_account); 
				int member_monkey = membervo.getMem_balance();//目前會員擁有的團購金
				int id = membervo.getMem_no();//取得會員ID

				
				/* 此為判斷正確 做交易部分 非常重要 (金額餘額足夠 商品正確)*/
				if ( member_monkey >= ssal ){ //假如擁有團購金 大於等於 此購物金額 就通過
					TicketOrDAO ticketordao = new TicketOrDAO();
					TicketOrVO ticketorvo  =ticketordao.getnewtickettype_order(buylistaTickets,id,ssal,discount);					
					System.out.println(ticketorvo.getMem_no());
					session.removeAttribute("shoppingcart"); //刪除購物車session 裡面的東西
					
					//購物成功後的轉導	
					session.setAttribute("buy_tk_list", buylistaTickets);
					session.setAttribute("ticketorvo", ticketorvo);
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_check_over);
					failureView.forward(req, res);	
					return; //程式中斷
				}
				else if ( member_monkey < ssal){ //如果餘額不足直接跳離
					errorMsgs.add("團購金餘額不足!");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_eshop);
					failureView.forward(req, res);	
					return; //程式中斷
				}
				else{
					errorMsgs.add("團購金餘額不足!");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_eshop);
					failureView.forward(req, res);	
					return; //程式中斷					
				}
				
				
//			}
//			catch(Exception e){
//				errorMsgs.add("伺服器連結失敗，請稍候登入購買 Z007");
//				RequestDispatcher failureView = req.getRequestDispatcher(no_url_eshop);
//				failureView.forward(req, res);	
//				return; //程式中斷
//			}
		}else if (action.equals("checkoutok") && session.getAttribute("ticketorvo")!=null){
			res.sendRedirect(no_url_check_over);
			//Check_Over.jsp
		}
		
	}

	private Ticket getTicket(HttpServletRequest req) {		
		String tickets_type_no = req.getParameter("tickets_type_no");
		String tickets_type_name = req.getParameter("tickets_type_name");
		//String store_name = req.getParameter("store_name");
		//String price = req.getParameter("price");
		String quantity = req.getParameter("quantity");
		

		Integer quantity_int = 0;
		Integer price_int = 0;
		Integer tickets_type_no_int = 0;
		try{
			quantity_int = new Integer(quantity).intValue();
			//price_int = new Integer(price).intValue();
			tickets_type_no_int = new Integer(tickets_type_no).intValue();
		}
		catch(Exception e){
			System.out.println("Error1");			
		}
/*
 * tickets_type_no
 * 
 * 
 * */
		TicketTypeService tickettypesvc = new TicketTypeService();
		TicketTypeVO ticketvo = tickettypesvc.getOneTicketType(tickets_type_no_int);
		StoreService storesvc = new StoreService();
		String store_name = storesvc.getOneStore(ticketvo.getStore_no()).getStore_name();
		
		
		
		TicketTypeService tickSvc = new TicketTypeService();
		price_int = tickSvc.getOneTicketType(tickets_type_no_int).getTickets_price(); //取出資料庫編號的價格去算
		
		
		Ticket tk = new Ticket();
		if (quantity_int > 0){
			tk.setTickets_type_no(tickets_type_no_int);
			tk.setTickets_type_name(tickets_type_name);
			tk.setStore_name(store_name);
			tk.setPrice(price_int);
			tk.setQuantity(quantity_int);
		}		
		return tk;
	}
}
