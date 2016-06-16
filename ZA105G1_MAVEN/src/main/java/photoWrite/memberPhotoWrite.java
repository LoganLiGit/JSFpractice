package photoWrite;
import java.sql.*;
import java.io.*;


class memberPhotoWrite {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String userid = "ZA105G1";
		String passwd = "ZA105G1";
		String picName[] = { "1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg" };
	
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			for (int i = 1; i <= 8; i++) {
				File pic = new File("D:\\ZA105G1\\ZA105G1_workspace\\ZA105G10221\\WebContent\\memberPhoto", picName[i-1]);
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
}