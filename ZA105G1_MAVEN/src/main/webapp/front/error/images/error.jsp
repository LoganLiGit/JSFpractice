<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
<title>權限錯誤</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href='http://fonts.googleapis.com/css?family=Metal+Mania' rel='stylesheet' type='text/css'>
<style type="text/css">
body{
	font-family: 'Metal Mania', cursive;}
body{
	background-image:url(images/ERRORbackground.png); 
	background-repeat:repeat; 
	background-size:contain;}	
.wrap{
	margin:0 auto;
	width:1000px;
	}

.logo p{
	color:white;
	font-size:20px;
	margin-top:1px;
	text-align:center;}	
.logo p span{
	color:lightgreen;}	
	
.sub a{
	color:#d9d900;
	background:#8a8a7b;
	text-decoration:none;
	padding:5px;
	font-size:13px;
	font-family: arial, serif;
	font-weight:bold;
	font-size:20px;
	}	

.ErrorPng{
	margin-r:auto;
	margin-left:auto;
}
.ErrorPng img{
	height:700px;
	display:block;
	margin:auto;
}
	
</style>
</head>


<body>
	<div  class="ErrorPng">
	<img src="images/Error.png">
	</div>
	<div class="wrap">
		<div class="logo">
		  
		   <div class="sub">
		     <p><a href="<%=request.getContextPath()%>/index.jsp">回到首頁</a></p>

		    </div>
		</div>
	</div>
	
</body>