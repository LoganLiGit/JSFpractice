package com.zipcodes.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ZipcodesDAO implements ZipcodesDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ZipcodesVO findByPrimaryKey1(Integer zipcodes_no){
		ZipcodesVO zipcodesvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL1 = "SELECT zipcodes_NO,zipcodes_DISTRICT,zipcodes_CITY FROM zipcodes where zipcodes_NO = " + zipcodes_no ;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL1);
			rs = pstmt.executeQuery();
			while (rs.next()){
				zipcodesvo = new ZipcodesVO();
				zipcodesvo.setZipcodes_no(rs.getInt("zipcodes_no"));
				zipcodesvo.setZipcodes_district(rs.getString("zipcodes_district"));
				zipcodesvo.setZipcodes_city(rs.getString("zipcodes_city"));
			}
			
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
		return zipcodesvo;
	}
	public ZipcodesVO findByPrimaryKey2(String zipcodes_city,String zipcodes_district){
		ZipcodesVO zipcodesvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL2 = "SELECT zipcodes_NO,zipcodes_DISTRICT,zipcodes_CITY FROM zipcodes where zipcodes_district = " + zipcodes_district + " and zipcodes_city =" + zipcodes_city ;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL2);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				zipcodesvo = new ZipcodesVO();
				zipcodesvo.setZipcodes_no(rs.getInt("zipcodes_no"));
				zipcodesvo.setZipcodes_district(rs.getString("zipcodes_district"));
				zipcodesvo.setZipcodes_city(rs.getString("zipcodes_city"));
			}
			
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
		return zipcodesvo;
	}
	public List<ZipcodesVO> findByPrimaryKey3(String zipcodes_city){
		List<ZipcodesVO> list = new ArrayList<ZipcodesVO>();
		ZipcodesVO zipcodesvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL3 = "SELECT zipcodes_NO,zipcodes_DISTRICT,zipcodes_CITY FROM zipcodes where zipcodes_city = '" + zipcodes_city +"'";
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SQL3);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				zipcodesvo = new ZipcodesVO();
				zipcodesvo.setZipcodes_no(rs.getInt("zipcodes_no"));
				zipcodesvo.setZipcodes_district(rs.getString("zipcodes_district"));
				zipcodesvo.setZipcodes_city(rs.getString("zipcodes_city"));
				list.add(zipcodesvo);
			}
			
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
		return list;
	}
	public List<ZipcodesVO> getAll(){
		List<ZipcodesVO> list = new ArrayList<ZipcodesVO>();
		ZipcodesVO zipcodesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String GET_ALL_STMT = "SELECT zipcodes_NO,zipcodes_DISTRICT,zipcodes_CITY FROM zipcodes";
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				zipcodesVO = new ZipcodesVO();
				zipcodesVO.setZipcodes_no(rs.getInt("zipcodes_no"));
				zipcodesVO.setZipcodes_district(rs.getString("zipcodes_district"));
				zipcodesVO.setZipcodes_city(rs.getString("zipcodes_city"));
				list.add(zipcodesVO);
			}
			
		}catch (SQLException se) {
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
		return list;	
	}
	
	public List<ZipcodesVO> getAll2(){
		List<ZipcodesVO> list = new ArrayList<ZipcodesVO>();
		ZipcodesVO zipcodesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String GET_ALL_STMT = "SELECT zipcodes_city FROM zipcodes group by zipcodes_city order by zipcodes_city";
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				zipcodesVO = new ZipcodesVO();
				zipcodesVO.setZipcodes_city(rs.getString("zipcodes_city"));
				list.add(zipcodesVO);
			}
			
		}catch (SQLException se) {
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
		return list;	
	}
}
