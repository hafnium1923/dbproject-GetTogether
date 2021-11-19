<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	List<Project> projectList = (List<Project>)request.getAttribute("projectList");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������Ʈ �˻� ���</title>
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
	text-align: center;
}

div.main {
	text-align: center;
	padding-top: 15px;
	font-size: 25px;
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
.search{
	text-align: right;
	margin-top: 10px;
}
p {
	text-align: left;
}
.context {
	display: inline-block;
}
table {
	border-spacing: 30px;
	font-size: 25px;
}
.project_context {
	background-color: #9DB589;
	width: 350px;
	height: 250px;
	padding-left: -50px;
}
</style>
</head>
<body>
	<div class="main">
		<h1>�𿩺���</h1>
	</div>
	<P></P>
	<div class="log">�α��� | ȸ������</div>

	<div class="banner">������Ʈ ��� &nbsp;&nbsp;&nbsp;&nbsp; |
		&nbsp;&nbsp;&nbsp;&nbsp; ������Ʈ ���� &nbsp;&nbsp;&nbsp;&nbsp; |
		&nbsp;&nbsp;&nbsp;&nbsp; ���� ��õ</div>

	
	<%	
		Project project1 = new Project(1, "project 1", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
		Project project2 = new Project(2, "project 2", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
		Project project3 = new Project(3, "project 3", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
		Project project4 = new Project(4, "project 4", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
		Project project5 = new Project(5, "project 5", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
		Project project6 = new Project(6, "project 6", "����", "����", "����", "goal", 1, "description", true, 1, 1, 1);
	%>

	<div class="search">
		<select style="height: 30px;">
			<option value="1">����</option>
			<option value="2">�ۼ���</option>
		</select> 
		<input type="text" style="height: 30px; width: 400px;">
	</div>
	<div class="context">
	<table>
			<tr>
				<td>"project"�� �˻���� �Դϴ�.</td>
			</tr>
			<tr>
			<td class="project_context">
				<%=project1.getTitle() %>
				<br>
				<%=project1.getGoal() %>
			</td>
			<td class="project_context">
				<%=project2.getTitle() %>
				<br>
				<%=project2.getGoal() %>
			</td>
			<td class="project_context">
				<%=project3.getTitle() %>
				<br>
				<%=project3.getGoal() %>
			</td>
		</tr>
		<tr>
			<td class="project_context">
				<%=project4.getTitle() %>
				<br>
				<%=project4.getGoal() %>
			</td>
			<td class="project_context">
				<%=project5.getTitle() %>
				<br>
				<%=project5.getGoal() %>
			</td>
			<td class="project_context">
				<%=project6.getTitle() %>
				<br>
				<%=project6.getGoal() %>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>