package com.TicketType.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.naming.java.javaURLContextFactory;

import com.TicketNo.model.TicketNoJDBCDAO;
import com.TicketNo.model.TicketNoVO;


public class updateimage {

	public static void main(String[] args) throws IOException {
//		//直接把IOException 給他拋出 
//		//利用FileInputStream取得檔案
//		FileInputStream img = new FileInputStream("C:\\abc.jpg");
//		//
//		byte[] byt = new byte[img.available()];
//		img.read(byt);
//		img.close();
//		
//		updateimage.update(848400002,byt);

		
		for (int i=1;i<15;i++){
			FileInputStream img1 = new FileInputStream("C:\\JPG\\" + i + ".jpg");
			//
			byte[] byt1 = new byte[img1.available()];
			img1.read(byt1);
			img1.close();
			
			int no = 848400000;
			no = no + i;
			updateimage.update(no,byt1);
		}
		
		for (int j=1;j<18;j++){
			int no2 = 848400150;
			no2 = no2 + (j*10);
			for (int i=1;i<15;i++){
				FileInputStream img1 = new FileInputStream("C:\\JPG\\" + i + ".jpg");
				//
				byte[] byt1 = new byte[img1.available()];
				img1.read(byt1);
				img1.close();
				
				int no = no2;
				no = no + i;
				System.out.println(no +":" + i +":" + j);
				updateimage.update(no,byt1);
			}
		}

		
	}
	

	
	//UPDATE (修改) 的 SQL 指令 (有圖片版本)
	private static final String UPDATE =
			"UPDATE ticket_type set "		+
			"TICKETS_IMAGE=? "				+			
			"where tickets_type_no=?";

	//修改(包含圖片)
	public static void update(Integer tickets_type_no,byte[] tickets_image){
		//連線的帳號
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "prada";
		String passwd = "aa20070";
		//團購券種類編號	(tickets_type_no)
		//團購券名稱		(tickets_type_name)
		//上架時間			(upper_date)
		//下架時間			(lower_date)
		//申請時間			(application_date)
		//團購劵總數量		(tickets_total)
		//剩餘數量			(tickets_quantity)
		//團購劵單價格		(tickets_price)
		//團購劵狀態		(tickets_state)
		//店家編號			(store_no)
		//團購劵說明		(tickets_ex)
		//團購劵圖片		(tickets_image)
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setBytes(1,tickets_image);
			pstmt.setInt(2,tickets_type_no);
						
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
