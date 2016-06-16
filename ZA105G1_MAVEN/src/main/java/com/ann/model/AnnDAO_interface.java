package com.ann.model;

import java.util.*;

public interface AnnDAO_interface {
	public void insert(AnnVO AnnVO);
    public void update(AnnVO AnnVO);
    public void delete(Integer ann_no);
    public AnnVO findByPrimaryKey(Integer ann_no);
    public List<AnnVO> getAll();
}
