package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.websocket.Session;



import com.member.model.*;
import com.sun.org.apache.bcel.internal.util.ByteSequence;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize=5*1024*1024, maxRequestSize=5*5*1024*1024)
public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		//*****��瑼Ｘ******//
		
		if ("login_check".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1��隢�� - 頛詨�撘�隤方���**********************/
				String acc = req.getParameter("mem_account");
				String pwd = req.getParameter("mem_password");
				
				if (acc == null || (acc.trim()).length() == 0) {
					errorMsgs.add("帳號錯誤或長度不正確！");
				}
				if (pwd == null || (pwd.trim()).length() == 0) {
					errorMsgs.add("密碼錯誤或長度不正確！");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneAccount(acc);
				if (memberVO == null){
					errorMsgs.add("帳號不存在");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				memberSvc.updateMemberOnline(memberVO.getMem_no(), 1);//��悸雿輻��銝�����
				String memberVO_acc = memberVO.getMem_account();
				String memberVO_pwd = memberVO.getMem_password();
			
				if (!memberVO_acc.equals(acc)||!memberVO_pwd.equals(pwd)){
					errorMsgs.add("帳號或密碼錯誤");
				}


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/login/login.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				
				/****蝣箄�董��隤歹�身摰ession嚗蒂�memberVO����撣唾��迂閮剝�猖ession****/
				
				  String mem_name = memberVO.getMem_name();
			      
			      HttpSession session = req.getSession();
			      session.setAttribute("memberVO", memberVO);
			      session.setAttribute("mem_name", mem_name);
			      session.setAttribute("mem_account", memberVO.getMem_account());
			    /****憒���雯���:����靘�雯���****/ 
		      
			       try {                                                        
			         String location = (String) session.getAttribute("location");	
			         System.out.println(location);
			         if (location != null) {
			           session.removeAttribute("location"); 
			           
			           res.sendRedirect(req.getContextPath()+location);  
		           
			           return;
			         }
			       }catch (Exception ignored) { }
			      res.sendRedirect(req.getContextPath()+"/index.jsp");  //*撌乩��3: (-->憒靘�雯���:����index.jsp)  
			    
			   

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/login/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 靘select_page.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("隢撓�撣唾��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��撣唾�撘�迤蝣�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************2.���閰Ｚ���*****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO_onemember = memberSvc.getOneMember(mem_no);
				if (memberVO_onemember == null) {
					errorMsgs.add("��鞈��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)*************/
				req.setAttribute("memberVO_onemember", memberVO_onemember); // 鞈�澈����emberVO�隞�,摮req
				String url = "/front/personal/aboutMe.jsp";//��悸雿輻�����
				RequestDispatcher successView = req.getRequestDispatcher(url); // ����漱 listOneMember.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜�����:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 靘listAllMember.jsp�����

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.��隢��****************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				
				/***************************2.���閰Ｚ���****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mem_no);
								
				/***************************3.�閰Ｗ���,皞��漱(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 鞈�澈����emberVO�隞�,摮req
				String url = "update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ����漱 update_member_input.jsp
				successView.forward(req, res);

				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("�瘜���耨������:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.��隢�� - 頛詨�撘�隤方���**********************/
				
//				*****��session銝衫emberVO
				MemberVO memberVO= (MemberVO)req.getSession().getAttribute("memberVO");

				Integer mem_no= memberVO.getMem_no();

				String mem_password = req.getParameter("mem_password").trim();
			    if(mem_password.isEmpty()){
			    	errorMsgs.add("請輸入密碼!");
			    }
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name.isEmpty()){
					errorMsgs.add("請輸入姓名!");
				}
				
			
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/update/update.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}

				String mem_nickname = req.getParameter("mem_nickname").trim();
				if (mem_nickname == null){
					mem_nickname="無";
				}
		
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();
		
				String mem_zipcode = req.getParameter("mem_zipcode").trim();
				if(mem_zipcode.isEmpty()){
					mem_zipcode="0";
				}
				String mem_city = req.getParameter("mem_city").trim();
				if(mem_city.isEmpty()){
					mem_city="0";
				}
				String mem_district = req.getParameter("mem_district").trim();
				if(mem_district.isEmpty()){
					mem_district="0";
				}
				String mem_address = req.getParameter("mem_address").trim();
				if(mem_address.isEmpty()){
					mem_address="0";
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone.isEmpty()){
					mem_phone="0";
				}
				String mem_cellphone = req.getParameter("mem_cellphone").trim();
				if(mem_cellphone.isEmpty()){
					mem_cellphone="0";
				}
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email.isEmpty()){
					mem_email="0";
				}
				String mem_skill = req.getParameter("mem_skill").trim();
				if(mem_skill.isEmpty()){
					mem_skill="0";
				}
				String mem_hobby = req.getParameter("mem_hobby").trim();
				if(mem_hobby.isEmpty()){
					mem_hobby="0";
				}
				

				String mem_intro = req.getParameter("mem_intro").trim();
				if(mem_intro.isEmpty()){
					mem_intro="0";
				}
		
				
				Integer mem_relationship = new Integer(req.getParameter("mem_relationship").trim());
				if(mem_relationship==0){
					mem_relationship=0;
				}
				
								
				memberVO.setMem_no(mem_no);

				memberVO.setMem_password(mem_password);

				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);

				memberVO.setMem_photo(mem_photo);

				memberVO.setMem_zipcode(mem_zipcode);
				memberVO.setMem_city(mem_city);
				memberVO.setMem_district(mem_district);
				memberVO.setMem_address(mem_address);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_cellphone(mem_cellphone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_skill(mem_skill);
				memberVO.setMem_hobby(mem_hobby);
				memberVO.setMem_relationship(mem_relationship);

				memberVO.setMem_intro(mem_intro);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�emberVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/update/update.jsp");
					failureView.forward(req, res);
					return; //蝔�葉�
				}
				
				/***************************2.���耨�鞈��*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember2(mem_no,mem_password,mem_name,mem_nickname,mem_photo,mem_zipcode,mem_city,mem_district,mem_address,mem_phone,mem_cellphone,mem_email,mem_skill,mem_hobby,mem_relationship,mem_intro);
				
				/***************************3.靽格摰��,皞��漱(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 鞈�澈update�����,甇�蝣箇��emberVO�隞�,摮req

			    res.sendRedirect(req.getContextPath()+"/index.jsp");  //*撌乩��3: (-->憒靘�雯���:����index.jsp)  

				/***************************�隞���隤方���*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/update/update.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 靘addMember.jsp�����  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.��隢�� - 頛詨�撘�隤方���*************************/

				String mem_account = req.getParameter("mem_account").trim();
				if(mem_account.isEmpty()){
			    	errorMsgs.add("請輸入帳號!");
			    }

				String mem_password = req.getParameter("mem_password").trim();
			    if(mem_password.isEmpty()){
			    	errorMsgs.add("請輸入密碼!");
			    }
				
				String mem_name = req.getParameter("mem_name").trim();
				if(mem_name.isEmpty()){
					errorMsgs.add("請輸入姓名!");
				}
				
				java.sql.Date mem_birthday = null;				
				mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());	
				if(mem_account.isEmpty()) {
					errorMsgs.add("請輸入日期!");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/register/register.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				MemberService memberSvc2 = new MemberService();
				MemberVO memberVO2 = memberSvc2.getOneAccount(mem_account);
				if (memberVO2!=null){
					errorMsgs.add("已有該帳號，請換帳號名稱");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/register/register.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				
				java.sql.Date mem_regist_date = null;
				mem_regist_date=new java.sql.Date(System.currentTimeMillis());

				String mem_nickname = req.getParameter("mem_nickname").trim();
				if (mem_nickname == null){
					mem_nickname="無";
				}
		
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();

				
				String mem_idcard = req.getParameter("mem_idcard").trim();
				if(mem_idcard.isEmpty()){
					mem_idcard="0";
				}
				String mem_sex = req.getParameter("mem_sex").trim();
			
				String mem_zipcode = req.getParameter("mem_zipcode").trim();
				if(mem_zipcode.isEmpty()){
					mem_zipcode="0";
				}
				String mem_city = req.getParameter("mem_city").trim();
				if(mem_city.isEmpty()){
					mem_city="0";
				}
				String mem_district = req.getParameter("mem_district").trim();
				if(mem_district.isEmpty()){
					mem_district="0";
				}
				String mem_address = req.getParameter("mem_address").trim();
				if(mem_address.isEmpty()){
					mem_address="0";
				}
				String mem_phone = req.getParameter("mem_phone").trim();
				if(mem_phone.isEmpty()){
					mem_phone="0";
				}
				String mem_cellphone = req.getParameter("mem_cellphone").trim();
				if(mem_cellphone.isEmpty()){
					mem_cellphone="0";
				}
				String mem_email = req.getParameter("mem_email").trim();
				if(mem_email.isEmpty()){
					mem_email="0";
				}
				String mem_skill = req.getParameter("mem_skill").trim();
				if(mem_skill.isEmpty()){
					mem_skill="0";
				}
				String mem_hobby = req.getParameter("mem_hobby").trim();
				if(mem_hobby.isEmpty()){
					mem_hobby="0";
				}
				
				String mem_redid = req.getParameter("mem_redid").trim();			
				if(mem_redid.isEmpty()||mem_redid==null){
					mem_redid="0";
				}

				String mem_intro = req.getParameter("mem_intro").trim();
			
				if(mem_intro.isEmpty()){
					mem_intro="0";
				}
		
				
				Integer mem_relationship = new Integer(req.getParameter("mem_relationship").trim());
				
				if(mem_relationship==0){
					mem_relationship=0;
				}

				Integer mem_right = 0;
				Integer mem_level = 0;
				Integer mem_status = 0;
				Integer mem_balance = 0;
				

				
				MemberVO memberVO = new MemberVO();
				memberVO.setMem_account(mem_account);
				memberVO.setMem_password(mem_password);
				memberVO.setMem_regist_date(mem_regist_date);
				memberVO.setMem_name(mem_name);
				memberVO.setMem_nickname(mem_nickname);
				memberVO.setMem_birthday(mem_birthday);
				memberVO.setMem_photo(mem_photo);
				memberVO.setMem_idcard(mem_idcard);
				memberVO.setMem_sex(mem_sex);
				memberVO.setMem_zipcode(mem_zipcode);
				memberVO.setMem_city(mem_city);
				memberVO.setMem_district(mem_district);
				memberVO.setMem_address(mem_address);
				memberVO.setMem_phone(mem_phone);
				memberVO.setMem_cellphone(mem_cellphone);
				memberVO.setMem_email(mem_email);
				memberVO.setMem_skill(mem_skill);
				memberVO.setMem_hobby(mem_hobby);
				memberVO.setMem_relationship(mem_relationship);
				memberVO.setMem_right(mem_right);
				memberVO.setMem_intro(mem_intro);
				memberVO.setMem_level(mem_level);
				memberVO.setMem_status(mem_status);
				memberVO.setMem_redid(mem_redid);
				memberVO.setMem_balance(mem_balance);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // ���撓��撘隤斤�emberVO�隞�,銋�req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/member/register/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.���憓���***************************************/
				
				MemberService memberSvc = new MemberService();
				
				memberVO = memberSvc.addMember(mem_account, mem_password, mem_regist_date, mem_name, mem_nickname, mem_birthday, mem_photo, mem_idcard, mem_sex, mem_zipcode, mem_city, mem_district, mem_address, mem_phone, mem_cellphone, mem_email, mem_skill, mem_hobby, mem_relationship, mem_right, mem_intro, mem_level, mem_status, mem_redid,mem_balance);

				
				/***************************3.�憓���,皞��漱(Send the Success view)***********/
				res.sendRedirect(req.getContextPath()+"/index.jsp");				
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/member/register/register.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 靘listAllMember.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.��隢��***************************************/
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				
				/***************************2.����鞈��***************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.deleteMember(mem_no);
				
				/***************************3.��摰��,皞��漱(Send the Success view)***********/								
				String url = "/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �������,頧漱���������雯���
				successView.forward(req, res);
				
				/***************************�隞���隤方���**********************************/
			} catch (Exception e) {
				errorMsgs.add("��鞈�仃���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update_right".equals(action)) {
			
			String msg = "權限更改成功";
			try {

							Integer mem_no = new Integer(req.getParameter("mem_no").trim());
							Integer mem_status = new Integer(req.getParameter("mem_status").trim());

							MemberService memberSvc = new MemberService();
							memberSvc.updateMember3(mem_no,mem_status);
							
			} catch (Exception e) {
				msg="權限更改成功";
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
