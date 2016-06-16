package com.OrderDe.model;

import java.util.List;


public class OrderDeService {
	public OrderDeDAO_interface dao;
	
	public OrderDeService(){
		dao = new OrderDeDAO();
	}

	public OrderDeVO addOrderDe(Integer order_no,String tickets_no,Integer order_num,Integer order_value,Integer order_status){
		OrderDeVO orderdevo = new OrderDeVO();
		
		orderdevo.setOrder_no(order_no);
		orderdevo.setTickets_no(tickets_no);
		orderdevo.setOrder_num(order_num);
		orderdevo.setOrder_value(order_value);
		orderdevo.setOrder_status(order_status);

		dao.insert(orderdevo);
		
		return orderdevo;		
	}
	
	public OrderDeVO updateOrderDe(Integer order_no,String tickets_no,Integer order_num,Integer order_value,Integer order_status){
		OrderDeVO orderdevo = new OrderDeVO();
		
		orderdevo.setOrder_no(order_no);
		orderdevo.setTickets_no(tickets_no);
		orderdevo.setOrder_num(order_num);
		orderdevo.setOrder_value(order_value);
		orderdevo.setOrder_status(order_status);

		dao.update(orderdevo);
		
		return orderdevo;	
	}
	
	public void deleteCashTr(Integer order_no,String tickets_no){
		dao.delete(order_no,tickets_no);
	}

	public OrderDeVO getOneCashTr(Integer order_no,String tickets_no){
		return dao.findByPrimaryKey(order_no,tickets_no);
	}
	
	//利用訂單 尋找所有資料
	public List<OrderDeVO> get_Order(Integer order_no){
		return dao.findByPrimaryKey_Order(order_no);
	}

	public List<OrderDeVO> getAll(){
		return dao.getAll();
	}
}