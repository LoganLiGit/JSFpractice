package com.TicketType.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.TicketNo.model.TicketNoDAO;
import com.TicketNo.model.TicketNoVO;
import com.store.model.StoreDAO;


public class TicketTypeJNDIDAO implements TicketTypeDAO_interface {
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
			"INSERT INTO ticket_type (" 			+			
			"tickets_type_no,"						+
			"tickets_type_name,"					+
			"upper_Date,"							+
			"lower_Date,"							+
			"application_date,"						+
			"tickets_total,"						+
			"tickets_quantity,"						+
			"tickets_price,"						+
			"tickets_state,"						+
			"store_no,"								+
			"tickets_ex, "							+
			"TICKETS_IMAGE) "						+
			"VALUES (tickets_type_no_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	
	//新增
	public void insert(TicketTypeVO ticketTypeVO){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,ticketTypeVO.getTickets_type_name());
			pstmt.setDate(2,ticketTypeVO.getUpper_date());			
			pstmt.setDate(3,ticketTypeVO.getLower_date());
			//----------------新增現在時間----------------------//
			java.util.Date nowdate = new java.util.Date();
			long newdatelong = nowdate.getTime();
			Timestamp timestamp =new Timestamp(newdatelong);
			pstmt.setTimestamp(4,timestamp);
			//-------------------------------------------------//
			pstmt.setInt(5,ticketTypeVO.getTickets_total());
			pstmt.setInt(6,ticketTypeVO.getTickets_quantity());
			pstmt.setInt(7,ticketTypeVO.getTickets_price());
			pstmt.setInt(8,ticketTypeVO.getTickets_state());
			pstmt.setInt(9,ticketTypeVO.getStore_no());
			pstmt.setString(10,ticketTypeVO.getTickets_ex());
			pstmt.setBytes(11,ticketTypeVO.getTickets_image());
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
	
	//UPDATE (修改) 的 SQL 指令 (有圖片版本)
	private static final String UPDATE =
			"UPDATE ticket_type set "		+
			"tickets_type_name=?,"			+
			"upper_Date=?,"					+
			"lower_Date=?,"					+
			"tickets_total=?,"				+
			"tickets_quantity=?,"			+
			"tickets_price=?,"				+
			"tickets_state=?,"				+
			"store_no=?,"					+
			"tickets_ex=?,"					+
			"TICKETS_IMAGE=? "				+			
			"where tickets_type_no=?";

	//修改(包含圖片)
	public void update(TicketTypeVO ticketTypeVO){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,ticketTypeVO.getTickets_type_name());
			pstmt.setDate(2,ticketTypeVO.getUpper_date());
			pstmt.setDate(3,ticketTypeVO.getLower_date());
			pstmt.setInt(4,ticketTypeVO.getTickets_total());
			pstmt.setInt(5,ticketTypeVO.getTickets_quantity());
			pstmt.setInt(6,ticketTypeVO.getTickets_price());
			pstmt.setInt(7,ticketTypeVO.getTickets_state());
			pstmt.setInt(8,ticketTypeVO.getStore_no());
			pstmt.setString(9,ticketTypeVO.getTickets_ex());
			pstmt.setBytes(10,ticketTypeVO.getTickets_image());
			
			pstmt.setInt(11,ticketTypeVO.getTickets_type_no());
						
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
	
	//UPDATE (修改) 的 SQL 指令 (無圖片版本)
	private static final String UPDATENOIMG =
			"UPDATE ticket_type set "		+
			"tickets_type_name=?,"			+
			"upper_Date=?,"					+
			"lower_Date=?,"					+
			"tickets_total=?,"				+
			"tickets_quantity=?,"			+
			"tickets_price=?,"				+
			"tickets_state=?,"				+
			"store_no=?,"					+
			"tickets_ex=? "					+			
			"where tickets_type_no=?";
	
	//修改(不包含圖片)
	public void updateno(TicketTypeVO ticketTypeVO){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATENOIMG);
			
