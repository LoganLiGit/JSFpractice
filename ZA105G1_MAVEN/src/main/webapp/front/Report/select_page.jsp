<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>IBM Report: Home</title>

<script type="text/javascript" src="<%=request.getContextPath()%>/Front/Report/css/report.js"></script>

</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>IBM Report: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for IBM Report: Home</p>

<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<ul>
  <li><a href='listAllReport.jsp'>List</a> all Reports. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="report.do" >
        <b>��J���|�s�� (�p7001):</b>
        <input type="text" name="report_no">
        <input type="submit" value="�e�X">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="reportSvc" scope="page" class="com.report.model.ReportService" />
   
  <li>
     <FORM METHOD="post" ACTION="report.do" >
       <b>������|�s��:</b>
       <select size="1" name="report_no">
         <c:forEach var="reportVO" items="${reportSvc.all}" > 
          <option value="${reportVO.report_no}">${reportVO.report_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="report.do" >
       <b>������|�H�s��:</b>
       <select size="1" name="report_no">
         <c:forEach var="reportVO" items="${reportSvc.all}" > 
          <option value="${reportVO.report_no}">${reportVO.mem_no}
         </c:forEach>   
       </select>
       <input type="submit" value="�e�X">
       <input type="hidden" name="action" value="getOne_For_Display">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href="#" onclick="window.open('addReport_store.jsp', '', config='height=500,width=500');">Add</a> a new Report store.</li>
</ul>
<ul>
  <li><a href='#' onclick="window.open('addReport_article.jsp', '', config='height=500,width=500');">Add</a> a new Report article.</li>
</ul>
<ul>
  <li><a href='#' onclick="window.open('addReport_group.jsp', '', config='height=500,width=500');">Add</a> a new Report group.</li>
</ul>
<ul>
  <li><a href='#' onclick="window.open('addReport_reply.jsp', '', config='height=500,width=500');">Add</a> a new Report reply.</li>
</ul>
</body>


</html>
