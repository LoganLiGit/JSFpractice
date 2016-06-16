package com.fbcm.controller;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.OrderDe.controller.OrderDeServlet;
import com.OrderDe.model.OrderDeService;
import com.OrderDe.model.OrderDeVO;
import com.TicketNo.model.TicketNoService;
import com.TicketNo.model.TicketNoVO;
import com.TicketType.model.TicketTypeService;
import com.TicketType.model.TicketTypeVO;
import com.Tickettypeall.model.newTestCompositeQuery;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig

public class Fbcm_Trandaction extends HttpServlet {
	Integer moneyup = 90000000;//團購金總量上限
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//儲值交易跑這個
		if ("my_add_trandaction".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			
			String my_add_trandaction_ok_url = "/Back/fbcm/myorderform/MyAddOKTrandaction.jsp"; //有錯誤要轉導到的地方
			String my_add_trandaction_no_url = "/Back/fbcm/myorderform/MyAddNOTrandaction.jsp";
			
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			Integer money = new Integer(req.getParameter("money"));
			
			MemberService membersvc = new MemberService();
			Integer now_money = membersvc.getOneMember(mem_no).getMem_balance();//取得原來的金額
			
			if (now_money < moneyup){
				MemberVO membervo = membersvc.add_money(mem_no, money);
				req.setAttribute("membervo", membervo);    // 資料庫取出的set物件,存入request
				req.setAttribute("money",money);			//本次增加金額
				RequestDispatcher successView = req.getRequestDispatcher(my_add_trandaction_ok_url);
				successView.forward(req, res);	
				return; //程式中斷
			}
			else{
				MemberVO membervo = membersvc.getOneMember(mem_no);
				errorMsgs.add("超過總量上限");
				req.setAttribute("moneyup", moneyup);    //團購金總量上限
				req.setAttribute("membervo", membervo);    // 資料庫取出的set物件,存入request
				req.setAttribute("money",money);			//本次增加金額
				RequestDispatcher successView = req.getRequestDispatcher(my_add_trandaction_no_url);
				successView.forward(req, res);	
				return; //程式中斷
			}
		}
		
		
		
	}
	
	

}
