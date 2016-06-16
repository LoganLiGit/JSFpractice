<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.group.table.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	GroupTableVO groupVO = (GroupTableVO) request
			.getAttribute("groupVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html lang="zh_TW">
<jsp:useBean id="groupSvc" scope="page" class="com.group.table.model.GroupTableService" />
<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/front/group/css/style4.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/style.css">
<link
	href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900,200italic,300italic,400italic,600italic,700italic,900italic'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lobster+Two:400,400italic,700,700italic' rel='stylesheet' type='text/css'>
<link href="<%=request.getContextPath()%>/front/group/css/animate.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="http://libs.useso.com/js/font-awesome/4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/formValidation.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front/group/css/default.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/group/css/bootstrap-datetimepicker.min.css" />
<link href="<%=request.getContextPath()%>/front/group/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/front/group/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 2000);
		});

	});
</script>
<script src="<%=request.getContextPath()%>/front/group/js/tms-0.4.1.js"></script>
<script src="<%=request.getContextPath()%>/front/group/js/jquery.easydropdown.js"></script>
<title>Insert title here</title>
</head>
<body>
<body>
		<!--============ header ============-->
	
	  <div id="header" style="width:1900px;height:225px;">
              <iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no">
             </iFrame>
         </div> 


<!--============ header  ============-->
	<!-- end of headerwrapper-->
	<!-- header-section-ends -->
	<div class="order-section-page">
		<div class="ordering-form">
			<div class="containerGroup">
				<form id="defaultForm" method="post" action="<%=request.getContextPath()%>/group/group.do" class="form-horizontal" enctype="multipart/form-data">
					<div class="order-form-head text-center wow bounceInLeft" data-wow-delay="0.4s">
						<h3>修改你的揪團</h3>
						<p>Have A Meal Have Fun！！</p>
						<div class="col-md-6 order-form-grids">
							<section>
								<div class="page-header">
									<h2>更改揪團資料</h2>
								</div>
								<div class="col-lg-8 col-lg-offset-2">
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">揪團名稱</label>
											<div class="col-lg-5">
												<input type="text" class="form-control" name="group_name" value="${groupVO.group_name}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">團長名稱</label>
											<div class="col-lg-5">
											<input type="text" class="form-control" name="group_name" value="${memberVO.mem_nickname}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">吃飯店家</label>
											<div class="col-lg-5">
												<select class="form-control" name="store_no">
													<option value="${groupVO.storeVO.store_no}">${groupVO.storeVO.store_name}</option>
													<c:forEach var="storeVO" items="${storeSvc.all}">
														<option value="${storeVO.store_no}">${storeVO.store_name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</fieldset>
									<fieldset>
										<div class="form-group">
											<label class="col-lg-3 control-label">揪團人數上限</label>
											<div class="col-lg-5">
												<input type="number" value="${groupVO.group_num}" class="form-control" name="group_num" min="1" data-fv-greaterthan-inclusive="false"
													data-fv-greaterthan-message="請選擇人數2-10" max="10" />
											</div>
										</div>
									</fieldset>
									<fieldset>
										<legend>簡單介紹這次的揪團</legend>
										<div class="form-group">
											<label class="col-lg-3 control-label">揪團簡介</label>
											<div class="col-lg-3">
												<textarea id="group_intro" name="group_intro">${groupVO.group_intro}</textarea>
											</div>
										</div>
									</fieldset>
									<fieldset>
										<%
											SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy H:mm a");
											String group_eat_date = df.format(groupVO.getGroup_eat_date());
											pageContext.setAttribute("group_eat_date", group_eat_date);
											String group_stop_date = df.format(groupVO.getGroup_stop_date());
											pageContext.setAttribute("group_stop_date", group_stop_date);
										%>
										<legend>填寫相關揪團時間</legend>
										<div class="form-group">
											<label class="col-lg-3 control-label">出發時間</label>
											<div class="col-lg-5">
												<div class="input-group date" id="datetimePicker1">
													<input type="text" class="form-control" name="group_eat_date" value="${group_eat_date}" />
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-lg-3 control-label">入團截止</label>
											<div class="col-lg-5">
												<div class="input-group date " id="datetimePicker3">
													<input type="text" class="form-control" name="group_stop_date" value="${group_stop_date}" />
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>
									</fieldset>
									<div class="form-group">
										<div class="col-lg-9 col-lg-offset-3">
											<button type="submit" class="btn btn-primary">Submit</button>
										</div>
									</div>
								</div>
							</section>
						</div>
					</div>
					<div class="col-md-6 ordering-image wow bounceIn" data-wow-delay="0.4s">
						<h2>揪團說明</h2>
						<label class="groupintro">想認識新朋友，又苦無管道的人，或是想嘗試新餐廳新菜色，又不想單獨一人，填寫完成左邊的資料一起相約吃飯吧！</label>
						<label class="groupintro">上傳一張圖片當揪團封面吧！</label>
						<input id="file-0a" class="file" type="file" multiple data-min-file-count="1" name="group_photo">
						<br>
					</div>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="group_no" value="${groupVO.group_no}">
				</form>
			</div>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/front/group/js/fileinput.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/front/group/js/fileinput_locale_zh.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/jquery.min2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/formValidation.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/bootstrap.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/moment.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$('#datetimePicker1').datetimepicker();
					$('#datetimePicker2').datetimepicker();
					$('#datetimePicker3').datetimepicker();
					$('#defaultForm').formValidation({
						message : '值不能為空',
						icon : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							group_name : {
								validators : {
									notEmpty : {
										message : '團名不能為空'
									},
									stringLength : {
										min : 2,
										max : 10,
										message : '團名長度需介於2-10之間'
									}
								}
							},
							store : {
								validators : {
									notEmpty : {
										message : '選擇出發的店家'
									}
								}
							},
							mem_no : {
								validators : {
									notEmpty : {
										message : '團長名稱不能為空'
									},
									stringLength : {
										min : 2,
										max : 10,
										message : '團長名稱需介於2-10之間'
									}
								}
							},
							group_num : {
								validators : {
									notEmpty : {
										message : '選擇人數上限'
									}
								}
							},

							group_intro : {
								validators : {
									notEmpty : {
										message : '簡介不能為空'
									},
									stringLength : {
										min : 10,
										max : 150,
										message : '字數介於10-150'
									}
								}
							},
							group_eat_date : {
								validators : {
									notEmpty : {
										message : '選擇吃飯時間'
									},
									date : {
										format : 'MM/DD/YYYY h:m A'

									}
								}
							},
							group_start_date : {
								validators : {
									notEmpty : {
										message : '選擇開始揪團時間'
									},
									date : {
										format : 'MM/DD/YYYY h:m A'

									}
								}
							},
							group_stop_date : {
								validators : {
									notEmpty : {
										message : '選擇截止揪團時間'
									},
									date : {
										format : 'MM/DD/YYYY h:m A'
									}
								}
							}

						}
					});
					$('#datetimePicker1').on(
							'dp.change dp.show',
							function(e) {
								// Validate the date when user change it
								$('#defaultForm').data('formValidation')
										.revalidateField('group_eat_date');
								// You also can call it as following:
								// $('#defaultForm').formValidation('revalidateField', 'datetimePicker');
							});
					$('#datetimePicker2').on(
							'dp.change dp.show',
							function(e) {
								// Validate the date when user change it
								$('#defaultForm').data('formValidation')
										.revalidateField('group_start_date');
								// You also can call it as following:
								// $('#defaultForm').formValidation('revalidateField', 'datetimePicker');
							});
					$('#datetimePicker3').on(
							'dp.change dp.show',
							function(e) {
								// Validate the date when user change it
								$('#defaultForm').data('formValidation')
										.revalidateField('group_stop_date');
								// You also can call it as following:
								// $('#defaultForm').formValidation('revalidateField', 'datetimePicker');
							});
				});
	</script>
	<div class="special-offers-section">
		<div class="containerGroup">
			<div class="special-offers-section-head text-center dotted-line">
				<h4>其他揪團列表</h4>
			</div>
			<div class="special-offers-section-grids">
				<div class="m_3">
					<span class="middle-dotted-line"> </span>
				</div>
				<div class="containerGroup">
					<ul id="flexiselDemo3">
						<c:forEach var="groupVO" items="${groupSvc.all}" varStatus="s">
							<li>
								<div class="offer">
									<div class="offer-image">
										<img src="<%=request.getContextPath()%>/group/DBGifReader4.do?group_no=${groupVO.group_no}" class="img-responsive" alt="" />
									</div>
									<div class="offer-text">
										<h4>${groupVO.group_name}</h4>
										<p>${groupVO.group_intro}</p>
										<input type="button" value="Grab It">
										<span></span>
									</div>
									<div class="clearfix"></div>
								</div>
							</li>
						</c:forEach>
					</ul>
					<script type="text/javascript">
						$(window).load(function() {
							$("#flexiselDemo3").flexisel({
								visibleItems : 3,
								animationSpeed : 1000,
								autoPlay : true,
								autoPlaySpeed : 3000,
								pauseOnHover : true,
								enableResponsiveBreakpoints : true,
								responsiveBreakpoints : {
									portrait : {
										changePoint : 480,
										visibleItems : 1
									},
									landscape : {
										changePoint : 640,
										visibleItems : 2
									},
									tablet : {
										changePoint : 768,
										visibleItems : 3
									}
								}
							});

						});
					</script>
					<script type="text/javascript" src="<%=request.getContextPath()%>/front/group/js/jquery.flexisel.js"></script>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- footer-section-starts -->
	<!-- footer-section-ends -->
	<!--============ COPYRIGHTS ============-->
	<div class="copyrightswrapper">
		<div id="copyrights" class="container">
			<p>Copyright 2016 All Rights Reserved</p>
		</div>
		<!-- end of copyrights-->
		<!-- end of website-->
</body>
</body>
</html>