<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
	ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //ReportServlet.java (Concroller), �s�Jreq��reportVO���� (�]�A�������X��reportVO, �]�]�A��J��ƿ��~�ɪ�reportVO����)
%>
<html>
<head>

<%-- �ĪG����--%>
<link href="<%=request.getContextPath()%>/jscss/ador/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="<%=request.getContextPath()%>/jscss/ador/lib/Hui-iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/jscss/ador/css/nono.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/js/H-ui.admin.js"></script> 

<title>���u��ƭק� - update_report_input.jsp</title>

</head>

<body bgcolor='white'>
<header class="header-top breadcrumb">
		<i class="Hui-iconfont">&#xe625;</i>
		<a>����</a>
		<i class="Hui-iconfont">&#xe63d;</i>
		<a>���|�޲z</a>
		<a href="<%=request.getRequestURI()%>" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" title="��s" >
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</header>
<div class="pd-20">
	<div class="text-c">	
		<div class="cl pd-5 bg-1 bk-gray mt-20"> 
			<span class="l">
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
				<a href="<%=request.getContextPath()%>/report/listAllReport.jsp" class="btn btn-danger radius">
					<i class="Hui-iconfont">&#xe678;</i>��^���|�޲z
				</a>
			</span> 
			<span class="r">
			</span>
		</div>

<FORM METHOD="post" ACTION="report.do" name="form1">
<div class="row cl distance">
				<label class="form-label col-2">���|�s��:</label>
				<div class="formControls col-5">
					<%=reportVO.getReport_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���|�H�s��:</label>
				<div class="formControls col-5">
					<%=reportVO.getMem_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���a�s��:</label>
				<div class="formControls col-5">
					<%=reportVO.getStore_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���O�s��:</label>
				<div class="formControls col-5">
					<%=reportVO.getArticle_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">�d���s��:</label>
				<div class="formControls col-5">
					<%=reportVO.getReply_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���νs��:</label>
				<div class="formControls col-5">
					<%=reportVO.getGroup_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���|���e:</label>
				<div class="formControls col-5">
					<%=reportVO.getReport_content()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">���|���A:</label>
				<div class="formControls col-5">
					<input type="TEXT" name="report_status" size="45" value="<%=reportVO.getReport_status()%>" class="input-text"/>
				</div>
			</div>
			
		
			<div class="row cl distance">
				<div class="col-10 col-offset-2">
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="report_no" value="<%=reportVO.getReport_no()%>">
					<button class="btn btn-primary radius bt" type="submit"><i class="Hui-iconfont">&#xe632;</i> �e�X�ק�</button>
				</div>
			</div>
		</FORM>
		
	</div>	
</div>
</body>
</html>
