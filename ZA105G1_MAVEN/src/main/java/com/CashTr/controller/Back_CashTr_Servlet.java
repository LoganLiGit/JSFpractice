package com.CashTr.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.CashTr.model.CashTrService;
import com.CashTr.model.CashTrVO;

/*		後端儲值金管理 控制器		*/
public class Back_CashTr_Servlet extends HttpServlet {
	String url_cashtr_index = "/Back/CashTr/Back_Cashtr_Index.jsp";
	String url_cashtr_over  = "/Back/CashTr/Back_Cashtr_Over.jsp";
	String url_cashtr_all   = "/Back/CashTr/Back_Cashtr_All.jsp";
	String url_cashtr_one   = "/Back/CashTr/Back_Cashtr_One.jsp";
	String url_cashtr_do    = "/Back/Cashtr/Cashtr_Servlet.do";
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		CashTrService cashtrservice = new CashTrService(); //新增一個專用的

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//System.out.println(action);
		//System.out.println(req.getParameter("editor"));
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		//以上為把錯誤訊息丟入req裡
		
		/*	假如是用會員編號查詢	*/
		if ("select_mem_no".equals(action)) 
		{
			List<CashTrVO> cashtrvo_list = null;
			try
			{
		/***************************1.接收請求參數*****************************************/
				String mem_no = req.getParameter("get_mem_no");
				if (mem_no.equals("請選擇")){
					errorMsgs.add("請選擇你所要查詢的會員");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_index);
					failureView.forward(req, res);
					return;
				}
		/*************************** 2.開始查詢資料 ****************************************/
				Integer mem_no_int = new Integer(mem_no);
				cashtrvo_list = cashtrservice.getusertrandaction(mem_no_int);
		/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
				successView.forward(req, res);	
				return;
				
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_index);
				failureView.forward(req, res);	
				return;
			}
		}
		
		
		
		
		if ("select_trmemno2".equals(action)) {
			//System.out.println("單一查詢金額查詢");
			/***************************1.接收請求參數*****************************************/
			System.out.println(req.getParameter("data_money2_max"));
			String data_money2_max = req.getParameter("data_money2_max");
			String data_money2_min = req.getParameter("data_money2_min");
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			//System.out.println(trandaction_money);
			/*判斷是否為 trandaction_money_int 為數字型態	*/
			/***************************2. 錯誤辨識 ****************************************/
			Integer data_money2_max_int = null;
			Integer data_money2_min_int = null;
			try
			{
				data_money2_max_int = new Integer(data_money2_max);
				data_money2_min_int = new Integer(data_money2_min);
			}
			catch(Exception e){
				//System.out.println("請輸入數字!");
				errorMsgs.add("請輸入數字!");
				//errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			try {
				if (data_money2_min.equals("") || data_money2_max.equals("")){
					errorMsgs.add("請輸入數字 請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					cashtrvo_list = cashtrservice.getMoney(data_money2_min_int,data_money2_max_int);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
		}
		
		if ("select_date_one".equals(action)) {
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			//System.out.println("單一查詢 起始 結尾 日期");
			String dateone = req.getParameter("dateone");
			/***************************2. 錯誤辨識 ****************************************/
			try 
			{
			    Date parseDate2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateone);
			 } 
			catch (Exception e) 
			{
				//System.out.println("請選正確日期!");
				errorMsgs.add("請選正確日期!");
				//errorMsgs.add( e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			try {
				if (dateone.equals("")){
					errorMsgs.add("請輸入正確日期  請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					Map<String,String[]> map = new TreeMap<String,String[]>();
					
					map.put("trandaction_date",new String[]{dateone});
					cashtrvo_list = cashtrservice.getAll(map);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			
		}
		
		
/***************************************************************************************************************/
		if ("select_trmoney".equals(action)) {
			//System.out.println("單一查詢金額查詢");
			/***************************1.接收請求參數*****************************************/
			String trandaction_money = req.getParameter("trandaction_money");
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			//System.out.println(trandaction_money);
			/*判斷是否為 trandaction_money_int 為數字型態	*/
			/***************************2. 錯誤辨識 ****************************************/
			try
			{
				Integer trandaction_money_int = new Integer(trandaction_money);
			}
			catch(Exception e){
				//System.out.println("請輸入數字!");
				errorMsgs.add("請輸入數字!");
				//errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			try {
				if (trandaction_money == ""){
					errorMsgs.add("請輸入數字 請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					Map<String,String[]> map = new TreeMap<String,String[]>();
					
					map.put("trandaction_money",new String[]{trandaction_money});
					cashtrvo_list = cashtrservice.getAll(map);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
		}
		
		if ("select_trmemno".equals(action)) {
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			/***************************1.接收請求參數*****************************************/
			//System.out.println("單一查詢 會員帳號");
			String mem_no = req.getParameter("mem_no");
			//System.out.println(mem_no);
			/***************************2. 錯誤辨識 ****************************************/
			try
			{
				Integer mem_no_int = new Integer(mem_no);
			}
			catch(Exception e){
				//System.out.println("請輸入編號!");
				errorMsgs.add("請輸入編號!");
				//errorMsgs.add( e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			try {
				if (mem_no == ""){
					errorMsgs.add("請輸入數字 請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					Map<String,String[]> map = new TreeMap<String,String[]>();
					
					map.put("mem_no",new String[]{mem_no});
					cashtrvo_list = cashtrservice.getAll(map);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			
		}
		if ("select_trno".equals(action)) {
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			/***************************1.接收請求參數*****************************************/
			//System.out.println(" 單一查詢 交易編號");
			String trandaction_no = req.getParameter("trandaction_no");
			//System.out.println(trandaction_no);
			/***************************2. 錯誤辨識 ****************************************/
			try
			{
				Integer trandaction_no_int = new Integer(trandaction_no);
			}
			catch(Exception e){
				//System.out.println("請輸入編號!");
				errorMsgs.add("請輸入編號!");
				//errorMsgs.add( e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			try {
				if (trandaction_no == ""){
					errorMsgs.add("請輸入數字 請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					Map<String,String[]> map = new TreeMap<String,String[]>();
					
					map.put("trandaction_no",new String[]{trandaction_no});
					cashtrvo_list = cashtrservice.getAll(map);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}		
			
		}
		if ("select_trdate".equals(action)) {
			List<CashTrVO> cashtrvo_list = new ArrayList<CashTrVO>();
			//System.out.println("單一查詢 起始 結尾 日期");
			String trandaction_date_up = req.getParameter("trandaction_date_up");
			String trandaction_date_down = req.getParameter("trandaction_date_down");
			System.out.println(trandaction_date_up);
			System.out.println(trandaction_date_down);
			/***************************2. 錯誤辨識 ****************************************/
			try 
			{
			    Date parseDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(trandaction_date_up);
			    Date parseDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(trandaction_date_down);
			 } 
			catch (Exception e) 
			{
				//System.out.println("請選正確日期!");
				errorMsgs.add("請選正確日期!");
				//errorMsgs.add( e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			try {
				if (trandaction_date_up.equals("") || trandaction_date_down.equals("") ){
					errorMsgs.add("請輸入正確日期  請勿空白 ");
					RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
					failureView.forward(req, res);	
					return;
				}
				else{
			/*************************** 3.開始查詢資料 ****************************************/
					cashtrvo_list = cashtrservice.getDay(trandaction_date_up, trandaction_date_down);
					//System.out.println("取得資料數:" + cashtrvo_list.size());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
					RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
					successView.forward(req, res);	
					return;					
				}
			}
			catch(Exception e)
			{
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}
			
			
		}
		if ("select_all".equals(action)) {
			List<CashTrVO> cashtrvo_list =new ArrayList<CashTrVO>();
			List<CashTrVO> list1 = new ArrayList<CashTrVO>();
			List<CashTrVO> list2 = new ArrayList<CashTrVO>();
			List<CashTrVO> list3 = new ArrayList<CashTrVO>();
			String select_trmoney_nourl="/Back/CashTr/back_CashTr_select_all.jsp";
			Map<String,String[]> map = new TreeMap<String,String[]>();
			//System.out.println("來自複合查詢");
			String trandaction_money = req.getParameter("trandaction_money");
			String mem_no = req.getParameter("mem_no");
			String trandaction_date_up = req.getParameter("trandaction_date_up");
			String trandaction_date_down = req.getParameter("trandaction_date_down");
			String money2_max = req.getParameter("data_money2_max");
			String money2_min = req.getParameter("data_money2_min");
			//檢查是否有輸入 在做轉換
			Integer time1 =0;
			Integer time2 =0;
			Integer time3 =0;
			
//			System.out.println("money2_max=" + money2_max);
//			System.out.println("money2_min=" + money2_min);
//			System.out.println("trandaction_money=" + trandaction_money);
//			System.out.println("mem_no=" +mem_no);
//			System.out.println("trandaction_date_up=" + trandaction_date_up);
//			System.out.println("trandaction_date_down=" + trandaction_date_down);
			
			if (trandaction_money != "" || mem_no != "" ||  trandaction_date_up != "" || trandaction_date_down != "" || money2_max!= "" || money2_min!="" ){
				
				/***************************2. 錯誤辨識開始 ****************************************/				
				if (trandaction_money != ""){
					//System.out.println("金額查詢 進來");
					/* 金額查詢    */
					try{
						Integer trandaction_money_int = new Integer(trandaction_money);
					}
					catch(Exception e){
						//System.out.println("請輸入數字!");
						errorMsgs.add("請輸入數字!以便金額查詢");
						//errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
						failureView.forward(req, res);	
						return;
					}
					map.put("trandaction_money",new String[]{trandaction_money});
				}
				if (mem_no != ""){
					/* 查詢 會員帳號   */
					//System.out.println("會員帳號 進來");
					try
					{
						Integer mem_no_int = new Integer(mem_no);
					}
					catch(Exception e){
						//System.out.println("請輸入編號!");
						errorMsgs.add("請輸入 會員編號!");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
						failureView.forward(req, res);	
						return;
					}
					map.put("mem_no",new String[]{mem_no});
				}


				/* 時間區間查詢    */
				if(!trandaction_date_up.equals("") || !trandaction_date_down.equals("")){
					//System.out.println("有時間");
					/* 搜尋日期  */
					try{
					    Date parseDate1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(trandaction_date_up);
					    Date parseDate2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(trandaction_date_down);
					 } 
					catch (Exception e) 
					{
						//System.out.println("請選正確日期!");
						errorMsgs.add("請選正確日期!");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
						failureView.forward(req, res);	
						return;
					}
					/***************************2.日期錯誤辨識結束 ****************************************/
					/*************************** 3.開始查詢日期資料 ****************************************/
					try{
						list1 = cashtrservice.getDay(trandaction_date_up, trandaction_date_down);
						time1++;
					}catch(Exception e){
						errorMsgs.add("日期資料-查詢錯誤!");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
						failureView.forward(req, res);	
						return;
					}
//					if (trandaction_money.equals("") && mem_no.equals("") && money2_max.equals("") && money2_min.equals("") ){//如果都沒有
//						cashtrvo_list = list1;
//						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//						req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
//						RequestDispatcher successView = req.getRequestDispatcher(select_trmoney_okurl);
//						successView.forward(req, res);	
//						return;	
//						// 如果有 直接給後面做
//					}
				}
				
				Integer money2_min_int = null;
				Integer money2_max_int = null;
				/* 金額區間查詢    */
				if(!money2_min.equals("") || !money2_max.equals("") ){
					try
					{
						money2_min_int = new Integer(money2_min);
						money2_max_int = new Integer(money2_max);
					}
					catch(Exception e){
						//System.out.println("請輸入編號!");
						errorMsgs.add("請輸入正確金額 !");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(select_trmoney_nourl);
						failureView.forward(req, res);	
						return;
					}
					
					if (money2_min_int > money2_max_int){ //假如大小相反
						Integer max = money2_min_int;
						money2_min_int = money2_max_int;
						money2_max_int = max;
					}
					
					try{
						list3 = cashtrservice.getMoney(money2_min_int, money2_max_int);
						time3++;
					}catch(Exception e){
						errorMsgs.add("其他-查詢錯誤!");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(select_trmoney_nourl);
						failureView.forward(req, res);	
						return;
					}
				}
				
				/* 其他單項查詢  */
				if (!trandaction_money.equals("") || !mem_no.equals("") ){
					try{
						list2  = cashtrservice.getAll(map);
						time2++;
					}catch(Exception e){
						errorMsgs.add("其他-查詢錯誤!");
						//errorMsgs.add( e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher(select_trmoney_nourl);
						failureView.forward(req, res);	
						return;
					}
				}
				
				
				
				
				if ( time1==1 && time2==1 && time3==1 ){
					for (int i=0;i<list1.size();i++){
						Integer trno1 = list1.get(i).getTrandaction_no(); // list1取出交易編號
						for (int j=0;j<list2.size();j++){
							Integer trno2 = list2.get(j).getTrandaction_no(); // list2 取出交易編號
							for (int k=0;k<list3.size();k++){
								Integer trno3 = list3.get(k).getTrandaction_no(); // list3 取出交易編號
								if (trno1.equals(trno2)){
									if(trno1.equals(trno3)){
										cashtrvo_list.add(list3.get(k)); //假如 都有 就取出
									}
								}
							}
						}
					}
				}
				else if (time1==1 && time2==1 && time3==0){	// 1 2 不等於0
					for (int i=0;i<list1.size();i++){
						Integer trno1 = list1.get(i).getTrandaction_no(); // list1取出交易編號
						for (int j=0;j<list2.size();j++){
							Integer trno2 = list2.get(j).getTrandaction_no(); // list2 取出交易編號
							if (trno1.equals(trno2)){									
								cashtrvo_list.add(list2.get(j));
							}								
						}					
					}					
				}
				else if (time1==1 && time2==0 && time3==1){	// 1 3 不等於0
					for (int i=0;i<list1.size();i++){
						Integer trno1 = list1.get(i).getTrandaction_no(); // list1取出交易編號
						for (int j=0;j<list3.size();j++){
							Integer trno3 = list3.get(j).getTrandaction_no(); // list3 取出交易編號
							if (trno1.equals(trno3)){									
								cashtrvo_list.add(list3.get(j));
							}								
						}					
					}	
					
				}else if (time1==0 && time2==1 && time3==1){	// 2 3 不等於0
					for (int i=0;i<list2.size();i++){
						Integer trno2 = list2.get(i).getTrandaction_no(); // list1取出交易編號
						for (int j=0;j<list3.size();j++){
							Integer trno3 = list3.get(j).getTrandaction_no(); // list3 取出交易編號
							if (trno2.equals(trno3)){									
								cashtrvo_list.add(list3.get(j));
							}								
						}					
					}	
				}else{
					if (time1==1){
						cashtrvo_list = list1;
					}
					else if (time2==1){
						cashtrvo_list = list2;
					}
					else if(time3==1){
						cashtrvo_list = list3;
					}else{
						System.out.println("Error");
					}
				}
//				System.out.println(list1.size());
//				System.out.println(list2.size());
//				System.out.println(list3.size());
				
				
				req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(url_cashtr_over);
				successView.forward(req, res);	
				return;
				
				
				/***************************2. 錯誤辨識結束 ****************************************/
				/*************************** 3.開始查詢資料 ****************************************/
//				
//				if (trandaction_money != "" || mem_no != "" ){
//					
//					if(trandaction_date_up != "" || trandaction_date_down != ""){ /*假如時間也有輸入 就兩個List 比對*/
//						//System.out.println("近來這裡");
//						//System.out.println("list1=" + list1.size());
//						//System.out.println("list2=" + list2.size());
//						/* 查詢完畢 有List1 (時間) 和 List2 (其他) 的查詢 */
//						for (int i=0;i<list1.size();i++){
//							Integer trno1 = list1.get(i).getTrandaction_no(); // list1取出交易編號
//							for (int j=0;j<list2.size();j++){
//								Integer trno2 = list2.get(j).getTrandaction_no(); // list2 取出交易編號
//								if (trno1.equals(trno2)){									
//									cashtrvo_list.add(list2.get(j));
//								}								
//							}					
//						}
//					}
//					else{
//						cashtrvo_list = list2;
//					}
//					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//					req.setAttribute("cashtrvo_list", cashtrvo_list);    // 資料庫取出的set物件,存入request
//					RequestDispatcher successView = req.getRequestDispatcher(select_trmoney_okurl);
//					successView.forward(req, res);	
//					return;						
//				}
			}
			else{
				/* 什麼都沒輸入 就會進來 */
				//System.out.println("什麼都沒輸入 查屁");
				errorMsgs.add("無輸入查詢條件，請輸入!");
				RequestDispatcher failureView = req.getRequestDispatcher(url_cashtr_all);
				failureView.forward(req, res);	
				return;
			}

			
		}
		
	}

}
