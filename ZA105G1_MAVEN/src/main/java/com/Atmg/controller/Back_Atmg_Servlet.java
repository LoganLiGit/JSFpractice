package com.Atmg.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.ad.model.AdService;
import com.ad.model.AdVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Back_Atmg_Servlet extends HttpServlet {
	String nourl_back_atmg_list=	"/Back/Atmg/Back_Atmg_List.jsp";
	String nourl_back_atmg_add= 	"/Back/Atmg/Back_Atmg_Add.jsp";
	String nourl_back_atmg_update=	"/Back/Atmg/Back_Atmg_Update.jsp";
	String nourl_back_atmg_one=		"/Back/Atmg/Back_Atmg_One.jsp";
	String nourl_back_atmg_servlet=	"/Back/Atmg/Back_Atmg_Servlet.do";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("ad_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入廣告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer ad_no = null;
				try {
					ad_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("廣告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_no);
				if (adVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adVO", adVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_atmg_one); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("get_update_ad_01".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer ad_no = new Integer(req.getParameter("ad_no"));
				
				/***************************2.開始查詢資料****************************************/
				AdService adSvc = new AdService();
				AdVO adVO = adSvc.getOneAd(ad_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("adVO", adVO);         // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_atmg_update);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
				failureView.forward(req, res);
			}
		}
		
		if ("get_update_ad_02".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer ad_no = new Integer(req.getParameter("ad_no").trim());
				
				/* 上傳圖片轉換 */
				byte[] ad_image = null;
				Part part = req.getPart("ad_images");
				try{
					InputStream in =part.getInputStream();
					ad_image = new byte[in.available()];
					in.read(ad_image);
					in.close();
				}catch(Exception e){
					System.out.println("圖片上傳GG中");
				}
				
				
				java.sql.Date ad_date = null;
				try {
					ad_date = java.sql.Date.valueOf(req.getParameter("ad_date").trim());
				} catch (Exception e) {
					ad_date =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date ad_date_ed = null;
				try {
					ad_date_ed = java.sql.Date.valueOf(req.getParameter("ad_date_ed").trim());
				} catch (Exception e) {
					ad_date_ed =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}

				AdVO adVO = new AdVO();
				adVO.setAd_no(ad_no);
				adVO.setAd_images(ad_image);
				adVO.setAd_date(ad_date);
				adVO.setAd_date_ed(ad_date_ed);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_update);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				AdService adSvc = new AdService();
				adVO = adSvc.updateAd(ad_no,ad_image, ad_date, ad_date_ed);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adVO", adVO); // 資料庫update成功後,正確的的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_atmg_list); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_update);
				failureView.forward(req, res);
			}
		}
		
		if ("insert_ad".equals(action)){ // 來自addAd.jsp的請求  
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數***************************************/
				Integer store_no = new Integer(req.getParameter("store_no").trim());
				
				/* 上傳圖片轉換 */
				byte[] ad_image = null;
				Part part = req.getPart("ad_images");
				try{
					InputStream in =part.getInputStream();
					ad_image = new byte[in.available()];
					in.read(ad_image);
					in.close();
				}catch(Exception e){
					System.out.println("圖片上傳GG中");
				}
				
				java.sql.Date ad_date = null;
				try {
					ad_date = java.sql.Date.valueOf(req.getParameter("ad_date").trim());
				} catch (Exception e) {
					ad_date =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入開始日期!");
				}
				
				java.sql.Date ad_date_ed = null;
				try {
					ad_date_ed = java.sql.Date.valueOf(req.getParameter("ad_date_ed").trim());
				} catch (Exception e) {
					ad_date_ed =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入結束日期!");
				}
				
				AdVO adVO = new AdVO();
				adVO.setStore_no(store_no);
				adVO.setAd_images(ad_image);
				adVO.setAd_date(ad_date);
				adVO.setAd_date_ed(ad_date_ed);
	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adVO", adVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_add);
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.開始新增資料***************************************/
				AdService adSvc = new AdService();
				adVO = adSvc.addAd(store_no, ad_image, ad_date, ad_date_ed);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_atmg_list); // 新增成功後轉交listAllAd.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_add);
				failureView.forward(req, res);
			}
		}
		
		if ("delete_atmg".equals(action)) { // 刪除 delete

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer ad_no = new Integer(req.getParameter("ad_no"));
				
				/***************************2.開始刪除資料***************************************/
				AdService adSvc = new AdService();
				adSvc.deleteAd(ad_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_atmg_list); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_atmg_list);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
	}
}
