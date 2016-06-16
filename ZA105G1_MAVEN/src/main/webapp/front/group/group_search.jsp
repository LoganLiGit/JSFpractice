<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>
<%@ page import="com.group.table.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="listGroups_ByCompositeQuery" scope="request" type="java.util.List" />
<html>
<head>
<title>Menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/stuck.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/style3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/touchTouch.css">
<script src="<%=request.getContextPath()%>/front/group/js/sweet-alert.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/sweet-alert.css">
<script src="<%=request.getContextPath()%>/front/group/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/jquery.easing.1.3.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/tmStickUp.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/jquery.ui.totop.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/touchTouch.jquery.js"></script>
</head>
<body>
	<!--============ header ============-->

		<div id="header" style="width: 1900px; height: 225px;">
			<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
	
	<!--============ header  ============-->
	<!--=====================
          Content
======================-->
	<section class="content gallery pad1">
		<div class="container">
			<div class="row">
				<c:forEach var="groupVO" items="${listGroups_ByCompositeQuery}" varStatus="s">
					<div class="grid_4">
						<div class="gall_block">
							<div class="maxheight">
								<a href="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupVO.group_no}" class="gall_item">
									<img src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupVO.group_no}">
								</a>
								<div class="gall_bot">
									<div class="text1">
										<a href="" data-toggle="modal" data-target="#myModal" class="group_name">${groupVO.group_name}</a>
										<ul class="group_detail">
											<li class="group_li">店名：${groupVO.storeVO.store_name}</li>
											<li class="group_li">地址：${groupVO.storeVO.store_city}${groupVO.storeVO.store_district}${groupVO.storeVO.store_address}</li>
											<li class="group_li">人數：${groupVO.groupMems.size()+1}/${groupVO.group_num}</li>
											<li class="group_li">出發時間：${groupVO.group_eat_date.toString().substring(0,groupVO.group_eat_date.toString().lastIndexOf(":"))}</li>
											<li class="group_li">報名截止：${groupVO.group_stop_date.toString().substring(0,groupVO.group_stop_date.toString().lastIndexOf(":"))}</li>
										</ul>
									</div>
										<button type="button" class="btn btn-default count${s.index}">加入揪團</button>
										<input type="hidden" name="group_no" id="arti_no" value="${groupVO.group_no}">
										<input type="hidden" name="mem_no" id="arti_no2" value="${memberVO.mem_no}">
								
									<c:if test="${groupVO.groupMems.size()<groupVO.group_num-1}">
										<script>
											document.querySelector(".count${s.index}").onclick = function() {
												 $.ajax({
													   type:"POST",
													   url:'<%=request.getContextPath()%>/group/group.do',
													   data:{action:'insertMem',group_no:'${groupVO.group_no}',mem_no:'${memberVO.mem_no}'}, //傳arti_no過去
													   dataType:"text",
													   success:function (data){
															swal("加入成功!", "祝您有個愉快的一餐!","success");
													    },
													    error:function(){alert("error")}
													    }) 
			
												
											};
										</script>
									</c:if>
									<c:if test="${groupVO.groupMems.size()>=groupVO.group_num-1}">
										<script>
											document.querySelector('.count${s.index}').onclick = function() {
												swal("不好意思，人數已滿", "可以持續注意該團喔!","error");
											
											};
										</script>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">詳細資料</h4>
								</div>
								<div class="modal-body">
									<table class="table table-hover table-expandable">
										<thead>
											<tr>
												<th>團名</th>
												<th>店名</th>
												<th>類型</th>
												<th>評分</th>
											</tr>
											<tr>
												<td>${groupVO.group_name}</td>
												<td>${groupVO.storeVO.store_name}</td>
												<td>${groupVO.storeVO.store_type}</td>
												<td>${groupVO.storeVO.store_score/groupVO.storeVO.store_scopenum}</td>
											</tr>
											<tr>
												<td colspan="4">介紹</td>
											</tr>
											<tr>
												<td colspan="4">${groupVO.group_intro}</td>
											</tr>
									</table>
								</div>
								<div class="modal-footer">
									<a href="<%=request.getContextPath()%>/front/store/store_detail.jsp?store_no=${groupVO.storeVO.store_no}" class="btn btn-default">店家</a>
									<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

         <!--============ footer ============-->
<div id="footer" style="width: 1900px; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>
</html>
