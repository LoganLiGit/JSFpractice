<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--註冊JSTL	 --%>
<%@page import="java.util.*" %>
<%@page import="com.member.model.*"%>
<%@page import="com.TicketOr.model.*"%>
<%
	String url_eorder_do = request.getContextPath() + "/front/E_My_Order/Eorder_Servlet.do"; //儲值專用的控制器
	List<TicketOrVO> list = null;
	String mem_account = (String)session.getAttribute("mem_account");
	if (mem_account == null ){		
	}else{
		Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no();
		Integer now_money_intheadd = new MemberService().getOneAccount(mem_account).getMem_balance();
		//Integer mem_no = new MemberService().getOneAccount(mem_account).getMem_no(); 
		//利用MemberService的這個getOneAccount方法取得裡面的getMem_no ()  (會員編號)
		list = new TicketOrService().getUserdate(mem_no);
		//List<TicketOrVO> list = new TicketOrService().getUserdate(mem_no);
		//利用TicketOrService的這個getUserdate(mem_no);方法取得裡面的list  (此會員編號的所有訂單資訊)
		pageContext.setAttribute("now_money_intheadd", now_money_intheadd);
		pageContext.setAttribute("list", list);
		pageContext.setAttribute("mem_no", mem_no);
	}
		Date date = new Date();
		int year = date.getYear() + 1900;
%>
<%-- 放進當前頁面 任意取用 --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>儲值團購金</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/ador/lib/layer/1.9.3/layer.js"></script> 
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/myorderform_list02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input01.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input02.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/back/css/myorderform/input03.css">

</head>
<body>
		<!--============ header ============-->
		<div id="header" style="width: 1920px; height: 225px;">
			<iFrame style="width: 1920px; height: 225px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/Header.jsp" scrolling="no"> </iFrame>
		</div>
		<!--============ header  ============-->
<FORM METHOD="post" ACTION="<%=url_eorder_do%>" id="inputtrform" >
<div class="container regBox" id="inputtr">
	<div class="company_inner div_center" style="width: 700px">
		<h2 class="align_c font_normal blue">儲值團購金收費選擇</h2>
			<p>請依序填寫下方內容，以完善您的付款資料！</p>
			<ul class="apLogin">
					<li>
						<label>會員類別：</label>
						<div class="col"><p class="text_normal margin_l10">個人會員</p></div>
					</li>
					<li>
						<label>會員帳號名/帳：</label>
						<div class="col">
	                   	<p class="text_normal margin_l10">${mem_account}  (${mem_no})</p>
	                    </div>
                    </li>
					<li>
						<label>目前餘額：</label>
						<div class="col">
	                   	<p class="text_normal margin_l10">${now_money_intheadd}</p>
	                    </div>
                    </li>
                    <li>
						<label>付費方式：</label>
						<div class="col">
							<select tabindex="1">
								<option value="2016">信用卡</option>
							</select>
						</div>
                    </li>
                    <li>
						<label>本次購買金額:</label>
						<div class="col">
							<select tabindex="1" name="money" id="money">
							<%
								for (int i=100;i<=10000;i=i+100){
							%>
								<option value="<%=i%>"><%=i %></option>
							<%}%>	
							</select>
						</div>
                    </li>
                    <li>
						<label>卡號:</label>
						<div class="col">
							<div>
								<input MaxLength="4" type="text" width="1"/><span class="dash">-</span>
								<input MaxLength="4" type="text" width="1"/><span class="dash">-</span>
								<input MaxLength="4" type="text" width="1"/><span class="dash">-</span>
								<input MaxLength="4" type="text" width="1"/>
							</div>
						</div>
                    </li>
                    <li>
						<label>信用卡有效月／年：</label>
						<div class="col">
							<select tabindex="1">
							<%
								for (int i=1;i<=12;i++){
							%>
								<option value="<%=i%>"><%=i %></option>
							<%}%>	
							</select>
							<span class="dash">/</span>
							<select tabindex="1">
							<%
								for (int j=year;j<year+15;j++){
							%>
								<option value="<%=j%>"><%=j %></option>
							<%}%>	
							</select>


						</div>
                    </li>
                    <li>
						<label>信用卡姓名:</label>
						<div class="col">
							<input class="btn btn-warning mr10 width150"  />
						</div>
                    </li>
                    <li>
						<label>CVV/CID(背後末三碼):</label>
						<div class="col">
							<input class="btn btn-warning mr10 width150"  />
						</div>
                    </li>
			</ul>
			<div class="next_btn">
            	<a class="btn blue_button btn_Submit" inputtr="inputtr" >送出資料</a>
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
<input type="hidden" name="mem_no" value="${mem_no}">
<input type="hidden" name="action" value="my_add_trandaction">
</FORM>

<%-- sweet 跳窗確認--%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/jscss/sweet/sweet-alert.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/jscss/sweet/sweet-alert.min.js"></script> 
<script type="text/javascript">
	$(function(){
		$("#inputtr").find("a").click(function(){
			if($(this).attr("inputtr")=='inputtr'){
				var money = $("#money").val();
				swal({
					title: "購買資料確定送出",
					text: "本次儲值金額為:" + money + "元 ",
					type: "warning",
					showCancelButton: true,
					confirmButtonColor: "#DD6B55",
					confirmButtonText: "確定輸入無誤!送出",
					closeOnConfirm: false
				},
				function(){
					swal("送出中! 請稍候", "送出中");
					$("#inputtrform").submit();
				})
			}
		})
	});
</script> 
<!--============ footer ============-->
<div id="footer" style="width: 100%; height: 455px;">
	<iFrame style="width:100%; height: 455px;" src="<%=request.getContextPath()%>/front/member/JuicyTalk/footer.jsp" scrolling="no"> </iFrame>
</div>
<!--============ footer  ============-->
</body>
</html>