package com.CashTr.model;

import java.text.SimpleDateFormat;
import java.util.List;

public class TestJDBC {

	public static void main(String[] args) {
			
		CashTrJDBCDAO dao = new CashTrJDBCDAO();
				
		// 新增測試
//		CashTrVO vo01 = new CashTrVO();
//		vo01.setMem_no(482000017);
//		vo01.setTrandaction_money(122000017);
//		dao.insert(vo01);
//		System.out.print("NEW Test OK");
//		//修改測試
//		CashTrVO vo02 = new CashTrVO();
//		
//
//		vo02.setMem_no(2222);
//		vo02.setTrandaction_money(1234);
//		vo02.setTrandaction_no(848200005);		
//		dao.update(vo02);
////		
//		pstmt.setDate(1,cashTrVO.getTrandaction_date());
//		pstmt.setInt(2, cashTrVO.getMem_no());
//		pstmt.setInt(3,cashTrVO.getTrandaction_money());
//		
//		pstmt.setInt(4,cashTrVO.getTrandaction_no());
		//刪除測試
//		dao.delete(848200005);
//		System.out.print("Delete Test  OK");
//		
		//查詢測試
//		CashTrVO vo03 = dao.findByPrimaryKey(848200006);
//		System.out.println(vo03.getMem_no() );
//		if (vo03.getTrandaction_date() == null){
//			System.out.println("申請時間:" + vo03.getTrandaction_date() );	
//			
//		}else{
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("申請時間:" + sdf2.format(vo03.getTrandaction_date() ) );			
//		}
//		System.out.println(vo03.getTrandaction_money() );
//		System.out.println(vo03.getTrandaction_no() );
//		System.out.println();
//		
		
//		
		// 查詢
//		List<CashTrVO> aax = dao.getAll();
//		for (CashTrVO vo05 : aax) {
//			System.out.println(vo05.getMem_no() );
//			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println("申請時間:" + sdf2.format(vo05.getTrandaction_date()) );
//			System.out.println(vo05.getTrandaction_money() );
//			System.out.println(vo05.getTrandaction_no() );
//			System.out.println();
//		}
	}

}
