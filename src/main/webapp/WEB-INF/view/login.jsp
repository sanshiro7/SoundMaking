<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <link rel="stylesheet" href="/SoundMaking/css?name=login.css">
        <title>おとづくり - ログイン - </title>
    </head>
    <body>
        <form action="" name="login_form" method="post">
            <div class="login_form_top">
                <h1>おとづくり</h1>
                <h2>LOGIN</h2>
                <p>UserID、Passwordをご入力の上、「LOGIN」ボタンをクリックしてください。</p>
                <% String errmsg = (String)request.getAttribute("errmsg");%>
                <% if ( errmsg != null ) {%>
                <p id="errmsg"><%=request.getAttribute("errmsg") %></p>
                <% } %>
            </div>
            <div class="login_form_btm">
                <input type="id" name="username" placeholder="UserID" id="username">
                <input type="password" name="password" placeholder="Password" id="password">
                <input type="submit" name="botton" value="LOGIN" id="login_button">
            </div>
        </form>
    </body>
</html>
