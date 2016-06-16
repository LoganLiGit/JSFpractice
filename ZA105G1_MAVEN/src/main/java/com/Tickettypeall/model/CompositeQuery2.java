package com.Tickettypeall.model;

import java.util.*;
public class CompositeQuery2 {
	
	
	public static String get_Where(Map <String,String[]> map){
		Set<String> keys = map.keySet();
		//取得所有Map的key值
		StringBuffer whereC = new StringBuffer();
		//未來串接使用
		int count = 0 ;
		for (String key : keys){
			String value = map.get(key)[0];
			//利用key 取得value 因為可能有許多值 所以設定為0 (第一個)
			if (value != null && value.trim().length() != 0 && !"action".equals(key) ){
			//假如值不等於null 或者 值去掉空白長度不等於0 或者 輸入的不是我們辨識用的key  我們就做以下事情
				count++;
				
				String aCond = get_aCondition_For_Oracle(key, value.trim());
				
				if (count == 1 ){
					whereC.append(aCond +", " );
				}
				else{
					whereC.append(" and " + aCond );
				}
				
			}
		}
		System.out.println("有送出查詢的資料欄位數 count = " + count );
		return whereC.toString();
	}
	
	
	private static String get_aCondition_For_Oracle(String columnName, String value) {		
		
		String aCoundition = null;
		//用於Number型態
		if ("tickets_total".equals(columnName) || "tickets_quantity".equals(columnName) || "tickets_price".equals(columnName) || "tickets_state".equals(columnName) || "store_no".equals(columnName) ) {
			aCoundition = columnName + "=" + value;
		}
		else if ( "tickets_ex".equals(columnName) || "tickets_type_name".equals(columnName) || "test2".equals(columnName) ) {
			aCoundition = columnName + "=" + value + "%'";
		}
		else if ( "upper_date".equals(columnName) || "lower_date".equals(columnName) || "application_date".equals(columnName) ) {
			aCoundition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
//		else if ( "application_date".equals(columnName) || "".equals(columnName) ) {
//			
//		}
		return aCoundition + " ";
	}


	public static void main(String[] args) {
		
		Map<String,String[]> map = new TreeMap<String,String[]>();
//		map.put("tickets_type_no",new String[]{ "7001"});
//		map.put("tickets_total",new String[]{ "7002"});
//		map.put("tickets_quantity",new String[]{ "7003"});
//		map.put("tickets_price",new String[]{ "7004"});
//		map.put("tickets_state",new String[]{ "7005"});
//		map.put("store_no",new String[]{ "7006"});
//		map.put("tickets_ex",new String[]{ "7007"});
//		map.put("upper_date",new String[]{ "1991-11-17"});
//		map.put("tickets_type_name",new String[]{ "中文"});
		map.put("application_date",new String[]{ "1991-11-17"});
		
		String finalSQL = "UPDATE ticket_type set " + get_Where(map) + " where tickets_type_no= ";
		
		System.out.println(finalSQL);

	}

}
