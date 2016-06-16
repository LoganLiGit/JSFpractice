package com.Adm.model;

import java.util.*;
import java.sql.*;

public class AdmJDBCDAO implements AdmDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ZA105G1";
	String passwd = "ZA105G1";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getAdmin_account());
			pstmt.setString(2, admVO.getAdmin_password());
			pstmt.setString(3, admVO.getAdmin_name());
			pstmt.setString(4, admVO.getAdmin_email());
			pstmt.setString(5, admVO.getAdmin_phone());
			pstmt.setString(6, admVO.getAdmin_address());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}

	@Override
	public void update(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	}

	@Override
	public void delete(Integer admin_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admin_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}

	@Override
	public AdmVO findByPrimaryKey(Integer admin_no) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public AdmVO findByAccount(String admin_account) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			
			con = DriverManager.getConnection(url, userid, passwd);
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
	

	public static void main(String[] args) {

		AdmJDBCDAO dao = new AdmJDBCDAO();

		// 新增
		AdmVO admVO1 = new AdmVO();
		admVO1.setAdmin_account("aa");
		admVO1.setAdmin_password("bb");
		admVO1.setAdmin_name("石門山下智久");
		admVO1.setAdmin_email("h23232321@hotmail.com");
		admVO1.setAdmin_phone("020433989");
		admVO1.setAdmin_address("火星鄉石頭路37號");
		dao.insert(admVO1);

//		// 修改
//		AdmVO admVO2 = new AdmVO();
//		admVO2.setAdmin_no(6);
//		admVO2.setAdmin_account("Q538283956");
//		admVO2.setAdmin_password("ty53231567");
//		admVO2.setAdmin_name("板橋王大陸");
//		admVO2.setAdmin_email("h2662343@gmail.com");
//		admVO2.setAdmin_phone("0903874293");
//		admVO2.setAdmin_address("水星水路55號");
//		dao.update(admVO2);

//		// 刪除
//		dao.delete(4);
//
//		// 查詢
//		AdmVO admVO3 = dao.findByPrimaryKey(6);
//		System.out.print(admVO3.getAdmin_no() + ",");
//		System.out.print(admVO3.getAdmin_account() + ",");
//		System.out.print(admVO3.getAdmin_password() + ",");
//		System.out.print(admVO3.getAdmin_name() + ",");
//		System.out.print(admVO3.getAdmin_email() + ",");
//		System.out.print(admVO3.getAdmin_phone() + ",");
//		System.out.println(admVO3.getAdmin_address());
//		System.out.println("---------------------");
//
		// 查詢
		List<AdmVO> list = dao.getAll();
		for (AdmVO aEmp : list) {
			System.out.print(aEmp.getAdmin_no() + ",");
			System.out.print(aEmp.getAdmin_account() + ",");
			System.out.print(aEmp.getAdmin_password() + ",");
			System.out.print(aEmp.getAdmin_name() + ",");
			System.out.print(aEmp.getAdmin_email() + ",");
			System.out.print(aEmp.getAdmin_phone() + ",");
			System.out.print(aEmp.getAdmin_address());
			System.out.println();
		}
	}
}