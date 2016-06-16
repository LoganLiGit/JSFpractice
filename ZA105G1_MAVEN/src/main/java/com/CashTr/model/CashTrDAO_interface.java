package com.CashTr.model;

import java.util.*;

public interface CashTrDAO_interface {
	public void insert(CashTrVO cashTrVO);
	public void update(CashTrVO cashTrVO);
	public void delete(Integer trandaction_no);
	public CashTrVO findByPrimaryKey(Integer trandaction_no);
	public List<CashTrVO> getAll();
	//-------------------------------當被儲值時狀態改變------------------------------//
	//扣除團購劵剩餘數量				儲值編號			 / 儲值金額			 /連線		
	public Integer addCashTrdate(Integer mem_no,Integer trandaction_money,java.sql.Connection con);
	
	public List<CashTrVO> findusertrandaction(Integer mem_no);
	
	public List<CashTrVO> getAll(Map<String, String[]> map);
	//萬用複合查詢
	
	public List<CashTrVO> getDay(String date1,String date2);
	//時間區間 查詢 用字串來查
	
	public List<CashTrVO> getTrMoney(Integer minMoney,Integer maxMoney);
	//金額區間 查詢 用字串來查
}
