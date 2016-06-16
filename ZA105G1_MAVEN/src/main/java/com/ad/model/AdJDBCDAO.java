package com.ad.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class AdJDBCDAO implements AdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ZA105G1";
	String passwd = "ZA105G1";
	
	private static final String INSERT_STMT = 
			"INSERT INTO advertisement (ad_no,store_no,ad_images,to_char(ad_date,'yyyy-mm-dd') ad_date,to_char(ad_date_ed,'yyyy-mm-dd') ad_date_ed) VALUES (advertisement_seq.NEXTVAL, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT ad_no,store_no,ad_images,to_char(ad_date,'yyyy-mm-dd') ad_date,to_char(ad_date_ed,'yyyy-mm-dd') ad_date_ed FROM advertisement order by ad_no";
		private static final String GET_ONE_STMT = 
			"SELECT ad_no,store_no,ad_images,to_char(ad_date,'yyyy-mm-dd') ad_date,to_char(ad_date_ed,'yyyy-mm-dd') ad_date_ed FROM advertisement where ad_no = ?";
		private static final String DELETE = 
			"DELETE FROM advertisement where ad_no = ?";
		private static final String UPDATE = 
			"UPDATE advertisement set ad_images=?, ad_date=?, ad_date_ed=? where ad_no = ?";
		
	@Override
	public void insert(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, adVO.getStore_no());
			pstmt.setBytes(2, adVO.getAd_images());
			pstmt.setDate(3, adVO.getAd_date());
			pstmt.setDate(4, adVO.getAd_date_ed());

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
	public void update(AdVO adVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1, adVO.getAd_images());
			pstmt.setDate(2, adVO.getAd_date());
			pstmt.setDate(3, adVO.getAd_date_ed());
			pstmt.setInt(4, adVO.getAd_no());

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
	public void delete(Integer ad_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ad_no);

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
	public AdVO findByPrimaryKey(Integer ad_no) {

		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ad_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				adVO = new AdVO();
				adVO.setAd_no(rs.getInt("ad_no"));
				adVO.setStore_no(rs.getInt("store_no"));
				adVO.setAd_images(rs.getBytes("ad_images"));
				adVO.setAd_date(rs.getDate("ad_date"));
				adVO.setAd_date_ed(rs.getDate("ad_date_ed"));
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
		return adVO;
	}
	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				adVO = new AdVO();
				adVO.setAd_no(rs.getInt("ad_no"));
				adVO.setStore_no(rs.getInt("store_no"));
				adVO.setAd_images(rs.getBytes("ad_images"));
				adVO.setAd_date(rs.getDate("ad_date"));
				adVO.setAd_date_ed(rs.getDate("ad_date_ed"));
				list.add(adVO); // Store the row in the list
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
	public static void main(String[] args) throws IOException{

		AdJDBCDAO dao = new AdJDBCDAO();
		AdVO adVO1 = new AdVO();
		FileInputStream in = new FileInputStream("C:\\images\\ad5.jpg");
		byte[] ad_images = new byte[in.available()];
		in.read(ad_images);
		
		 //新增	 
//		 adVO1.setStore_no(5);
//		 adVO1.setAd_images(ad_images);
//		 adVO1.setAd_date(java.sql.Date.valueOf("2016-01-02"));
//		 adVO1.setAd_date_ed(java.sql.Date.valueOf("2016-02-01"));
//		 dao.insert(adVO1);
				

		 //修改
//		 adVO1.setAd_no(005);
//		 adVO1.setAd_images(ad_images);
//		 adVO1.setAd_date(java.sql.Date.valueOf("2016-01-01"));
//		 adVO1.setAd_date_ed(java.sql.Date.valueOf("2016-01-31"));
//		 dao.update(adVO1);
				

		 //刪除
//		 dao.delete(5);
		 
		 in.close();

		// 查詢
//		AdVO adVO3 = dao.findByPrimaryKey(5);
//		System.out.print(adVO3.getAd_no() + ",");
//		System.out.print(adVO3.getStore_no() + ",");
//		System.out.print(adVO3.getAd_images() + ",");
//		System.out.print(adVO3.getAd_date() + ",");
//		System.out.print(adVO3.getAd_date_ed());
//		System.out.println("---------------------");

		// 查詢
		List<AdVO> list = dao.getAll();
		for (AdVO aAd : list) {
			System.out.print(aAd.getAd_no() + ",");
			System.out.print(aAd.getStore_no() + ",");
			System.out.print(aAd.getAd_images() + ",");
			System.out.print(aAd.getAd_date() + ",");
			System.out.print(aAd.getAd_date_ed());
			System.out.println();
		}
	}
	
}
