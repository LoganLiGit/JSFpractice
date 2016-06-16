package com.ann.model;
import java.sql.Date;

public class AnnVO implements java.io.Serializable {
	private Integer ann_no;
	private Date ann_date;
	private String ann_info;
	private String ann_content;
	
	public Integer getAnn_no() {
		return ann_no;
	}
	public void setAnn_no(Integer ann_no) {
		this.ann_no = ann_no;
	}
	public Date getAnn_date() {
		return ann_date;
	}
	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}
	public String getAnn_info() {
		return ann_info;
	}
	public void setAnn_info(String ann_info) {
		this.ann_info = ann_info;
	}
	public String getAnn_content() {
		return ann_content;
	}
	public void setAnn_content(String ann_content) {
		this.ann_content = ann_content;
	}
}
