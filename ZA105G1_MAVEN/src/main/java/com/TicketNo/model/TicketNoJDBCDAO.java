package com.TicketNo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicketNoJDBCDAO implements TicketNoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "prada";
	String passwd = "aa20070";
	
	private static final String INSERT_STMT =			
			"INSERT INTO tickets_no_detail (" 	+
			"tickets_no,"						+
			"tickets_type_no,"						+
			"tickets_no_status) "					+
			"VALUES (?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT "+
			"tickets_no,"+
			"tickets_type_no,"+
			"tickets_no_status "+
			" FROM tickets_no_detail order by tickets_no";
	private static final String GET_ONE_STMT = 
			"SELECT "+
			"tickets_no,"+
			"tickets_type_no,"+
			"tickets_no_status "+
			"FROM tickets_no_detail where tickets_no = ?";
	private static final String DELETE = 
			"DELETE FROM tickets_no_detail where tickets_no = ?";
	private static final String UPDATE =
			"UPDATE tickets_no_detail set "		+
			"tickets_type_no=?,"					+
			"tickets_no_status=? "					+
			"where tickets_no=?";
	
//	新增
	public void insert(TicketNoVO ticketNoVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			String id = Create_TickID.create(15);
			pstmt.setString(1,id);
			pstmt.setInt(2,ticketNoVO.getTickets_type_no());
			pstmt.setInt(3,ticketNoVO.getTickets_no_status());
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(TicketNoVO ticketNoVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1,ticketNoVO.getTickets_type_no());
			pstmt.setInt(2,ticketNoVO.getTickets_no_status());
			pstmt.setString(3,ticketNoVO.getTickets_no());	
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(String tickets_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1,tickets_no);

			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public TicketNoVO findByPrimaryKey(String tickets_no){
		TicketNoVO ticketNoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1,tickets_no);

			rs = pstmt.executeQuery();

			while (rs.next()){				
				ticketNoVO = new TicketNoVO();
				
				ticketNoVO.setTickets_no(rs.getString("tickets_no"));
				ticketNoVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketNoVO.setTickets_no_status(rs.getInt("tickets_no_status"));			
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return ticketNoVO;
	}
//	全部查詢
	public List<TicketNoVO> getAll(){
		List<TicketNoVO> list = new ArrayList<TicketNoVO>();
		
		TicketNoVO ticketNoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()){				
				ticketNoVO = new TicketNoVO();
				
				ticketNoVO.setTickets_no(rs.getString("tickets_no"));
				ticketNoVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketNoVO.setTickets_no_status(rs.getInt("tickets_no_status"));
				list.add(ticketNoVO);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	//當團購劵 從未審核(0) 轉換成 已審核(1) 需要做的動作
	public void state1_create_tickettype(Integer tickets_type_no,java.sql.Connection con){
		PreparedStatement pstmt = null;
		try {

			String id = Create_TickID.create(10).toString();
			
			String insertstmy2sql = "INSERT INTO tickets_no_detail (tickets_no,tickets_type_no,tickets_no_status) VALUES ('" + id + "'," + tickets_type_no + ",0)"; 
			System.out.println(insertstmy2sql);
			pstmt = con.prepareStatement(insertstmy2sql);

			pstmt.executeUpdate();
			
		
		} catch (Exception se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由state1_create_tickettype");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}		
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	//萬用複合查詢
	public List<TicketNoVO> getAll(Map<String, String[]> map){
		List<TicketNoVO> list = new ArrayList<TicketNoVO>();
		TicketNoVO ticketNoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String outSql = "select * from TICKETS_NO_DETAIL " + CompositeQuery.get_Where(map) + " order by TICKETS_NO ";
			
			pstmt =con.prepareStatement(outSql);
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				ticketNoVO = new TicketNoVO();
				ticketNoVO.setTickets_no(rs.getString("tickets_no"));				
				ticketNoVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketNoVO.setTickets_no_status(rs.getInt("tickets_no_status"));
				list.add(ticketNoVO);
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
	
	private static final String GET_ONE_STMT2 = 
			"SELECT "+
			"tickets_no,"+
			"tickets_type_no,"+
			"tickets_no_status "+
			"FROM tickets_no_detail where tickets_type_no = ? and tickets_no_status = ?";
	//查詢狀態的List	
	public List<TicketNoVO> getAllstate(Integer tickets_type_no,Integer tickets_no_status){
		List<TicketNoVO> list = new ArrayList<TicketNoVO>();
		TicketNoVO ticketNoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT2);
			pstmt.setInt(1,tickets_type_no);
			pstmt.setInt(2,tickets_no_status);
			rs = pstmt.executeQuery();
			
			while (rs.next()){				
				ticketNoVO = new TicketNoVO();
				
				ticketNoVO.setTickets_no(rs.getString("tickets_no"));
				ticketNoVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketNoVO.setTickets_no_status(rs.getInt("tickets_no_status"));
				list.add(ticketNoVO);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	private static final String UPDATESTATE =
			"UPDATE tickets_no_detail set "		+
			"tickets_no_status=? "					+
			"where tickets_no=?";
	//當被購買時狀態改變
	public void getbuy(String  tickets_no,java.sql.Connection con){
		PreparedStatement pstmt = null;
		try {

     		pstmt = con.prepareStatement(UPDATESTATE);

			pstmt.setInt(1,1);
			pstmt.setString(2,tickets_no);	

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由TicketNoJDBCDAO");
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
	}
	
	public Integer select_state(Map<String, String[]> map){
		Integer state_num = 0;
		Connection con = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			String sql_select_state = "SELECT count(*) as count FROM TICKETS_NO_DETAIL " + CompositeQuery.get_Where(map);
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql_select_state);
			rs.next();
			state_num =rs.getInt("count");
			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return state_num;
	}
	
}
