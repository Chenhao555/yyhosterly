<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include  file="/WEB-INF/jspf/basePath.jspf" %>
<!DOCTYPE html>
<HTML  xmlns="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8" />
        <title>例子</title>
        <link type="text/css"  data-th-href="@{/static/css/index.css}" rel="stylesheet"  />
    </head>
    <body>
        <div class="login_wall">
            <div class="login_main">
                <div class="login_dl fr">
                    <form >
                        <span>登录<a class="fr" data-th-href="@{/register}">注册</a></span>
                        <p><em>账　号：</em><input type="text" name="username"></p>
                        <p><em>密　码：</em><input type="text" name="password"></p>
                        <p><em>验证码：</em><input type="text" name="code" style="width:134px;  height:27px;">
                        <img style="margin-left:5px" id="Img" onclick="changeCode();"></p>
                        <input type="button" value="确定" id="login_btn"/>
                    </form>
                </div>
                <div class="clear"></div>
            </div>

        </div>
    </body>
    <script type="text/javascript" src="${BASEPATH}/static/js/jquery-1.12.4.js" ></script>
    <script type="text/javascript" data-th-inline="javascript">
    	$(function(){
    		$("#Img").attr("src", "/yyhostelry/ran/getVerifyCode?t=" + genTimestamp());
    	})
        /*<![CDATA[*/

        // 获取当前时间
        function genTimestamp() {
            var time = new Date();
            return time.getTime();
        }

        // 点击获取验证码
        function changeCode() {
            $("#Img").attr("src", "/yyhostelry/ran/getVerifyCode?t=" + genTimestamp());
        }

        // 点击登录
        $("#login_btn").click(function(){
            var username = $("input[name='username']").val();
            var password = $("input[name='password']").val();
            var code = $("input[name='code']").val();
            if(username != "" && password != ""){
                var data = {"username":username, "password": password};
                // console.log(data); return false;
                $.post("/ran/getVerifyCode?code="+code, data, function(res){
                    // console.log(res); return false;
                    if(res.code == 100){
                        alert(res.extend.msg);
                        location.href = "/index";
                    }
                })
            }else{
                alert("用户名和密码未填写！");
            }
        })
        /*]]>*/
    </script>
</html>