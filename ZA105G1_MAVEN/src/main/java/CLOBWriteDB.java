import java.sql.*;
import java.io.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;


public class CLOBWriteDB {

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

        String sql = "update article set ARTICLE_CONTENT=? where article_no = 2";
       
        //CLOB的文字部分
        String wordStr = "<p>我愛一條柴大戰奇淫合歡散</p>";  
        
        try {
          con = DriverManager.getConnection(url, userid, passwd);
          
          /**字串編碼(BASE64)與解碼 */
          Base64 base64Word = new Base64();
          String encodeWord = new String(base64Word.encode(wordStr.getBytes()));
          System.out.println(encodeWord);
          
          String decodeWord = new String(base64Word.decode(encodeWord.getBytes()));
          System.out.println(decodeWord);
          
          
          /**
           *  圖片編碼(BASE64)
           *  fileName：要轉成BASE64的圖片
           */
          // 將檔案透過imageio轉成byte
          String fileName = "D:\\555.jpg";
          BufferedImage img = ImageIO.read(new File(fileName));
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ImageIO.write(img, "jpg", bos);       //依照圖片格式修改檔案格式
          byte[] imgBytes = bos.toByteArray();
          bos.close();
          
          // 將byte執行編碼
          String encodeImg = Base64.encodeBase64String(imgBytes);
          
          //圖加上文字全部轉成Base64編碼後加在一起
          String imgStr = "<img src='data:image/jpeg;base64, " + encodeImg + "' />";
          String combineStr = wordStr + imgStr; 
          
//          將圖文一起寫入資料庫
          pstmt = con.prepareStatement(sql);
          pstmt.setString(1, combineStr);
          System.out.println("打完收工");
          pstmt.executeUpdate();
          System.out.println("打完收工");
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
