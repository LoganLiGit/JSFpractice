package com.store.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StoreDAO implements StoreDAO_interface {
	// 嚙瑾嚙踝蕭嚙踝蕭嚙諄程嚙踝蕭嚙踝蕭,嚙緩嚙踝蕭@嚙諉賂蕭w ,嚙瑾嚙諄一嚙踝蕭DataSource嚙磐嚙箠
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ZA105G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO STORE (store_no,store_account,store_password,store_name,store_regist_date,manager_name) VALUES (STORE_NO.NEXTVAL, ?, ?, ?, ?, ?)";
//	private static final String INSERT_STMT = "INSERT INTO STORE (store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude) VALUES (STORE_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store order by store_no";
	private static final String GET_ONE_STMT = "SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store where store_no = ?";
	private static final String DELETE = "DELETE FROM store where store_no = ?";
//	private static final String UPDATE = "UPDATE store set store_account=?,store_password=?,store_state=?,store_name=?,store_regist_date=?,store_zipcode=?,store_city=?,store_district=?,store_address=?,store_phone=?,store_type=?,store_score=?,store_balance=?,store_cell_registcode=?,store_violation=?,manager_name=?,manager_gender=?,manager_email=?,manager_id=?,manager_cellphone=?,manager_credit_num=?,manager_credit_expyear=?,manager_credit_expmonth=?,manager_credit_secure_num=?,tickts_limits=?,store_longitude=?,store_latitude=? where store_no = ?";
	private static final String UPDATE = "UPDATE store set store_account=?,store_password=?,store_name=?,store_regist_date=?,manager_name=? where store_no = ?";
	private static final String GET_STORENO_STMT = "SELECT store_no FROM store where store_name = ?";
	private static final String GET_SEARCHED_BY_STORENAME_STMT ="SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store where store_name like ?";
	private static final String GET_SEARCHED_BY_STOREADDRESS_STMT ="SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store where store_address like ?";
	private static final String GET_SEARCHED_BY_FOODTYPE_STMT ="SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store where store_type like ?";
	private static final String GET_ONE_ACCOUNT = "SELECT store_no,store_account,store_password,store_state,store_name,store_regist_date,store_zipcode,store_city,store_district,store_address,store_phone,store_type,store_score,store_balance,store_cell_registcode,store_violation,manager_name,manager_gender,manager_email,manager_id,manager_cellphone,manager_credit_num,manager_credit_expyear,manager_credit_expmonth,manager_credit_secure_num,tickts_limits,store_longitude,store_latitude FROM store where store_account = ?";
	@Override
	public void insert(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);


//			pstmt.setString(1, storeVO.getStore_account());
//			pstmt.setString(2, storeVO.getStore_password());
//			pstmt.setInt(3, storeVO.getStore_state());
//			pstmt.setString(4, storeVO.getStore_name());
//			pstmt.setDate(5, storeVO.getStore_regist_date());
//			pstmt.setString(6, storeVO.getStore_zipcode());
//			pstmt.setString(7, storeVO.getStore_city());
//			pstmt.setString(8, storeVO.getStore_district());
//			pstmt.setString(9, storeVO.getStore_address());
//			pstmt.setString(10, storeVO.getStore_phone());
//			pstmt.setString(11, storeVO.getStore_type());
//			pstmt.setInt(12, storeVO.getStore_score());
//			pstmt.setInt(13, storeVO.getStore_balance());
//			pstmt.setString(14, storeVO.getStore_cell_registcode());
//			pstmt.setInt(15, storeVO.getStore_violation());
//			pstmt.setString(16, storeVO.getManager_name());
//			pstmt.setInt(17, storeVO.getManager_gender());
//			pstmt.setString(18, storeVO.getManager_email());
//			pstmt.setString(19, storeVO.getManager_id());
//			pstmt.setString(20, storeVO.getManager_cellphone());
//			pstmt.setInt(21, storeVO.getManager_credit_num());
//			pstmt.setInt(22, storeVO.getManager_credit_expyear());
//			pstmt.setInt(23, storeVO.getManager_credit_expmonth());
//			pstmt.setInt(24, storeVO.getManager_credit_secure_num());
//			pstmt.setInt(25, storeVO.getTickts_limits());
//			pstmt.setDouble(26, storeVO.getStore_longitude());
//			pstmt.setDouble(27, storeVO.getStore_latitude());
			pstmt.setString(1, storeVO.getStore_account());
			pstmt.setString(2, storeVO.getStore_password());
			
			pstmt.setString(3, storeVO.getStore_name());
			pstmt.setDate(4, storeVO.getStore_regist_date());
		
			pstmt.setString(5, storeVO.getManager_name());
		
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
	public void update(StoreVO storeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setString(1, storeVO.getStore_account());
//			pstmt.setString(2, storeVO.getStore_password());
//			pstmt.setInt(3, storeVO.getStore_state());
//			pstmt.setString(4, storeVO.getStore_name());
//			pstmt.setDate(5, storeVO.getStore_regist_date());
//			pstmt.setString(6, storeVO.getStore_zipcode());
//			pstmt.setString(7, storeVO.getStore_city());
//			pstmt.setString(8, storeVO.getStore_district());
//			pstmt.setString(9, storeVO.getStore_address());
//			pstmt.setString(10, storeVO.getStore_phone());
//			pstmt.setString(11, storeVO.getStore_type());
//			pstmt.setInt(12, storeVO.getStore_score());
//			pstmt.setInt(13, storeVO.getStore_balance());
//			pstmt.setString(14, storeVO.getStore_cell_registcode());
//			pstmt.setInt(15, storeVO.getStore_violation());
//			pstmt.setString(16, storeVO.getManager_name());
//			pstmt.setInt(17, storeVO.getManager_gender());
//			pstmt.setString(18, storeVO.getManager_email());
//			pstmt.setString(19, storeVO.getManager_id());
//			pstmt.setString(20, storeVO.getManager_cellphone());
//			pstmt.setInt(21, storeVO.getManager_credit_num());
//			pstmt.setInt(22, storeVO.getManager_credit_expyear());
//			pstmt.setInt(23, storeVO.getManager_credit_expmonth());
//			pstmt.setInt(24, storeVO.getManager_credit_secure_num());
//			pstmt.setInt(25, storeVO.getTickts_limits());
//			pstmt.setDouble(26, storeVO.getStore_longitude());
//			pstmt.setDouble(27, storeVO.getStore_latitude());
//			pstmt.setInt(28, storeVO.getStore_no());
			
			pstmt.setString(1, storeVO.getStore_account());
			pstmt.setString(2, storeVO.getStore_password());
			
			pstmt.setString(3, storeVO.getStore_name());
			pstmt.setDate(4, storeVO.getStore_regist_date());
			pstmt.setString(5, storeVO.getManager_name());
			pstmt.setInt(6, storeVO.getStore_no());
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
	public void delete(Integer store_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, store_no);

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
	public StoreVO findByPrimaryKey(Integer store_no) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, store_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));

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
		return storeVO;
	}
	
	@Override
	public StoreVO findByStoreName(String store_name) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STORENO_STMT);

			System.out.println("DAOstore_name:"+store_name);
			
			pstmt.setString(1, store_name);

			rs = pstmt.executeQuery();

			System.out.println("DAOstore_name2:"+store_name);
			
			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				System.out.println("DAOstore_name3:"+rs.getInt("store_no"));
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
		System.out.println("DAO:"+storeVO.equals(null));
		return storeVO;
	}

	@Override
	public List<StoreVO> getAll() {
		// TODO Auto-generated method stub

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));
				list.add(storeVO); // Store the row in the list
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
	public List<StoreVO> getSearchedByStoreName(String store_key) {
		// TODO Auto-generated method stub

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCHED_BY_STORENAME_STMT);
			pstmt.setString(1, "%"+store_key+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));
				list.add(storeVO); // Store the row in the list
				
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
		System.out.println("list:"+list.size());
		return list;
	}
	
	@Override
	public List<StoreVO> getSearchedByStoreAddress(String store_key) {
		// TODO Auto-generated method stub

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCHED_BY_STOREADDRESS_STMT);
			pstmt.setString(1, "%"+store_key+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));
				list.add(storeVO); // Store the row in the list
				
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
		System.out.println("list:"+list.size());
		return list;
	}
	
	@Override
	public List<StoreVO> getSearchedByFoodType(String store_key) {
		// TODO Auto-generated method stub

		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCHED_BY_FOODTYPE_STMT);
			pstmt.setString(1, "%"+store_key+"%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));
				list.add(storeVO); // Store the row in the list
				
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
		System.out.println("list:"+list.size());
		return list;
	}
	
	public void storecheckmoney(Integer store_no,Integer money,java.sql.Connection con){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer money_old = findByPrimaryKey(store_no).getStore_balance();
		Integer money_new = money + money_old;
		String sqlstorecheckmoney = "UPDATE store set store_balance=" + money_new + " where store_no = " + store_no ;
		//System.out.println(sqlstorecheckmoney);
		try {		
			pstmt = con.prepareStatement(sqlstorecheckmoney);	
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			if (con != null) {
				try {
					// 3嚙踝蕭�頨急謍梱�蕭�嚙踝蕭��ception嚙踐赤嚙踝蕭賹蕭蹇蕭�atch嚙踝蕭����
					System.err.print("Transaction is being �蝞賂蕭���蕭嚙踝��嚙�: ");
					System.err.println("��蕭 rolled back-嚙踐�� TicketTypeJDBCDAO 嚙踐赤嚙踝蕭嚙�");
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
	@Override
	public List<StoreVO> getAll2(Map<String, String[]> map) {
		List<StoreVO> list = new ArrayList<StoreVO>();
		StoreVO storeVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from store "
		          + com.store.model.CompositeQuery.jdbcUtil_CompositeQuery_Store.get_WhereCondition(map)
		          + "order by store_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("������finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));
				list.add(storeVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public StoreVO findByStoreAccount(String store_account) {
		// TODO Auto-generated method stub
		StoreVO storeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, store_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 銋迂� Domain objects
				storeVO = new StoreVO();
				storeVO.setStore_no(rs.getInt("store_no"));
				storeVO.setStore_account(rs.getString("store_account"));
				storeVO.setStore_password(rs.getString("store_password"));
				storeVO.setStore_state(rs.getInt("store_state"));
				storeVO.setStore_name(rs.getString("store_name"));
				storeVO.setStore_regist_date(rs.getDate("store_regist_date"));
				storeVO.setStore_zipcode(rs.getString("store_zipcode"));
				storeVO.setStore_city(rs.getString("store_city"));
				storeVO.setStore_district(rs.getString("store_district"));
				storeVO.setStore_address(rs.getString("store_address"));
				storeVO.setStore_phone(rs.getString("store_phone"));
				storeVO.setStore_type(rs.getString("store_type"));
				storeVO.setStore_score(rs.getDouble("store_score"));
				storeVO.setStore_balance(rs.getInt("store_balance"));
				storeVO.setStore_cell_registcode(rs
						.getString("store_cell_registcode"));
				storeVO.setStore_violation(rs.getInt("store_violation"));
				storeVO.setManager_name(rs.getString("manager_name"));
				storeVO.setManager_gender(rs.getInt("manager_gender"));
				storeVO.setManager_email(rs.getString("manager_email"));
				storeVO.setManager_id(rs.getString("manager_id"));
				storeVO.setManager_cellphone(rs.getString("manager_cellphone"));
				storeVO.setManager_credit_num(rs.getInt("manager_credit_num"));
				storeVO.setManager_credit_expyear(rs
						.getInt("manager_credit_expyear"));
				storeVO.setManager_credit_expmonth(rs
						.getInt("manager_credit_expmonth"));
				storeVO.setManager_credit_secure_num(rs
						.getInt("manager_credit_secure_num"));
				storeVO.setTickts_limits(rs.getInt("tickts_limits"));
				storeVO.setStore_longitude(rs.getDouble("store_longitude"));
				storeVO.setStore_latitude(rs.getDouble("store_latitude"));

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
		return storeVO;
	}
}