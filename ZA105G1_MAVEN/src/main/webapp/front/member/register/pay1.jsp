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
			$("#m3").val("�K���T�U�ܤE");
			$("#m4").val("�ܤE");
			$("#m5").val("1999-09-09");
			$("#m6").val("p5251@gmail.com");
			$("#m7").val("A258357892");
			$("#m8").val("Dolly Duck");
			$("#m9").val("320");
			$("#m10").val("�x�_��");
			$("#m11").val("�j�w��");
			$("#m12").val("�Ѷ���12��");
			$("#m13").val("020433989");
			$("#m14").val("0978586177");
			$("#m15").val("�ۺq�����J");
			$("#m16").val("���h���צN�h��");
			$("#m17").val("0");
			$("#m18").val("���H�ͩʼֵ��n�I�A�P�@�L���A�o�ѿW�p");
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
                    <h2><span></span>�|�����U</h2>
                    <%-- ���~��C --%>
					<c:if test="${not empty errorMsgs}">
						<font color='red'>�Эץ��H�U���~:
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
                    
                     	<lable>�b��:</lable>
                        </br>
                        <input type="text" name="mem_account" value="" onfocus="this.value = '';" placeholder='�п�J�b��' id=m1>
                        </br>
                        
                        <lable>�K�X:</lable>
                        </br>
                        <input type="password" name="mem_password" value="" onfocus="this.value = '';" placeholder='�п�J�K�X' id=m2>
                        </br>
                        
                        <lable>�m�W:</lable>
                        </br>
                        <input type="text" name="mem_name" value="First Name" onfocus="this.value = '';" placeholder='�m�W' id=m3>
                        </br>
                        
                        <lable>�ʺ�:</lable>
                        </br>
                        <input type="text" name="mem_nickname" value="" onfocus="this.value = '';" placeholder='�ʺ�' id=m4>
                         </br>
                         
                        <lable>�ʧO:</lable>
                        </br>
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-default">
                                <input type="radio" id="q156" name="mem_sex" value="�k" checked />
                                <lable>male</lable>
                            </label>
                            <label class="btn btn-default">
                                <input type="radio" id="q157" name="mem_sex" value="�k" />
                                <lable>female</lable>
                            </label>
                        </div>
                        </br>

                        <lable>�ͤ�:</lable>
                        </br>
                        <input type="text" id=m5 name="mem_birthday"  /></p>
                        </br>
                        

                        <lable>�q�l�l��:</lable>
                        </br>
                        <input type="text" name="mem_email" value="Email Address" onfocus="this.value = '';" placeholder='email' id=m6>
                        </br>
                        
                        <lable>�����Ҹ��X:</lable>
                        </br>
                        <input type="text" id=m7 name="mem_idcard"  ></p>
                        </br>
                        
                        <lable>�j�Y�K:</lable>
                        </br>
                        <input type="file" id=m8 name="mem_photo"> </p>
                        </br>
                        
                        <lable>�l���ϸ�:</lable>
                        </br>
                        <input type="text" id=m9 name="mem_zipcode" > </p>
                        </br>
                        
                        <lable>����:</lable>
                        </br>
                        <input type="text" id=m10 name="mem_city"  ></p>
                        </br>
                        
                        <lable>�ϰ�:</lable>
                        </br>
                        <input type="text" id=m11 name="mem_district"  ></p>
                        </br>
                        
                        <lable>�a�}:</lable>
                        </br>
                        <input type="text" id=m12 name="mem_address"  ></p>
                        </br>
                        
                        <lable>�a�̹q��:</lable>
                        </br>
                        <input type="text" id=m13 name="mem_phone" ></p>
                        </br>
                        
                        <lable>���:</lable>
                        </br>
                        <input type="text" id=m14 name="mem_cellphone"  ></p>
                        </br>
                        
                        <lable>�M��:</lable>
                        </br>
                        <input type="text"  id=m15 name="mem_skill" ></p>
                        </br>
                        
                        <lable>���ߦn:</lable>
                        </br>
                        <input type="text" id=m16 name="mem_hobby"  ></p>
                        </br>
                        
<!--                         <lable>�P�����A:</lable>s -->
<!--                         </br> -->
<!--                         <input type="text" id=m17 name="mem_relationship"  ></p> -->
<!--                         </br> -->

                       <lable>�P�����A:</lable>	
						</br>	
						
					    <select name="mem_relationship" id="m17">
					      <option value="0" selected>���B</option>
					      <option value="1">�w�B</option>
					    </select>
					    
						</br>

                        <lable>²�檺�ۧڤ���:</lable></br>
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
                            <input type="submit"  value="�T�{�e�X">
                            <button type="button" id="btn">�ֳt���U</button>
                        </div>
                        <div class="clear"> </div>
                    </div>

                </form>
            </div>

</body>

</html>