package com.fbcm.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

import com.TicketNo.model.TicketNoService;
import com.TicketNo.model.TicketNoVO;
import com.TicketType.model.TicketTypeService;
import com.TicketType.model.TicketTypeVO;
import com.Tickettypeall.model.newTestCompositeQuery;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig
public class FbcmServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//--------------------------------------------------店家端使用----------------------------------------------//	
//--------------------------------------------------登入使用----------------------------------------------//		
		if ("fbcm_login".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			
			String login_errorurl01 = "/login/login.jsp"; //有錯誤要轉導到的地方
			String login_okurl01 = "/Font/fbcm/fbcm_index.jsp";
			
			try{
				/***************************1.接收請求參數*****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeService = new StoreService();
				StoreVO storeVO = storeService.getOneStore(store_no);	
				/*************************** 3.創造HttpSession資訊**********************************/
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				session.setAttribute("storeno",store_no); // 在session 放入店家編號
				/*************************** 4.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeVO", storeVO);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(login_okurl01);
				successView.forward(req, res);			
			}			
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(login_errorurl01);
				failureView.forward(req, res);				
			}
		}
//--------------------------------------------------返回使用----------------------------------------------//
		if ("back".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			
			String logingoto = "/Font/fbcm/fbcm_index.jsp";
			String loginno = "/login/login.jsp";
			try{
				/*************************** 1.查詢HttpSession資訊**********************************/
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				if (session.getAttribute("storeno") == null ){
					RequestDispatcher successView = req.getRequestDispatcher(loginno);
					successView.forward(req, res);	
				}
				else{
					Integer store_no = (Integer) session.getAttribute("storeno");
					/*************************** 2.開始查詢資料 ****************************************/
					StoreService storeService = new StoreService();
					StoreVO storeVO = storeService.getOneStore(store_no);	
					/*************************** 4.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("storeVO", storeVO);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(logingoto);
					successView.forward(req, res);		
				}
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(loginno);
				failureView.forward(req, res);	
			}			
		}
		
//---------------------------------------------從第一層選單 fbcm_index 的動作轉換 --------------------------------------------------------------//	
		
//------------------------------------店家頁面管理系統 按下申請團購劵 轉換到 申請團購劵的頁面 ---------------------------------------------------//
		if ("font_fbcm_add_tickettype".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			String login_errorurl01 = "/login/login11.jsp"; //有錯誤要轉導到的地方
			String login_okurl01 = "/Font/fbcm/font_fbcm_add_tickettype.jsp";
			try{
				/***************************1.接收請求參數***************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeSvc = new StoreService();							
				TicketTypeService tickettypeSvc = new TicketTypeService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				/*************************** 3.測試HttpSession資訊**********************************/
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				session.setAttribute("storeno",store_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				session.setAttribute("storeVO", storeVO);    // 資料庫取出的set物件,存入request
			
				RequestDispatcher successView = req.getRequestDispatcher(login_okurl01);
			
				successView.forward(req, res);				
			}
			catch(Exception e){
				
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(login_okurl01);
				failureView.forward(req, res);
			}
		}
		
//------------------------------------店家頁面管理系統 按下查詢審核中團購劵 轉換到 審核團購卷的頁面 ---------------------------------------------------//
		if ("font_fbcm_nook_tickettype".equals(action)){	//按下查詢尚未審核  從 fbcm_index.jsp 轉送 fbcm_listnoOK_tickettype.jsp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//以上為把錯誤訊息丟入req裡
			String login_errorurl01 = "/login/login.jsp"; //有錯誤要轉導到的地方
			String login_okurl01 = "/Font/fbcm/font_fbcm_nook_tickettype.jsp";
			try{
				/***************************1.接收請求參數***************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				String  store_no_String = store_no.toString();
				/*************************** 2.開始查詢資料 ****************************************/
//				StoreService storeSvc = new StoreService();
//				StoreVO storeVO = storeSvc.getOneStore(store_no);
				
				Map<String,String[]> map = new TreeMap<String,String[]>();
				map.put("store_no",new String[]{ store_no_String });
				map.put("tickets_state",new String[]{ "0"});
				TicketTypeService ticketTypeService = new TicketTypeService();
				List<TicketTypeVO> list = ticketTypeService.getAll(map);
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				session.setAttribute("storeno",store_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list",list);
				RequestDispatcher successView = req.getRequestDispatcher(login_okurl01);
				successView.forward(req, res);				
			}
			catch(Exception e){
				
			}
			
		}
		
//------------------------------------店家頁面管理系統 按下查詢所有團購劵 轉換到 所有團購卷的頁面 ---------------------------------------------------//
		if ("font_fbcm_all_tickettype".equals(action)){ //按下查詢尚未審核  從 fbcm_index.jsp 轉送 fbcm_listnoOK_tickettype.jsp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("123");
			//以上為把錯誤訊息丟入req裡
			String login_errorurl01 = "/login/login.jsp"; //有錯誤要轉導到的地方
			String login_okurl01 = "/Font/fbcm/font_fbcm_all_tickettype.jsp";
			try{
				/***************************1.接收請求參數***************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				String  store_no_String = store_no.toString();
				/*************************** 2.開始查詢資料 ****************************************/
				Map<String,String[]> map = new TreeMap<String,String[]>();
				map.put("store_no",new String[]{ store_no_String });
				TicketTypeService ticketTypeService = new TicketTypeService();
				List<TicketTypeVO> list = ticketTypeService.getAll(map);
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				session.setAttribute("storeno",store_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list",list);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(login_okurl01);
				successView.forward(req, res);
				
			}catch(Exception e){
				
			}
		}
		
		
		
		
		
//---------------------------------------------從第二層選單 fbcm_index --> 申請團購劵 () ----> 辨識部分 -------- -----------------------------------//
//------------------------------------申請團購劵的頁面  的錯誤處理 與 正確新增轉頁 ------------------------------------------------------------------//	
		if ("font_insert_tickettype".equals(action)) { // font_add_tickettype.jsp 過來的錯誤辨識 與 送出新增
			// 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String inserterrorurl = "/Font/fbcm/font_fbcm_add_tickettype.jsp";
			String insertokurl ="/Font/fbcm/font_fbcm_addok_tickettype.jsp";
			//暫時可以轉換到all
			try{
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				/* 團購券種類編號 */
//				Integer tickets_type_no = null;
//				try {
//					tickets_type_no = new Integer(req.getParameter("tickets_type_no").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("團購券種類編號錯誤.請找開發人員");
//					tickets_type_no = 0;
//				}				
//				不用PK 主鍵 在後面addTicketType會自動用SQL增加
				/* 團購券名稱 */
				String tickets_type_name = null;
				try{
					tickets_type_name = new String(req.getParameter("tickets_type_name").trim());					
				}catch(Exception e){
					errorMsgs.add("請輸入團購劵總數量.");
					tickets_type_name = "";
				}
				
				/* 上架時間 處理*/
				java.sql.Date upper_date = null;				
				DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
//				因為格式 特別 做格式轉換
				try{					
					java.util.Date du  = df1.parse(req.getParameter("upper_date").trim());
					upper_date = new java.sql.Date(du.getTime());					
//					upper_date = java.sql.Date.valueOf(req.getParameter("upper_date").trim());
				}  catch (Exception e) {
//					upper_date =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入上架時間!");					
				}
				
				/* 下架時間 處理*/
				java.sql.Date lower_date = null;
				DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
//				因為格式 特別 做格式轉換
				try{
					java.util.Date dd  = df2.parse(req.getParameter("lower_date").trim());
					lower_date = new java.sql.Date(dd.getTime());	
					
//					lower_date = java.sql.Date.valueOf(req.getParameter("lower_date").trim());
				}  catch (Exception e) {
//					lower_date =new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入下架時間!");
				}
				/* 團購劵總數量 */				
				Integer tickets_total = null;
				/* 剩餘數量 */
				Integer tickets_quantity = null;
				try {
					tickets_total = new Integer(req.getParameter("tickets_total").trim());
					tickets_quantity = new Integer(req.getParameter("tickets_total").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入團購劵總數量.");
					tickets_quantity = 0;
					tickets_total = 0;
					
				}
				/* 團購劵單價格 */
				Integer tickets_price = null;
				try {
					tickets_price = new Integer(req.getParameter("tickets_price").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入團購劵單價格.");
					tickets_price = null;
				}
				
				/* 團購劵狀態 */
				Integer tickets_state = 0;
				/* 剛申請尚未審核 所以狀態強制設為 設定尚未審核 */

				
				/* 店家編號 */
				Integer store_no = null;
				try {
					store_no = new Integer(req.getParameter("store_no").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("店家編號異常");
					store_no = null;
				}
				
				/* 團購劵說明 */
				String tickets_ex = null;
				try {
					tickets_ex = new String(req.getParameter("tickets_ex").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("請輸入店家編號.");
					tickets_ex = "";
				}
				/* 上傳圖片轉換 */
				byte[] tickets_image = null;
				Part part = req.getPart("tickets_image");
				try{
					InputStream in =part.getInputStream();
					tickets_image = new byte[in.available()];
					in.read(tickets_image);
					in.close();
				}catch(Exception e){
					errorMsgs.add("圖片");
				}
				
				TicketTypeVO ticketTypeVO = new TicketTypeVO();
//				ticketTypeVO.setTickets_type_no(tickets_type_no);
//				不用PK 主鍵 在後面addTicketType會自動用SQL增加
				ticketTypeVO.setTickets_type_name(tickets_type_name);
				ticketTypeVO.setUpper_date(upper_date);
				ticketTypeVO.setLower_date(lower_date);
				ticketTypeVO.setTickets_total(tickets_total);
				ticketTypeVO.setTickets_quantity(tickets_quantity);
				ticketTypeVO.setTickets_price(tickets_price);
				ticketTypeVO.setTickets_state(tickets_state);
				ticketTypeVO.setStore_no(store_no);
				ticketTypeVO.setTickets_ex(tickets_ex);
				ticketTypeVO.setTickets_image(tickets_image);
				
				if (!errorMsgs.isEmpty()){
					req.setAttribute("ticketTypeVO", ticketTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req (不懂 等解釋)
					RequestDispatcher failurView = req.getRequestDispatcher(inserterrorurl);
					failurView.forward(req,res);
					return; //程式中斷
				}
				/***************************2.開始新增資料*****************************************/
				TicketTypeService ticktypeservice = new TicketTypeService();
				ticktypeservice.addTicketType(tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex, tickets_image);
				TicketTypeVO tickettypevo1 =ticktypeservice.getnewticketno(store_no);
				Integer typeno = tickettypevo1.getTickets_type_no();
				TicketTypeVO tickettypevo2 = ticktypeservice.getOneTicketType(typeno);
				HttpSession session = req.getSession();
				//System.out.println(session.getId()); //測試取得Session 的ID
				session.setAttribute("storeno",store_no);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("tickettypevo2", tickettypevo2); 
				session.setAttribute("tickettypevo2", tickettypevo2); 
				RequestDispatcher dispatcher = req.getRequestDispatcher(insertokurl);
				dispatcher.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/					
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(inserterrorurl);
				failureView.forward(req, res);
			}
		}
		
//---------------------------------------------從第二層選單  -------- -----------------------------------//
//------------------------------------font_fbcm_nook_tickettype.jsp --> 刪除團購劵 ------------------------------------------------------------------//	
		if ("delete".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.getParameter("whichPage");
			String deleteurl = "/Font/fbcm/font_fbcm_nook_tickettype.jsp";
			req.setAttribute("errorMsgs", errorMsgs);
			try{
				/***************************1.接收請求參數***************************************/
				Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
				Integer store_no = new Integer(req.getParameter("store_no"));
				/***************************2.開始刪除資料***************************************/
				TicketTypeService ticketTypeService = new TicketTypeService();
				ticketTypeService.deleteTicketType(tickets_type_no);
				/***************************3.刪除完成,需要在查詢一次***********/
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				//System.out.println(store_no);
				
				String  store_no_String = store_no.toString();
				/*************************** 2.開始查詢資料 ****************************************/
				Map<String,String[]> map = new TreeMap<String,String[]>();
				map.put("store_no",new String[]{ store_no_String });
				TicketTypeService ticketTypeService_a = new TicketTypeService();
				List<TicketTypeVO> list = ticketTypeService_a.getAll(map);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list",list);    // 資料庫取出的set物件,存入request				
				
				
				RequestDispatcher successView = req.getRequestDispatcher(deleteurl);
				successView.forward(req,res);
			}catch(Exception e){
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(deleteurl);
				failureView.forward(req, res);
			}
		}
//---------------------------------------------從第二層選單  -------- -----------------------------------//
//------------------------------------font_fbcm_nook_tickettype.jsp --> 修改團購劵 ---> 頁面 (第一次 ) ------------------------------------------------------------------//	
				if ("getOne_For_Update".equals(action)) { 
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					//以上為把錯誤訊息丟入req裡
					String okurl01 = "/Font/fbcm/fbnt_fbcm_update.jsp";
					String errorurl02 = "/Font/Login/storelogin.jsp"; //有錯誤要轉導到的地方
					try{
						/***************************1.接收請求參數****************************************/
						Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
						
						//因為是 所以不會有錯誤的問題 直接做查詢的功能
						/***************************2.開始查詢資料*****************************************/
						TicketTypeService tickertypeservice = new TicketTypeService();
						//new 一個M的Service 
						TicketTypeVO tickettypevo = tickertypeservice.getOneTicketType(tickets_type_no);
						//使用M的Service 的 methon:getOneTicketType 取得TicketTypeVO型態的資料
						String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
						
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("tickettypevO", tickettypevo);
						// 從前方方法取出資料庫的tickettypevO物件,存入req
						RequestDispatcher sucessView = req.getRequestDispatcher(okurl01);
						sucessView.forward(req,res);
						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e){
						errorMsgs.add("無法取得資料 錯誤資訊:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(errorurl02);
						failureView.forward(req,res);
					}					
				}
		
//------------------------------------font_fbcm_nook_tickettype.jsp --> 修改團購劵 ---> 頁面 -> 送出 (第二次 ) ------------------------------------------------------------------//	
				if ("font_fbcm_update".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					
					String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
					//以上為把錯誤訊息丟入req裡
					String font_fbcm_update_ok_url = "/Font/fbcm/font_fbcm_nook_tickettype.jsp";		  //正確要轉導的網頁
					String font_fbcm_update_no_url = "/Font/fbcm/fbnt_fbcm_update.jsp"; 		  //有錯誤要轉導到的地方
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						/* 團購券種類編號 */ /*正常不可能錯誤 因非使用者輸入*/
						Integer tickets_type_no = null;
						try {
							tickets_type_no = new Integer(req.getParameter("tickets_type_no").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("團購券種類編號錯誤.請找開發人員");
							tickets_type_no = 0;
						}
						
						/* 團購券名稱 */
						
						String tickets_type_name = null;
						try {
							tickets_type_name = new String(req.getParameter("tickets_type_name").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購券名稱.");
							tickets_type_name = "";
						}
						
						/* 上架時間 處理*/
						java.sql.Date upper_date = null;
						try{
							upper_date = java.sql.Date.valueOf(req.getParameter("upper_date").trim());
						}  catch (IllegalArgumentException e) {
							upper_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入上架時間!");
						}
						/* 下架時間 處理*/
						java.sql.Date lower_date = null;
						try{
							lower_date = java.sql.Date.valueOf(req.getParameter("lower_date").trim());
						}  catch (IllegalArgumentException e) {
							lower_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入下架時間!");
						}
						/* 團購劵總數量 */
						Integer tickets_total = null;
						try {
							tickets_total = new Integer(req.getParameter("tickets_total").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵總數量.");
							tickets_total = 0;
						}					
						
						/* 團購劵單價格 */
						Integer tickets_price = null;
						try {
							tickets_price = new Integer(req.getParameter("tickets_price").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵單價格.");
							tickets_price = null;
						}
						/* 團購劵說明 */
						String tickets_ex = null;
						try {
							tickets_ex = new String(req.getParameter("tickets_ex").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵說明.");
							tickets_ex = "";
						}						
						/* 剩餘數量 */
						Integer tickets_quantity = 0; /*因為尚未通過審核 還沒創造團購劵*/
						/* 團購劵狀態 */
						Integer tickets_state = 0; /*因為尚未通過審核  所以狀態為尚未審核 */
						/* 店家編號 */
						Integer store_no = null;						
						
						try {
							store_no = new Integer(req.getParameter("store_no").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入店家編號.");
							store_no = null;
						} catch (Exception e) {
							errorMsgs.add("系統錯誤.請找開發人員");
							store_no = null;
						}
						
						TicketTypeVO ticketTypeVO = new TicketTypeVO();
						
						long imglenght = 0;
						/* 上傳圖片轉換 */
						byte[] tickets_image = null;
						Part part = req.getPart("tickets_image");
						InputStream in =part.getInputStream();
						try{
							imglenght = in.available();
							tickets_image = new byte[in.available()];
							in.read(tickets_image);
							in.close();
						}catch(Exception e){
							System.out.println("圖片上傳GG中");
						}
						
						ticketTypeVO.setTickets_type_no(tickets_type_no);
						ticketTypeVO.setTickets_type_name(tickets_type_name);
						ticketTypeVO.setUpper_date(upper_date);
						ticketTypeVO.setLower_date(lower_date);
						ticketTypeVO.setTickets_total(tickets_total);
						ticketTypeVO.setTickets_quantity(tickets_quantity);
						ticketTypeVO.setTickets_price(tickets_price);
						ticketTypeVO.setTickets_state(tickets_state);
						ticketTypeVO.setStore_no(store_no);
						ticketTypeVO.setTickets_ex(tickets_ex);
						ticketTypeVO.setTickets_image(tickets_image);
						
						
						if (!errorMsgs.isEmpty()){
							req.setAttribute("tickettypevO", ticketTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req (不懂 等解釋)
							RequestDispatcher failurView = req.getRequestDispatcher(font_fbcm_update_no_url);
							failurView.forward(req,res);
							return; //程式中斷
						}
						/***************************2.開始修改資料*****************************************/
						TicketTypeService ticketTypeService = new TicketTypeService();
						if (imglenght == 0){					
							ticketTypeVO = ticketTypeService.updatenoimgTicketType(tickets_type_no, tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex);
						}else{
							ticketTypeVO = ticketTypeService.updateTicketType(tickets_type_no, tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex, tickets_image);
						}
						/***************************1.接收請求參數***************************************/						
						String  store_no_String = store_no.toString();
						/*************************** 2.開始查詢資料 ****************************************/
//						StoreService storeSvc = new StoreService();
//						StoreVO storeVO = storeSvc.getOneStore(store_no);
						
						Map<String,String[]> map = new TreeMap<String,String[]>();
						map.put("store_no",new String[]{ store_no_String });
						map.put("tickets_state",new String[]{ "0"});
						TicketTypeService ticketTypeService1 = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService1.getAll(map);
						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);						
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						RequestDispatcher failurView = req.getRequestDispatcher(requestURL);
						failurView.forward(req,res);
						
						/***************************其他可能的錯誤處理*************************************/						
					}catch(Exception e){
						errorMsgs.add("修改資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher(font_fbcm_update_no_url);
						failureView.forward(req, res);
					}
						
				}
//--------------------------------------------------店家端結束----------------------------------------------//
				
				
				
//--------------------------------------------------後端使用----------------------------------------------//
				
//(狀態修改頁面轉換-----------						修改狀態				------------------------------------------//
				if ("stateserver".equals(action)){					
					String stateserver = req.getParameter("stateserver");					
					
					String login_state00 = "/Ador/fbcm/ador_fbcm_select0_tickettype.jsp";
					String login_state01 = "/Ador/fbcm/ador_fbcm_select1_tickettype.jsp";
					String login_state02 = "/Ador/fbcm/ador_fbcm_select2_tickettype.jsp";
					String login_state03 = "/Ador/fbcm/ador_fbcm_select3_tickettype.jsp";
					String login_state04 = "/Ador/fbcm/ador_fbcm_select4_tickettype.jsp";
					String login_state05 = "/Ador/fbcm/ador_fbcm_select5_tickettype.jsp";
					
					
					Map<String,String[]> map = new TreeMap<String,String[]>();
					/* 只顯示尚未審核 */
					if("0".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "0"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state00);
						successView.forward(req, res);
					}
					/* 只顯示審核已通過 */
					else if ("1".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "1"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state01);
						successView.forward(req, res);
						
					}
					/* 只顯示上架中 */
					else if ("2".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "2"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state02);
						successView.forward(req, res);
						
					}
					/* 只顯示下架中 */
					else if ("3".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "3"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state03);
						successView.forward(req, res);
						
					}
					else if ("4".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "4"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state04);
						successView.forward(req, res);
						
					}
					/*	只顯示審核未通過	*/
					else if ("5".equals(stateserver)){
						/*************************** 1.開始查詢資料 ****************************************/
						map.put("tickets_state",new String[]{ "5"});
						TicketTypeService ticketTypeService = new TicketTypeService();
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(login_state05);
						successView.forward(req, res);						
					}					
				}
//修改狀態---------------------------------ador_fbcm_select_all_tickettype.jsp傳送過來的狀態改變--------------------------------------------//
				if ("all_state_chage".equals(action)){
					String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
					//String all_state_chage_ok_url = "/Ador/fbcm/ador_fbcm_select_all_tickettype.jsp";
					String all_state_chage_no_url = requestURL;
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					//以上為把錯誤訊息丟入req裡
					
					String state_chage = req.getParameter("state_chage");//狀態要改???					
					/*************************** 1.開始查詢資料 ****************************************/
					/*							    查詢當前狀態									*/
					Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));							
					TicketTypeService ticketTypeService = new TicketTypeService();
					/*Integer state =ticketTypeService.getOneTicketType(tickets_type_no).getTickets_state();*/
					/***********************************************************************************/
					//審核不通過
					if("5".equals(state_chage)){
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,5);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 審核不通過");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}	
						
					}
					//審核通過
					else if("o1".equals(state_chage)) {
						try{						
							ticketTypeService.getState0to1(tickets_type_no);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 審核通過");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}
					}
					//上架通過
					else if("o2".equals(state_chage)) {
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,2);	
							System.out.println("2");
							errorMsgs.add(tickets_type_no + "狀態已修改成 上架");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}						
					}
					//下架通過
					else if("o3".equals(state_chage)) {
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,3);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 下架");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}							
					}
					//售完通過
					else if("o4".equals(state_chage)) {
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,4);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 售完");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}						
					}
					//未審通過
					else if("o5".equals(state_chage)) {
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,5);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 未通過");
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);	
						}
						catch(Exception e){
							errorMsgs.add("狀態改變錯誤:"+e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher(all_state_chage_no_url);
							failureView.forward(req, res);
						}
					}else{
						errorMsgs.add("狀態改變錯誤");
						RequestDispatcher failureView = req
								.getRequestDispatcher(all_state_chage_no_url);
						failureView.forward(req, res);						
					}					
				}
//修改狀態----------------------------------ador_fbcm_select_all_tickettype.jsp傳送過來的狀態改變結束--------------------------------------------//
				if ("OkorNo".equals(action)){
					String okurl = "/Ador/fbcm/ador_fbcm_selectnono_tickettype.jsp";
					String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
					String error = "/Ador/fbcm/ador_fbcm_select_all_tickettype.jsp";
										
					String login_state00 = "/Ador/fbcm/ador_fbcm_select0_tickettype.jsp";
					String login_state01 = "/Ador/fbcm/ador_fbcm_select1_tickettype.jsp";
					String login_state02 = "/Ador/fbcm/ador_fbcm_select2_tickettype.jsp";
					String login_state03 = "/Ador/fbcm/ador_fbcm_select3_tickettype.jsp";
					String login_state04 = "/Ador/fbcm/ador_fbcm_select4_tickettype.jsp";
					String login_state05 = "/Ador/fbcm/ador_fbcm_select5_tickettype.jsp";
										
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					//以上為把錯誤訊息丟入req裡
					
					String areyouok = req.getParameter("OkorNo"); //狀態要改?
					String pagee = req.getParameter("whichPage"); //頁數
					String pageee = req.getParameter("stateserver"); //狀態
					

					/*************************** 1.開始查詢資料 ****************************************/
					TicketTypeService ticketTypeService = new TicketTypeService();
					/*							    查詢當前狀態									*/

					/* 假如已經有錯誤訊息 又上下頁 就會發生這種錯誤 */
					if ( req.getParameter("tickets_type_no") == null){
						if (pagee!= null && pageee!= null){
																
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if (pageee.equals("0")){
								requestURL = login_state00;
								map.put("tickets_state",new String[]{ "0"});
								System.out.println("因為錯誤 進入這裡0");
							}
							else if (pageee.equals("1")){
								requestURL = login_state01;
								map.put("tickets_state",new String[]{ "1"});
								System.out.println("因為錯誤 進入這裡1");
							}
							else if (pageee.equals("2")){
								requestURL = login_state02;
								map.put("tickets_state",new String[]{ "2"});
								System.out.println("因為錯誤 進入這裡2");
							}
							else if (pageee.equals("3")){
								requestURL = login_state03;
								map.put("tickets_state",new String[]{ "3"});
								System.out.println("因為錯誤 進入這裡3");
							}
							else if (pageee.equals("4")){
								requestURL = login_state04;
								map.put("tickets_state",new String[]{ "4"});
								System.out.println("因為錯誤 進入這裡4");
							}
							else if (pageee.equals("5")){
								requestURL = login_state05;
								map.put("tickets_state",new String[]{ "5"});
								System.out.println("因為錯誤 進入這裡5");
							}
							else{
								System.out.println("Erroe333");
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷							
						}
					}
					
					Integer tickets_type_no = 0;	
					String  tickets_type_no_string = "";
					//假如轉型錯誤
					try{
						tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
						tickets_type_no_string = new String(req.getParameter("tickets_type_no"));
					}catch (Exception e) {
						System.out.println("Erroe123");
						RequestDispatcher failureView = req.getRequestDispatcher(error);
						failureView.forward(req, res);
						return; //程式中斷
					}

					
					
					if("o1".equals(areyouok))	{
						try{						
							ticketTypeService.getState0to1(tickets_type_no);
							errorMsgs.add(tickets_type_no + "狀態已修改成 審核通過");
						}
						catch(Exception e){
							errorMsgs.add(tickets_type_no + "狀態改變錯誤:"+e.getMessage());
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if ( requestURL.equals(login_state00) ){
								map.put("tickets_state",new String[]{ "0"});
							}
							else if ( requestURL.equals(login_state01) ){
								map.put("tickets_state",new String[]{ "1"});
							}
							else if ( requestURL.equals(login_state02) ){
								map.put("tickets_state",new String[]{ "2"});
							}
							else if ( requestURL.equals(login_state03) ){
								map.put("tickets_state",new String[]{ "3"});
							}
							else if ( requestURL.equals(login_state04) ){
								map.put("tickets_state",new String[]{ "4"});
							}
							else if ( requestURL.equals(login_state05) ){
								map.put("tickets_state",new String[]{ "5"});
							}
							else{
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷
						}						
					}
					else if("o2".equals(areyouok)){
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,2);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 上架");
						}
						catch(Exception e){
							errorMsgs.add(tickets_type_no + "狀態改變錯誤:"+e.getMessage());
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if ( requestURL.equals(login_state00) ){
								map.put("tickets_state",new String[]{ "0"});
							}
							else if ( requestURL.equals(login_state01) ){
								map.put("tickets_state",new String[]{ "1"});
							}
							else if ( requestURL.equals(login_state02) ){
								map.put("tickets_state",new String[]{ "2"});
							}
							else if ( requestURL.equals(login_state03) ){
								map.put("tickets_state",new String[]{ "3"});
							}
							else if ( requestURL.equals(login_state04) ){
								map.put("tickets_state",new String[]{ "4"});
							}
							else if ( requestURL.equals(login_state05) ){
								map.put("tickets_state",new String[]{ "5"});
							}
							else{
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷
						}						
					}
					else if("o3".equals(areyouok)){
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,3);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 下架");
						}
						catch(Exception e){
							errorMsgs.add(tickets_type_no + "狀態改變錯誤:"+e.getMessage());
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if ( requestURL.equals(login_state00) ){
								map.put("tickets_state",new String[]{ "0"});
							}
							else if ( requestURL.equals(login_state01) ){
								map.put("tickets_state",new String[]{ "1"});
							}
							else if ( requestURL.equals(login_state02) ){
								map.put("tickets_state",new String[]{ "2"});
							}
							else if ( requestURL.equals(login_state03) ){
								map.put("tickets_state",new String[]{ "3"});
							}
							else if ( requestURL.equals(login_state04) ){
								map.put("tickets_state",new String[]{ "4"});
							}
							else if ( requestURL.equals(login_state05) ){
								map.put("tickets_state",new String[]{ "5"});
							}
							else{
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷
						}		
					}
					else if("o4".equals(areyouok)){
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,4);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 售完");
						}
						catch(Exception e){
							errorMsgs.add(tickets_type_no + "狀態改變錯誤:"+e.getMessage());
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if ( requestURL.equals(login_state00) ){
								map.put("tickets_state",new String[]{ "0"});
							}
							else if ( requestURL.equals(login_state01) ){
								map.put("tickets_state",new String[]{ "1"});
							}
							else if ( requestURL.equals(login_state02) ){
								map.put("tickets_state",new String[]{ "2"});
							}
							else if ( requestURL.equals(login_state03) ){
								map.put("tickets_state",new String[]{ "3"});
							}
							else if ( requestURL.equals(login_state04) ){
								map.put("tickets_state",new String[]{ "4"});
							}
							else if ( requestURL.equals(login_state05) ){
								map.put("tickets_state",new String[]{ "5"});
							}
							else{
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷
						}								
					}
					else if("o5".equals(areyouok)){
						try{						
							ticketTypeService.updatestateTicketType(tickets_type_no,5);	
							errorMsgs.add(tickets_type_no + "狀態已修改成 未通過");
						}
						catch(Exception e){
							errorMsgs.add(tickets_type_no + "狀態改變錯誤:"+e.getMessage());
							Map<String,String[]> map = new TreeMap<String,String[]>();
							if ( requestURL.equals(login_state00) ){
								map.put("tickets_state",new String[]{ "0"});
							}
							else if ( requestURL.equals(login_state01) ){
								map.put("tickets_state",new String[]{ "1"});
							}
							else if ( requestURL.equals(login_state02) ){
								map.put("tickets_state",new String[]{ "2"});
							}
							else if ( requestURL.equals(login_state03) ){
								map.put("tickets_state",new String[]{ "3"});
							}
							else if ( requestURL.equals(login_state04) ){
								map.put("tickets_state",new String[]{ "4"});
							}
							else if ( requestURL.equals(login_state05) ){
								map.put("tickets_state",new String[]{ "5"});
							}
							else{
								RequestDispatcher failureView = req.getRequestDispatcher(error);
								failureView.forward(req, res);
								return; //程式中斷
							}
							/*************************** 1.開始查詢資料 ****************************************/
							List<TicketTypeVO> list = ticketTypeService.getAll(map);
							/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
							req.setAttribute("list",list);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
							return; //程式中斷
						}						
					}
					else {
						errorMsgs.add(tickets_type_no + "狀態改變錯誤:");
						Map<String,String[]> map = new TreeMap<String,String[]>();
						if ( requestURL.equals(login_state00) ){
							map.put("tickets_state",new String[]{ "0"});
						}
						else if ( requestURL.equals(login_state01) ){
							map.put("tickets_state",new String[]{ "1"});
						}
						else if ( requestURL.equals(login_state02) ){
							map.put("tickets_state",new String[]{ "2"});
						}
						else if ( requestURL.equals(login_state03) ){
							map.put("tickets_state",new String[]{ "3"});
						}
						else if ( requestURL.equals(login_state04) ){
							map.put("tickets_state",new String[]{ "4"});
						}
						else if ( requestURL.equals(login_state05) ){
							map.put("tickets_state",new String[]{ "5"});
						}
						else{
							RequestDispatcher failureView = req.getRequestDispatcher(error);
							failureView.forward(req, res);
							return; //程式中斷
						}
						/*************************** 1.開始查詢資料 ****************************************/
						List<TicketTypeVO> list = ticketTypeService.getAll(map);
						/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("list",list);
						RequestDispatcher successView = req.getRequestDispatcher(requestURL);
						successView.forward(req, res);
						return; //程式中斷
					}
					
					/*************************** 從新查詢資料 ****************************************/
					Map<String,String[]> map = new TreeMap<String,String[]>();
					map.put("tickets_type_no",new String[]{ tickets_type_no_string});
					List<TicketTypeVO> list = ticketTypeService.getAll(map);
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("list",list);							
					RequestDispatcher successView = req.getRequestDispatcher(okurl);
					successView.forward(req, res);					
				}
//--------------------------------------------------批量修改狀態開始---------------------------------------------------------//	
				// 批量專用 方法
				if ("OkorNo_1".equals(action) || "OkorNo_2".equals(action) || "OkorNo_3".equals(action) || "OkorNo_4".equals(action) || "OkorNo_5".equals(action)){
					String errorurl = "/Ador/fbcm/ador_fbcm_select_all_tickettype.jsp"; // 錯誤之後送到的路徑
					String requestURL = req.getParameter("requestURL"); 				// 送出的來源網頁路徑
					String[] OkorNoAry = req.getParameterValues("checkItem");
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					//批量通過 就值這個
					if ("OkorNo_1".equals(action)){
						if (OkorNoAry != null){
							for (int i=0;i<OkorNoAry.length;i++){
								Integer tickets_type_no = new Integer(OkorNoAry[i]);
								try{
									TicketTypeService ticketTypeService = new TicketTypeService();
									ticketTypeService.getState0to1(tickets_type_no);
									errorMsgs.add(tickets_type_no + "狀態已修改成 審核通過");
								}
								catch(Exception e){
									errorMsgs.add(tickets_type_no +"狀態改變錯誤:"+e.getMessage());
								}	
							}
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷

						}else{
							errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷
						}
					}
					//批量 上架 就值這個
					if ("OkorNo_2".equals(action)){
						if (OkorNoAry != null){
							for (int i=0;i<OkorNoAry.length;i++){
								Integer tickets_type_no = new Integer(OkorNoAry[i]);
								try{
									TicketTypeService ticketTypeService = new TicketTypeService();
									ticketTypeService.updatestateTicketType(tickets_type_no,2);	
									errorMsgs.add(tickets_type_no + "狀態已修改成  上架");
								}
								catch(Exception e){
									errorMsgs.add(tickets_type_no +"狀態改變錯誤:"+e.getMessage());
								}	
							}
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷

						}else{
							errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷
						}
					}
					//批量下架 就值這個
					if ("OkorNo_3".equals(action)){
						if (OkorNoAry != null){
							for (int i=0;i<OkorNoAry.length;i++){
								Integer tickets_type_no = new Integer(OkorNoAry[i]);
								try{
									TicketTypeService ticketTypeService = new TicketTypeService();
									ticketTypeService.updatestateTicketType(tickets_type_no,3);	
									errorMsgs.add(tickets_type_no + "狀態已修改成下架");
								}
								catch(Exception e){
									errorMsgs.add(tickets_type_no +"狀態改變錯誤:"+e.getMessage());
								}	
							}
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷

						}else{
							errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷
						}
					}
					//批量      售完   就值這個
					if ("OkorNo_4".equals(action)){
						if (OkorNoAry != null){
							for (int i=0;i<OkorNoAry.length;i++){
								Integer tickets_type_no = new Integer(OkorNoAry[i]);
								try{
									TicketTypeService ticketTypeService = new TicketTypeService();
									ticketTypeService.updatestateTicketType(tickets_type_no,4);	
									errorMsgs.add(tickets_type_no + "狀態已修改成 售完");
								}
								catch(Exception e){
									errorMsgs.add(tickets_type_no +"狀態改變錯誤:"+e.getMessage());
								}	
							}
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷

						}else{
							errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷
						}
					}
					//批量不通過 就值這個
					if ("OkorNo_5".equals(action)){
						if (OkorNoAry != null){
							for (int i=0;i<OkorNoAry.length;i++){
								Integer tickets_type_no = new Integer(OkorNoAry[i]);
								try{
									TicketTypeService ticketTypeService = new TicketTypeService();
									ticketTypeService.updatestateTicketType(tickets_type_no,5);	
									errorMsgs.add(tickets_type_no + "狀態已修改成 未通過");
								}
								catch(Exception e){
									errorMsgs.add(tickets_type_no +"狀態改變錯誤:"+e.getMessage());
								}	
							}
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷

						}else{
							errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
							RequestDispatcher failureView = req.getRequestDispatcher(errorurl);
							failureView.forward(req, res);
							return; //程式中斷
						}
					}
				}
//--------------------------------------------------批量修改狀態結束---------------------------------------------------------//		
				



//--------------------------------------------------修改狀態結束---------------------------------------------------------//

//管理員手動------------------------------------------新增/修改/刪除 團購劵  -------------------------------------------------------//			

				
				
//管理員手動修改------------------------------------------修改團購劵(第一次轉導)----------------------------------------------------//
				if ("listall_update".equals(action)){ //來自listAllTicketType.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					//以上為把錯誤訊息丟入req裡
					String listall_update_ok_url = "/Ador/fbcm/ador_fbcm_listAll_update_tickettype.jsp";
					String listall_update_no_url = "/Ador/fbcm/ador_fbcm_listAll_tickettype.jsp"; //有錯誤要轉導到的地方
					
					try{
						/***************************1.接收請求參數****************************************/
						Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));						
						//因為是 所以不會有錯誤的問題 直接做查詢的功能
						/***************************2.開始查詢資料*****************************************/
						TicketTypeService tickertypeservice = new TicketTypeService();
						//new 一個M的Service 
						TicketTypeVO tickettypevO = tickertypeservice.getOneTicketType(tickets_type_no);
						//使用M的Service 的 methon:getOneTicketType 取得TicketTypeVO型態的資料
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("tickettypevO", tickettypevO);
						// 從前方方法取出資料庫的tickettypevO物件,存入req
						RequestDispatcher sucessView = req.getRequestDispatcher(listall_update_ok_url);
						sucessView.forward(req,res);						
						/***************************其他可能的錯誤處理**********************************/
					} catch (Exception e){
						errorMsgs.add("無法取得資料 錯誤資訊:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(listall_update_no_url);
						failureView.forward(req,res);
					}					
				}
//------------------------------//
					if ("listall_update_two".equals(action)) {
					
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑
					//以上為把錯誤訊息丟入req裡
					String font_fbcm_update_no_url = "/Ador/fbcm/ador_fbcm_listAll_update_tickettype.jsp"; 		  //有錯誤要轉導到的地方
					try{
						/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
						/* 團購券種類編號 */ /*正常不可能錯誤 因非使用者輸入*/
						Integer tickets_type_no = null;
						try {
							tickets_type_no = new Integer(req.getParameter("tickets_type_no").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("團購券種類編號錯誤.請找開發人員");
							tickets_type_no = 0;
						}
						
						/* 團購券名稱 */
						
						String tickets_type_name = null;
						try {
							tickets_type_name = new String(req.getParameter("tickets_type_name").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購券名稱.");
							tickets_type_name = "";
						}
						
						/* 上架時間 處理*/
						java.sql.Date upper_date = null;
						try{
							upper_date = java.sql.Date.valueOf(req.getParameter("upper_date").trim());
						}  catch (IllegalArgumentException e) {
							upper_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入上架時間!");
						}
						/* 下架時間 處理*/
						java.sql.Date lower_date = null;
						try{
							lower_date = java.sql.Date.valueOf(req.getParameter("lower_date").trim());
						}  catch (IllegalArgumentException e) {
							lower_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入下架時間!");
						}
						/* 團購劵總數量 */
						Integer tickets_total = null;
						try {
							tickets_total = new Integer(req.getParameter("tickets_total").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵總數量.");
							tickets_total = 0;
						}					
						
						/* 團購劵單價格 */
						Integer tickets_price = null;
						try {
							tickets_price = new Integer(req.getParameter("tickets_price").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵單價格.");
							tickets_price = null;
						}
						/* 團購劵說明 */
						String tickets_ex = null;
						try {
							tickets_ex = new String(req.getParameter("tickets_ex").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵說明.");
							tickets_ex = "";
						}						
						/* 剩餘數量 */
						Integer tickets_quantity = null;
						try {
							tickets_quantity = new Integer(req.getParameter("tickets_quantity").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("剩餘數量異常");
							tickets_quantity = 0;
						}
						
						/* 團購劵狀態 */
						Integer tickets_state = null;
						try {
							tickets_state = new Integer(req.getParameter("tickets_state").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵狀態.");
							tickets_state = 1;
						}
						
						/* 店家編號 */
						Integer store_no = null;						
						try {
							store_no = new Integer(req.getParameter("store_no").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入店家編號.");
							store_no = null;
						} catch (Exception e) {
							errorMsgs.add("系統錯誤.請找開發人員");
							store_no = null;
						}
						
						TicketTypeVO ticketTypeVO = new TicketTypeVO();
						
						long imglenght = 0;
						/* 上傳圖片轉換 */
						byte[] tickets_image = null;
						Part part = req.getPart("tickets_image");
						InputStream in =part.getInputStream();
						try{
							imglenght = in.available();
							tickets_image = new byte[in.available()];
							in.read(tickets_image);
							in.close();
						}catch(Exception e){
							System.out.println("圖片上傳GG中");
						}
						
						ticketTypeVO.setTickets_type_no(tickets_type_no);
						ticketTypeVO.setTickets_type_name(tickets_type_name);
						ticketTypeVO.setUpper_date(upper_date);
						ticketTypeVO.setLower_date(lower_date);
						ticketTypeVO.setTickets_total(tickets_total);
						ticketTypeVO.setTickets_quantity(tickets_quantity);
						ticketTypeVO.setTickets_price(tickets_price);
						ticketTypeVO.setTickets_state(tickets_state);
						ticketTypeVO.setStore_no(store_no);
						ticketTypeVO.setTickets_ex(tickets_ex);
						ticketTypeVO.setTickets_image(tickets_image);
						
						
						if (!errorMsgs.isEmpty()){
							req.setAttribute("tickettypevO", ticketTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req (不懂 等解釋)
							RequestDispatcher failurView = req.getRequestDispatcher(font_fbcm_update_no_url);
							failurView.forward(req,res);
							return; //程式中斷
						}
						/***************************2.開始修改資料*****************************************/
						TicketTypeService ticketTypeService = new TicketTypeService();
						if (imglenght == 0){					
							ticketTypeVO = ticketTypeService.updatenoimgTicketType(tickets_type_no, tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex);
						}else{
							ticketTypeVO = ticketTypeService.updateTicketType(tickets_type_no, tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex, tickets_image);
						}
						RequestDispatcher failurView = req.getRequestDispatcher(requestURL);
						failurView.forward(req,res);
						
						/***************************其他可能的錯誤處理*************************************/						
					}catch(Exception e){
						errorMsgs.add("修改資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher(font_fbcm_update_no_url);
						failureView.forward(req, res);
					}
						
				}
//管理員手動刪除-------------------------------------------------刪除團購劵------------------------------------------------------------//
				if ("listall_delete".equals(action)) { // 來自listAllTicketType.jsp
					String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					try{
						/***************************1.接收請求參數***************************************/
						Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
						/***************************2.開始刪除資料***************************************/
						TicketTypeService ticketTypeService = new TicketTypeService();
						ticketTypeService.deleteTicketType(tickets_type_no);
						errorMsgs.add("團購劵" + tickets_type_no +"已被刪除");
						/***************************3.刪除完成,準備轉交(Send the Success view)***********/
						RequestDispatcher successView = req.getRequestDispatcher(requestURL);
						successView.forward(req,res);
					}catch(Exception e){
						errorMsgs.add("刪除資料失敗:"+e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher(requestURL);
						failureView.forward(req, res);
					}
				}
//管理員手動新增---------------------------------第一次進入選擇店家---------------------------------------------------------//				
				if ("ador_fbcm_new_select".equals(action)) { // 來自select_page.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);
					String ador_fbcm_new_select_ok_url = "/Ador/fbcm/ador_fbcm_new_tickettype.jsp";
					String ador_fbcm_new_select_no_url = "/Ador/fbcm/ador_fbcm_new_select_tickettype.jsp";
					try {
						/***************************
						 * 1.接收請求參數 - 輸入格式的錯誤處理
						 **********************/
						String str = req.getParameter("store_no");
						if (str == null || (str.trim()).length() == 0) {
							errorMsgs.add("請輸入店家編號");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher(ador_fbcm_new_select_no_url);
							failureView.forward(req, res);
							return;// 程式中斷
						}

						Integer store_no = null;
						try {
							store_no = new Integer(str);
						} catch (Exception e) {
							errorMsgs.add("店家編號格式不正確");
						}
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher(ador_fbcm_new_select_no_url);
							failureView.forward(req, res);
							return;// 程式中斷
						}
						/***************************
						 * 2.開始查詢資料
						 *****************************************/
						StoreService storeSvc = new StoreService();
						StoreVO storeVO = storeSvc.getOneStore(store_no);
						if (storeVO == null) {
							errorMsgs.add("查無資料");
						}
						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req.getRequestDispatcher(ador_fbcm_new_select_no_url);
							failureView.forward(req, res);
							return;// 程式中斷
						}
						/***************************
						 * 3.查詢完成,準備轉交(Send the Success view)
						 *************/
						req.setAttribute("store_no", store_no); // 資料庫取出的empVO物件,存入req
						RequestDispatcher successView = req.getRequestDispatcher(ador_fbcm_new_select_ok_url); // 成功轉交
						successView.forward(req, res);
						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
						failureView.forward(req, res);

					}
				}
//管理員手動新增---------------------------------第二次進入辨識並且新增---------------------------------------------------------//	
				if ("ador_fbcm_new_tickettype".equals(action)) {
					// 來自addEmp.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					String inserterrorurl = "/Ador/fbcm/ador_fbcm_new_tickettype.jsp";
					String insertokurl ="/Ador/fbcm/ador_fbcm_new_ok_tickettype.jsp";
					//暫時可以轉換到all
					try{
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						/* 團購券種類編號 */
//						Integer tickets_type_no = null;
//						try {
//							tickets_type_no = new Integer(req.getParameter("tickets_type_no").trim());
//						} catch (NumberFormatException e) {
//							errorMsgs.add("團購券種類編號錯誤.請找開發人員");
//							tickets_type_no = 0;
//						}				
//						不用PK 主鍵 在後面addTicketType會自動用SQL增加
						/* 團購券名稱 */
						String tickets_type_name = null;
						try{
							tickets_type_name = new String(req.getParameter("tickets_type_name").trim());					
						}catch(Exception e){
							errorMsgs.add("請輸入團購劵總數量.");
							tickets_type_name = "";
						}
						
						/* 上架時間 處理*/
						java.sql.Date upper_date = null;				
						DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
//						因為格式 特別 做格式轉換
						try{					
							java.util.Date du  = df1.parse(req.getParameter("upper_date").trim());
							upper_date = new java.sql.Date(du.getTime());					
//							upper_date = java.sql.Date.valueOf(req.getParameter("upper_date").trim());
						}  catch (Exception e) {
//							upper_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入上架時間!");					
						}
						
						/* 下架時間 處理*/
						java.sql.Date lower_date = null;
						DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
//						因為格式 特別 做格式轉換
						try{
							java.util.Date dd  = df2.parse(req.getParameter("lower_date").trim());
							lower_date = new java.sql.Date(dd.getTime());	
							
//							lower_date = java.sql.Date.valueOf(req.getParameter("lower_date").trim());
						}  catch (Exception e) {
//							lower_date =new java.sql.Date(System.currentTimeMillis());
							errorMsgs.add("請輸入下架時間!");
						}
						/* 團購劵總數量 */				
						Integer tickets_total = null;
						/* 剩餘數量 */
						Integer tickets_quantity = 0;
						try {
							tickets_total = new Integer(req.getParameter("tickets_total").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵總數量.");
							tickets_quantity = 0;
							tickets_total = 0;
							
						}
						/* 團購劵單價格 */
						Integer tickets_price = null;
						try {
							tickets_price = new Integer(req.getParameter("tickets_price").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵單價格.");
							tickets_price = null;
						}
						
						/* 團購劵狀態 */
						Integer tickets_state = null;
						try {
							tickets_state = new Integer(req.getParameter("tickets_state").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入團購劵狀態.");
							tickets_state = 0;
						}
						
						/* 店家編號 */
						Integer store_no = null;
						try {
							store_no = new Integer(req.getParameter("store_no").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("店家編號異常");
							store_no = null;
						}
						
						/* 團購劵說明 */
						String tickets_ex = null;
						try {
							tickets_ex = new String(req.getParameter("tickets_ex").trim());
						} catch (NumberFormatException e) {
							errorMsgs.add("請輸入店家編號.");
							tickets_ex = "";
						}
						/* 上傳圖片轉換 */
						byte[] tickets_image = null;
						Part part = req.getPart("tickets_image");
						try{
							InputStream in =part.getInputStream();
							tickets_image = new byte[in.available()];
							in.read(tickets_image);
							in.close();
						}catch(Exception e){
							errorMsgs.add("圖片");
						}
						
						TicketTypeVO ticketTypeVO = new TicketTypeVO();
//						ticketTypeVO.setTickets_type_no(tickets_type_no);
//						不用PK 主鍵 在後面addTicketType會自動用SQL增加
						ticketTypeVO.setTickets_type_name(tickets_type_name);
						ticketTypeVO.setUpper_date(upper_date);
						ticketTypeVO.setLower_date(lower_date);
						ticketTypeVO.setTickets_total(tickets_total);
						ticketTypeVO.setTickets_quantity(tickets_quantity);
						ticketTypeVO.setTickets_price(tickets_price);
						ticketTypeVO.setTickets_state(tickets_state);
						ticketTypeVO.setStore_no(store_no);
						ticketTypeVO.setTickets_ex(tickets_ex);
						ticketTypeVO.setTickets_image(tickets_image);
						
						if (!errorMsgs.isEmpty()){
							req.setAttribute("ticketTypeVO", ticketTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req (不懂 等解釋)
							RequestDispatcher failurView = req.getRequestDispatcher(inserterrorurl);
							failurView.forward(req,res);
							return; //程式中斷
						}
						/***************************2.開始新增資料*****************************************/
						TicketTypeService ticktypeservice = new TicketTypeService();
						ticktypeservice.addTicketType(tickets_type_name, upper_date, lower_date, tickets_total, tickets_quantity, tickets_price, tickets_state, store_no, tickets_ex, tickets_image);
						TicketTypeVO tickettypevo1 =ticktypeservice.getnewticketno(store_no);
						Integer typeno = tickettypevo1.getTickets_type_no();
						TicketTypeVO tickettypevo2 = ticktypeservice.getOneTicketType(typeno);
						/***************************3.新增完成,準備轉交(Send the Success view)***********/
						req.setAttribute("tickettypevo2", tickettypevo2); 
						RequestDispatcher dispatcher = req.getRequestDispatcher(insertokurl);
						dispatcher.forward(req,res);
						/***************************其他可能的錯誤處理**********************************/					
					}
					catch(Exception e){
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher(inserterrorurl);
						failureView.forward(req, res);
					}
				}
//管理員查詢狀態---------------------------------第二次進入辨識並且新增---------------------------------------------------------//	
				//--------------AJAX取得選單---------//
				if ("ador_fbcm_usetickets_no_get".equals(action)) {
					JSONArray array = new JSONArray();
					if (!"".equals(req.getParameter("store_no"))){
						Map<String,String[]> map = new TreeMap<String,String[]>();
						String store_no = req.getParameter("store_no");
						map.put("store_no",new String[]{ store_no });
						TicketTypeService dao = new TicketTypeService();
						List<TicketTypeVO> list = dao.getAll(map);
						for (TicketTypeVO outlist:list){
							JSONObject obj = new JSONObject();
							try{
								obj.put("tickets_type_no",outlist.getTickets_type_no());
								obj.put("tickets_type_name", outlist.getTickets_type_name());
							}catch(Exception e){}
							array.add(obj);
						}						
					}
					res.setContentType("text/plain");
					res.setCharacterEncoding("UTF-8");
					PrintWriter out = res.getWriter();
					out.write(array.toString());
					out.flush();
					out.close();					
				}
				//-------------查詢選單----------------//
				if ("ador_fbcm_usetickets_no".equals(action)) {
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					String inserterrorurl = "/Ador/fbcm/ador_fbcm_usetickets_no.jsp";
					String insertokurl ="/Ador/fbcm/ador_fbcm_usetickets_no_detail.jsp";
					try{
						/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
						String tickets_type_no_str = req.getParameter("tickets_type_no");
						/***************************
						 * 2.開始查詢資料
						 *****************************************/
						TicketTypeService tickertypeservice = new TicketTypeService();
						TicketTypeVO tickettypevO = tickertypeservice.getOneTicketType(tickets_type_no);
						if (tickettypevO == null){
							errorMsgs.add("查無資料");
						}
						//如果資料等於空值 當然就是沒有資料
						if (!errorMsgs.isEmpty()){
							//轉導至哪個程式
							RequestDispatcher failureView = req.getRequestDispatcher(inserterrorurl);
							failureView.forward(req, res);
							return; // 程式中斷
						}						
						Map<String,String[]> map = new TreeMap<String,String[]>();
						map.put("tickets_type_no",new String[]{ tickets_type_no_str });						
						TicketNoService dao = new TicketNoService();
						List<TicketNoVO> list =dao.getAll(map);

						Map<String,String[]> map2 = new TreeMap<String,String[]>();
						map.put("tickets_type_no",new String[]{ tickets_type_no_str });
						map.put("tickets_no_status",new String[]{ "0" });	
						TicketNoService dao2 = new TicketNoService();
						Integer time = dao2.getAll(map).size();
						System.out.println(time);
						/***************************3.查詢完成,準備轉交(Send the Success view)***********/
						req.setAttribute("list", list); 
						req.setAttribute("tickettypevO", tickettypevO); 
						RequestDispatcher dispatcher = req.getRequestDispatcher(insertokurl);
						dispatcher.forward(req,res);
						/***************************其他可能的錯誤處理**********************************/						
					}catch(Exception e){
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher(inserterrorurl);
						failureView.forward(req, res);						
					}
				}
				

//--------------------------------------------------後端結束----------------------------------------------//				
		
	}
}
