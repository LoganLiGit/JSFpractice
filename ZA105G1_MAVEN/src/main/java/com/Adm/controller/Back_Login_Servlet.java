package com.Adm.controller;
//登入登出使用

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Adm.model.AdmService;
import com.Adm.model.AdmVO;
import com.History.model.HistoryServlet;

public class Back_Login_Servlet extends HttpServlet {
	String nourl_back_login = "/Back/Login/Back_Login.jsp";
	String nourl_back_index = "/Back/Index/Back_Index.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//*****登入檢查******//
		if ("login_check".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1接收請求參數 - 輸入格式的錯誤處理**********************/
				String acc = req.getParameter("admin_account");
				String pwd = req.getParameter("admin_password");
				
				if (acc == null || (acc.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (pwd == null || (pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_login);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAccount(req.getParameter("admin_account"));
				if (admVO == null){
					errorMsgs.add("帳號不存在");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_login);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String admVO_acc = admVO.getAdmin_account();
				String admVO_pwd = admVO.getAdmin_password();
			
				if (!admVO_acc.equals(acc)||!admVO_pwd.equals(pwd)){
					errorMsgs.add("帳號或密碼錯誤");
				}


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_login);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				//做登入紀錄
				String login_ip = "IP: " + req.getRemoteAddr() + "，主機名稱:" + req.getRemoteHost();
				Integer admin_no = admVO.getAdmin_no();
				HistoryServlet historyServlet = new HistoryServlet();
				historyServlet.admin_login(admin_no,login_ip);
				
				String admin_account = admVO.getAdmin_account();
				HttpSession session = req.getSession();
			    session.setAttribute("admin_account", admin_account);
			    session.setAttribute("admVO", admVO);			      
				res.sendRedirect(req.getContextPath()+ nourl_back_index);
				  
				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_login);
//				failureView.forward(req, res);
//			}
		}
		
		
	}

}
