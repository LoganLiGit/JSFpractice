package com.History.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class HistoryServlet {
	private HistoryDAO_interface dao;
		
	public HistoryServlet(){
		dao = new HistoryDAO();
	}
	
	public List<HistoryVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	
	//管理者登入
	public void admin_login(Integer admin_no,String login_ip){
		java.util.Date nowdate = new java.util.Date();
		long newdatelong = nowdate.getTime();
		Timestamp login_in_time =new Timestamp(newdatelong);
		
		HistoryVO historyvo = new HistoryVO();		
		historyvo.setAdmin_no(admin_no);
		historyvo.setLogin_in_time(login_in_time);
		historyvo.setHistory_state(0);
		historyvo.setLogin_ip(login_ip);
		
		historyvo.setMem_no(0);
		historyvo.setStore_no(0);
		historyvo.setUpdate_time(null);
		historyvo.setHistory_date(null);
		historyvo.setLogin_out_time(null);
		dao.insert(historyvo);		
	}
	//管理者登出
	public void admin_logout(Integer admin_no,String login_ip){
		java.util.Date nowdate = new java.util.Date();
		long newdatelong = nowdate.getTime();
		Timestamp login_out_time =new Timestamp(newdatelong);
		
		HistoryVO historyvo = new HistoryVO();		
		historyvo.setAdmin_no(admin_no);
		historyvo.setLogin_out_time(login_out_time);
		historyvo.setHistory_state(1);
		historyvo.setLogin_ip(login_ip);
		
		historyvo.setMem_no(0);
		historyvo.setStore_no(0);
		historyvo.setUpdate_time(null);
		historyvo.setHistory_date(null);
		historyvo.setLogin_in_time(null);
		dao.insert(historyvo);		
	}
	//管理者修改資料
	public void admin_update(Integer admin_no,String history_date,String login_ip){
		java.util.Date nowdate = new java.util.Date();
		long newdatelong = nowdate.getTime();
		Timestamp update_time =new Timestamp(newdatelong);
		
		HistoryVO historyvo = new HistoryVO();	
		historyvo.setAdmin_no(admin_no);
		historyvo.setUpdate_time(update_time);
		historyvo.setHistory_date(history_date);
		historyvo.setHistory_state(2);
		historyvo.setLogin_ip(login_ip);
		
		historyvo.setMem_no(0);
		historyvo.setStore_no(0);
		historyvo.setLogin_in_time(null);
		historyvo.setLogin_out_time(null);
		
		dao.insert(historyvo);		
	}
	public HistoryVO select_last_login(Integer admin_no){
		String admin_no_string = admin_no.toString();
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("admin_no",new String[]{admin_no_string});
		map.put("history_state",new String[]{"0"});
		return dao.getAll2(map);
	}
	
//	historyvo.setAdmin_no(null);
//	historyvo.setMem_no(null);
//	historyvo.setStore_no(null);
//	historyvo.setUpdate_time(null);
//	historyvo.setHistory_date(null);
//	historyvo.setLogin_in_time(null);
//	historyvo.setLogin_out_time(null);
//	historyvo.setHistory_state(null);
}
