<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>修改用户</title>
   <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
   <style>
   span{
     color:red;
   }
   </style>
    <style>
        .loginForm {
            /*边框高度*/
            height: 650px;
            /*边框宽度*/
            width: 500px;
            /*边框颜色*/
            border: #4d4d4d solid 1px;
            /*边框圆角*/
            border-radius: 4px;
            /*阴影 水平方向，竖直方向，模糊距离*/
            box-shadow: 5px 5px 5px #4d4d4d;
            /*上边界距离*/
            margin-top: 20px;
            /*左边界距离：自动*/
            margin-left: auto;
            /*右边界距离：自动*/
            margin-right: auto;
            /*用户名、密码间距*/
            padding: 20px 40px;
        }

        /*将用户登录置于中间*/
        .loginForm h2 {
            text-align: center;
        }

        /*修改button属性*/
        .button {
            text-align: center;
            vertical-align: middle;
        }
    </style>
    <script type="text/javascript">
        function checkName()
        {
           var spanText = document.getElementById("checkname");
            spanText.innerHTML = "";
            var name = document.getElementById("name").value;
            if( name == null || name == '')
            {
               var spanText = document.getElementById("checkname");
               spanText.innerHTML = "用户名不能为空";
               document.getElementById('name').focus();
            }

            if(name.length <3 || name.length > 12)
            {
                 var spanText = document.getElementById("checkname");
                 spanText.innerHTML = "用户名长度不合法";
                 document.getElementById('name').focus();
            }

            var  reg=/^[a-zA-Z_0-9]+$/;
             if(!reg.test(name))
             {
                   var spanText = document.getElementById("checkname");
                   spanText.innerHTML = "用户名不合法";
                   document.getElementById('name').focus();
             }
        }
        function checkPass()
         {
             var spanText = document.getElementById("checkpwd");
             spanText.innerHTML = "";
             var pwd = document.getElementById("password").value;
             if( pwd == null || pwd == '')
               {
                     var spanText = document.getElementById("checkpwd");
                     spanText.innerHTML = "密码不能为空";
                     document.getElementById('password').focus();
               }
             if(pwd.length <3 || pwd.length > 12)
               {
                    var spanText = document.getElementById("checkpwd");
                    spanText.innerHTML = "密码长度不合法";
                    document.getElementById('password').focus();
               }
              var  reg=/^[a-zA-Z_0-9]+$/;
              if(!reg.test(pwd))
                {
                   var spanText = document.getElementById("checkpwd");
                   spanText.innerHTML = "密码不合法";
                   document.getElementById('password').focus();
               }
          }
         function checkPass2()
         {
           var spanText = document.getElementById("checkpwd2");
             spanText.innerHTML = "";
            var pwd = document.getElementById("password").value;
            var pwd2 = document.getElementById("password2").value;
            if(pwd != pwd2)
            {
               var spanText = document.getElementById("checkpwd2");
               spanText.innerHTML = "密码不一致";
               document.getElementById('password2').focus();
            }

         }

         function checkEmail()
         {
              var spanText = document.getElementById("checkEmail");
              spanText.innerHTML = "";
              var email = document.getElementById("email").value;
              if( email == null || email == '')
                 {
                    var spanText = document.getElementById("checkEmail");
                    spanText.innerHTML = "邮箱不能为空";
                    document.getElementById('email').focus();
                }
               let reg = /^[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$/
              if(!reg.test(email))
                 {
                     var spanText = document.getElementById("checkEmail");
                     spanText.innerHTML = "邮箱不合法";
                    document.getElementById('email').focus();
                }

         }
        function ajax(){
            var ajax = new XMLHttpRequest();
            var name = document.getElementById("name").value;
            var url = "checkNameApi";
            ajax.open("post",url,true);
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            ajax.onreadystatechange = function(){
                if(ajax.readyState == 4 && ajax.status == 200){
                    var text = ajax.responseText;
                    var spanText = document.getElementById("checkname");
                    spanText.innerHTML = text;
                }
            }
            ajax.send("name=" + name);
        }
        function getUser()
        {
            var ajax = new XMLHttpRequest();
            var id = <%=request.getParameter("id")%>;
            var url = "getUserApi";
            ajax.open("post",url,true);
            ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
            ajax.onreadystatechange = function(){
                if(ajax.readyState == 4 && ajax.status == 200){
                    var resobj = ajax.responseText;
                     console.log(resobj);
                     var obj = JSON.parse(resobj);
                     document.getElementById("id").value=obj.id;
                     document.getElementById("name").value=obj.name;
                     document.getElementById("password").value=obj.password;
                     document.getElementById("password2").value=obj.password;
                     if('男' == obj.gender)
                     {
                         document.getElementById("gender1").checked="checked";
                     }else
                     {
                         document.getElementById("gender2").checked="checked";
                     }
                     document.getElementById("email").value=obj.email;
                     document.getElementById("birthday").value=obj.birthday;
                }
            }
            ajax.send("id=" + id);
        }

    </script>
</head>
<body onload="getUser();">
<div class="loginForm">
    <h2>用户修改</h2>
    <form action="${pageContext.request.contextPath}/userApi?method=modUser" method="post">
        <div class="form-group">
            <label for="exampleInputEmail1">用户名</label>
            <input type="hidden" name="id" id="id">
            <input type="text" class="form-control" name="name" id="name" onblur="checkName();ajax();" placeholder="请输入用户名"><span id="checkname"></span>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">密码</label>
            <input type="password" class="form-control" name="password" id="password" onblur="checkPass();"  placeholder="请输入密码"><span id="checkpwd"></span>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">确认密码</label>
            <input type="password" class="form-control" name="password2"  id="password2"  onblur="checkPass2();"  placeholder="请输入确认密码"><span id="checkpwd2"></span>
        </div>
         <div class="form-group">
            <label for="exampleInputPassword1">性别</label>
              &nbsp;&nbsp;&nbsp;&nbsp;男&nbsp;&nbsp;<input type="radio" name="gender" id="gender1" value="男" />&nbsp;&nbsp;&nbsp;&nbsp;女&nbsp;&nbsp;<input type="radio" name="gender" id="gender2"  value="女" />
        </div>
         <div class="form-group">
            <label for="exampleInputPassword1">生日</label>
           <input name="birthday" class="form-control" type="text" id="birthday" size="20" >
        </div>
         <div class="form-group">
            <label for="exampleInputPassword1">邮箱</label>
           <input name="email" class="form-control" type="text" id="email" onblur="checkEmail();" size="20" ><span id="checkEmail"></span>
        </div>
        <div class="button">
            <input type="submit" class="btn btn-primary" value="修 改"/>
        </div>
    </form>
</div>

</body>
</html>
