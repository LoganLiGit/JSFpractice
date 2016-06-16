package com.ann.model;

import java.util.List;

public class AnnService {
	
	private AnnDAO_interface dao;
	
	public AnnService(){
		dao = new AnnDAO();
	}
	
	public AnnVO addAnn(String ann_info,String ann_content,java.sql.Date ann_date){
		
		AnnVO annVO = new AnnVO();
		
		annVO.setAnn_info(ann_info);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_date(ann_date);
		dao.insert(annVO);
		
		return annVO;
	}
	
	public AnnVO updateAnn(Integer ann_no,String ann_info,String ann_content,java.sql.Date ann_date){
		
		AnnVO annVO = new AnnVO();
		
		annVO.setAnn_no(ann_no);
		annVO.setAnn_info(ann_info);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_date(ann_date);
		dao.update(annVO);
		
		return annVO;
	}
	
	public void deleteAnn(Integer ann_no){
		dao.delete(ann_no);
	}
	
	public AnnVO getOneAnn(Integer ann_no){
		return dao.findByPrimaryKey(ann_no);
	}
	
	public List<AnnVO> getAll() {
		return dao.getAll();
	}
}
