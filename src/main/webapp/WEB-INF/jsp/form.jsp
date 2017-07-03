<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form</title>
</head>
<body>
   <form id="loginForm" action="${pageContext.request.contextPath }/test/upload2"  method="post" enctype="multipart/form-data" >
            <legend>手机号码登录</legend>
            <fieldset>
                <div class="msg" id="message"></div>
                <div>
                    <label for="mobile">手机号：</label>
                    <input id="mobile" name="mobile" type="text">
                </div>
                <div>
                    <label for="password">密　码：</label>
                    <input id="password" type="password" name="password">
                </div>
                <div>
                    <textarea id='txt'></textarea>
                </div>
                <div>
	               	<label for="a">a</label></lab><input type="checkbox" name="checkbox" value="a"  />
	                <label for="b">b</label><input type="checkbox" name="checkbox" value="b"/>
	                <label for="c">c</label><input type="checkbox" name="checkbox" value="c"/>
                </div>
                <div>
	                <label for="x">x</label></lab><input type="radio" name="radio" value="x" />
	                <label for="y">y</label><input type="radio" name="radio" value="y"/>
	                <label for="z">z</label><input type="radio" name="radio" value="z"/>
                </div>
                <div>
                	<input name="file" type="file"/>
                </div>
                <div><button id="login" type="submit">提交</button></div>
            </fieldset>
        </form>
</body>
</html>