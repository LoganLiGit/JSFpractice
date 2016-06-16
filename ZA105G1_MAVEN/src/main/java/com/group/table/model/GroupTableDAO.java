package com.group.table.model;

import org.hibernate.*;

import com.group.mem.model.GroupMemVO;
import com.store.model.StoreVO;

import hibernate.util.HibernateUtil;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Group;
import hibernate.util.CompositeQuery.HibernateUtil_CompositeQuery_Store;

import java.util.*;


public class GroupTableDAO implements GroupTableDAO_interface{
	private static final String GET_ALL_STMT = "from GroupTableVO order by group_no";
		@Override
		public void insert(GroupTableVO groupTableVO) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				session.saveOrUpdate(groupTableVO);
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}
		@Override
		public void update(GroupTableVO groupTableVO) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				session.saveOrUpdate(groupTableVO);
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}
	
		@Override
		public void delete(Integer group_no) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				
				GroupTableVO groupTableVO = (GroupTableVO) session.get(GroupTableVO.class, group_no);
				session.delete(groupTableVO);

				
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
		}

		
		@Override
		public GroupTableVO findByPrimaryKey(Integer group_no) {
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
			return groupTableVO;
		}
		

		@Override
		public List<GroupTableVO> getAll() {
		
			List<GroupTableVO> list = null;
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
		public Set<GroupMemVO> getGroupMemsByGroup_no(Integer group_no) {		
			Set<GroupMemVO>	set = findByPrimaryKey(group_no).getGroupMems();
			return set;
		}
		
		@Override
		public List<GroupTableVO> getAll(Map<String, String[]> map) {
			List<GroupTableVO> list = null;
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				list = HibernateUtil_CompositeQuery_Group.getAllC(map);
				session.getTransaction().commit();
			} catch (RuntimeException ex) {
				session.getTransaction().rollback();
				throw ex;
			}
			return list;
		}
		
		@Override
		public List<GroupTableVO> getGroupTablesByMem_no(Integer mem_no) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction tx = null;
			List<GroupTableVO> list=null;
			List<GroupMemVO> list2=null;
			try {
				tx = session.beginTransaction();
				Query query = session
						.createQuery("from GroupTableVO where mem_no=?");
				query.setParameter(0, mem_no);
				Query query2 = session
						.createQuery("from GroupMemVO where mem_no=?");
				query2.setParameter(0, mem_no);
				list2 = query2.list();
				list = query.list();
				for (GroupMemVO aEmp2 : list2) {
					list.add(aEmp2.getGroupTableVO());
				}
				tx.commit();
			} catch (RuntimeException ex) {
				if (tx != null)
					tx.rollback();
				throw ex; //System.out.println(ex.getMessage());
			}
			return list;
		}
	public static void main(String[] args) {

		GroupTableDAO dao = new GroupTableDAO();
	
//		// �s�W
		
		
//		FileInputStream in1 = new FileInputStream("./WebContent/group/images/abc.jpg");
//		byte[] buf1 = new byte[in1.available()];
//		in1.read(buf1);
//		in1.close();
//		
//		GroupTableVO groupVO1 = new GroupTableVO();
//		groupVO1.setMem_no(4);
//		groupVO1.setStore_no(5);
//		groupVO1.setGroup_num(9);
//		groupVO1.setGroup_intro("�Y�B�Y�B");
//		groupVO1.setGroup_photo(buf1);
//		groupVO1.setGroup_eat_date(java.sql.Date.valueOf("2016-02-05"));
//		groupVO1.setGroup_start_date(java.sql.Date.valueOf("2016-01-25"));
//		groupVO1.setGroup_stop_date(java.sql.Date.valueOf("2016-01-27"));
//		groupVO1.setGroup_status(0);
//		dao.insert(groupVO1);
		

//		// �ק�
		
//		FileInputStream in2 = new FileInputStream("./WebContent/group/images/store2.jpg");
//		byte[] buf2 = new byte[in2.available()];
//		in2.read(buf2);
//		in2.close();
//		
//		GroupTableVO groupVO2 = new GroupTableVO();
//		groupVO2.setGroup_no(2);
//		groupVO2.setStore_no(9);
//		groupVO2.setGroup_num(10);
//		groupVO2.setGroup_intro("�N");
//		groupVO2.setGroup_photo(buf2);
//		groupVO2.setGroup_eat_date(java.sql.Date.valueOf("2016-05-05"));
//		groupVO2.setGroup_start_date(java.sql.Date.valueOf("2016-01-10"));
//		groupVO2.setGroup_stop_date(java.sql.Date.valueOf("2016-01-30"));
//		groupVO2.setGroup_status(1);
//		dao.update(groupVO2);
		


//		// �d��
//		GroupTableVO groupVO3 = dao.findByPrimaryKey(2);
//		System.out.print(groupVO3.getGroup_no() + ",");
//		System.out.print(groupVO3.getMem_no() + ",");
//		System.out.print(groupVO3.getStore_no() + ",");
//		System.out.print(groupVO3.getGroup_num() + ",");
//		System.out.print(groupVO3.getGroup_intro() + ",");
//		System.out.print(groupVO3.getGroup_photo() + ",");
//		System.out.print(groupVO3.getGroup_eat_date() + ",");
//		System.out.print(groupVO3.getGroup_start_date() + ",");
//		System.out.print(groupVO3.getGroup_stop_date() + ",");
//		System.out.println(groupVO3.getGroup_status());

//
//		// �d��
		List<GroupTableVO> list = dao.getAll();
		for (GroupTableVO aGroup : list) {
			
			System.out.println(aGroup.getGroup_eat_date() + ",");

		}
		
	}

	
}
