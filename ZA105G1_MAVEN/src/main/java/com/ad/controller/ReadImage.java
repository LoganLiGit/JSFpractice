package com.ad.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ReadImage extends HttpServlet {
	Connection con;
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	
		res.setContentType("image/gif; charset=UTF-8");
		ServletOutputStream out = res.getOutputStream();
		String ad_no = req.getParameter("ad_no");
		try {
			req.setCharacterEncoding("UTF-8");
			Statement stmt = con.createStatement();
			
			String ad_no2=new String(ad_no.getBytes("ISO-8859-1"),"UTF-8");
			ResultSet rs = stmt.executeQuery(
					"SELECT	ad_images from advertisement where ad_no ='"+ad_no2+"'");
			
			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("ad_images"));
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
//			FileInputStream in = new FileInputStream(temp);
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
