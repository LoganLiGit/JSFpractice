package com.OrderDe.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.TicketNo.model.TicketNoJDBCDAO;
import com.TicketNo.model.TicketNoVO;
import com.TicketShopCar.model.Ticket;
import com.TicketType.model.TicketTypeJDBCDAO;

public class OrderDeJDBCDAO implements OrderDeDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "prada";
	String passwd = "aa20070";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,orderDeVo.getOrder_no());
			pstmt.setString(2,orderDeVo.getTickets_no());
			pstmt.setInt(3,orderDeVo.getOrder_num());
			pstmt.setInt(4,orderDeVo.getOrder_value());
			pstmt.setInt(5,orderDeVo.getOrder_status());
			
			pstmt.executeUpdate();
			System.out.print("NEW TEST OK!!");
			
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
	public void update(OrderDeVO orderDeVo){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			
			pstmt.setInt(1,orderDeVo.getOrder_num());
			pstmt.setInt(2,orderDeVo.getOrder_value());
			pstmt.setInt(3,orderDeVo.getOrder_status());
			
			pstmt.setString(4,orderDeVo.getTickets_no());
			pstmt.setInt(5,orderDeVo.getOrder_no());
			
			pstmt.executeUpdate();
			System.out.print("NEW TEST OK!!");
			
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
	public void delete(Integer order_no,String tickets_no){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,order_no);
			pstmt.setString(2,tickets_no);

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
	public OrderDeVO findByPrimaryKey(Integer order_no,String tickets_no){
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return orderdevo;
	}
	
	//利用order_no 訂單編號 尋找所有的
	public List<OrderDeVO> findByPrimaryKey_Order(Integer order_no){
		List<OrderDeVO> list = new ArrayList<OrderDeVO>();
		
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	public List<OrderDeVO> getAll(){
		List<OrderDeVO> list = new ArrayList<OrderDeVO>();
		
		OrderDeVO orderdevo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			TicketNoJDBCDAO ticketno = new TicketNoJDBCDAO();
			List<TicketNoVO> volist = ticketno.getAllstate(tickets_type_no, 0);//查詢目前尚未賣出的團購劵序號  (0未賣出 / 1尚未使用(已賣出) / 2已使用 / 3失效)
			
			for (int time=0;time<qu;time++){
				String tickid = null;
				//先取得尚未賣出的序號
				for (int i = 0 ; i<volist.size();i++){
					if (i==time){
						tickid = volist.get(i).getTickets_no();	
						System.out.println("本次查詢到的" + tickid);
						//在把此序號狀態改成1尚未使用(已賣出)
						ticketno.getbuy(tickid,con);
					}				
				}			
				pstmt = con.prepareStatement(INSERT_STMT2);
	     		pstmt.setInt(1,next_deptno);//訂單編號
	     		pstmt.setString(2,tickid);	//序號
	     		pstmt.setInt(3,0);
	     		pstmt.setInt(4,price);		//價格
	     		pstmt.setInt(5,0);			//購買狀態(0正常	1退訂)
	     		System.out.println("團購劵編號:" + tickets_type_no);
	     		System.out.println(tickid + "已經被購買");
	     		pstmt.executeUpdate();
			}
			
			//團購券總類ticket_type 剩餘數量 扣掉
			TicketTypeJDBCDAO tickettypedao = new TicketTypeJDBCDAO();
			//扣除團購劵剩餘數量				團購劵種類編號	 / 扣除數量			 /連線		
			tickettypedao.deductedtime(tickets_type_no, qu, con);

			
			//需要做的數量 qu
//			String tickid = null;
			//先取得尚未賣出的序號
//			TicketNoJDBCDAO ticketno = new TicketNoJDBCDAO();
//			List<TicketNoVO> volist = ticketno.getAllstate(tickets_type_no, 0);//查詢目前尚未賣出的團購劵序號  (0未賣出 / 1尚未使用(已賣出) / 2已使用 / 3失效)
//			for (int i = 0 ; i<volist.size();i++){
//				if (i==1){
//					tickid = volist.get(i).getTickets_no();	
//					System.out.println("本次查詢到的" + tickid);
//				}
//			}
			//在把此序號狀態改成1尚未使用(已賣出)
//			ticketno.getbuy(tickid,con);
//     		pstmt = con.prepareStatement(INSERT_STMT2);
     		
//     		pstmt.setInt(1,next_deptno);//訂單編號
//     		pstmt.setString(2,tickid);	//序號
//     		pstmt.setInt(3,0);
//     		pstmt.setInt(4,price);		//價格
//     		pstmt.setInt(5,0);			//購買狀態(0正常	1退訂)
//     		System.out.println(tickid + "已經被購買");
     		//新增訂單的作業流程 (多個)
     		//新增團購券訂單明細
     		//傳入  團購券種類編號 /此訂單編號  / 折價後的單品價格/連線
     		//(tickets_type_no,next_deptno,price,con)
     		
/*
 * 訂單編號(order_no)
 * 團購券序號(tickets_no String)
 * 購買單價(order_value)
 * 購買狀態(order_status)
 * */
//			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-OrderDeJDBCDAO");
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
	
	
}
