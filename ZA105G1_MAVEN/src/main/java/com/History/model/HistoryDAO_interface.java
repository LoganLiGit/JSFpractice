package com.History.model;

import java.util.List;
import java.util.Map;

public interface HistoryDAO_interface {
	public void insert(HistoryVO historyvo);
	//新增
	public List<HistoryVO> getAll(Map<String, String[]> map);
	//萬用複合查詢
	public HistoryVO getAll2(Map<String, String[]> map);
	//萬用複合查詢
}
