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
	<!--start-smoth-scrolling-->
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
	<!--============ Navigation ============-->

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
	<!-- end of headerwrapper-->

	<!--header-top-end-->
	<!--start-header-->
	<div class="header">
		<div class="container">
			<div class="head">


				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						<li><a href="index.jsp">����</a></li>
						<li><a href="personal.jsp" class="active">�ӤH����</a></li>
						<li><a href="about.jsp">�����</a></li>
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
	<!--about-starts-->
	<div class="about">
		<div class="container">
			<div class="about-main">
				<div class="col-md-8 about-left">
					<div class="about-one">
						<p>�̷s���O</p>
						<h3>�����s��~���W�馡�����N��~</h3>
					</div>
					<div class="about-two">
						<a href="single.jsp"><img src="images2/cm20140109.jpg" alt="" /></a>
						<p>�����a <a href="about.jsp">�ּw��</a> on 10 feb, 2016 <a href="#">�d��(2)</a></p>
						<p>�ѩ�̪�E�|�Ӧh �o�i���W�f�O��Q�ƨ�Q�Ѥ~�ϥ� ���F�B�ʹN�����b�����f�I���F �ܤ[�ܤ[���e���Y�L���W �p�G���O�]���f�O��i��]���|�A���J ������b�Ӧh�N�ש� �v���E�P �q�`�ڳ��b�_�����a����Y ���ѴN�ӹ������W�ݸ�H�e���S���������.</p>
						<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��A�L�׬O���ɡu�ز��p�����v�B�u��l���v�B�u�Q���ޡv�����Ҥ��������q�q�Y�칡�C���F�O���̷s�A�ץĻP�f�P�A�i���W�馡�����N�סj�׫~�@�߲{�I�{���A�U�����Ͻ����˼˷s�A�����A����Ź�ȦY�A�u��Ź�ȨS�G�ˡI�i���W�馡�����N�סj�W�H��u�_�q������v��O���i���L���n�����A�@�@�_�q�����D�N���H�����j�}�A��J�U�������l��������ػP�_�q����A�C�f���u��ιL�}��r�ήe�C.</p>
						<div class="about-btn">
							<a href="single.jsp">Read More</a>
						</div>
						<ul>
							<li>
								<p>Share : </p>
							</li>
							<li><a href="#"><span class="fb"> </span></a></li>

						</ul>
					</div>
					<div class="about-tre">
						<div class="a-1">
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad2.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">�a���շŲ����K�O�N</a></h3>
								<p>����O�~�[�ݰ_�ӫD�`���ت��خ� �ӷs�a���ն}�b�o��@�w�]�n�D�`����]�� ť���Ӹ��8000�U���y �u���O�I�R��өO! �@�Ӥ@�i���N���@���C��ܬ����j�t�Ͼ�.
								</p>
								<label>May 29, 2015</label>
							</div>
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad3.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">��������~�j�K�j����</a></h3>

								<p>�����󰪶������߸θ۸��P�շR����e���f�A�֦��u�V���a�z��m�P�U����o�����A��W�[�F�h���榡�A�H�̰��W�檺���һP�A�ȡA���C�@���U�ȳ���P�컫�ܦp�k�C.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="a-1">
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad5.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">���k�إq</a></h3>
								<p>���L�ߩ󦹪��u��[�W���驱���ֻ��]���Q�a�a!�γ\�٤��u,�|�Z��~�Q�p��~���N~����~����~�H�J��!���ҬO!�������䤤���@�a�饻���Q�����إq���ޤH�`��!���ݥL�X�����n���藍�_�ۤv����.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="col-md-6 abt-left">

								<a href="single.jsp"><img src="images2/ad1.jpg" alt="" /></a>
								<h6>Read More</h6>
								<h3><a href="single.jsp">Tasty Coffee</a></h3>
								<p>���ѨӨ�F����������P������񪺬��� �԰ϦN�L��]���W!(���e��]��O�԰Ϥ��@) ����񲴱�h���ݹL�h���B���O�p�Y���a�A �o�̤]�O�ǥͮɴ��`�Ӫ��a��!�n�h���a���� �o��ɪ��h���O��.</p>
								<label>May 29, 2015</label>
							</div>
							<div class="clearfix"></div>
						</div>

					</div>
				</div>
				<div class="col-md-4 about-right heading">
					<div class="abt-1">
						<h3>�����</h3>
						<div class="abt-one">
							<img src="images2/KFC.jpg" alt="" />
							<p>�ּw��O�M�欵�������\�s�ꩱ�A�`���]�����ֶ��{�����������C�O���y�ĤG�j���\���s����~.</p>
							<div class="a-btn">
								<a href="about.jsp">�����h</a></br>
								<a href="">�[�J���`</a></br>
							</div>
						</div>
					</div>
					<div class="abt-2">
						<h3>�A�i��]���w�����O</h3>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">�s���Ʋz�T���Q</a></h4>
								<p>���Ѫ����\�گS�O��F�i�H�ݩ]�� �٥i�H���{���t�۪��~�Ӷ����s���s�� �ݬݮ�W�������������O���O�W����^���O.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/right1.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">�����W��{�����x</a></h4>
								<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��,�����Ҥ��������q�q�Y�칡.</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/left1.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">������A���s���C�s���s��</a></h4>
								<p>��W�������������ݦ�����A�o���@�I�]�����R�A ��ä��t�w��\��A��_�o��Ӹ`�A�s��ڦ��\�U��o��Τ�!!.
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
					<div class="abt-2">
						<h3>�̷s���é��a</h3>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="">�s���Ʋz�T���Q���@</a></h4>
								<p>�a�ϡG������</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href="single.jsp"><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="">�s���Ʋz�T���Q���T</a></h4>
								<p>�a�ϡG�x�_��</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
						<div class="might-grid">
							<div class="grid-might">
								<a href=""><img src="images2/ma.jpg" class="img-responsive" alt=""> </a>
							</div>
							<div class="might-top">
								<h4><a href="single.jsp">�s���Ʋz�T���Q���G</a></h4>
								<p>�a�ϡG�x�n��</p>
								<p>�����G���A�Ʋz</p>
							</div>
							<div class="clearfix"></div>

						</div>
					</div>
					<div class="abt-2">
						<h3>�֨����`�A</h3>
						<div class="flower">
							<a href=""><img src="images2/t-4.jpg" class="img-flower" alt=""> </a>
							<a href=""><img src="images2/t-2.jpg" class="img-flower" alt=""> </a>
							<a href=""><img src="images2/t-3.jpg" class="img-flower" alt=""> </a>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--about-end-->
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
				<p></p>
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>

</html>