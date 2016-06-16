package com.reply.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.reply.model.*;
import com.article.model.*;

public class ReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("reply_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙稷嚙請編嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				Integer reply_no = null;
				try {
					reply_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙稷嚙請編嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				ReplyService replySvc = new ReplyService();
				ReplyVO replyVO = replySvc.getOneReply(reply_no);
				if (replyVO == null) {
					errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				req.setAttribute("replyVO", replyVO); // 嚙踝蕭w嚙踝蕭X嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/reply/listOneReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneEmp.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭o嚙踝蕭嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSome_For_Display".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			List<ReplyVO> replies = new LinkedList<ReplyVO>();
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("article_no");
				System.out.println("article_no:"+str);
				Integer article_click = new Integer(req.getParameter("article_click"));
				article_click++;
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙踝蕭嚙瞌嚙編嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				Integer article_no = null;
				try {
					article_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙踝蕭嚙瞌嚙編嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				ReplyService replySvc = new ReplyService();//抓某篇食記的留言
				replies = replySvc.getSomeReply(article_no);
				
				ArticleService articleSvc = new ArticleService();
				articleSvc.updateArticleClick(article_no,article_click);//查看食記,更新點擊數
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);//抓某篇食記
				
				MemberService memberSvc = new MemberService();
				System.out.println("articleVO"+articleVO.getMem_no());
				MemberVO memberVO = memberSvc.getOneMember(articleVO.getMem_no());
				
				System.out.println("嚙範嚙趣的嚙稷嚙請蛛蕭嚙踝蕭:"+replies.size());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				req.setAttribute("replies", replies); 
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("memberVO", memberVO);
				String url = "/front/personal/singleArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneEmp.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			
		}
		
		
		
		
		if ("getOne_For_Update".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
				Integer reply_no = new Integer(req.getParameter("reply_no"));
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�***************************************/
				ReplyService replySvc = new ReplyService();
				ReplyVO replyVO = replySvc.getOneReply(reply_no);
								
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)************/
				req.setAttribute("replyVO", replyVO);         // 嚙踝蕭w嚙踝蕭X嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/reply/update_reply_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙�update_emp_input.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭o嚙緯嚙論改的嚙踝蕭嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/listAllReply.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSome_For_Update".equals(action)) { // 嚙諉佗蕭listSomeFriend.jsp嚙踝蕭嚙請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭****************************************/
				Integer reply_no = new Integer(req.getParameter("reply_no"));
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�***************************************/
				ReplyService replySvc = new ReplyService();
				ReplyVO replyVO = replySvc.getOneReply(reply_no);
								
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)************/
				req.setAttribute("replyVO", replyVO);         // 嚙踝蕭w嚙踝蕭X嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/reply/update_replyInSomeReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭嚙穀嚙踝蕭嚙�update_emp_input.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭o嚙緯嚙論改的嚙踝蕭嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/listSomeReply.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 嚙諉佗蕭update_emp_input.jsp嚙踝蕭嚙請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				Integer reply_no = new Integer(req.getParameter("reply_no").trim());
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer article_no = new Integer(req.getParameter("article_no").trim());	
				String reply_msg = req.getParameter("reply_msg").trim();	
				
				java.sql.Timestamp reply_time = null;
				reply_time = new java.sql.Timestamp(System.currentTimeMillis());

				ReplyVO replyVO = new ReplyVO();
				replyVO.setReply_no(reply_no);
				replyVO.setMem_no(mem_no);
				replyVO.setArticle_no(article_no);
				replyVO.setReply_msg(reply_msg);
				replyVO.setReply_time(reply_time);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭~嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/update_reply_input.jsp");
					failureView.forward(req, res);
					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
				ReplyService replySvc = new ReplyService();
				replyVO = replySvc.updateReply(reply_no, mem_no, article_no, reply_msg, reply_time);
				
				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				req.setAttribute("replyVO", replyVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				String url = "/reply/listOneReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙論改蕭嚙複伐蕭嚙踝蕭:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/update_reply_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update2".equals(action)) { // 嚙諉佗蕭update_friendInSomeFriend.jsp嚙踝蕭嚙請求
			List<ReplyVO> replies = new LinkedList<ReplyVO>();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
//			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				Integer reply_no = new Integer(req.getParameter("reply_no").trim());
				Integer article_no = new Integer(req.getParameter("article_no").trim());
				String reply_msg = req.getParameter("reply_msg").trim();
				
				ReplyVO replyVO = new ReplyVO();
				replyVO.setArticle_no(article_no);
				replyVO.setReply_msg(reply_msg);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭~嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/update_reply_input.jsp");
					failureView.forward(req, res);
					return; //嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙論改蕭嚙踝蕭*****************************************/
				ReplyService replySvc = new ReplyService();
				replyVO = replySvc.updateReply2(reply_no, reply_msg);
				replies = replySvc.getSomeReply(article_no);
				
				/***************************3.嚙論改完嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				//req.setAttribute("friendVO", friendVO); // 嚙踝蕭wupdate嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙確嚙踝蕭嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙編嚙皚req
				req.setAttribute("replies", replies);
				String url = "/reply/listSomeReply.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙論改成嚙穀嚙踝蕭,嚙踝蕭嚙締istOneEmp.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("嚙論改蕭嚙複伐蕭嚙踝蕭:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/reply/update_reply_input.jsp");
//				failureView.forward(req, res);
//			}
		}

        if ("insert".equals(action)) { // 嚙諉佗蕭addEmp.jsp嚙踝蕭嚙請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲*************************/
				
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer article_no = new Integer(req.getParameter("article_no").trim());
				String reply_msg = req.getParameter("reply_msg").trim();
				
				java.sql.Timestamp reply_time = null;
				reply_time = new java.sql.Timestamp(System.currentTimeMillis());
				

				ReplyVO replyVO = new ReplyVO();
				replyVO.setMem_no(mem_no);
				replyVO.setArticle_no(article_no);
				replyVO.setReply_msg(reply_msg);
				replyVO.setReply_time(reply_time);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭~嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/addReply.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�**************************************/
				ReplyService replySvc = new ReplyService();
				replyVO = replySvc.addReply(mem_no, article_no, reply_msg, reply_time);
				
				/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)***********/
				String url = "/friend/singleArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/addReply.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("insert2".equals(action)) { // 嚙諉佗蕭addEmp.jsp嚙踝蕭嚙請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			List<ReplyVO> replies = new LinkedList<ReplyVO>();

			try {
				/***********************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲*************************/
				
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer article_no = new Integer(req.getParameter("article_no").trim());
				String reply_msg = req.getParameter("reply_msg").trim();
				
				java.sql.Timestamp reply_time = null;
				reply_time = new java.sql.Timestamp(System.currentTimeMillis());
				

				ReplyVO replyVO = new ReplyVO();
				replyVO.setMem_no(mem_no);
				replyVO.setArticle_no(article_no);
				replyVO.setReply_msg(reply_msg);
				replyVO.setReply_time(reply_time);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("replyVO", replyVO); // 嚙緣嚙踝蕭嚙踝蕭J嚙賣式嚙踝蕭~嚙踝蕭empVO嚙踝蕭嚙踝蕭,嚙稽嚙編嚙皚req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/reply/addReply.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.嚙罷嚙締嚙編嚙磕嚙踝蕭嚙�**************************************/
				ReplyService replySvc = new ReplyService();//抓某篇食記的回覆
				replies = replySvc.addReply2(mem_no, article_no, reply_msg, reply_time);//新增留言
				int article_replies = replies.size();//抓新增完留言後的留言篇數
				
				ArticleService articleSvc = new ArticleService();
				articleSvc.updateArticleRepliesNum(article_no, article_replies);//更新此篇食記的留言篇數
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);//抓某篇食記的相關資訊
				System.out.println("嚙範嚙趣的嚙稷嚙請蛛蕭嚙踝蕭:"+replies.size());
				if (replies.isEmpty()) {
					errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
				}
				
				/***************************3.嚙編嚙磕嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)***********/
				req.setAttribute("replies", replies);
				req.setAttribute("articleVO", articleVO);
				String url = "/front/personal/singleArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙編嚙磕嚙踝蕭嚙穀嚙踝蕭嚙踝蕭嚙締istAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/addReply.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
//				Integer reply_no = new Integer(req.getParameter("reply_no"));
//				
//				/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�**************************************/
//				ReplyService replySvc = new ReplyService();
//				replySvc.deleteReply(reply_no);
//				
//				/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)***********/								
//				String url = "/friend/singleArticle.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
//				successView.forward(req, res);
//				
//				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("嚙磋嚙踝蕭嚙踝蕭嚙踝蕭嚙�"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/reply/listAllReply.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if ("delete2".equals(action)) { // 嚙諉佗蕭listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			List<ReplyVO> replies = new LinkedList<ReplyVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭***************************************/
				Integer reply_no = new Integer(req.getParameter("reply_no"));
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.嚙罷嚙締嚙磋嚙踝蕭嚙踝蕭嚙�**************************************/

				
				ReplyService replySvc = new ReplyService();//抓某篇食記的回覆
				replies = replySvc.deleteReply2(reply_no,article_no);//刪除某篇留言
				int article_replies = replies.size();//抓刪除留言後的留言篇數
				ArticleService articleSvc = new ArticleService();
				articleSvc.updateArticleRepliesNum(article_no, article_replies);//更新此篇食記的留言篇數
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);//抓某篇食記的相關資訊
				System.out.println("嚙範嚙趣的嚙稷嚙請蛛蕭嚙踝蕭:"+replies.size());
				if (replies.isEmpty()) {
					errorMsgs.add("嚙範嚙盤嚙踝蕭嚙�");
				}
				
				/***************************3.嚙磋嚙踝蕭嚙踝蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)***********/	
				req.setAttribute("replies", replies);
				req.setAttribute("articleVO", articleVO);
				
				String url = "/front/personal/singleArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙磋嚙踝蕭嚙踝蕭嚙穀嚙踝蕭,嚙踝蕭嚙稷嚙箴嚙碼嚙磋嚙踝蕭嚙踝蕭嚙諉瘀蕭嚙踝蕭嚙踝蕭
				successView.forward(req, res);
				
				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲**********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙磋嚙踝蕭嚙踝蕭嚙踝蕭嚙�"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/reply/listAllReply.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	}
}
