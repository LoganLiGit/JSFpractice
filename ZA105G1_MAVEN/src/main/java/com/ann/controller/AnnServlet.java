package com.ann.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.ann.model.*;

public class AnnServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("ann_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ann/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer ann_no = null;
				try {
					ann_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ann/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AnnService ann_Svc = new AnnService();
				AnnVO annVO = ann_Svc.getOneAnn(ann_no);
				if (annVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/ann/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("annVO", annVO); // 資料庫取出的annVO物件,存入req
				String url = "/front/ann_front/listOneAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
																				// listOneAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ann/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllAnn.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數****************************************/
				Integer ann_no = new Integer(req.getParameter("ann_no"));

				/***************************2.開始查詢資料****************************************/
				AnnService annSvc = new AnnService();
				AnnVO annVO = annSvc.getOneAnn(ann_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("annVO", annVO); // 資料庫取出的annVO物件,存入req
				String url = "/ann/update_ann_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_ann_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_ann_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer ann_no = new Integer(req.getParameter("ann_no").trim());
				
				String ann_info = req.getParameter("ann_info").trim();
				if(ann_info.length() == 0){
					errorMsgs.add("請輸入標題");
				}

				String ann_content = req.getParameter("ann_content").trim();
				if(ann_content.length() == 0){
					errorMsgs.add("請輸入內容");
				}
				
				java.sql.Date ann_date = null;
				try {
					ann_date = java.sql.Date.valueOf(req.getParameter("ann_date").trim());
				} catch (IllegalArgumentException e) {
					ann_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				AnnVO annVO = new AnnVO();
				annVO.setAnn_no(ann_no);
				annVO.setAnn_info(ann_info);
				annVO.setAnn_content(ann_content);
				annVO.setAnn_date(ann_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的annVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ann/update_ann_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2..開始修改資料 *****************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.updateAnn(ann_no, ann_info, ann_content, ann_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("annVO", annVO); // 資料庫update成功後,正確的的annVO物件,存入req
				String url = "/ann/listOneAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ann/update_ann_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addAnn.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String ann_info = req.getParameter("ann_info").trim();
				if(ann_info.length() == 0){
					errorMsgs.add("請輸入標題");
				}

				String ann_content = req.getParameter("ann_content").trim();
				if(ann_content.length() == 0){
					errorMsgs.add("請輸入內容");
				}
				
				Date ann_date = new java.sql.Date(System.currentTimeMillis());

				AnnVO annVO = new AnnVO();
				annVO.setAnn_info(ann_info);
				annVO.setAnn_content(ann_content);
				annVO.setAnn_date((java.sql.Date) ann_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的annVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ann/addAnn.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.addAnn(ann_info, ann_content, (java.sql.Date) ann_date);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/ann/listAllAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ann/addAnn.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAnn.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer ann_no = new Integer(req.getParameter("ann_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				AnnService annSvc = new AnnService();
				annSvc.deleteAnn(ann_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/ann/listAllAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
