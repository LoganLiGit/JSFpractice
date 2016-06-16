

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WriteCLOB")
public class WriteCLOB extends HttpServlet {

  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  String driver = "oracle.jdbc.driver.OracleDriver";
	  String url = "jdbc:oracle:thin:@localhost:1521:XE";
	  String userid = "ZA105G1";
	  String passwd = "ZA105G1";
	  ResultSet rs = null; 
	  
	  
    req.setCharacterEncoding("UTF-8");
    res.setContentType("text/html; charset=UTF-8");
    PrintWriter out = res.getWriter();

    String sql = "select article_content from article where article_no = ?";
    int number = 3;
    
    try {
      Class.forName(driver);
      con = DriverManager.getConnection(url, userid, passwd);
      
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, number);
      rs = pstmt.executeQuery();
      
      
      out.println("<html><body>");
      if (rs.next()){
        String test = rs.getString("article_content");
        out.println(test +"<br>");
      }
      out.println("</body></html>");

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

