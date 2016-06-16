package com.reply.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReplyJNDIDAO implements ReplyDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
		"INSERT INTO reply (reply_no,mem_no,article_no,reply_msg,reply_time) VALUES (reply_reply_no.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT reply_no,mem_no,article_no,reply_msg,reply_time FROM reply order by reply_no";
	private static final String GET_ONE_STMT = 
		"SELECT reply_no,mem_no,article_no,reply_msg,reply_time FROM reply where reply_no = ?";
	private static final String GET_SOME_STMT =
		"SELECT reply_no,mem_no,article_no,reply_msg,reply_time FROM reply where article_no = ? order by reply_no";
	private static final String DELETE = 
		"DELETE FROM reply where reply_no = ?";
	private static final String DELETE_BY_ARTICLE = "DELETE FROM reply where article_no = ?";
	private static final String UPDATE = 
		"UPDATE reply set mem_no=?, article_no=?, reply_msg=?, reply_time=? where reply_no = ?";
	private static final String UPDATE2 = 
			"UPDATE reply set reply_msg=?where reply_no = ?";
	
	@Override
	public void insert(ReplyVO replyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, replyVO.getMem_no());
			pstmt.setInt(2, replyVO.getArticle_no());
			pstmt.setString(3, replyVO.getReply_msg());
			pstmt.setTimestamp(4, replyVO.getReply_time());
			
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
	public List<ReplyVO> insert2(ReplyVO replyVO, Integer article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, replyVO.getMem_no());
			pstmt.setInt(2, replyVO.getArticle_no());
			pstmt.setString(3, replyVO.getReply_msg());
			pstmt.setTimestamp(4, replyVO.getReply_time());
			
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
		
		ResultSet rs = null;
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				replyVO = new ReplyVO();
				replyVO.setReply_no(rs.getInt("reply_no"));
				replyVO.setMem_no(rs.getInt("mem_no"));
				replyVO.setArticle_no(rs.getInt("article_no"));
				replyVO.setReply_msg(rs.getString("reply_msg"));
				replyVO.setReply_time(rs.getTimestamp("reply_time"));
				list.add(replyVO); // Store the row in the list
				
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
	public void update(ReplyVO replyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, replyVO.getMem_no());
			pstmt.setInt(2, replyVO.getArticle_no());
			pstmt.setString(3, replyVO.getReply_msg());
			pstmt.setTimestamp(4, replyVO.getReply_time());
			pstmt.setInt(5, replyVO.getReply_no());

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
	public void update2(ReplyVO replyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			pstmt.setString(1, replyVO.getReply_msg());
			pstmt.setInt(2, replyVO.getReply_no());

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
	public void delete(Integer reply_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reply_no);

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
	public void deleteReplyByArticle(Integer article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_BY_ARTICLE);

			pstmt.setInt(1, article_no);

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
	public List<ReplyVO> delete2(Integer reply_no, Integer article_no) {//�Y�g���O�d���R����d��

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reply_no);

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
		
		ResultSet rs = null;
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				replyVO = new ReplyVO();
				replyVO.setReply_no(rs.getInt("reply_no"));
				replyVO.setMem_no(rs.getInt("mem_no"));
				replyVO.setArticle_no(rs.getInt("article_no"));
				replyVO.setReply_msg(rs.getString("reply_msg"));
				replyVO.setReply_time(rs.getTimestamp("reply_time"));
				list.add(replyVO); // Store the row in the list
				
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
	public ReplyVO findByPrimaryKey(Integer reply_no) {

		ReplyVO replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reply_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				replyVO = new ReplyVO();
				replyVO.setReply_no(rs.getInt("reply_no"));
				replyVO.setMem_no(rs.getInt("mem_no"));
				replyVO.setArticle_no(rs.getInt("article_no"));
				replyVO.setReply_msg(rs.getString("reply_msg"));
				replyVO.setReply_time(rs.getTimestamp("reply_time"));
				
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
		return replyVO;
	}
	
	@Override
	public List<ReplyVO> findByPrimaryKey2(Integer article_no) {

		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				replyVO = new ReplyVO();
				replyVO.setReply_no(rs.getInt("reply_no"));
				replyVO.setMem_no(rs.getInt("mem_no"));
				replyVO.setArticle_no(rs.getInt("article_no"));
				replyVO.setReply_msg(rs.getString("reply_msg"));
				replyVO.setReply_time(rs.getTimestamp("reply_time"));
				list.add(replyVO); // Store the row in the list
				
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
	public List<ReplyVO> getAll() {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		ReplyVO replyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				replyVO = new ReplyVO();
				replyVO.setReply_no(rs.getInt("reply_no"));
				replyVO.setMem_no(rs.getInt("mem_no"));
				replyVO.setArticle_no(rs.getInt("article_no"));
				replyVO.setReply_msg(rs.getString("reply_msg"));
				replyVO.setReply_time(rs.getTimestamp("reply_time"));
				list.add(replyVO); // Store the row in the list
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
