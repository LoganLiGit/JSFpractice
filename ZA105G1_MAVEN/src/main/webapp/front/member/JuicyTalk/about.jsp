<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<title>�ּw�򪺭ӤH����</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<script type="application/x-javascript">
		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
	</script>
	<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
	<link href="css/personalStyle.css" rel='stylesheet' type='text/css' />
	<script src="js/jquery.min.js"></script>
	<!---- start-smoth-scrolling---->
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function ($) {
			$(".scroll").click(function (event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop: $(this.hash).offset().top
				}, 1000);
			});
		});
	</script>

	<script src="js/modernizr.custom.97074.js"></script>
	<script src="js/jquery.chocolat.js"></script>
	<link rel="stylesheet" href="css/chocolat.css" type="text/css" media="screen" charset="utf-8">
	<!--light-box-files -->
	<script type="text/javascript" charset="utf-8">
		$(function () {
			$('.gallery a').Chocolat();
		});
	</script>
	<script type="text/javascript" src="js/jquery.hoverdir.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			$("#login_from").submit();
		}
		)
	});
	</script>
</head>

<body>
	<!--header-top-starts-->
	<div id="slider" class="container">
		<div class="login">
		
		
<!--********�n�J�n�X�ʺA*********** -->
		 <c:choose>
		    <c:when test="${ memberVO.mem_name!=null }">
				<a href="<%=request.getContextPath()%>/front/member/update/update.jsp"><button width="20" height="20" >${memberVO.mem_name}�A�n</button> </a> 
				<a href="<%=request.getContextPath()%>/front/member/logout/logout.jsp"><button width="20" height="20" >�n�X</button> </a>
		    </c:when>
		    <c:otherwise>
				<a id="login" ><button width="20" height="20" >�n�J�|��</button></a>
		        <a href="<%=request.getContextPath()%>/front/member/register/register.jsp"><button width="20" height="20" >���U�|��</button> </a>
		    </c:otherwise>
		  </c:choose>
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front/member/login/login.jsp" id="login_from" >
		<input type="hidden" name="url" value="<%=request.getServletPath()%>">
	</FORM>
