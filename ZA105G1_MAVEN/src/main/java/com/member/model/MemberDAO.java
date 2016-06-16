package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.CashTr.model.CashTrDAO;

public class MemberDAO implements MemberDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO MEMBER_TABLE (MEM_NO,MEM_ACCOUNT,MEM_PASSWORD,MEM_REGIST_DATE,MEM_NAME,MEM_NICKNAME,MEM_BIRTHDAY,MEM_PHOTO,MEM_IDCARD,"
			+ "						   MEM_SEX,MEM_ZIPCODE,MEM_CITY,MEM_DISTRICT,MEM_ADDRESS,MEM_PHONE,MEM_CELLPHONE,MEM_EMAIL,MEM_SKILL,MEM_HOBBY,"
			+ "						   MEM_RELATIONSHIP,MEM_RIGHT,MEM_INTRO,MEM_LEVEL,MEM_STATUS,MEM_REDID,MEM_BALANCE) VALUES (MEMBER_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
		private static final String GET_ALL_STMT = 
			"SELECT MEM_NO,MEM_ACCOUNT,MEM_PASSWORD,to_char(MEM_REGIST_DATE,'yyyy-mm-dd') MEM_REGIST_DATE,MEM_NAME,MEM_NICKNAME,to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY,MEM_PHOTO,MEM_IDCARD,"
			+ "						   MEM_SEX,MEM_ZIPCODE,MEM_CITY,MEM_DISTRICT,MEM_ADDRESS,MEM_PHONE,MEM_CELLPHONE,MEM_EMAIL,MEM_SKILL,MEM_HOBBY,"
			+ "						   MEM_RELATIONSHIP,MEM_RIGHT,MEM_INTRO,MEM_LEVEL,MEM_STATUS,MEM_REDID,MEM_BALANCE FROM MEMBER_TABLE order by MEM_NO";
		private static final String GET_ONE_STMT = 
			"SELECT MEM_NO,MEM_ACCOUNT,MEM_PASSWORD,to_char(MEM_REGIST_DATE,'yyyy-mm-dd') MEM_REGIST_DATE,MEM_NAME,MEM_NICKNAME,to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY,MEM_PHOTO,MEM_IDCARD,"
			+ "						   MEM_SEX,MEM_ZIPCODE,MEM_CITY,MEM_DISTRICT,MEM_ADDRESS,MEM_PHONE,MEM_CELLPHONE,MEM_EMAIL,MEM_SKILL,MEM_HOBBY,"
			+ "						   MEM_RELATIONSHIP,MEM_RIGHT,MEM_INTRO,MEM_LEVEL,MEM_STATUS,MEM_REDID,MEM_BALANCE FROM MEMBER_TABLE where MEM_NO = ?";
		private static final String DELETE = 
			"DELETE FROM MEMBER_TABLE where MEM_NO = ?";
		private static final String UPDATE = 
			"UPDATE MEMBER_TABLE set MEM_ACCOUNT=?,MEM_PASSWORD=?,MEM_REGIST_DATE=?,MEM_NAME=?,MEM_NICKNAME=?,MEM_BIRTHDAY=?,MEM_PHOTO=?,MEM_IDCARD=?,"
			+ "						   MEM_SEX=?,MEM_ZIPCODE=?,MEM_CITY=?,MEM_DISTRICT=?,MEM_ADDRESS=?,MEM_PHONE=?,MEM_CELLPHONE=?,MEM_EMAIL=?,MEM_SKILL=?,MEM_HOBBY=?,"
			+ "						   MEM_RELATIONSHIP=?,MEM_RIGHT=?,MEM_INTRO=?,MEM_LEVEL=?,MEM_STATUS=?,MEM_REDID=?,MEM_BALANCE=?  where MEM_NO = ?";
		private static final String UPDATE2 = 
				"UPDATE MEMBER_TABLE set MEM_PASSWORD=?,MEM_NAME=?,MEM_NICKNAME=?,MEM_PHOTO=?,"
				+ "						   MEM_ZIPCODE=?,MEM_CITY=?,MEM_DISTRICT=?,MEM_ADDRESS=?,MEM_PHONE=?,MEM_CELLPHONE=?,MEM_EMAIL=?,MEM_SKILL=?,MEM_HOBBY=?,"
				+ "						   MEM_RELATIONSHIP=?,MEM_INTRO=?  where MEM_NO = ?";
		private static final String GET_ONE_ACCOUNT = 
				"SELECT MEM_NO,MEM_ACCOUNT,MEM_PASSWORD,to_char(MEM_REGIST_DATE,'yyyy-mm-dd') MEM_REGIST_DATE,MEM_NAME,MEM_NICKNAME,to_char(MEM_BIRTHDAY,'yyyy-mm-dd') MEM_BIRTHDAY,MEM_PHOTO,MEM_IDCARD,"
				+ "						   MEM_SEX,MEM_ZIPCODE,MEM_CITY,MEM_DISTRICT,MEM_ADDRESS,MEM_PHONE,MEM_CELLPHONE,MEM_EMAIL,MEM_SKILL,MEM_HOBBY,"
				+ "						   MEM_RELATIONSHIP,MEM_RIGHT,MEM_INTRO,MEM_LEVEL,MEM_STATUS,MEM_REDID,MEM_BALANCE FROM MEMBER_TABLE where MEM_ACCOUNT = ?";
		private static final String LOGIN = 
				"SELECT MEM_NO, MEM_NAME, MEM_PASSWORD FROM MEMBER_TABLE WHERE MEM_ACCOUNT=?";
			private static final String PERSONAL = 
					"SELECT MEM_NO, MEM_NAME FROM MEMBER_TABLE WHERE MEM_NO=?";
			private static final String GET_ONE_MEMNO = 
					"SELECT MEM_NO FROM MEMBER_TABLE WHERE MEM_NAME=?";
			private static final String UPDATE_ONLINE =
					"UPDATE MEMBER_TABLE set MEM_STATUS=? where MEM_NO = ?";
			private static final String CHANGE_PHOTO_STMT = 
					"UPDATE MEMBER_TABLE set MEM_PHOTO=? where MEM_NO = ?";

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMem_account());
			pstmt.setString(2, memberVO.getMem_password());
			pstmt.setDate(3, memberVO.getMem_regist_date());
			pstmt.setString(4, memberVO.getMem_name());
			pstmt.setString(5, memberVO.getMem_nickname());
			pstmt.setDate(6, memberVO.getMem_birthday());
			pstmt.setBytes(7, memberVO.getMem_photo());
			pstmt.setString(8, memberVO.getMem_idcard());
			pstmt.setString(9, memberVO.getMem_sex());
			pstmt.setString(10, memberVO.getMem_zipcode());
			pstmt.setString(11, memberVO.getMem_city());
			pstmt.setString(12, memberVO.getMem_district());
			pstmt.setString(13, memberVO.getMem_address());
			pstmt.setString(14, memberVO.getMem_phone());
			pstmt.setString(15, memberVO.getMem_cellphone());
			pstmt.setString(16, memberVO.getMem_email());
			pstmt.setString(17, memberVO.getMem_skill());
			pstmt.setString(18, memberVO.getMem_hobby());
			pstmt.setInt(19, memberVO.getMem_relationship());
			pstmt.setInt(20, memberVO.getMem_right());
			pstmt.setString(21, memberVO.getMem_intro());
			pstmt.setInt(22, memberVO.getMem_level());
			pstmt.setInt(23, memberVO.getMem_status());
			pstmt.setString(24, memberVO.getMem_redid());
			pstmt.setInt(25, memberVO.getMem_balance());

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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMem_account());
			pstmt.setString(2, memberVO.getMem_password());
			pstmt.setDate(3, memberVO.getMem_regist_date());
			pstmt.setString(4, memberVO.getMem_name());
			pstmt.setString(5, memberVO.getMem_nickname());
			pstmt.setDate(6, memberVO.getMem_birthday());
			pstmt.setBytes(7, memberVO.getMem_photo());
			pstmt.setString(8, memberVO.getMem_idcard());
			pstmt.setString(9, memberVO.getMem_sex());
			pstmt.setString(10, memberVO.getMem_zipcode());
			pstmt.setString(11, memberVO.getMem_city());
			pstmt.setString(12, memberVO.getMem_district());
			pstmt.setString(13, memberVO.getMem_address());
			pstmt.setString(14, memberVO.getMem_phone());
			pstmt.setString(15, memberVO.getMem_cellphone());
			pstmt.setString(16, memberVO.getMem_email());
			pstmt.setString(17, memberVO.getMem_skill());
			pstmt.setString(18, memberVO.getMem_hobby());
			pstmt.setInt(19, memberVO.getMem_relationship());
			pstmt.setInt(20, memberVO.getMem_right());
			pstmt.setString(21, memberVO.getMem_intro());
			pstmt.setInt(22, memberVO.getMem_level());
			pstmt.setInt(23, memberVO.getMem_status());
			pstmt.setString(24, memberVO.getMem_redid());
			pstmt.setInt(25, memberVO.getMem_balance());
			
			pstmt.setInt(26, memberVO.getMem_no());

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
	public void update2(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE2);

			
			pstmt.setString(1, memberVO.getMem_password());
			
			pstmt.setString(2, memberVO.getMem_name());
			pstmt.setString(3, memberVO.getMem_nickname());
			
			pstmt.setBytes(4, memberVO.getMem_photo());
			
			
			pstmt.setString(5, memberVO.getMem_zipcode());
			pstmt.setString(6, memberVO.getMem_city());
			pstmt.setString(7, memberVO.getMem_district());
			pstmt.setString(8, memberVO.getMem_address());
			pstmt.setString(9, memberVO.getMem_phone());
			pstmt.setString(10, memberVO.getMem_cellphone());
			pstmt.setString(11, memberVO.getMem_email());
			pstmt.setString(12, memberVO.getMem_skill());
			pstmt.setString(13, memberVO.getMem_hobby());
			pstmt.setInt(14, memberVO.getMem_relationship());
			
			pstmt.setString(15, memberVO.getMem_intro());
			
			
			pstmt.setInt(16, memberVO.getMem_no());

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
	public void delete(Integer mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mem_no);

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
	public MemberVO findByPrimaryKey(Integer mem_no) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_account(rs.getString("mem_account"));
				memberVO.setMem_password(rs.getString("mem_password"));
				memberVO.setMem_regist_date(rs.getDate("mem_regist_date"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_idcard(rs.getString("mem_idcard"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_zipcode(rs.getString("mem_zipcode"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_district(rs.getString("mem_district"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_cellphone(rs.getString("mem_cellphone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_skill(rs.getString("mem_skill"));
				memberVO.setMem_hobby(rs.getString("mem_hobby"));
				memberVO.setMem_relationship(rs.getInt("mem_relationship"));
				memberVO.setMem_right(rs.getInt("mem_right"));
				memberVO.setMem_intro(rs.getString("mem_intro"));
				memberVO.setMem_level(rs.getInt("mem_level"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setMem_redid(rs.getString("mem_redid"));
				memberVO.setMem_balance(rs.getInt("mem_balance"));
				
				memberVO.setMem_no(rs.getInt("mem_no"));
				
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_account(rs.getString("mem_account"));
				memberVO.setMem_password(rs.getString("mem_password"));
				memberVO.setMem_regist_date(rs.getDate("mem_regist_date"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_idcard(rs.getString("mem_idcard"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_zipcode(rs.getString("mem_zipcode"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_district(rs.getString("mem_district"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_cellphone(rs.getString("mem_cellphone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_skill(rs.getString("mem_skill"));
				memberVO.setMem_hobby(rs.getString("mem_hobby"));
				memberVO.setMem_relationship(rs.getInt("mem_relationship"));
				memberVO.setMem_right(rs.getInt("mem_right"));
				memberVO.setMem_intro(rs.getString("mem_intro"));
				memberVO.setMem_level(rs.getInt("mem_level"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setMem_redid(rs.getString("mem_redid"));
				memberVO.setMem_balance(rs.getInt("mem_balance"));
				
				list.add(memberVO); // Store the row in the list
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
	public MemberVO findByAccount(String mem_account) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, mem_account);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_account(rs.getString("mem_account"));
				memberVO.setMem_password(rs.getString("mem_password"));
				memberVO.setMem_regist_date(rs.getDate("mem_regist_date"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_nickname(rs.getString("mem_nickname"));
				memberVO.setMem_birthday(rs.getDate("mem_birthday"));
				memberVO.setMem_photo(rs.getBytes("mem_photo"));
				memberVO.setMem_idcard(rs.getString("mem_idcard"));
				memberVO.setMem_sex(rs.getString("mem_sex"));
				memberVO.setMem_zipcode(rs.getString("mem_zipcode"));
				memberVO.setMem_city(rs.getString("mem_city"));
				memberVO.setMem_district(rs.getString("mem_district"));
				memberVO.setMem_address(rs.getString("mem_address"));
				memberVO.setMem_phone(rs.getString("mem_phone"));
				memberVO.setMem_cellphone(rs.getString("mem_cellphone"));
				memberVO.setMem_email(rs.getString("mem_email"));
				memberVO.setMem_skill(rs.getString("mem_skill"));
				memberVO.setMem_hobby(rs.getString("mem_hobby"));
				memberVO.setMem_relationship(rs.getInt("mem_relationship"));
				memberVO.setMem_right(rs.getInt("mem_right"));
				memberVO.setMem_intro(rs.getString("mem_intro"));
				memberVO.setMem_level(rs.getInt("mem_level"));
				memberVO.setMem_status(rs.getInt("mem_status"));
				memberVO.setMem_redid(rs.getString("mem_redid"));
				memberVO.setMem_balance(rs.getInt("mem_balance"));
				
				memberVO.setMem_no(rs.getInt("mem_no"));
				
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
		return memberVO;
	}
	private static final String COMONEY = 
			"UPDATE MEMBER_TABLE set mem_balance =? where MEM_NO = ?";
	
	public void comoney(Integer mem_no,Integer money,java.sql.Connection con){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		try {
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, mem_no);
			rs = pstmt.executeQuery();
			int nowmoney = 0;
			while (rs.next()) {
				// memberVO 也稱為 Domain objects
				memberVO = new MemberVO();
				nowmoney = rs.getInt("mem_balance");
			}
     		pstmt = con.prepareStatement(COMONEY);
     		nowmoney = nowmoney -money;
     		if (nowmoney < 0){
     			con.rollback();
     		}
			pstmt.setInt(1,nowmoney);
			pstmt.setInt(2,mem_no);
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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
	
	//儲值 原本金額不能大於90000000
	public MemberVO addmoney(Integer mem_no,Integer money){
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			memberVO = findByPrimaryKey(mem_no);
			Integer nowmoney = findByPrimaryKey(mem_no).getMem_balance(); //先查詢到目前的金額
			if (nowmoney < 90000000){
				Integer sum_money = 0;
				try{				
					sum_money = nowmoney + money; //原本金額 + 本次增加金額 = 總和金額
					System.out.println(sum_money);
				}catch(Exception e){
					System.out.println("Error1");
				}
				//Integer sum_money = nowmoney + money; //原本金額 + 本次增加金額 = 總和金額
				String addmoneysql ="UPDATE MEMBER_TABLE set MEM_BALANCE=" + sum_money + " where MEM_NO = " + mem_no;
				
				CashTrDAO cashjdbcdao = new CashTrDAO();
				// 1●設定於 pstm.executeUpdate()之前
	    		con.setAutoCommit(false); //關閉交易
				
				pstmt = con.prepareStatement(addmoneysql);
				rs = pstmt.executeQuery();
				Integer trandaction_no = cashjdbcdao.addCashTrdate(mem_no, money, con); //回傳trandaction_no(交易編號)
				con.commit();
				con.setAutoCommit(true); //從開交易					
			}
			else{
				System.out.println("Error2");
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
		return memberVO;
	}
	private static final String UPDATE3 = 
			"UPDATE MEMBER_TABLE set MEM_STATUS=?  where MEM_NO = ?";
	
	
	@Override
	public void update3(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE3);

			
			pstmt.setInt(1, memberVO.getMem_status());
			
			pstmt.setInt(2, memberVO.getMem_no());

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
	public MemberVO login(String mem_account) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, mem_account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 嚙稽嚙誶穿蕭 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
				memberVO.setMem_name(rs.getString("mem_name"));
				memberVO.setMem_password(rs.getString("mem_password"));
				
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
		return memberVO;
	}
	
	@Override
	public MemberVO personal(Integer mem_no) {
		MemberVO memberVO2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(PERSONAL);

			pstmt.setInt(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 嚙稽嚙誶穿蕭 Domain objects
				memberVO2 = new MemberVO();
				memberVO2.setMem_no(rs.getInt("mem_no"));
				memberVO2.setMem_name(rs.getString("mem_name"));
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
		return memberVO2;
		
	}
	
	@Override
	public MemberVO findByMemName(String mem_name) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEMNO);

			pstmt.setString(1, mem_name);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memberVO 嚙稽嚙誶穿蕭 Domain objects
				memberVO = new MemberVO();
				memberVO.setMem_no(rs.getInt("mem_no"));
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
		return memberVO;
	}
	@Override
	public void updateMemberOnline(Integer mem_no, Integer mem_status) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ONLINE);

			pstmt.setInt(1, mem_status);
			pstmt.setInt(2, mem_no);

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
	public void changePhoto(byte[] mem_photo, Integer photo_mem_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_PHOTO_STMT);

			pstmt.setBytes(1, mem_photo);
			pstmt.setInt(2, photo_mem_no);
			

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