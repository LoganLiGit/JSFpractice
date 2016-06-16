package com.Ntmg.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ann.model.AnnService;
import com.ann.model.AnnVO;

public class Back_Ntmg_Servlet extends HttpServlet {
	String nourl_back_ntmg_list = 	"/Back/Ntmg/Back_Ntmg_List.jsp";
	String nourl_back_ntmg_add = 	"/Back/Ntmg/Back_Ntmg_Add.jsp";
	String nourl_back_ntmg_update = "/Back/Ntmg/Back_Ntmg_Update.jsp";
	String nourl_back_ntmg_one = 	"/Back/Ntmg/Back_Ntmg_One.jsp";
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 	{
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		if ("select_ntmg".equals(action)) { // 來自查詢的請求

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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer ann_no = null;
				try {
					ann_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("annVO", annVO); // 資料庫取出的annVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_ntmg_one); // 成功轉交
																				// listOneAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
				failureView.forward(req, res);
			}
		}

		if ("insert_ntmg".equals(action)) { //按下新增團購劵的錯誤辨識 

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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_add);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.addAnn(ann_info, ann_content, (java.sql.Date) ann_date);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("annVO", annVO); 
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_ntmg_list);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_add);
				failureView.forward(req, res);
			}
		}
	
		if ("get_update_ntmg_01".equals(action)) { 		//如果從總覽(nourl_back_ntmg_list)進來 是按下修改 把資料查完 轉給修改頁面

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer ann_no = new Integer(req.getParameter("ann_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				AnnService annSvc = new AnnService();
				AnnVO annVO = annSvc.getOneAnn(ann_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("annVO", annVO); // 資料庫取出的annVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_ntmg_update);// 成功轉交
																				// update_ann_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
				failureView.forward(req, res);
			}
		}
		
		if ("get_update_ntmg_02".equals(action)) {  //按出修改就會轉到02這裡 做錯誤辨識
			//如果從總覽(nourl_back_ntmg_list)進來 是按下修改 把資料查完 轉給修改頁面 會走01 走完 修改完資料
			//按出修改就會轉到02這裡 做錯誤辨識

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
				} catch (Exception e) {
					ann_date =new java.sql.Date(System.currentTimeMillis());
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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_update);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.updateAnn(ann_no, ann_info, ann_content, ann_date);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("annVO", annVO); // 資料庫update成功後,正確的的annVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_ntmg_list); // 修改成功後,轉交listOneAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_update);
				failureView.forward(req, res);
			}
		}

		if ("delete_ntmg".equals(action)) { 	//假如是刪除公告

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
				errorMsgs.add("公告編號: " + ann_no +" 已經被刪除");
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_ntmg_list);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_ntmg_list);
				failureView.forward(req, res);
			}
		}
	}

}
