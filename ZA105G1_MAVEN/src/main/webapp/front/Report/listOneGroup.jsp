<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.group.table.model.*"%>   
<%
	GroupTableVO groupVO = (GroupTableVO) request.getAttribute("groupVO");
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
							<th>揪團編號</th>
							<th>會員編號</th>
							<th>店家編號</th>
							<th>揪團人數</th>
							<th>簡介</th>
							<th>圖片</th>
							<th>揪團時間</th>
							<th>發起時間</th>
							<th>截止時間</th>
							<th>揪團狀態</th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td>${groupVO.group_no}</td>
							<td>${groupVO.mem_no}</td>
							<td>${groupVO.storeVO.store_no}</td>
							<td>${groupVO.group_num}</td>
							<td>${groupVO.group_intro}</td>
							<td>${groupVO.group_photo}</td>
							<td>${groupVO.group_eat_date}</td>
							<td>${groupVO.group_start_date}</td>
							<td>${groupVO.group_stop_date}</td>
							<td>${groupVO.group_status}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>