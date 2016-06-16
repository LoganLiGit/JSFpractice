<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <title>Minimal Flat User Account Flat Responsive Widget Template | Home :: w3layouts</title>
    <!-- Custom Theme files -->
    <link href="<%=request.getContextPath()%>/front/personal/css/Login.css" rel="stylesheet" type="text/css" media="all" />
    <script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Minimal Flat User Account Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
    <!--Google Fonts-->
    <script src="<%=request.getContextPath()%>/front/personal/jquery/Login.js"></script>
</head>

<body>
    <div class="login">
        <div class="login-top">
            <h2>�w��n�J</h2>
            <h3>LOG IN</h3>
        </div>
        <div class="login-bottom">
            <h3>�ϥΨ�L���s�b��s��:</h3>
            <ul>
                <li><a class="tw" href="#">�ϥ�Twitter�n�J</a></li>
                <li><a class="fa" href="#">�ϥ�Facebook�n�J</a></li>
                <div class="clear"> </div>
            </ul>

            <h3>�ϥΫH�c�b��/������X:</h3>
            <form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">
                <div class="user">
                    <input type="text" name="mem_account" >
                    <i></i>
                </div>
                <h3>�K�X:</h3>
                <div class="user-in">
                    <input type="password" name="mem_password" >
                    <i></i>
                </div>
                <input type="hidden" name="action" value="login_check">
                <input type="submit" value="�n�J">
            </form>
            <div class="keepme">
                <label class="checkbox">
                    <input type="checkbox" name="checkbox" checked><i> </i> �O��ڪ��b��</label>
                <div class="keep-loginbutton">
                   
                </div>
                <div class="clear"> </div>
            </div>
            <div class="forgot">
                <p id="forgot_password"><a href="#">�ѰO�K�X?</a></p>
                <div class="forgot-register">
                    <p>�٨S�[�JJuicyTalk����C��?? <a href="http://127.0.0.1:54324/%E8%A8%BB%E5%86%8A/%E8%A8%BB%E5%86%8A.html">�I�o�̵��U</a></p>
                </div>
                <div class="clear"> </div>
            </div>
        </div>
        <div class="div_forgot_password">
            <form>

                <h3>�п�J�H�c:</h3>
                <div class="e-mail">
                    <input type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'E-mail';}">
                    <i></i>
                </div>
            </form>

            <div class="findPassword">
                <form>
                    <input type="submit" value="�e�X">
                </form>

            </div>
        </div>
    </div>


</body>

</html>