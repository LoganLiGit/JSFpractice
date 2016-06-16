package com.group.table.model;

import java.util.*;

import com.group.mem.model.GroupMemVO;
import com.store.model.StoreVO;

public interface GroupTableDAO_interface {
	
	public void insert(GroupTableVO groupVO);
	public void update(GroupTableVO groupVO);
	public void delete(Integer group_no);
	public GroupTableVO findByPrimaryKey(Integer group_no);
	public List<GroupTableVO> getAll();
	public Set<GroupMemVO> getGroupMemsByGroup_no(Integer group_no);
	public List<GroupTableVO> getAll(Map<String, String[]> map); 
	public List<GroupTableVO> getGroupTablesByMem_no(Integer mem_no);
}
