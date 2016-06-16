package com.OrderDe.model;
//團購券訂單明細order_details

import java.util.*;

import com.CashTr.model.CashTrVO;

public interface OrderDeDAO_interface {
	public void insert(OrderDeVO orderDeVo);
	public void update(OrderDeVO orderDeVo);
	public void delete(Integer order_no,String tickets_no);
	public OrderDeVO findByPrimaryKey(Integer order_no,String tickets_no);
	public List<OrderDeVO> getAll();
	//利用order_no 訂單編號 尋找所有的
	public List<OrderDeVO> findByPrimaryKey_Order(Integer order_no);
	
	
	//新增訂單的作業流程 (多個)
	//新增團購券訂單明細
	//傳入  團購券種類編號 /此訂單編號  / 折價後的單品價格/連線
	//(tickets_type_no,next_deptno,price,con)
	public void getneworderde(Integer tickets_type_no,Integer next_deptno,Integer price,Integer qu,java.sql.Connection con);
}
