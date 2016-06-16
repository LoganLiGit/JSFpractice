package com.talk.model;

import java.util.*;

public interface TalkDAO_interface {
	public void insert(TalkVO talkVO);

	public void update(TalkVO talkVO);

	public void delete(Integer talk_no);

	public TalkVO findByPrimaryKey(Integer talk_no);

	public List<TalkVO> getAll();
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	// public List<EmpVO> getAll(Map<String, String[]> map);

	public List<TalkVO> getTalk(Integer mem_no, Integer friend_no);

	public  void updateAllRecords(TalkVO talkVO);

	public  Integer getUnreadMessageNum(Integer friend_no, Integer mem_no);
}
