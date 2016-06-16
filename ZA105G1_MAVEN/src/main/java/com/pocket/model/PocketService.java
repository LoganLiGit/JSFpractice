package com.pocket.model;

import java.sql.Timestamp;
import java.util.List;

public class PocketService {
	
	private PocketDAO_interface dao;
	
	public PocketService() {
		dao = new PocketJNDIDAO();
	}
	
	public PocketVO addPocket(Integer mem_no, Integer store_no, Timestamp keep_time) {

		PocketVO pocketVO = new PocketVO();
		
		pocketVO.setMem_no(mem_no);
		pocketVO.setStore_no(store_no);	
		pocketVO.setKeep_time(keep_time);	
		dao.insert(pocketVO);

		return pocketVO;
	}
	
	public PocketVO updatePocket(Integer pl_no, Integer mem_no, Integer store_no, Timestamp keep_time) {

		PocketVO pocketVO = new PocketVO();

		pocketVO.setPl_no(pl_no);
		pocketVO.setMem_no(mem_no);
		pocketVO.setStore_no(store_no);
		pocketVO.setKeep_time(keep_time);
		
		dao.update(pocketVO);
		
		return pocketVO;
	}
	
	public void deletePocket(Integer pl_no) {
		dao.delete(pl_no);
	}
	
	public PocketVO getOnePocket(Integer pl_no) {
		return dao.findByPrimaryKey(pl_no);
	}
	
	public List<PocketVO> getAll() {
		return dao.getAll();
	}
	
	public List<PocketVO> getKeepStores(Integer mem_no) {
		return dao.getKeepStores(mem_no);
	}

}
