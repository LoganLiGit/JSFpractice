package com.ad.model;

import java.util.*;

public interface AdDAO_interface {
	public void insert(AdVO AdVO);
    public void update(AdVO AdVO);
    public void delete(Integer ad_no);
    public AdVO findByPrimaryKey(Integer ad_no);
    public List<AdVO> getAll();
}
