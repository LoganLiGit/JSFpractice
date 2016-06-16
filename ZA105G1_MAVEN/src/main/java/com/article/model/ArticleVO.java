package com.article.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ArticleVO implements java.io.Serializable{
	private Integer article_no;
	private Integer store_no;
	private Integer mem_no;
	private String store_name;
	private String article_content;
	private String article_title;
	private Timestamp article_create;
	private Timestamp article_modify;
	private String article_status;
	private Integer article_score;
	private Integer article_click;
	private Integer article_replies;
	//9�����
	
	public Integer getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	public Integer getStore_no() {
		return store_no;
	}
	public void setStore_no(Integer store_no) {
		this.store_no = store_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public Timestamp getArticle_create() {
		return article_create;
	}
	public void setArticle_create(Timestamp article_create) {
		this.article_create = article_create;
	}
	public Timestamp getArticle_modify() {
		return article_modify;
	}
	public void setArticle_modify(Timestamp article_modify) {
		this.article_modify = article_modify;
	}
	public String getArticle_status() {
		return article_status;
	}
	public void setArticle_status(String article_status) {
		this.article_status = article_status;
	}
	public Integer getArticle_score() {
		return article_score;
	}
	public void setArticle_score(Integer article_score) {
		this.article_score = article_score;
	}
	public Integer getArticle_click() {
		return article_click;
	}
	public void setArticle_click(Integer article_click) {
		this.article_click = article_click;
	}
	public Integer getArticle_replies() {
		return article_replies;
	}
	public void setArticle_replies(Integer article_replies) {
		this.article_replies = article_replies;
	}
	
	
}
