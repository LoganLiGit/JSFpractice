package com.zipcodes.model;

import java.util.List;

public interface ZipcodesDAO_interface {
	public ZipcodesVO findByPrimaryKey1(Integer zipcodes_no);
	public ZipcodesVO findByPrimaryKey2(String zipcodes_city,String zipcodes_district);
	public List<ZipcodesVO> findByPrimaryKey3(String zipcodes_city);
	public List<ZipcodesVO> getAll();
	public List<ZipcodesVO> getAll2();
}
