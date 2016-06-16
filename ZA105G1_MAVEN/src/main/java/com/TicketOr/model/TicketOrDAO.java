package com.TicketOr.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.OrderDe.model.OrderDeDAO;
import com.TicketShopCar.model.Ticket;
import com.TicketType.model.TicketTypeService;
import com.member.model.MemberDAO;

public class TicketOrDAO implements TicketOrDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	
	private static final String INSERT_STMT =			
			"INSERT INTO tickets_order (" 	+
			"order_no,"						+
			"order_Date,"					+
			"mem_no,"						+
			"order_money,"					+
			"order_status)"					+
			"VALUES (order_no_seq.NEXTVAL,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT "+
			"order_no,"+
			"to_char(order_Date,'yyyy-mm-dd') order_Date,"+
			"mem_no,"+
			"order_money,"+
			"order_status "+
			" FROM tickets_order order by order_no";
	private static final String GET_ONE_STMT = 
			"SELECT "+
			"order_no,"+
			"to_char(order_Date,'yyyy-mm-dd') order_Date,"+
			"mem_no,"+
			"order_money,"+
			"order_status "+
			"FROM tickets_order where order_no = ?";
	private static final String DELETE = 
			"DELETE FROM tickets_order where order_no = ?";
	private static final String UPDATE =
			"UPDATE tickets_order set "		+
			"order_Date=?,"					+
			"mem_no=?,"					+
			"order_money=?,"				+
			"order_status=? "			+
			"where order_no=?";
	
