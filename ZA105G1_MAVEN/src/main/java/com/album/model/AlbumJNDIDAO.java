package com.album.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.article.model.ArticleVO;
import com.friend.model.FriendVO;

public class AlbumJNDIDAO implements AlbumDAO_interface {

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
	private static final String INSERT_STMT = "INSERT INTO album (photo_no, mem_no,mem_photo,photo_title,photo_description) VALUES (photo_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT photo_no,mem_no,mem_photo,photo_title,photo_description FROM album where mem_no=?";
	//private static final String GET_ONE_STMT = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click FROM article where article_no = ?";
	private static final String DELETE = "DELETE FROM album where photo_no = ?";
	//private static final String UPDATE = "UPDATE article set store_no=?, mem_no=?,store_name=?, article_content=?, article_title=?, article_create=?, article_modify=? ,article_status=?, article_score=?, article_click=? where article_no = ?";
	//private static final String GET_SOME_STMT = "SELECT article_no,store_no,mem_no,store_name,article_content,article_title,article_create,article_modify,article_status,article_score,article_click FROM article where mem_no = ? order by article_create desc";
	//private static final String GET_LATEST_STMT ="SELECT MAX(article_create) FROM article where mem_no = ?";
	//private static final String UPDATE_CLICK ="UPDATE article set article_click=? where article_no = ?";
	
	@Override
	public void insert(AlbumVO albumVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, albumVO.getMem_no());
			pstmt.setBytes(2, albumVO.getMem_photo());
			pstmt.setString(3, albumVO.getPhoto_title());
			pstmt.setString(4, albumVO.getPhoto_description());
			

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

//	@Override
//	public void update(ArticleVO articleVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, articleVO.getStore_no());
//			pstmt.setInt(2, articleVO.getMem_no());
//			pstmt.setString(3, articleVO.getStore_name());
//			pstmt.setString(4, articleVO.getArticle_content());
//			pstmt.setString(5, articleVO.getArticle_title());
//			pstmt.setTimestamp(6, articleVO.getArticle_create());
//			pstmt.setTimestamp(7, articleVO.getArticle_modify());
//			pstmt.setString(8, articleVO.getArticle_status());
//			pstmt.setInt(9, articleVO.getArticle_score());
//			pstmt.setInt(10, articleVO.getArticle_no());
//			pstmt.setInt(11, articleVO.getArticle_click());
//			
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void delete(Integer photo_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, photo_no);

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
	public List<AlbumVO> getAll(Integer mem_no) {
		List<AlbumVO> list = new ArrayList<AlbumVO>();
		AlbumVO albumVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				albumVO = new AlbumVO();
				albumVO.setPhoto_no(rs.getInt("photo_no"));
				albumVO.setMem_no(rs.getInt("mem_no"));
				albumVO.setMem_photo(rs.getBytes("mem_photo"));
				albumVO.setPhoto_title(rs.getString("photo_title"));
				albumVO.setPhoto_description(rs.getString("photo_description"));
				
				list.add(albumVO); // Store the row in the list
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
