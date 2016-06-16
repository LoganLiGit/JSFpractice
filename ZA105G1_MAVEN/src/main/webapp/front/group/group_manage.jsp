<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.group.table.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.store.model.*"%>
<%
	if (request.getParameter("mem_no") != null) {
		Integer mem_no = Integer.parseInt(request
				.getParameter("mem_no"));
		GroupTableService groupSvc = new GroupTableService();
		List<GroupTableVO> list = groupSvc
				.getGroupTablesByMem_no(mem_no);
		pageContext.setAttribute("list", list);

	} else {
		List<GroupTableVO> list = (List<GroupTableVO>) request
				.getAttribute("getGroups_For_Mem");
		pageContext.setAttribute("list", list);
	}

	MemberService memberSvc = new MemberService();
	pageContext.setAttribute("memberSvc", memberSvc);
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
%>
<html lang="zh_TW">
<head>
<title>Juicy Talk</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link id="color" rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/color1.css">
<link href="<%=request.getContextPath()%>/front/group/css/css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/style.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/styleMenage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/bootstrap-table-expandable.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/facebookchatboxUI.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/facebookchatboxUI.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/bootstrap-table-expandable.css">
<!-- INCLUDES -->
<!-- scroll function -->
</head>
<body>
	<!--============ header ============-->
	<div id="header" style="width: 1900px; height: 225px;">
		<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
	</div>
	<!--============ header  ============-->
	<!-- chatbox-starts-->
	<!-- 在個人頁面顯示某人的朋友們 (右邊聊天欄)-->
	<div class="chat-sidebar">
		<c:forEach var="groupTableVO" items="${list}" varStatus="s">
			<div class="sidebar-name" id="sidebar-name${groupTableVO.group_no}" onclick="connect('${groupTableVO.group_no}','${groupTableVO.group_name}','${sessionScope.memberVO.mem_no}')"> 
			
				<div style="display: inline-block;">
					<a href="javascript:register_popup('${groupTableVO.group_no}', '${groupTableVO.group_name}','${sessionScope.memberVO.mem_no}');">
						<img class="charimg" src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupTableVO.group_no}">
						<span>${groupTableVO.group_name}</span>
					</a>
				</div>

			</div>
		</c:forEach>
	</div>
	<script>
    
	  var MyPoint = "/MyEchoServer2";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	  
		var statusOutput = document.getElementById("statusOutput");
		var webSocket = new Array(50);
		var c=0;
		var myMap = new Map();
		
		function connect(reciever_no,reciever_name,sender_no) {
			// 建立 websocket 物件
			
			param2='popup-messages'+reciever_no;
			if(myMap.get(reciever_name)!=1){
				
				
				// 建立 websocket 物件
				var endPointURL = "ws://" + window.location.host + webCtx + MyPoint + '/'+sender_no + '/'+reciever_no;
				webSocket[reciever_no] = new WebSocket(endPointURL);

				
				myMap.set(reciever_name, 1);
				
			}
			webSocket[reciever_no].onopen = function(event) {
				updateStatus("WebSocket 成功連線");
			};

			webSocket[reciever_no].onmessage  = function(event) {
				   
				param2='popup-messages'+reciever_no;
				
				var messagesArea = document.getElementById(param2);
		        var jsonObj = JSON.parse(event.data);
		        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
		        messagesArea.value = messagesArea.value + message;
		        messagesArea.scrollTop = messagesArea.scrollHeight;
			};

			webSocket[reciever_no].onclose = function(event) {
				
				myMap.set(reciever_name, 0);
				updateStatus("WebSocket 已離線");
			
			};
		}
		
		
		var inputUserName ='${memberVO.mem_name}';
		inputUserName.focus();
		
		function sendMessage(reciever_no,sender_no) {
		
			param='message'+reciever_no;
			
		    var userName = inputUserName.trim();
		    var inputMessage = document.getElementById(param);
		    var message = inputMessage.value.trim();
		    
		    if (message === ""){
		        alert ("訊息請勿空白!");
		        inputMessage.focus();	
		    }else{
		        var jsonObj = {"userName" : userName, "message" : message};
		      
		        
		        webSocket[reciever_no].send(JSON.stringify(jsonObj));
		        inputMessage.value = "";
		        inputMessage.focus();
		    }
		}

		
		function disconnect (reciever_no) {
			webSocket[reciever_no].close();
			
		}

		
		function updateStatus(newStatus) {
			statusOutput.innerHTML = newStatus;
		}
    
