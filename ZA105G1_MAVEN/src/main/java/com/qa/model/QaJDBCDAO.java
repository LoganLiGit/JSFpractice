package com.qa.model;

import java.util.*;
import java.sql.*;

public class QaJDBCDAO implements QaDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ZA105G1";
	String passwd = "ZA105G1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO qa (qa_no,qa_date,qa_info,qa_content) VALUES (qa_seq.NEXTVAL, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT qa_no,to_char(qa_date,'yyyy-mm-dd') qa_date,qa_info,qa_content FROM qa order by qa_no";
		private static final String GET_ONE_STMT = 
			"SELECT qa_no,to_char(qa_date,'yyyy-mm-dd') qa_date,qa_info,qa_content FROM qa where qa_no = ?";
		private static final String DELETE = 
			"DELETE FROM qa where qa_no = ?";
		private static final String UPDATE = 
			"UPDATE qa set qa_date=?, qa_info=?, qa_content=? where qa_no = ?";
		
	@Override
	public void insert(QaVO qaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, qaVO.getQa_date());
			pstmt.setString(2, qaVO.getQa_info());
			pstmt.setString(3, qaVO.getQa_content());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	@Override
	public void update(QaVO qaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, qaVO.getQa_date());
			pstmt.setString(2, qaVO.getQa_info());
			pstmt.setString(3, qaVO.getQa_content());
			pstmt.setInt(4, qaVO.getQa_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(Integer qa_no) { 
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, qa_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public QaVO findByPrimaryKey(Integer qa_no) {

		QaVO qaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, qa_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嘿 Domain objects
				qaVO = new QaVO();
				qaVO.setQa_no(rs.getInt("qa_no"));
				qaVO.setQa_date(rs.getDate("qa_date"));
				qaVO.setQa_info(rs.getString("qa_info"));
				qaVO.setQa_content(rs.getString("qa_content"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return qaVO;
	}
	@Override
	public List<QaVO> getAll() {
		List<QaVO> list = new ArrayList<QaVO>();
		QaVO qaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嘿 Domain objects
				qaVO = new QaVO();
				qaVO.setQa_no(rs.getInt("qa_no"));
				qaVO.setQa_date(rs.getDate("qa_date"));
				qaVO.setQa_info(rs.getString("qa_info"));
				qaVO.setQa_content(rs.getString("qa_content"));
				list.add(qaVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	public static void main(String[] args) {

		QaJDBCDAO dao = new QaJDBCDAO();

		 //穝糤
//		 QaVO qaVO1 = new QaVO();
//		 qaVO1.setQa_date(java.sql.Date.valueOf("2015-12-01"));
//		 qaVO1.setQa_info("MANAGER");
//		 qaVO1.setQa_content("代刚代刚代刚代刚代刚代刚代刚代刚代刚代刚代刚代刚");
//		 dao.insert(qaVO1);
				

		 //э
//		 QaVO qaVO2 = new QaVO();
//		 qaVO2.setQa_no(4);
//		 qaVO2.setQa_date(java.sql.Date.valueOf("2016-01-25"));
//		 qaVO2.setQa_info("MANAGER2");
//		 qaVO2.setQa_content("ッв2");
//		 dao.update(qaVO2);
				

		 //埃
//		 dao.delete(4);

		// 琩高
//		QaVO qaVO3 = dao.findByPrimaryKey(2);
//		System.out.print(qaVO3.getQa_no() + ",");
//		System.out.print(qaVO3.getQa_date() + ",");
//		System.out.print(qaVO3.getQa_info() + ",");
//		System.out.print(qaVO3.getQa_content());
//		System.out.println("---------------------");

		// 琩高
		List<QaVO> list = dao.getAll();
		for (QaVO aQa : list) {
			System.out.print(aQa.getQa_no() + ",");
			System.out.print(aQa.getQa_date() + ",");
			System.out.print(aQa.getQa_info() + ",");
			System.out.print(aQa.getQa_content());
			System.out.println();
		}
	}
	
}
