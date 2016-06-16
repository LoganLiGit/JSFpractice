package com.store.pic.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.store.model.StoreService;
import com.store.model.StoreVO;
import com.store.pic.model.Store_picService;
import com.store.pic.model.Store_picVO;

/**
 * Servlet implementation class StoreServlet
 */
// @WebServlet("/StoreServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Store_picServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				String str = req.getParameter("pic_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�Ӥ��s��");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				Integer pic_no = null;
				try {
					pic_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�Ӥ��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/***************************
				 * 2.�}�l�d�߸��
				 *****************************************/
				Store_picService store_picSvc = new Store_picService();
				Store_picVO store_picVO = store_picSvc.getOneStore_pic(pic_no);
				if (store_picVO == null) {
					errorMsgs.add("�d�L���");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 *************/

				req.setAttribute("store_picVO", store_picVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/store_pic/listOneStore_pic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���
				// listOneEmp.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/select_page.jsp");
				failureView.forward(req, res);

			}
		}

		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllStore.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); // �e�X�ק諸�ӷ��������|, �s�Jreq
			// (�O���F��update_emp_input.jsp)
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // �e�X�ק諸�ӷ��������ĴX��,
			// �s�Jreq(�u�Ω�:istAllEmp.jsp)

			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ****************************************/
				Integer pic_no = new Integer(req.getParameter("pic_no"));

				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/
				Store_picService store_picSvc = new Store_picService();
				Store_picVO store_picVO = store_picSvc.getOneStore_pic(pic_no);

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("store_picVO", store_picVO); // ��Ʈw���X��storeVO����,�s�Jreq
				String url = "/store_pic/update_store_pic_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_store_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/listAllStore_pic.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Insert".equals(action)) { // �Ӧ�listAllStore.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			req.setAttribute("requestURL", requestURL); // �e�X�ק諸�ӷ��������|, �s�Jreq
			// (�O���F��update_emp_input.jsp)
			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // �e�X�ק諸�ӷ��������ĴX��,
			// �s�Jreq(�u�Ω�:istAllEmp.jsp)
			
			try {
				/***************************
				 * 1.�����ШD�Ѽ�
				 ****************************************/
				Integer store_no = new Integer(req.getParameter("store_no"));
				
				/***************************
				 * 2.�}�l�d�߸��
				 ****************************************/

				/***************************
				 * 3.�d�ߧ���,�ǳ����(Send the Success view)
				 ************/
				req.setAttribute("store_no", store_no); // ��Ʈw���X��storeVO����,�s�Jreq
				String url = "/store_pic/addStore_pic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���
																				// update_store_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/addStore_pic.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			// /dept/listAllDept.jsp�j
			req.setAttribute("requestURL", requestURL); // �e�X�ק諸�ӷ��������|, �s�Jreq

			String whichPage = req.getParameter("whichPage"); // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:istAllEmp.jsp)
			req.setAttribute("whichPage", whichPage); // �e�X�ק諸�ӷ��������ĴX��,
			
			// �s�Jreq(�u�Ω�:istAllEmp.jsp)
			String pic_format = null;
			byte[] buf = null;
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {

				if (getFileNameFromPart(part) != null && part.getContentType() != null) {
					String name = getFileNameFromPart(part);
					pic_format = name.substring(name.lastIndexOf(".") + 1, name.length());
					InputStream in = part.getInputStream();
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				}
			}
			try {
				/***************************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
				 **********************/
				Integer pic_no = new Integer(req.getParameter("pic_no").trim());
				String pic_name = req.getParameter("pic_name").trim();
				Integer store_no = new Integer(req.getParameter("store_no").trim());
				byte[] store_pic = buf;
				Store_picVO store_picVO = new Store_picVO();
				store_picVO.setPic_no(pic_no);
				store_picVO.setPic_name(pic_name);
				store_picVO.setStore_pic(store_pic);
				store_picVO.setPic_format(pic_format);
				
				StoreVO storeVO = new StoreVO();
				storeVO.setStore_no(store_no);
				store_picVO.setStoreVO(storeVO);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("store_picVO", store_picVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/update_store_pic_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/***************************
				 * 2.�}�l�ק���
				 *****************************************/
				Store_picService store_picSvc = new Store_picService();

				store_picVO = store_picSvc.updateStore_pic(pic_no, pic_name, store_no, store_pic, pic_format);

				/***************************
				 * 3.�ק粒��,�ǳ����(Send the Success view)
				 *************/
//				req.setAttribute("store_picVO", store_picVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/store_pic/listOneStore_pic.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//				
				StoreService storeSvc = new StoreService();
		
				if (requestURL.equals("/store/listStore_pics_ByStore_no.jsp") || requestURL.equals("/store/listAllStore.jsp"))
					req.setAttribute("listStore_pics_ByStore_no", storeSvc.getStore_picsByStore_no(store_no)); // ��Ʈw���X��list����,�s�Jrequest

				String url = requestURL + "?whichPage=" + whichPage + "&pic_no=" + pic_no; // �e�X�ק諸�ӷ��������ĴX��(�u�Ω�:istAllEmp.jsp)�M�ק諸�O���@��
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���^�e�X�ק諸�ӷ�����
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/

			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/update_store_pic_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

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
						Integer store_no = new Integer(req.getParameter("store_no").trim());
						byte[] store_pic = buf;
						Store_picVO store_picVO = new Store_picVO();
						store_picVO.setPic_name(pic_name);
						store_picVO.setStore_pic(store_pic);
						store_picVO.setPic_format(pic_format);
						
						StoreVO storeVO = new StoreVO();
						storeVO.setStore_no(store_no);
						store_picVO.setStoreVO(storeVO);
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("store_picVO", store_picVO); // �t����J�榡���~��empVO����,�]�s�Jreq
							RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/addStore_pic.jsp");
							failureView.forward(req, res);
							return;
						}

						/**************************** 2.�}�l�s�W���***************************************/
						Store_picService store_picSvc = new Store_picService();
						store_picVO = store_picSvc.addStore_pic(pic_name, store_no, store_pic, pic_format);
						/**************************** 3.�s�W����,�ǳ����(Send the Success view)***********/
					}
				}
				String url = "/store_pic/listAllStore_pic.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/

			} catch (Exception e) {

				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req.getRequestDispatcher("/store_pic/addStore_pic.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|:
																// �i�ର�i/emp/listAllEmp.jsp�j
																// ��
																// �i/dept/listEmps_ByDeptno.jsp�j
																// �� �i
																// /dept/listAllDept.jsp�j
			String whichPage = req.getParameter("whichPage"); // �e�X�R�����ӷ��������ĴX��(�u�Ω�:istAllEmp.jsp)

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer pic_no = new Integer(req.getParameter("pic_no"));

				/*************************** 2.�}�l�R����� ***************************************/
				Store_picService store_picSvc = new Store_picService();
				Store_picVO store_picVo = store_picSvc.getOneStore_pic(pic_no);
				store_picSvc.deleteStore_pic(pic_no);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
//				String url = "/store_pic/listAllStore_pic.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
				StoreService storeSvc = new StoreService();
				if (requestURL.equals("/store/listStore_pics_ByStore_no.jsp") || requestURL.equals("/store/listAllStore.jsp"))
					req.setAttribute("listStore_pics_ByStore_no", storeSvc.getStore_picsByStore_no(store_picVo.getStoreVO().getStore_no())); // ��Ʈw���X��list����,�s�Jrequest

				String url = requestURL + "?whichPage=" + whichPage; // �e�X�R�����ӷ��������ĴX��(�u�Ω�:istAllEmp.jsp)
				RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}

	// ���X�W�Ǫ��ɮצW�� (�]��API������method,�ҥH�����ۦ漶�g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // ���ե�

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
