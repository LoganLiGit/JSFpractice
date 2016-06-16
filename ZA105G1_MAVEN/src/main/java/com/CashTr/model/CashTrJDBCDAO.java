package com.CashTr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CashTrJDBCDAO implements CashTrDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "prada";
	String passwd = "aa20070";
	
	private static final String INSERT_STMT = 
			"INSERT INTO TRANDACTION (Trandaction_date,Trandaction_no,Mem_no,Trandaction_money) VALUES (?,trandaction_no_seq.NEXTVAL,?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT TRANDACTION_DATE,TRANDACTION_NO,MEM_NO,TRANDACTION_MONEY FROM TRANDACTION order by TRANDACTION_NO";
	private static final String GET_ONE_STMT = 
			"SELECT TRANDACTION_DATE,TRANDACTION_NO,MEM_NO,TRANDACTION_MONEY FROM TRANDACTION where TRANDACTION_NO = ?";
	private static final String DELETE = 
			"DELETE FROM TRANDACTION where trandaction_no = ?";
	private static final String UPDATE = 
			"UPDATE TRANDACTION set Mem_no=?,Trandaction_money=? where Trandaction_no= ?";

	
//	新增
	@Override
	public void insert(CashTrVO cashTrVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			//----------------新增現在時間----------------------//
			java.util.Date nowdate = new java.util.Date();
			long newdatelong = nowdate.getTime();
			Timestamp timestamp =new Timestamp(newdatelong);
			pstmt.setTimestamp(1,timestamp);
			//-------------------------------------------------//
			pstmt.setInt(2, cashTrVO.getMem_no());
			pstmt.setInt(3,cashTrVO.getTrandaction_money());
			
			pstmt.executeUpdate();
			
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
	
//	修改
	@Override
	public void update(CashTrVO cashTrVO){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, cashTrVO.getMem_no());
			pstmt.setInt(2,cashTrVO.getTrandaction_money());
			
			pstmt.setInt(3,cashTrVO.getTrandaction_no());
			
			pstmt.executeUpdate();
			
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
//	刪除
	public void delete(Integer trandaction_no){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1,trandaction_no);
			
			pstmt.executeUpdate();
			
			
			
			
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
//	查詢
	public CashTrVO findByPrimaryKey(Integer trandaction_no){
		CashTrVO cashTrVO = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, trandaction_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cashTrVO = new CashTrVO();
				//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("trandaction_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("trandaction_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					cashTrVO.setTrandaction_date(new java.sql.Timestamp(d1));
				}
				else{
					cashTrVO.setTrandaction_date(rs.getTimestamp("trandaction_date"));
				}
				

				cashTrVO.setTrandaction_no(rs.getInt("trandaction_no"));
				cashTrVO.setMem_no(rs.getInt("mem_no"));
				cashTrVO.setTrandaction_money(rs.getInt("trandaction_money"));
			}
			
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
		return cashTrVO;
		
	}
//	全部查詢
	public List<CashTrVO> getAll(){
		List<CashTrVO> list = new ArrayList<CashTrVO>();
		
		CashTrVO cashTrVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cashTrVO = new CashTrVO();
////-----------------------------------------------------------------------------------------------------------------------//		
				//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("trandaction_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("trandaction_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					cashTrVO.setTrandaction_date(new java.sql.Timestamp(d1));
				}
				else{
					cashTrVO.setTrandaction_date(rs.getTimestamp("trandaction_date"));
				}
