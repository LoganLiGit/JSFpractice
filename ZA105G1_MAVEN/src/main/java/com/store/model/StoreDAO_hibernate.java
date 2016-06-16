package com.store.model;
import java.sql.*;

import org.hibernate.*;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Store;




import java.util.*;

import com.store.pic.model.Store_picVO;

public class StoreDAO_hibernate implements StoreDAO_hibernate_interface {

	private static final String GET_ALL_STMT = "from StoreVO order by store_name";
	@Override
	public void insert(StoreVO storeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(storeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(StoreVO storeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(storeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer store_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			StoreVO storeVO = (StoreVO) session.get(StoreVO.class, store_no);
//			System.out.println(store_no);
//			System.out.println(storeVO.getStore_city());
//			
			session.delete(storeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public StoreVO findByPrimaryKey(Integer store_no) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			storeVO = (StoreVO) session.get(StoreVO.class, store_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		List<StoreVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	@Override
	public Set<Store_picVO> getStore_picsByStore_no(Integer store_no) {
		Set<Store_picVO> set = findByPrimaryKey(store_no).getStore_pics();
		return set;
	}
	@Override
	public List<StoreVO> getAll(Map<String, String[]> map) {
		List<StoreVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			list = HibernateUtil_CompositeQuery_Store.getAllC(map);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	

	
	
	public static void main(String[] args) {

		StoreDAO_hibernate dao = new StoreDAO_hibernate();

		//��� �憓�-1(銝��dept2.hbm.xml敹��ascade="save-update" ��ascade="all"��身摰�)(��撘瑕之,銝�祕���蒂銝虜�)(雿�,���閮銝餅���敦瑼�甈⊥憓���)
//		DeptVO deptVO = new DeptVO(); // ���POJO
//		Set<EmpVO> set = new HashSet<EmpVO>();// 皞�蔭��撌交鈭�,隞乩噶cascade="save-update"��葫閰�
//
//		EmpVO empXX = new EmpVO();   // �撌仙OJO1
//		empXX.setEname("�15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15000));
//		empXX.setComm(new Double(150));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO();   // �撌仙OJO2
//		empYY.setEname("�16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16000));
//		empYY.setComm(new Double(160));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDname("鋆賡�");
//		deptVO.setLoc("銝剖��正");
//		deptVO.setEmps(set);
//		dao.insert(deptVO);



		//��� 靽格-1(銝��dept2.hbm.xml敹��ascade="save-update" ��� cascade="all"��身摰�)(��撘瑕之,銝�祕���蒂銝虜�)(雿�,�閬��蝙�銋�)
//		DeptVO deptVO = new DeptVO(); // ���POJO
//		Set<EmpVO> set = new HashSet<EmpVO>(); // 皞�蔭��撌交鈭�,隞乩噶cascade="save-update"��葫閰�
//
//		EmpVO empXX = new EmpVO(); // �撌仙OJO1
//		empXX.setEmpno(7015); // ������ empXX.setEmpno(7015); ����pdate��
//		empXX.setEname("�瘞�15");
//		empXX.setJob("MANAGER15");
//		empXX.setHiredate(java.sql.Date.valueOf("2001-01-15"));
//		empXX.setSal(new Double(15555));
//		empXX.setComm(new Double(155));
//		empXX.setDeptVO(deptVO);
//
//		EmpVO empYY = new EmpVO(); // �撌仙OJO2
//		empYY.setEmpno(7016); // ������ empXX.setEmpno(7016); ����pdate��
//		empYY.setEname("�瘞�16");
//		empYY.setJob("MANAGER16");
//		empYY.setHiredate(java.sql.Date.valueOf("2001-01-16"));
//		empYY.setSal(new Double(16666));
//		empYY.setComm(new Double(166));
//		empYY.setDeptVO(deptVO);
//
//		set.add(empXX);
//		set.add(empYY);
//
//		deptVO.setDeptno(50); // �����eptVO.setDeptno(50); ����pdate��
//		deptVO.setDname("鋆賡�1");
//		deptVO.setLoc("銝剖��正1");
//		deptVO.setEmps(set);
//		dao.update(deptVO);



//		��� 靽格-2(銝�閮剖�ascade="save-update" ��� cascade="all")(�蝬虜閬�����靽格)
//		StoreVO storeVO2 = new StoreVO(); // ���POJO
//		storeVO2.setStore_no(4);
//		storeVO2.setStore_name("ttttt");
//		storeVO2.setStore_password("123");
//		storeVO2.setStore_account("212");
//		storeVO2.setStore_state(1);
//		storeVO2.setStore_regist_date(java.sql.Date.valueOf("2002-01-01"));
//		storeVO2.setManager_name("頠�");
//		dao.update(storeVO2);



		//��� (頞�撥憭�!撠�蝙�!)(銝��dept2.hbm.xml敹��ascade="delete" ��� cascade="all"��身摰�, ����nverse="true"閮剖��)
//		dao.delete(8);



		//��� �憓�-2(銝�閬ascade="save-update" ��� cascade="all"��身摰�)(�蝬虜閬������憓�)
//		DeptVO deptVO = new DeptVO(); // ���POJO
//		deptVO.setDname("鋆賡�s");
//		deptVO.setLoc("銝剖��正s");
//		dao.insert(deptVO);



		//��� �閰�-findByPrimaryKey (�蝘�!) (銝��dept2.hbm.xml敹�身�lazy="false")
//		DeptVO deptVO3 = dao.findByPrimaryKey(30);
//		System.out.print(deptVO3.getDeptno() + ",");
//		System.out.print(deptVO3.getDname() + ",");
//		System.out.print(deptVO3.getLoc());
//		System.out.println("\n-----------------");
//		Set<EmpVO> set3 = deptVO3.getEmps();
//		for (EmpVO aEmp : set3) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptVO().getDeptno() + ",");
//			System.out.print(aEmp.getDeptVO().getDname() + ",");
//			System.out.print(aEmp.getDeptVO().getLoc());
//			System.out.println();
//		}



		//��� �閰�-getAll-1 (銝��dept2.hbm.xml銝閮剔lazy="false",��瘝�憭��隞�)
//		List<DeptVO> list1 = dao.getAll();
//		for (DeptVO aDept : list1) {
//			System.out.print(aDept.getDeptno() + ",");
//			System.out.print(aDept.getDname() + ",");
//			System.out.print(aDept.getLoc());
//			System.out.println();
//		}



		//��� �閰�-getAll-2 (�蝘�!!!) (銝��dept2.hbm.xml敹�身�lazy="false")
		List<StoreVO> list2 = dao.getAll();
//		for (StoreVO aStore : list2) {
//			System.out.print(aStore.getStore_account() + ",");
//			System.out.print(aStore.getStore_name() + ",");
//			System.out.print(aStore.getManager_name());
//			System.out.println("\n-----------------");
//			Set<Store_picVO> set2 = aStore.getStore_pics();
//			for (Store_picVO aStore_picVO : set2) {
//				System.out.print(aStore_picVO.getPic_name() + ",");
//				System.out.print(aStore_picVO.getPic_no() + ",");
//				System.out.print(aStore_picVO.getPic_format() + ",");
//				System.out.print(aStore_picVO.getStoreVO().getStore_name() + ",");
//				
//				System.out.println();
//			}
//			System.out.println();
//		}

	}

	@Override
	public void updateScope(StoreVO storeVO, Double store_scope) {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
		
			storeVO.setStore_no(storeVO.getStore_no());
			storeVO.setStore_score(storeVO.getStore_score()+store_scope);
			storeVO.setStore_scopenum(storeVO.getStore_scopenum()+1);
			session.saveOrUpdate(storeVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
		
	}
}