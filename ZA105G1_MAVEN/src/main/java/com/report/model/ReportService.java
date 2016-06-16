package com.report.model;

import java.util.List;

public class ReportService {
	
	private ReportDAO_interface dao;
	
	public ReportService(){
		dao = new ReportDAO();
	}

	public ReportVO addReport1(Integer mem_no,Integer store_no,String report_content,Integer report_status){
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setMem_no(mem_no);
		reportVO.setStore_no(store_no);
		reportVO.setReport_content(report_content);
		reportVO.setReport_status(report_status);
		dao.insert1(reportVO);
		
		return reportVO;
	}
	
	public ReportVO addReport2(Integer mem_no,Integer article_no,String report_content,Integer report_status){
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setMem_no(mem_no);
		reportVO.setArticle_no(article_no);
		reportVO.setReport_content(report_content);
		reportVO.setReport_status(report_status);
		dao.insert2(reportVO);
		
		return reportVO;
	}
	
	public ReportVO addReport3(Integer mem_no,Integer group_no,String report_content,Integer report_status){
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setMem_no(mem_no);
		reportVO.setGroup_no(group_no);
		reportVO.setReport_content(report_content);
		reportVO.setReport_status(report_status);
		dao.insert3(reportVO);
		
		return reportVO;
	}
	
	
	public ReportVO updateReport(Integer report_no,Integer report_status){
		ReportVO reportVO = new ReportVO();
		
		reportVO.setReport_no(report_no);
		reportVO.setReport_status(report_status);
		dao.update(reportVO);
		
		return getOneReport(report_no);
	}
	
	public void deleteReport(Integer report_no){
		dao.delete(report_no);
	}
	public void deleteReportByArticle_no(Integer article_no){
		dao.deleteReportByArticle_no(article_no);
	}
	public ReportVO getOneReport(Integer report_no){
		return dao.findByPrimaryKey(report_no);
	}
	
	public List<ReportVO> getAll() {
		return dao.getAll();
	}
}