////-----------------------------------------------------------------------------------------------------------------------//		
				cashTrVO.setTrandaction_no(rs.getInt("trandaction_no"));
				cashTrVO.setMem_no(rs.getInt("mem_no"));
				cashTrVO.setTrandaction_money(rs.getInt("trandaction_money"));
				list.add(cashTrVO);
			}
			
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
		return list;
		
	}
	
	//-------------------------------當被儲值時狀態改變------------------------------//
	//扣除團購劵剩餘數量				儲值編號			 / 儲值金額			 /連線		
	public Integer addCashTrdate(Integer mem_no,Integer trandaction_money,java.sql.Connection con){
		CashTrVO cashTrVO = null;		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer next_deptno = null;	
		try {
    		//跟資料說說哪個是主鍵
    		String cols[] = {"trandaction_no"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			
			//----------------新增現在時間----------------------//
			java.util.Date nowdate = new java.util.Date();
			long newdatelong = nowdate.getTime();
			Timestamp timestamp =new Timestamp(newdatelong);
			pstmt.setTimestamp(1,timestamp);
			//-------------------------------------------------//
			pstmt.setInt(2,mem_no);
			pstmt.setInt(3,trandaction_money);
			
			pstmt.executeUpdate();
			
																		    //此交易編號
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_deptno = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_deptno +"交易編號");
			} else {
				System.out.println("未取得自增主鍵值");
			}			
			rs.close();
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-TicketTypeJDBCDAO");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		return next_deptno;
	}
	
	public List<CashTrVO> findusertrandaction(Integer mem_no){
		List<CashTrVO> list = new ArrayList<CashTrVO>();
		
		CashTrVO cashTrVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String GET_ALL_STMT2 = "SELECT TRANDACTION_DATE,TRANDACTION_NO,MEM_NO,TRANDACTION_MONEY FROM TRANDACTION where TRANDACTION_NO =" + mem_no;
			pstmt = con.prepareStatement(GET_ALL_STMT2 );
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cashTrVO = new CashTrVO();
////-----------------------------------------------------------------------------------------------------------------------//		
				//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
				if (rs.getString("trandaction_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("trandaction_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					cashTrVO.setTrandaction_date(new java.sql.Timestamp(d1));
				}
				else{
					cashTrVO.setTrandaction_date(rs.getTimestamp("trandaction_date"));
				}
////-----------------------------------------------------------------------------------------------------------------------//		
				cashTrVO.setTrandaction_no(rs.getInt("trandaction_no"));
				cashTrVO.setMem_no(rs.getInt("mem_no"));
				cashTrVO.setTrandaction_money(rs.getInt("trandaction_money"));
				list.add(cashTrVO);
			}
			
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
		return list;
	}
	public List<CashTrVO> getAll(Map<String, String[]> map){
		List<CashTrVO> list = new ArrayList<CashTrVO>();
		CashTrVO cashTrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String outSql = "select * from trandaction " + CompositeQuery.get_Where(map) + "order by trandaction_no";
			pstmt =con.prepareStatement(outSql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				cashTrVO = new CashTrVO();
				cashTrVO.setMem_no(rs.getInt("mem_no"));				
				cashTrVO.setTrandaction_money(rs.getInt("trandaction_money"));
				cashTrVO.setTrandaction_no(rs.getInt("trandaction_no"));
//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("trandaction_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("trandaction_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					cashTrVO.setTrandaction_date(new java.sql.Timestamp(d1));
				}
				else{
					cashTrVO.setTrandaction_date(rs.getTimestamp("trandaction_date"));
				}
//-----------------------------------------------------------------------------------------------------------------------//
				list.add(cashTrVO);
			}
			
		}catch (ClassNotFoundException e) {
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
		return list;
	}
	
	
	public List<CashTrVO> getDay(String date1,String date2){
		List<CashTrVO> list = new ArrayList<CashTrVO>();
		CashTrVO cashTrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String getDaySql = "select * from trandaction where to_char(trandaction_date,'yyyy-mm-dd HH:MI:SS') > "
			+ date1 + "to_char(trandaction_date,'yyyy-mm-dd HH:MI:SS')" + date2 ;
			pstmt =con.prepareStatement(getDaySql);
			
			rs = pstmt.executeQuery();
			while (rs.next()){
				cashTrVO = new CashTrVO();
				cashTrVO.setMem_no(rs.getInt("mem_no"));				
				cashTrVO.setTrandaction_money(rs.getInt("trandaction_money"));
				cashTrVO.setTrandaction_no(rs.getInt("trandaction_no"));
//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("trandaction_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("trandaction_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					cashTrVO.setTrandaction_date(new java.sql.Timestamp(d1));
				}
				else{
					cashTrVO.setTrandaction_date(rs.getTimestamp("trandaction_date"));
				}
//-----------------------------------------------------------------------------------------------------------------------//
				list.add(cashTrVO);
			}
			
			
		}catch (ClassNotFoundException e) {
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
		return list;
	}
	
	public List<CashTrVO> getTrMoney(Integer minMoney,Integer maxMoney){
		List<CashTrVO> list = new ArrayList<CashTrVO>();
		return list;
	}
}
