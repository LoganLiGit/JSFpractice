<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<HTML> 
	<HEAD> 
	<TITLE>�n�X����</TITLE> 
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
<!-- // 		session.invalidate(); //�j��_�ثe��session����  -->
	
<%-- %>  --%>

<!--********�n�J�n�X�ʺA*********** -->
		 <c:choose>
		    <c:when test="${ memberVO.mem_name!=null }">
				<a href="<%=request.getContextPath()%>/front/member/update/update.jsp"><button width="20" height="20" >${memberVO.mem_name}�A�n</button> </a> 
				<a href="<%=request.getContextPath()%>/front/member/logout/logout.jsp"><button width="20" height="20" >�n�X</button> </a>
		    </c:when>
		    <c:otherwise>
				<a id="login" ><button width="20" height="20" >�n�J�|��</button></a>
		        <a href="<%=request.getContextPath()%>/front/member/register/register.jsp"><button width="20" height="20" >���U�|��</button> </a>
		    </c:otherwise>
		  </c:choose>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login_from" >
		<input type="hidden" name="url" value="<%=request.getServletPath()%>">
	</FORM>
<!--********�n�J�n�X�ʺA*********** -->

<div class="logo"> <a href=""><img src="images2/logo2.jpg" width="90" height="90"></a> </div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
                 <li><a href="index.jsp">HOME</a></li>
                <li><a href="test.jsp">test.jsp</a></li>
                <li><a href="test2.jsp">test2.jsp</a></li>
                <li><a href="personal.jsp">�ӤH����</a></li>
                <li><a href="#contactus">���ʨ�ӫ�</a></li>
            </ul>
        </nav>
      </div> <!--end of header-->
	<center><BR> 
		<FONT SIZE = 5 COLOR = blue>�ϥΪ̴��յe��</FONT><HR><BR> 
		<BR><BR> 
		<a href="<%=request.getContextPath()%>/front/member/JuicyTalk/index.jsp">��^����</a> 
		
	</center> 
	</BODY> 
	</HTML> 