<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.article.model.*"%>   
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
							<th>食記編號</th>
							<th>店家編號</th>
							<th>會員編號</th>
							<th>店家名稱</th>
							<th>食記內容</th>
							<th>食記標題</th>
							<th>食記建立</th>
							<th>食記修改</th>
							<th>食記狀態</th>
							<th>食記得分</th>
							<th>食記點擊</th>
							<th>食記回復</th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td>${articleVO.article_no}</td>
							<td>${articleVO.store_no}</td>
							<td>${articleVO.mem_no}</td>
							<td>${articleVO.store_name}</td>
							<td>${articleVO.article_content}</td>
							<td>${articleVO.article_title}</td>
							<td>${articleVO.article_create}</td>
							<td>${articleVO.article_modify}</td>
							<td>${articleVO.article_status}</td>
							<td>${articleVO.article_score}</td>
							<td>${articleVO.article_click}</td>
							<td>${articleVO.article_replies}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>