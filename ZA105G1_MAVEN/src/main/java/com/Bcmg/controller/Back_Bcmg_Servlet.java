package com.Bcmg.controller;

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
import javax.servlet.http.Part;

import org.omg.CORBA.PUBLIC_MEMBER;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.TicketNo.model.TicketNoService;
import com.TicketNo.model.TicketNoVO;
import com.TicketType.model.TicketTypeService;
import com.TicketType.model.TicketTypeVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

@MultipartConfig
public class Back_Bcmg_Servlet extends HttpServlet {
	String no_url_back_syatus_modify_list = "/Back/Bcmg/StateModify/Back_Bcmg_Status_Modify_List.jsp";
	String no_url_back_syatus_modify_one = "/Back/Bcmg/StateModify/Back_Bcmg_Status_Modify_One.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		List<String> errorMsgs = new LinkedList<String>();
		
//--------------------------------------------------後端使用----------------------------------------------//
//(狀態修改頁面轉換-----------						修改狀態				------------------------------------------//
		if ("stateserver".equals(action)){					
			String stateserver = req.getParameter("stateserver");				
			Map<String,String[]> map = new TreeMap<String,String[]>();
			if("0".equals(stateserver) || "1".equals(stateserver) || "2".equals(stateserver) || "3".equals(stateserver) || "4".equals(stateserver) || "5".equals(stateserver)){
				map.put("tickets_state",new String[]{stateserver});
				TicketTypeService ticketTypeService = new TicketTypeService();
				List<TicketTypeVO> list = ticketTypeService.getAll(map);
				Integer staten = null;
				try{
					staten = new Integer(stateserver);
				}catch(Exception e){
					errorMsgs.add("狀態改變錯誤:"+e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("list",list);
				req.setAttribute("nostate",staten);
				RequestDispatcher successView = req.getRequestDispatcher(no_url_back_syatus_modify_one);
				successView.forward(req, res);				
			}	
		}

//修改狀態---------------------------------ador_fbcm_select_all_tickettype.jsp傳送過來的狀態改變--------------------------------------------//
		if ("all_state_chage".equals(action)){
			String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
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
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}else{
				errorMsgs.add("狀態改變錯誤");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);						
			}					
		}
		
		
//--------------------------------------	單選下拉改變狀態就會進來這裡 	-----------------------------------------------//
		if ("OkorNo".equals(action)){
			String requestURL = req.getParameter("requestURL"); // 送出的來源網頁路徑
			req.setAttribute("errorMsgs", errorMsgs);
			
			String areyouok = req.getParameter("OkorNo"); //狀態要改?
			String pagee = req.getParameter("whichPage"); //頁數
			String in_state = req.getParameter("stateserver"); //送來的狀態是哪個頁面的狀態
			System.out.println("狀態要改:" + areyouok);
			System.out.println("頁數:" + pagee);
			System.out.println("送來的狀態是哪個頁面的狀態:" + in_state);
			
			/*************************** 1.開始查詢資料 ****************************************/
			TicketTypeService ticketTypeService = new TicketTypeService();
			/*							    查詢當前狀態									*/

			Map<String,String[]> map = new TreeMap<String,String[]>();
			/* 假如已經有錯誤訊息 又上下頁 就會發生這種錯誤 */
			if ( req.getParameter("tickets_type_no") == null){
				if (pagee!= null && in_state!= null){
					if ( in_state.equals("0") || in_state.equals("1") || in_state.equals("2") || in_state.equals("3") || in_state.equals("4") || in_state.equals("5") || in_state.equals("6")){
						map.put("tickets_state",new String[]{in_state});
					}
					else{
						errorMsgs.add("狀態修改失敗");
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
			
			Integer errornum = 0 ; // 錯誤碼 等於1 代表錯誤
			Integer tickets_type_no = 0;	
			String  tickets_type_no_string = "";
			//假如轉型錯誤
			try{
				tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
				tickets_type_no_string = new String(req.getParameter("tickets_type_no"));
			}
			catch (Exception e) {
				errornum = 1;
			}			
			if("o1".equals(areyouok) )			{
				try{						
					ticketTypeService.getState0to1(tickets_type_no);
					errorMsgs.add(tickets_type_no + "狀態已修改成 審核通過");
				}
				catch(Exception e){
					errorMsgs.add(tickets_type_no + "狀態修改錯誤");
					errornum = 1;
				}
			}
			else if("o2".equals(areyouok) )		{
				try{						
				ticketTypeService.updatestateTicketType(tickets_type_no,2);	
				errorMsgs.add(tickets_type_no + "狀態已修改成 上架");
				}
				catch(Exception e){
					errorMsgs.add(tickets_type_no + "狀態修改錯誤");
					errornum = 1;
				}
			}
			else if("o3".equals(areyouok))		{
				try{						
					ticketTypeService.updatestateTicketType(tickets_type_no,3);	
					errorMsgs.add(tickets_type_no + "狀態已修改成 下架");
				}
				catch(Exception e){
					errorMsgs.add(tickets_type_no + "狀態修改錯誤");
					errornum = 1;
				}
			}
			else if("o4".equals(areyouok))		{
				try{						
					ticketTypeService.updatestateTicketType(tickets_type_no,4);	
					errorMsgs.add(tickets_type_no + "狀態已修改成 售完");
				}
				catch(Exception e){
					errorMsgs.add(tickets_type_no + "狀態修改錯誤");
					errornum = 1;
				}
			}
			else if("o5".equals(areyouok))		{
				try{						
					ticketTypeService.updatestateTicketType(tickets_type_no,5);	
					errorMsgs.add(tickets_type_no + "狀態已修改成 未通過");
				}
				catch(Exception e){
					errorMsgs.add(tickets_type_no + "狀態修改錯誤");
					errornum = 1;
				}
			}
			else {
				errorMsgs.add(tickets_type_no + "請選擇正確的選項");
				errornum = 1;
			}
			
			if (errornum == 1 ){ 	// 假如有錯誤 就進來
				if ( in_state.equals("0") || in_state.equals("1") || in_state.equals("2") || in_state.equals("3") || in_state.equals("4") || in_state.equals("5") || in_state.equals("6")){
					map.put("tickets_state",new String[]{in_state});
				}
				else{
					errorMsgs.add(tickets_type_no + "狀態修改失敗，直接轉回總覽"); // 完全沒有狀態，就會進來這裡
					RequestDispatcher successView = req.getRequestDispatcher(no_url_back_syatus_modify_list); //因為錯誤 沒有狀態 直接轉回總覽
					successView.forward(req, res);
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
			map.put("tickets_type_no",new String[]{ tickets_type_no_string});
			List<TicketTypeVO> list = ticketTypeService.getAll(map);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("list",list);							
			RequestDispatcher successView = req.getRequestDispatcher(no_url_back_syatus_modify_list); //如果修改成功 跳到總覽
			successView.forward(req, res);	
		}
//--------------------------------------------------批量修改狀態開始---------------------------------------------------------//	
		// 批量專用 方法
		if ("OkorNo_1".equals(action) || "OkorNo_2".equals(action) || "OkorNo_3".equals(action) || "OkorNo_4".equals(action) || "OkorNo_5".equals(action)){
			String requestURL = req.getParameter("requestURL"); 				// 送出的來源網頁路徑
			String[] OkorNoAry = req.getParameterValues("checkItem");
			
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
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷

				}else{
					errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷

				}else{
					errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷

				}else{
					errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷

				}else{
					errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
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
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷

				}else{
					errorMsgs.add("請勾選要改變的，無勾選直接回到總表");
					RequestDispatcher failureView = req.getRequestDispatcher(no_url_back_syatus_modify_list);
					failureView.forward(req, res);
					return; //程式中斷
				}
			}
		}
//--------------------------------------------------批量修改狀態結束---------------------------------------------------------//		
	}
}


