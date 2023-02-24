<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<link rel="stylesheet" href="/SoundMaking/css?name=menu.css">
<link rel="stylesheet" href="/SoundMaking/css?name=button.css">
<title>おとづくり - おとづくり -</title>
</head>
<body>
<header>
    <h1><a href="">おとづくり</a></h1>
    <nav>
        <ul>
            <li><a href="/SoundMaking/menu" id="menu">メニュー</a></li>
            <li><a href="/SoundMaking/logout" id="logout">LOGOUT</a></li>
        </ul>
    </nav>
</header>
<br>
エフェクターを組み合わせて音を作ってみましょう！<br>
<br>
<table id="effectors">
<tr>
<%@ page import="java.util.Map" %>
<% int[] effectorIds = (int[])request.getAttribute("effectorIds"); %>
<% int i = 0; %>
<% for(int effectorId : effectorIds) { %>
    <td>
        <form action="" method="post">
            <input type="hidden" name="action" value="pedal">
            <input type="hidden" name="boardNo" value="<%=i %>">
<%     boolean[] effectOnOffs = (boolean[])request.getAttribute("effectOnOffs"); %>
<%     if (effectOnOffs[i] == true) {%>
            <input type="image" src="/SoundMaking/img?name=<%=effectorId %>on.png" id="effector_img<%=i+1 %>"><input type="hidden" name="onoff" value="off">
<%     } else { %>
            <input type="image" src="/SoundMaking/img?name=<%=effectorId %>off.png" id="effector_img<%=i+1 %>"><input type="hidden" name="onoff" value="on">
<%     } %>
         </form>
     </td>
<%     i++; %>
<% } %>
    <td><img src="/SoundMaking/img?name=line1.png"></td>
</tr>
<tr>
<% Map<Integer, String> effectorList = (Map<Integer, String>)request.getAttribute("effectorList"); %>
<% int j = 0; %>
<% for(int effectorId : effectorIds) { %>
    <td>
        <form action="" method="post">
            <select name="effectId" onchange="submit(this.form)" id="select<%=j+1 %>">
<%    for (Integer key : effectorList.keySet()) { %>            
                <option value="<%=key %>" <% if ( effectorId == key ) { %>selected<% } %>><%=effectorList.get(key) %></option>
<%    } %>
            </select>
            <input type="hidden" name="action"   value="effector">
            <input type="hidden" name="boaradNo" value="<%=j %>">
        </form>
    </td>
<%    j++; %>
<% } %>
    <td><img src="/SoundMaking/img?name=line2.png"></td>
</tr>
<tr>
    <td colspan="5" id="outputsound">
<% String outsound = (String)request.getAttribute("outsound"); %>
<% if (outsound != null ) {%>
        <strong><big><big><div id="outsound"><%=outsound %></div></big></big></strong>
<% } else {%>
        <big><div id="outsound">シ～ン</div></big>
<% } %>
</td><td><img src="/SoundMaking/img?name=music_guitar_amp.png"></td></tr>
</table>
<p>
<form action="" method="post">
	<input type="submit" value="おとを出す" class="btn--orange" id="outsound_button">
	<input type="hidden" name="action" value="sound">
</form>
</p>
<p>
<form action="/SoundMaking/sound/soundmanager" method="get">
	<input type="submit" value="おとの管理" class="btn--orange" id="soundmanager_button">
</form>
</p>
</body>
</html>
