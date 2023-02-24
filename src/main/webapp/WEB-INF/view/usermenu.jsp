<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="/SoundMaking/css?name=menu.css">
<title>おとづくり - ユーザーメニュー -</title>
</head>
<body>
<header>
    <h1><a href="">ユーザーメニュー</a></h1>
    <nav>
        <ul>
            <li><a href="/SoundMaking/menu" id="menu">メニュー</a></li>
            <li><a href="/SoundMaking/logout" id="logout">LOGOUT</a></li>
        </ul>
    </nav>
</header>
<p><a href="menu/changepassword" id="changepassword_link"><div class="midashi"><img src="/SoundMaking/img?name=hana.png">パスワード変更<img src="/SoundMaking/img?name=hana.png"></div></a></p>
<% String groupname = (String)request.getAttribute("groupname"); %>
<% if (groupname.equals("admin")) { %>
<p><a href="menu/manager" id="usermanager_link"><div class="midashi"><img src="/SoundMaking/img?name=hana.png">ユーザー管理<img src="/SoundMaking/img?name=hana.png"></div></a></p>
<% } %>
</body>
</html>
