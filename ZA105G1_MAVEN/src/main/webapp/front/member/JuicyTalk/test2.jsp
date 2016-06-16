<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<HTML> 
	<HEAD> 
	<TITLE>登出完成</TITLE> 
	</HEAD> 
	<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		$("#login_from").submit();
	}
	)
});
</script>
	<BODY> 
	
<%-- <%          --%>
<!-- // 		session.removeAttribute("mem_account");  -->
<!-- // 		session.invalidate(); //強制中斷目前的session物件  -->
	
<%-- %>  --%>

<!--********登入登出動態*********** -->
		 <c:choose>
		    <c:when test="${ memberVO.mem_name!=null }">
				<a href="<%=request.getContextPath()%>/front/member/update/update.jsp"><button width="20" height="20" >${memberVO.mem_name}你好</button> </a> 
				<a href="<%=request.getContextPath()%>/front/member/logout/logout.jsp"><button width="20" height="20" >登出</button> </a>
		    </c:when>
		    <c:otherwise>
				<a id="login" ><button width="20" height="20" >登入會員</button></a>
		        <a href="<%=request.getContextPath()%>/front/member/register/register.jsp"><button width="20" height="20" >註冊會員</button> </a>
		    </c:otherwise>
		  </c:choose>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login_from" >
		<input type="hidden" name="url" value="<%=request.getServletPath()%>">
	</FORM>
<!--********登入登出動態*********** -->

<div class="logo"> <a href=""><img src="images2/logo2.jpg" width="90" height="90"></a> </div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
                 <li><a href="index.jsp">HOME</a></li>
                <li><a href="test.jsp">test.jsp</a></li>
                <li><a href="test2.jsp">test2.jsp</a></li>
                <li><a href="personal.jsp">個人頁面</a></li>
                <li><a href="#contactus">團購券商城</a></li>
            </ul>
        </nav>
      </div> <!--end of header-->
	<center><BR> 
		<FONT SIZE = 5 COLOR = blue>使用者測試畫面</FONT><HR><BR> 
		<BR><BR> 
		<a href="<%=request.getContextPath()%>/front/member/JuicyTalk/index.jsp">返回首頁</a> 
		
	</center> 
	</BODY> 
	</HTML> 