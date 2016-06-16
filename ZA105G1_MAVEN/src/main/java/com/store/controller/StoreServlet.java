package com.store.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.pocket.model.*;
import com.store.model.*;
import com.store.pic.model.*;
import com.store.pic.model.*;

/**
 * Servlet implementation class StoreServlet
 */
// @WebServlet("/StoreServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("listStore_pics_ByStore_no_A".equals(action)
				|| "listStore_pics_ByStore_no_B".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				StoreService storeSvc = new StoreService();

				Set<Store_picVO> set = storeSvc
						.getStore_picsByStore_no(store_no);

				StoreVO storeVO = storeSvc.getOneStore(store_no);
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				req.setAttribute("listStore_pics_ByStore_no", set); // ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭set���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest

				String url = null;
				if ("listStore_pics_ByStore_no_A".equals(action)) {
					req.setAttribute("storeVO", storeVO);
					url = "/store/listOneStore.jsp";
				} // ���雓�鞊莎�揭���雓����雓�鞊莎�揭���雓� dept/listEmps_ByDeptno.jsp
				else if ("listStore_pics_ByStore_no_B".equals(action))
					url = "/store/listAllStore.jsp"; // ���雓�鞊莎�揭���雓����雓�鞊莎�揭���雓�
														// dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("store_detail".equals(action)) { // ���雓�嚙踐���蕭謚啾揭select_page.jsp���雓�鞊莎�揭���雓�嚙踝蕭豯株��蕭
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱� - ���雓�鞊莎�揭J���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 **********************/
				String str = req.getParameter("store_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("���雓�嚙踐���嚙踐揭J���雓�鞊莎�揭���雓嚙踐����蕭��蕭�����蕭謖鞊莎�揭");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���雓�輔���雓�鞊莎�揭���雓�鞊莎�揭���雓嚙踝蕭
				}

				Integer store_no = null;
				try {
					store_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("���雓�鞊莎�揭���雓嚙踐����蕭��蕭�����蕭謖鞊莎�揭���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�鞊莎�揭���雓�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���雓�輔���雓�鞊莎�揭���雓�鞊莎�揭���雓嚙踝蕭
				}
				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				Set<Store_picVO> store_picVO = storeSvc
						.getStore_picsByStore_no(store_no);
				if (storeVO == null) {
					errorMsgs.add("���雓�嚙踝蕭��雓嚙踝����蕭謖鞊莎�揭���雓�");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���雓�輔���雓�鞊莎�揭���雓�鞊莎�揭���雓嚙踝蕭
				}
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 *************/
				req.setAttribute("storeVO", storeVO); // ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭empVO���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎q
				req.setAttribute("store_picVO", store_picVO);
				String url = "/store/store_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���雓�鞊莎�揭���雓����雓�鞊莎�揭���雓�
																				// listOneEmp.jsp
				successView.forward(req, res);
				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("���雓嚙踝����蕭��嚙踝蕭���蕭謖鞊莎�揭���雓����雓�鞊莎�揭���雓�:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getOne_For_Display".equals(action)) { // ���雓蕭���嚙踝蕭��lect_page.jsp嚙踝蕭謕蕭豲���嚙踐�蕭����鞊舀�嚙踝蕭謕
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.嚙踝蕭謕蕭��嚙踝蕭�����嚙踐玫雓�����蕭���嚙踝蕭����蕭��鞊梧蕭 - ���雓�����蕭嚙踐�蕭�����雓ａ嚙踐高嚙踐�蕭豲雓�嚙踝蕭謖���蕭��蕭謕蕭��ㄜ嚙踝蕭謕蕭豲
				 **********************/
				String str = req.getParameter("store_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("������頩�嚙踐的嚙踐��������嚙踐�蕭��鞊堊偌�嚙踝蕭謚迎蕭謕蕭豲");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���嚙踝蕭��鞊船����蕭謕����蕭�����
				}

				Integer store_no = null;
				try {
					store_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�����嚙踐�蕭��鞊堊偌�嚙踝蕭謚迎蕭謕蕭��頩����雓ａ嚙踐�蕭謕蕭豲雓Ｗ祐����蕭�嚙踐�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���嚙踝蕭��鞊船����蕭謕����蕭�����
				}
				/***************************
				 * 2.嚙踝蕭謕蕭豲���鞊船����鞊臬�頩嚙踝嚙踐�ㄜ嚙踐���嚙踝蕭謕蕭豲
				 *****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				if (storeVO == null) {
					errorMsgs.add("嚙踝蕭謕蕭��頩����嚙踝蕭謏��嚙踝蕭��嚙踝蕭謕蕭豲");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// ���嚙踝蕭��鞊船����蕭謕����蕭�����
				}
				/***************************
				 * 3.嚙踝蕭謕蕭��頩嚙踝嚙踐�蕭嚙踐����蕭嚙踐�蕭豲,���鞊臬�嚙踝蕭謕蕭��雓����蕭��蕭��(Send the Success view)
				 *************/
				req.setAttribute("storeVO", storeVO); // ���嚙踝蕭��嚙踝蕭謕蕭��嚙踐玩���蕭��嚙踝蕭謕蕭�����蕭謕蕭謕蕭豲����pVO嚙踝蕭謕蕭豲���鞊舀�,�����撥嚙踐姿��eq
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕蕭豲���嚙踝蕭豯抆���蕭謚叟���蕭��蕭��
																				// listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 嚙踝蕭謕蕭豲���鞊臬����蕭�嚙踐�蕭豲嚙踐狀嚙踐嚙踝蕭謕蕭豲雓�嚙踝蕭謖���蕭��蕭謕蕭��ㄜ嚙踝蕭謕蕭豲 *************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝蕭謕蕭豲嚙踝蕭謖雓�鞈對���朵嚙踐此�����蕭����嚙踝蕭謕蕭豲:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);

			}
		}
		if ("getOne_For_Update".equals(action)) { // ���雓�嚙踐���蕭謚啾揭listAllStore.jsp���雓�鞊莎�揭���雓�嚙踝蕭豯株��蕭

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓���,
														// ���雓�蕭�����蕭��雓閎q
			// (���雓�嚙踐ㄝ���蕭謖鞊莎�揭���雓����雓�鞊莎�揭update_emp_input.jsp)
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage);
			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);

				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				req.setAttribute("storeVO", storeVO); // ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭storeVO���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎q
				String url = "/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���雓�鞊莎�揭���雓����雓�鞊莎�揭���雓�
																				// update_store_input.jsp
				successView.forward(req, res);

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add("���雓嚙踝����蕭��嚙踝蕭���蕭謖鞊莎�揭���雓����雓�蕭豲���蕭謖雓�蕭謖���蕭��雓�鞊莎�揭���雓�:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // ���雓�嚙踐���蕭謚啾揭update_emp_input.jsp���雓�鞊莎�揭���雓�嚙踝蕭豯株��蕭

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓���:
																// ���雓�頩筑�雓�鞊臭����雓���蕭/emp/listAllEmp.jsp���雓���蕭
																// ���雓�鞊莎�揭
																// ���雓���蕭/dept/listEmps_ByDeptno.jsp���雓���蕭
																// ���雓�鞊莎�揭 ���雓���蕭
																// /dept/listAllDept.jsp���雓���蕭
			req.setAttribute("requestURL", requestURL); // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓���,
														// ���雓�蕭�����蕭��雓閎q

			String whichPage = req.getParameter("whichPage"); // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎謑脣��雓�鞊莎�揭(���雓�雓�雓�嚙踝蕭�嚙踝�嚙踐揭:istAllEmp.jsp)
			req.setAttribute("whichPage", whichPage); // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎謑脣��雓�鞊莎�揭,
														// ���雓�蕭�����蕭��雓閎q(���雓�雓�雓�嚙踝蕭�嚙踝�嚙踐揭:istAllEmp.jsp)

			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱� - ���雓�鞊莎�揭J���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 **********************/
				Integer store_no = new Integer(req.getParameter("store_no")
						.trim());
				String store_account = req.getParameter("store_account").trim();
				String store_password = req.getParameter("store_password")
						.trim();
				String store_name = req.getParameter("store_name").trim();
				java.sql.Date store_regist_date = null;
				try {
					store_regist_date = java.sql.Date.valueOf(req.getParameter(
							"store_regist_date").trim());
				} catch (IllegalArgumentException e) {
					store_regist_date = new java.sql.Date(
							System.currentTimeMillis());
					errorMsgs.add("���雓�嚙踐���嚙踐揭J���雓�鞊莎�揭���雓�嚙踝蕭���蕭謖鞊莎�揭���雓�!");
				}

				Double store_longitude = null;
				try {
					store_longitude = new Double(req.getParameter(
							"store_longitude").trim());
				} catch (NumberFormatException e) {
					store_longitude = 0.0;
					errorMsgs.add(".");
				}

				Double store_latitude = null;
				try {
					store_latitude = new Double(req.getParameter(
							"store_latitude").trim());
				} catch (NumberFormatException e) {
					store_latitude = 0.0;
					errorMsgs.add(".");
				}
				Integer store_state = new Integer(req.getParameter(
						"store_state").trim());
				String store_zipcode = req.getParameter("store_zipcode").trim();
				String store_city = req.getParameter("store_city").trim();
				String store_district = req.getParameter("store_district")
						.trim();
				String store_address = req.getParameter("store_address").trim();
				String store_phone = req.getParameter("store_phone").trim();
				String store_type = req.getParameter("store_type").trim();
				Double store_score = new Double(req.getParameter("store_score")
						.trim());
				Integer store_balance = new Integer(req.getParameter(
						"store_balance").trim());
				String store_cell_registcode = req.getParameter(
						"store_cell_registcode").trim();
				Integer store_violation = new Integer(req.getParameter(
						"store_violation").trim());
				String manager_name = req.getParameter("manager_name").trim();
				Integer manager_gender = new Integer(req.getParameter(
						"manager_gender").trim());
				String manager_email = req.getParameter("manager_email").trim();
				String manager_id = req.getParameter("manager_id").trim();
				String manager_cellphone = req
						.getParameter("manager_cellphone").trim();
				Integer manager_credit_num = new Integer(req.getParameter(
						"manager_credit_num").trim());
				Integer manager_credit_expyear = new Integer(req.getParameter(
						"manager_credit_expyear").trim());
				Integer manager_credit_expmonth = new Integer(req.getParameter(
						"manager_credit_expmonth").trim());
				Integer manager_credit_secure_num = new Integer(req
						.getParameter("manager_credit_secure_num").trim());
				Integer tickts_limits = new Integer(req.getParameter(
						"tickts_limits").trim());

				String store_introduction = req.getParameter(
						"store_introduction").trim();
				Integer clicks = new Integer(req.getParameter(
						"clicks").trim());
				Integer store_articles = new Integer(req.getParameter(
						"store_articles").trim());
				Integer store_scopenum = new Integer(req.getParameter(
						"store_scopenum").trim());
				Integer store_pocketnum= new Integer(req.getParameter(
						"store_pocketnum").trim());
				StoreVO storeVO = new StoreVO();
				storeVO.setStore_no(store_no);
				storeVO.setStore_account(store_account);
				storeVO.setStore_password(store_password);
				storeVO.setStore_state(store_state);
				storeVO.setStore_name(store_name);
				storeVO.setStore_regist_date(store_regist_date);
				storeVO.setStore_zipcode(store_zipcode);
				storeVO.setStore_city(store_city);
				storeVO.setStore_district(store_district);
				storeVO.setStore_address(store_address);
				storeVO.setStore_phone(store_phone);
				storeVO.setStore_type(store_type);
				storeVO.setStore_score(store_score);
				storeVO.setStore_balance(store_balance);
				storeVO.setStore_cell_registcode(store_cell_registcode);
				storeVO.setStore_violation(store_violation);
				storeVO.setManager_name(manager_name);
				storeVO.setManager_gender(manager_gender);
				storeVO.setManager_email(manager_email);
				storeVO.setManager_id(manager_id);
				storeVO.setManager_cellphone(manager_cellphone);
				storeVO.setManager_credit_num(manager_credit_num);
				storeVO.setManager_credit_expyear(manager_credit_expyear);
				storeVO.setManager_credit_expmonth(manager_credit_expmonth);
				storeVO.setManager_credit_secure_num(manager_credit_secure_num);
				storeVO.setTickts_limits(tickts_limits);
				storeVO.setStore_longitude(store_longitude);
				storeVO.setStore_latitude(store_latitude);
				storeVO.setStore_introduction(store_introduction);
				storeVO.setClicks(clicks);
				storeVO.setStore_articles(store_articles);
				storeVO.setStore_scopenum(store_scopenum);
				storeVO.setStore_pocketnum(store_pocketnum);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // ���雓�蕭蹎刻���蕭謖鞊莎�揭���雓�鞊莎�揭J���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�蕭�����蕭謖鞊莎�揭empVO���雓�鞊莎�揭���雓�鞊莎�揭,���雓嚙踝����蕭��蕭�����蕭��雓閎q
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/update_store_input.jsp");
					failureView.forward(req, res);
					return; // ���雓�輔���雓�鞊莎�揭���雓�鞊莎�揭���雓嚙踝蕭
				}

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�雓�蕭謖嚙踐揭���雓�鞊莎�揭
				 *****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore(store_no, store_account,
						store_password, store_state, store_name,
						store_regist_date, store_zipcode, store_city,
						store_district, store_address, store_phone, store_type,
						store_score, store_balance, store_cell_registcode,
						store_violation, manager_name, manager_gender,
						manager_email, manager_id, manager_cellphone,
						manager_credit_num, manager_credit_expyear,
						manager_credit_expmonth, manager_credit_secure_num,
						tickts_limits, store_longitude, store_latitude,
						store_introduction, clicks, store_articles,
						store_scopenum,store_pocketnum);

				
				/***************************
				 * 3.���雓�雓�蕭謘踐嚙踐ㄝ���蕭謖鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 *************/

				// if (requestURL.equals("/store/listStore_pics_ByStore_no.jsp")
				// || requestURL.equals("/store/listAllStore.jsp"))
				// req.setAttribute("listStore_pics_ByStore_no",
				// storeSvc.getStore_picsByStore_no(store_no)); //
				// ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭list���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest

				String url = requestURL + "?whichPage=" + whichPage
						+ "&store_no=" + store_no; // ���雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎謑脣��雓�鞊莎�揭(���雓�雓�雓�嚙踝蕭�嚙踝�嚙踐揭:istAllEmp.jsp)���雓�嚙踝蕭��雓�雓�蕭謖���蕭��雓�嚙踐ㄝ���蕭謖鞊莎�揭���雓嚙踝����蕭謖鞊莎�揭
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���雓�雓�蕭謖���蕭��雓����雓�鞊莎�揭,���雓�鞊莎�揭���雓蹌��雓�蕭�����蕭�����雓�雓�蕭謖���蕭��雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭
				successView.forward(req, res);

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("���雓�雓�蕭謖嚙踐揭���雓�嚙踝���蕭�豱血��雓�鞊莎�揭:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { //閮餃��振鞈���register.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.新增店家開始
				 *************************/
				String store_account = req.getParameter("store_account").trim();
				if(store_account.isEmpty()){
					errorMsgs.add("請輸入店家帳號!");
				}
				
				StoreService storeSvc2 = new StoreService();
				StoreVO storeVO2 = storeSvc2.getStoreAccount(store_account);
				if(store_account!=null && storeVO2!=null){
					errorMsgs.add("已有相同的帳號!");
				}
				
				
				//**密碼自動生成				
				String store_password =create(6);
				//**密碼自動生成		
				
				String store_name = req.getParameter("store_name").trim();
				if(store_name.isEmpty()){
					errorMsgs.add("請輸入店家名稱!");
				}
				
				java.sql.Date store_regist_date = null;
				store_regist_date = new java.sql.Date(System.currentTimeMillis());
				

				 Double store_longitude = 0.0;
				 try {
				 store_longitude = new Double(req.getParameter("store_longitude").trim());
				 } catch (NumberFormatException e) {
				 store_longitude = 0.0;
				 }
				
				 Double store_latitude = 0.0;
				 try {
				 store_latitude = new
				 Double(req.getParameter("store_latitude").trim());
				 } catch (NumberFormatException e) {
				 store_latitude = 0.0;
				 }
				//**權限預設為審核中(2)，須經由理原認可才能正式啟用
				 Integer store_state = 2;
		 
				 String store_zipcode =req.getParameter("store_zipcode").trim();
				 if(store_zipcode.isEmpty()){
					 store_zipcode="0";
				 }
				 
				 String store_city = req.getParameter("store_city").trim();
				 if(store_city.isEmpty()){
					 store_city="0";
				 }
				 
				 String store_district =req.getParameter("store_district").trim();
				 if(store_district.isEmpty()){
					 store_district="0";
				 }
				 
				 String store_address =req.getParameter("store_address").trim();
				 if(store_address.isEmpty()){
					 store_address="0";
				 }
				 String store_phone = req.getParameter("store_phone").trim();
				 if(store_phone.isEmpty()){
					 store_phone="0";
				 }
				 String store_type = req.getParameter("store_type").trim();
				 if(store_type.isEmpty()){
					 store_type="0";
				 }
				 Double store_score = 0.0;
				 Integer store_balance = 0;
				 
				 
//				 ************自動生成手機註冊碼************
				 String store_cell_registcode = create_cell_registcode(6);
				 
				 Integer store_violation = 0;
				 String manager_name = req.getParameter("manager_name").trim();
				 if(manager_name.isEmpty()){
					 errorMsgs.add("聯絡人姓名不可為空");
				 }
				 Integer manager_gender=0;
				 
				 try {
					manager_gender = new Integer(req.getParameter("manager_gender").trim());
				} catch (NumberFormatException e) {
					manager_gender=0;
				}
				 
				 String manager_email = req.getParameter("manager_email").trim();
				 if(manager_email.isEmpty()){
					 errorMsgs.add("聯絡人信箱不可為空");
				 }
				 
				 String manager_id = req.getParameter("manager_id").trim();
				 if(manager_id.isEmpty()){
					 manager_id="0";
				 }
				 String manager_cellphone = req.getParameter("manager_cellphone").trim();
				 if(manager_cellphone.isEmpty()){
					 errorMsgs.add("聯絡人手機不可為空");
				 }
				 
				 //**第一階段除錯
				 if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front/store/register/register.jsp");
						failureView.forward(req, res);
						return;
				 }				
				 Integer manager_credit_num=0;
				
				 Integer manager_credit_expyear=0;
				
				 Integer manager_credit_expmonth=0;
				
				 Integer manager_credit_secure_num=0;
				
				 Integer tickts_limits = 1;

				 String store_introduction = req.getParameter("store_introduction").trim();
				 if(store_introduction.isEmpty()){
					 store_introduction="0";
				 }
				 
				 StoreVO storeVO = new StoreVO();
				 storeVO.setStore_account(store_account);
				 storeVO.setStore_password(store_password);
				 storeVO.setStore_state(store_state);
				 storeVO.setStore_name(store_name);
				 storeVO.setStore_regist_date(store_regist_date);
				 storeVO.setStore_zipcode(store_zipcode);
				 storeVO.setStore_city(store_city);
				 storeVO.setStore_district(store_district);
				 storeVO.setStore_address(store_address);
				 storeVO.setStore_phone(store_phone);
				 storeVO.setStore_type(store_type);
				 storeVO.setStore_score(store_score);
				 storeVO.setStore_balance(store_balance);
				 storeVO.setStore_cell_registcode(store_cell_registcode);
				 storeVO.setStore_violation(store_violation);
				 storeVO.setManager_name(manager_name);
				 storeVO.setManager_gender(manager_gender);
				 storeVO.setManager_email(manager_email);
				 storeVO.setManager_id(manager_id);
				 storeVO.setManager_cellphone(manager_cellphone);
				 storeVO.setManager_credit_num(manager_credit_num);
				 storeVO.setManager_credit_expyear(manager_credit_expyear);
				 storeVO.setManager_credit_expmonth(manager_credit_expmonth);
				 storeVO.setManager_credit_secure_num(manager_credit_secure_num);
				 storeVO.setTickts_limits(tickts_limits);
				 storeVO.setStore_longitude(store_longitude);
				 storeVO.setStore_latitude(store_latitude);
				 storeVO.setStore_introduction(store_introduction);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // ���雓�蕭蹎刻���蕭謖鞊莎�揭���雓�鞊莎�揭J���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�蕭�����蕭謖鞊莎�揭empVO���雓�鞊莎�揭���雓�鞊莎�揭,���雓嚙踝����蕭��蕭�����蕭��雓閎q
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/register/register.jsp");
					failureView.forward(req, res);
					return;
				}

				/***************************
				 *  2.把值設進資料庫
				 ***************************************/

				 StoreService storeSvc = new StoreService();
				 storeVO = storeSvc.addStore(store_account, store_password,
				 store_state, store_name, store_regist_date, store_zipcode,
				 store_city, store_district, store_address, store_phone,
				 store_type, store_score, store_balance,
				 store_cell_registcode, store_violation, manager_name,
				 manager_gender, manager_email, manager_id, manager_cellphone,
				 manager_credit_num, manager_credit_expyear,
				 manager_credit_expmonth, manager_credit_secure_num,
				 tickts_limits, store_longitude, store_latitude,store_introduction);
