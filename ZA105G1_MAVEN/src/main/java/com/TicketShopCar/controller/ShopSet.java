package com.TicketShopCar.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.TicketType.model.*;
import com.Tickettypeall.model.newTestCompositeQuery;
import com.store.model.*;
import com.zipcodes.model.ZipcodesService;
import com.zipcodes.model.ZipcodesVO;


public class ShopSet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");
				
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		//以上為把錯誤訊息丟入req裡
		
		StoreService storeservice = new StoreService();
		//假如是按下 店家縣市 就只顯示縣市的團購劵
		if (action.equals("store_city")) {
			String store_city_okurl = "/Back/fbcm/EShop.jsp";
			String store_city_nourl = "/Back/fbcm/EShop.jsp";
			
			List<ZipcodesVO> store_city2 = new ArrayList();
			List store_city_list = new ArrayList();
			
			List<StoreVO> store_no_list = new ArrayList<StoreVO>();
			List<TicketTypeVO> tickettypevo_list = new ArrayList<TicketTypeVO>();
			TicketTypeService tickettypeservice = new TicketTypeService(); 
			//System.out.println("縣市的團購劵");
			try{
				String store_city = req.getParameter("store_city");
							
				Map<String,String[]> map = new TreeMap<String,String[]>();
				map.put("store_city",new String[]{store_city});
				store_no_list = storeservice.getAll(map); //取得List陣列 為 所在鄉鎮市的店家
				 for (int i=0;i<store_no_list.size();i++){
					 Integer store_no = store_no_list.get(i).getStore_no();
					 //System.out.println("店家編號 " + store_no);
					 tickettypevo_list = tickettypeservice.getAll();
					 for (int j=0;j<tickettypevo_list.size();j++){
						 Integer store_no2 = tickettypevo_list.get(j).getStore_no();
						 if (store_no == store_no2){
							 store_city_list.add(tickettypevo_list.get(j));
							 //如果編號相同 就送進list
						 }
					 }
				 }
				
				
				ZipcodesService zipcodesservice = new ZipcodesService();
				store_city2 = zipcodesservice.getOne3(store_city);						//查詢下一個選單
				
				req.setAttribute("store_city2", store_city2);							// 把下一個選單放入
				req.setAttribute("store_city", store_city);								// 放入本次選擇的縣市團購劵
				req.setAttribute("store_city_list", store_city_list);   				// 資料庫取出的set物件,存入request (本次查詢到的list)
				RequestDispatcher successView = req.getRequestDispatcher(store_city_okurl);
				successView.forward(req, res);	
				return;//程式中斷
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(store_city_nourl);
				failureView.forward(req, res);	
				return;//程式中斷
			}	
			
		}
		//假如是按下 店家區鄉鎮 就只顯示 店家區鄉鎮的團購劵
		if (action.equals("store_district")) {
			String store_district_okurl = "/Back/fbcm/EShop.jsp";
			String store_district_nourl = "/Back/fbcm/EShop.jsp";
			List store_district_list = new ArrayList();
			List<StoreVO> store_no_list = new ArrayList<StoreVO>();
			List<ZipcodesVO> store_city2 = new ArrayList();
			List<TicketTypeVO> tickettypevo_list = new ArrayList<TicketTypeVO>();
			TicketTypeService tickettypeservice = new TicketTypeService(); 
			//System.out.println("店家區鄉鎮的團購劵");
			try{
				String store_district = req.getParameter("store_district");
				Map<String,String[]> map = new TreeMap<String,String[]>();
				map.put("store_district",new String[]{store_district});				
				
				store_no_list = storeservice.getAll(map); //取得List陣列 為 所在鄉鎮市的店家
				 for (int i=0;i<store_no_list.size();i++){
					 Integer store_no = store_no_list.get(i).getStore_no();
					 //System.out.println("店家編號 " + store_no);
					 tickettypevo_list = tickettypeservice.getAll();
					 for (int j=0;j<tickettypevo_list.size();j++){
						 Integer store_no2 = tickettypevo_list.get(j).getStore_no();
						 if (store_no == store_no2){
							 store_district_list.add(tickettypevo_list.get(j));
							 //如果編號相同 就送進list
						 }
					 }
				 }
				 
				/*	地區區域	查詢	*/
				String store_city = req.getParameter("store_city");
				ZipcodesService zipcodesservice = new ZipcodesService();
				store_city2 = zipcodesservice.getOne3(store_city);
				
				req.setAttribute("store_city2", store_city2);								//     把前一次選單放入
				req.setAttribute("store_city",req.getParameter("store_city"));				// 放入前一次選擇的縣市團購劵
				req.setAttribute("store_district",req.getParameter("store_district"));		// 放入前一次選擇的鄉鎮團購劵
				req.setAttribute("store_district_list", store_district_list);    			// 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(store_district_okurl);
				successView.forward(req, res);	
				return;//程式中斷
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(store_district_nourl);
				failureView.forward(req, res);	
				return;//程式中斷
			}		
			
			
		}
		//假如是按下圖片 或者 名稱 就執行這個 要給團購劵詳細資訊
		if (action.equals("select_tickettype")) {
			String select_tickettype_okurl = "/Back/fbcm/listShop.jsp"; //正確要轉導到的地方
			String select_tickettype_nourl = "/Back/fbcm/EShop.jsp";	//錯誤要轉導到的地方
			//System.out.println("要給團購劵詳細資訊");
			try{
				/***************************1.接收請求參數*****************************************/
				Integer tickets_type_no = new Integer(req.getParameter("tickets_type_no"));
				/*************************** 2.開始查詢資料 ****************************************/				
				TicketTypeService tickettypeservice = new TicketTypeService();
				TicketTypeVO tickettypevo = tickettypeservice.getOneTicketType(tickets_type_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("tickettypevo", tickettypevo);    // 資料庫取出的set物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher(select_tickettype_okurl);
				successView.forward(req, res);	
				return;//程式中斷
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(select_tickettype_nourl);
				failureView.forward(req, res);	
				return;//程式中斷
			}						
		}
		
	}
	

}
