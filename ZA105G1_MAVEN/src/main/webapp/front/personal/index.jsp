<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>


<html>
<head>
	<title>Juicy Talk</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <script src="<%=request.getContextPath()%>/front/personal/js/jquery.js"></script> 
	<script src="<%=request.getContextPath()%>/front/personal/js/jquery.glide.js"></script>
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/front/personal/css/animate.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/MyJQ.js"></script>
    <script src="<%=request.getContextPath()%>/front/personal/js/jquery.localScroll.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/front/personal/js/jquery.scrollTo.min.js" type="text/javascript"></script> 
    <script src="<%=request.getContextPath()%>/front/personal/js/wow.min.js" type="text/javascript"></script> 

<!-- scroll function -->
<script type="text/javascript">
$(document).ready(function() {
   $('#navigations').localScroll({duration:800});
});
</script>


<script src="<%=request.getContextPath()%>/front/personal/js/wow.min.js"></script>
<script>
new WOW().init();
</script>


</head>
<body>

<!--============ Navigation ============-->

  <div id="slider" class="container">
 <div class="login">
<a href="<%=request.getContextPath()%>/front/personal/login.jsp"><button width="20" height="20" >�n�J�|��</button> </a> 
<a href="../���U/���U.html"><button width="20" height="20" >���U�|��</button> </a> 
<a href="<%=request.getContextPath()%>/front/personal/logout.jsp"><button width="20" height="20" >�n�X�|��</button> </a>
</div>
</div>
<div class="headerwrapper">

	<div id="header" class="container">
   
		<div class="logo"> <a href="<%=request.getContextPath()%>/front/personal/index.jsp">
		<img src="<%=request.getContextPath()%>/front/personal/images2/logo2.jpg" width="90" height="90"></a> </div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
                <li><a href="<%=request.getContextPath()%>/front/personal/index.jsp">HOME</a></li>
                <li> <a href="#slider">�������s</a></li>
                <li><a href="#map">����</a></li>
                <li>
                	<FORM METHOD="post"ACTION="<%=request.getContextPath()%>/personal/personal.do">
						<input type="submit" value="�ӤH����">
						<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
						<input type="hidden" name="login_mem_no" value="${memberVO.mem_no}">
						<input type="hidden" name="action" value="getPersonal_Display">
					</FORM>
				</li>
                <li><a href="#contactus">���ʨ�ӫ�</a></li>
                <li>${memberVO.mem_name}�A�n</li>
                <li>--${memberVO.mem_no}�A�n--</li>
                     
            </ul>
        </nav>
      </div> <!--end of header-->
</div> <!-- end of headerwrapper-->



<!--============ Slider ============-->


<div class="sliderwrapper">
      <div id="slider" class="container">
           <div class="slider">
      			<ul class="slides">
    		 	 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">Juicy Talk </h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">
							�@�Ӭ��F���j�a�W�i��y�P�ɥά���������<br>
                            �]�����ѰӮa���A�ȥ\��<br>
                            ���\�U�������W��
						</p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/logo1.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slide1img"> 
                      </li>
      			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">�W�r�Ѩ� </h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">
                        �������W�r���X�FJuicy�MTalk<br>
                        Juicy�O���۩󭹪����h�ġA�N���O�������̲�<br>
                        Talk�O���ܪ��f�y�A�ɥN��y���N�q
                        </p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/juta.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slideimg2"> 
                      </li>
     			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">�̷s����</h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">�q�L�U���̷s��APP�i�H�����|���IPHONE 6S</p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/hero.png" width="400" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slideimg2"> 
                      </li>
        		  </ul>
            </div>
      </div> <!-- End of Slider-->
</div> <!-- end of sliderwrapper-->



<!--============ Best Dishes ============-->



<div class="bestdisheswrapper">
    <div id="bestdishes" class="container">
       
		 <h2 class="wow fadeInUp" data-wow-delay="0.3s">�X�@���a</h2>
      <div class="slider">
      		    <ul class="slides">
          	 	 <li class="slide">
                    <div class="item">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/001.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>�c�체��</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item2">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/002.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>�߫}���ͪ��B��</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item3">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/003.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>�D�F�@�ػ����]</h3>
                      </div> <!-- end of item-->
                  </li>
                   <li class="slide">
                    <div class="item">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/004.jpg" width="226" height="225" alt="sliderimg" > 
                          <h3>�ߤ��ڮx</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item2">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/005.jpg" width="226" height="225" alt="sliderimg"> 
                          <h3>�t���ɩ|�D�D�\�U</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item3">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/006.jpg" width="226" height="225" alt="sliderimg"> 
                          <h3> Hello Kitty Sweets</h3>
                      </div> <!-- end of item-->
                  </li>
                   
        </ul>
      </div> <!-- end of slider-->
    </div> <!-- end of besth dishes-->
</div> <!-- end of bestdishes wrapper-->

<!--============ BOOK ONLINE ============-->

