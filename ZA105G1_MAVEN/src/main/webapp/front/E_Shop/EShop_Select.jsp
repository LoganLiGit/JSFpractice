<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.TicketType.model.*"%>
<%@page import="com.store.model.StoreVO"%>
<%--import	 --%>
<%
	List<TicketTypeVO> shoplist = new TicketTypeService().getShopAll();
	pageContext.setAttribute("shoplist",shoplist);
%>
<%--	取出資料庫所有上架的商品	 --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商城輪播廣告OK</title>

</head>
<body>
<article name="article1" class="container_12">
	<div class="grid_11" id="div01">
		<div class="rollBox">
			<a href="javascript:;" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()" onmouseout="ISL_StopDown()" class="img1" hidefocus="true">
			</a>
			<div class="Cont" id="ISL_Cont">
		    	<div class="ScrCont">
		    		<div id="List1">
		    		<c:forEach var="tickettypevo" items="${shoplist}">
		    			<div class="pic">
					    	<a href="#" target="_blank">
					    		<img width="140" height="105" class="post-image" src=" <%= request.getContextPath() %>/DBGifReader11.do?name=${ tickettypevo.tickets_type_no }"/>
					    	</a>
					    	<a href="#" target="_blank">${ tickettypevo.tickets_type_name }</a>
		    			</div>
		    		</c:forEach>
		    		</div>    		
		    	</div>
		    </div>
		</div>
	</div>
</article>

<style type="text/css">
.rollBox img{border:none;}
.rollBox{width: 925px;overflow:hidden;}
.rollBox .Cont{width:900px;}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{width:130px;float:left;text-align:center;padding-right:20px;}
*+html .rollBox .Cont .pic{width:130px;float:left;text-align:center;padding-right:30px;}
.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
.rollBox .Cont .pic p{line-height:26px;color:#505050;}
.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
.rollBox #List1,.rollBox #List2{float:left;}
.img1,.img2,.Cont{
float:left;}
.img1,.img2{
width:25px;
height:105px;
display:block;
cursor:pointer;
margin-top:20px;
}
.img1{
	background-image:url(http://www.51xuediannao.com/uploads/soft/Slide/gundongzhanshi/shqm_left_pic.gif);
	background-repeat: no-repeat;
	background-position: center center;}
.img2{
	background-image:url(http://www.51xuediannao.com/uploads/soft/Slide/gundongzhanshi/shqm_right_pic.gif);
	background-repeat: no-repeat;
	background-position: center center;
}
 </style>
<script language="javascript" type="text/javascript">
<!--//--><![CDATA[//><!--
//图片滚动列表 mengjia 070816
var Speed = 10; //速度(毫秒)
var Space = 10; //每次移动(px)
var PageWidth = 160; //翻页宽度
var fill = 0; //整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;
GetObj("List2").innerHTML = GetObj("List1").innerHTML;
GetObj('ISL_Cont').scrollLeft = fill;
GetObj("ISL_Cont").onmouseover = function(){clearInterval(AutoPlayObj);}
GetObj("ISL_Cont").onmouseout = function(){AutoPlay();}
AutoPlay();
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval

('document.all.'+objName)}}
function AutoPlay(){ //自动滚动
clearInterval(AutoPlayObj);
AutoPlayObj = setInterval('ISL_GoDown();ISL_StopDown();',3000); //间隔时间
}
function ISL_GoUp(){ //上翻开始
if(MoveLock) return;
clearInterval(AutoPlayObj);
MoveLock = true;
MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
}
function ISL_StopUp(){ //上翻停止
clearInterval(MoveTimeObj);
if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0){
Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
CompScr();
}else{
MoveLock = false;
}
AutoPlay();
}
function ISL_ScrUp(){ //上翻动作
if(GetObj('ISL_Cont').scrollLeft <= 0){GetObj('ISL_Cont').scrollLeft = GetObj

('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth}
GetObj('ISL_Cont').scrollLeft -= Space ;
}
function ISL_GoDown(){ //下翻
clearInterval(MoveTimeObj);
if(MoveLock) return;
clearInterval(AutoPlayObj);
MoveLock = true;
ISL_ScrDown();
MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
}
function ISL_StopDown(){ //下翻停止
clearInterval(MoveTimeObj);
if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0 ){
Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
CompScr();
}else{
MoveLock = false;
}
AutoPlay();
}
function ISL_ScrDown(){ //下翻动作
if(GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth){GetObj('ISL_Cont').scrollLeft =

GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;}
GetObj('ISL_Cont').scrollLeft += Space ;
}
function CompScr(){
var num;
if(Comp == 0){MoveLock = false;return;}
if(Comp < 0){ //上翻
if(Comp < -Space){
   Comp += Space;
   num = Space;
}else{
   num = -Comp;
   Comp = 0;
}
GetObj('ISL_Cont').scrollLeft -= num;
setTimeout('CompScr()',Speed);
}else{ //下翻
if(Comp > Space){
   Comp -= Space;
   num = Space;
}else{
   num = Comp;
   Comp = 0;
}
GetObj('ISL_Cont').scrollLeft += num;
setTimeout('CompScr()',Speed);
}
}
//--><!]]>
</script>
</body>
</html>