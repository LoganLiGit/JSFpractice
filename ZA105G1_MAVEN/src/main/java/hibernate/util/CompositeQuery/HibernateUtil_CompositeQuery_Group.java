/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */

package hibernate.util.CompositeQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;

import java.util.*;

import com.group.table.model.GroupTableVO;
import com.store.model.StoreVO;

public class HibernateUtil_CompositeQuery_Group {

	public static Criteria get_aCriteria_For_AnyDB1(Criteria query, String columnName, String value) {	
		// else if ("sal".equals(columnName) || "comm".equals(columnName))// double
		// query.add(Restrictions.eq(columnName, new Double(value)));
		
	
		
		
		GroupTableVO groupTableVO = new GroupTableVO();
		StoreVO StoreVO = new StoreVO();
		 if ("group_name".equals(columnName))// integer
			 query.add(Restrictions.like(columnName, "%" + value + "%"));
		 else if ("group_eat_date".equals(columnName)) {//data{
			 query.add(Restrictions.eq(columnName, java.sql.Timestamp.valueOf(value)));
		}
		
	return query;
	
	}

	public static List<GroupTableVO> getAllC(Map<String, String[]> map) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<GroupTableVO> list = null;
		try {
			Criteria query = session.createCriteria(GroupTableVO.class);

			Set<String> keys = map.keySet();
			int count = 0;

			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					query = get_aCriteria_For_AnyDB1(query, key, value);	
		
				}
			}

			query.addOrder(Order.asc("group_no"));

			list = query.list();
			
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
		} finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}

		return list;

	}

	public static void main(String argv[]) {

	}
}
