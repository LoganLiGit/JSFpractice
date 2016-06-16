package com.store.pic.model;
import java.util.*;

import org.hibernate.*;

import hibernate.util.HibernateUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
public class Store_picDAO implements Store_picDAO_interface{
	private static final String GET_ALL_STMT = "from Store_picVO order by pic_no";
	
	@Override
	public void insert(Store_picVO store_picVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(store_picVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void update(Store_picVO store_picVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(store_picVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}


	@Override
	public void delete(Integer pic_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
//			Query query = session.createQuery("delete EmpVO where empno=?");
//			query.setParameter(0, empno);
//			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
			Store_picVO store_picVO = new Store_picVO();
			store_picVO.setPic_no(pic_no);
			session.delete(store_picVO);

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��emp2.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}


	@Override
	public Store_picVO findByPrimaryKey(Integer pic_no) {
		Store_picVO store_picVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			store_picVO = (Store_picVO) session.get(Store_picVO.class, pic_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return store_picVO;
	}


	@Override
	public List<Store_picVO> getAll() {
		List<Store_picVO> list = null;
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


	
	public static void main(String[] args) throws IOException {

//		Store_picDAO dao = new Store_picDAO();

//		//�� �s�W
//		com.store.model.StoreVO storeVO = new com.store.model.StoreVO(); 
//		storeVO.setStore_no(3);
//
//		FileInputStream in = new FileInputStream("D:\\bazil007.png");
//		byte[] buf = new byte[in.available()];
//		in.read(buf);
//		in.close();
////		�s�W
//		Store_picVO store_picVO1 = new Store_picVO();
//		store_picVO1.setPic_name("���c�U��");
//		store_picVO1.setStore_pic(buf);
//		store_picVO1.setPic_format("png");
//		store_picVO1.setStoreVO(storeVO);
//		dao.insert(store_picVO1);
		



		//�� �ק�
//		Store_picVO store_picVO2 = new Store_picVO();
//		store_picVO2.setPic_no(6);
//		store_picVO2.setPic_name("���c�̦n�U��");
//		store_picVO2.setStoreVO(storeVO);
//		store_picVO2.setStore_pic(null);
//		store_picVO2.setPic_format("jpg");
//		dao.update(store_picVO2);



		//�� �R��(�p��cascade - �h��emp2.hbm.xml�p�G�]�� cascade="all"��
		// cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R��)
//		dao.delete(1);



//		�� �d��-findByPrimaryKey (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
//		Store_picVO store_picVO3 = dao.findByPrimaryKey(2);
//		System.out.print(store_picVO3.getPic_no() + ", ");
//		System.out.print(store_picVO3.getPic_name() + ", ");
//		System.out.print(store_picVO3.getStore_pic() + ", ");
//		System.out.println(store_picVO3.getPic_format());
//		System.out.println("--------------------------------------");
//		// �`�N�H�U�T�檺�g�k (�u!)
//		System.out.print(store_picVO3.getStoreVO().getStore_no() + ",");
//		System.out.print(store_picVO3.getStoreVO().getStore_account() + ",");
//		System.out.print(store_picVO3.getStoreVO().getStore_password());
//		System.out.println();



		//�� �d��-getAll (�h��emp2.hbm.xml�����]��lazy="false")(�u!)
//		List<Store_picVO> list = dao.getAll();
//		for (Store_picVO aStore_pic : list) {
//			System.out.print(aStore_pic.getPic_no() + ",");
//			System.out.print(aStore_pic.getPic_name() + ",");
//	
//			// �`�N�H�U�T�檺�g�k (�u!)
//			System.out.print(aStore_pic.getStoreVO().getStore_no() + ",");
//			System.out.print(aStore_pic.getStoreVO().getStore_account() + ",");
//			System.out.print(aStore_pic.getStoreVO().getStore_password());
//			System.out.println();
//		}
	}
}
