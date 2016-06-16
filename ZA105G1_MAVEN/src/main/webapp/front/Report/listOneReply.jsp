<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.reply.model.*"%>
<%@ page import="java.util.*"%>
<%
ReplyVO replyVO = (ReplyVO) request.getAttribute("replyVO"); //ReportServlet.java(Concroller), 存入req的reportVO物件
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%-- 效果部分--%>
<link href="<%=request.getContextPath()%>/jscss/ador/css/H-ui.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="pd-20">
		<div class="text-c">
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th>留言編號</th>
							<th>留言人編號</th>
							<th>食記編號</th>
							<th>留言訊息</th>
							<th>留言時間</th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td>${replyVO.getReply_no()}</td>
							<td>${replyVO.getMem_no()}</td>
							<td>${replyVO.getArticle_no()}</td>
							<td>${replyVO.getReply_msg()}</td>
							<td>${replyVO.getReply_time()}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
</html>