//	新增
	public void insert(TicketOrVO ticketOrVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setDate(1,ticketOrVO.getOrder_date());
			pstmt.setInt(2,ticketOrVO.getMem_no());
			pstmt.setInt(3,ticketOrVO.getOrder_money());
			pstmt.setInt(4,ticketOrVO.getOrder_status());
			
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
	
//	修改
	public void update(TicketOrVO ticketOrVO){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setDate(1,ticketOrVO.getOrder_date());
			pstmt.setInt(2,ticketOrVO.getMem_no());
			pstmt.setInt(3,ticketOrVO.getOrder_money());
			pstmt.setInt(4,ticketOrVO.getOrder_status());
			
			pstmt.setInt(5,ticketOrVO.getOrder_no());
			
			
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
//	刪除
	public void delete(Integer order_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,order_no);

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
//	查詢
	public TicketOrVO findByPrimaryKey(Integer order_no){
		TicketOrVO ticketOrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,order_no);

			rs = pstmt.executeQuery();

			while (rs.next()){				
				ticketOrVO = new TicketOrVO();
				
				ticketOrVO.setOrder_no(rs.getInt("order_no"));
				ticketOrVO.setOrder_date(rs.getDate("order_date"));					
				ticketOrVO.setMem_no(rs.getInt("mem_no"));
				ticketOrVO.setOrder_money(rs.getInt("order_money"));				
				ticketOrVO.setOrder_status(rs.getInt("order_status"));
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
		return ticketOrVO;
	}
//	全部查詢
	public List<TicketOrVO> getAll(){
		List<TicketOrVO> list = new ArrayList<TicketOrVO>();
		
		TicketOrVO ticketOrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()){				
				ticketOrVO = new TicketOrVO();
				
				ticketOrVO.setOrder_no(rs.getInt("order_no"));
				ticketOrVO.setOrder_date(rs.getDate("order_date"));					
				ticketOrVO.setMem_no(rs.getInt("mem_no"));
				ticketOrVO.setOrder_money(rs.getInt("order_money"));				
				ticketOrVO.setOrder_status(rs.getInt("order_status"));
				list.add(ticketOrVO);
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
	
	// 購買時 交易的主鍵Table 訂單資訊 1筆		list陣列資料     /會員帳號      /計算的總金額        /折扣
	private static final String INSERT_STMT2 =			
			"INSERT INTO tickets_order (" 	+
			"order_no,"						+
			"order_Date,"					+
			"mem_no,"						+
			"order_money,"					+
			"order_status)"					+
			"VALUES (order_no_seq.NEXTVAL,?,?,?,?)";
	// 購買時 交易的主鍵Table 訂單資訊 1筆		list陣列資料     /會員帳號      /計算的總金額        /折扣
	public TicketOrVO getnewtickettype_order(Vector list,int mem_no,int sal,double discount){
		TicketOrVO ticketOrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{	
			con = ds.getConnection();
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false); //關閉交易
    		
    		//先創立團購券訂單資訊
    		
    		//跟資料說說哪個是主鍵
    		String cols[] = {"order_no"};
    		pstmt = con.prepareStatement(INSERT_STMT2 , cols);
    		//----------------新增現在時間----------------------//
    		java.util.Date nowdate = new java.util.Date();
    		long newdatelong = nowdate.getTime();
    		java.sql.Date sqlDate = new java.sql.Date(newdatelong); 
    		//------------------------------------------------//
			pstmt.setDate(1,sqlDate);
			pstmt.setInt(2,mem_no);
			pstmt.setInt(3,sal);
			pstmt.setInt(4,1);
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
    		//System.out.println("新增日期:" + sqlDate);
    		//先創立團購券訂單資訊
			
			//掘取對應的自增主鍵值
			//Integer next_deptno_int;
			//String next_deptno = null;																//此訂單編號 
			Integer next_deptno = null;																    //此訂單編號 
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_deptno = rs.getInt(1);
				System.out.println("自增主鍵值= " + next_deptno +"訂單編號");
			} else {
				System.out.println("未取得自增主鍵值");
			}			
			rs.close();
			//next_deptno_int = (Integer)next_deptno;
			// 再同時在新增團購券訂單明細
			
			for (int i = 0; i < list.size(); i++) {
				Ticket order = (Ticket) list.get(i);
				int tickets_type_no = order.getTickets_type_no(); 										//取得團購券種類編號(tickets_type_no)
				TicketTypeService tickSvc = new TicketTypeService();
				Integer nodiscountprice = tickSvc.getOneTicketType(tickets_type_no).getTickets_price(); //取出資料庫編號的價格去算(沒有折扣的價格)
				Integer price = (int)(nodiscountprice * discount);										//折價後的單品價格
				int qu = order.getQuantity();															//購買此種類團購劵的數量 ()
				
				OrderDeDAO orderde = new OrderDeDAO();
				orderde.getneworderde(tickets_type_no, next_deptno, price,qu,con);
				
				
//				//新增團購券訂單明細
//				//傳入  團購券種類編號 / 此訂單編號  / 折價後的單品價格/ 本 團購券種類的購買數量/ 連線
			}
			//開始扣款囉~~

			MemberDAO memberdao = new MemberDAO();
			memberdao.comoney(mem_no, sal, con);
			
			con.commit();
			con.setAutoCommit(true);
			
			//以上全部做完 用訂單編號去查詢本訂單資料
			
			ticketOrVO = findByPrimaryKey(next_deptno);
			
			// 做成之後，確定訂單正常購買了，要確定資料庫剩餘數量是否為0 為0就售完
			TicketTypeService tickettypeservice = new TicketTypeService();
			for (int i = 0; i < list.size(); i++) {
				Ticket order = (Ticket) list.get(i);
				int tickets_type_no = order.getTickets_type_no(); 										//取得團購券種類編號(tickets_type_no)
				Integer time = tickettypeservice.getOneTicketType(tickets_type_no).getTickets_quantity();
				if (time == 0){
					tickettypeservice.updatestateTicketType(tickets_type_no,4);
					System.out.println("改成已售完");
				}
			}
			
		}
		catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ticketOrVO;
	}
	
	private static final String GET_ONE_STMT2 =				"SELECT " +
			"order_no,"+
			"to_char(order_Date,'yyyy-mm-dd') order_Date,"+
			"mem_no,"+
			"order_money,"+
			"order_status "+
			"FROM tickets_order where mem_no = ?";
	public List<TicketOrVO> getUser(Integer mem_no){
		List<TicketOrVO> list = new ArrayList<TicketOrVO>();
		TicketOrVO ticketOrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT2);
			
			pstmt.setInt(1,mem_no);

			rs = pstmt.executeQuery();
			
			while (rs.next()){				
				ticketOrVO = new TicketOrVO();
				
				ticketOrVO.setOrder_no(rs.getInt("order_no"));
				ticketOrVO.setOrder_date(rs.getDate("order_date"));					
				ticketOrVO.setMem_no(rs.getInt("mem_no"));
				ticketOrVO.setOrder_money(rs.getInt("order_money"));				
				ticketOrVO.setOrder_status(rs.getInt("order_status"));
				list.add(ticketOrVO);
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
}