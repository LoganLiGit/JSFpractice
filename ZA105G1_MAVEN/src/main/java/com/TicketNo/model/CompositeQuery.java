package com.TicketNo.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery {
	
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
		if ( "tickets_type_no".equals(columnName) || "tickets_no_status".equals(columnName) ) {
			aCoundition = columnName + "=" + value;
		}
		else if ( "tickets_no".equals(columnName) ) {
			aCoundition = columnName + " like '%" + value + "%'";
		}

		return aCoundition + " ";
	}

	public static void main(String[] args) {
		Map<String,String[]> map = new TreeMap<String,String[]>();
		map.put("tickets_no",new String[]{ "JcfVyR73ML"});
		map.put("tickets_type_no",new String[]{ "848400001"});
		map.put("tickets_no_status",new String[]{ "0"});
		String finalSQL = "select * from TICKETS_NO_DETAIL " + get_Where(map) + " order by TICKETS_NO ";
		
		System.out.println(finalSQL);
		
	}

}
