package com.Qamg.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qa.model.QaService;
import com.qa.model.QaVO;

public class Back_Qamg_Servlet extends HttpServlet {
	String nourl_back_qamg_list=	"/Back/Qamg/Back_Qamg_List.jsp";
	String nourl_back_qamg_add=		"/Back/Qamg/Back_Qamg_Add.jsp";
	String nourl_back_qamg_update=	"/Back/Qamg/Back_Qamg_Update.jsp";
	String nourl_back_qamg_one=		"/Back/Qamg/Back_Qamg_One.jsp";
	String nourl_back_qamg_servlet=	"/Back/Qamg/Back_Qamg_Servlet.do";
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("select_eqmg".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("qa_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer qa_no = null;
				try {
					qa_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				QaService qa_Svc = new QaService();
				QaVO qaVO = qa_Svc.getOneQa(qa_no);
				if (qaVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("qaVO", qaVO); // 資料庫取出的qaVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_qamg_one); // 成功轉交
																				// listOneQa.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if ("get_update_qa_01".equals(action)) { //第一次按下修改會進來這裡查詢 把所有資料丟給UPDATE 頁面

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer qa_no = new Integer(req.getParameter("qa_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				QaService qaSvc = new QaService();
				QaVO qaVO = qaSvc.getOneQa(qa_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("qaVO", qaVO); // 資料庫取出的qaVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_qamg_update);// 成功轉交
																				// update_qa_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
				failureView.forward(req, res);
			}
		}
		
		if ("get_update_qa_02".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer qa_no = new Integer(req.getParameter("qa_no").trim());
				
				String qa_info = req.getParameter("qa_info").trim();
				if(qa_info.length() == 0){
					errorMsgs.add("請輸入標題");
				}

				String qa_content = req.getParameter("qa_content").trim();
				if(qa_content.length() == 0){
					errorMsgs.add("請輸入內容");
				}
				
				java.sql.Date qa_date = null;
				try {
					qa_date = java.sql.Date.valueOf(req.getParameter("qa_date").trim());
				} catch (Exception e) {
					qa_date =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				QaVO qaVO = new QaVO();
				qaVO.setQa_no(qa_no);
				qaVO.setQa_info(qa_info);
				qaVO.setQa_content(qa_content);
				qaVO.setQa_date(qa_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO); // 含有輸入格式錯誤的qaVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_update);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.updateQa(qa_no, qa_info, qa_content, qa_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				errorMsgs.add("QA 編號: " + qa_no + "已經修改完成 ");
				req.setAttribute("qaVO", qaVO); // 資料庫update成功後,正確的的qaVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_qamg_list); // 修改成功後,轉交listOneQa.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_update);
				failureView.forward(req, res);
			}
		}
		
		if ("insert_qa".equals(action)) { //按下新增QA 就會執行這裡

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String qa_info = req.getParameter("qa_info").trim();
				if(qa_info.length() == 0){
					errorMsgs.add("請輸入標題");
				}

				String qa_content = req.getParameter("qa_content").trim();
				if(qa_content.length() == 0){
					errorMsgs.add("請輸入內容");
				}
				
				Date qa_date = new java.sql.Date(System.currentTimeMillis());
				
				QaVO qaVO = new QaVO();
				qaVO.setQa_info(qa_info);
				qaVO.setQa_content(qa_content);
				qaVO.setQa_date(qa_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("qaVO", qaVO); // 含有輸入格式錯誤的qaVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_add);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				QaService qaSvc = new QaService();
				qaVO = qaSvc.addQa(qa_info, qa_content, qa_date);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				errorMsgs.add("Q & A標題 :" + qa_info + "已經正確新增 ");
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_qamg_list); // 新增成功後轉交listAllQa.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_add);
				failureView.forward(req, res);
			}
		}
		
		if ("delete_qa".equals(action)) { //按下刪除QA 就做這個

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer qa_no = new Integer(req.getParameter("qa_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				QaService qaSvc = new QaService();
				qaSvc.deleteQa(qa_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				
				errorMsgs.add("Q & A 編號 :" + qa_no + " 已經順利刪除 ");
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_qamg_list);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_qamg_list);
				failureView.forward(req, res);
			}
		}
	}

}
