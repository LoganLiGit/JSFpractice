package com.Stoma.controller;


import java.io.IOException;
import java.io.PrintWriter;




import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.model.StoreService;
import com.store.model.StoreVO;




/**
 * Servlet implementation class StoreServlet
 */
// @WebServlet("/StoreServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Back_Stoma_Servlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	
		if ("update_right".equals(action)) {
			
			String msg = "權限更改成功";
			try {
	
				Integer store_no = new Integer(req.getParameter("store_no").trim());
				Integer store_state = new Integer(req.getParameter("store_state").trim());
				
				StoreService storeSvc = new StoreService();
				StoreVO A = storeSvc.getOneStore(store_no);

				A.setStore_state(store_state);
				storeSvc.updateStore2(A);
			}
			catch (Exception e) {
				msg="權限更改失敗";
			}
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(msg);
			out.flush();
			out.close();
		}
	}
}
