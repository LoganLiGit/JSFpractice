<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>

<%-- 放進當前頁面 任意取用 --%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/member/register/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/front/member/register/lib/layer/1.9.3/layer.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input03.css">
      <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript">
	$(function(){
		$("#inputtr").find("a").click(function(){
			if($(this).attr("inputtr")=='inputtr'){
				$("#inputtrform").submit();
			}
		})
	});
	
	 $(function() {
		    $( "#m5" ).datepicker( {dateFormat:'yy-mm-dd'});
		  });
	  
	 
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員註冊</title>
</head>
<body>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/member.do" id="inputtrform" enctype="multipart/form-data">


<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
	<br>
		<h2 class="align_c font_normal blue">會員修改</h2>
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
			<p>請修改填寫下方內容，以完善您的資料！</p>
			<ul class="apLogin">
						<li>
							<label>帳號:</label>	                        
	                        <label  name="mem_account" value="${memberVO.mem_account}"  placeholder='請輸入帳號' id=m1 style="text-align:left">&nbsp;${memberVO.mem_account}</label>
                        </li>
                        
					    <li>
                        <label>密碼:</label>
                        <input type="password" name="mem_password" value="${memberVO.mem_password}" placeholder='請輸入密碼' id=m2 >                       
                        </li>
                                 
                        <li>
                        <label>姓名:</label>
                        <input type="text" name="mem_name" value="${memberVO.mem_name}"  placeholder='姓名' id=m3>
                        </li>
                        
                        <li>
                        <label>暱稱:</label>
                        <input type="text" name="mem_nickname" value="${memberVO.mem_nickname}"  placeholder='暱稱' id=m4>
                        </li>
                        
                        <li>
                        <label>性別:</label>
                        <label  name="mem_sex" value="${memberVO.mem_sex}"  style="text-align:left">&nbsp;${memberVO.mem_sex}</label>
	
                        </li> 
                        
						<li>
                        <label>生日:</label>
                        <label type="text" id=m5 name="mem_birthday"  value="${memberVO.mem_birthday}"  style="text-align:left">&nbsp;${memberVO.mem_birthday}</label>
                        </li>
                        
						<li>
                        <label>電子郵件:</label>
                        <input type="text" name="mem_email" value="${memberVO.mem_email}"  placeholder='email' id=m6>
                        </li>
                        
                        <li>
                        <label>身分證號碼:</label>
                        <label type="text" id=m7 name="mem_idcard" value="${memberVO.mem_idcard}" style="text-align:left">&nbsp;${memberVO.mem_idcard}</label>
                        </li>
                        
                        <li>
                        <label>大頭貼:</label>
                        <input type="file" id=m8 name="mem_photo" value="${memberVO.mem_photo}"> </p>
                        </li>
                        
                        <li>
                        <label>郵遞區號:</label>
                        <input type="text" id=m9 name="mem_zipcode" value="${memberVO.mem_zipcode}" > </p>
                        </li>
                        
                        <li>
                        <label>城市:</label>
                        <input type="text" id=m10 name="mem_city" value="${memberVO.mem_city}" ></p>
                        </li>
                        
                        <li>
                        <label>區域:</label>
                        <input type="text" id=m11 name="mem_district" value="${memberVO.mem_district}" ></p>
                        </li>
                        
                        <li>
                        <label>地址:</label>
                        <input type="text" id=m12 name="mem_address" value="${memberVO.mem_address}" ></p>
                        </li>
                        
                        <li>
                        <label>家裡電話:</label>
                        <input type="text" id=m13 name="mem_phone" value="${memberVO.mem_phone}"></p>
                        </li>
                        
                        <li>
                        <label>手機:</label>
                        <input type="text" id=m14 name="mem_cellphone" value="${memberVO.mem_cellphone}" ></p>
                        </li>
                        
                        <li>
                        <label>專長:</label>
                        <input type="text"  id=m15 name="mem_skill" value="${memberVO.mem_skill}" ></p>
                        </li>
                        
                        <li>
                        <label>菜色喜好:</label>
                        <input type="text" id=m16 name="mem_hobby" value="${memberVO.mem_hobby}" ></p>
                        </li>
                        
						<li>
                       <label>感情狀態:</label>	
					    <select name="mem_relationship" id="m17" style="width:170px;" value="${memberVO.mem_relationship}">
					      <option value="0" selected>未婚</option>
					      <option value="1">已婚</option>
					    </select>
                        </li>

						<li>
                        <label>簡單的自我介紹:</label>
                        <input type="textarea" id=m18 rows="5" id="comment" name="mem_intro" size="50" value="${memberVO.mem_intro}" />
                        </br>
                        </li>
						

<!-- 						<label>GCM:</label> -->

                        <input type="hidden" id=m19 name="mem_redid" value="${memberVO.mem_redid}"  ></p>

			</ul>
			<div class="next_btn">
            	<a href="#" class="btn blue_button btn_Submit" inputtr="inputtr" >送出資料</a>
            	<button type="button" id="btn">快速註冊</button>
            </div>
     </div>
     <div class="message margin_t30">
            <h4>注意事項：</h4>
            <ol>
            	<li>身分證字號將會新增至JuicyTalk會員專區基本資料中，用於日後身分驗證使用。</li>
            	<li>安全提示問題與答案用於資料修改及密碼重設，請務必妥善保管。</li>
            </ol>
      </div>	
</div>
<input type="hidden" name="action" value="update">
</FORM>

</html>