package com.CashTr.model;
//CashTr 專用複合查詢
import java.util.*;
public class CompositeQuery {
	
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
		//System.out.println("有送出查詢的資料欄位數 count = " + count );
		return whereC.toString();
	}
	
	
	private static String get_aCondition_For_Oracle(String columnName, String value) {		
		String aCoundition = null;
		//用於Number型態
		if ( "trandaction_no".equals(columnName) || "mem_no".equals(columnName) || "trandaction_money".equals(columnName) ) {
			aCoundition = columnName + "=" + value ;
		}
		else if ( "trandaction_date".equals(columnName) || "".equals(columnName) ) {
			aCoundition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		else if ( "".equals(columnName) || "".equals(columnName) ) {
			
		}		
		return aCoundition + " ";
	}


	public static void main(String[] args) {
		
		Map<String,String[]> map = new TreeMap<String,String[]>();
		//map.put("trandaction_no",new String[]{ "123"});
		//map.put("mem_no",new String[]{ "123"});
		map.put("trandaction_money",new String[]{ "1200"});
		map.put("trandaction_date",new String[]{ "1981-11-17"});
		
		//String finalSQL = "select trandaction_no trandaction_date from trandaction " + get_Where(map) + "  ";
		//System.out.println(finalSQL);
	}

}
