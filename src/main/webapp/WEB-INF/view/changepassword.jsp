<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="../../css?name=usermanager.css">
<link rel="stylesheet" href="../../css?name=button.css">
<title>おとづくり - パスワード変更 -</title>
</head>
<body>
<header>
    <h1><a href="">パスワード変更</a></h1>
    <nav>
        <ul>
            <li><a href="/SoundMaking/menu" id="menu">メニュー</a></li>
            <li><a href="/SoundMaking/user/menu" id="usermenu">ユーザーメニュー</a></li>
            <li><a href="/SoundMaking/logout" id="logout" >LOGOUT</a></li>
        </ul>
    </nav>
</header>
<p>パスワードは20文字までです。</p>
<p>
<form action="" method="post">
<table>
<tr><td>新パスワード</td><td><input type="password" name ="newpassword1" id="password1"></td></tr>
<tr><td>新パスワード(確認)</td><td><input type="password" name ="newpassword2" id="password2"></td></tr>
</table>
<p><input type="submit" value="変更する" class="btn--orange" id="change_button"></p>
</form>
</p>
<% String msg = (String)request.getAttribute("msg"); %>
<% if (msg != null) { %>
<p id="msg"><%=msg %></p>
<% } %>
<% String err = (String)request.getAttribute("err"); %>
<% if (err != null) { %>
<p id="errmsg"><span style="color:red;"><%=err %></span></p>
<% } %>
</body>
</html>