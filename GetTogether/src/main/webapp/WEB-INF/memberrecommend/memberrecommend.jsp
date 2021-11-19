<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
<meta charset="UTF-8">
<head>
<title>memberRecommend</title>
<%@ include file="/WEB-INF/components/nav.jsp" %>
<link rel=stylesheet href="<c:url value='/css/memberrecommend.css' />" type="text/css">
</head>	
<body>
		<div class="page-layout">
			<div class="subtitle">
				<h2>팀원 추천</h2>
				<div class="search-box">
					<input class="input-search" type="text" placeholder="검색하기">
					<button class="option-button">search</button>
				</div>
			</div>
			<div class="member-container">
				<c:forEach var="recommendMember" items="${memberRecommendList}">
					<div class="member-box">
						<ul>
							<li>${recommendMember.mname}</li>
							<div class="member-option-list">
								<button class="option-button">${recommendMember.language}</button>
							</div>
						</ul>
					</div>
				</c:forEach>	
		</div>
		</div>
	</body>
</html>
