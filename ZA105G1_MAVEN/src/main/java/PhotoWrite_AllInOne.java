import java.sql.*;
import java.io.*;

class PhotoWrite_AllInOne {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		writeGroup();
		writeMem();
		writeStore();
		writeAD();
	}
	public static void writeAD(){
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ZA105G1";
		String passwd = "ZA105G1";
		String picName[] = { "1.jpg", "3.jpg", "5.jpg", "7.jpg",  "9.jpg" };
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i <= 5; i++) {
				File pic = new File("WebContent\\front\\store\\images", picName[i-1]);
				// 相對路徑- picFrom
				// 絕對路徑- 譬如:
				// File pic = new File("x:\\aa\\bb\\picFrom", picName);
				long flen = pic.length();
				String fileName = pic.getName();
				int dotPos = fileName.indexOf('.');
			
				InputStream fin = new FileInputStream(pic);

				System.out.println("Update the database... "+i);
				pstmt = con.prepareStatement("UPDATE advertisement set ad_images=? where ad_no = ?");
				pstmt.setBinaryStream(1, fin, (int) flen);
				
				pstmt.setInt(2, i);
				
				// void pstmt.setBinaryStream(int parameterIndex, InputStream x,
				// int
				// length) throws SQLException
				int rowsUpdated = pstmt.executeUpdate();

				fin.close();
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	
	}
	
	public static void writeStore(){
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ZA105G1";
		String passwd = "ZA105G1";
		String picName[] = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg",
				"14.jpg", "15.jpg", "16.jpg", "17.jpg", "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg",
				"24.jpg", "25.jpg", "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg" };
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i <= 30; i++) {
				File pic = new File("WebContent\\front\\store\\images", picName[i-1]);
				// 相對路徑- picFrom
				// 絕對路徑- 譬如:
				// File pic = new File("x:\\aa\\bb\\picFrom", picName);
				long flen = pic.length();
				String fileName = pic.getName();
				int dotPos = fileName.indexOf('.');
				String fno = fileName.substring(0, dotPos);
				String format = fileName.substring(dotPos + 1);
				InputStream fin = new FileInputStream(pic);

				System.out.println("Update the database... "+i);
				pstmt = con.prepareStatement("UPDATE STORE_PIC set store_pic=? ,pic_format=? where pic_no = ?");
				pstmt.setBinaryStream(1, fin, (int) flen);
				pstmt.setString(2, format);
				pstmt.setInt(3, i);
				
				// void pstmt.setBinaryStream(int parameterIndex, InputStream x,
				// int
				// length) throws SQLException
				int rowsUpdated = pstmt.executeUpdate();

				fin.close();
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	
	}
	public static void writeMem(){
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ZA105G1";
		String passwd = "ZA105G1";
		String picName[] = { "100.png", "101.png", "102.png", "103.png", "104.png", "105.png",
				"106.jpg", "107.jpg"};
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i <= 8; i++) {
				File pic = new File("WebContent\\front\\store\\images", picName[i-1]);
				// ?��對路�?- picFrom
				// 絕�?�路�?- 譬�??:
				// File pic = new File("x:\\aa\\bb\\picFrom", picName);
				long flen = pic.length();
				String fileName = pic.getName();
				int dotPos = fileName.indexOf('.');
				String fno = fileName.substring(0, dotPos);
				String format = fileName.substring(dotPos + 1);
				InputStream fin = new FileInputStream(pic);

				System.out.println("Update the database... "+i);
				pstmt = con.prepareStatement("UPDATE member_table set mem_photo=? where mem_no = ?");
				pstmt.setBinaryStream(1, fin, (int) flen);
			
				pstmt.setInt(2, i);
				
				// void pstmt.setBinaryStream(int parameterIndex, InputStream x,
				// int
				// length) throws SQLException
				int rowsUpdated = pstmt.executeUpdate();

				fin.close();
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public static void writeGroup(){
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ZA105G1";
		String passwd = "ZA105G1";
		String picName[] = { "2.jpg", "4.jpg", "6.jpg", "8.jpg", "10.jpg", "12.jpg",
				"14.jpg", "16.jpg", "18.jpg", "20.jpg"};
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i <= 10; i++) {
				File pic = new File("WebContent\\front\\store\\images", picName[i-1]);
				// 相對路徑- picFrom
				// 絕對路徑- 譬如:
				// File pic = new File("x:\\aa\\bb\\picFrom", picName);
				long flen = pic.length();
				String fileName = pic.getName();
				int dotPos = fileName.indexOf('.');
				String fno = fileName.substring(0, dotPos);
				String format = fileName.substring(dotPos + 1);
				InputStream fin = new FileInputStream(pic);

				System.out.println("Update the database... "+i);
				pstmt = con.prepareStatement("UPDATE group_table set group_photo=? where group_no = ?");
				pstmt.setBinaryStream(1, fin, (int) flen);
			
				pstmt.setInt(2, i);
				
				// void pstmt.setBinaryStream(int parameterIndex, InputStream x,
				// int
				// length) throws SQLException
				int rowsUpdated = pstmt.executeUpdate();

				fin.close();
			}

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
	}
}