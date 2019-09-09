<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewprot" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

	<c:if test="${not empty login}">
	${login.id} 님, 안녕하세요 <a href="/member/logout">로그아웃</a>
	</c:if>
	<c:if test="${empty login}">
	로그인이 필요합니다 <a href="/member/login">로그인</a>
	</c:if>

	<h1>회원 목록</h1>
	
	<table border="1">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>전화번호</th>
		</tr>
	</thead>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.id}</td>
			<td><a href="/member/read?id=${list.id}">${list.name}</a></td>
			<td>${list.email}</td>
			<td>${list.phone}</td>
		</tr>
	</c:forEach>
	</table>
	<br>
	<a href="/member/insert"><button>회원등록</button></a>
	

</body>
</html>