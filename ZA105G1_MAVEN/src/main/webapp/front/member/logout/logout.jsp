<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>

	<HTML> 
	<HEAD> 
	<TITLE>�n�X����</TITLE> 
	</HEAD> 
	<BODY> 
	
<%         
		session.removeAttribute("mem_account"); 
		session.invalidate(); //�j��_�ثe��session���� 
// 		response.sendRedirect(request.getContextPath()+"/front/member/JuicyTalk/index.jsp");
		response.setHeader("refresh","3;URL="+request.getContextPath()+"/index.jsp") ;
%> 

	<center><BR> 
		<FONT SIZE = 5 COLOR = blue>�ϥΪ̵n�X���\!</FONT><HR><BR> 
		<BR><BR> 
		
		<a href="<%=request.getContextPath()%>/index.jsp">��^����</a> 
		
		<FONT SIZE =3>
		�T����۰ʸ���쭺�������I�I�I<br>
		
		�p�G�S������A�Ы�<a href="<%=request.getContextPath()%>/index.jsp">�o��</a>�I�I�I<br>
		
		</FONT>
	</center> 
	</BODY> 
	</HTML> 