<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� �α���</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Jua&display=swap');
*{
	font-family: 'Jua', sans-serif;
}

body {
	font-weight: bold;
	font-size: 12pt;
	color: #2F2F2F;
	background-color: #F6F8ED;
}

div.main {
	text-align: center;
	padding-top: 15px;
}

div.banner {
	background-color: #9DB589;
	text-align: center;
	padding: 20px;
	font-size: 18pt;
	color: #2F2F2F;
}

div.log {
	text-align: right;
}

div.box {
	border: 1px solid black;
	margin: 10% auto;
	width: 700px;
	height: 350px;
	background-color: #9DB589;
	text-align: center;
}

div.form {
	margin-top: 15px;
	display: inline-block;
	text-align: center;
}

#inputbox {
	width: 500px;
	height: 70px;
	margin-bottom: 10px;
}

#loginbutton {
	width: 506px;
	height: 70px;
	background-color: #2F2F2F;
	color: white;
}
</style>
</head>
<body>
	<h1>
		<div class="main">�𿩺���</div>
	</h1>
	<P></P>
	<div class="log">�α��� | ȸ������</div>

	<div class="banner">������Ʈ ��� &nbsp;&nbsp;&nbsp;&nbsp; |
		&nbsp;&nbsp;&nbsp;&nbsp; ������Ʈ ���� &nbsp;&nbsp;&nbsp;&nbsp; |
		&nbsp;&nbsp;&nbsp;&nbsp; ���� ��õ</div>
		
	<div class="box">
	<div class="form">
	<form name="loginForm" method="post" action="<c:url value='/user/login' />">
		<input type="text" id="inputbox" placeholder="���̵�" name="memberId" /><br>
		<input type="password" id="inputbox" placeholder="��й�ȣ" name="passwd" /><br>
	
		<input type="submit" id="loginbutton" value="�α���" />
		<br><br><br>
		<a href= "">���̵�/��й�ȣ ã��</a> | <a href="inputForm.jsp">ȸ������</a>
	</form>
	</div>
	</div>
</body>
</html>