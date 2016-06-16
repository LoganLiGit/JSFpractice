/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧOhibernate.util.CompositeQuery
 * */


package com.store.model.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Store {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("store_name".equals(columnName) || "store_phone".equals(columnName)|| "store_city".equals(columnName)
				|| "store_district".equals(columnName) || "store_type".equals(columnName)){ // �Ω�varchar
			aCondition = columnName + " like '%" + value + "%'";
		}
		else if ( "store_state".equals(columnName)  ) {
			aCondition = columnName + "=" + value;
		}

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				//System.out.println(" " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {


	}
}
