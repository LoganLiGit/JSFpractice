package com.Adm.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdmJNDIDAO implements AdmDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO administrator (admin_no,admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address) VALUES (administrator_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT admin_no,admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address FROM administrator order by admin_no";
		private static final String GET_ONE_STMT = 
			"SELECT admin_no,admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address FROM administrator where admin_no = ?";
		private static final String DELETE = 
			"DELETE FROM administrator where admin_no = ?";
		private static final String UPDATE = 
			"UPDATE administrator set admin_account=?,  admin_password=?,  admin_name=?, admin_email=?,  admin_phone=?,  admin_address=? where admin_no = ?";
		private static final String GET_ONE_ACCOUNT = 
				"SELECT admin_no,admin_account,admin_password,admin_name,admin_email,admin_phone,admin_address FROM administrator where admin_account = ?";
	@Override
	public void insert(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getAdmin_account());
			pstmt.setString(2, admVO.getAdmin_password());
			pstmt.setString(3, admVO.getAdmin_name());
			pstmt.setString(4, admVO.getAdmin_email());
			pstmt.setString(5, admVO.getAdmin_phone());
			pstmt.setString(6, admVO.getAdmin_address());

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
	public void update(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getAdmin_account());
			pstmt.setString(2, admVO.getAdmin_password());
			pstmt.setString(3, admVO.getAdmin_name());
			pstmt.setString(4, admVO.getAdmin_email());
			pstmt.setString(5, admVO.getAdmin_phone());
			pstmt.setString(6, admVO.getAdmin_address());
			pstmt.setInt(7, admVO.getAdmin_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer admin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admin_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public AdmVO findByPrimaryKey(Integer admin_no) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admin_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// AdmVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmin_no(rs.getInt("admin_no"));
				admVO.setAdmin_account(rs.getString("admin_account"));
				admVO.setAdmin_password(rs.getString("admin_password"));
				admVO.setAdmin_name(rs.getString("admin_name"));
				admVO.setAdmin_email(rs.getString("admin_email"));
				admVO.setAdmin_phone(rs.getString("admin_phone"));
				admVO.setAdmin_address(rs.getString("admin_address"));
			}

			// Handle any driver errors
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
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// admVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmin_no(rs.getInt("admin_no"));
				admVO.setAdmin_account(rs.getString("admin_account"));
				admVO.setAdmin_password(rs.getString("admin_password"));
				admVO.setAdmin_name(rs.getString("admin_name"));
				admVO.setAdmin_email(rs.getString("admin_email"));
				admVO.setAdmin_phone(rs.getString("admin_phone"));
				admVO.setAdmin_address(rs.getString("admin_address"));
				list.add(admVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public AdmVO findByAccount(String admin_account) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, admin_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// admVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmin_no(rs.getInt("admin_no"));
				admVO.setAdmin_account(rs.getString("admin_account"));
				admVO.setAdmin_password(rs.getString("admin_password"));
				admVO.setAdmin_name(rs.getString("admin_name"));
				admVO.setAdmin_email(rs.getString("admin_email"));
				admVO.setAdmin_phone(rs.getString("admin_phone"));
				admVO.setAdmin_address(rs.getString("admin_address"));
			}

			// Handle any driver errors
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
		return admVO;
	}
}