			pstmt.setString(1,ticketTypeVO.getTickets_type_name());
			pstmt.setDate(2,ticketTypeVO.getUpper_date());
			pstmt.setDate(3,ticketTypeVO.getLower_date());
			pstmt.setInt(4,ticketTypeVO.getTickets_total());
			pstmt.setInt(5,ticketTypeVO.getTickets_quantity());
			pstmt.setInt(6,ticketTypeVO.getTickets_price());
			pstmt.setInt(7,ticketTypeVO.getTickets_state());
			pstmt.setInt(8,ticketTypeVO.getStore_no());
			pstmt.setString(9,ticketTypeVO.getTickets_ex());
			
			pstmt.setInt(10,ticketTypeVO.getTickets_type_no());
						
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
	
	//修改團購劵狀態
	public void updatestate(Integer tickets_type_no,Integer tickets_state){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			String updateno = "UPDATE ticket_type set tickets_state= " + tickets_state + "where tickets_type_no=" + tickets_type_no;
			pstmt = con.prepareStatement(updateno);						
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
	//delete (刪除) 的 SQL 指令)
	private static final String DELETE = 
			"DELETE FROM ticket_type "	+
			" where tickets_type_no = ?";
	
	//刪除
	public void delete(Integer tickets_type_no){
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,tickets_type_no);

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
	
	//findByPrimaryKey (一鍵查找) 的 SQL 指令
	private static final String GET_ONE_STMT = 
			"SELECT "										+			
			"tickets_type_no,"								+
			"tickets_type_name,"							+
			"to_char(upper_Date,'yyyy-mm-dd') upper_Date,"	+
			"to_char(lower_Date,'yyyy-mm-dd') lower_Date,"	+
			"application_date,"								+
			"tickets_total,"								+
			"tickets_quantity,"								+
			"tickets_price,"								+
			"tickets_state,"								+
			"store_no,"										+
			"tickets_ex,"									+
			"TICKETS_IMAGE "								+
			"FROM ticket_type where tickets_type_no = ?";
	
	//一鍵查找
 	public TicketTypeVO findByPrimaryKey(Integer tickets_type_no){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		TicketTypeVO ticketTypeVO  = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,tickets_type_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				ticketTypeVO = new TicketTypeVO();
				ticketTypeVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketTypeVO.setTickets_type_name(rs.getString("tickets_type_name"));
				ticketTypeVO.setUpper_date(rs.getDate("upper_date"));
				ticketTypeVO.setLower_date(rs.getDate("lower_date"));
				//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("application_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("application_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					ticketTypeVO.setApplication_date(new java.sql.Timestamp(d1));
				}
				else{
					ticketTypeVO.setApplication_date(rs.getTimestamp("application_date"));
				}

				//-----------------------------------------------------------------------------------------------------------------------//					
				ticketTypeVO.setTickets_total(rs.getInt("tickets_total"));
				ticketTypeVO.setTickets_quantity(rs.getInt("tickets_quantity"));
				ticketTypeVO.setTickets_price(rs.getInt("tickets_price"));
				ticketTypeVO.setTickets_state(rs.getInt("tickets_state"));
				ticketTypeVO.setStore_no(rs.getInt("store_no"));
				ticketTypeVO.setTickets_ex(rs.getString("tickets_ex"));
				ticketTypeVO.setTickets_image(rs.getBytes("tickets_image"));
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
		return ticketTypeVO;

	}
	
	//getAll (全找) 的 SQL 指令
	private static final String GET_ALL_STMT =
			"SELECT "										+			
			"tickets_type_no,"								+
			"tickets_type_name,"							+
			"to_char(upper_Date,'yyyy-mm-dd') upper_Date,"	+
			"to_char(lower_Date,'yyyy-mm-dd') lower_Date,"	+
			"application_date,"								+
			"tickets_total,"								+
			"tickets_quantity,"								+
			"tickets_price,"								+
			"tickets_state,"								+
			"store_no,"										+
			"tickets_ex,"									+
			"TICKETS_IMAGE "								+
			"FROM ticket_type order by tickets_type_no";
	
