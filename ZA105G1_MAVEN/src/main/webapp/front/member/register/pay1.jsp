<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html>

<head>
    <title>Register</title>
    <link href="css/register.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="application/x-javascript">
        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }
    </script>
      <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
	    $( "#m5" ).datepicker( {dateFormat:'yy-mm-dd'});
	  });
  
  $(document).ready(function() {
	  $("#btn").click(function() {
			$("#m1").val("aaa");
			$("#m2").val("bbb");
			$("#m3").val("八卦三下至九");
			$("#m4").val("至九");
			$("#m5").val("1999-09-09");
			$("#m6").val("p5251@gmail.com");
			$("#m7").val("A258357892");
			$("#m8").val("Dolly Duck");
			$("#m9").val("320");
			$("#m10").val("台北市");
			$("#m11").val("大安區");
			$("#m12").val("天隆路12號");
			$("#m13").val("020433989");
			$("#m14").val("0978586177");
			$("#m15").val("唱歌掉麥克");
			$("#m16").val("雙層牛肉吉士堡");
			$("#m17").val("0");
			$("#m18").val("本人生性樂善好施，與世無爭，得天獨厚");
			$("#m19").val("1");
			
			
		});
  });

  </script>
    <!--webfonts-->
    <link href='http://fonts.googleapis.com/css?family=Lobster|Pacifico:400,700,300|Roboto:400,100,100italic,300,300italic,400italic,500italic,500' ' rel='stylesheet type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100,500,600,700,300 ' rel='stylesheet ' type='text/css '>
    <!--webfonts-->
</head>

<body>
    <!--start-login-form-->
    <div class="main">
        <div class="wrap">
            <div class="Regisration">
                <div class="Regisration-head">
                    <h2><span></span>會員註冊</h2>
                    <%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li>${message}</li>
							</c:forEach>
						</ul>
						</font>
					</c:if>
                </div>
                <FORM METHOD="post" ACTION="member.do" name="form1" enctype="multipart/form-data">
                    <div class="info">
                    
                     	<lable>帳號:</lable>
                        </br>
                        <input type="text" name="mem_account" value="" onfocus="this.value = '';" placeholder='請輸入帳號' id=m1>
                        </br>
                        
                        <lable>密碼:</lable>
                        </br>
                        <input type="password" name="mem_password" value="" onfocus="this.value = '';" placeholder='請輸入密碼' id=m2>
                        </br>
                        
                        <lable>姓名:</lable>
                        </br>
                        <input type="text" name="mem_name" value="First Name" onfocus="this.value = '';" placeholder='姓名' id=m3>
                        </br>
                        
                        <lable>暱稱:</lable>
                        </br>
                        <input type="text" name="mem_nickname" value="" onfocus="this.value = '';" placeholder='暱稱' id=m4>
                         </br>
                         
                        <lable>性別:</lable>
                        </br>
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default">
                                <input type="radio" id="q156" name="mem_sex" value="男" checked />
                                <lable>male</lable>
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" id="q157" name="mem_sex" value="女" />
                                <lable>female</lable>
                            </label>
                        </div>
                        </br>

                        <lable>生日:</lable>
                        </br>
                        <input type="text" id=m5 name="mem_birthday"  /></p>
                        </br>
                        

                        <lable>電子郵件:</lable>
                        </br>
                        <input type="text" name="mem_email" value="Email Address" onfocus="this.value = '';" placeholder='email' id=m6>
                        </br>
                        
                        <lable>身分證號碼:</lable>
                        </br>
                        <input type="text" id=m7 name="mem_idcard"  ></p>
                        </br>
                        
                        <lable>大頭貼:</lable>
                        </br>
                        <input type="file" id=m8 name="mem_photo"> </p>
                        </br>
                        
                        <lable>郵遞區號:</lable>
                        </br>
                        <input type="text" id=m9 name="mem_zipcode" > </p>
                        </br>
                        
                        <lable>城市:</lable>
                        </br>
                        <input type="text" id=m10 name="mem_city"  ></p>
                        </br>
                        
                        <lable>區域:</lable>
                        </br>
                        <input type="text" id=m11 name="mem_district"  ></p>
                        </br>
                        
                        <lable>地址:</lable>
                        </br>
                        <input type="text" id=m12 name="mem_address"  ></p>
                        </br>
                        
                        <lable>家裡電話:</lable>
                        </br>
                        <input type="text" id=m13 name="mem_phone" ></p>
                        </br>
                        
                        <lable>手機:</lable>
                        </br>
                        <input type="text" id=m14 name="mem_cellphone"  ></p>
                        </br>
                        
                        <lable>專長:</lable>
                        </br>
                        <input type="text"  id=m15 name="mem_skill" ></p>
                        </br>
                        
                        <lable>菜色喜好:</lable>
                        </br>
                        <input type="text" id=m16 name="mem_hobby"  ></p>
                        </br>
                        
<!--                         <lable>感情狀態:</lable>s -->
<!--                         </br> -->
<!--                         <input type="text" id=m17 name="mem_relationship"  ></p> -->
<!--                         </br> -->

                       <lable>感情狀態:</lable>	
						</br>	
						
					    <select name="mem_relationship" id="m17">
					      <option value="0" selected>未婚</option>
					      <option value="1">已婚</option>
					    </select>
					    
						</br>

                        <lable>簡單的自我介紹:</lable></br>
                        <textarea class="form-control"  id=m18 rows="5" id="comment" name="mem_intro" size="60" ></textarea>
						</br>
						


						
<!-- 						<lable>GCM:</lable> -->
                        </br>
                        <input type="hidden" id=m19 name="mem_redid"  ></p>
                        </br>

						
                    </div>
                    <div class="Remember-me">
                        <div class="p-container">
                            <label class="checkbox">
                                <input type="checkbox" name="checkbox" checked><i></i>I agree to the Terms and Conditions</label>
                            <div class="clear"></div>
                        </div>

                        <div class="submit">
                        	<input type="hidden" name="action" value="insert">
                            <input type="submit"  value="確認送出">
                            <button type="button" id="btn">快速註冊</button>
                        </div>
                        <div class="clear"> </div>
                    </div>

                </form>
            </div>

</body>

</html>