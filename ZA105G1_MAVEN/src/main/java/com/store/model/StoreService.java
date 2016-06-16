package com.store.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.store.pic.model.Store_picVO;

public class StoreService {

	private StoreDAO_hibernate_interface dao_hibernate;
	private StoreDAO_interface dao;
	public StoreService() {
		dao_hibernate = new StoreDAO_hibernate();
		dao = new StoreDAO();
	}
	



	public StoreVO addStore(String store_account, String store_password,
			Integer store_state, String store_name,
			java.sql.Date store_regist_date, String store_zipcode,
			String store_city, String store_district, String store_address,
			String store_phone, String store_type, Double store_score,
			Integer store_balance, String store_cell_registcode,
			Integer store_violation, String manager_name,
			Integer manager_gender, String manager_email, String manager_id,
			String manager_cellphone, Integer manager_credit_num,
			Integer manager_credit_expyear, Integer manager_credit_expmonth,
			Integer manager_credit_secure_num, Integer tickts_limits,
			Double store_longitude, Double store_latitude,String store_introduction) {
//	public StoreVO addStore(String store_account, String store_password,String store_name,
//			java.sql.Date store_regist_date, String manager_name) {
		StoreVO storeVO = new StoreVO();

		storeVO.setStore_account(store_account);
		storeVO.setStore_password(store_password);
		storeVO.setStore_state(store_state);
		storeVO.setStore_name(store_name);
		storeVO.setStore_regist_date(store_regist_date);
		storeVO.setStore_zipcode(store_zipcode);
		storeVO.setStore_city(store_city);
		storeVO.setStore_district(store_district);
		storeVO.setStore_address(store_address);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_type(store_type);
		storeVO.setStore_score(store_score);
		storeVO.setStore_balance(store_balance);
		storeVO.setStore_cell_registcode(store_cell_registcode);
		storeVO.setStore_violation(store_violation);
		storeVO.setManager_name(manager_name);
		storeVO.setManager_gender(manager_gender);
		storeVO.setManager_email(manager_email);
		storeVO.setManager_id(manager_id);
		storeVO.setManager_cellphone(manager_cellphone);
		storeVO.setManager_credit_num(manager_credit_num);
		storeVO.setManager_credit_expyear(manager_credit_expyear);
		storeVO.setManager_credit_expmonth(manager_credit_expmonth);
		storeVO.setManager_credit_secure_num(manager_credit_secure_num);
		storeVO.setTickts_limits(tickts_limits);
		storeVO.setStore_longitude(store_longitude);
		storeVO.setStore_latitude(store_latitude);
		storeVO.setStore_introduction(store_introduction);

		dao_hibernate.insert(storeVO);

		return storeVO;
	}
	public StoreVO updateStore2(StoreVO storevo){
		dao_hibernate.update(storevo);
		return storevo;
	}
	public StoreVO updateStore(Integer store_no, String store_account,
			String store_password, Integer store_state, String store_name,
			java.sql.Date store_regist_date, String store_zipcode,
			String store_city, String store_district, String store_address,
			String store_phone, String store_type, Double store_score,
			Integer store_balance, String store_cell_registcode,
			Integer store_violation, String manager_name,
			Integer manager_gender, String manager_email, String manager_id,
			String manager_cellphone, Integer manager_credit_num,
			Integer manager_credit_expyear, Integer manager_credit_expmonth,
			Integer manager_credit_secure_num, Integer tickts_limits,
			Double store_longitude, Double store_latitude,String store_introduction,Integer clicks,Integer store_articles,Integer store_scopenum,Integer store_pocketnum) {
//	public StoreVO updateStore(Integer store_no,String store_account, String store_password,String store_name,
//				java.sql.Date store_regist_date, String manager_name) {
		
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_no(store_no);
		storeVO.setStore_account(store_account);
		storeVO.setStore_password(store_password);
		storeVO.setStore_state(store_state);
		storeVO.setStore_name(store_name);
		storeVO.setStore_regist_date(store_regist_date);
		storeVO.setStore_zipcode(store_zipcode);
		storeVO.setStore_city(store_city);
		storeVO.setStore_district(store_district);
		storeVO.setStore_address(store_address);
		storeVO.setStore_phone(store_phone);
		storeVO.setStore_type(store_type);
		storeVO.setStore_score(store_score);
		storeVO.setStore_balance(store_balance);
		storeVO.setStore_cell_registcode(store_cell_registcode);
		storeVO.setStore_violation(store_violation);
		storeVO.setManager_name(manager_name);
		storeVO.setManager_gender(manager_gender);
		storeVO.setManager_email(manager_email);
		storeVO.setManager_id(manager_id);
		storeVO.setManager_cellphone(manager_cellphone);
		storeVO.setManager_credit_num(manager_credit_num);
		storeVO.setManager_credit_expyear(manager_credit_expyear);
		storeVO.setManager_credit_expmonth(manager_credit_expmonth);
		storeVO.setManager_credit_secure_num(manager_credit_secure_num);
		storeVO.setTickts_limits(tickts_limits);
		storeVO.setStore_longitude(store_longitude);
		storeVO.setStore_latitude(store_latitude);
		storeVO.setStore_introduction(store_introduction);
		storeVO.setClicks(clicks);
		storeVO.setStore_articles(store_articles);
		storeVO.setStore_scopenum(store_scopenum);
		storeVO.setStore_pocketnum(store_pocketnum);
		dao_hibernate.update(storeVO);

		return storeVO;
	}

	public void deleteStore(Integer store_no) {
		dao_hibernate.delete(store_no);
	}

	public StoreVO getOneStore(Integer store_no) {
		return dao_hibernate.findByPrimaryKey(store_no);
	}

	public List<StoreVO> getAll() {
		return dao_hibernate.getAll();
	}
	
	public List<StoreVO> getAllforShop() {
		return dao.getAll();
	}
	public StoreVO getStoreNo(String store_name) {
		System.out.println(store_name);
		return dao.findByStoreName(store_name);
	}
	public Set<Store_picVO> getStore_picsByStore_no(Integer store_no) {
		return dao_hibernate.getStore_picsByStore_no(store_no);
	}
	public List<StoreVO> getAll(Map<String, String[]> map) {
		return dao_hibernate.getAll(map);
	}
	
	public List<StoreVO> getSearchedByStoreName(String store_key) {
		return dao.getSearchedByStoreName(store_key);
	}
	
	public List<StoreVO> getSearchedByStoreAddress(String store_key) {
		return dao.getSearchedByStoreAddress(store_key);
	}
	
	public List<StoreVO> getSearchedByFoodType(String store_key) {
		return dao.getSearchedByFoodType(store_key);
	}
	
	public StoreVO updateScope(Integer store_no,Double store_scope) {
		StoreVO storeVO = dao_hibernate.findByPrimaryKey(store_no);
		dao_hibernate.updateScope(storeVO,store_scope);
		return storeVO;
	}
	
	public List<StoreVO> getAll2(Map<String, String[]> map) {
		return dao.getAll2(map);
	}
	public StoreVO getStoreAccount(String store_account) {
		return dao.findByStoreAccount(store_account);
	}
}
