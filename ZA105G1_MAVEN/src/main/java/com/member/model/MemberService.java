package com.member.model;

import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String mem_account, String mem_password, java.sql.Date mem_regist_date, 
			String mem_name, String mem_nickname, java.sql.Date mem_birthday, byte[] mem_photo, String mem_idcard,
			String mem_sex, String mem_zipcode, String mem_city, String mem_district, String mem_address,
			String mem_phone, String mem_cellphone, String mem_email, String mem_skill, String mem_hobby,
			Integer mem_relationship, Integer mem_right, String mem_intro, Integer mem_level, Integer mem_status
			,String mem_redid,Integer mem_balance) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_account(mem_account);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_regist_date(mem_regist_date);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_idcard(mem_idcard);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_zipcode(mem_zipcode);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_district(mem_district);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_cellphone(mem_cellphone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_skill(mem_skill);
		memberVO.setMem_hobby(mem_hobby);
		memberVO.setMem_relationship(mem_relationship);
		memberVO.setMem_right(mem_right);
		memberVO.setMem_intro(mem_intro);
		memberVO.setMem_level(mem_level);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_redid(mem_redid);
		memberVO.setMem_balance(mem_balance);
		
		dao.insert(memberVO);


		return memberVO;
	}

	public MemberVO updateMember(Integer mem_no,String mem_account, String mem_password, java.sql.Date mem_regist_date, 
			String mem_name, String mem_nickname, java.sql.Date mem_birthday, byte[] mem_photo, String mem_idcard,
			String mem_sex, String mem_zipcode, String mem_city, String mem_district, String mem_address,
			String mem_phone, String mem_cellphone, String mem_email, String mem_skill, String mem_hobby,
			Integer mem_relationship, Integer mem_right, String mem_intro, Integer mem_level, Integer mem_status
			,String mem_redid,Integer mem_balance) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_account(mem_account);
		memberVO.setMem_password(mem_password);
		memberVO.setMem_regist_date(mem_regist_date);
		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);
		memberVO.setMem_birthday(mem_birthday);
		memberVO.setMem_photo(mem_photo);
		memberVO.setMem_idcard(mem_idcard);
		memberVO.setMem_sex(mem_sex);
		memberVO.setMem_zipcode(mem_zipcode);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_district(mem_district);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_cellphone(mem_cellphone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_skill(mem_skill);
		memberVO.setMem_hobby(mem_hobby);
		memberVO.setMem_relationship(mem_relationship);
		memberVO.setMem_right(mem_right);
		memberVO.setMem_intro(mem_intro);
		memberVO.setMem_level(mem_level);
		memberVO.setMem_status(mem_status);
		memberVO.setMem_redid(mem_redid);
		memberVO.setMem_balance(mem_balance);
		
		dao.update(memberVO);

		return memberVO;
	}

	public MemberVO updateMember2(Integer mem_no,String mem_password,
			String mem_name, String mem_nickname,  byte[] mem_photo,
			String mem_zipcode, String mem_city, String mem_district, String mem_address,
			String mem_phone, String mem_cellphone, String mem_email, String mem_skill, String mem_hobby,
			Integer mem_relationship,String mem_intro) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);

		memberVO.setMem_password(mem_password);

		memberVO.setMem_name(mem_name);
		memberVO.setMem_nickname(mem_nickname);

		memberVO.setMem_photo(mem_photo);

		memberVO.setMem_zipcode(mem_zipcode);
		memberVO.setMem_city(mem_city);
		memberVO.setMem_district(mem_district);
		memberVO.setMem_address(mem_address);
		memberVO.setMem_phone(mem_phone);
		memberVO.setMem_cellphone(mem_cellphone);
		memberVO.setMem_email(mem_email);
		memberVO.setMem_skill(mem_skill);
		memberVO.setMem_hobby(mem_hobby);
		memberVO.setMem_relationship(mem_relationship);

		memberVO.setMem_intro(mem_intro);
		
		dao.update2(memberVO);

		return memberVO;
	}
	public void deleteMember(Integer mem_no) {
		dao.delete(mem_no);
	}

	public MemberVO getOneMember(Integer mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public MemberVO getOneAccount(String mem_account) {
		return dao.findByAccount(mem_account);
	}
	
	public MemberVO getOneAccount2(Integer mem_no) {
		return dao.personal(mem_no);
	}
	
	public MemberVO getOneMemNo(String mem_name) {
		return dao.findByMemName(mem_name);
	}
	public MemberVO updateMember3(Integer mem_no, Integer mem_status
			) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMem_no(mem_no);
		memberVO.setMem_status(mem_status);
		
		
		dao.update3(memberVO);

		return memberVO;
	}
	
	public MemberVO add_money(Integer mem_no,Integer money) {
		return dao.addmoney(mem_no, money);
	}
	public void updateMemberOnline(Integer mem_no, Integer mem_status){
		dao.updateMemberOnline(mem_no,mem_status);
		return;
	}
	public void changePhoto(byte[] mem_photo, Integer photo_mem_no){
		dao.changePhoto(mem_photo, photo_mem_no);
		return;
	}
}
