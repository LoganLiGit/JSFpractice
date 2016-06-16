package com.Tickettypeall.model;

import java.util.*;
public class newTestCompositeQuery {
	
	
	public static String get_Where(Map <String,String[]> map){
		Set<String> keys = map.keySet();
		//取得所有Map的key值
		StringBuffer whereC = new StringBuffer();
		//未來串接使用
		int count = 0 ;
		for (String key:keys){
			String value = map.get(key)[0];
			//利用key 取得value 因為可能有許多值 所以設定為0 (第一個)
			if (value != null && value.trim().length() != 0 && !"action".equals(key) ){
			//假如值不等於null 或者 值去掉空白長度不等於0 或者 輸入的不是我們辨識用的key  我們就做以下事情
				count++;
				
				String aCond = get_aCondition_For_Oracle(key,value.trim());
				
				if (count == 1 ){
					whereC.append(" where " + aCond );
				}
				else{
					whereC.append(" and " + aCond );
				}
				
			}
		}
		System.out.println("有送出查詢的資料欄位數 count = " + count );
		return whereC.toString();
	}
	
	
	private static String get_aCondition_For_Oracle(String key, String trim) {		
		String aCoundition = null;
		
		if ( "".equals(key) || "".equals(key) ) {
			
		}
		else if ( "".equals(key) || "".equals(key) ) {
			
		}
		else if ( "".equals(key) || "".equals(key) ) {
			
		}		
		return aCoundition + " ";
	}


	public static void main(String[] args) {
		
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("",new String[]{ " "});
		
		

	}

}