//				storeVO = storeSvc.addStore(store_account, store_password,
//						store_name, store_regist_date, manager_name);

				/***************************
				 * 3.全都成功，轉至成功頁面
				 ***********/
//				String url = "/store/listAllStore.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // ���雓�蕭�����蕭��嚙踝蕭賹雓�鞊莎�揭���雓����雓�鞊莎�揭���雓�鞊莎�揭���雓�韏荔�AllEmp.jsp
//				successView.forward(req, res);
				 addstore_pic(storeVO.getStore_no(),req);
				 res.sendRedirect(req.getContextPath()+"/index.jsp");
				 
				/***************************
				 * 轉交完成
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/store/register/register.jsp");
				failureView.forward(req, res);
			}

		}
		if ("delete".equals(action)) { // ���雓�嚙踐���蕭謚啾揭listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // ���雓�蕭�����蕭�����雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓���:
			// ���雓�頩筑�雓�鞊臭����雓���蕭/emp/listAllEmp.jsp���雓���蕭
			// ���雓�鞊莎�揭
			// ���雓���蕭/dept/listEmps_ByDeptno.jsp���雓���蕭
			// ���雓�鞊莎�揭 ���雓���蕭
			// /dept/listAllDept.jsp���雓���蕭
			String whichPage = req.getParameter("whichPage"); // ���雓�蕭�����蕭�����雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎謑脣��雓�鞊莎�揭(���雓�雓�雓�嚙踝蕭�嚙踝�嚙踐揭:istAllEmp.jsp)
			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ***************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓�
				 ***************************************/
				StoreService storeSvc = new StoreService();
				storeSvc.deleteStore(store_no);

				/***************************
				 * 3.���雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ***********/
				String url = "/store/listAllStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓����雓�鞊莎�揭,���雓�鞊莎�揭���雓蹌��雓�蕭�����蕭�����雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踐���嚙踐揭���雓�鞊莎�揭���雓�鞊莎�揭
				successView.forward(req, res);

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add("���雓�嚙踝蕭豲雓�鞊莎�揭���雓�鞊莎�揭���蕭���雓�鞊莎�揭���雓�:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listStores_ByCompositeQuery".equals(action)) { // ���雓蕭���嚙踝蕭��lect_page.jsp嚙踝蕭謕蕭豲���嚙踐�蕭����嚙踝蕭謕蕭豲嚙踝�����嚙踐�ㄜ嚙踐���鞊舀�嚙踝蕭謕
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {

				/*************************** 1.���頩����嚙踐的嚙踐������嚙踝蕭��嚙踝蕭謕蕭��雓����蕭��蕭�嚙踐� **********************************/
				// 嚙踝蕭謕蕭��嚙踝蕭謢察���嚙踝ap<String,String[]> getParameterMap()嚙踝蕭謕蕭豲���嚙踐疚���蕭��雓葡�
				// ���雓��蕭嚙踐�蕭豲:an immutable java.util.Map

				Map<String, String[]> map = req.getParameterMap();
				 for (Object key : map.keySet()) {
					 System.out.println(key + " : " + map.get(key)[0]);
				 }
				/*************************** 2.嚙踝蕭謕蕭豲���鞊船����頩����嚙踝蕭謕蕭豲嚙踝�����嚙踐� ***************************************/
				StoreService storeSvc = new StoreService();
				List<StoreVO> list = storeSvc.getAll(map);

				/*************************** 3.嚙踝蕭謕蕭��頩嚙踝嚙踐�蕭嚙踐����蕭嚙踐�蕭豲,���鞊臬�嚙踝蕭謕蕭��雓����蕭��蕭��(Send the Success view) ************/
				req.setAttribute("listStores_ByCompositeQuery", list); // ���嚙踝蕭��嚙踝蕭謕蕭��嚙踐玩���蕭��嚙踝蕭謕蕭�����蕭謕蕭謕蕭豲����st嚙踝蕭謕蕭豲���鞊舀�,�����撥嚙踐姿��equest
				RequestDispatcher successView = req
						.getRequestDispatcher("/front/store/store_search.jsp"); // 嚙踝蕭謕蕭豲���嚙踝蕭豯抆���蕭謚叟���蕭��蕭��istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/*************************** 嚙踝蕭謕蕭豲���鞊臬����蕭�嚙踐�蕭豲嚙踐狀嚙踐嚙踝蕭謕蕭豲雓�嚙踝蕭謖���蕭��蕭謕蕭��ㄜ嚙踝蕭謕蕭豲 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("store_search".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				StoreService storeSvc = new StoreService();
				Set<Store_picVO> set = storeSvc
						.getStore_picsByStore_no(store_no);
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				req.setAttribute("store_picVO", set);
				req.setAttribute("storeVO", storeVO);// ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭set���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest
				RequestDispatcher successView = req
						.getRequestDispatcher("/front/store/store_detail.jsp"); // 嚙踝蕭謕蕭豲���嚙踝蕭豯抆���蕭謚叟���蕭��蕭��istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		if ("store_scope".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Double store_scope = new Double(req.getParameter("store_scope"));
				System.out.println(store_no+","+store_scope);
				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.updateScope(store_no, store_scope);
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				req.setAttribute("storeVO", storeVO);// ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭set���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest
				res.setHeader("Cache-Control", "no-store");
				res.setDateHeader("Expires", 0);
				RequestDispatcher successView = req
						.getRequestDispatcher("/front/store/store_detail.jsp"); // 嚙踝蕭謕蕭豲���嚙踝蕭豯抆���蕭謚叟���蕭��蕭��istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		if ("store_pocket".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				Timestamp t = new Timestamp(System.currentTimeMillis()); 
				PocketService pocketSvc = new PocketService();
				PocketVO pocketVO = pocketSvc.addPocket(mem_no, store_no,t);
				
				StoreService storeSvc = new StoreService();
				Set<Store_picVO> set = storeSvc
						.getStore_picsByStore_no(store_no);
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				storeVO.setStore_pocketnum(storeVO.getStore_pocketnum()+1);
				
				StoreDAO_hibernate_interface dao= new StoreDAO_hibernate();
				dao.update(storeVO);
				
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				req.setAttribute("store_picVO", set);
				req.setAttribute("storeVO", storeVO);
		
				String url = "/front/store/store_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���雓�蕭�����蕭��嚙踝蕭賹雓�鞊莎�揭���雓����雓�鞊莎�揭���雓�鞊莎�揭���雓�韏荔�AllEmp.jsp
				Thread.sleep(1500);
				successView.forward(req, res);
				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("go_group".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�嚙踝蕭��雓�頩�頩�揭���雓�
				 ****************************************/
				StoreService storeSvc = new StoreService();
				StoreVO storeVO = storeSvc.getOneStore(store_no);
				/***************************
				 * 3.���雓�嚙踝蕭��雓�頩�蕭謖嚙踐揭���雓�鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 ************/
				// ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭set���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest
				HttpSession session = req.getSession();
				session.setAttribute("storeVO", storeVO);
				session.setAttribute("mem_no", mem_no);
				res.setHeader("Cache-Control", "no-store");
				res.setDateHeader("Expires", 0);
//				RequestDispatcher successView = req
//						.getRequestDispatcher("/group/group_creat.jsp"); // 嚙踝蕭謕蕭豲���嚙踝蕭豯抆���蕭謚叟���蕭��蕭��istEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
				res.sendRedirect("../front/group/group_creat.jsp");
				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("update2".equals(action)) { // ���雓�嚙踐���蕭謚啾揭update_emp_input.jsp���雓�鞊莎�揭���雓�嚙踝蕭豯株��蕭

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.���雓�鞊莎�揭���雓�鞊莎�揭���雓�嚙踝蕭豯株頩��雓□嚙踐鬲嚙踐甇歹蕭豱� - ���雓�鞊莎�揭J���雓���蕭嚙踐江����蕭謖鞊莎�揭���雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 **********************/
				StoreVO storeVO = (StoreVO) req.getSession().getAttribute("storeVO");
				
				String store_password = req.getParameter("store_password").trim();
				if(store_password.isEmpty()){
					errorMsgs.add("請輸入密碼!");
				}
				
				String store_name = req.getParameter("store_name").trim();
				if(store_name.isEmpty()){
					errorMsgs.add("請輸入店家名稱!");
				}
//				Double store_longitude = null;
//				try {
//					store_longitude = new Double(req.getParameter(
//							"store_longitude").trim());
//				} catch (NumberFormatException e) {
//					store_longitude = 0.0;
//					errorMsgs.add(".");
//				}
//
//				Double store_latitude = null;
//				try {
//					store_latitude = new Double(req.getParameter(
//							"store_latitude").trim());
//				} catch (NumberFormatException e) {
//					store_latitude = 0.0;
//					errorMsgs.add(".");
//				}

//				String store_zipcode = req.getParameter("store_zipcode").trim();
//				if(store_zipcode.isEmpty()){
//
//				 }
				String store_city = req.getParameter("store_city").trim();
				String store_district = req.getParameter("store_district").trim();
				
				String store_address = req.getParameter("store_address").trim();
				String store_phone = req.getParameter("store_phone").trim();
				String store_type = req.getParameter("store_type").trim();
			
				
				String manager_name = req.getParameter("manager_name").trim();
			
				String manager_email = req.getParameter("manager_email").trim();
//				String manager_id = req.getParameter("manager_id").trim();
				String manager_cellphone = req.getParameter("manager_cellphone").trim();
//				Integer manager_gender = new Integer(req.getParameter("manager_gender"));

				String store_introduction = req.getParameter(
						"store_introduction").trim();
				if (store_introduction==null){
					store_introduction="還沒輸入店家介紹歐<3";
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/update/update.jsp");
					failureView.forward(req, res);
					return; 
				}

				storeVO.setStore_password(store_password);
				storeVO.setStore_name(store_name);

//				storeVO.setStore_zipcode(store_zipcode);
				storeVO.setStore_city(store_city);
				storeVO.setStore_district(store_district);
				storeVO.setStore_address(store_address);
				storeVO.setStore_phone(store_phone);
				storeVO.setStore_type(store_type);
				storeVO.setManager_name(manager_name);
//				storeVO.setManager_gender(manager_gender);
				storeVO.setManager_email(manager_email);
//				storeVO.setManager_id(manager_id);
				storeVO.setManager_cellphone(manager_cellphone);
//				storeVO.setStore_longitude(store_longitude);
//				storeVO.setStore_latitude(store_latitude);
				storeVO.setStore_introduction(store_introduction);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/store/update/update.jsp");
					failureView.forward(req, res);
					return; 
				}

				/***************************
				 * 2.���雓嚙踝����蕭��頩筑�雓�雓�蕭謖嚙踐揭���雓�鞊莎�揭
				 *****************************************/
				StoreService storeSvc = new StoreService();
				storeVO = storeSvc.updateStore2(storeVO);

				
				/***************************
				 * 3.���雓�雓�蕭謘踐嚙踐ㄝ���蕭謖鞊莎�揭,���雓�嚙踝蕭��嚙踐�蕭豱血��雓�鞊莎�揭���雓�(Send the Success view)
				 *************/

				// if (requestURL.equals("/store/listStore_pics_ByStore_no.jsp")
				// || requestURL.equals("/store/listAllStore.jsp"))
				// req.setAttribute("listStore_pics_ByStore_no",
				// storeSvc.getStore_picsByStore_no(store_no)); //
				// ���雓�鞊莎�揭���蕭����雓�鞊莎�揭���雓����雓�鞊莎�揭list���雓�鞊莎�揭���雓�鞊莎�揭,���雓�蕭�����蕭��雓閎quest

				  res.sendRedirect(req.getContextPath()+"/index_store.jsp"); 

				/***************************
				 * ���雓�鞊莎�揭L���雓�頩筑�雓�鞊臭�嚙踝蕭��雓�鞊莎�揭���雓�蕭�����蕭�豱詨��雓�蕭嚙�
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("目前連線繁忙，請稍後在試:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/store/update/update.jsp");
				failureView.forward(req, res);
			}
		}
	}

	private void addstore_pic(Integer store_no,HttpServletRequest req) throws IllegalStateException, IOException, ServletException {
		String pic_format = null;
		byte[] buf = null;
		Collection<Part> parts = req.getParts();
		try {
			for (Part part : parts) {

				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					String name = getFileNameFromPart(part);
					pic_format = name.substring(name.lastIndexOf(".") + 1, name.length());
					// f = new File(fsaveDirectory, name);
					// part.write(f.toString());
					InputStream in = part.getInputStream();
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
					/***********************
					 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
					 *************************/
					String pic_name = name.substring(0, name.lastIndexOf("."));
				
					byte[] store_pic = buf;
					Store_picVO store_picVO = new Store_picVO();
					store_picVO.setPic_name(pic_name);
					store_picVO.setStore_pic(store_pic);
					store_picVO.setPic_format(pic_format);
					
					StoreVO storeVO = new StoreVO();
					storeVO.setStore_no(store_no);
					store_picVO.setStoreVO(storeVO);
					// Send the use back to the form, if there were errors
				

					/**************************** 2.�}�l�s�W���***************************************/
					Store_picService store_picSvc = new Store_picService();
					store_picVO = store_picSvc.addStore_pic(pic_name, store_no, store_pic, pic_format);
					/**************************** 3.�s�W����,�ǳ����(Send the Success view)***********/
				}
			}
		} catch (Exception e) {
			System.out.println("寫入有例外");
		}
		
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // ���雓�鞊莎�揭���雓▽貔蕭謇穿蕭嚙踐豱�
		String filename = new File(header.substring(
				header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // ���雓�鞊莎�揭���雓▽貔蕭謇穿蕭嚙踐豱�

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

	public static String create (int len){
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; //<---摨���暻澆停���
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<len;i++){
		int idx = (int)(Math.random() * str.length());
		sb.append(str.charAt(idx));
		}
		return sb.toString();
		}
	public static String create_cell_registcode (int len){
		String str = "123456789";//<---序號要什麼就加什
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<len;i++){
		int idx = (int)(Math.random() * str.length());
		sb.append(str.charAt(idx));
		}
		return sb.toString();
		}
}
