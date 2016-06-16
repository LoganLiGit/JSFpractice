package com.ad.model;

import java.sql.Date;
import java.util.List;

public class AdService {

	private AdDAO_interface dao;

	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(Integer store_no, byte[] ad_images, Date ad_date, Date ad_date_ed) {

		AdVO adVO = new AdVO();

		adVO.setStore_no(store_no);
		adVO.setAd_images(ad_images);
		adVO.setAd_date(ad_date);
		adVO.setAd_date_ed(ad_date_ed);
		dao.insert(adVO);

		return adVO;
	}

	public AdVO updateAd(Integer ad_no,byte[] ad_images, Date ad_date, Date ad_date_ed) {

		AdVO adVO = new AdVO();

		adVO.setAd_no(ad_no);
		adVO.setAd_images(ad_images);
		adVO.setAd_date(ad_date);
		adVO.setAd_date_ed(ad_date_ed);
		dao.update(adVO);

		return getOneAd(ad_no);
	}

	public void deleteAd(Integer ad_no) {
		dao.delete(ad_no);
	}

	public AdVO getOneAd(Integer ad_no) {
		return dao.findByPrimaryKey(ad_no);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}
}
