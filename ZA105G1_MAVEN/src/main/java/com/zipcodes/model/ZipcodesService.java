package com.zipcodes.model;

import java.util.List;

public class ZipcodesService {
	private ZipcodesDAO_interface dao;
	
	public ZipcodesService(){
		dao = new ZipcodesDAO();
	}
	
	public ZipcodesVO getOne1(Integer zipcodes_no){
		return dao.findByPrimaryKey1(zipcodes_no);
	}
	
	public ZipcodesVO getOne2(String zipcodes_city,String zipcodes_district){
		return dao.findByPrimaryKey2(zipcodes_city,zipcodes_district);
	}
	
	public List<ZipcodesVO> getOne3(String zipcodes_city){
		return dao.findByPrimaryKey3(zipcodes_city);
	}
	
	public List<ZipcodesVO> getAll1(){
		return dao.getAll();
	}
	
	public List<ZipcodesVO> getAll2(){
		return dao.getAll2();
	}

}
