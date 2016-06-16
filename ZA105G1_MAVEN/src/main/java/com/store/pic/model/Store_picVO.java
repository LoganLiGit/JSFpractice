package com.store.pic.model;
import com.store.model.StoreVO;
public class Store_picVO implements java.io.Serializable{
	private Integer pic_no;
	private String pic_name;

	private byte[] store_pic;
	private String pic_format;
	private StoreVO storeVO;
	
	public StoreVO getStoreVO() {
		return storeVO;
	}
	public void setStoreVO(StoreVO storeVO) {
		this.storeVO = storeVO;
	}
	public Integer getPic_no() {
		return pic_no;
	}
	public void setPic_no(Integer pic_no) {
		this.pic_no = pic_no;
	}
	public String getPic_name() {
		return pic_name;
	}
	public void setPic_name(String pic_name) {
		this.pic_name = pic_name;
	}

	public byte[] getStore_pic() {
		return store_pic;
	}
	public void setStore_pic(byte[] store_pic) {
		this.store_pic = store_pic;
	}
	public String getPic_format() {
		return pic_format;
	}
	public void setPic_format(String pic_format) {
		this.pic_format = pic_format;
	}

}
