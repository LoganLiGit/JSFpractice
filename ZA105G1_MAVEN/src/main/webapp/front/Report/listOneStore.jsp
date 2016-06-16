<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.store.model.*"%>
<%
	
	
	StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
							<th>店家編號</th>
							<th>店家帳號</th>
							<th>店家密碼</th>
							<th>店家狀態</th>
							<th>店家名稱</th>
							<th>註冊日期</th>
							<th>店家郵遞區號</th>
							<th>店家城市</th>
							<th>店家區域</th>
							<th>店家地址</th>
							<th>店家電話</th>
							<th>店家類型</th>
							<th>店家評分</th>
							<th>帳戶儲值金</th>
							<th>手機註冊碼</th>
							<th>違規次數</th>
							<th>聯絡人姓名</th>
							<th>聯絡人性別</th>
							<th>聯絡人電子郵件</th>
							<th>聯絡人身份證字號</th>
							<th>聯絡人手機</th>
							<th>信用卡號碼</th>
							<th>信用卡到期年</th>
							<th>信用卡到期日</th>
							<th>信用卡安全碼</th>
							<th>團購券權限</th>
							<th>地址經度</th>
							<th>地址緯度</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr align='center' valign='middle'>
							<td>${storeVO.store_no}</td>
							<td>${storeVO.store_account}</td>
							<td>${storeVO.store_password}</td>
							<td>${storeVO.store_state}</td>
							<td>${storeVO.store_name}</td>
							<td>${storeVO.store_regist_date}</td>
							<td>${storeVO.store_zipcode}</td>
							<td>${storeVO.store_city}</td>
							<td>${storeVO.store_district}</td>
							<td>${storeVO.store_address}</td>
							<td>${storeVO.store_phone}</td>
							<td>${storeVO.store_type}</td>
							<td>${storeVO.store_score}</td>
							<td>${storeVO.store_balance}</td>
							<td>${storeVO.store_cell_registcode}</td>
							<td>${storeVO.store_violation}</td>
							<td>${storeVO.manager_name}</td>
							<td>${storeVO.manager_gender}</td>
							<td>${storeVO.manager_email}</td>
							<td>${storeVO.manager_id}</td>
							<td>${storeVO.manager_cellphone}</td>
							<td>${storeVO.manager_credit_num}</td>
							<td>${storeVO.manager_credit_expyear}</td>
							<td>${storeVO.manager_credit_expmonth}</td>
							<td>${storeVO.manager_credit_secure_num}</td>
							<td>${storeVO.tickts_limits}</td>
							<td>${storeVO.store_longitude}</td>
							<td>${storeVO.store_latitude}</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/SendMail/SendMail.do">
									<button type="submit" class="btn radius delete">
										<i class="Hui-iconfont">&#xe68a;</i>
									</button>
									<input type="hidden" name="store_no" value="${storeVO.store_no}"> 
									<input type="hidden" name="action" value="send_For_Report">
								</FORM>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>