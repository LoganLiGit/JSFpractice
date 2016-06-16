package com.group.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group.mem.model.GroupMemService;
import com.group.mem.model.GroupMemVO;
import com.group.table.model.GroupTableService;
import com.group.table.model.GroupTableVO;
import com.member.model.MemberVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.store.pic.model.Store_picVO;

/**
 * Servlet implementation class GroupTableServlet
 */
@WebServlet("/GroupTableServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GroupTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getGroups_For_Mem".equals(action)) { // ����select_page.jsp��������

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				 Integer mem_no = new Integer(req.getParameter("mem_no").trim());

				/*************************** 2.��蔆����������蕭 *****************************************/
				GroupTableService groupSvc = new GroupTableService();
				List<GroupTableVO> list = groupSvc.getGroupTablesByMem_no(mem_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;// ��溯��������酉
				}

				/*************************** 3.���������,���������蕭(Send the Success view) *************/
				req.setAttribute("getGroups_For_Mem", list); // ����w�����Ⅳ���empVO������,��楊���eq
				String url = "/front/group/group_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �����������蕭
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ���L����������劑����歇 *************************************/
			} catch (Exception e) {
				errorMsgs.add("Exception e" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listGroups_ByCompositeQuery".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println("123");
			try {
				Map<String, String[]> map = req.getParameterMap();
				 for (Object key : map.keySet()) {
					 System.out.println(key + " : " + map.get(key)[0]);
				 }
				GroupTableService groupSvc = new GroupTableService();
				List<GroupTableVO> list = groupSvc.getAll(map);

				/*************************** 3.���蕭蹓遴����嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view) ************/
				req.setAttribute("listGroups_ByCompositeQuery", list); // 嚙踝蕭�����蕭�嚙踝����嚙踝���嚙踐凝ist���嚙踝蕭豯株,嚙踐雓Ｗ�request
				RequestDispatcher successView = req
						.getRequestDispatcher("/front/group/group_search.jsp"); // ���嚙踝蕭��鞈對�嚙踐��蓮istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				req.setAttribute("listGroups_ByCompositeQuery", list);
				/*************************** ���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭��� **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/group/group_search.jsp");
				failureView.forward(req, res);
			}
		}
		if ("listGroups_ByCompositeQuery_Store".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Map<String, String[]> map = req.getParameterMap();
				 for (Object key : map.keySet()) {
					 System.out.println(key + " : " + map.get(key)[0]);
				 }

				StoreService storeSvc = new StoreService();
				List<StoreVO> listStore = storeSvc.getAll(map);
			
				List<GroupTableVO> list = new LinkedList<GroupTableVO>();
				for (StoreVO alistStore : listStore) {
					list.addAll(alistStore.getGroupTables());
				}
			
				/*************************** 3.���蕭蹓遴����嚙踐ㄞ���,嚙踝蕭豯凌���蕭謖嚙踐��蕭(Send the Success view) ************/
				req.setAttribute("listGroups_ByCompositeQuery", list); // 嚙踝蕭�����蕭�嚙踝����嚙踝���嚙踐凝ist���嚙踝蕭豯株,嚙踐雓Ｗ�request
				RequestDispatcher successView = req
						.getRequestDispatcher("/front/group/group_search.jsp"); // ���嚙踝蕭��鞈對�嚙踐��蓮istEmps_ByCompositeQuery.jsp
				
				successView.forward(req, res);
	
				/*************************** ���蕭嚙踝蕭豯凌嚙踐���蕭�����蕭謖蕭��狗嚙踐���飭��� **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/group/group_search.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // ����select_page.jsp��������

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����������姜瞏 - ���J��都撘�������劑����歇 **********************/
				String str = req.getParameter("group_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("嚙踐��嚙踝�陬");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;// ��溯��������酉
				}

				Integer group_no = null;
				try {
					group_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("��嚙踐��嚙�");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;// ��溯��������酉
				}

				/*************************** 2.��蔆����������蕭 *****************************************/
				GroupTableService groupSvc = new GroupTableService();
				GroupTableVO groupVO = groupSvc.getOneGroupTableVO(group_no);
				if (groupVO == null) {
					errorMsgs.add("VO��謘ULL");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;// ��溯��������酉
				}

				/*************************** 3.���������,���������蕭(Send the Success view) *************/
				req.setAttribute("groupVO", groupVO); // ����w�����Ⅳ���empVO������,��楊���eq
				String url = "/front/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �����������蕭
																				// listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ���L����������劑����歇 *************************************/
			} catch (Exception e) {
				errorMsgs.add("Exception e" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insertGroup".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			HttpSession session = req.getSession();
			session.removeAttribute("storeVO");
			req.setAttribute("errorMsgs", errorMsgs);
			byte[] buf = null;
			try {
				/*********************** 1.�����������姜瞏 - ���J��都撘�������劑����歇 *************************/
				MemberVO memberVO= (MemberVO) session.getAttribute("memberVO");
				
				Integer mem_no =memberVO.getMem_no();
				Integer store_no = new Integer(req.getParameter("store_no")
						.trim());
				
				Integer group_num = new Integer(req.getParameter("group_num")
						.trim());

				String group_name = req.getParameter("group_name").trim();
				String group_intro = req.getParameter("group_intro").trim();
				
				javax.servlet.http.Part part = req.getPart("group_photo");
				InputStream in = part.getInputStream();
				buf = new byte[in.available()];
				in.read(buf);
				in.close();
				// Timestamp group_start_date = null;
				// try {
				//
				// group_start_date = java.sql.Timestamp.valueOf(changeTime(req
				// .getParameter("group_start_date").trim()));
				// } catch (IllegalArgumentException e) {
				// group_start_date = new java.sql.Timestamp(
				// System.currentTimeMillis());
				// errorMsgs.add("group_start_date");
				// }
				Timestamp group_eat_date = null;
				try {
					group_eat_date = java.sql.Timestamp.valueOf(changeTime(req
							.getParameter("group_eat_date").trim()));
				} catch (IllegalArgumentException e) {
					group_eat_date = new java.sql.Timestamp(
							System.currentTimeMillis());
					errorMsgs.add("group_eat_date!");
				}
				Timestamp group_stop_date = null;
				try {
					group_stop_date = java.sql.Timestamp.valueOf(changeTime(req
							.getParameter("group_stop_date").trim()));
				} catch (IllegalArgumentException e) {
					group_stop_date = new java.sql.Timestamp(
							System.currentTimeMillis());
					errorMsgs.add("group_stop_date!");
				}
				
				GroupTableVO groupVO = new GroupTableVO();
				
				StoreVO storeVO = new StoreVO();
				storeVO.setStore_no(store_no);
				groupVO.setStoreVO(storeVO);
			
				groupVO.setMem_no(mem_no);
				groupVO.setGroup_num(group_num);
				groupVO.setGroup_intro(group_intro);
			
				groupVO.setGroup_name(group_name);
				groupVO.setGroup_start_date(group_eat_date);
				// groupVO.setGroup_start_date(group_start_date);
				groupVO.setGroup_start_date(group_stop_date);
			
				groupVO.setGroup_photo(buf);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupVO", groupVO); // ��楠������J��都撘����劑���empVO������,��里��楊���eq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/group/c.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.��蔆����楊�������蕭 ***************************************/
				GroupTableService groupSvc = new GroupTableService();
				groupVO = groupSvc.addGroupTable(mem_no, store_no, group_num,
						group_name, group_intro, group_eat_date,
						group_stop_date, buf);
				
				List<GroupTableVO> list = groupSvc.getGroupTablesByMem_no(mem_no);
				
				req.setAttribute("getGroups_For_Mem", list);
			
				/*************************** 3.��楊��������,���������蕭(Send the Success view) ***********/
				String url = "/front/group/group_manage.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��楊�����������������stAllGroup.jsp
				successView.forward(req, res);
				
				/*************************** ���L����������劑����歇 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/group/addGroup.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insertMem".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			System.out.println(req.getParameter("mem_no"));
			System.out.println(req.getParameter("group_no"));
			try {
				/*********************** 1.�����������姜瞏 - ���J��都撘�������劑����歇 *************************/
				Integer mem_no = new Integer(Integer.parseInt(req.getParameter("mem_no").trim()));
				Integer group_no = new Integer(Integer.parseInt(req.getParameter("group_no").trim()));
				System.out.println(mem_no+" "+group_no);
				GroupTableService groupSvc = new GroupTableService();
				GroupMemVO groupMemVO = new GroupMemVO();
				groupMemVO.setMem_no(mem_no);
				groupMemVO.setGroup_no(group_no);
				groupMemVO.setGroupTableVO(groupSvc
						.getOneGroupTableVO(group_no));
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("groupSvc", groupSvc); // ��楠������J��都撘����劑���empVO������,��里��楊���eq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front/group/addGroupMem.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.��蔆����楊�������蕭 ***************************************/
				GroupMemService groupMemSvc = new GroupMemService();
				groupMemSvc.addGroupMem(groupMemVO);
				
				/*************************** 3.��楊��������,���������蕭(Send the Success view) ***********/
				String url = "/front/group/group_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��楊�����������������stAllGroup.jsp
				successView.forward(req, res);

				/*************************** ���L����������劑����歇 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front/group/addGroup.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 嚙踝蕭謕蕭�嚙踐�listAllStore.jsp嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭嚙�

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			//
			// String requestURL = req.getParameter("requestURL");
			// req.setAttribute("requestURL", requestURL); //
			// 嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭謘踐�蕭嚙踝�蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕,
			// // 嚙踝蕭謕�蕭嚙踐�蕭謇q
			// // (嚙踝蕭謕蕭�嚙踐�蕭豲嚙踝蕭謕扔嚙踝蕭謕蕭豲update_emp_input.jsp)
			// String whichPage = req.getParameter("whichPage");
			// req.setAttribute("whichPage", whichPage);
			try {
				/***************************
				 * 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此�
				 ****************************************/
				Integer group_no = new Integer(req.getParameter("group_no"));

				/***************************
				 * 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭蹎∴蕭蹇嚙踝蕭謕
				 ****************************************/

				GroupTableService groupTableSvc = new GroupTableService();
				GroupTableVO groupVO = groupTableSvc
						.getOneGroupTableVO(group_no);
				/***************************
				 * 3.嚙踝蕭謕蕭��蕭謕蕭蹎�蕭�嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success
				 * view)
				 ************/
				req.setAttribute("groupVO", groupVO); // 嚙踝蕭謕蕭豲嚙踐�嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕蕭豲storeVO嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕�蕭嚙踐�蕭謇q
				String url = "/front/group/update_group_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲嚙踝蕭謕
																				// update_store_input.jsp
				successView.forward(req, res);

				/***************************
				 * 嚙踝蕭謕蕭豲L嚙踝蕭謕蕭蹎蕭謕蕭豯佗蕭��蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝蕭謕�蕭嚙踐�蕭��嚙踐�蕭豲嚙踝蕭謕熄嚙踝蕭謕�蕭嚙踐�蕭謘踐�蕭嚙踝�蕭謕蕭豲嚙踝蕭謕:"
						+ e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/listAllStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			// req.setAttribute("errorMsgs", errorMsgs);
			// String requestURL = req.getParameter("requestURL");
			// req.setAttribute("requestURL", requestURL);
			// String whichPage = req.getParameter("whichPage");
			// req.setAttribute("whichPage", whichPage);
			byte[] buf = null;
			try {
				javax.servlet.http.Part part = req.getPart("group_photo");
				InputStream in = part.getInputStream();
				buf = new byte[in.available()];
				in.read(buf);
				in.close();
				HttpSession session = req.getSession();
				MemberVO memberVO= (MemberVO) session.getAttribute("memberVO");
				Integer mem_no = memberVO.getMem_no();// ����ession��楊���
				Integer store_no = new Integer(req.getParameter("store_no")
						.trim());

				Integer group_no = new Integer(req.getParameter("group_no")
						.trim());

				Integer group_num = new Integer(req.getParameter("group_num")
						.trim());

				String group_name = req.getParameter("group_name").trim();
		
				String group_intro = req.getParameter("group_intro").trim();

				// Timestamp group_start_date = null;
				// try {
				//
				// group_start_date = java.sql.Timestamp.valueOf(changeTime(req
				// .getParameter("group_start_date").trim()));
				// } catch (IllegalArgumentException e) {
				// group_start_date = new java.sql.Timestamp(
				// System.currentTimeMillis());
				// errorMsgs.add("group_start_date");
				// }
				Timestamp group_eat_date = null;
				try {
					group_eat_date = java.sql.Timestamp.valueOf(changeTime(req
							.getParameter("group_eat_date").trim()));
				} catch (IllegalArgumentException e) {
					group_eat_date = new java.sql.Timestamp(
							System.currentTimeMillis());
					errorMsgs.add("group_eat_date!");
				}
				Timestamp group_stop_date = null;
				try {
					group_stop_date = java.sql.Timestamp.valueOf(changeTime(req
							.getParameter("group_stop_date").trim()));
				} catch (IllegalArgumentException e) {
					group_stop_date = new java.sql.Timestamp(
							System.currentTimeMillis());
					errorMsgs.add("group_stop_date!");
				}
				GroupTableVO groupVO = new GroupTableVO();
				StoreVO storeVO = new StoreVO();
				storeVO.setStore_no(store_no);
				groupVO.setStoreVO(storeVO);
				groupVO.setMem_no(mem_no);
				groupVO.setGroup_num(group_num);
				groupVO.setGroup_intro(group_intro);
				groupVO.setGroup_name(group_name);
				groupVO.setGroup_start_date(group_eat_date);
				// groupVO.setGroup_start_date(group_start_date);
				groupVO.setGroup_start_date(group_stop_date);
				groupVO.setGroup_photo(buf);
				/***************************
				 * 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭謘踐�蕭�嚙踝蕭謕蕭豲
				 *****************************************/
				GroupTableService groupSvc = new GroupTableService();
				groupSvc.updateGroupTable(group_no, mem_no, store_no,
						group_num, group_name, group_intro, group_eat_date,
						group_stop_date,buf);

				/***************************
				 * 3.嚙踝蕭謕蕭謘踐��嚙踐�蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success view)
				 *************/

				List<GroupTableVO> list = groupSvc.getGroupTablesByMem_no(mem_no);
				req.setAttribute("getGroups_For_Mem", list); // ����w�����Ⅳ���empVO������,��楊���eq
				String url = "/front/group/group_manage.jsp";

				// String url = requestURL + "?whichPage=" + whichPage
				// + "&group_no=" + group_no; //
				// 嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭謘踐�蕭嚙踝�蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲(嚙踝蕭謕蕭謍堆蕭謕蕭���蕭�:istAllEmp.jsp)嚙踝蕭謕蕭��蕭謕蕭謘踐�蕭嚙踝�蕭謕蕭�嚙踐�蕭豲嚙踝蕭謕�蕭嚙踐�蕭豲
				RequestDispatcher successView = req.getRequestDispatcher(url); // 嚙踝蕭謕蕭謘踐�蕭嚙踐�蕭謕蕭嚙踝蕭謕蕭豲,嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭謘踐�蕭嚙踝�蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲
				successView.forward(req, res);

				/***************************
				 * 嚙踝蕭謕蕭豲L嚙踝蕭謕蕭蹎蕭謕蕭豯佗蕭��蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��
				 *************************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝蕭謕蕭謘踐�蕭�嚙踝蕭謕蕭�嚙踐�嚙踝蕭謕蕭豲:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭��蕭蹇蕭謕��此�
				 ***************************************/
				Integer group_no = new Integer(req.getParameter("group_no"));

				/***************************
				 * 2.嚙踝蕭謕�蕭嚙踐�蕭蹎蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕
				 ***************************************/
				GroupTableService groupSvc = new GroupTableService();
				groupSvc.deleteGroupTable(group_no);
				/***************************
				 * 3.嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success
				 * view)
				 ***********/
				String url = "/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭嚙踝蕭謕蕭豲,嚙踝蕭謕蕭豲嚙踝蕭謕嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭�嚙踝蕭�嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲
				successView.forward(req, res);

				/***************************
				 * 嚙踝蕭謕蕭豲L嚙踝蕭謕蕭蹎蕭謕蕭豯佗蕭��蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝�蕭謒蕭��嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}

		if ("deleteOneMem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer group_no = new Integer(req.getParameter("group_no"));
				Integer mem_no = new Integer(req.getParameter("mem_no"));
			
				GroupMemService groupMem = new GroupMemService();
				groupMem.deleteGroupMem(group_no, mem_no);

				GroupTableService groupSvc = new GroupTableService();
				List<GroupTableVO> list = groupSvc.getGroupTablesByMem_no(mem_no);
				/***************************
				 * 3.嚙踝蕭謕蕭��蕭謕蕭豲嚙踝蕭謕蕭豲嚙踝蕭謕蕭豲,嚙踝蕭謕蕭��蕭��嚙踝蕭謕蕭豲嚙踝蕭謕(Send the Success
				 * view)
				 ***********/
				req.setAttribute("getGroups_For_Mem", list);
				String url = "/front/group/group_manage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ��楊�����������������stAllGroup.jsp
				successView.forward(req, res);
				/***************************
				 * 嚙踝蕭謕蕭豲L嚙踝蕭謕蕭蹎蕭謕蕭豯佗蕭��蕭謕蕭豲嚙踝蕭謕�蕭嚙踐�嚙踝蕭謕��
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add("嚙踝�蕭謒蕭��嚙�" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}

	}

	public String changeTime(String str) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(str.substring(str.lastIndexOf("/") + 1,
				str.lastIndexOf("/") + 5));
		strBuffer.append("-");
		strBuffer.append(str.substring(0, 2));
		strBuffer.append("-");
		strBuffer.append(str.substring(str.lastIndexOf("/") - 2,
				str.lastIndexOf("/")));
		strBuffer.append(" ");
		if (str.substring(str.length() - 2, str.length()).equals("PM")) {
			if (str.length() == 18) {
				String hour = str.substring(11, 12);
				Integer tmp = Integer.parseInt(hour) + 12;
				strBuffer.append(tmp.toString());
				strBuffer.append(":");
				strBuffer.append(str.substring(str.lastIndexOf(" ") - 2,
						str.lastIndexOf(" ")));
				strBuffer.append(":00");

			} else {
				String hour = str.substring(11, 13);
				Integer tmp = Integer.parseInt(hour) + 12;
				strBuffer.append(tmp.toString());
				strBuffer.append(":");
				strBuffer.append(str.substring(str.lastIndexOf(" ") - 2,
						str.lastIndexOf(" ")));
				strBuffer.append(":00");

			}
		} else {
			if (str.length() == 18) {
				strBuffer.append("0");
				strBuffer.append(str.substring(str.lastIndexOf(" ") - 4,
						str.lastIndexOf(" ")));
				strBuffer.append(":00");

			} else {
				strBuffer.append(str.substring(str.lastIndexOf(" ") - 5,
						str.lastIndexOf(" ")));
				strBuffer.append(":00");

			}
		}
		return strBuffer.toString();
	}
}
