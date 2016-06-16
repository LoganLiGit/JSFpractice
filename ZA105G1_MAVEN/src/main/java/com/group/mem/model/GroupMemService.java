package com.group.mem.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.group.table.model.GroupTableVO;
import com.store.model.StoreVO;

public class GroupMemService {

	private GroupMemDAO_interface dao ;
	
	public GroupMemService(){
		dao = new GroupMemDAO();
	}
	
	public GroupMemVO addGroupMem(GroupMemVO groupMemVO){
		dao.insert(groupMemVO);
		
		return groupMemVO;
		
	}
	
	public void deleteGroupMem(Integer group_no,Integer mem_no){
		dao.delete(group_no,mem_no);
	}
	
	public Set<GroupMemVO> getGroupMem(Integer group_no){
		return dao.findByGroupNo(group_no);
	}
	
}
