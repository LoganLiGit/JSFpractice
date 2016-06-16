<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
	ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //ReportServlet.java (Concroller), 存入req的reportVO物件 (包括幫忙取出的reportVO, 也包括輸入資料錯誤時的reportVO物件)
%>
<html>
<head>

<%-- 效果部分--%>
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

<title>員工資料修改 - update_report_input.jsp</title>

</head>

<body bgcolor='white'>
<header class="header-top breadcrumb">
		<i class="Hui-iconfont">&#xe625;</i>
		<a>首頁</a>
		<i class="Hui-iconfont">&#xe63d;</i>
		<a>檢舉管理</a>
		<a href="<%=request.getRequestURI()%>" class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" title="刷新" >
			<i class="Hui-iconfont">&#xe68f;</i>
		</a>
	</header>
<div class="pd-20">
	<div class="text-c">	
		<div class="cl pd-5 bg-1 bk-gray mt-20"> 
			<span class="l">
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
				<a href="<%=request.getContextPath()%>/report/listAllReport.jsp" class="btn btn-danger radius">
					<i class="Hui-iconfont">&#xe678;</i>返回檢舉管理
				</a>
			</span> 
			<span class="r">
			</span>
		</div>

<FORM METHOD="post" ACTION="report.do" name="form1">
<div class="row cl distance">
				<label class="form-label col-2">檢舉編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getReport_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">檢舉人編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getMem_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">店家編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getStore_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">食記編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getArticle_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">留言編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getReply_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">揪團編號:</label>
				<div class="formControls col-5">
					<%=reportVO.getGroup_no()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">檢舉內容:</label>
				<div class="formControls col-5">
					<%=reportVO.getReport_content()%>
				</div>
			</div>
			
			<div class="row cl distance">
				<label class="form-label col-2">檢舉狀態:</label>
				<div class="formControls col-5">
					<input type="TEXT" name="report_status" size="45" value="<%=reportVO.getReport_status()%>" class="input-text"/>
				</div>
			</div>
			
		
			<div class="row cl distance">
				<div class="col-10 col-offset-2">
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="report_no" value="<%=reportVO.getReport_no()%>">
					<button class="btn btn-primary radius bt" type="submit"><i class="Hui-iconfont">&#xe632;</i> 送出修改</button>
				</div>
			</div>
		</FORM>
		
	</div>	
</div>
</body>
</html>
