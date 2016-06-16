<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>�����s��~���W�馡�����N��~</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/personalStyle.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"></script>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
		<script type="text/javascript">
			$(function(){
				$("#login").click(function(){
					$("#login_from").submit();
				}
				)
			});
		</script>
<!--start-smoth-scrolling-->
</head>
<body>
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

	<!--start-single-->
	<div class="single">
		<div class="container">
				<div class="single-top">
						
					<div class=" single-grid">
						<h4>�����s��~���W�馡�����N��~</h4>				
							<ul class="blog-ic">
								<li><a href="about.jsp"><span> <i  class="glyphicon glyphicon-user"> </i>�ּw��</span> </a> </li>
		  						 <li><span><i class="glyphicon glyphicon-time"> </i>10 feb, 2016</span></li>		  						 	
		  						 <li><span><i class="glyphicon glyphicon-eye-open"> </i>�I����:15</span></li>
		  					</ul>	
<a href="#"><img class="img-responsive" src="images2/cm20140109.jpg" alt=" "></a>	  						
						<p>�ѩ�̪�E�|�Ӧh �o�i���W�f�O��Q�ƨ�Q�Ѥ~�ϥ� ���F�B�ʹN�����b�����f�I���F �ܤ[�ܤ[���e���Y�L���W �p�G���O�]���f�O��i��]���|�A���J ������b�Ӧh�N�ש� �v���E�P �q�`�ڳ��b�_�����a����Y ���ѴN�ӹ������W�ݸ�H�e���S���������.</p>
						<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��A�L�׬O���ɡu�ز��p�����v�B�u��l���v�B�u�Q���ޡv�����Ҥ��������q�q�Y�칡�C���F�O���̷s�A�ץĻP�f�P�A�i���W�馡�����N�סj�׫~�@�߲{�I�{���A�U�����Ͻ����˼˷s�A�����A����Ź�ȦY�A�u��Ź�ȨS�G�ˡI�i���W�馡�����N�סj�W�H��u�_�q������v��O���i���L���n�����A�@�@�_�q�����D�N���H�����j�}�A��J�U�������l��������ػP�_�q����A�C�f���u��ιL�}��r�ήe�C.</p>
						<p>�ѩ�̪�E�|�Ӧh �o�i���W�f�O��Q�ƨ�Q�Ѥ~�ϥ� ���F�B�ʹN�����b�����f�I���F �ܤ[�ܤ[���e���Y�L���W �p�G���O�]���f�O��i��]���|�A���J ������b�Ӧh�N�ש� �v���E�P �q�`�ڳ��b�_�����a����Y ���ѴN�ӹ������W�ݸ�H�e���S���������.</p>
						<a href="single.jsp"><img src="images2/ad3.jpg" alt="" /></a>
						<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��A�L�׬O���ɡu�ز��p�����v�B�u��l���v�B�u�Q���ޡv�����Ҥ��������q�q�Y�칡�C���F�O���̷s�A�ץĻP�f�P�A�i���W�馡�����N�סj�׫~�@�߲{�I�{���A�U�����Ͻ����˼˷s�A�����A����Ź�ȦY�A�u��Ź�ȨS�G�ˡI�i���W�馡�����N�סj�W�H��u�_�q������v��O���i���L���n�����A�@�@�_�q�����D�N���H�����j�}�A��J�U�������l��������ػP�_�q����A�C�f���u��ιL�}��r�ήe�C.</p>
<p>�ѩ�̪�E�|�Ӧh �o�i���W�f�O��Q�ƨ�Q�Ѥ~�ϥ� ���F�B�ʹN�����b�����f�I���F �ܤ[�ܤ[���e���Y�L���W �p�G���O�]���f�O��i��]���|�A���J ������b�Ӧh�N�ש� �v���E�P �q�`�ڳ��b�_�����a����Y ���ѴN�ӹ������W�ݸ�H�e���S���������.</p>
						<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��A�L�׬O���ɡu�ز��p�����v�B�u��l���v�B�u�Q���ޡv�����Ҥ��������q�q�Y�칡�C���F�O���̷s�A�ץĻP�f�P�A�i���W�馡�����N�סj�׫~�@�߲{�I�{���A�U�����Ͻ����˼˷s�A�����A����Ź�ȦY�A�u��Ź�ȨS�G�ˡI�i���W�馡�����N�סj�W�H��u�_�q������v��O���i���L���n�����A�@�@�_�q�����D�N���H�����j�}�A��J�U�������l��������ػP�_�q����A�C�f���u��ιL�}��r�ήe�C.</p>
						<p>�ѩ�̪�E�|�Ӧh �o�i���W�f�O��Q�ƨ�Q�Ѥ~�ϥ� ���F�B�ʹN�����b�����f�I���F �ܤ[�ܤ[���e���Y�L���W �p�G���O�]���f�O��i��]���|�A���J ������b�Ӧh�N�ש� �v���E�P �q�`�ڳ��b�_�����a����Y ���ѴN�ӹ������W�ݸ�H�e���S���������.</p>
						<p>���F�s�A�{�����A�A�i���W�馡�����N�סj�Y��׫~�A���ѳ��I�N�׵��ū~��A�L�׬O���ɡu�ز��p�����v�B�u��l���v�B�u�Q���ޡv�����Ҥ��������q�q�Y�칡�C���F�O���̷s�A�ץĻP�f�P�A�i���W�馡�����N�סj�׫~�@�߲{�I�{���A�U�����Ͻ����˼˷s�A�����A����Ź�ȦY�A�u��Ź�ȨS�G�ˡI�i���W�馡�����N�סj�W�H��u�_�q������v��O���i���L���n�����A�@�@�_�q�����D�N���H�����j�}�A��J�U�������l��������ػP�_�q����A�C�f���u��ιL�}��r�ήe�C.</p>
					</div>
					<div class="comments heading">
						<h3>�d��</h3>
						<div class="media">
					      	<div class="media-body">
						        <h4 class="media-heading">�����</h4>
						        <p>���@�ѡA�q�δ�,�q���R,�q�Ӯ�T�H�h��a�A���G�T�H���Ĥ��F�A���O�u���q�Ӯ�|��a�A����q���R�ϤF�_�ӡA�M�ӱϤW�����q���R�N��q�Ӯ𻡡G.</p>
					      	</div>
					      <div class="media-right">
					        <a href="#">
								<img src="images/t-2.jpg" alt="" width=200px> </a>
					      </div>
					 </div>
					  <div class="media">
					      <div class="media-left">
					        <a href="#">
					        	<img src="images/t-2.jpg" alt="" width=200px>
					        </a>
					      </div>
					      <div class="media-body">
					        <h4 class="media-heading">�����</h4>
					        <p>�����ϰq�δ�.</p>
					      </div>
					    </div>
    				</div>
    				<div class="comment-bottom heading">
    					<h3>�ڭn�d��</h3>
    					<form>	
						<input type="text" value="Name" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='Name';}">
						<input type="text" value="Subject" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='Subject';}">
						<textarea cols="77" rows="6" value=" " onfocus="this.value='';" onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
							<input type="submit" value="Send">
					</form>
    				</div>
				</div>	
			</div>					
	</div>
	<!--end-single-->
	<!--footer-starts-->
	<div class="footer">
		<div class="container">
			<div class="footer-text">
				<p> </p>
			</div>
		</div>
	</div>
	<!--footer-end-->
</body>
</html>