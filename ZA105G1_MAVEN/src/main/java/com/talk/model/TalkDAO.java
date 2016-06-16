package com.talk.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TalkDAO implements TalkDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ZA105G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

//	INSERT_STMT的時候用字串讀入，再用to_date轉換格式
	private static final String INSERT_STMT = "INSERT INTO TALK (talk_no,mem_no,sender,friend_no,receiver,talk_time,talk_note,read_status) VALUES (TALK_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
//	查詢時直接用Date的型態直接拿出來不用經過to_charar
	private static final String GET_ALL_STMT = "SELECT talk_no,mem_no,sender,friend_no,receiver,talk_time,talk_note,read_status FROM talk order by talk_no";
	private static final String GET_ONE_STMT = "SELECT talk_no,mem_no,sender,friend_no,receiver,talk_time,talk_note,read_status FROM talk where talk_no = ?";
	private static final String DELETE = "DELETE FROM talk where talk_no = ?";
	private static final String UPDATE = "UPDATE talk set read_status=? where mem_no=? and friend_no=?";
	private static final String GET_SOMEONE_TALK_STMT = "SELECT talk_no,mem_no,sender,friend_no,receiver,talk_time,talk_note,read_status FROM talk where mem_no = ? and friend_no = ? or mem_no = ? and friend_no = ? order by talk_time ";
	private static final String UPDATE_SOMEBODY_RECORDS = "UPDATE talk set read_status=? where mem_no=? and friend_no=?";
	private static final String GET_UNREAD_MESSAGE_NUMS = "SELECT talk_no,mem_no,sender,friend_no,receiver,talk_time,talk_note,read_status FROM talk where friend_no = ? and mem_no = ? and read_status=0";
	
	@Override
	public void insert(TalkVO talkVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, talkVO.getMem_no());
			pstmt.setString(2, talkVO.getSender());
			pstmt.setInt(3, talkVO.getFriend_no());
			pstmt.setString(4, talkVO.getReceiver());
//			新增時設的樣子是String，SimpleDateFormat改格式
//			下兩行要修改
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			pstmt.setString(3,sdf.format(talkVO.getTalk_time()).toString());
			
			pstmt.setTimestamp(5, talkVO.getTalk_time());
			pstmt.setString(6, talkVO.getTalk_note());
			pstmt.setInt(7, talkVO.getRead_status());
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
	public void update(TalkVO talkVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, talkVO.getRead_status());
			pstmt.setInt(2, talkVO.getMem_no());
			pstmt.setInt(3, talkVO.getFriend_no());
			
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
	public void delete(Integer talk_no) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, talk_no);

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
	public TalkVO findByPrimaryKey(Integer talk_no) {
		// TODO Auto-generated method stub
		TalkVO talkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, talk_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				talkVO = new TalkVO();
				talkVO.setTalk_no(rs.getInt("talk_no"));
				talkVO.setMem_no(rs.getInt("mem_no"));
				talkVO.setSender(rs.getString("sender"));
				talkVO.setFriend_no(rs.getInt("friend_no"));
				talkVO.setReceiver(rs.getString("receiver"));
//				下兩行要修改
//				long d1 = (Timestamp.valueOf(rs.getString("talk_time")).getTime());
//				talkVO.setTalk_time(new java.sql.Date(d1));
				talkVO.setTalk_time(rs.getTimestamp("talk_time"));
				talkVO.setTalk_note(rs.getString("talk_note"));
				talkVO.setRead_status(rs.getInt("read_status"));

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
		return talkVO;
	}

	@Override
	public List<TalkVO> getAll() {
		// TODO Auto-generated method stub
		List<TalkVO> list = new ArrayList<TalkVO>();
		TalkVO talkVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				talkVO = new TalkVO();
				talkVO.setTalk_no(rs.getInt("talk_no"));
				talkVO.setMem_no(rs.getInt("mem_no"));
				talkVO.setSender(rs.getString("sender"));
				talkVO.setFriend_no(rs.getInt("friend_no"));
				talkVO.setReceiver(rs.getString("receiver"));
//				將拿出來的時間轉成long型態
//				long d1 = (Timestamp.valueOf(rs.getString("talk_time")).getTime());
//				long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
//				talkVO.setTalk_time(new java.util.Date(d1));
				talkVO.setTalk_time(rs.getTimestamp("talk_time"));
				talkVO.setTalk_note(rs.getString("talk_note"));
				talkVO.setRead_status(rs.getInt("read_status"));

				list.add(talkVO); // Store the row in the list
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
	public List<TalkVO> getTalk(Integer mem_no, Integer friend_no) {
		// TODO Auto-generated method stub
		List<TalkVO> list = new ArrayList<TalkVO>();
		TalkVO talkVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOMEONE_TALK_STMT);
			pstmt.setInt(1, mem_no);
			pstmt.setInt(2, friend_no);
			pstmt.setInt(3, friend_no);
			pstmt.setInt(4, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				talkVO = new TalkVO();
				talkVO.setTalk_no(rs.getInt("talk_no"));
				talkVO.setMem_no(rs.getInt("mem_no"));
				talkVO.setSender(rs.getString("sender"));
				talkVO.setFriend_no(rs.getInt("friend_no"));
				talkVO.setReceiver(rs.getString("receiver"));
//				將拿出來的時間轉成long型態
//				long d1 = (Timestamp.valueOf(rs.getString("talk_time")).getTime());
//				long再放進new java.sql.Date，這邊如果直接用rs.getDate("talk_time")，拿出來的會是sql.Date沒辦法顯示時分秒
//				talkVO.setTalk_time(new java.util.Date(d1));
				talkVO.setTalk_time(rs.getTimestamp("talk_time"));
				talkVO.setTalk_note(rs.getString("talk_note"));
				talkVO.setRead_status(rs.getInt("read_status"));

				list.add(talkVO); // Store the row in the list
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
	public Integer getUnreadMessageNum(Integer friend_no, Integer mem_no) {
		// TODO Auto-generated method stub
		int unReadMessage = 0;
		TalkVO talkVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_UNREAD_MESSAGE_NUMS);
			pstmt.setInt(1, friend_no);
			pstmt.setInt(2, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				unReadMessage++;
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
		return unReadMessage;
	}
	
	@Override
	public void updateAllRecords(TalkVO talkVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SOMEBODY_RECORDS);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, talkVO.getMem_no());
			pstmt.setInt(3, talkVO.getFriend_no());

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

}
