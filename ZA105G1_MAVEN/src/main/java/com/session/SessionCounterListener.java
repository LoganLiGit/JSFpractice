package com.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebListener
public class SessionCounterListener extends HttpServlet implements HttpSessionListener {
	
	MemberVO memberVO = null;
	MemberService memberSvc = new MemberService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
	}
	

	  private static int totalActiveSessions;

	  public static int getTotalActiveSession(){
	        return totalActiveSessions;
	  }

	  @Override
	  public void sessionCreated(HttpSessionEvent arg0) {
	        totalActiveSessions++;
	        System.out.println("sessionCreated - add one session into counter");
	  }

	  @Override
	  public void sessionDestroyed(HttpSessionEvent arg0) {
	        totalActiveSessions--;
	        System.out.println("sessionDestroyed - deduct one session from counter");
	        System.out.println(memberVO);
	        //memberSvc.updateMemberOnline(memberVO.getMem_no(), 0);
	        
	    	
	    	
	  }
	}