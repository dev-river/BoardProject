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
	<h3>회원정보수정</h3>
	
	<form action="/member/update" method="post">
		<label for="acc">권한:</label><input id="acc" name="acc" type="text" value="${vo.acc}"><br>
		<label for="id">ID:</label><input required id="id" name="id" type="text" value="${vo.id}" readonly><br>
		<label for="pw">PW:</label><input required id="pw" name="pw" type="password" value="${vo.pw}"><br>
		<label for="name">이름:</label><input required id="name" name="name" type="text"  value="${vo.name}"><br>
		<label for="birth">생년월일:</label><input required id="birth" name="birth" type="text"  value="${vo.birth}">******<br>
		<label for="email">이메일:</label><input id="email" name="email" type="text"  value="${vo.email}"><br>
		<label for="adrs1">주소:</label><input required id="adrs1" name="adrs1" type="text"  value="${vo.adrs1}"><br>
		<label for="adrs2">상세주소:</label><input required id="adrs2" name="adrs2" type="text" value="${vo.adrs2}"><br>
		<label for="phone">전화번호:</label><input required id="phone" name="phone" type="text" value="${vo.phone}"><br>
		<input type="submit" value="수정">
	</form>
</body>
</html>