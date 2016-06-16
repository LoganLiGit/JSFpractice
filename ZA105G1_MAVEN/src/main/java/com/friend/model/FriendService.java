package com.friend.model;

import java.util.List;

public class FriendService {
	
	private FriendDAO_interface dao;
	
	public FriendService() {
		dao = new FriendJNDIDAO();
	}
	
	public FriendVO addFriend(Integer mem_no, Integer friend_no, Integer friend_status) {

		FriendVO friendVO = new FriendVO();
		
		friendVO.setMem_no(mem_no);
		friendVO.setFriend_no(friend_no);
		friendVO.setFriend_status(friend_status);
		dao.insert(friendVO);

		return friendVO;
	}
	
	public FriendVO updateFriend(Integer mem_no, Integer friend_no, Integer friend_status) {

		FriendVO friendVO = new FriendVO();

		friendVO.setMem_no(mem_no);
		friendVO.setFriend_no(friend_no);
		friendVO.setFriend_status(friend_status);
		
		dao.update(friendVO);
		
		return friendVO;
	}
	
	public void deleteFriend(Integer mem_no, Integer friend_no) {
		dao.delete(mem_no,friend_no);
	}
	
	public FriendVO getOneFriend(Integer mem_no, Integer friend_no) {
		return dao.findByPrimaryKey(mem_no,friend_no);
	}
	
	public List<FriendVO> getSomeFriend(Integer mem_no) {
		return dao.findByPrimaryKey2(mem_no);
	}
	public List<FriendVO> getSomeTodayFriend(Integer mem_no) {
		
		return dao.findByPrimaryKey3(mem_no);
		
	}
	
	public List<FriendVO> getAll() {
		return dao.getAll();
	}

}
