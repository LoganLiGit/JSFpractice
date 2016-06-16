<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.report.model.*"%>
<%
	String url_back_report_list=	request.getContextPath() + "/Back/Report/Back_Report_List.jsp";
	String url_back_report_update=	request.getContextPath() + "/Back/Report/Back_Report_Update.jsp";
	String url_back_report_one=		request.getContextPath() + "/Back/Report/Back_Report_One.jsp";
	String url_back_report_servlet=	request.getContextPath() + "/Back/Report/Back_Report_Servlet.do";
	
	ReportVO reportVO = (ReportVO) request.getAttribute("reportVO"); //ReportServlet.java(Concroller), 存入req的reportVO物件
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<jsp:useBean id="articleSvc" scope="page" class="com.article.model.ArticleService" />
<jsp:useBean id="replySvc" scope="page" class="com.reply.model.ReplyService" />
<jsp:useBean id="groupSvc" scope="page" class="com.group.table.model.GroupTableService" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</head>
<body>
<header class="header-top breadcrumb">
	<i class="Hui-iconfont">&#xe625;</i>
	<a>首頁</a>
	<i class="Hui-iconfont">&#xe63d;</i>
	<a>檢舉總覽</a>
	<i class="Hui-iconfont">&#xe63d;</i>
	<a>檢舉查詢</a>
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
					<font color='red'>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li>${message}</li>
						</c:forEach>
					</ul>
					</font>
				</c:if>
			</span> 
			<span class="r">
				<a href="<%= url_back_report_list %>" class="btn btn-danger radius">
					<i class="Hui-iconfont">&#xe678;</i>返回檢舉總覽
				</a>
			</span>
		</div>
		<div class="mt-20">
			<table class="table table-border table-bordered table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th>檢舉編號</th>
						<th>檢舉人編號</th>
						<th>店家編號</th>
						<th>食記編號</th>
						<th>留言編號</th>
						<th>揪團編號</th>
						<th>檢舉內容</th>
						<th>檢舉狀態</th>
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>
				<tbody>
					<tr align='center' valign='middle'>
						<td>${reportVO.report_no}</td>
						<td>
							<c:forEach var="memberVO" items="${memberSvc.all}">
								<c:if test="${reportVO.mem_no==memberVO.mem_no}">
									${memberVO.mem_name}
		            			</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="storeVO" items="${storeSvc.all}">
								<c:if test="${reportVO.store_no==storeVO.store_no}">
									<a href="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do?store_no=${storeVO.store_no}&action=getStore_For_One&report_no=${reportVO.report_no}">${storeVO.store_name}</a>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="articleVO" items="${articleSvc.all}">
								<c:if test="${reportVO.article_no==articleVO.article_no}">
									<a href="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do?article_no=${articleVO.article_no}&action=getArticle_For_One&report_no=${reportVO.report_no}">${articleVO.article_title}</a>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="replyVO" items="${replySvc.all}">
								<c:if test="${reportVO.reply_no==replyVO.reply_no}">
									<a href="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do?reply_no=${replyVO.reply_no}&action=getReply_For_One&report_no=${reportVO.report_no}">${replyVO.reply_msg}</a>
								</c:if>
							</c:forEach>
						</td>
						<td>
							<c:forEach var="groupVO" items="${groupSvc.all}">
								<c:if test="${reportVO.group_no==groupVO.group_no}">
									<a href="<%=request.getContextPath()%>/Back/Report/Back_Report_Servlet.do?group_no=${groupVO.group_no}&action=getGroup_For_One&report_no=${reportVO.report_no}">${groupVO.group_name}</a>
								</c:if>
							</c:forEach>
						</td>
						<td>${reportVO.report_content}</td>
						<td>
							<c:if test="${reportVO.report_status == 0 }">
								<font color="red">未審核</font>
							</c:if>
							<c:if test="${reportVO.report_status == 1 }">
								<font color="lightgreen">已審核</font>
							</c:if>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%= url_back_report_servlet %>">
								<div>
									<select style="float:left" name="report_status">
										<option value="0">未審核</option>
										<option value="1">已審核</option>
									</select>
								</div>
								<div>
									<button type="submit" class="btn btn-primary radius">
										<i class="Hui-iconfont">&#xe6df;</i>修改
									</button>
									<input type="hidden" name="report_no" value="${reportVO.report_no}"> 
									<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> <!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="action" value="update_For_One">
								</div>
							</FORM>
						</td>
						<td>
							<FORM METHOD="post" ACTION="<%= url_back_report_servlet %>">
								<button type="submit" class="btn radius delete">
									<i class="Hui-iconfont">&#xe609;</i>刪除
								</button>
								<input type="hidden" name="report_no" value="${reportVO.report_no}"> 
								<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> <!--送出本網頁的路徑給Controller-->
								<input type="hidden" name="action" value="delete">
							</FORM>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<%
	if (request.getAttribute("storeVO") != null) {
%>
<jsp:include page="listOneStore.jsp" />
<%
	} else if (request.getAttribute("articleVO") != null) {
%>
<jsp:include page="listOneArticle.jsp" />
<%
	} else if (request.getAttribute("replyVO") != null) {
%>
<jsp:include page="listOneReply.jsp" />
<%
	} else if (request.getAttribute("groupVO") != null) {
%>
<jsp:include page="listOneGroup.jsp" />
<%
	}
%>
</body>
</html>
