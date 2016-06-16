package com.CashTr.model;
import java.sql.Timestamp;

public class CashTrVO implements java.io.Serializable {
	private Timestamp trandaction_date;
	private Integer trandaction_no;
	private Integer mem_no;
	private Integer trandaction_money;

	public Timestamp getTrandaction_date() {
		return trandaction_date;
	}
	public void setTrandaction_date(Timestamp trandaction_date) {
		this.trandaction_date = trandaction_date;
	}
	public Integer getTrandaction_no() {
		return trandaction_no;
	}
	public void setTrandaction_no(Integer trandaction_no) {
		this.trandaction_no = trandaction_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public Integer getTrandaction_money() {
		return trandaction_money;
	}
	public void setTrandaction_money(Integer trandaction_money) {
		this.trandaction_money = trandaction_money;
	}


}
