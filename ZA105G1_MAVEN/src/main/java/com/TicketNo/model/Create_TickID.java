package com.TicketNo.model;

public class Create_TickID {
	
	public static String create (int len){
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; //<---序號要什麼就加什
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<len;i++){
			int idx = (int)(Math.random() * str.length());
			sb.append(str.charAt(idx));
		}
		return sb.toString();
	}	

	public static void main(String[] args) {
		String no = create(24); //<----你要創造幾個位數的就填數字多少
		System.out.println(no);
	}

}
