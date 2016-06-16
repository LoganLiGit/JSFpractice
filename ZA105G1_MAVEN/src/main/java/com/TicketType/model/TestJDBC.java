package com.TicketType.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//團購券種類編號	(tickets_type_no)	
//團購券名稱		(tickets_type_name)
//上架時間			(upper_date)
//下架時間			(lower_date)
//申請時間			(application_date)
//團購劵總數量		(tickets_total)
//剩餘數量			(tickets_quantity)
//團購劵單價格		(tickets_price)
//團購劵狀態		(tickets_state)
//店家編號			(store_no)
//團購劵說明		(tickets_ex)
//團購劵圖片		(tickets_image)

public class TestJDBC {	
	//修改狀態
	public static void main(String[] args) {
		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("tickets_state",new String[]{ "1"});
		Integer timeInteger = dao.select_num(map);
		System.out.println(timeInteger);
		//dao.updatestate(848400002,2);
		
//		Integer abcInteger = dao.selectnum(2);
//		System.out.println(abcInteger);
//		
//		Integer ab = dao.select_store_num(1,0);
//		System.out.println(ab);
	}
	//測試連動修改
//	public static void main(String[] args) {
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		dao.stateok(848400015);
//	}
	
//	//複合查詢測試
//	public static void main(String[] args) {
//		
//		
//		Map<String,String[]> map = new TreeMap<String,String[]>();
//		map.put("application_date",new String[]{ "2016-02-16"});
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		List<TicketTypeVO> list = dao.getAll(map);
//		
//		for (TicketTypeVO vo05 : list) {
//			
//			//團購券種類編號	(tickets_type_no)
//			System.out.println("團購券種類編號:" + vo05.getTickets_type_no());
//			
//			//上架時間			(upper_date)
//			System.out.println("上架時間:" + vo05.getUpper_date());
//			
//			//下架時間			(lower_date)
//			System.out.println("下架時間:" + vo05.getLower_date());			
//			
//			//申請時間			(application_date)
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("申請時間:" + sdf2.format(vo05.getApplication_date()) );
//						
//			//團購劵總數量		(tickets_total)
//			System.out.println("團購劵總數量:" + vo05.getTickets_total() );
//			
//			//剩餘數量			(tickets_quantity)
//			System.out.println("剩餘數量:" + vo05.getTickets_quantity() );
//			
//			//團購劵單價格		(tickets_price)
//			System.out.println("團購劵單價格:" + vo05.getTickets_price() );
//			
//			//團購劵狀態		(tickets_state)
//			System.out.println("團購劵狀態:" + vo05.getTickets_state() );
//			
//			//店家編號			(store_no)
//			System.out.println("店家編號:" + vo05.getStore_no() );
//			
//			//團購劵說明		(tickets_ex)
//			System.out.println("團購劵說明:" + vo05.getTickets_ex() );
//			
//			//團購劵圖片		(tickets_image)
//			System.out.println(); //JDBC無法讀取圖片
//			
//			System.out.println("//---------------------------//");
//		}
//	}
	
//	//查找最新日期的資料的種類編號
//	public static void main(String[] args) {
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		
//		TicketTypeVO vo02 =dao.selectlastaddticketsno(837800001);
//		System.out.println("團購券種類編號:" + vo02.getTickets_type_no());		
//
//	}
	
//	public static void main(String[] args) {
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		
//		TicketTypeVO vo02 =dao.selectlastadd(837800001);
//		
//		
//		if (vo02.getApplication_date() == null){
//			System.out.println("申請時間:" + vo02.getApplication_date() );				
//		}
//		else{
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("申請時間:" + sdf2.format(vo02.getApplication_date()) );				
//		}
//	}
	
	
//	public static void main(String[] args) {
//		//直接把IOException 給他拋出 
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//
//		
//		//修改 update(); 測試修改(有圖片)
//		TicketTypeVO vo02 = new TicketTypeVO();
//		
//		vo02.setTickets_type_name("你愛團購劵");
//		vo02.setUpper_date(java.sql.Date.valueOf("2016-01-16"));
//		vo02.setLower_date(java.sql.Date.valueOf("2016-01-16"));
//		vo02.setTickets_total(1);
//		vo02.setTickets_quantity(1);
//		vo02.setTickets_price(1);
//		vo02.setTickets_state(1);		
//		vo02.setStore_no(837800001);
//		vo02.setTickets_ex("ABasasaC");
//		
//		vo02.setTickets_type_no(848400008);
//		dao.updateno(vo02);
//		System.out.print("UPDATA Test OK");	
//
//	}
	
	
//	public static void main(String[] args) throws IOException {
//		//直接把IOException 給他拋出 
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		//利用FileInputStream取得檔案
//		FileInputStream img = new FileInputStream("C:\\abc.jpg");
//		//
//		byte[] byt = new byte[img.available()];
//		img.read(byt);
//		img.close();
//		
//		//修改 update(); 測試修改(有圖片)
//		TicketTypeVO vo02 = new TicketTypeVO();
//		
//		vo02.setTickets_type_name("你愛團購劵");
//		vo02.setUpper_date(java.sql.Date.valueOf("2016-01-16"));
//		vo02.setLower_date(java.sql.Date.valueOf("2016-01-16"));
//		vo02.setTickets_total(1);
//		vo02.setTickets_quantity(1);
//		vo02.setTickets_price(1);
//		vo02.setTickets_state(1);		
//		vo02.setStore_no(837800001);
//		vo02.setTickets_ex("ABC");
//		vo02.setTickets_image(byt);		
//		
//		vo02.setTickets_type_no(848400008);
//		dao.update(vo02);
//		System.out.print("UPDATA Test OK");	
//
//	}
	
//	public static void main(String[] args) throws IOException {
//		//直接把IOException 給他拋出 
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();
//		//利用FileInputStream取得檔案
//		FileInputStream img = new FileInputStream("C:\\abc.jpg");
//		//
//		byte[] byt = new byte[img.available()];
//		img.read(byt);
//		img.close();
//		//
//		TicketTypeVO vo01 = new TicketTypeVO();
//		
//		//測試insert(); 測試新增
//		vo01.setTickets_type_name("你最愛團購劵");
//		vo01.setUpper_date(java.sql.Date.valueOf("2016-01-16"));
//		vo01.setLower_date(java.sql.Date.valueOf("2016-01-16"));
//		vo01.setTickets_total(1);
//		vo01.setTickets_quantity(1);
//		vo01.setTickets_price(1);
//		vo01.setTickets_state(1);		
//		vo01.setStore_no(837800001);
//		vo01.setTickets_ex("ABdC");
//		vo01.setTickets_image(byt);
//		dao.insert(vo01);
//		System.out.print("NEW Test OK");
//	}
	
//	public static void main(String[] args) {
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();		
//		
//		//測試findByPrimaryKey(); 一鍵查詢
//		TicketTypeVO vo05 = dao.findByPrimaryKey(848400013);
//		
//			
//			//團購券種類編號	(tickets_type_no)
//			System.out.println("團購券種類編號:" + vo05.getTickets_type_no());
//			
//			//團購券名稱		(tickets_type_name)
//			System.out.println("團購券名稱:" + vo05.getTickets_type_name());
//			
//			//上架時間			(upper_date)
//			System.out.println("上架時間:" + vo05.getUpper_date());
//			
//			//下架時間			(lower_date)
//			System.out.println("下架時間:" + vo05.getLower_date());			
//			
//			//申請時間			(application_date)
//			if (vo05.getApplication_date() == null){
//				System.out.println("申請時間:" + vo05.getApplication_date() );				
//			}
//			else{
//				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				System.out.println("申請時間:" + sdf2.format(vo05.getApplication_date()) );				
//			}
//
//						
//			//團購劵總數量		(tickets_total)
//			System.out.println("團購劵總數量:" + vo05.getTickets_total() );
//			
//			//剩餘數量			(tickets_quantity)
//			System.out.println("剩餘數量:" + vo05.getTickets_quantity() );
//			
//			//團購劵單價格		(tickets_price)
//			System.out.println("團購劵單價格:" + vo05.getTickets_price() );
//			
//			//團購劵狀態		(tickets_state)
//			System.out.println("團購劵狀態:" + vo05.getTickets_state() );
//			
//			//店家編號			(store_no)
//			System.out.println("店家編號:" + vo05.getStore_no() );
//			
//			//團購劵說明		(tickets_ex)
//			System.out.println("團購劵說明:" + vo05.getTickets_ex() );
//			
//			//團購劵圖片		(tickets_image)
//			System.out.println(); //JDBC無法讀取圖片
//			
//			System.out.println("//---------------------------//");	
//	}

//	public static void main(String[] args) {
//		TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();		
//		
//		//測試getAll(); 呼叫全部
//		List<TicketTypeVO> list = dao.getAll();
//		
//		for (TicketTypeVO vo05 : list) {
//			
//			//團購券種類編號	(tickets_type_no)
//			System.out.println("團購券種類編號:" + vo05.getTickets_type_no());
//			
//			//團購券名稱		(tickets_type_name)
//			//System.out.println("團購券名稱" + vo05.getTickets_type_name());  尚未建立
//			
//			//上架時間			(upper_date)
//			System.out.println("上架時間:" + vo05.getUpper_date());
//			
//			//下架時間			(lower_date)
//			System.out.println("下架時間:" + vo05.getLower_date());			
//			
//			//申請時間			(application_date)
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("申請時間:" + sdf2.format(vo05.getApplication_date()) );
//						
//			//團購劵總數量		(tickets_total)
//			System.out.println("團購劵總數量:" + vo05.getTickets_total() );
//			
//			//剩餘數量			(tickets_quantity)
//			System.out.println("剩餘數量:" + vo05.getTickets_quantity() );
//			
//			//團購劵單價格		(tickets_price)
//			System.out.println("團購劵單價格:" + vo05.getTickets_price() );
//			
//			//團購劵狀態		(tickets_state)
//			System.out.println("團購劵狀態:" + vo05.getTickets_state() );
//			
//			//店家編號			(store_no)
//			System.out.println("店家編號:" + vo05.getStore_no() );
//			
//			//團購劵說明		(tickets_ex)
//			System.out.println("團購劵說明:" + vo05.getTickets_ex() );
//			
//			//團購劵圖片		(tickets_image)
//			System.out.println(); //JDBC無法讀取圖片
//			
//			System.out.println("//---------------------------//");
//		}		
//	}

	
//	public static void main(String[] args) {
//	TicketTypeJDBCDAO dao = new TicketTypeJDBCDAO();		
//	
//	//測試getAll(); 呼叫全部
//	List<TicketTypeVO> list = dao.getshopall();
//	
//	for (TicketTypeVO vo05 : list) {
//		
//		//團購券種類編號	(tickets_type_no)
//		System.out.println("團購券種類編號:" + vo05.getTickets_type_no());
//		
//		//團購券名稱		(tickets_type_name)
//		//System.out.println("團購券名稱" + vo05.getTickets_type_name());  尚未建立
//		
//		//上架時間			(upper_date)
//		System.out.println("上架時間:" + vo05.getUpper_date());
//		
//		//下架時間			(lower_date)
//		System.out.println("下架時間:" + vo05.getLower_date());			
//		
//		//申請時間			(application_date)
//		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println("申請時間:" + sdf2.format(vo05.getApplication_date()) );
//					
//		//團購劵總數量		(tickets_total)
//		System.out.println("團購劵總數量:" + vo05.getTickets_total() );
//		
//		//剩餘數量			(tickets_quantity)
//		System.out.println("剩餘數量:" + vo05.getTickets_quantity() );
//		
//		//團購劵單價格		(tickets_price)
//		System.out.println("團購劵單價格:" + vo05.getTickets_price() );
//		
//		//團購劵狀態		(tickets_state)
//		System.out.println("團購劵狀態:" + vo05.getTickets_state() );
//		
//		//店家編號			(store_no)
//		System.out.println("店家編號:" + vo05.getStore_no() );
//		
//		//團購劵說明		(tickets_ex)
//		System.out.println("團購劵說明:" + vo05.getTickets_ex() );
//		
//		//團購劵圖片		(tickets_image)
//		System.out.println(); //JDBC無法讀取圖片
//		
//		System.out.println("//---------------------------//");
//	}		
//}
}
