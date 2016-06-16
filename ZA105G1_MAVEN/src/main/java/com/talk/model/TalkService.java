package com.talk.model;

import java.sql.Timestamp;
import java.util.*;



public class TalkService {
	private TalkDAO_interface dao;
	
	public TalkService() {
		dao = new TalkDAO();
	}
	
	public TalkVO addTalk(Integer mem_no,String sender,Integer friend_no,String receiver,Timestamp talk_time,String talk_note,Integer read_status) {

		TalkVO talkVO = new TalkVO();

		talkVO.setMem_no(mem_no);
		talkVO.setSender(sender);
		talkVO.setFriend_no(friend_no);
		talkVO.setReceiver(receiver);
		talkVO.setTalk_time(talk_time);
		talkVO.setTalk_note(talk_note);
		talkVO.setRead_status(read_status);
		dao.insert(talkVO);
		return talkVO;
	}
	
	public TalkVO updateTalk(Integer mem_no, Integer friend_no,Integer read_status) {

		TalkVO talkVO = new TalkVO();
		talkVO.setMem_no(mem_no);
		talkVO.setFriend_no(friend_no);
		talkVO.setRead_status(read_status);
		dao.update(talkVO);
		return talkVO;
	}
	
	public void deleteTalk(Integer talk_no) {
		dao.delete(talk_no);
	}

	public TalkVO getOneTalk(Integer talk_no) {
		return dao.findByPrimaryKey(talk_no);
	}

	public List<TalkVO> getAll() {
		return dao.getAll();
	}
	
	public List<TalkVO> getTalk(Integer mem_no, Integer friend_no) {
		return dao.getTalk(mem_no, friend_no);
	}
	
	public Integer getUnreadMessageNum(Integer friend_no, Integer mem_no){
		return dao.getUnreadMessageNum(friend_no, mem_no);
	}
	
	public TalkVO updateAllRecords(Integer mem_no, Integer friend_no) {
		TalkVO talkVO = new TalkVO();
		talkVO.setMem_no(mem_no);
		talkVO.setFriend_no(friend_no);
		dao.updateAllRecords(talkVO);
		return talkVO;
	}
}