<div class="bookonlinewrapper">
    <div class="container" id="bookonline">
	     <h3 class="wow fadeInUp" data-wow-delay="0.3s">���a�s�i</h3>
       	  		<div id="adlist">
                	<table>
                    	<tr>
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad2.jpg" class="image wow zoomIn" >      
                          <h4>�ɤH���o�\���]</h4>
                          <p>�ɤH��Ӹ����ߩ�褸1972�~�A�ɤH��Ĥ@�N���ѷݷݡB�ѥ����γз~���...</p>
                          </td> <!-- end of item-->
                
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad3.jpg" class="image wow zoomIn" >      
                          <h4>�H�]�N ��N�s��</h4>
                          <p>�H�]�N��2007�~�ХߡA�߷R�馡�Ʋz�믫���п�H�A�b�x�_�u�@�Q�~ ��M...</p>
                          </td> <!-- end of item-->
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad5.jpg" class="image wow zoomIn" >      
                          <h4>Move Deluxe �V</h4>
                          <p>�iMove Deluxe�V�j�b��o�B�g��P�зN�������������A���e�{��...</p>
                          </td> <!-- end of item-->
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad6.jpg" class="image wow zoomIn" >      
                          <h4>�s�ʤ��Ȯa�\�U</h4>
                          <p>��68�~���߱��s���Q���\�U�A�Щ��t�ѡA�]�~���諸�n�}�\�U�Ӹ��ȧ�h�b�a...</p>
                          </td> <!-- end of item-->
                          
                        </tr>
       				  </table>
  				  </div>
   </div>
</div> <!-- end of book online wrapper-->



<!--============ MAP ============-->
<!--============ Contact us ============-->
<div class="contactwrapper">
  <div id="contactus" class="container">
        
    </div> 
    <!--contactus class-->
</div> <!-- end of contact wrapper-->


<!--============ FOOTER ============-->


<div class="footerwrapper">
    <footer class="container">
    	<div class="customerreview">
       		
          <div class="review">
             <h4>- �ϥγW�d</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
        		<p>Juicy Talk �Y�̷ӥ��A�ȱ��ڤ����w�A�����x�Ȫ��I���A�ȡBJuicy Talk ���a�u�f��Juicy Talk App �������A�ȡC
          		�z�����P�N���A�ȱ��ڤ����e�ç������U�{�ǡA�~�ন���uJuicy Talk�v�������|���èϥΡuJuicy Talk�v���Ѫ��U���A�ȡC
			    </p>
                <a href="">�ԲӽЫ�</a>
		  </div> <!-- end of review-->
          
          <div class="review">
          <br><br>
             <h4>- Q&A</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
        		<p>Q1.Juicy Talk �O����H<br>
				Q2.�[�J�|��������n�B�H<br>
				Q3.�[�J�|���n�I�O�ܡH 
			    </p>
                <a href="">�ԲӽЫ�</a>
		  </div> <!-- end of review-->
        </div>
		<div class="announcement">
        
        <div class="review">
             <h4>- ���i</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
       <table height="350" width="600" border="1" >
    <tr>
      <th scope="col">���i����</th>
      <th scope="col">���D</th>
      <th scope="col">�o��ɶ�</th>
    </tr>
    <tr>
      <td>���ʸ�T</td>
      <td>�i�F�ˮȹC�_���D�ѷR���S�����۶Ҽx�� �j�����ܧ�P�ɥR����</td>
      <td>2016-01-05</td>
    </tr>
    <tr>
      <td>���ʸ�T</td>
      <td>�u�P���k���������A���A��ı�B��ı�����p�A�סv - �R�Y�{�ʧK�O�٧U1,000��������</td>
      <td>2016-01-03</td>
    </tr>
    <tr>
<td>�u�f�j����</td>
      <td>�uJuicyTalk���e�ݤu�{�v�����e��¾�v-���F�y�����ʡA�}��{����5000����� </td>
      <td>2016-01-01</td>
    </tr>
    <tr>
      <td>���ʸ�T</td>
      <td>�u�������j�ɨ��^���j����v-����έ��Ů��L�۬����d���|���i���������ӷӤ@�i</td>
      <td>2016-12-01</td>
    </tr>
    <tr>
    <td id="more" colspan="3" ><a href="">more</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
</table>   
		  </div> <!-- end of review-->
        </div>
    </footer> <!-- end of footer-->

</div> <!-- end of footer-->




<!--============ COPYRIGHTS ============-->


<div class="copyrightswrapper">
    <div id="copyrights" class="container">
    
 	   <p>Copyright 2016 All Rights Reserved</p>
    
    </div> <!-- end of copyrights-->
 <!-- end of website-->
</div>


<script type="text/javascript">
    $('.sliderwrapper .slider').glide({
		autoplay: 7000,
		animationDuration: 3000,
		arrows: true,
		
		
	
		});
	
</script>
	
    <script type="text/javascript">
    $('.bestdisheswrapper .slider').glide({
		autoplay: false,
		animationDuration: 700,
		arrows: true,
		navigation:false,
		
		
	
		});
	
	
</script>
	
   
   

</body>

</html>