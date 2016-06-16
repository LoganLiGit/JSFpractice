<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.TicketOr.model.*"%>
<%
	String url_eshop = request.getContextPath() + "/front/E_Shop/EShop.jsp";
	if (request.getAttribute("membervo") != null && request.getAttribute("money") != null){
		MemberVO membervo =null;
		Integer money=null;
		List errorMsgs = null;
		Integer moneyup =null;
		membervo = (MemberVO)request.getAttribute("membervo");
		money = (Integer)request.getAttribute("money");
		moneyup = (Integer)request.getAttribute("moneyup");
		errorMsgs =(List)request.getAttribute("errorMsgs");
		
		pageContext.setAttribute("errorMsgs", errorMsgs);
		pageContext.setAttribute("membervo", membervo);
		pageContext.setAttribute("money", money);
		pageContext.setAttribute("moneyup", moneyup);
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>團購金儲值失敗</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input03.css">
</head>
<body>
		<!--============ header ============-->
		<div id="header" style="width: 100%; height: 225px;">
			<iFrame style="width: 100%; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
		<h2 class="align_c font_normal blue">團購金失敗</h2>
			<p>
			儲值金總量不得超過 ${moneyup} 元，此次儲值失敗，敬請見諒
			</p>
				<ul class="apLogin">
					<li>
						<label>會員類別：</label>
						<div class="col"><p class="text_normal margin_l10">個人會員</p></div>
					</li>
					<li>
						<label>會員帳號名/帳：</label>
						<div class="col">
						<p class="text_normal margin_l10"> ${membervo.mem_account}  / ${membervo.mem_no}</p>
						</div>
                    </li>
					<li>
						<label>本次儲值金額：</label>
						<div class="col">
	                   	<p class="text_normal margin_l10">${money}</p>
	                    </div>
                    </li>
					<li>
						<label>目前餘額：</label>
						<div class="col">
	                   	<p class="text_normal margin_l10">${membervo.mem_balance}</p>
	                    </div>
                    </li>
                </ul>
				<div class="next_btn">
	            	<a class="btn blue_button btn_Submit" href="<%=url_eshop %>" >返回商城</a>
	            </div>
     </div>
     <div class="message margin_t30">
            <h4>注意事項：</h4>
            <ol>
            	<li></li>
            	<li>安全提示問題與答案用於資料修改及密碼重設，請務必妥善保管。</li>
            </ol>
      </div>	
</div>

</body>
</html>