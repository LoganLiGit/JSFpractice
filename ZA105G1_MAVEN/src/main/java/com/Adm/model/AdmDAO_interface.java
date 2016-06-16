package com.Adm.model;

import java.util.*;

public interface AdmDAO_interface {
          public void insert(AdmVO admVO);
          public void update(AdmVO admVO);
          public void delete(Integer admin_no);
          public AdmVO findByPrimaryKey(Integer admin_no);
          public List<AdmVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
          public AdmVO findByAccount(String admin_account);
}
