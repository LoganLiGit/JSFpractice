package com.Adm.model;

import java.util.List;


public class AdmService {

	private AdmDAO_interface dao;

	public AdmService() {
		dao = new AdmDAO();
	}

	public AdmVO addAdm(String admin_account,
			String admin_password,String admin_name,String admin_email,
			String admin_phone,String admin_address) {

		AdmVO admVO = new AdmVO();

		admVO.setAdmin_account(admin_account);
		admVO.setAdmin_password(admin_password);
		admVO.setAdmin_name(admin_name);
		admVO.setAdmin_email(admin_email);
		admVO.setAdmin_phone(admin_phone);
		admVO.setAdmin_address(admin_address);
		dao.insert(admVO);

		return admVO;
	}

	public AdmVO updateAdm(Integer admin_no,String admin_account,
			String admin_password,String admin_name,String admin_email,
			String admin_phone,String admin_address) {

		AdmVO admVO = new AdmVO();

		admVO.setAdmin_no(admin_no);
		admVO.setAdmin_account(admin_account);
		admVO.setAdmin_password(admin_password);
		admVO.setAdmin_name(admin_name);
		admVO.setAdmin_email(admin_email);
		admVO.setAdmin_phone(admin_phone);
		admVO.setAdmin_address(admin_address);
		dao.update(admVO);

		return admVO;
	}

	public void deleteAdm(Integer admin_no) {
		dao.delete(admin_no);
	}

	public AdmVO getOneAdm(Integer admin_no) {
		return dao.findByPrimaryKey(admin_no);
	}

	public List<AdmVO> getAll() {
		return dao.getAll();
	}
	public AdmVO getOneAccount(String adm_account) {
		return dao.findByAccount(adm_account);
	}
}
