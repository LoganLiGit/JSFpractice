package com.store.model;

import java.util.*;

public interface StoreDAO_interface {
	public void insert(StoreVO storeVO);

	public void update(StoreVO storeVO);

	public void delete(Integer store_no);

	public StoreVO findByPrimaryKey(Integer store_no);
	
	public StoreVO findByStoreName(String store_name);

	public List<StoreVO> getAll();
	public List<StoreVO> getAll2(Map<String, String[]> map) ;
	public List<StoreVO> getSearchedByStoreName(String store_key);
	
	public List<StoreVO> getSearchedByStoreAddress(String store_key);
	
	public List<StoreVO> getSearchedByFoodType(String store_key);
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<EmpVO> getAll(Map<String, String[]> map);
	public void storecheckmoney(Integer store_no,Integer money,java.sql.Connection con);
	public StoreVO findByStoreAccount(String store_account);
}