<!--********�n�J�n�X�ʺA*********** -->

		</div>
	</div>
	<div class="headerwrapper">

		<div id="header" class="container">

			<div class="logo">
				<a href="#"><img src="images2/logo2.jpg" alt="logo" width="90" height="90"></a>
			</div>
			<!--end of Logo-->
			<nav>
				 <ul id="navigations">
                <li><a href="index.jsp">HOME</a></li>
                <li><a href="test.jsp">test.jsp</a></li>
                <li><a href="test2.jsp">test2.jsp</a></li>
                <li><a href="personal.jsp">�ӤH����</a></li>
                <li><a href="#contactus">���ʨ�ӫ�</a></li>
            </ul>
			</nav>
		</div>
		<!--end of header-->
	</div>
	<!--header-top-end-->
	<!--start-header-->
	<div class="header">
		<div class="container">
			<div class="head">


				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="index.jsp">����</a></li>
						<li><a href="personal.jsp">�ӤH����</a></li>
						<li><a href="about.jsp" class="active">�����</a></li>
						<li><a href="">�ڪ����O</a></li>
						<li><a href="gallery.jsp">�ڪ���ï</a></li>

					</ul>
				</div>


				<div class="header-right">
					<div class="search-bar">
						<input type="text" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
						<input type="submit" value="">
					</div>
					<ul>
						<li><a href="#"><span class="fb"> </span></a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- script-for-menu -->
	<!-- script-for-menu -->
	<script>
		$("span.menu").click(function () {
			$(" ul.navig").slideToggle("slow", function () {});
		});
	</script>
	<!-- script-for-menu -->
	<!--banner-starts-->
	<div class="banner">
		<div class="container">
			<div class="banner-top">

			</div>
		</div>
	</div>
	<!--banner-end-->
	<!--welcome-starts-->
	<div class="welcome">
		<div class="container">
			<div class="welcome-top heading">
				<h3>�w��j�a</h3>
				<div class="welcome-bottom">
					<img src="images2/KFC2.jpg" alt="" />
					<p>�ּw��O�M�欵�������\�s�ꩱ�A�`���]�����ֶ��{�����������C�O���y�ĤG�j���\���s����~�A�Ȧ������ҡA�I��2013�~12��A�ּw��b118�Ӱ�a�M�a�Ͼ֦�1�U8000�Ӥ����C �ּw��ѫ����w�P��w���п�A��w���b�j�����ɴ��}�l�b���ֶ��{�캸�����������\�U�欵���C��w���F�Ѩ��\�U�S�\�g�窺��������O�A1952�~�A�Ĥ@�a�u�ּw�����v���S�\�g���\�U�b�S�L�{�}����~�A�P�~���b�����W�v���C��w���H�u��w���W�աv���W�r�D�W�A���������ƾ��v���ۦW�H���A�ּw��s�x�H��w���@���s�i�ζH�C1964�~�A�L�N���q�浹�@����̡A�D�n�������P���ԩM�ǧJ�P����C �ּw��O�Ĥ@�a�V����X�i���s���\���~���@�A1960�~�N�����A�ּw���^��B������Τ��R�}�]�����C70�~�N��80�~�N�����A�ּw��ꤺ�~�~�Z�߼~�ѥb�A�o�O�]���ּw����~�Ҧ��v�洫���O�����~�A�ӳo�ǥ��~���\�U�޲z�S���g��C1970�~�N��A�ּw��浹�F�P�s�g�P���q��B�ܮ��A��R. J.�p�կ��ү󤽥q���ޡA��ӤS�N�ּw����浹�ʨƤ��q�C1987�~�b����}�]���Ĥ@�a�覡�\���s�ꩱ�C�ּw��ۦ��b���ꨳ�t�X�i�A�p������O�ּw��Q��̰��������C�ʨƤ��q�����\�U�����A�߮��d���y�\�����q�A���W���ʳ��\�����ΡC �ּw�򪺲��~�O�������A�H��w����11�د��ĩM���ƪ��t��ը�,�t�誺�����O�ӷ~���K�C��1990�~�N��H�ӡA�ּw���_�X�i���A���Ѩ�L���ײ��~�p���h�]�A�F��,�����B�D�ձ��ߵ�A���~�M���ơA���ƥѦʨƤ��q�����C.
					</p>
				</div>
			</div>
		</div>
	</div>
	<!--welcome-end-->
	<!--	�ӤH���-->
	<div class="team">
		<div class="container">
			<div class="team-top heading">
				<h3>�ӤH���</h3>
				 
				
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	</div>


	<!--team-starts-->
	<div class="team">
		<div class="container">
			<div class="team-top heading">
				<h3>�ڪ��n��</h3>
			</div>
			<div class="team-bottom">
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>�����</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>�����</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>�����</h4>
				</div>
				<div class="col-md-3 team-left">
					<img src="images/t-2.jpg" alt="" />
					<h4>�����</h4>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--team-end-->
	<!--slide-starts-->
	<div class="slide">
		<div class="container">
			<div class="fle-xsel">
				<ul id="flexiselDemo3">
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/01.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/02.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad1.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad2.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad3.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="banner-1">
								<img src="images2/ad5.jpg" class="img-responsive" alt="">
							</div>
						</a>
					</li>
				</ul>

				<script type="text/javascript">
					$(window).load(function () {

						$("#flexiselDemo3").flexisel({
							visibleItems: 5,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: {
								portrait: {
									changePoint: 480,
									visibleItems: 2
								},
								landscape: {
									changePoint: 640,
									visibleItems: 3
								},
								tablet: {
									changePoint: 768,
									visibleItems: 3
								}
							}
						});

					});
				</script>
				<script type="text/javascript" src="js/jquery.flexisel.js"></script>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	<!--slide-end-->
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-text">
				
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>

</html>