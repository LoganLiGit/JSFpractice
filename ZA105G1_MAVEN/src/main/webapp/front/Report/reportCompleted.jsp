<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.report.model.*"%>
<%
ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //ReportServlet.java(Concroller), �s�Jreq��reportVO����
%>
<html>
<head>
<title>���u��� - listOneReport.jsp</title>
</head>
<body onload= "CountAndClose() ">

	<h1>���|����</h1>
<A HREF="#" onClick="window.close()">��������</A><a id="counter">3</a>
</body>
<script>
	function CountAndClose(){
	
		var e = document.getElementById( "counter");
		
		var cTicks = parseInt(e.innerHTML);
		
		var timer = setInterval(function(){
		
			if( cTicks ){
				
				e.innerHTML = --cTicks;
				
			}else{
				
				clearInterval(timer);
				
				window.close(); 
			
			}
		
		}, 1000);
	
	}
	
</script>
</html>
