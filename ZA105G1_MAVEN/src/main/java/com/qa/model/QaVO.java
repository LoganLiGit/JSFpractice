package com.qa.model;
import java.sql.Date;

public class QaVO implements java.io.Serializable{
	private Integer qa_no;
	private Date qa_date;
	private String qa_info;
	private String qa_content;
	
	public Integer getQa_no() {
		return qa_no;
	}
	public void setQa_no(Integer qa_no) {
		this.qa_no = qa_no;
	}
	public Date getQa_date() {
		return qa_date;
	}
	public void setQa_date(Date qa_date) {
		this.qa_date = qa_date;
	}
	public String getQa_info() {
		return qa_info;
	}
	public void setQa_info(String qa_info) {
		this.qa_info = qa_info;
	}
	public String getQa_content() {
		return qa_content;
	}
	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}
}
