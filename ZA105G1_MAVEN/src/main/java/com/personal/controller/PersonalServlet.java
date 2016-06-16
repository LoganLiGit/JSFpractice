package com.personal.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.album.model.*;
import com.article.model.*;
import com.friend.model.*;
import com.member.model.*;
import com.pocket.model.*;
import com.reply.model.ReplyService;
import com.reply.model.ReplyVO;
import com.store.model.*;
import com.talk.model.TalkService;
import com.talk.model.TalkVO;

@MultipartConfig
public class PersonalServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		
		if ("getPersonal_Display".equals(action)) { 
			System.out.println("123");
			List<String> errorMsgs = new LinkedList<String>();
			List<FriendVO> friends = new LinkedList<FriendVO>();//裝當前使用者瀏覽其他使用者的朋友
			List<FriendVO> userfriends = new LinkedList<FriendVO>();//裝當前使用者(登入者)的朋友
			List<ArticleVO> articles = new LinkedList<ArticleVO>();//裝某人的食記
			List<ArticleVO> articleRepliesNum = new LinkedList<ArticleVO>();//裝某人的各篇食記的留言數
			List<StoreVO> stores = new LinkedList<StoreVO>();//裝某人的食記關聯店家
			List<ArticleVO> allarticles = new LinkedList<ArticleVO>();//裝所有的食記
			List<ArticleVO> rankedArticles = new LinkedList<ArticleVO>();//裝排名的食記
			List<PocketVO> pockets = new LinkedList<PocketVO>();//裝口袋名單
			List<StoreVO> keepStores = new LinkedList<StoreVO>();//裝收藏店家
			List<MemberVO> allMembers = new LinkedList<MemberVO>();//所有會員
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);


				/***************************1.??????D??? - ??J?榡????~?B?z**********************/
				String str = req.getParameter("mem_no");//抓非當前使用者(登入者)的的編號
				if (str == null || (str.trim()).length() == 0) {
					
					String str2 = req.getParameter("mem_name");//以會員名稱抓會員編號(搜尋使用者bar)
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMemNo(str2);
					str = String.valueOf(memberVO.getMem_no());				
				}
				
				String str3 = req.getParameter("login_mem_no");//抓當前使用者(登入者)的的編號
				
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
				
				Integer login_mem_no = null;
				try {
					login_mem_no = new Integer(str3);
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
				MemberService memberSvc = new MemberService();
				MemberVO memberVO2 = memberSvc.getOneAccount2(mem_no);
				
				FriendService friendSvc = new FriendService();
				friends = friendSvc.getSomeFriend(mem_no);//裝非當前使用者(登入者)的朋友
				
				
				userfriends = friendSvc.getSomeFriend(login_mem_no);//裝當前使用者(登入者)的朋友
				
				
				ArticleService articleSvc = new ArticleService();
				articles = articleSvc.getSomeArticle(mem_no);//抓某人的所有食記
				
				
				
					/*抓某人的所有食記,再抓每篇食記編號,再以食記編號抓該篇食記有幾篇留言,再存進 articleRepliesNum<Integer>*/
				
				
				
				
				
				StoreService storeSvc = new StoreService();
				for(int i=0; i<articles.size(); i++){//利用某人的食記再去抓實際的相關店家資訊,再存到另一個list
					stores.add(storeSvc.getOneStore(articles.get(i).getStore_no()));
				}
				
				allarticles = articleSvc.getAll();//抓所有食記
				System.out.println("allarticles.size():"+allarticles.size());
				if(allarticles.size()!=0){
					if(allarticles.size()<=5){
						for(int i=0; i<allarticles.size(); i++){//抓到所有食記取出前n筆丟入另一個list
							rankedArticles.add(i, allarticles.get(i));
						}
					}
					else{
						for(int i=0; i<5; i++){//抓到所有食記取出前n筆丟入另一個list
							rankedArticles.add(i, allarticles.get(i));
						}
					}
					
				}
				
				
				ArticleVO latestArticle = articleSvc.getlatestArticle(mem_no);//抓某人的最新食記
				
				
				
				PocketService pocketSvc = new PocketService();
				pockets = pocketSvc.getKeepStores(mem_no);//抓某人的口袋名單
				
				//承上步驟,抓某人的口袋名單,利用店家編號得到店家詳細資料,再裝進keepStores裡面
				for(int i=0; i<pockets.size(); i++){
					keepStores.add(i, storeSvc.getOneStore(pockets.get(i).getStore_no()));
				}
				
				MemberService memberSvc1 = new MemberService();
				allMembers = memberSvc1.getAll();

				
				/***************************3.?d?????,??????(Send the Success view)*************/			
				HttpSession session = req.getSession();
				session.setAttribute("memberVO2", memberVO2);
				session.setAttribute("friends", friends);//裝非當前使用者(登入者)的朋友
				session.setAttribute("userfriends", userfriends);//裝當前使用者(登入者)的朋友
				session.setAttribute("articles", articles);// 放某人的食記
				session.setAttribute("stores", stores);// 放某人的食記相關店家資訊
				session.setAttribute("latestArticle", latestArticle);// 放某人的最新食記
				session.setAttribute("allarticles", allarticles);// 放所有人食記
				session.setAttribute("articleRepliesNum", articleRepliesNum);// 放所有人食記
				session.setAttribute("rankedArticles", rankedArticles);// 放所有人排名食記
				session.setAttribute("keepStores", keepStores);// 放某人的收藏店家
				session.setAttribute("allMembers", allMembers);// 放某人的收藏店家
				
				System.out.println(session.getId());
				if(!memberVO2.equals(null)){
					String url = "/front/personal/personal.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ???\??? listOneEmp.jsp
					successView.forward(req, res);
				}
				

				/***************************??L?i????~?B?z*************************************/

		}
		
		
		if ("get_Album".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			List<AlbumVO> myAlbum = new LinkedList<AlbumVO>();//裝當前使用者瀏覽其他使用者的朋友
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙箭嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				AlbumService albumSvc = new AlbumService();
				myAlbum = albumSvc.getAll(mem_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("myAlbum", myAlbum);
				String url = "/front/personal/album.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneMember.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭o嚙踝蕭嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("get_SomeOne_All_Article".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("mem_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙箭嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				Integer mem_no = null;
				try {
					mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//嚙緹嚙踝蕭嚙踝蕭嚙稻
				}
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				HttpSession session = req.getSession();
				
				String url = "/front/personal/SomeOneAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneMember.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙盤嚙糊嚙踝蕭o嚙踝蕭嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert_photo".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			List<AlbumVO> myAlbum = new LinkedList<AlbumVO>();//裝當前使用者瀏覽其他使用者的朋友
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("photo_mem_no");
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = null;
				mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();
				
				String photo_title = req.getParameter("photo_title");
				String photo_description = req.getParameter("photo_description");
				
				System.out.println("str:"+str);
				System.out.println(mem_photo.equals(null));
				System.out.println("photo_title:"+photo_title);
				System.out.println("photo_description:"+photo_description);
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙箭嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				
				
				Integer photo_mem_no = null;
				try {
					photo_mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
			
				
				
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				AlbumService albumSvc = new AlbumService();
				albumSvc.addAlbum(photo_mem_no,mem_photo,photo_title,photo_description);
				
				myAlbum = albumSvc.getAll(photo_mem_no);
				System.out.println("myAlbum.size():"+myAlbum.size());
				// Send the use back to the form, if there were errors
			
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("myAlbum", myAlbum);
				String url = "/front/personal/album.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneMember.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			
		}
		
		if ("change_photo".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("photo_mem_no");
				
				Part part = req.getPart("mem_photo");
				InputStream in = part.getInputStream();
				byte[] mem_photo = null;
				mem_photo = new byte[in.available()];
				in.read(mem_photo);
				in.close();
				
				System.out.println("str:"+str);
				System.out.println(mem_photo.equals(null));
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙請選蕭J嚙箭嚙踝蕭");
				}
				// Send the use back to the form, if there were errors
				
				
				Integer photo_mem_no = null;
				try {
					photo_mem_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
			
				
				
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				MemberService memberSvc = new MemberService();
				
				
				memberSvc.changePhoto(mem_photo,photo_mem_no);
				// Send the use back to the form, if there were errors
				MemberVO memberVO_onemember = memberSvc.getOneMember(photo_mem_no);
				req.setAttribute("memberVO_onemember", memberVO_onemember);
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				
				String url = "/front/personal/aboutMe.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneMember.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/

		}
		
		if ("delete_photo".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			List<AlbumVO> myAlbum = new LinkedList<AlbumVO>();//裝當前使用者瀏覽其他使用者的朋友
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***************************1.嚙踝蕭嚙踝蕭嚙請求嚙諸潘蕭 - 嚙踝蕭J嚙賣式嚙踝蕭嚙踝蕭~嚙畿嚙緲**********************/
				String str = req.getParameter("photo_no");
				String str2 = req.getParameter("mem_no");
					
				
				
				Integer photo_no = null;
				try {
					photo_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				Integer mem_no = null;
				try {
					mem_no = new Integer(str2);
				} catch (Exception e) {
					errorMsgs.add("嚙罵嚙踝蕭b嚙踝蕭嚙賣式嚙踝蕭嚙踝蕭嚙確");
				}
				// Send the use back to the form, if there were errors
			
				
				
				
				/***************************2.嚙罷嚙締嚙範嚙賠賂蕭嚙�****************************************/
				AlbumService albumSvc = new AlbumService();
				albumSvc.deleteAlbum(photo_no);
				
				myAlbum = albumSvc.getAll(mem_no);
				System.out.println("myAlbum.size():"+myAlbum.size());
				// Send the use back to the form, if there were errors
			
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("myAlbum", myAlbum);
				String url = "/album.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭嚙穀嚙踝蕭嚙�listOneMember.jsp
				successView.forward(req, res);

				/***************************嚙踝蕭L嚙箠嚙賞的嚙踝蕭~嚙畿嚙緲*************************************/
			
		}
		
		if("insert_message".equals(action)){
			
			
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			Integer friend_no = new Integer(req.getParameter("friend_no"));
			
			java.sql.Timestamp talk_time = null;
			talk_time = new java.sql.Timestamp(System.currentTimeMillis());
			
			String talk_note = req.getParameter("message");
			Integer read_status = 0;
			
			System.out.println(talk_time);
			
			MemberService memberSvc = new MemberService();
			MemberVO memberVO1 = memberSvc.getOneMember(mem_no);//查發送者的名子
			MemberVO memberVO2 = memberSvc.getOneMember(friend_no);//查接收者者的名子
			TalkService talkSvc = new TalkService();
			
			talkSvc.addTalk(mem_no, memberVO1.getMem_name(), friend_no, memberVO2.getMem_name(), talk_time, talk_note, read_status);
			
			System.out.println("mem_no:"+mem_no);
			System.out.println("friend_no:"+friend_no);
			System.out.println("talk_note:"+talk_note);
		}
		
		if("get_message".equals(action)){
			
			List<TalkVO> chatRecords = new LinkedList<TalkVO>();//裝聊天紀錄
			
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			Integer friend_no = new Integer(req.getParameter("friend_no"));
			System.out.println("mem_no:"+mem_no);
			System.out.println("friend_no:"+friend_no);
			
			
			
			TalkService talkSvc = new TalkService();
			chatRecords = talkSvc.getTalk(mem_no, friend_no);
			talkSvc.updateAllRecords(friend_no,mem_no);
			
			
			ListIterator<TalkVO> l = chatRecords.listIterator();
			
			JSONArray array = new JSONArray();
			
			JSONObject obj = new JSONObject();
			
			int index=0;
			while(l.hasNext()&&index<chatRecords.size()){
				obj.put("mem_no", chatRecords.get(index).getMem_no());
				obj.put("sender", chatRecords.get(index).getSender());
				obj.put("friend_no", chatRecords.get(index).getFriend_no());
				obj.put("receiver", chatRecords.get(index).getReceiver());
				obj.put("talk_note", chatRecords.get(index).getTalk_note());
				index++;
				array.add(obj);
				
			}
			
			res.setContentType("text/plain");
		    res.setCharacterEncoding("UTF-8");
		    //System.out.println("json:"+array.toString());
		    
		    PrintWriter out = res.getWriter();
		    out.write(array.toString());
		    out.flush();
			out.close();
			
			
		}
		
		if("update_message_status".equals(action)){
			
			List<TalkVO> chatRecords = new LinkedList<TalkVO>();//裝聊天紀錄
			
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			Integer friend_no = new Integer(req.getParameter("friend_no"));
			Integer read_status = 1;
			
			
			TalkService talkSvc = new TalkService();
			talkSvc.updateTalk(friend_no,mem_no,read_status);

			
		}
		
		if("getOnlineFriend".equals(action)){

			List<FriendVO> userfriends = new LinkedList<FriendVO>();//裝當前使用者(登入者)的朋友
			Integer mem_no = new Integer(req.getParameter("mem_no"));
			//System.out.println(mem_no);
			FriendService friendSvc = new FriendService();
			userfriends = friendSvc.getSomeFriend(mem_no);
			
			ListIterator<FriendVO> f = userfriends.listIterator();
			
			
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			MemberService memberSvc = new MemberService();
			
			
			
			int index=0;
			while(f.hasNext()&&index<userfriends.size()){
				obj.put("mem_no", memberSvc.getOneMember(userfriends.get(index).getFriend_no()).getMem_no());
				obj.put("mem_status", memberSvc.getOneMember(userfriends.get(index).getFriend_no()).getMem_status());
				index++;
				array.add(obj);
			}
			
			res.setContentType("text/plain");
		    res.setCharacterEncoding("UTF-8");
		    //System.out.println("json:"+array.toString());
		    
		    PrintWriter out = res.getWriter();
		    out.write(array.toString());
		    out.flush();
			out.close();
		}
		
		if("getUnReadMessage".equals(action)){

			List<FriendVO> userfriends = new LinkedList<FriendVO>();//裝當前使用者(登入者)的朋友
			Integer mem_no = new Integer(req.getParameter("mem_no"));//登入者編號
			//System.out.println(mem_no);
			FriendService friendSvc = new FriendService();
			userfriends = friendSvc.getSomeFriend(mem_no);//抓到登入者的朋友有誰
			
			ListIterator<FriendVO> f = userfriends.listIterator();
			
			
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			MemberService memberSvc = new MemberService();
			TalkService talkSvc = new TalkService();
			
			
			int index=0;
			while(f.hasNext()&&index<userfriends.size()){
				obj.put("mem_no", memberSvc.getOneMember(userfriends.get(index).getFriend_no()).getMem_no());//拿到朋友的會員編號
				obj.put("unreadMessageNum", talkSvc.getUnreadMessageNum(mem_no,  memberSvc.getOneMember(userfriends.get(index).getFriend_no()).getMem_no()));
				//以第一欄朋友會員編號跟第二欄我的會員編號查詢朋友送給我的未讀訊息有幾筆
				
				index++;
				array.add(obj);
			}
			
			res.setContentType("text/plain");
		    res.setCharacterEncoding("UTF-8");
		    //System.out.println("json:"+array.toString());
		    
		    PrintWriter out = res.getWriter();
		    out.write(array.toString());
		    out.flush();
			out.close();
		}	
	}
}
