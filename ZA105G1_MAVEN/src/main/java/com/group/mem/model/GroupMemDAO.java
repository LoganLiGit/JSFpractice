package com.group.mem.model;

import hibernate.util.HibernateUtil;

import java.sql.*;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.group.table.model.GroupTableVO;

public class GroupMemDAO implements GroupMemDAO_interface {

	private static final String GET_ALL_STMT = "from GroupMemVO order by group_no";

	@Override
	public void insert(GroupMemVO groupMemVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(groupMemVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer group_no, Integer mem_no) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session
					.createQuery("delete from GroupMemVO where group_no=? and mem_no=?");
			query.setParameter(0, group_no);
			query.setParameter(1, mem_no);
			query.executeUpdate();

			tx.commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		} finally {
//			session.close();
//			HibernateUtil.getSessionFactory().close();
//		}
		} catch (RuntimeException ex) {
			if (tx != null)
				tx.rollback();
			throw ex; //System.out.println(ex.getMessage());
		}
	}

	
	@Override
	public Set<GroupMemVO> findByGroupNo(Integer group_no) {
		GroupTableVO groupTableVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			groupTableVO = (GroupTableVO) session.get(GroupTableVO.class, group_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return groupTableVO.getGroupMems();
	}
	

	public static void main(String[] args) {

		GroupMemDAO dao = new GroupMemDAO();

		// // �s�W
		// GroupMemVO groupMemVO1 = new GroupMemVO();
		// groupMemVO1.setGroup_no(1);
		// groupMemVO1.setMem_no(6);
		// dao.insert(groupMemVO1);

		// // �R��
		// dao.delete(1,8);

		// // �d��
		// List<GroupMemVO> list = dao.findByGroupNo(1);
		// for (GroupMemVO aGroup : list) {
		// System.out.print(aGroup.getGroup_no() + ",");
		// System.out.print(aGroup.getMem_no());
		// System.out.println();
		// }
	}

}
