package com.friend.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

import com.friend.model.*;
import com.google.gson.Gson;
import com.member.model.*;

import org.apache.*;
import org.apache.tomcat.util.codec.binary.Base64;

public class FriendServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String action2 = req.getParameter("action2");
		
		
		if ("getOne_For_Display".equals(action)) { // ???select_page.jsp????D

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				String str = req.getParameter("mem_no");
				String str1 = req.getParameter("friend_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("???J?|??s??");
				}
				if (str1 == null || (str1.trim()).length() == 0) {
					errorMsgs.add("???J?B??s??");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("?|??s???榡?????T");
				}
				Integer friend_no = null;
				try {
					friend_no = new Integer(str1);
				} catch (Exception e) {
					errorMsgs.add("?B??s???榡?????T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************2.?}?l?d????*****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.getOneFriend(mem_no,friend_no);
				if (friendVO == null) {
					errorMsgs.add("?d?L???");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************3.?d?????,??????(Send the Success view)*************/
				req.setAttribute("friendVO", friendVO); // ???w??X??empVO????,?s?Jreq
				String url = "/friend/listOneFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ???\??? listOneEmp.jsp
				successView.forward(req, res);

				/***************************??L?i????~?B?z*************************************/
			} catch (Exception e) {
				errorMsgs.add("?L?k??o???:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSome_For_Display".equals(action)) { // ???select_page.jsp????D

			List<String> errorMsgs = new LinkedList<String>();
			List<FriendVO> friends = new LinkedList<FriendVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("???J?|??s??");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("?|??s???榡?????T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************2.?}?l?d????*****************************************/
				FriendService friendSvc = new FriendService();
				friends = friendSvc.getSomeFriend(mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************3.?d?????,??????(Send the Success view)*************/
				req.setAttribute("friends", friends); // ???w??X??friends????,?s?Jreq
				System.out.println("friends:"+friends.size());
				String url = "/front/personal/listSomeFriend2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ???\??? listOneEmp.jsp
				successView.forward(req, res);

				/***************************??L?i????~?B?z*************************************/
			} catch (Exception e) {
				errorMsgs.add("?L?k??o???:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getTodayFriend".equals(req.getParameter("action"))) { 

			List<String> errorMsgs = new LinkedList<String>();
			List<FriendVO> todayFriend = new LinkedList<FriendVO>();
			List<FriendVO> list = new LinkedList<FriendVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("???J?|??s??");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("?|??s???榡?????T");
				}
				
				System.out.println("mem_no:"+mem_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************2.?}?l?d????*****************************************/
				FriendService friendSvc = new FriendService();
				todayFriend = friendSvc.getSomeTodayFriend(mem_no);
				System.out.println("todayFriend:"+todayFriend.size());//抓還沒成為朋友的人
				System.out.println((int)(Math.random()*todayFriend.size()));
				FriendVO friendVO = todayFriend.get((int)(Math.random()*todayFriend.size()));
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(friendVO.getMem_no());
				System.out.println("friendVO.getMem_no():"+friendVO.getMem_no());

				System.out.println("memberVO.getMem_no():"+memberVO.getMem_no());
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//?{?????_
				}
				
				/***************************3.?d?????,??????(Send the Success view)*************/
				//req.setAttribute("friendVO", friendVO); // ???w??X??friends????,?s?Jreq

				//String a = Base64.encodeBase64String(memberVO.getMem_photo());
				//System.out.println("a:"+a);
				
				JSONArray array = new JSONArray();
				
				JSONObject obj = new JSONObject();
				try{
					
					//obj.put("mem_photo", a);
					obj.put("mem_photo", memberVO.getMem_no());
					obj.put("mem_no", memberVO.getMem_no());
					obj.put("mem_name", memberVO.getMem_name());
					obj.put("mem_nickname", memberVO.getMem_nickname());
					obj.put("mem_birthday", memberVO.getMem_birthday().toString());
					obj.put("mem_skill", memberVO.getMem_skill());
					obj.put("mem_hobby", memberVO.getMem_hobby());
				}catch(Exception e){}
				array.add(obj);
				
				System.out.println("obj.size():"+obj.size());
  

			    res.setContentType("text/plain");
			    res.setCharacterEncoding("UTF-8");
			    System.out.println("json:"+array.toString());
			    
			    PrintWriter out = res.getWriter();
			    out.write(array.toString());
			    out.flush();
				out.close();
				
//				HttpSession session = req.getSession();
//				session.setAttribute("todayFriend_no", memberVO.getMem_no());
//				System.out.println("session_memberVO.getMem_no():"+memberVO.getMem_no());
				
				
				

				/***************************??L?i????~?B?z*************************************/
			} catch (Exception e) {
				errorMsgs.add("?L?k??o???:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // ???listAllEmp.jsp????D

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????D???****************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer friend_no = new Integer(req.getParameter("friend_no"));
				
				/***************************2.?}?l?d????****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.getOneFriend(mem_no,friend_no);
								
				/***************************3.?d?????,??????(Send the Success view)************/
				req.setAttribute("friendVO", friendVO);         // ???w??X??empVO????,?s?Jreq
				String url = "/friend/update_friend_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ???\??? update_emp_input.jsp
				successView.forward(req, res);

				/***************************??L?i????~?B?z**********************************/
			} catch (Exception e) {
				errorMsgs.add("?L?k??o?n??????:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSome_For_Update".equals(action)) { // ???listSomeFriend.jsp????D

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.??????D???****************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer friend_no = new Integer(req.getParameter("friend_no"));
				
				/***************************2.?}?l?d????****************************************/
				FriendService friendSvc = new FriendService();
				FriendVO friendVO = friendSvc.getOneFriend(mem_no,friend_no);
								
				/***************************3.?d?????,??????(Send the Success view)************/
				req.setAttribute("friendVO", friendVO);         // ???w??X??empVO????,?s?Jreq
				String url = "/friend/update_friendInSomeFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ???\??? update_emp_input.jsp
				successView.forward(req, res);

				/***************************??L?i????~?B?z**********************************/
			} catch (Exception e) {
				errorMsgs.add("?L?k??o?n??????:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/listSomeFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // ???update_emp_input.jsp????D
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer friend_no = new Integer(req.getParameter("friend_no").trim());
				Integer friend_status = new Integer(req.getParameter("friend_status").trim());			
				
				FriendVO friendVO = new FriendVO();
				friendVO.setMem_no(mem_no);
				friendVO.setFriend_no(friend_no);
				friendVO.setFriend_status(friend_status);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO); // ?t????J?榡??~??empVO????,?]?s?Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/update_friend_input.jsp");
					failureView.forward(req, res);
					return; //?{?????_
				}
				
				/***************************2.?}?l?????*****************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.updateFriend(mem_no, friend_no, friend_status);
				
				/***************************3.??粒??,??????(Send the Success view)*************/
				//req.setAttribute("friendVO", friendVO); // ???wupdate???\??,???T????empVO????,?s?Jreq
				String url = "/listSomeFriend2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ??令?\??,???listOneEmp.jsp
				successView.forward(req, res);

				/***************************??L?i????~?B?z*************************************/
			} catch (Exception e) {
				errorMsgs.add("????????:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/update_friend_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update2".equals(action)) { //當接受對方的朋友邀請後,我這邊就更新朋友狀態
			List<FriendVO> friends = new LinkedList<FriendVO>();
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer friend_no = new Integer(req.getParameter("friend_no").trim());
				Integer friend_status = new Integer(req.getParameter("friend_status").trim());			
				
				FriendVO friendVO = new FriendVO();
				friendVO.setMem_no(mem_no);
				friendVO.setFriend_no(friend_no);
				friendVO.setFriend_status(friend_status);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO); // ?t????J?榡??~??empVO????,?]?s?Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/update_friend_input.jsp");
					failureView.forward(req, res);
					return; //?{?????_
				}
				
				/***************************2.?}?l?????*****************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.updateFriend(mem_no, friend_no, friend_status);//我接受別人的邀請
				friends = friendSvc.getSomeFriend(mem_no);
				
				/***************************3.??粒??,??????(Send the Success view)*************/
				//req.setAttribute("friendVO", friendVO); // ???wupdate???\??,???T????empVO????,?s?Jreq
				req.setAttribute("friends", friends);
				/*String url = "/front/personal/listSomeFriend2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ??令?\??,???listOneEmp.jsp
				successView.forward(req, res);*/

				/***************************??L?i????~?B?z*************************************/
			} catch (Exception e) {
				errorMsgs.add("????????:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/update_friend_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action2)) { //當接受我的朋友邀請的會員接受後,我這邊就新增朋友
        	
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.??????D??? - ??J?榡????~?B?z*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer friend_no = new Integer(req.getParameter("friend_no").trim());
				Integer friend_status = new Integer(req.getParameter("friend_status").trim());

				FriendVO friendVO = new FriendVO();
			    

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO); // ?t????J?榡??~??empVO????,?]?s?Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/addFriend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.?}?l?s?W???***************************************/
				FriendService friendSvc = new FriendService();
				System.out.println(friendSvc.getOneFriend(friend_no, mem_no));
				if(friendSvc.getOneFriend(friend_no, mem_no)==null){//接受朋友邀請後,對方那邊新增一筆朋友資料,														//但要先判斷對方是否也有邀請我為朋友了,如果沒有就直接新增
					friendSvc.addFriend(friend_no ,mem_no, friend_status);
				}
				else
				{	
					friendSvc.deleteFriend(friend_no, mem_no);
					friendSvc.addFriend(friend_no ,mem_no, friend_status);//有的話先刪掉在新增

				}
				
				
				
				/***************************3.?s?W????,??????(Send the Success view)***********/
				
				String url = "/front/personal/listSomeFriend2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************??L?i????~?B?z**********************************/
			} catch (Exception e) {
				System.out.println("對方新增朋友有誤");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/addFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // ???listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			List<FriendVO> friends = new LinkedList<FriendVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.??????D???***************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				Integer friend_no = new Integer(req.getParameter("friend_no"));
				
				System.out.println("mem_no:"+mem_no);
				System.out.println("friend_no:"+friend_no);
				/***************************2.?}?l?R?????***************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.deleteFriend(mem_no,friend_no);//我刪除朋友,對方變成不是我的朋友(我的頁面看不到對方)
				friendSvc.deleteFriend(friend_no,mem_no);//我刪除朋友,我變成不是對方的朋友(對方頁面看不到我)
				
				friends = friendSvc.getSomeFriend(mem_no);
				
				/***************************3.?R??????,??????(Send the Success view)***********/	
				req.setAttribute("friends", friends);
				String url = "/front/personal/listSomeFriend2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ?R?????\??,???^?e?X?R???????????
				successView.forward(req, res);
				
				
				/***************************??L?i????~?B?z**********************************/
			} catch (Exception e) {
				errorMsgs.add("?R????????:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("inviteToBeFriend1".equals(action)) { //發送今日朋友朋友邀請

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.??????D??? - ??J?榡????~?B?z*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				System.out.println("mem_no:"+mem_no);
				Integer friend_no = new Integer(req.getParameter("friend_no").trim());
				System.out.println("friend_no:"+friend_no);
				Integer friend_status = 0;
				System.out.println("friend_status:"+friend_status);

				FriendVO friendVO = new FriendVO();
			    

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO); // ?t????J?榡??~??empVO????,?]?s?Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/addFriend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.?}?l?s?W???***************************************/
				FriendService friendSvc = new FriendService();
				friendSvc.addFriend(friend_no ,mem_no, friend_status);
				
				
				/***************************3.?s?W????,??????(Send the Success view)***********/
				
				/*String url = "/friend/listAllFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ?s?W???\?????listAllEmp.jsp
				successView.forward(req, res);	*/			
				
				/***************************??L?i????~?B?z**********************************/

		}
		
		if ("inviteToBeFriend2".equals(action)) { //發送一般朋友邀請

			List<String> errorMsgs = new LinkedList<String>();
			List<FriendVO> friends = new LinkedList<FriendVO>();//裝當前使用者瀏覽其他使用者的朋友
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.??????D??? - ??J?榡????~?B?z*************************/
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				System.out.println("mem_no:"+mem_no);//邀請者
				Integer friend_no = new Integer(req.getParameter("friend_no").trim());
				System.out.println("friend_no:"+friend_no);//被邀請者
				Integer friend_status = 0;
				System.out.println("friend_status:"+friend_status);

				FriendVO friendVO = new FriendVO();
			    

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendVO", friendVO); // ?t????J?榡??~??empVO????,?]?s?Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/addFriend.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/***************************2.?}?l?s?W???***************************************/
				FriendService friendSvc = new FriendService();
				
				friends = friendSvc.getSomeFriend(friend_no);
				System.out.println("invitedfriends.size():"+friends.size());
				
				//做判斷被邀請者是否已經是朋友或已被邀請了
				boolean friendAlready = false;
				for(int i=0; i<friends.size(); i++){
					if(friends.get(i).getFriend_no().equals(mem_no)){
						friendAlready = true;
						break;
					}
				}
				
				
				HttpSession session = req.getSession();
				
				if(friendAlready==true){//邀請者已經是朋友或已被邀請了
					
					
					session.setAttribute("inviteResult", "邀請者已經是朋友或已邀請過了");
					
					String url = "/front/personal/personal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					
					successView.forward(req, res);	
					
				}
				else{//加一筆邀請資料
					friendSvc.addFriend(friend_no ,mem_no, friend_status);
					session.setAttribute("inviteResult", "發送邀請成功");
					String url = "/front/personal/personal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);

				}
				
				
		}
	}

}
