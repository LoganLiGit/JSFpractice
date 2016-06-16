package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;

public class Back_Mbma_Servlet extends HttpServlet {
	
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
	
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer mem_right = new Integer(req.getParameter("mem_right").trim());
	
				MemberService memberSvc = new MemberService();
				memberSvc.updateMember3(mem_no,mem_right);
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
