package com.History.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HistoryDAO implements HistoryDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	//insert (新增) 的 SQL 指令
	private static final String INSERT_STMT =
			"INSERT INTO history_record (" 			+			
			"history_no,"							+
			"admin_no,"								+
			"mem_no,"								+
			"store_no,"								+
			"update_time,"							+
			"history_date,"							+
			"login_in_time,"						+
			"login_out_time,"						+
			"history_state,"						+
			"login_ip) "							+
			"VALUES (history_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	//新增
	public void insert(HistoryVO historyvo){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1,historyvo.getAdmin_no());
			pstmt.setInt(2,historyvo.getMem_no());
			pstmt.setInt(3,historyvo.getStore_no());
			pstmt.setTimestamp(4,historyvo.getUpdate_time());
			pstmt.setString(5,historyvo.getHistory_date());
			pstmt.setTimestamp(6,historyvo.getLogin_in_time());
			pstmt.setTimestamp(7,historyvo.getLogin_out_time());
			pstmt.setInt(8,historyvo.getHistory_state());
			pstmt.setString(9,historyvo.getLogin_ip());
//			//----------------新增現在時間----------------------//
//			java.util.Date nowdate = new java.util.Date();
//			long newdatelong = nowdate.getTime();
//			Timestamp timestamp =new Timestamp(newdatelong);
//			pstmt.setTimestamp(4,timestamp);
//			//-------------------------------------------------//
			pstmt.executeUpdate();
			
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
	
	//萬用複合查詢
	public List<HistoryVO> getAll(Map<String, String[]> map){
		List<HistoryVO> list = new ArrayList<HistoryVO>();
		HistoryVO historyvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
		
			String outSql = "select * from history_record " + CompositeQuery.get_Where(map) + "order by history_no";
					
			pstmt =con.prepareStatement(outSql);
			
			rs = pstmt.executeQuery();			
			
			while (rs.next()){	
				historyvo = new HistoryVO();
				historyvo.setHistory_no(rs.getInt("history_no"));
				historyvo.setAdmin_no(rs.getInt("admin_no"));
				historyvo.setMem_no(rs.getInt("mem_no"));
				historyvo.setStore_no(rs.getInt("store_no"));
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("update_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("update_time")).getTime();
					historyvo.setUpdate_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setUpdate_time(rs.getTimestamp("update_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
				historyvo.setHistory_date(rs.getString("history_date"));
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("login_in_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("login_in_time")).getTime();
					historyvo.setLogin_in_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setLogin_in_time(rs.getTimestamp("login_in_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("login_out_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("login_out_time")).getTime();
					historyvo.setLogin_out_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setLogin_out_time(rs.getTimestamp("login_out_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
				historyvo.setHistory_state(rs.getInt("history_state"));
				historyvo.setLogin_ip(rs.getString("login_ip"));
				list.add(historyvo);
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
	
	//萬用複合查詢
	public HistoryVO getAll2(Map<String, String[]> map){
		HistoryVO historyvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
		
			String outSql = "select * from history_record " + CompositeQuery.get_Where(map) + "order by history_no DESC";
					
			pstmt =con.prepareStatement(outSql);
			
			rs = pstmt.executeQuery();			
			
			rs.next();
			rs.next();
			
				historyvo = new HistoryVO();
				historyvo.setHistory_no(rs.getInt("history_no"));
				historyvo.setAdmin_no(rs.getInt("admin_no"));
				historyvo.setMem_no(rs.getInt("mem_no"));
				historyvo.setStore_no(rs.getInt("store_no"));
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("update_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("update_time")).getTime();
					historyvo.setUpdate_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setUpdate_time(rs.getTimestamp("update_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
				historyvo.setHistory_date(rs.getString("history_date"));
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("login_in_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("login_in_time")).getTime();
					historyvo.setLogin_in_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setLogin_in_time(rs.getTimestamp("login_in_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
////-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("login_out_time") != null ){
					long d1 = Timestamp.valueOf(rs.getString("login_out_time")).getTime();
					historyvo.setLogin_out_time(new java.sql.Timestamp(d1));
				}
				else{
					historyvo.setLogin_out_time(rs.getTimestamp("login_out_time"));
				}
////-----------------------------------------------------------------------------------------------------------------------//
				historyvo.setHistory_state(rs.getInt("history_state"));
				historyvo.setLogin_ip(rs.getString("login_ip"));
		
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
		return historyvo;
	}
}
