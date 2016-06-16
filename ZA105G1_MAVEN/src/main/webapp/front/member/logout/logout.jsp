<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

	<HTML> 
	<HEAD> 
	<TITLE>登出完成</TITLE> 
	</HEAD> 
	<BODY> 
	
<%         
		session.removeAttribute("mem_account"); 
		session.invalidate(); //強制中斷目前的session物件 
// 		response.sendRedirect(request.getContextPath()+"/front/member/JuicyTalk/index.jsp");
		response.setHeader("refresh","3;URL="+request.getContextPath()+"/index.jsp") ;
%> 

	<center><BR> 
		<FONT SIZE = 5 COLOR = blue>使用者登出成功!</FONT><HR><BR> 
		<BR><BR> 
		
		<a href="<%=request.getContextPath()%>/index.jsp">返回首頁</a> 
		
		<FONT SIZE =3>
		三秒秒後自動跳轉到首頁視窗！！！<br>
		
		如果沒有跳轉，請按<a href="<%=request.getContextPath()%>/index.jsp">這裡</a>！！！<br>
		
		</FONT>
	</center> 
	</BODY> 
	</HTML> 