package com.report.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReportDAO implements ReportDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ZA105G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT1 = 
			"INSERT INTO report (report_no,mem_no,store_no,report_content,report_status) VALUES (report_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String INSERT_STMT2 = 
			"INSERT INTO report (report_no,mem_no,article_no,report_content,report_status) VALUES (report_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String INSERT_STMT3 = 
			"INSERT INTO report (report_no,mem_no,group_no,report_content,report_status) VALUES (report_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT report_no,mem_no,store_no,article_no,group_no,report_content,report_status FROM report order by report_no";
	private static final String GET_ONE_STMT = 
			"SELECT report_no,mem_no,store_no,article_no,group_no,report_content,report_status FROM report where report_no = ?";
	private static final String DELETE = 
			"DELETE FROM report where report_no = ?";
	private static final String UPDATE = 
			"UPDATE report set report_status=? where report_no = ?";
	private static final String DELETE_BY_ARTICLE_NO = 
			"DELETE FROM report where article_no = ?";
	@Override
	public void insert1(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT1);

			pstmt.setInt(1, reportVO.getMem_no());
			pstmt.setInt(2, reportVO.getStore_no());
			pstmt.setString(3, reportVO.getReport_content());
			pstmt.setInt(4, reportVO.getReport_status());

			pstmt.executeUpdate();

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
	public void insert2(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT2);

			pstmt.setInt(1, reportVO.getMem_no());
			pstmt.setInt(2, reportVO.getArticle_no());
			pstmt.setString(3, reportVO.getReport_content());
			pstmt.setInt(4, reportVO.getReport_status());

			pstmt.executeUpdate();

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
	
	public void insert3(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT3);

			pstmt.setInt(1, reportVO.getMem_no());
			pstmt.setInt(2, reportVO.getGroup_no());
			pstmt.setString(3, reportVO.getReport_content());
			pstmt.setInt(4, reportVO.getReport_status());

			pstmt.executeUpdate();

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
	public void update(ReportVO reportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setInt(1, reportVO.getMem_no());
//			pstmt.setInt(2, reportVO.getStore_no());
//			pstmt.setInt(3, reportVO.getArticle_no());
//			pstmt.setInt(4, reportVO.getGroup_no());
//			pstmt.setString(5, reportVO.getReport_content());
			pstmt.setInt(1, reportVO.getReport_status());
			pstmt.setInt(2, reportVO.getReport_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer report_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, report_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ReportVO findByPrimaryKey(Integer report_no) {

		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				reportVO = new ReportVO();
				reportVO.setReport_no(rs.getInt("report_no"));
				reportVO.setMem_no(rs.getInt("mem_no"));
				reportVO.setStore_no(rs.getInt("store_no"));
				reportVO.setArticle_no(rs.getInt("article_no"));
				reportVO.setGroup_no(rs.getInt("group_no"));
				reportVO.setReport_content(rs.getString("report_content"));
				reportVO.setReport_status(rs.getInt("report_status"));
			}

			// Handle any driver errors
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
		return reportVO;
	
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// reportVO �]�٬� Domain objects
				reportVO = new ReportVO();
				reportVO.setReport_no(rs.getInt("report_no"));
				reportVO.setMem_no(rs.getInt("mem_no"));
				reportVO.setStore_no(rs.getInt("store_no"));
				reportVO.setArticle_no(rs.getInt("article_no"));
				reportVO.setGroup_no(rs.getInt("group_no"));
				reportVO.setReport_content(rs.getString("report_content"));
				reportVO.setReport_status(rs.getInt("report_status")); // Store the row in the list
				list.add(reportVO);
			}

			// Handle any driver errors
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
	@Override
	public void deleteReportByArticle_no(Integer article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_ARTICLE_NO);

			pstmt.setInt(1, article_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
}
