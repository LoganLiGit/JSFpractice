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
<a href="<%=request.getContextPath()%>/front/personal/login.jsp"><button width="20" height="20" >登入會員</button> </a> 
<a href="../註冊/註冊.html"><button width="20" height="20" >註冊會員</button> </a> 
<a href="<%=request.getContextPath()%>/front/personal/logout.jsp"><button width="20" height="20" >登出會員</button> </a>
</div>
</div>
<div class="headerwrapper">

	<div id="header" class="container">
   
		<div class="logo"> <a href="<%=request.getContextPath()%>/front/personal/index.jsp">
		<img src="<%=request.getContextPath()%>/front/personal/images2/logo2.jpg" width="90" height="90"></a> </div> <!--end of Logo-->
        <nav>
            <ul id="navigations">
                <li><a href="<%=request.getContextPath()%>/front/personal/index.jsp">HOME</a></li>
                <li> <a href="#slider">美食社群</a></li>
                <li><a href="#map">揪團</a></li>
                <li>
                	<FORM METHOD="post"ACTION="<%=request.getContextPath()%>/personal/personal.do">
						<input type="submit" value="個人頁面">
						<input type="hidden" name="mem_no" value="${memberVO.mem_no}">
						<input type="hidden" name="login_mem_no" value="${memberVO.mem_no}">
						<input type="hidden" name="action" value="getPersonal_Display">
					</FORM>
				</li>
                <li><a href="#contactus">團購券商城</a></li>
                <li>${memberVO.mem_name}你好</li>
                <li>--${memberVO.mem_no}你好--</li>
                     
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
							一個為了讓大家增進交流與享用美食的網站<br>
                            也有提供商家的服務功能<br>
                            讓餐廳提高知名度
						</p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/logo1.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slide1img"> 
                      </li>
      			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">名字由來 </h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">
                        網站的名字結合了Juicy和Talk<br>
                        Juicy是取自於食物的多汁，代表的是食物的甘甜<br>
                        Talk是說話的口語，借代交流的意義
                        </p>
                      <img src="<%=request.getContextPath()%>/front/personal/images2/juta.png" width="317" height="256" class="wow fadeInRight" 
                      data-wow-delay="0.8s" alt="slideimg2"> 
                      </li>
     			 	  <li class="slide">
                      	<h5 class="wow fadeInDown" data-wow-delay="0.8s">最新活動</h5>
                      	<p class="wow fadeInUp" data-wow-delay="0.8s">通過下載最新的APP可以有機會抽到IPHONE 6S</p>
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
       
		 <h2 class="wow fadeInUp" data-wow-delay="0.3s">合作店家</h2>
      <div class="slider">
      		    <ul class="slides">
          	 	 <li class="slide">
                    <div class="item">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/001.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>宮原眼科</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item2">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/002.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>貓咪先生的朋友</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item3">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/003.jpg" width="226" height="225" alt="sliderimg" class="wow flipInX"
                           data-wow-delay=".8s"> 
                          <h3>胖達咖啡輕食館</h3>
                      </div> <!-- end of item-->
                  </li>
                   <li class="slide">
                    <div class="item">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/004.jpg" width="226" height="225" alt="sliderimg" > 
                          <h3>心之芳庭</h3>
                      </div> <!-- end of item-->
                      
                   <div class="item2">
                          <img src="<%=request.getContextPath()%>/front/personal/images2/005.jpg" width="226" height="225" alt="sliderimg"> 
                          <h3>川布時尚主題餐廳</h3>
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
	     <h3 class="wow fadeInUp" data-wow-delay="0.3s">店家廣告</h3>
       	  		<div id="adlist">
                	<table>
                    	<tr>
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad2.jpg" class="image wow zoomIn" >      
                          <h4>怡人園精緻餐敘館</h4>
                          <p>怡人園商號成立於西元1972年，怡人園第一代曾老爺爺、老奶奶用創業基金...</p>
                          </td> <!-- end of item-->
                
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad3.jpg" class="image wow zoomIn" >      
                          <h4>鯨吞燒 串燒酒場</h4>
                          <p>鯨吞燒於2007年創立，喜愛日式料理精神的創辦人，在台北工作十年 後決...</p>
                          </td> <!-- end of item-->
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad5.jpg" class="image wow zoomIn" >      
                          <h4>Move Deluxe 燄</h4>
                          <p>【Move Deluxe燄】在精緻、經典與創意之間完美拿捏，屢屢呈現豐...</p>
                          </td> <!-- end of item-->
                          <td>
                          <input type="image" src="<%=request.getContextPath()%>/front/personal/images2/ad6.jpg" class="image wow zoomIn" >      
                          <h4>新百王客家餐廳</h4>
                          <p>於68年成立惟新海霸王餐廳，創店緣由，因外公爵的要開餐廳來腹務更多在地...</p>
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
             <h4>- 使用規範</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
        		<p>Juicy Talk 係依照本服務條款之約定，提供儲值金兌換服務、Juicy Talk 店家優惠及Juicy Talk App 等相關服務。
          		您必須同意本服務條款之內容並完成註冊程序，才能成為「Juicy Talk」的正式會員並使用「Juicy Talk」提供的各項服務。
			    </p>
                <a href="">詳細請按</a>
		  </div> <!-- end of review-->
          
          <div class="review">
          <br><br>
             <h4>- Q&A</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
        		<p>Q1.Juicy Talk 是什麼？<br>
				Q2.加入會員有什麼好處？<br>
				Q3.加入會員要付費嗎？ 
			    </p>
                <a href="">詳細請按</a>
		  </div> <!-- end of review-->
        </div>
		<div class="announcement">
        
        <div class="review">
             <h4>- 公告</h4>
          </div> <!-- end of review-->
          <div class="clearfix"></div>
          <div class="line"></div>
          <div class="review">
       <table height="350" width="600" border="1" >
    <tr>
      <th scope="col">公告類型</th>
      <th scope="col">標題</th>
      <th scope="col">發表時間</th>
    </tr>
    <tr>
      <td>活動資訊</td>
      <td>【東森旅遊北海道×愛評特派員招募徵選 】活動變更與補充說明</td>
      <td>2016-01-05</td>
    </tr>
    <tr>
      <td>活動資訊</td>
      <td>「與型男店員有約，給你視覺、味覺滿滿小鮮肉」 - 愛吃閃購免費贊助1,000元見面金</td>
      <td>2016-01-03</td>
    </tr>
    <tr>
<td>優惠大活動</td>
      <td>「JuicyTalk的前端工程師元旦前離職」-為了慶祝活動，開放現金券5000元抽獎 </td>
      <td>2016-01-01</td>
    </tr>
    <tr>
      <td>活動資訊</td>
      <td>「路型鳥大賽車回味大車拚」-能夠用飛空挺過幻界關卡的會員可換取站長帥照一張</td>
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