package com.Bcmg.controller;
//(後端) 團購劵系統 新增/修改/刪除 控制器
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.Part;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.TicketNo.model.TicketNoService;
import com.TicketNo.model.TicketNoVO;
import com.TicketType.model.TicketTypeService;
import com.TicketType.model.TicketTypeVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig
public class Back_Bcmg_Servlet2 extends HttpServlet {
	String nourl_back_bcmg_date_modify_list = "/Back/Bcmg/Data_Modify/Back_Bcmg_Date_Modify_List.jsp";
	String nourl_back_bcmg_date_modify_update = "/Back/Bcmg/Data_Modify/Back_Bcmg_Date_Modify_Update.jsp";
	//資料修改/刪除 專用連結
	String nourl_back_bcmg_manually_list = "/Back/Bcmg/ManuallyAdd/Back_Bcmg_Manually_List.jsp";
	String nourl_back_bcmg_manually_add = "/Back/Bcmg/ManuallyAdd/Back_Bcmg_Manually_Add.jsp";	
	String nourl_back_bcmg_manually_success = "/Back/Bcmg/ManuallyAdd/Back_Bcmg_Manually_Success.jsp";
	//資料手動新增 專用連結
	String nourl_bcmg_use_query_list = "/Back/Bcmg/UseQuery/Back_Bcmg_Use_Query_List.jsp";
	String nourl_bcmg_use_query_one = "/Back/Bcmg/UseQuery/Back_Bcmg_Use_Query_One.jsp";
	//資料查詢  專用連結
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		
//管理員手動------------------------------------------新增/修改/刪除 團購劵  -------------------------------------------------------//	
//管理員手動修改------------------------------------------修改團購劵(第一次轉導)----------------------------------------------------//
			if ("listall_update".equals(action)){ //來自listAllTicketType.jsp的請求
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				//以上為把錯誤訊息丟入req裡
				
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
					RequestDispatcher sucessView = req.getRequestDispatcher(nourl_back_bcmg_date_modify_update);
					sucessView.forward(req,res);						
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e){
					errorMsgs.add("無法取得資料 錯誤資訊:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_bcmg_date_modify_list);
					failureView.forward(req,res);
				}					
			}
//管理員手動修改------------------------------------------修改團購劵(第二次 以輸入資料)----------------------------------------------------//
			if ("listall_update_two".equals(action)) {
			
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
				
				if (tickets_type_name.equals("")){ // 假如名稱沒有填入
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
					RequestDispatcher failurView = req.getRequestDispatcher(nourl_back_bcmg_date_modify_update);
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
						.getRequestDispatcher(nourl_back_bcmg_date_modify_update);
				failureView.forward(req, res);
			}
				
		}
		//管理員手動刪除-------------------------------------------------刪除團購劵------------------------------------------------------------//
		if ("listall_delete".equals(action)) { // 來自listAllTicketType.jsp
				String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
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
					//errorMsgs.add("刪除資料失敗:"+e.getMessage());
					errorMsgs.add("錯誤訊息提示: 只有尚未審核/審核未通過 的可以刪除");
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
			}
		}
		
//管理員手動新增---------------------------------第一次選擇店家進入新增 ---------------------------------------------------------//				
		if ("ador_fbcm_new_select".equals(action)) { // 來自select_page.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("store_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入店家編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_bcmg_manually_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_bcmg_manually_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_back_bcmg_manually_list);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("store_no", store_no); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(nourl_back_bcmg_manually_add); // 成功轉交
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
			req.setAttribute("errorMsgs", errorMsgs);
			
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
					errorMsgs.add("請輸入團購券名稱");
					tickets_type_name = "";
				}
				
				if (tickets_type_name.equals("")){ // 假如名稱沒有填入
					errorMsgs.add("請輸入團購券名稱");
					tickets_type_name = "";
				}
				
//				System.out.println(req.getParameter("upper_date").trim());
//				System.out.println(req.getParameter("lower_date").trim());
				/* 上架時間 處理*/
				java.sql.Date upper_date = null;				

				try{
					upper_date = java.sql.Date.valueOf(req.getParameter("upper_date").trim());

				}  catch (Exception e) {

					errorMsgs.add("請輸入上架時間!");					
				}
				
				/* 下架時間 處理*/
				java.sql.Date lower_date = null;

				try{
					lower_date = java.sql.Date.valueOf(req.getParameter("lower_date").trim());

				}  catch (Exception e) {

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
					tickets_total = null;
					
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
					RequestDispatcher failurView = req.getRequestDispatcher(nourl_back_bcmg_manually_add);
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
				RequestDispatcher dispatcher = req.getRequestDispatcher(nourl_back_bcmg_manually_success);
				dispatcher.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/					
			}
			catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(nourl_back_bcmg_manually_add);
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
			req.setAttribute("errorMsgs", errorMsgs);
			
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
					RequestDispatcher failureView = req.getRequestDispatcher(nourl_bcmg_use_query_list);
					failureView.forward(req, res);
					return; // 程式中斷
				}						
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/
				req.setAttribute("tickettypevO", tickettypevO); 
				RequestDispatcher dispatcher = req.getRequestDispatcher(nourl_bcmg_use_query_one);
				dispatcher.forward(req,res);
				/***************************其他可能的錯誤處理**********************************/						
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(nourl_bcmg_use_query_list);
				failureView.forward(req, res);						
			}
		}
		

//--------------------------------------------------後端結束----------------------------------------------//	
		
	}
}
