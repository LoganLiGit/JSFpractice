package com.group.mem.model;
import java.io.Serializable;

import javax.persistence.Column;

public class Group_MemID implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer group_no;
	private Integer mem_no;
	
	@Column(name="group_no")
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	@Column(name="mem_no")
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	@Override
	public int hashCode() {
		  int result;
		  result = group_no.hashCode() + mem_no.hashCode();
		  return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		return true;
		if (obj == null)
		return false;
		if (getClass() != obj.getClass())
		return false;
		final Group_MemID other = (Group_MemID) obj;
		if (group_no == other.group_no && mem_no == other.mem_no)
		return true;
		return false;
		
	}
}