</script>


	<!-- end of headerwrapper-->
	<section id="maincontainer" class="containerMeg clearfixMeg">
		<h1 class="heading1">
			揪團
			<span>管理</span>
		</h1>
		<!-- options -->
		<section id="options" class="clearfixMeg">
			<ul id="filters" class="option-set clearfixMeg" data-option-key="filter">
				<li>
					<a href="" data-option-value="*" class="selected">全部揪團</a>
				</li>
				<li>
					<a href="" data-option-value=".Oneday" class="">一天以內</a>
				</li>
				<li>
					<a href="" data-option-value=".Threeday">三天以內</a>
				</li>
				<li>
					<a href="" data-option-value=".Oneweek">一個禮拜內</a>
				</li>
				<li>
					<a href="" data-option-value=".Expired">已過時間</a>
				</li>
			</ul>
		</section>
		<!-- Recipea -->
		<%
			java.sql.Timestamp nowday = new java.sql.Timestamp(
					System.currentTimeMillis());

			pageContext.setAttribute("nowday", nowday);
		%>
		<div id="recipecontainer" class="clearfixMeg recipecolumn3 recipesortable isotope" style="position: relative; overflow: hidden; height: 738px;">
			<ul>
				<c:forEach var="groupTableVO" items="${list}" varStatus="s">
					<c:choose>
						<c:when
							test="${((groupTableVO.group_eat_date.getTime()-nowday.getTime())/(1000*60*60*24))<1&&
						((groupTableVO.group_eat_date.getTime()-nowday.getTime())/(1000*60*60*24))>0}">
							<li class="element Oneday isotope-item"
								style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px) scale3d(1, 1, 1); opacity: 1;">
								<img class="manageImg" src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupTableVO.group_no}">
								<span class="view">&nbsp;</span>
								<div class="recipedetails">
									<a href="" class="title">${groupTableVO.group_name}</a>
									<p>店家名稱：${groupTableVO.storeVO.store_name}</p>
									<p>團長：${memberSvc.getOneMember(groupTableVO.mem_no).mem_nickname}</p>
									<p>出發日期：${groupTableVO.group_eat_date.toString().substring(0,groupTableVO.group_eat_date.toString().lastIndexOf(":"))}</p>
									<c:if test="${groupTableVO.mem_no==memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">修改</button>
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</div>
									</c:if>
									<c:if test="${groupTableVO.mem_no!=memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">退出</button>
												<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="deleteOneMem">
											</FORM>
										</div>
									</c:if>
									<button type="button" class="btn btn-default">檢舉</button>
								</div>
							</li>
						</c:when>
						<c:when
							test="${((groupTableVO.group_eat_date.getTime()-nowday.getTime())/( 1000*60*60*24))<3&&
						((groupTableVO.group_eat_date.getTime()-nowday.getTime())/(1000*60*60*24))>0}">
							<li class="element Threeday isotope-item"
								style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px) scale3d(1, 1, 1); opacity: 1;">
								<img class="manageImg" src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupTableVO.group_no}">
								<span class="view">&nbsp;</span>
								<div class="recipedetails">
									<a href="" class="title">${groupTableVO.group_name}</a>
									<p>店家名稱：${groupTableVO.storeVO.store_name}</p>
									<p>團長：${memberSvc.getOneMember(groupTableVO.mem_no).mem_nickname}</p>
									<p>出發日期：${groupTableVO.group_eat_date.toString().substring(0,groupTableVO.group_eat_date.toString().lastIndexOf(":"))}</p>
									<c:if test="${groupTableVO.mem_no==memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">修改</button>
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</div>
									</c:if>
									<c:if test="${groupTableVO.mem_no!=memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">退出</button>
												<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="deleteOneMem">
											</FORM>
										</div>
									</c:if>
									<button type="button" class="btn btn-default">檢舉</button>
								</div>
							</li>
						</c:when>
						<c:when
							test="${((groupTableVO.group_eat_date.getTime()-nowday.getTime())/( 1000*60*60*24))<7&&
						((groupTableVO.group_eat_date.getTime()-nowday.getTime())/(1000*60*60*24))>0}">
							<li class="element Oneweek isotope-item"
								style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px) scale3d(1, 1, 1); opacity: 1;">
								<img class="manageImg" src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupTableVO.group_no}">
								<span class="view">&nbsp;</span>
								<div class="recipedetails">
									<a href="" class="title">${groupTableVO.group_name}</a>
									<p>店家名稱：${groupTableVO.storeVO.store_name}</p>
									<p>團長：${memberSvc.getOneMember(groupTableVO.mem_no).mem_nickname}</p>
									<p>出發日期：${groupTableVO.group_eat_date.toString().substring(0,groupTableVO.group_eat_date.toString().lastIndexOf(":"))}</p>
									<c:if test="${groupTableVO.mem_no==memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">修改</button>
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</div>
									</c:if>
									<c:if test="${groupTableVO.mem_no!=memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">退出</button>
												<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="deleteOneMem">
											</FORM>
										</div>
									</c:if>
								<button type="button" class="btn btn-default">檢舉</button>
								</div>
							</li>
						</c:when>
						<c:when test="${((groupTableVO.group_eat_date.getTime()-nowday.getTime())/( 1000*60*60*24))<0}">
							<li class="element Expired isotope-item"
								style="position: absolute; left: 0px; top: 0px; transform: translate3d(0px, 0px, 0px) scale3d(1, 1, 1); opacity: 1;">
								<img class="manageImg" src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupTableVO.group_no}">
								<span class="view">&nbsp;</span>
								<div class="recipedetails">
									<a href="" class="title">${groupTableVO.group_name}</a>
									<p>店家名稱：${groupTableVO.storeVO.store_name}</p>
									<p>團長：${memberSvc.getOneMember(groupTableVO.mem_no).mem_nickname}</p>
									<p>出發日期：${groupTableVO.group_eat_date.toString().substring(0,groupTableVO.group_eat_date.toString().lastIndexOf(":"))}</p>
									<c:if test="${groupTableVO.mem_no==memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">修改</button>
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</div>
									</c:if>
									<c:if test="${groupTableVO.mem_no!=memberVO.mem_no}">
										<p>揪團人數：${groupTableVO.groupMems.size()+1}/${groupTableVO.group_num}</p>
										<button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal${s.index}">查看團員</button>
										<div class="buttongroup">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/group/group.do">
												<button type="submit" class="btn btn-default">退出</button>
												<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
												<input type="hidden" name="group_no" value="${groupTableVO.group_no}">
												<input type="hidden" name="action" value="deleteOneMem">
											</FORM>
										</div>
									</c:if>
							
									<button type="button" class="btn btn-default">檢舉</button>
								</div>
							</li>
						</c:when>
					</c:choose>
					<div class="modal fade" id="myModal${s.index}" role="dialog">
						<div class="modal-dialog">
							<!-- Modal content-->
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">團員名單</h4>
								</div>
								<div class="modal-body">
									<table class="table table-hover table-expandable">
										<thead>
											<tr>
												<th>名稱</th>
												<th>性別</th>
												<th>信箱</th>
												<th>手機</th>
												<th>興趣</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>${memberSvc.getOneMember(groupTableVO.mem_no).mem_nickname}</td>
												<td>${memberSvc.getOneMember(groupTableVO.mem_no).mem_sex}</td>
												<td>${memberSvc.getOneMember(groupTableVO.mem_no).mem_email}</td>
												<td>${memberSvc.getOneMember(groupTableVO.mem_no).mem_cellphone }</td>
												<td>${memberSvc.getOneMember(groupTableVO.mem_no).mem_skill}</td>
											</tr>
											<tr>
												<td colspan="5"><img src="<%=request.getContextPath()%>/group/DBGifReader1.do?mem_no=${groupTableVO.mem_no}">
													${memberSvc.getOneMember(groupTableVO.mem_no).mem_intro}</td>
											</tr>
											<c:forEach var="GroupMemVO" items="${groupTableVO.groupMems}">
												<tr>
													<td>${memberSvc.getOneMember(GroupMemVO.mem_no).mem_nickname}</td>
													<td>${memberSvc.getOneMember(GroupMemVO.mem_no).mem_sex}</td>
													<td>${memberSvc.getOneMember(GroupMemVO.mem_no).mem_email}</td>
													<td>${memberSvc.getOneMember(GroupMemVO.mem_no).mem_cellphone }</td>
													<td>${memberSvc.getOneMember(GroupMemVO.mem_no).mem_skill}</td>
												</tr>
												<tr>
													<td colspan="5"><img src="<%=request.getContextPath()%>/group/DBGifReader1.do?mem_no=${GroupMemVO.mem_no}">
														${memberSvc.getOneMember(GroupMemVO.mem_no).mem_intro}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</ul>
		</div>
		<!-- Modal -->
		<!-- #container -->
	</section>
<!--============ footer ============-->
<div id="footer" style="width: 1900px; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>
<script src="<%=request.getContextPath()%>/front/group/js/jquery-latest.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/jquery.isotope.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/jquery.fancybox-1.3.4.pack.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/script.js"></script>
</html>