	//全找
	public List<TicketTypeVO> getAll(){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		List<TicketTypeVO> list = new ArrayList<TicketTypeVO>();
		
		TicketTypeVO ticketTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				ticketTypeVO = new TicketTypeVO();
				ticketTypeVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketTypeVO.setTickets_type_name(rs.getString("tickets_type_name"));
				ticketTypeVO.setUpper_date(rs.getDate("upper_date"));
				ticketTypeVO.setLower_date(rs.getDate("lower_date"));
//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("application_date") != null ){
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("application_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					ticketTypeVO.setApplication_date(new java.sql.Timestamp(d1));
				}
				else{
					ticketTypeVO.setApplication_date(rs.getTimestamp("application_date"));
				}

//-----------------------------------------------------------------------------------------------------------------------//				
				ticketTypeVO.setTickets_total(rs.getInt("tickets_total"));
				ticketTypeVO.setTickets_quantity(rs.getInt("tickets_quantity"));
				ticketTypeVO.setTickets_price(rs.getInt("tickets_price"));
				ticketTypeVO.setTickets_state(rs.getInt("tickets_state"));
				ticketTypeVO.setStore_no(rs.getInt("store_no"));
				ticketTypeVO.setTickets_ex(rs.getString("tickets_ex"));
				ticketTypeVO.setTickets_image(rs.getBytes("tickets_image"));
				list.add(ticketTypeVO);
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
	
	//selectlastadd (查找最新日期) 的 SQL 指令	
	private static final String SELECTADD = "SELECT max(APPLICATION_DATE) APPLICATION_DATE FROM ticket_type where STORE_NO = ?";
	
	//查找最新日期的資料
	public TicketTypeVO selectlastadd(Integer store_no){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		TicketTypeVO ticketTypeVO  = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECTADD);
			
			pstmt.setInt(1,store_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				ticketTypeVO = new TicketTypeVO();
//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//				將拿出來的時間轉成long型態
				if (rs.getString("application_date") != null ){					
					//將拿出來的時間轉成long型態
					long d1 = Timestamp.valueOf(rs.getString("application_date")).getTime();
//					long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
					System.out.println(new java.sql.Timestamp(d1) );
					ticketTypeVO.setApplication_date(new java.sql.Timestamp(d1));
				}
				else{					
					ticketTypeVO.setApplication_date(rs.getTimestamp("application_date"));
				}
//-----------------------------------------------------------------------------------------------------------------------//		
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
		return ticketTypeVO;
	}
	
	//selectlastaddticketsno 查找最新日期的種類編號 的 SQL 指令
	public static final String SELECTADDNO ="select tickets_type_no from TICKET_TYPE where APPLICATION_DATE = (SELECT max(APPLICATION_DATE) APPLICATION_DATE FROM ticket_type where STORE_NO = ? )";
	
	//查找最新日期的種類編號
	public TicketTypeVO selectlastaddticketsno(Integer store_no){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		TicketTypeVO ticketTypeVO  = null;		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECTADDNO);
			
			pstmt.setInt(1,store_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()){
				ticketTypeVO = new TicketTypeVO();
				ticketTypeVO.setTickets_type_no(rs.getInt("tickets_type_no"));
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
		return ticketTypeVO;
	}
	
	
	//萬用複合查詢
	public List<TicketTypeVO> getAll(Map<String, String[]> map){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
			List<TicketTypeVO> list = new ArrayList<TicketTypeVO>();
			TicketTypeVO ticketTypeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = ds.getConnection();
				
				String outSql = "select * from ticket_type " + CompositeQuery.get_Where(map) + "order by tickets_type_no";
				
				pstmt =con.prepareStatement(outSql);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()){					
					ticketTypeVO = new TicketTypeVO();
					ticketTypeVO.setTickets_type_no(rs.getInt("tickets_type_no"));
					ticketTypeVO.setTickets_type_name(rs.getString("tickets_type_name"));
					ticketTypeVO.setUpper_date(rs.getDate("upper_date"));
					ticketTypeVO.setLower_date(rs.getDate("lower_date"));					
//-------------------------------------因為需要	日期 + 時分秒 需要做特別處理 ----------------------------------------------//
//					將拿出來的時間轉成long型態
					if (rs.getString("application_date") != null ){
						//將拿出來的時間轉成long型態
						long d1 = Timestamp.valueOf(rs.getString("application_date")).getTime();
//						long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
						ticketTypeVO.setApplication_date(new java.sql.Timestamp(d1));
					}
					else{
						ticketTypeVO.setApplication_date(rs.getTimestamp("application_date"));
					}
//-----------------------------------------------------------------------------------------------------------------------//					
					ticketTypeVO.setTickets_total(rs.getInt("tickets_total"));
					ticketTypeVO.setTickets_quantity(rs.getInt("tickets_quantity"));
					ticketTypeVO.setTickets_price(rs.getInt("tickets_price"));
					ticketTypeVO.setTickets_state(rs.getInt("tickets_state"));
					ticketTypeVO.setStore_no(rs.getInt("store_no"));
					ticketTypeVO.setTickets_ex(rs.getString("tickets_ex"));					
					ticketTypeVO.setTickets_image(rs.getBytes("tickets_image"));
					list.add(ticketTypeVO);
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
	
	//商店街要上架的物品  的 SQL 指令
	private static final String GET_ALL_SHOP =
			"SELECT "										+			
			"tickets_type_no,"								+
			"tickets_type_name,"							+
			"to_char(upper_Date,'yyyy-mm-dd') upper_Date,"	+
			"to_char(lower_Date,'yyyy-mm-dd') lower_Date,"	+
			"tickets_total,"								+
			"tickets_quantity,"								+
			"tickets_price,"								+
			"tickets_state,"								+
			"store_no,"										+
			"tickets_ex,"									+
			"TICKETS_IMAGE "								+
			"FROM ticket_type where tickets_state = 2 order by tickets_type_no";
	
	//商店街要上架的物品
	public List<TicketTypeVO> getshopall(){
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		List<TicketTypeVO> list = new ArrayList<TicketTypeVO>();
		
		TicketTypeVO ticketTypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SHOP);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				ticketTypeVO = new TicketTypeVO();
				ticketTypeVO.setTickets_type_no(rs.getInt("tickets_type_no"));
				ticketTypeVO.setTickets_type_name(rs.getString("tickets_type_name"));
				ticketTypeVO.setUpper_date(rs.getDate("upper_date"));
				ticketTypeVO.setLower_date(rs.getDate("lower_date"));	
				ticketTypeVO.setTickets_total(rs.getInt("tickets_total"));
				ticketTypeVO.setTickets_quantity(rs.getInt("tickets_quantity"));
				ticketTypeVO.setTickets_price(rs.getInt("tickets_price"));
				ticketTypeVO.setTickets_state(rs.getInt("tickets_state"));
				ticketTypeVO.setStore_no(rs.getInt("store_no"));
				ticketTypeVO.setTickets_ex(rs.getString("tickets_ex"));
				ticketTypeVO.setTickets_image(rs.getBytes("tickets_image"));
				list.add(ticketTypeVO);
			}
			System.out.println(list.size());
			
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

	
	//團購劵審核通過 創造序號的 SQL 指令
	private static final String STATEOK =
			"UPDATE ticket_type set "		+
			"tickets_quantity=?,"			+
			"tickets_state=? "				+			
			"where tickets_type_no=?";
	
	//團購劵審核通過 創造序號
	public void stateok(Integer tickets_type_no){
		TicketNoDAO dao = new TicketNoDAO();
		Integer state = findByPrimaryKey(tickets_type_no).getTickets_state(); //先查到目前的狀態 如果等於0才做
		Integer total = findByPrimaryKey(tickets_type_no).getTickets_total(); //查詢發行總數量
		Map<String,String[]> map = new TreeMap<String,String[]>();
		String tickets_type_no_String = tickets_type_no.toString(); //把編號轉成字串 符合複合查詢的指令
		map.put("tickets_type_no",new String[]{tickets_type_no_String}); //把字串丟進去複合查詢需要的map
		List<TicketNoVO> list = dao.getAll(map); 
		Integer totalok = list.size();//查詢目前已創造團購劵的序號數量
		
		System.out.println("當前此團購劵已有:" + totalok + "張序號");
		//if (state == 0 && (total - totalok) == total){
		if (state == 0){
			Connection con = null;
			PreparedStatement pstmt = null;
			try{
				con = ds.getConnection();
				
				// 1●設定於 pstm.executeUpdate()之前關閉交易
	    		con.setAutoCommit(false);
	    		
	    		for (int i=0;i<total;i++){ //看總數量有多少 就創造多少張
	    			dao.state1_create_tickettype(tickets_type_no,con);
	    		}
	    		pstmt = con.prepareStatement(STATEOK);
	    		
	    		TicketTypeVO ticketTypeVO = new TicketTypeVO();
				pstmt.setInt(1,total);
				pstmt.setInt(2,1);
				pstmt.setInt(3,tickets_type_no);
	    		//修改狀態
	    		//修改剩餘數量
				pstmt.executeUpdate();    		
	    		
				con.commit();
				con.setAutoCommit(true);
				System.out.println("total ="+ total );
	    		
				
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
		else {
			System.out.println("已有團購劵了，不能重複狀態由未審核轉審核完畢");
		}
	}
	
	//-------------------------------當被購買時狀態改變(貫穿最底層)------------------------------//
	//扣除團購劵剩餘數量				團購劵種類編號	 / 扣除數量			 /連線							
	public void deductedtime(Integer tickets_type_no,Integer deducte,java.sql.Connection con){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Integer time = 0;
			//先做團購劵種類編號 剩餘數量的查詢 (開始)
			pstmt = con.prepareStatement(GET_ONE_STMT);			
			pstmt.setInt(1,tickets_type_no);			
			rs = pstmt.executeQuery();			
			while (rs.next()){
				time = rs.getInt("tickets_quantity"); //剩餘數量(未販賣)
				System.out.println(time);
			}
			//先做團購劵種類編號 剩餘數量的查詢 (結束)
			
			//開始做剩餘數量的更改(開始)
			time = time - deducte;
			String updateno = "UPDATE ticket_type set tickets_quantity= " + time + "where tickets_type_no= " + tickets_type_no;
			pstmt = con.prepareStatement(updateno);						
			pstmt.executeUpdate();			
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being 伺服器有錯誤: ");
					System.err.println("此 rolled back-由 TicketTypeJDBCDAO 發生");
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
	
	
	//---------------------------------店家團購金結清部分----------------------------------------------//
	//---------------------------------店家團購金結清部分----------------------------------------------//
	public Integer checkmoney(Integer tickets_type_no,Double co){
		String sql_checkmoneyString = "UPDATE ticket_type set tickets_state=6 where tickets_type_no=" + tickets_type_no ;
		Connection con = null;
		PreparedStatement pstmt = null;
		StoreDAO storedao = new StoreDAO();
		Integer totalmoney = 0;
		try{
			//System.out.println("成功進來 ");
			Integer time = findByPrimaryKey(tickets_type_no).getTickets_total();
			Integer money = findByPrimaryKey(tickets_type_no).getTickets_price();
			Integer storeno = findByPrimaryKey(tickets_type_no).getStore_no();
			Integer state = findByPrimaryKey(tickets_type_no).getTickets_state();
			totalmoney = (int) (time * money * co);
			if (state== 4){
				con = ds.getConnection();
				// 1●設定於 pstm.executeUpdate()之前關閉交易
	    		con.setAutoCommit(false);
				pstmt = con.prepareStatement(sql_checkmoneyString);
				storedao.storecheckmoney(storeno, totalmoney, con);
				
				pstmt.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			}

		}catch (SQLException se) {
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
		return totalmoney;
	}
	
	//查詢狀態的數量有多少
	public Integer selectnum(Integer tickets_state){
		String sql_select_num = "SELECT count(*) as count FROM ticket_type where tickets_state = " + tickets_state ;
		TicketTypeVO ticketTypeVO = null;
		Integer state_num = 0;
		Connection con = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql_select_num);
			rs.next();
			state_num =rs.getInt("count");
			
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
		return state_num;
	}
	
	//查詢店家團購劵狀態的數量有多少
	public Integer select_store_num(Integer store_no ,Integer tickets_state){
		String sql_select_store_num = "SELECT count(*) as count FROM ticket_type where tickets_state = " + tickets_state + " and store_no = " + store_no;
		TicketTypeVO ticketTypeVO = null;
		Integer state_num = 0;
		Connection con = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql_select_store_num);
			rs.next();
			state_num =rs.getInt("count");
			
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
		return state_num;
	}
	
	public Integer select_num(Map<String, String[]> map)	{
		Integer state_num = 0;
		Connection con = null;
		Statement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
				
			String sql_select_num = "SELECT count(*) as count FROM ticket_type " + CompositeQuery.get_Where(map) ;
				
			pstmt = con.createStatement();
			rs = pstmt.executeQuery(sql_select_num);
				
			rs.next();
			state_num =rs.getInt("count");
				
				
		} 
		catch (SQLException se) {
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
