package com.qa.model;

import java.util.List;
import java.sql.Date;

public class QaService {
	
	private QaDAO_interface dao;
	
	public QaService(){
		dao = new QaDAO();
	}
	
	public QaVO addQa(String qa_info,String qa_content,Date qa_date){
		
		QaVO qaVO = new QaVO();
		
		qaVO.setQa_info(qa_info);
		qaVO.setQa_content(qa_content);
		qaVO.setQa_date(qa_date);
		dao.insert(qaVO);
		
		return qaVO;
	}
	
	public QaVO updateQa(Integer qa_no,String qa_info,String qa_content,Date qa_date){
		
		QaVO qaVO = new QaVO();
		
		qaVO.setQa_no(qa_no);
		qaVO.setQa_info(qa_info);
		qaVO.setQa_content(qa_content);
		qaVO.setQa_date(qa_date);
		dao.update(qaVO);
		
		return qaVO;
	}
	
	public void deleteQa(Integer qa_no){
		dao.delete(qa_no);
	}
	
	public QaVO getOneQa(Integer qa_no){
		return dao.findByPrimaryKey(qa_no);
	}
	
	public List<QaVO> getAll() {
		return dao.getAll();
	}
}
