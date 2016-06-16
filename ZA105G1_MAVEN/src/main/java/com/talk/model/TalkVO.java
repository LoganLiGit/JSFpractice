package com.talk.model;

import java.sql.Timestamp;
import java.util.Date;


public class TalkVO implements java.io.Serializable  {
	private Integer talk_no;
	private Integer mem_no;
	private String sender;
	private Integer friend_no;
	private String receiver;
	private Timestamp talk_time;
	private String talk_note ;
	private Integer read_status;
	

	public Integer getTalk_no() {
		return talk_no;
	}
	public void setTalk_no(Integer talk_no) {
		this.talk_no = talk_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public Integer getFriend_no() {
		return friend_no;
	}
	public void setFriend_no(Integer friend_no) {
		this.friend_no = friend_no;
	}
	
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public Timestamp getTalk_time() {
		return talk_time;
	}
	public void setTalk_time(Timestamp date) {
		this.talk_time = date;
	}
	
	public String getTalk_note() {
		return talk_note;
	}
	public void setTalk_note(String talk_note) {
		this.talk_note = talk_note;
	}
	
	public Integer getRead_status() {
		return read_status;
	}
	public void setRead_status(Integer read_status) {
		this.read_status = read_status;
	}
	
}
