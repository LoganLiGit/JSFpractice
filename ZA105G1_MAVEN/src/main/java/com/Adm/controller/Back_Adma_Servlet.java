package com.Adm.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Adm.model.AdmService;
import com.Adm.model.AdmVO;

public class Back_Adma_Servlet extends HttpServlet {
	String nourl_back_adma_add = "/Back/Adma/Back_Adma_Add.jsp";
	String nourl_back_adma_update = "/Back/Adma/Back_Adma_Update.jsp";
	String nourl_back_adma_list = "/Back/Adma/Back_Adma_List.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//第一次 按下修改會進來這裡
		if ("getOne_For_Update".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer admin_no = new Integer(req.getParameter("admin_no"));
				
				/***************************2.開始查詢資料****************************************/
				AdmService admSvc = new AdmService();
				AdmVO admVO = admSvc.getOneAdm(admin_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admVO", admVO);         // 資料庫取出的admVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_adma_update);// 成功轉交 update_adm_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_list);
				failureView.forward(req, res);
			}
		}
		
		//第二次按下修改會進來這裡
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer admin_no = new Integer(req.getParameter("admin_no").trim());
				String admin_account = req.getParameter("admin_account").trim();
				String admin_password = req.getParameter("admin_password").trim();				
				String admin_name = req.getParameter("admin_name").trim();	
				String admin_email = req.getParameter("admin_email").trim();	
				String admin_phone = req.getParameter("admin_phone").trim();	
				String admin_address = req.getParameter("admin_address").trim();	
				

				AdmVO admVO = new AdmVO();
				admVO.setAdmin_no(admin_no);
				admVO.setAdmin_account(admin_account);
				admVO.setAdmin_password(admin_password);
				admVO.setAdmin_name(admin_name);
				admVO.setAdmin_email(admin_email);
				admVO.setAdmin_phone(admin_phone);
				admVO.setAdmin_address(admin_address);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的admVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_update);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				errorMsgs.add("管理員帳號編號:" + admin_no + " 已修改此帳號所有資料 ");
				/***************************2.開始修改資料*****************************************/
				AdmService admSvc = new AdmService();
				admVO = admSvc.updateAdm(admin_no,admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admVO", admVO); // 資料庫update成功後,正確的的admVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_adma_list); // 修改成功後,轉交listOneAdm.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_update);
				failureView.forward(req, res);
			}
		}
	
		//如果按下刪除 會跑到這裡
		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer admin_no = new Integer(req.getParameter("admin_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdmService admSvc = new AdmService();
				admSvc.deleteAdm(admin_no);
				errorMsgs.add("管理員帳號編號:" + admin_no + " 已刪除此帳號所有資料 ");
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_adma_list);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_list);
				failureView.forward(req, res);
			}
		}
		//從新增管理員帳號過來的新增
        if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String admin_account = req.getParameter("admin_account").trim();
				String admin_password = req.getParameter("admin_password").trim();				
				String admin_name = req.getParameter("admin_name").trim();	
				String admin_email = req.getParameter("admin_email").trim();	
				String admin_phone = req.getParameter("admin_phone").trim();	
				String admin_address = req.getParameter("admin_address").trim();	


				AdmVO admVO = new AdmVO();
				admVO.setAdmin_account(admin_account);
				admVO.setAdmin_password(admin_password);
				admVO.setAdmin_name(admin_name);
				admVO.setAdmin_email(admin_email);
				admVO.setAdmin_phone(admin_phone);
				admVO.setAdmin_address(admin_address);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admVO", admVO); // 含有輸入格式錯誤的admVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_add);
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				AdmService admSvc = new AdmService();
				admSvc.addAdm(admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address);
				errorMsgs.add("管理員   帳號:" + admin_account + " 已創造此帳號 ");
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_adma_list); // 新增成功後轉交listAllAdm.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("新增管理員錯誤: 有重複相同帳號的管理員 ");
				//errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_adma_add);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//			System.out.println("進來這裡?");
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("admin_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸管理員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer admin_no = null;
//				try {
//					admin_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("管理員編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				AdmService admSvc = new AdmService();
//				AdmVO admVO = admSvc.getOneAdm(admin_no);
//				if (admVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("admVO", admVO); // 資料庫取出的admVO物件,存入req
//				String url = "/Back/adm/user/listOneAdm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdm.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("admin_no");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸管理員編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer admin_no = null;
//				try {
//					admin_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("管理員編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				AdmService admSvc = new AdmService();
//				AdmVO admVO = admSvc.getOneAdm(admin_no);
//				if (admVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("admVO", admVO); // 資料庫取出的admVO物件,存入req
//				String url = "/Back/adm/user/listOneAdm.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdm.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/Back/adm/user/adm_change.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
		
		
		


		
		

	}
}
