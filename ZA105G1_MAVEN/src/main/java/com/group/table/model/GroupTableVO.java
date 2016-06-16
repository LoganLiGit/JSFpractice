package com.group.table.model;

import java.io.Serializable;
import java.sql.Timestamp;

import java.util.HashSet;
import java.util.Set;

import com.group.mem.model.GroupMemVO;
import com.member.model.MemberVO;
import com.store.model.StoreVO;



public class GroupTableVO implements Serializable{
	
	private Integer group_no;

	private Integer mem_no;

	private StoreVO storeVO;
	private Integer group_num;
	private String group_name;

	private String  group_intro;
	private byte[]  group_photo;
	private Timestamp group_eat_date;
	private Timestamp group_start_date;
	private Timestamp group_stop_date;
	private Integer group_status;
	private Set<GroupMemVO> groupMems = new HashSet<GroupMemVO>();
	
	
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public Set<GroupMemVO> getGroupMems() {
		return groupMems;
	}
	public void setGroupMems(Set<GroupMemVO> groupMems) {
		this.groupMems = groupMems;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public StoreVO getStoreVO() {
		return storeVO;
	}
	public void setStoreVO(StoreVO storeVO) {
		this.storeVO = storeVO;
	}
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}

	public Integer getGroup_num() {
		return group_num;
	}
	public void setGroup_num(Integer group_num) {
		this.group_num = group_num;
	}
	public String getGroup_intro() {
		return group_intro;
	}
	public void setGroup_intro(String group_intro) {
		this.group_intro = group_intro;
	}
	public byte[] getGroup_photo() {
		return group_photo;
	}
	public void setGroup_photo(byte[] group_photo) {
		this.group_photo = group_photo;
	}
	public Timestamp getGroup_eat_date() {
		return group_eat_date;
	}
	public void setGroup_eat_date(Timestamp group_eat_date) {
		this.group_eat_date = group_eat_date;
	}
	public Timestamp getGroup_start_date() {
		
		return group_start_date;
	}
	public void setGroup_start_date(Timestamp group_start_date) {
		this.group_start_date = group_start_date;
	}
	public Timestamp getGroup_stop_date() {
		
		return group_stop_date;
	}
	public void setGroup_stop_date(Timestamp group_stop_date) {

		this.group_stop_date = group_stop_date;
	}
	public Integer getGroup_status() {
		return group_status;
	}
	public void setGroup_status(Integer group_status) {
		this.group_status = group_status;
	}

	

}
