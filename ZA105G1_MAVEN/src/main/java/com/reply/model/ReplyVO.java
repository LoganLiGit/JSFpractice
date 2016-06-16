package com.reply.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReplyVO implements java.io.Serializable{
	private Integer reply_no;
	private Integer mem_no;
	private Integer article_no;
	private String reply_msg;
	private Timestamp reply_time;
	
	public Integer getReply_no(){
		return reply_no;
	}
	public void setReply_no(Integer reply_no){
		this.reply_no = reply_no;
	}
	public Integer getMem_no(){
		return mem_no;
	}
	public void setMem_no(Integer mem_no){
		this.mem_no = mem_no;
	}
	public Integer getArticle_no(){
		return article_no;
	}
	public void setArticle_no(Integer article_no){
		this.article_no = article_no;
	}
	public String getReply_msg(){
		return reply_msg;
	}
	public void setReply_msg(String reply_msg){
		this.reply_msg = reply_msg;
	}
	public Timestamp getReply_time(){
		return reply_time;
	}
	public void setReply_time(Timestamp reply_time){
		this.reply_time = reply_time;
	}
}
