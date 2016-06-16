<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>




<%-- 放進當前頁面 任意取用 --%>
<html>
<head>

<script src="<%=request.getContextPath()%>/front/member/register/js/1.12.0.jquery.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script> -->
<script src="js/dk-tw-citySelector.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/member/register/css/input03.css">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

 
	<script>
	$(function() {
		$('.form-1').dk_tw_citySelector('.county', '.district', '.zipcode');
		
	});
	</script>
<script type="text/javascript">

	
	 $(function() {
		    $( "#m5" ).datepicker( {dateFormat:'yy-mm-dd'});
		  });
	 $(document).ready(function() {
		 $("#inputtr").find("a").click(function(){
				if($(this).attr("inputtr")=='inputtr'){
					$("#inputtrform").submit();
				}
			}); 
	 });
	 
	 
	  $(document).ready(function() {
		  $("#mgcbtn").click(function() {
				$("#m1").val("aaaa");
				$("#m2").val("aaaa");
				$("#m3").val("皮卡丘陵地");
				$("#m4").val("精靈寶可夢");
				$("#m5").val("1999-09-09");
				$("#m6").val("p5251@gmail.com");
				$("#m7").val("A258357892");
// 				$("#m8").val("Dolly Duck");
				$("#m9").val("106");
				$("#m10").val("台北市");
				//$("#m11").val("大安區");
				
				$("#m12").val("天隆路12號");
				$("#m13").val("020433989");
				$("#m14").val("0978586177");
				$("#m15").val("唱歌掉麥克");
				$("#m16").val("雙層牛肉吉士堡");
				$("#m17").val("0");
				$("#m18").val("本人生性樂善好施，與世無爭，得天獨厚");
				$("#m19").val("1");
				
				if ($("#m10").prop("checked")== true){
					$("#m11").val("大安區");
				}
			});
		  $("#inputtr").find("a").click(function(){
				if($(this).attr("inputtr")=='inputtr'){
					$("#inputtrform").submit();
				}
			});
	  });
</script> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>會員註冊</title>
</head>
<body>
	
	

<FORM METHOD="post" ACTION="member.do" id="inputtrform" class="form-1" enctype="multipart/form-data">


<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
	<br>
	
		<h2 class="align_c font_normal blue">會員註冊</h2>
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
	                        <input type="text" name="mem_account" value=""  placeholder='請輸入帳號' id=m1>
                        </li>
                        
					    <li>
                        <label><font style="color:red">*</font>密碼:</label>
                        <input type="password" name="mem_password" value="" placeholder='請輸入密碼' id=m2>                       
                        </li>
                                 
                        <li>
                        <label><font style="color:red">*</font>姓名:</label>
                        <input type="text" name="mem_name" value=""  placeholder='姓名' id=m3>
                        </li>
                        
                        <li>
                        <label>暱稱:</label>
                        <input type="text" name="mem_nickname" value=""  placeholder='暱稱' id=m4>
                        </li>
                        
                        <li>
                        <label>性別:</label>
                        <div class="btn-group" data-toggle="buttons" style="padding:10px;">
                            
                                <input type="radio" id="q156" name="mem_sex" value="男" checked />
                                male
                   
                                <input type="radio" id="q157" name="mem_sex" value="女" />
                                female
                        </div>
                        </li> 
                        
						<li>
                        <label>生日:</label>
                        <input type="date" id=m5 name="mem_birthday"  />
                        </li>
                        
						<li>
                        <label>電子郵件:</label>
                        <input type="email" name="mem_email" value=""  placeholder='email' id=m6>
                        </li>
                        
                        <li>
                        <label>身分證號碼:</label>
                        <input type="text" id=m7 name="mem_idcard"  ></p>
                        </li>
                        
                        <li>
                        <label>大頭貼:</label>
                        <input type="file" id=m8 name="mem_photo"> </p>
                        </li>
                        
<!--                         <li> -->
<!--                         <label>郵遞區號:</label> -->
<!--                         <input type="text" id=m9 name="mem_zipcode" > </p> -->
<!--                         </li> -->
                        
                        
                        
<!--                         <li> -->
<!--                         <label>城市:</label> -->
<!--                         <select name="mem_city"  > -->
<%--                         	<c:forEach var="zipcodesVO" items="${list}"> --%>
<%-- 					            <option value="${zipcodesVO.zipcodes_city}">${zipcodesVO.zipcodes_city}</option> --%>
<%-- 					        </c:forEach> --%>
<!--                         </select > -->
<!--                         </li> -->
                        
                        
		<!--                         <li> -->
		<!--                         <label>城市:</label> -->
		<!--                         <input type="text" id=m10 name="mem_city"  ></p> -->
		<!--                         </li> -->
		                        
		<!--                         <li> -->
		<!--                         <label>區域:</label> -->
		<!--                         <input type="text" id=m11 name="mem_district"  ></p> -->
		<!--                         </li> -->
		<!--                         <li> -->

<!-- 						<li> -->
<!-- 						<label>區域:</label> -->
<!--                         <select name="mem_district" > -->
<%--                         	<c:forEach var="zipcodesVO" items="${list2}"> --%>
<%-- 					            <option value="${zipcodesVO.zipcodes_district}">${zipcodesVO.zipcodes_district}</option> --%>
<%-- 					        </c:forEach> --%>
<!--                         </select > -->
<!--                         </li> -->
                        
                        <li>
                        <label>城市:</label>
                        	<div style="padding:10px 0px;">
								<select class="county" id="m10" name="mem_city" style="width:170px;"></select></p>
							</div>
						</li>	
						
						<li>
							<label>區域:</label>
							<div style="padding:10px 0px;">
					        	<select class="district" id="m11" name="mem_district" style="width:170px;"></select></p>
					   	    </div>
					    </li>
					    
					    <li>   
					    	<label>郵遞區號:</label> 
					        <input class="zipcode" id="m9" name="mem_zipcode" value="" type="text" placeholder="郵遞區號" autocomplete="off" readonly></p>
						</li>

                        
                        <li>
                        <label>地址:</label>
                        <input type="text" id=m12 name="mem_address"  ></p>
                        </li>
                        
                        <li>
                        <label>家裡電話:</label>
                        <input type="text" id=m13 name="mem_phone" ></p>
                        </li>
                        
                        <li>
                        <label>手機:</label>
                        <input type="text" id=m14 name="mem_cellphone"  ></p>
                        </li>
                        
                        <li>
                        <label>專長:</label>
                        <input type="text"  id=m15 name="mem_skill" ></p>
                        </li>
                        
                        <li>
                        <label>菜色喜好:</label>
                        <input type="text" id=m16 name="mem_hobby"  ></p>
                        </li>
                        
						<li>
                       <label>感情狀態:</label>	
                       <div style="padding:10px 0px ;">
					    <select name="mem_relationship" id="m17" style="width:170px;">
					      <option value="0" selected>未婚</option>
					      <option value="1">已婚</option>
					    </select>
					    </div>
                        </li>

						<li>
                        <label>簡單的自我介紹:</label>
                        <input type="text" id="m18" name="mem_intro" size="40" />                        
                        </li>
						

<!-- 						<label>GCM:</label> -->

                        <input type="hidden" id=m19 name="mem_redid"  ></p>

			</ul>
			
			<div class="next_btn">
            	<a href="#" class="btn blue_button btn_Submit" inputtr="inputtr" >送出資料</a>
            	<button type="button" id="mgcbtn">快速註冊</button>
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
<input type="hidden" name="action" value="insert">
</FORM>

</html>