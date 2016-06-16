+<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.store.model.*"%>

<%-- 放進當前頁面 任意取用 --%>
<html>
<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/update/css/input.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/update/css/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/update/css/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/update/css/input03.css">
      <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
	$(function(){
		$("#inputtr").find("a").click(function(){
			if($(this).attr("inputtr")=='inputtr'){
				$("#inputtrform").submit();
			}
		})
	});
	
	 
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" id="inputtrform" enctype="multipart/form-data">


<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
	<br>
		<h2 class="align_c font_normal blue">店家資料修改</h2>
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
							<label>店家帳號:</label>	                        
	                        <label  name="store_account" value="${storeVO.store_account}" id=m1 style="text-align:left">&nbsp;${storeVO.store_account}</label>
                        </li>
                        
					    <li>
                        <label>密碼:</label>
                        <input type="password" name="store_password" value="${storeVO.store_password}" placeholder='請輸入密碼' id=m2 >                       
                        </li>
                                 
                        <li>
                        <label>店家名稱:</label>
                        <input type="text" name="store_name" value="${storeVO.store_name}"  placeholder='姓名' id=m3>
                        </li>
                        
                        <li>
                        <label>註冊日期:</label>
                        <label  name="store_regist_Date" value="${storeVO.store_regist_date}"  id=m4 style="text-align:left">&nbsp;${storeVO.store_regist_date}</label>
                        </li>
                        
                        
						<li>
                        <label>郵遞區號:</label>
                        <label  id=m5 name="store_zipcode"  value="${storeVO.store_zipcode}"  style="text-align:left" >&nbsp;${storeVO.store_zipcode}</label>
                        </li>
                        
						<li>
                        <label>店家城市:</label>
                        <input type="text" name="store_city" value="${storeVO.store_city}"  id=m6>
                        </li>
                        
                        <li>
                        <label>店家區域:</label>
                        <input type="text" id=m7 name="store_district" value="${storeVO.store_district}" >
                        </li>
                        
                        
                        <li>
                        <label>店家地址:</label>
                        <input type="text" id=m9 name="store_address" value="${storeVO.store_address}" > </p>
                        </li>
                        
                        <li>
                        <label>店家電話:</label>
                        <input type="text" id=m10 name="store_phone" value="${storeVO.store_phone}" ></p>
                        </li>
                        
                        <li>
                        <label>店家類型:</label>
                        <input type="text" id=m11 name="store_type" value="${storeVO.store_type}" ></p>
                        </li>
                        
                        <li>
                        <label>聯絡人姓名:</label>
                        <input type="text" id=m12 name="manager_name" value="${storeVO.manager_name}" ></p>
                        </li>
                        
                        <li>
                        <label>聯絡人電子郵件:</label>
                        <input type="text" id=m13 name="manager_email" value="${storeVO.manager_email}"></p>
                        </li>
                        
                        <li>
                        <label>聯絡人手機:</label>
                        <input type="text" id=m14 name="manager_cellphone" value="${storeVO.manager_cellphone}" ></p>
                        </li>
                        
<!--                         <li> -->
<!--                         <label>信用卡號碼:</label> -->
<%--                         <input type="text"  id=m15 name="manager_credit_num" value="${storeVO.manager_credit_num}" ></p> --%>
<!--                         </li> -->
                      	<li>
                        <label>店家介紹:</label>
                        <input type="text" id="m17" name="store_introduction" value="${storeVO.store_introduction}" size="40" />                        
                        </li>
                        

			</ul>
			<div class="next_btn">
            	<a href="#" class="btn blue_button btn_Submit" inputtr="inputtr" >送出資料</a>
            	
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
<input type="hidden" name="action" value="update2">
</FORM>

</html>