package com.member.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class DBGifReader13 extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		
		req.setCharacterEncoding("Big5");//��
		
		
		
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String mem_no = null;
			String store_no = null;
			String photo_no = null;
			mem_no = req.getParameter("mem_no");
			store_no = req.getParameter("store_no");
			photo_no = req.getParameter("photo_no");
			
			if(mem_no!=null){
				String mem_no2 = new String(mem_no.getBytes("ISO-8859-1"),"Big5");
				ResultSet rs = stmt.executeQuery(
					"SELECT mem_photo FROM member_table WHERE mem_no = '"+mem_no2+"'");

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("mem_photo"));
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
				
			}
			
			if(store_no!=null){
				String store_no2 = new String(store_no.getBytes("ISO-8859-1"),"Big5");
				ResultSet rs = stmt.executeQuery(
					"SELECT store_pic FROM store_pic WHERE store_no = '"+store_no2+"'");

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
				
			}
			
			if(photo_no!=null){
				String photo_no2 = new String(photo_no.getBytes("ISO-8859-1"),"Big5");
				ResultSet rs = stmt.executeQuery(
					"SELECT mem_photo FROM album WHERE photo_no = '"+photo_no2+"'");

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("mem_photo"));
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
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ZA105G1");
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
