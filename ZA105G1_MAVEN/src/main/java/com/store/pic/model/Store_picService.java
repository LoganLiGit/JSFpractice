package com.store.pic.model;

import java.util.List;
import java.util.Map;


import com.store.model.StoreVO;

public class Store_picService {
	private Store_picDAO_interface dao;

	public Store_picService() {
		dao = new Store_picDAO();
	}
	public Store_picVO addStore_pic(String pic_name, Integer store_no, byte[] store_pic, String pic_format) {

		Store_picVO store_picVO = new Store_picVO();
		store_picVO.setPic_name(pic_name);
		store_picVO.setStore_pic(store_pic);
		store_picVO.setPic_format(pic_format);
		
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_no(store_no);
		store_picVO.setStoreVO(storeVO);
		
		dao.insert(store_picVO);
		return store_picVO;
	}
	public Store_picVO updateStore_pic(Integer pic_no,String pic_name, Integer store_no, byte[] store_pic, String pic_format) {

		Store_picVO store_picVO = new Store_picVO();
		store_picVO.setPic_no(pic_no);
		store_picVO.setPic_name(pic_name);
		
		store_picVO.setStore_pic(store_pic);
		store_picVO.setPic_format(pic_format);
		
		StoreVO storeVO = new StoreVO();
		storeVO.setStore_no(store_no);
		store_picVO.setStoreVO(storeVO);
		dao.update(store_picVO);
		return store_picVO;
	}
	public void deleteStore_pic(Integer pic_no) {
		dao.delete(pic_no);
	}

	public Store_picVO getOneStore_pic(Integer pic_no) {
		return dao.findByPrimaryKey(pic_no);
	}

	public List<Store_picVO> getAll() {
		return dao.getAll();
	}

}
