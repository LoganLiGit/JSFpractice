package com.pocket.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PocketJNDIDAO implements PocketDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ZA105G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO pocket (pl_no,mem_no,store_no,keep_time) VALUES (pocket_pl_no.NEXTVAL, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT pl_no,mem_no,store_no,keep_time FROM pocket order by pl_no";
	private static final String GET_ONE_STMT = 
		"SELECT pl_no,mem_no,store_no,keep_time FROM pocket where pl_no = ?";
	private static final String DELETE = 
		"DELETE FROM pocket where pl_no = ?";
	private static final String UPDATE = 
		"UPDATE pocket set mem_no=?, store_no=? where pl_no = ?, keep_time=?";
	private static final String GET_POCKET_STMT=
		"SELECT pl_no,mem_no,store_no,keep_time FROM pocket where mem_no =? order by keep_time desc";
	
	public void insert(PocketVO pocketVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, pocketVO.getMem_no());
			pstmt.setInt(2, pocketVO.getStore_no());
			pstmt.setTimestamp(3, pocketVO.getKeep_time());
			
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
	public void update(PocketVO pocketVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, pocketVO.getMem_no());
			pstmt.setInt(2, pocketVO.getStore_no());
			pstmt.setInt(3, pocketVO.getPl_no());
			pstmt.setTimestamp(4, pocketVO.getKeep_time());

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
	public void delete(Integer pl_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pl_no);

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
	public PocketVO findByPrimaryKey(Integer pl_no) {

		PocketVO pocketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pl_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				pocketVO = new PocketVO();
				pocketVO.setPl_no(rs.getInt("pl_no"));
				pocketVO.setMem_no(rs.getInt("mem_no"));
				pocketVO.setStore_no(rs.getInt("store_no"));
				pocketVO.setKeep_time(rs.getTimestamp("keep_time"));
				
			}

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
		return pocketVO;
	}

	@Override
	public List<PocketVO> getAll() {
		List<PocketVO> list = new ArrayList<PocketVO>();
		PocketVO pocketVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				pocketVO = new PocketVO();
				pocketVO.setPl_no(rs.getInt("pl_no"));
				pocketVO.setMem_no(rs.getInt("mem_no"));
				pocketVO.setStore_no(rs.getInt("store_no"));
				pocketVO.setKeep_time(rs.getTimestamp("keep_time"));
				
				list.add(pocketVO); // Store the row in the list
			}

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
	
	@Override
	public List<PocketVO> getKeepStores(Integer mem_no) {
		List<PocketVO> list = new ArrayList<PocketVO>();
		PocketVO pocketVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_POCKET_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				pocketVO = new PocketVO();
				pocketVO.setPl_no(rs.getInt("pl_no"));
				pocketVO.setMem_no(rs.getInt("mem_no"));
				pocketVO.setStore_no(rs.getInt("store_no"));
				pocketVO.setKeep_time(rs.getTimestamp("keep_time"));
				
				list.add(pocketVO); // Store the row in the list
			}

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
	
}
