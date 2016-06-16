package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
          public void insert(MemberVO memberVO);
          public void update(MemberVO memberVO);
          public void update2(MemberVO memberVO);
          public void delete(Integer mem_no);
          public MemberVO findByPrimaryKey(Integer mem_no);
          public List<MemberVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<MemberVO> getAll(Map<String, String[]> map);
          public MemberVO findByAccount(String mem_account);
          public void comoney(Integer mem_no,Integer money,java.sql.Connection con);
          //儲值
          public MemberVO addmoney(Integer mem_no,Integer money);
          //修改會員權限
          public void update3(MemberVO memberVO);
          
          public MemberVO login(String mem_account);
          public MemberVO personal(Integer mem_no);
          //嚙磊嚙諄複合嚙範嚙踝蕭(嚙褒入嚙諸數恬蕭嚙璀Map)(嚙稷嚙踝蕭 List)
//        public List<MemberVO> getAll(Map<String, String[]> map); 
          public MemberVO findByMemName(String mem_name);
          public void updateMemberOnline(Integer mem_no, Integer mem_status);
          public void changePhoto(byte[] mem_photo, Integer photo_mem_no);
}
