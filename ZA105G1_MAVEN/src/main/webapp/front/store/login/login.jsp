<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>

<head>
<title>login</title>
<!-- Custom Theme files -->
<link href="<%=request.getContextPath()%>/front/store/login/css/Login.css" rel="stylesheet" type="text/css" media="all" />
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"content="Minimal Flat User Account Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts-->
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'rel='stylesheet' type='text/css'>
<!--Google Fonts-->
<script src="<%=request.getContextPath()%>/front/store/login/jquery/Login.js"></script>
</head>

<body>

	<div class="login">
		<div class="login-top">
			<h2>JuicyTalk 店家登入</h2>
			<h3>LOG IN</h3>
		</div>
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
		<div class="login-bottom">
			<h3>帳號:</h3>
			<form action="<%=request.getContextPath()%>/storelogin.do" method="post">
				<%
					String url = request.getParameter("url");
					if (url != null) {
						session.setAttribute("location", url);
					}
				%>
				<div class="user">
					<input type="text" value="" name="store_account"
						placeholder='請輸入帳號'> <i></i>
				</div>
				<h3>密碼:</h3>
				<div class="user-in">
					<input type="password" value="" name="store_password"
						placeholder='請輸入密碼' /> <i></i>
				</div>
				<div class="keepme">
					<input type="hidden" name="action" value="login_check">

					<div class="keep-loginbutton">
						<form>
							<input type="hidden" name="referer"
								value="<%=request.getHeader("referer")%>"> <input
								type="submit" value="登入">
						</form>
					</div>
					<div class="clear"></div>
				</div>
			</form>

			<div class="forgot">
				<p id="forgot_password">
<!-- 					<a href="#">忘記密碼?</a> -->
				</p>
				<div class="forgot-register">
					<p>
						還沒加入JuicyTalk的行列嗎?? <a
							href="<%=request.getContextPath()%>/front/store/register/register.jsp">點這裡註冊</a>
					</p>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="div_forgot_password">
			<form>

				<h3>請輸入信箱:</h3>
				<div class="e-mail">
					<input type="text" value="" onfocus="this.value = '';"
						onblur="if (this.value == '') {this.value = 'E-mail';}"> <i></i>
				</div>
			</form>

			<div class="findPassword">
				<form>

					<input type="submit" value="送出">
				</form>

			</div>
		</div>
	</div>


</body>

</html>