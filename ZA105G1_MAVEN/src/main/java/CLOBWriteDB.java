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
       
        //CLOB����r����
        String wordStr = "<p>�ڷR�@����j�ԩ_�]�X�w��</p>";  
        
        try {
          con = DriverManager.getConnection(url, userid, passwd);
          
          /**�r��s�X(BASE64)�P�ѽX */
          Base64 base64Word = new Base64();
          String encodeWord = new String(base64Word.encode(wordStr.getBytes()));
          System.out.println(encodeWord);
          
          String decodeWord = new String(base64Word.decode(encodeWord.getBytes()));
          System.out.println(decodeWord);
          
          
          /**
           *  �Ϥ��s�X(BASE64)
           *  fileName�G�n�নBASE64���Ϥ�
           */
          // �N�ɮ׳z�Limageio�নbyte
          String fileName = "D:\\555.jpg";
          BufferedImage img = ImageIO.read(new File(fileName));
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          ImageIO.write(img, "jpg", bos);       //�̷ӹϤ��榡�ק��ɮ׮榡
          byte[] imgBytes = bos.toByteArray();
          bos.close();
          
          // �Nbyte����s�X
          String encodeImg = Base64.encodeBase64String(imgBytes);
          
          //�ϥ[�W��r�����নBase64�s�X��[�b�@�_
          String imgStr = "<img src='data:image/jpeg;base64, " + encodeImg + "' />";
          String combineStr = wordStr + imgStr; 
          
//          �N�Ϥ�@�_�g�J��Ʈw
          pstmt = con.prepareStatement(sql);
          pstmt.setString(1, combineStr);
          System.out.println("�������u");
          pstmt.executeUpdate();
          System.out.println("�������u");
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
