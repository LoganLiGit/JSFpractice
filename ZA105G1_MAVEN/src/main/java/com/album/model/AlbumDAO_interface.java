package com.album.model;

import java.util.*;

public interface AlbumDAO_interface {
	public void insert(AlbumVO albumVO);
   // public void update(AlbumVO albumVO);
    public void delete(Integer photo_no);
   // public AlbumVO findByPrimaryKey(Integer mem_no);
   // public List<AlbumVO> findByPrimaryKey2(Integer mem_no);
   // public AlbumVO findByPrimaryKey3(Integer photo_no);
    public List<AlbumVO> getAll(Integer mem_no);
    
}
