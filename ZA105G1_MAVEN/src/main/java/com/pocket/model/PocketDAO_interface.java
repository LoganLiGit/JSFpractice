package com.pocket.model;

import java.util.*;

public interface PocketDAO_interface {
	public void insert(PocketVO pocketVO);
    public void update(PocketVO pocketVO);
    public void delete(Integer pl_no);
    public PocketVO findByPrimaryKey(Integer pl_no);
    public List<PocketVO> getAll();
    public List<PocketVO> getKeepStores(Integer mem_no);
}
