package com.qa.model;

import java.util.*;

public interface QaDAO_interface {
	public void insert(QaVO QaVO);
    public void update(QaVO QaVO);
    public void delete(Integer qa_no);
    public QaVO findByPrimaryKey(Integer qa_no);
    public List<QaVO> getAll();
}
