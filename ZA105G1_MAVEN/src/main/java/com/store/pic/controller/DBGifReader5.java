package com.store.pic.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader5 extends HttpServlet {
	Connection con;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		String pic_no = req.getParameter("pic_no");
		try {
			req.setCharacterEncoding("Big5");
			Statement stmt = con.createStatement();
			
			String pic_no2=new String(pic_no.getBytes("ISO-8859-1"),"Big5");
			ResultSet rs = stmt.executeQuery(
					"SELECT	store_pic from store_pic where pic_no ='"+pic_no2+"'");
			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("store_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				res.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			FileInputStream in = new FileInputStream("D:/ZA105_Web/eclipse_WTP_workspace1/ZA105G1_0403/WebContent/store_pic/NoPic.jpg");
//			byte[] buf = new byte[4 * 1024]; // 4K buffer
//			int len;
//			while ((len = in.read(buf)) != -1) {
//				out.write(buf, 0, len);
//			}
//			in.close();
			
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
