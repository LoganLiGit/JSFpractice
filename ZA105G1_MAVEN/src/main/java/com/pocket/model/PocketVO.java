package com.pocket.model;

import java.sql.Timestamp;

public class PocketVO implements java.io.Serializable{
	private Integer pl_no;
	private Integer mem_no;
	private Integer store_no;
	private Timestamp keep_time;
	
	
	public Integer getPl_no(){
		return pl_no;
	}
	public void setPl_no(Integer pl_no){
		this.pl_no = pl_no;
	}
	public Integer getMem_no(){
		return mem_no;
	}
	public void setMem_no(Integer mem_no){
		this.mem_no = mem_no;
	}
	public Integer getStore_no(){
		return store_no;
	}
	public void setStore_no(Integer store_no){
		this.store_no = store_no;
	}
	public Timestamp getKeep_time() {
		return keep_time;
	}
	public void setKeep_time(Timestamp keep_time) {
		this.keep_time = keep_time;
	}
}
