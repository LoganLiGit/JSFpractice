package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.article.model.*;
import com.friend.model.FriendService;
import com.friend.model.FriendVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.reply.model.ReplyService;
import com.reply.model.ReplyVO;
import com.report.model.ReportService;
import com.store.model.*;


public class ArticleServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡����~�B�z**********************/
				String str = req.getParameter("article_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���O�s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				Integer article_no = null;
				try {
					article_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���O�s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
				if (articleVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // ��Ʈw��X��empVO����,�s�Jreq
				String url = "/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺��~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k��o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // ��Ʈw��X��empVO����,�s�Jreq
				String url = "/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺��~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k��o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/ListAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			List<ReplyVO> replies = new LinkedList<ReplyVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			
				/***************************1.�����ШD�Ѽ� - ��J�榡����~�B�z**********************/
				Integer article_no = new Integer(req.getParameter("article_no").trim());
				String store_name = req.getParameter("store_name").trim();//前端送來店家名稱 在這立刻轉成店家編號再存入資料庫
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getStoreNo(store_name);	
				
				Integer store_no = storeVO.getStore_no();//轉換成功
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				String article_content = req.getParameter("article_content").trim();
				
				System.out.println("1:"+article_content);
				String article_title = req.getParameter("article_title").trim();
				
				java.sql.Timestamp article_create = java.sql.Timestamp.valueOf(req.getParameter("article_create").trim());
				
				java.sql.Timestamp article_modify = null;
				article_modify = new java.sql.Timestamp(System.currentTimeMillis());
				
				String article_status = req.getParameter("article_status").trim();
				Integer article_score = new Integer(req.getParameter("article_score").trim());
				Integer article_click = new Integer(req.getParameter("article_click").trim());
				Integer article_replies = new Integer(req.getParameter("article_replies").trim());

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticle_no(article_no);
				articleVO.setStore_no(store_no);
				articleVO.setMem_no(mem_no);
				articleVO.setStore_name(store_name);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_create(article_create);
				articleVO.setArticle_modify(article_modify);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_score(article_score);
				articleVO.setArticle_click(article_click);
				articleVO.setArticle_replies(article_replies);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡��~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}

				
				/***************************2.�}�l�ק���*****************************************/
				ArticleService articleSvc = new ArticleService();
				System.out.println("2:"+article_content);		
				articleSvc.updateArticle(article_no, store_no, mem_no, store_name, article_content, article_title,article_create, article_modify,article_status,article_score,article_click,article_replies);
				articleVO = articleSvc.getOneArticle(article_no);
				System.out.println("3:"+articleVO.getArticle_content());
				ReplyService replySvc = new ReplyService();//抓某篇食記的留言
				replies = replySvc.getSomeReply(article_no);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("replies", replies);
				req.setAttribute("articleVO", articleVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/front/personal/singleArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺��~�B�z*************************************/
	
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			List<ArticleVO> articles = new LinkedList<ArticleVO>();
			List<StoreVO> stores = new LinkedList<StoreVO>();//裝某人的食記關聯店家
			List<ArticleVO> allarticles = new LinkedList<ArticleVO>();//裝所有的食記
			List<ArticleVO> rankedArticles = new LinkedList<ArticleVO>();//裝排名的食記
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***********************1.�����ШD�Ѽ� - ��J�榡����~�B�z*************************/
				String store_name = req.getParameter("store_name").trim();//前端送來店家名稱 在這立刻轉成店家編號再存入資料庫
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getStoreNo(store_name);	
				System.out.println(storeVO.equals(null));
				
				Integer store_no = storeVO.getStore_no();//轉換成功
				System.out.println(store_no);
				
				Integer mem_no = new Integer(req.getParameter("mem_no").trim());
				Integer article_score = 0;
				try {
					article_score = new Integer(req.getParameter("article_score").trim());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					errorMsgs.add("請輸入評分!");
				}
				
				String article_status = req.getParameter("article_status").trim();
				
				
				java.sql.Timestamp article_create = null;
				article_create = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				java.sql.Timestamp article_modify = null;
				article_modify = new java.sql.Timestamp(System.currentTimeMillis());
				
				
				String article_title = req.getParameter("article_title").trim();
				if(article_title==null){
					errorMsgs.add("請輸入標題!");
				}
				String article_content = req.getParameter("article_content").trim();
				if(article_content==null){
					errorMsgs.add("請輸入內容!");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/personal/myArticle.jsp");
					failureView.forward(req, res);
					return;//蝔�葉�
				}
				Integer article_click = new Integer(req.getParameter("article_click").trim());
				Integer article_replies = new Integer(req.getParameter("article_replies").trim());
				
				
				System.out.println("store_no"+store_no);
				System.out.println("mem_no"+mem_no);
				System.out.println("store_name"+store_name);
				System.out.println("article_score"+article_score);
				System.out.println("article_status"+article_status);
				System.out.println("article_create"+article_create);
				System.out.println("article_modify"+article_modify);
				System.out.println("article_title"+article_title);
				System.out.println("article_content"+article_content);
				System.out.println("article_click"+article_click);
				

				ArticleVO articleVO = new ArticleVO();
				
				articleVO.setStore_no(store_no);
				articleVO.setMem_no(mem_no);
				articleVO.setStore_name(store_name);
				articleVO.setArticle_content(article_content);
				articleVO.setArticle_title(article_title);
				articleVO.setArticle_create(article_create);
				articleVO.setArticle_modify(article_modify);
				articleVO.setArticle_status(article_status);
				articleVO.setArticle_score(article_score);
				articleVO.setArticle_click(article_click);
				articleVO.setArticle_replies(article_replies);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // �t����J�榡��~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/index.jsp");
					failureView.forward(req, res);
					
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				ArticleService articleSvc = new ArticleService();
				articleVO = articleSvc.addArticle(store_no, mem_no, store_name, article_content, article_title, article_create, article_modify, article_status, article_score,article_click,article_replies);
				articles = articleSvc.getSomeArticle(mem_no);
				
				for(int i=0; i<articles.size(); i++){//利用某人的食記再去抓實際的相關店家資訊,再存到另一個list
					stores.add(storeSvc.getOneStore(articles.get(i).getStore_no()));
				}
				
				allarticles = articleSvc.getAll();//抓所有食記
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
				ArticleVO latestArticle = articleSvc.getlatestArticle(mem_no);
				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				HttpSession session = req.getSession();
				session.setAttribute("articles", articles);// 放新增後的食記
				session.setAttribute("stores", stores);// 放某人的新增後食記相關店家資訊
				session.setAttribute("latestArticle", latestArticle);// 放新增後的最新食記
				session.setAttribute("allarticles", allarticles);// 放所有人食記
				session.setAttribute("rankedArticles", rankedArticles);// 放所有人食記
				String url = "/front/personal/personal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺��~�B�z**********************************/
			
		}
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			List<ArticleVO> articles = new LinkedList<ArticleVO>();
			List<ArticleVO> allarticles = new LinkedList<ArticleVO>();//裝所有的食記
			List<ArticleVO> rankedArticles = new LinkedList<ArticleVO>();//裝排名的食記
			int step=0;
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer article_no = new Integer(req.getParameter("article_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				
				/***************************2.�}�l�R�����***************************************/
				ReplyService replySvc = new ReplyService();
				ReportService reportSvc = new ReportService();
				System.out.println("step:"+step);
				if(step==0){
					System.out.println("article_no:"+article_no);
					replySvc.deleteReplyByArticle(article_no);
					reportSvc.deleteReportByArticle_no(article_no);
					System.out.println("+++++++++++++++++++++++++++++");
					step++;
				}
				
				
				ArticleService articleSvc = new ArticleService();
				articleSvc.deleteArticle(article_no);
				articles = articleSvc.getSomeArticle(mem_no);
				allarticles = articleSvc.getAll();//抓所有食記
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
				
				ArticleVO latestArticle = articleSvc.getlatestArticle(mem_no);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/		
				HttpSession session = req.getSession();
				session.setAttribute("articles", articles);// 放刪除後的食記
				session.setAttribute("latestArticle", latestArticle);// 放刪除後的最新食記
				session.setAttribute("allarticles", allarticles);// 放所有人食記
				session.setAttribute("rankedArticles", rankedArticles);// 放所有人食記
				
				String url = "/front/personal/personal.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺��~�B�z**********************************/
			
		}
		
		if ("myArticle".equals(action)) { //personal頁面轉到someStore頁面
			
			List<StoreVO> allstores = new LinkedList<StoreVO>();
			try {		
				StoreService storeSvc = new StoreService();
				allstores = storeSvc.getAll();
				HttpSession session = req.getSession();
				session.setAttribute("allstores", allstores);
				session.setAttribute("searchedStores", allstores);
				
				String url = "/front/personal/someStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺��~�B�z**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("myArticle2".equals(action)) { //someStore頁面轉到myArticle頁面
			
			try {		
				Integer store_no = new Integer(req.getParameter("store_no"));
				
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				System.out.println(storeVO.getStore_name());
				HttpSession session = req.getSession();
				session.setAttribute("storeVO", storeVO);
				
				
				String url = "/front/personal/myArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺��~�B�z**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("updateMyArticle".equals(action)) { //singleStore頁面轉到myArticle2頁面
			
			try {		
					
				
				int article_no = new Integer(req.getParameter("article_no"));
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(article_no);
				
				req.setAttribute("articleVO", articleVO);
				String url = "/front/personal/myArticle2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************��L�i�઺��~�B�z**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getSearchedStoreDisplay".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			List<StoreVO> searchedStores = new LinkedList<StoreVO>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
				/***************************1.�����ШD�Ѽ� - ��J�榡����~�B�z**********************/
				String store_key = req.getParameter("store_key");
				if (store_key == null || (store_key.trim()).length() == 0) {
					errorMsgs.add("�п�J�|��s��");
				}
				System.out.println(store_key);
				
				String optradio = req.getParameter("optradio");
				System.out.println(optradio);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				StoreService storeSvc = new StoreService();
				
				switch(optradio){
				case "storeName":
					searchedStores = storeSvc.getSearchedByStoreName(store_key);
					break;
				case "storeAddr":
					searchedStores = storeSvc.getSearchedByStoreAddress(store_key);
					break;
				case "foodType":
					searchedStores = storeSvc.getSearchedByFoodType(store_key);
					break;
				default:System.out.println("沒有選擇");
					
			}
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("searchedStores", searchedStores);
				System.out.println("searchedStores:"+searchedStores.size());
				String url = "/front/personal/someStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺��~�B�z*************************************/

		}
		
		if ("insertArticleReport".equals(action)) { // 檢舉食記
			
			List<String> errorMsgs = new LinkedList<String>();
			
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			/***********************1.�����ШD�Ѽ� - ��J�榡����~�B�z*************************/
			String mem_name = req.getParameter("mem_name").trim();
			
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMemNo(mem_name);
			Integer mem_no = memberVO.getMem_no();
			
			Integer article_no = new Integer(req.getParameter("article_no").trim());
			String report_content = req.getParameter("report_content").trim();
			Integer report_status = new Integer(req.getParameter("report_status").trim());
			
			System.out.println("mem_no:"+mem_no);
			System.out.println("article_no:"+article_no);
			System.out.println("report_content:"+report_content);
			System.out.println("report_status:"+report_status);
			


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
				
				return;
			}
			
			/***************************2.�}�l�s�W���***************************************/
			ReportService reportSvc = new ReportService();
			reportSvc.addReport2(mem_no, article_no, report_content, report_status);
				
			
		}
		
		
	}
}
