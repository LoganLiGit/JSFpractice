<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.album.model.*"%>
<%
	List<AlbumVO> myAlbum = (List<AlbumVO>) session.getAttribute("myAlbum"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("相片筆數:" + myAlbum.size());
	pageContext.setAttribute("myAlbum", myAlbum);
%>
<!DOCTYPE html>
<html>

<head>
<title>${memberVO2.mem_name}的相簿</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<meta name="keywords" content="Coffee Break Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

<!-- 連結關於javascript的程式區 開始 -->


<script type="application/x-javascript">

		addEventListener("load", function () {
			setTimeout(hideURLbar, 0);
		}, false);

		function hideURLbar() {
			window.scrollTo(0, 1);
		}
		
</script>

<script src="<%=request.getContextPath()%>/front/personal/js/jquery.min.js"></script>

<!---- start-smoth-scrolling---->
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/move-top.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/easing.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/facebookchatboxUI.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/magicsuggest-min.js"></script>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/modernizr.custom.97074.js"></script>
<script src="<%=request.getContextPath()%>/front/personal/js/jquery.chocolat.js"></script>

	
	<!--light-box-files -->
	<script type="text/javascript" charset="utf-8">
		$(function () {
			$('.gallery a').Chocolat();
		});
	</script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/front/personal/js/jquery.hoverdir.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
		
	
		
	});
 	 
</script>

<script>
	var file = Array(5);
	var fileReader;
	function doFirst()
	{
  		 document.getElementById('myFile').onchange = fileChange;
	}
	
	
	function fileChange()
	{
		
	    file = document.getElementById('myFile').files[0];
	    if(file)
	    {
	        fileReader = new FileReader();
	        fileReader.readAsDataURL(file);
	        fileReader.onload = openFile;
	    }
	}
	
	function openFile(e)
	{
	    //document.getElementById('fileContent').value = fileReader.result;
	    document.getElementById('image').src = e.target.result;
	}
	
	window.addEventListener('load',doFirst,false);	
	
</script>


<!-- 連結關於javascript的程式區 結束 -->


<!-- 連結關於css的程式區 開始 -->

<link href="<%=request.getContextPath()%>/front/personal/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/personalStyle.css" rel='stylesheet' type='text/css' />
<link href="<%=request.getContextPath()%>/front/personal/css/magicsuggest-min.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
<link rel="stylesheet"href="<%=request.getContextPath()%>/front/personal/css/facebookchatboxUI.css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
<!-- 連結關於css的程式區 結束 -->
<style>
	.latestimgtest img {
		width:600px !important;
		height:337px !important;
		
	}

	span img {
		width:312px !important;
		height:265px !important;
		
	}
	
	.grid-might img {
		width:95px !important;
		height:71px !important;
		
	}
	
	.col-md{
		background-color:orange;
	}	
		
	
	
</style>
</head>

<body>
<!--============ header ============-->
<div id="header" style="width:100%; height: 225px;">
	<iFrame src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
</div>
<!--============ header  ============-->
	

	<div class="header">
		
		<div class="container" style="padding:20px;">
			<div class="head">
<!-- 上方副功能導覽列 -->			
				<div class="navigation">
					<span class="menu"></span>
					<ul class="navig">
						
						
						<li style="border-right:solid 5px;">
<%-- 						<li><form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do"> --%>
<!-- 								<input type="submit" value="關於我" >  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 							</form> -->
							<a href="../member/member.do?action=getOne_For_Display&mem_no=${memberVO2.mem_no}"
								style="font-size:36px; font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">關於我</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="所有食記" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_SomeOne_All_Article">	 -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_SomeOne_All_Article&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">所有食記</a>
						</li>
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do"> --%>
<!-- 								<input type="submit" value="相簿" class="btn-info btn-lg">  -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}">  --%>
<!-- 								<input type="hidden" name="action" value="get_Album"> -->
<!-- 							</form> -->
							<a href="../personal/personal.do?action=get_Album&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">相簿</a>
						</li>
						<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
						<li style="border-right:solid 5px;">
