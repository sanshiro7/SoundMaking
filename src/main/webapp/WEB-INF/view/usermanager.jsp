<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.subro.soundmaking.db.Loginuser" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="/SoundMaking/css?name=usermanager.css">
<link rel="stylesheet" href="/SoundMaking/css?name=button.css">
<title>おとづくり - ユーザー管理 -</title>
</head>
<body>
<header>
    <h1><a href="">ユーザー管理</a></h1>
    <nav>
        <ul>
            <li><a href="/SoundMaking/menu" id="menu">メニュー</a></li>
            <li><a href="/SoundMaking/user/menu" id="usermenu">ユーザーメニュー</a></li>
            <li><a href="/SoundMaking/logout" id="logout">LOGOUT</a></li>
        </ul>
    </nav>
</header>
<br>
<table id="userlist">
<tr><th>ID</th><th>ユーザー名</th><th>所属グループ</th><th></th><th></th></tr>
<% List<Loginuser> loginusers = (List<Loginuser>)request.getAttribute("loginusers"); %>
<% String action              = (String)request.getAttribute("action"); %>
<% String requiredId          = (String)request.getAttribute("id"); %>
<% int counter = 1;%>
<% for(Loginuser loginuser: loginusers) { %>
<%		String id        = String.valueOf(loginuser.getId()); %>
<%		String username  = loginuser.getUsername(); %>
<%		String groupname = loginuser.getGroupname(); %>
<%     	if ( id.equals(requiredId) && action.equals("update") ) { %>
            <tr><form action="" method="post">
                <td id="id<%=counter %>"><%=id %></td>
                <td id="username<%=counter %>"><%=username %></td>
                <td><input type="text" name="groupname" value="<%=groupname %>" id="groupname<%=counter %>"></td>
                <td><input type="submit" value="決定" class="btn--orange" id="confirm_button<%=counter %>"><input type="hidden" name="action" value="confirm"><input type="hidden" name="id" value="<%=id %>"></form></td>
            </tr>
<%		} else { %>
            <tr>
                <td id="id<%=counter %>"><%=id %></td>
                <td id="username<%=counter %>"><%=username %></td>
                <td id="groupname<%=counter %>"><%=groupname %></td>
                <td><form action="" method="post"><input type="submit" value="編集" class="btn--orange" id="update_button<%=counter %>"><input type="hidden" name="action" value="update"><input type="hidden" name="id" value="<%=id %>"></form></td>
                <td><form action="" method="post"><input type="submit" value="削除" class="btn--orange" id="delete_button<%=counter %>"><input type="hidden" name="action" value="delete"><input type="hidden" name="id" value="<%=id %>"></form></td>
            </tr>
<% 		} %>
<%     counter++; %>
<% } %>
</table>
<p>
<strong><img src="/SoundMaking/img?name=hana2.png"><big>新規ユーザー登録</big><img src="/SoundMaking/img?name=hana2.png"></strong><br>
<small>(IDは自動で付与されます)</small>
<form action="" method="post">
<table>
    <tr><th>ユーザー名</th><th>グループ名</th><th>パスワード</th><th></th></tr>
    <tr>
        <td><input type="text" name="username" id="new_username"></td>
        <td><input type="text" name="groupname" id="new_groupname"></td>
        <td><input type="text" name="password" id="new_password"></td>
        <td><input type="submit" value="新規登録" class="btn--orange" id="insert_button"></td>
    </tr>
</table>
<input type="hidden" name="action" value="insert">
</form>
</p>
<% String err = (String)request.getAttribute("err"); %>
<% if (err != null ) {%>
<p id="err"><span class="err"><%=err %></span></p>
<% } %>
</body>
</html>
