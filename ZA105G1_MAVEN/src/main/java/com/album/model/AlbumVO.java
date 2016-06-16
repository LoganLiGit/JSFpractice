package com.album.model;

public class AlbumVO {
	private Integer photo_no;
	private Integer mem_no;
	private byte[] mem_photo;
	private String photo_title;
	private String photo_description;
	

	
	public Integer getPhoto_no() {
		return photo_no;
	}
	public void setPhoto_no(Integer photo_no) {
		this.photo_no = photo_no;
	}
	public Integer getMem_no() {
		return mem_no;
	}
	public void setMem_no(Integer mem_no) {
		this.mem_no = mem_no;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getPhoto_title() {
		return photo_title;
	}
	public void setPhoto_title(String photo_title) {
		this.photo_title = photo_title;
	}
	public String getPhoto_description() {
		return photo_description;
	}
	public void setPhoto_description(String photo_description) {
		this.photo_description = photo_description;
	}
	
}
