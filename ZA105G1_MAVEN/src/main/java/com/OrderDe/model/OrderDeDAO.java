package com.OrderDe.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.TicketNo.model.TicketNoDAO;
import com.TicketNo.model.TicketNoVO;
import com.TicketType.model.TicketTypeDAO;

public class OrderDeDAO implements OrderDeDAO_interface {
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
			"INSERT INTO order_details (" 			+
			"order_no,"								+
			"tickets_no,"							+
			"order_num,"							+
			"order_value,"							+
			"order_status) "						+
			"VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT =
			"SELECT "+
			"order_no,"								+
			"tickets_no,"							+
			"order_num,"							+
			"order_value,"							+
			"order_status "							+
			" FROM order_details order by order_no";
	private static final String GET_ONE_STMT = 
			"SELECT "+
			"order_no,"								+
			"tickets_no,"							+
			"order_num,"							+
			"order_value,"							+
			"order_status "							+
			" FROM order_details where order_no = ? AND tickets_no = ? ";
	private static final String DELETE = 
			"DELETE FROM order_details where order_no = ? AND tickets_no = ?";
	private static final String UPDATE =
			"UPDATE order_details set "			+
			"order_num=?,"						+
			"order_value=?,"					+
			"order_status=? "					+
			" where tickets_no=? AND order_no=? ";
	
	//新增
	public void insert(OrderDeVO orderDeVo){
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,orderDeVo.getOrder_no());
			pstmt.setString(2,orderDeVo.getTickets_no());
			pstmt.setInt(3,orderDeVo.getOrder_num());
			pstmt.setInt(4,orderDeVo.getOrder_value());
			pstmt.setInt(5,orderDeVo.getOrder_status());
			
			pstmt.executeUpdate();
			System.out.print("NEW TEST OK!!");
			
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
	public void update(OrderDeVO orderDeVo){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);


			
			pstmt.setInt(1,orderDeVo.getOrder_num());
			pstmt.setInt(2,orderDeVo.getOrder_value());
			pstmt.setInt(3,orderDeVo.getOrder_status());
			
			pstmt.setString(4,orderDeVo.getTickets_no());
			pstmt.setInt(5,orderDeVo.getOrder_no());
			
			pstmt.executeUpdate();
			System.out.print("NEW TEST OK!!");
			
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
	public void delete(Integer order_no,String tickets_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,order_no);
			pstmt.setString(2,tickets_no);

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
	public OrderDeVO findByPrimaryKey(Integer order_no,String tickets_no){
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1,order_no);
			pstmt.setString(2,tickets_no);	

			rs = pstmt.executeQuery();

			while (rs.next()){				
				orderdevo = new OrderDeVO();
				
				orderdevo.setOrder_no(rs.getInt("order_no"));
				orderdevo.setTickets_no(rs.getString("tickets_no"));
				orderdevo.setOrder_num(rs.getInt("order_num"));
				orderdevo.setOrder_value(rs.getInt("order_value"));
				orderdevo.setOrder_status(rs.getInt("order_status"));
		
			}
			
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
		return orderdevo;
	}
	public List<OrderDeVO> getAll(){
		List<OrderDeVO> list = new ArrayList<OrderDeVO>();
		
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()){				
				orderdevo = new OrderDeVO();
				
				orderdevo.setOrder_no(rs.getInt("order_no"));
				orderdevo.setTickets_no(rs.getString("tickets_no"));
				orderdevo.setOrder_num(rs.getInt("order_num"));
				orderdevo.setOrder_value(rs.getInt("order_value"));
				orderdevo.setOrder_status(rs.getInt("order_status"));

				list.add(orderdevo);
			}
			
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
	
	
	private static final String INSERT_STMT2 =			
			"INSERT INTO order_details (" 			+
			"order_no,"								+
			"tickets_no,"							+
			"order_num,"							+
			"order_value,"							+
			"order_status) "						+
			"VALUES (?,?,?,?,?)";
	//新增訂單的作業流程
	
	public void getneworderde(Integer tickets_type_no,Integer next_deptno,Integer price,Integer qu,java.sql.Connection con){
		PreparedStatement pstmt = null;
		
		try {
/*------------------以下已把此 團購券總類ticket_type 的團購券訂單明細創立好-----------*/
			TicketNoDAO ticketno = new TicketNoDAO();
			List<TicketNoVO> volist = ticketno.getAllstate(tickets_type_no, 0);//查詢目前尚未賣出的團購劵序號  (0未賣出 / 1尚未使用(已賣出) / 2已使用 / 3失效)
			
			for (int time=0;time<qu;time++){
				String tickid = null;
				
				//先取得尚未賣出的序號
				for (int i = 0 ; i<volist.size();i++){
					if (i==time){
						tickid = volist.get(i).getTickets_no();	
						//先查到尚未使用的序號						
						ticketno.getbuy(tickid,con);
						//在把此序號狀態改成1尚未使用(已賣出)
					}
				}
				
				pstmt = con.prepareStatement(INSERT_STMT2);
	     		pstmt.setInt(1,next_deptno);//訂單編號
	     		pstmt.setString(2,tickid);	//序號
	     		pstmt.setInt(3,0);			// 此為無用表格 等待刪除
	     		pstmt.setInt(4,price);		//價格
	     		pstmt.setInt(5,0);			//購買狀態(0正常	1退訂)
	     		pstmt.executeUpdate();
	     		//新增訂單的作業流程 (多個)
	     		//新增團購券訂單明細				
			}
			
/*------------------以上已把此 團購券總類ticket_type 的團購券訂單明細創立好-----------*/

/*----------------------團購券總類ticket_type 剩餘數量 扣掉-------------------------*/
			
			TicketTypeDAO tickettypedao = new TicketTypeDAO();
			//扣除團購劵剩餘數量				團購劵種類編號	 / 扣除數量			 /連線	
			tickettypedao.deductedtime(tickets_type_no, qu, con);
					
			
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being 伺服器有錯誤:");
					System.err.println("此 rolled back-由 OrderDeJDBCDAO 發生");
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
	
	//利用order_no 訂單編號 尋找所有的
	public List<OrderDeVO> findByPrimaryKey_Order(Integer order_no){
		List<OrderDeVO> list = new ArrayList<OrderDeVO>();
		
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String GET_ONE_STMTA = "SELECT order_no,tickets_no,order_num,order_value,order_status FROM order_details where order_no = " + order_no ;
			pstmt = con.prepareStatement(GET_ONE_STMTA);
			rs = pstmt.executeQuery();
			
			while (rs.next()){				
				orderdevo = new OrderDeVO();
				
				orderdevo.setOrder_no(rs.getInt("order_no"));
				orderdevo.setTickets_no(rs.getString("tickets_no"));
				orderdevo.setOrder_num(rs.getInt("order_num"));
				orderdevo.setOrder_value(rs.getInt("order_value"));
				orderdevo.setOrder_status(rs.getInt("order_status"));

				list.add(orderdevo);
			}
			
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
	
}

