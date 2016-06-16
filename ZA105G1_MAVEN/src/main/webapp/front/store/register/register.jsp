<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.store.model.*"%>




<%-- 放進當前頁面 任意取用 --%>
<html>
<head>
<script src="<%=request.getContextPath()%>/front/store/register/js/1.12.0.jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/store/register/js/dk-tw-citySelector.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/register/css/input.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/register/css/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/register/css/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/store/register/css/input03.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
	$(function() {
		$('.form-1').dk_tw_citySelector('.county', '.district', '.zipcode');
		
	});
</script>
<script >

	
	
	 $(document).ready(function() {
		 $("#inputtr").find("a").click(function(){
				if($(this).attr("inputtr")=='inputtr'){
					$("#inputtrform").submit();
				}
			}); 
	 });
	 
	 
	  $(document).ready(function() {
		  $("#mgcbtn").click(function() {
				$("#m1").val("a123456");
// 				$("#m2").val("a123156");
				$("#m3").val("傑尼龜噴水餐廳");
// 				$("#m4").val("台北市");
// 				$("#m5").val("中正區");
// 				$("#m6").val("軟趴趴");

				$("#m7").val("中華路二段75巷20號1樓");
				$("#m8").val("0223117318");
				$("#m9").val("海鮮料理");
				$("#m10").val("精靈寶可夢傑尼龜");
				$("#m11").val(0);
				$("#m12").val("Dalanpa1991@gmail.com");
				$("#m13").val("H124073978");
				$("#m14").val("0978586187");
				$("#m15").val("121.506874");
				$("#m16").val("25.033512");
				$("#m17").val("以會噴水的傑尼龜店長為主題的日式料理店，豐富的菜色與高雅的裝潢讓你就算在台灣也能享受到日式高級的品味，周ㄧ~周四11：00-22:00 周五11:00-23:00 周六10:00-23:00 周日10:00-22:00");
			});
		  $("#inputtr").find("a").click(function(){
				if($(this).attr("inputtr")=='inputtr'){
					$("#inputtrform").submit();
				}
			});
	  });
	  
	 	 var store_pic1 = function(event) {
			var output = document.getElementById('output1');
			output.src = URL.createObjectURL(event.target.files[0]);
			
		};
		var store_pic3 = function(event) {
			var output = document.getElementById('output3');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
		var store_pic2 = function(event) {
			var output = document.getElementById('output2');
			output.src = URL.createObjectURL(event.target.files[0]);
		};
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店家會員註冊</title>
</head>
<body>
	
	

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/store/store.do" id="inputtrform" class="form-1" enctype="multipart/form-data">


<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
	<br>
	
		<h2 class="align_c font_normal orange">店家會員註冊</h2>
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
						<p>請依序填寫下方內容，以完善您的資料！</p>
						<ul class="apLogin" >
	

						<li>
							<label><font style="color:red">*</font>帳號:</label>	                        
	                        <input type="TEXT" name="store_account" value=""  placeholder='請輸入帳號' id=m1>
                        </li>
                        
<!-- 					    <li> -->
<!--                         <label>密碼:</label> -->
<!--                         <input type="TEXT" name="store_password" size="15" value="" placeholder='請輸入密碼' id=m2>                        -->
<!--                         </li> -->
                                 
                        <li>
                        <label><font style="color:red">*</font>店家名稱:</label>
                        <input type="TEXT" name="store_name" value=""  placeholder='姓名' id=m3>
                        </li>
                        

                        <li>
                        <label><font style="color:red">*</font>店家城市:</label>
                        <div style="padding:10px 0px;">
                        <select class="county" name="store_city" style="width:195px;"  id=m4  ></select>
                        </div>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>店家區域:</label>
                        <div style="padding:10px 0px;">
                        <select class="district" name="store_district" style="width:195px;"  id=m5></select>
                        </div>
                        </li>
  
  
 						<li>
                        <label>店家郵遞區號:</label>
                        <input type="TEXT" class="zipcode" name="store_zipcode" value=""  id=m6 autocomplete="off" readonly>
                        </li>    
                                          
                        <li>
                        <label><font style="color:red">*</font>店家地址:</label>
                        <input type="TEXT" name="store_address"  value=""  id=m7>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>店家電話:</label>
                        <input type="TEXT" name="store_phone"  value=""  id=m8>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>店家類型:</label>
                        <input type="TEXT" name="store_type"  value=""  id=m9>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>聯絡人姓名:</label>
                        <input type="TEXT" name="manager_name"  value=""  id=m10>
                        </li>
                        

                        <li>
                        <label>聯絡人性別:</label>
                        <div class="btn-group" data-toggle="buttons" style="padding:10px;">
                            
                                <input type="radio" id="q156" name="manager_gender" value="0" checked />
                              	  男
                   
                                <input type="radio" id="q157" name="manager_gender" value="1" />
                       			   女			
                        </div>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>聯絡人電子郵件:</label>
                        <input type="email" name="manager_email" value=""  id=m12>
                        </li>
                        
                        <li>
                        <label>聯絡人身份證字號:</label>
                        <input type="TEXT" name="manager_id" value=""  id=m13>
                        </li>
                        
                        <li>
                        <label><font style="color:red">*</font>聯絡人手機:</label>
                        <input type="TEXT" name="manager_cellphone" value=""  id=m14>
                        </li>
                        
                        <li>
                        <label>店家介紹:</label>
                        <input type="text" id="m17" name="store_introduction" size="40" />                        
                        </li>
                          
                        <li>
                        <label>照片:</label>
                        <input style="width:170px" type="file" name="store_pic[]" accept="image/*" onchange="store_pic2(event)">
                        <input style="width:170px" type="file" name="store_pic[]" accept="image/*" onchange="store_pic1(event)">
						</li>
						<li>
						<label>&nbsp;</label>						
							<img style="padding-left:30px" id="output2" width="150" height="110" />
							<img style="padding-left:30px" id="output1" width="150" height="110" />
						</li>
						<li>
						<label>&nbsp;</label>						
					
						</li>
						

                        
                        <input type="hidden" name="store_longitude" value=0.0 id="m15">
                        <input type="hidden" name="store_latitude" value=0.0 id="m16">
                        
                       
			</ul>
			
			<div class="next_btn">
            	<a href="#" class="btn blue_button btn_Submit" inputtr="inputtr" >送出資料</a>
            	<button type="button" id="mgcbtn">快速註冊</button>
            </div>
     </div>
     <div class="message margin_t15">
            <h4>注意事項：</h4>
            <ol>
            	<li>身分證字號將會新增至JuicyTalk會員專區基本資料中，用於日後身分驗證使用。</li>
            	<li>為了作業上的流程，密碼將會在通過審核後寄至您所填寫的電子郵件信箱。</li>
            </ol>
      </div>	
</div>
<input type="hidden" name="action" value="insert">
</FORM>

</html>