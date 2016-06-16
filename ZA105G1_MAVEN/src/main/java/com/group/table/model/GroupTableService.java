package com.group.table.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.member.model.MemberVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.store.pic.model.Store_picService;

public class GroupTableService {

	private GroupTableDAO_interface dao;
	
	public GroupTableService(){
		dao = new GroupTableDAO();
	}
	
	public GroupTableVO addGroupTable(Integer mem_no, Integer store_no, Integer group_num,String group_name, String group_intro, 
			 Timestamp group_eat_date,  Timestamp group_stop_date,byte[] buf){
		
		
		GroupTableVO groupVO = new GroupTableVO();
		StoreService storeSvc = new StoreService();
		
		groupVO.setStoreVO(storeSvc.getOneStore(store_no));
		groupVO.setGroup_photo(buf);
//		groupVO.setGroup_photo(storeSvc.getOneStore(store_no).getStore_pics().iterator().next().getStore_pic());
		groupVO.setMem_no(mem_no);
		groupVO.setGroup_num(group_num);
		groupVO.setGroup_name(group_name);
		groupVO.setGroup_intro(group_intro);
		groupVO.setGroup_eat_date(group_eat_date);
//		groupVO.setGroup_start_date(group_start_date);
		groupVO.setGroup_stop_date(group_stop_date);
	
		dao.insert(groupVO);
		
		return groupVO;
		
	}
	
	public GroupTableVO updateGroupTable(Integer group_no, Integer mem_no, Integer store_no, Integer group_num, String group_name,String group_intro, 
			 Timestamp group_eat_date,  Timestamp group_stop_date,byte[] buf){
		
		
		GroupTableVO groupVO = new GroupTableVO();
		groupVO.setGroup_no(group_no);
		StoreService storeSvc = new StoreService();
	
		groupVO.setStoreVO(storeSvc.getOneStore(store_no));
		groupVO.setGroup_photo(buf);
//		groupVO.setGroup_photo(storeSvc.getOneStore(store_no).getStore_pics().iterator().next().getStore_pic());
		groupVO.setMem_no(mem_no);
		groupVO.setGroup_num(group_num);
		groupVO.setGroup_name(group_name);
		groupVO.setGroup_intro(group_intro);

		groupVO.setGroup_eat_date(group_eat_date);
//		groupVO.setGroup_start_date(group_start_date);
		groupVO.setGroup_stop_date(group_stop_date);
//		groupVO.setGroup_status(group_status);
		dao.update(groupVO);
		
		return groupVO;
		
	}
	
	public void deleteGroupTable(Integer group_no){
		dao.delete(group_no);
	}
	
	public GroupTableVO getOneGroupTableVO(Integer group_no){
		return dao.findByPrimaryKey(group_no);
	}
	
	public List<GroupTableVO> getAll(){
		return dao.getAll();
	}
	public List<GroupTableVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public List<GroupTableVO> getGroupTablesByMem_no(Integer mem_no) {
		return dao.getGroupTablesByMem_no(mem_no);
	}
}
