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


import com.store.model.StoreVO;

public class HibernateUtil_CompositeQuery_Store {

	public static Criteria get_aCriteria_For_AnyDB(Criteria query, String columnName, String value) {
		// if ("empno".equals(columnName) || "deptno".equals(columnName))
		// //�Ω�Integer
		// query.add(Restrictions.eq(columnName, new Integer(value)));
		// else if ("sal".equals(columnName) || "comm".equals(columnName))
		// //�Ω�Double
		// query.add(Restrictions.eq(columnName, new Double(value)));
		// else if ("ename".equals(columnName) || "job".equals(columnName))
		// //�Ω�varchar
		// query.add(Restrictions.like(columnName, "%"+value+"%"));
		// else if ("hiredate".equals(columnName)) //�Ω�date
		// query.add(Restrictions.eq(columnName, java.sql.Date.valueOf(value)));

		// if ("pic_no".equals(columnName)) //�Ω�Integer
		// query.add(Restrictions.eq(columnName, new Integer(value)));
		// else if ("sal".equals(columnName) || "comm".equals(columnName))
		// //�Ω�Double
		// query.add(Restrictions.eq(columnName, new Double(value)));
		if ("store_name".equals(columnName) || "store_phone".equals(columnName) || "store_city".equals(columnName)
				|| "store_district".equals(columnName) || "store_type".equals(columnName)) {// �Ω�varchar
			query.add(Restrictions.like(columnName, "%" + value + "%"));
		// else if ("hiredate".equals(columnName)) //�Ω�date
		// query.add(Restrictions.eq(columnName, java.sql.Date.valueOf(value)));
		// else if ("store_no".equals(columnName) ) {
//
//		StoreVO storeVO = new StoreVO();
//		storeVO.setStore_no(new Integer(value));
//
//		query.add(Restrictions.eq("storeVO", storeVO));

	}
	return query;
	}

	public static List<StoreVO> getAllC(Map<String, String[]> map) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<StoreVO> list = null;
		try {
			Criteria query = session.createCriteria(StoreVO.class);
			Set<String> keys = map.keySet();
			int count = 0;

			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					query = get_aCriteria_For_AnyDB(query, key, value);
					System.out.println("���e�X�d�߸�ƪ�����count = " + count);
				}
			}
			query.addOrder(Order.asc("store_name"));
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
