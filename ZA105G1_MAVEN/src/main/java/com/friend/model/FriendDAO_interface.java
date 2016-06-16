package com.friend.model;

import java.util.*;

public interface FriendDAO_interface {
	public void insert(FriendVO friendVO);
    public void update(FriendVO friendVO);
    public void delete(Integer mem_no, Integer friend_no);
    public FriendVO findByPrimaryKey(Integer mem_no, Integer friend_no);
    public List<FriendVO> findByPrimaryKey2(Integer mem_no);//find someone's friends.
    public List<FriendVO> findByPrimaryKey3(Integer mem_no);//find not someone's friends.
    public List<FriendVO> getAll();
}
