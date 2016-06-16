package com.article.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.friend.model.FriendVO;

public class ArticleJNDIDAO implements ArticleDAO_interface {

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
	private static final String INSERT_STMT = "INSERT INTO article (article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click,article_replies) VALUES (article_article_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click,article_replies FROM article order by article_click desc";
	private static final String GET_ONE_STMT = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click,article_replies FROM article where article_no = ?";
	private static final String DELETE = "DELETE FROM article where article_no = ?";
	private static final String UPDATE = "UPDATE article set store_no=?, mem_no=?,store_name=?, article_content=?, article_title=?, article_create=?, article_modify=? ,article_status=?, article_score=?, article_click=?,article_replies=? where article_no = ?";
	private static final String GET_SOME_STMT = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click,article_replies FROM article where mem_no = ? order by article_create desc";
	private static final String GET_SOME_STMT_SOTRE = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click,article_replies FROM article where store_no = ? order by article_create desc";
	private static final String GET_LATEST_STMT ="SELECT MAX(article_create) FROM article where mem_no = ?";
	private static final String UPDATE_CLICK ="UPDATE article set article_click=? where article_no = ?";
	private static final String UPDATE_REPLIES = "UPDATE article set article_replies=? where article_no = ?";
	
	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getStore_no());
			pstmt.setInt(2, articleVO.getMem_no());
			pstmt.setString(3, articleVO.getStore_name());
			pstmt.setString(4, articleVO.getArticle_content());
			pstmt.setString(5, articleVO.getArticle_title());
			pstmt.setTimestamp(6, articleVO.getArticle_create());
			pstmt.setTimestamp(7, articleVO.getArticle_modify());
			pstmt.setString(8, articleVO.getArticle_status());
			pstmt.setInt(9, articleVO.getArticle_score());
			pstmt.setInt(10, articleVO.getArticle_click());
			pstmt.setInt(11, articleVO.getArticle_replies());

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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleVO.getStore_no());
			pstmt.setInt(2, articleVO.getMem_no());
			pstmt.setString(3, articleVO.getStore_name());
			
			System.out.println(articleVO.getArticle_content()+"...");
			
			pstmt.setString(4, articleVO.getArticle_content());
			pstmt.setString(5, articleVO.getArticle_title());
			pstmt.setTimestamp(6, articleVO.getArticle_create());
			pstmt.setTimestamp(7, articleVO.getArticle_modify());
			pstmt.setString(8, articleVO.getArticle_status());
			pstmt.setInt(9, articleVO.getArticle_score());
			
			pstmt.setInt(10, articleVO.getArticle_click());
			pstmt.setInt(11, articleVO.getArticle_replies());
			pstmt.setInt(12, articleVO.getArticle_no());
			
			System.out.println(articleVO.getStore_no());
			System.out.println(articleVO.getMem_no());
			System.out.println(articleVO.getStore_name());
			System.out.println(articleVO.getArticle_content());
			System.out.println(articleVO.getArticle_title());
			System.out.println(articleVO.getArticle_create());
			System.out.println(articleVO.getArticle_modify());
			System.out.println(articleVO.getArticle_status());
			System.out.println(articleVO.getArticle_score());
			System.out.println(articleVO.getArticle_click());
			System.out.println(articleVO.getArticle_replies());
			System.out.println(articleVO.getArticle_no());
			
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
	public void delete(Integer article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

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
	public ArticleVO findByPrimaryKey(Integer article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_no"));
				articleVO.setStore_no(rs.getInt("store_no"));
				articleVO.setMem_no(rs.getInt("mem_no"));
				articleVO.setStore_name(rs.getString("store_name"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_create(rs.getTimestamp("article_create"));
				articleVO.setArticle_modify(rs.getTimestamp("article_modify"));
				articleVO.setArticle_status(rs.getString("article_status"));
				articleVO.setArticle_score(rs.getInt("article_score"));
				articleVO.setArticle_click(rs.getInt("article_click"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));

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
		return articleVO;
	}
	
	@Override
	public List<ArticleVO> ListArticleByStore_no(Integer store_no) {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT_SOTRE);

			pstmt.setInt(1, store_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_no"));
				articleVO.setStore_no(rs.getInt("store_no"));
				articleVO.setMem_no(rs.getInt("mem_no"));
				articleVO.setStore_name(rs.getString("store_name"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_create(rs.getTimestamp("article_create"));
				articleVO.setArticle_modify(rs.getTimestamp("article_modify"));
				articleVO.setArticle_status(rs.getString("article_status"));
				articleVO.setArticle_score(rs.getInt("article_score"));
				articleVO.setArticle_click(rs.getInt("article_click"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));

				list.add(articleVO); // Store the row in the list
				
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
	public List<ArticleVO> findByPrimaryKey2(Integer mem_no) {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_no"));
				articleVO.setStore_no(rs.getInt("store_no"));
				articleVO.setMem_no(rs.getInt("mem_no"));
				articleVO.setStore_name(rs.getString("store_name"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_create(rs.getTimestamp("article_create"));
				articleVO.setArticle_modify(rs.getTimestamp("article_modify"));
				articleVO.setArticle_status(rs.getString("article_status"));
				articleVO.setArticle_score(rs.getInt("article_score"));
				articleVO.setArticle_click(rs.getInt("article_click"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));

				list.add(articleVO); // Store the row in the list
				
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
	public ArticleVO findByPrimaryKey3(Integer article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LATEST_STMT);

			pstmt.setInt(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_create(rs.getTimestamp("MAX(article_create)"));

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
		return articleVO;
	}
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				articleVO = new ArticleVO();
				articleVO.setArticle_no(rs.getInt("article_no"));
				articleVO.setStore_no(rs.getInt("store_no"));
				articleVO.setMem_no(rs.getInt("mem_no"));
				articleVO.setStore_name(rs.getString("store_name"));
				articleVO.setArticle_content(rs.getString("article_content"));
				articleVO.setArticle_title(rs.getString("article_title"));
				articleVO.setArticle_create(rs.getTimestamp("article_create"));
				articleVO.setArticle_modify(rs.getTimestamp("article_modify"));
				articleVO.setArticle_status(rs.getString("article_status"));
				articleVO.setArticle_score(rs.getInt("article_score"));
				articleVO.setArticle_click(rs.getInt("article_click"));
				articleVO.setArticle_replies(rs.getInt("article_replies"));
				list.add(articleVO); // Store the row in the list
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
	public void updateArticleClick(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_CLICK);

			pstmt.setInt(1, articleVO.getArticle_click());
			pstmt.setInt(2, articleVO.getArticle_no());
			

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
	public void updateArticleRepliesNum(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_REPLIES);

			pstmt.setInt(1, articleVO.getArticle_replies());
			pstmt.setInt(2, articleVO.getArticle_no());
			

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


}
