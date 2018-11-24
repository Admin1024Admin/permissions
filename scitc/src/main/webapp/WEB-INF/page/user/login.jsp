<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>360智慧幼儿园-后台登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/font.css">
	<link rel="stylesheet" href="/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>

</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">360智慧幼儿园</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="userName" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="userPwd" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr15">
            <a href="${pageContext.request.contextPath}/user/loginPhonePage.action" style="color: #189F92">手机短信登陆</a>
            <hr class="hr20" >
        </form>
    </div>

    <script>
        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              form.on('submit(login)', function(data){

                $.ajax({
                    type:"post",
                    url:"${pageContext.request.contextPath}/user/user_login.action",
                    data:{userName:data.field.userName,userPwd:data.field.userPwd},
                    dataType:"json",
                    success:function(serverData){
                        layer.msg(serverData.msg,function () {
                            if(serverData.code == 200){
                                window.location.href="${pageContext.request.contextPath}/main_index.action";
                            }
                        });
                    }
                });
                return false;
              });
            });
        })
    </script>
    <!-- 底部结束 -->

</body>
</html>