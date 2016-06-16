package com.EShop.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class Eorder_Servlet extends HttpServlet {
	Integer moneyup = 90000000;//團購金總量上限
	String nourl_gift = "/front/E_My_Order/Eorder_Gift.jsp";
	String nourl_gift_pass = "/front/E_My_Order/Eorder_Gift_Pass.jsp";
	String nourl_gift_nopass = "/front/E_My_Order/Eorder_Gift_Nopass.jsp";
	String nourl_gift_record = "/front/E_My_Order/Eorder_Gift_Record.jsp";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//儲值交易跑這個
		if ("my_add_trandaction".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			Integer money = new Integer(req.getParameter("money"));
			
			MemberService membersvc = new MemberService();
			Integer now_money = membersvc.getOneMember(mem_no).getMem_balance();//取得原來的金額
			
			if (now_money < moneyup){
				MemberVO membervo = membersvc.add_money(mem_no, money);
				req.setAttribute("membervo", membervo);    // 資料庫取出的set物件,存入request
				req.setAttribute("money",money);			//本次增加金額
				RequestDispatcher successView = req.getRequestDispatcher(nourl_gift_pass);
				successView.forward(req, res);	
				return; //程式中斷
			}
			else{
				MemberVO membervo = membersvc.getOneMember(mem_no);
				errorMsgs.add("超過總量上限");
				req.setAttribute("moneyup", moneyup);    //團購金總量上限
				req.setAttribute("membervo", membervo);    // 資料庫取出的set物件,存入request
				req.setAttribute("money",money);			//本次增加金額
				RequestDispatcher successView = req.getRequestDispatcher(nourl_gift_nopass);
				successView.forward(req, res);	
				return; //程式中斷
			}
		}		
	}
}
