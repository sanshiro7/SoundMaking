<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.subro.soundmaking.db.SoundSetting" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="/SoundMaking/css?name=menu.css">
<link rel="stylesheet" href="/SoundMaking/css?name=button.css">
<title>おとづくり - おとの管理 -</title>
</head>
<body>
</head>
<body>
<header>
    <h1><a href="">おとの管理</a></h1>
    <nav>
        <ul>
            <li><a href="/SoundMaking/menu" id="menu">メニュー</a></li>
            <li><a href="/SoundMaking/sound" id="sound">おとづくり</a></li>
            <li><a href="/SoundMaking/logout" id="logout">LOGOUT</a></li>
        </ul>
    </nav>
</header>
<br>
<%
List<SoundSetting> soundsettings = (List<SoundSetting>)request.getAttribute("soundsettings");

if (soundsettings.size() == 0) {
%>
	<p>まだおとは登録されていません。</p>
<%
}
else {
%>	
<table id="soundsettinglist">
    <tr><th>おとの名前</th><th>1</th><th>2</th><th>3</th><th>4</th><th>5</th><th></th><th></th></tr>
<%	int i = 1; 
	for(SoundSetting soundsetting : soundsettings) {
	
    	String onoff1 = "off";
    	String onoff2 = "off";
    	String onoff3 = "off";
    	String onoff4 = "off";
    	String onoff5 = "off";
    	 
    	if(soundsetting.isEffectorOnOff1()) { onoff1 = "on"; }
    	if(soundsetting.isEffectorOnOff2()) { onoff2 = "on"; }
    	if(soundsetting.isEffectorOnOff3()) { onoff3 = "on"; }
     	if(soundsetting.isEffectorOnOff4()) { onoff4 = "on"; }
     	if(soundsetting.isEffectorOnOff5()) { onoff5 = "on"; }
%>
    <tr>
        <td><div id="soundsettingname<%=i %>"><%=soundsetting.getSoundName() %></div></td>
        <td><img src="/SoundMaking/img?name=<%=soundsetting.getEffectorId1() %><%=onoff1 %>.png" width="40" height="60" id="img<%=i %>-1"></td>
        <td><img src="/SoundMaking/img?name=<%=soundsetting.getEffectorId2() %><%=onoff2 %>.png" width="40" height="60" id="img<%=i %>-2"></td>
        <td><img src="/SoundMaking/img?name=<%=soundsetting.getEffectorId3() %><%=onoff3 %>.png" width="40" height="60" id="img<%=i %>-3"></td>
        <td><img src="/SoundMaking/img?name=<%=soundsetting.getEffectorId4() %><%=onoff4 %>.png" width="40" height="60" id="img<%=i %>-4"></td>
        <td><img src="/SoundMaking/img?name=<%=soundsetting.getEffectorId5() %><%=onoff5 %>.png" width="40" height="60" id="img<%=i %>-5"></td>
        <td><form action="" method="post"><input type="submit" value="呼出" class="btn--orange" id="load_button<%=i %>"><input type="hidden" name="action" value="load"><input type="hidden" name="soundsettingId" value="<%=soundsetting.getId() %>"></form></td>
        <td><form action="" method="post"><input type="submit" value="削除" class="btn--orange" id="delete_button<%=i %>"><input type="hidden" name="action" value="delete"><input type="hidden" name="soundsettingId" value="<%=soundsetting.getId() %>"></form></td>
    </tr>
<%
		i++;
	} 
}
%>
</table>
<p>
<img src="/SoundMaking/img?name=hana2.png">現在編集中のおとを登録できます<img src="/SoundMaking/img?name=hana2.png"><br>
<form action="" method="post">
おとの名前：<input type="text" name="soundname" id="soundsettingname"> 
	<input type="submit" value="おとの登録" class="btn--orange" id="create_button">
	<input type="hidden" name="action" value="create">
</form>
</p>
<% String err = (String)request.getAttribute("err"); %>
<% if( err != null) { %>
<p><div class="err" id="err"><%=err %></div></p>
<% } %>
</body>
</html>
</body>
</html>