package com.group.mem.model;

import java.util.*;


public interface GroupMemDAO_interface {
	
	public void insert(GroupMemVO groupMemVO);
	public void delete(Integer group_no,Integer mem_no);
	public Set<GroupMemVO> findByGroupNo(Integer group_no);

}
