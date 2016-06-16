package com.CashTr.model;

import java.util.List;
import java.util.Map;

public class CashTrService {
	private CashTrDAO_interface dao;
	
	public CashTrService(){
		dao = new CashTrDAO();
	}
	
	public CashTrVO addCashTr(Integer mem_no,Integer trandaction_money){
		CashTrVO cashTrVO = new CashTrVO();
		
		cashTrVO.setMem_no(mem_no);
		cashTrVO.setTrandaction_money(trandaction_money);
		dao.insert(cashTrVO);
		
		return cashTrVO;		
	}
	
	public CashTrVO updateCashTr(Integer trandaction_no,Integer mem_no,Integer trandaction_money){
		CashTrVO cashTrVO = new CashTrVO();
		cashTrVO.setTrandaction_no(trandaction_no);
		cashTrVO.setMem_no(mem_no);
		cashTrVO.setTrandaction_money(trandaction_money);
		dao.update(cashTrVO);
		
		return cashTrVO;	
	}
	
	public void deleteCashTr(Integer trandaction_no){
		dao.delete(trandaction_no);
	}

	public CashTrVO getOneCashTr(Integer trandaction_no){
		return dao.findByPrimaryKey(trandaction_no);
	}

	public List<CashTrVO> getAll(){
		return dao.getAll();
	}
	
	public List<CashTrVO> getusertrandaction(Integer mem_no){
		return dao.findusertrandaction(mem_no);
	}

	//萬用複合查詢
	public List<CashTrVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	
	//查詢區間內的 date1起始時間  date2 終止時間
	public List<CashTrVO> getDay(String date1,String date2){
		return dao.getDay(date1, date2);
	}
	
	//查詢區間內的 date1起始時間  date2 終止時間
	public List<CashTrVO> getMoney(Integer minMoney,Integer maxMoney){
		return dao.getTrMoney(minMoney, maxMoney);
	}
}