<%-- 							<form METHOD="post" ACTION="<%=request.getContextPath()%>/article/article.do"> --%>
<!-- 								<input type="submit" value="發表食記" class="btn-info btn-lg"> -->
<!-- 								<input type="hidden" name="action" value="myArticle"> -->
<!-- 							</form> -->
							<a href="../article/article.do?action=myArticle"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">發表食記</a>
						</li>
						</c:if>
						<li style="border-right:solid 5px;">
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/friend/friend.do"> --%>
<!-- 								<input type="submit" value="我的朋友" class="btn-info btn-lg"> -->
<%-- 								<input type="hidden" name="mem_no" value="${memberVO2.mem_no}" > --%>
<!-- 								<input type="hidden" name="action" value="getSome_For_Display"> -->
<!-- 							</FORM> -->
							<a href="../friend/friend.do?action=getSome_For_Display&mem_no=${memberVO2.mem_no}"
							style="font-size:36px;font-family: Meiryo, 微軟正黑體, Microsoft JhengHei;">我的朋友</a>
						</li>

					</ul>
				</div>

<!-- 搜尋使用者bar -->
				<div class="header-right">
					<div class="search-bar">		
						 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do">
							<input type="submit" value="">
							<input id="tags"  name="mem_name" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
							<input type="hidden" name="login_mem_no" value="${memberVO.mem_no}">						
							<input type="hidden" name="action" value="getPersonal_Display">
						</FORM> 
					</div>
				</div>
			</div>
		</div>
	<script>
		$("span.menu").click(function () {
			$(" ul.navig").slideToggle("slow", function () {});
		});
	</script>
	<!-- script-for-menu -->
	<!--banner-starts-->
	
	<!--banner-end-->
	<!--gallery-starts-->
	<div class="gallery">
		<div class="container">
			<c:if test="${memberVO.mem_no==memberVO2.mem_no}">
			<div>
<!-- 按鈕觸發 modal -->							
				<input type="button" id="addPhoto" class=" btn-default btn-lg" data-toggle="modal" data-target="#myModal" value="新增照片">	
			</div>
			</c:if>
			
			<div class="container">
					<!-- Modal -->
  					<div class="modal fade" id="myModal" role="dialog" >
   					<div class="modal-dialog">
    
     				 <!-- Modal content-->
      				<div align="center" class="modal-content" style="width:1000px;">
        				<div class="modal-header">
          					<button type="button" class="close" data-dismiss="modal">&times;</button>
         					 <h4 class="modal-title">新增一張照片</h4>
        				</div>
        				<div class="modal-body" id="modal-body" style=" height:500px;">
        					<form METHOD="post" ACTION="<%=request.getContextPath()%>/personal/personal.do" enctype="multipart/form-data">
	        					新增照片:<input type="file" name="mem_photo" id="myFile">   
	        						   <input type="hidden" name="photo_mem_no" value="${memberVO.mem_no}">	 
	          					照片標題:<input type="text" name="photo_title" value="請輸入照片標題!!!">	
	          					照片描述:<input type="text" name="photo_description" value="請輸入照片描述!!!">
	          						   <input type="hidden" name="action" value="insert_photo">	
	          						   <input type="submit" class="btn btn-default" value="確定">	
	          						   <p>
       										<img id="image" width="250">
   									   </p>	
          					</form>	   		
        				</div>
        				<div>       		
								 
								   					     				        
        				</div>
        				<div class="modal-footer">
          					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        				</div>
     				</div>
      
    				</div>
  					</div>
				</div>
			
			<div class="gallery-top heading">
				<h3>${memberVO2.mem_name}的相簿</h3>
			</div>
			<section>
				<ul id="da-thumbs" class="da-thumbs">
					<c:forEach var="albumVO" items="${myAlbum}">
						<c:if test="${myAlbum.size()!=0}">
						<li>
							<a href="<%=request.getContextPath()%>/DBGifReader13?photo_no=${albumVO.photo_no}" rel="title" class="b-link-stripe b-animate-go  thickbox">
								<img src="<%=request.getContextPath()%>/DBGifReader13?photo_no=${albumVO.photo_no}">
								<div>
									<h5>${albumVO.photo_title}</h5>
									<span>${albumVO.photo_description}</span>
								</div>
							</a>
						</li>
						
						</c:if>
						
					</c:forEach>
					
					<div class="clearfix"> </div>
				</ul>
			</section>

			<script type="text/javascript">
				$(function () {

					$(' #da-thumbs > li ').each(function () {
						$(this).hoverdir();
					});

				});
			</script>
		</div>
	</div>
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>

</html>