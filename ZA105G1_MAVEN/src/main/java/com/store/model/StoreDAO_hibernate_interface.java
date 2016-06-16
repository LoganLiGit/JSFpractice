package com.store.model;

import java.util.*;

import com.store.pic.model.Store_picVO;

public interface StoreDAO_hibernate_interface {
	public void insert(StoreVO storeVO);

	public void update(StoreVO storeVO);

	public void delete(Integer store_no);

	public StoreVO findByPrimaryKey(Integer store_no);

	public List<StoreVO> getAll();
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<EmpVO> getAll(Map<String, String[]> map);
	
	public Set<Store_picVO> getStore_picsByStore_no(Integer store_no);
    public List<StoreVO> getAll(Map<String, String[]> map); 
    public void updateScope(StoreVO storeVO,Double store_scope);
 
}
