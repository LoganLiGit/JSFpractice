package com.store.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.pocket.model.*;
import com.store.model.*;
import com.store.pic.model.*;
import com.store.pic.model.*;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class StoreLogin extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//*****��瑼Ｘ******//
		
		if ("login_check".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1��隢�� - 頛詨�撘�隤方���**********************/
				String acc = req.getParameter("store_account");
				String pwd = req.getParameter("store_password");
			
				if (acc == null || (acc.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (pwd == null || (pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getStoreAccount(req.getParameter("store_account"));
				if (storeVO == null){
					errorMsgs.add("帳號不存在");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
//				************於管理員把權限改為審核通過才能登入**************	
				if (storeVO.getStore_state() == 2  ){
					errorMsgs.add("帳號還未通過審核");
				}

				
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				String storeVO_acc = storeVO.getStore_account();
				String storeVO_pwd = storeVO.getStore_password();
			
				if (!storeVO_acc.equals(acc)||!storeVO_pwd.equals(pwd)){
					errorMsgs.add("帳號或密碼錯誤");
				}


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}

				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				HttpSession session = req.getSession();
				
			      session.setAttribute("storeVO", storeVO);
			      /****如有來源網頁:則重導至來源網頁****/ 
			      
//			       try {                                                        
//			         String location = (String) session.getAttribute("location");	
//			    
//			         if (location != null) {
//			           session.removeAttribute("location"); 
//			           
//			           res.sendRedirect(req.getContextPath()+location);  
//		           
//			           return;
//			         }
//			       }catch (Exception ignored) { }
			      res.sendRedirect(req.getContextPath()+"/index_store.jsp");  //*撌乩��3: (-->憒靘�雯���:����index.jsp)  

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/store/login/login.jsp");
				failureView.forward(req, res);
			}
		}
	}
}