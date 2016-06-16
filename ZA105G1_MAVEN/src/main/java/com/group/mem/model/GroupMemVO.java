package com.group.mem.model;

import java.io.Serializable;

import com.group.table.model.GroupTableVO;

public class GroupMemVO implements Serializable{
	
	private Integer group_no;
	private Integer mem_no;
	private GroupTableVO groupTableVO;
	
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	
	public GroupTableVO getGroupTableVO() {
		return groupTableVO;
	}
	public void setGroupTableVO(GroupTableVO groupTableVO) {
		this.groupTableVO = groupTableVO;
	}

	

	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	
	

}
