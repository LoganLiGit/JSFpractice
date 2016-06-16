<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.report.model.*"%>
<%
ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //ReportServlet.java(Concroller), 存入req的reportVO物件
%>
<html>
<head>
<title>員工資料 - listOneReport.jsp</title>
</head>
<body onload= "CountAndClose() ">

	<h1>檢舉完成</h1>
<A HREF="#" onClick="window.close()">關閉視窗</A><a id="counter">3</a>
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
