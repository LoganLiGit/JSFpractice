package com.store.pic.model;

import java.util.*;

public interface Store_picDAO_interface {

		public void insert(Store_picVO store_picVO);

		public void update(Store_picVO store_picVO);

		public void delete(Integer pic_no);

		public Store_picVO findByPrimaryKey(Integer pic_no);

		public List<Store_picVO> getAll();



}
