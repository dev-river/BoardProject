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

	<h1>회원상세보기</h1>
	권한 : ${vo.acc}<br>
	아이디 : ${vo.id}<br>
	비밀번호 : ${vo.pw}<br>
	이름 : ${vo.name}<br>
	생년월일 : ${vo.birth}<br>
	이메일 : ${vo.email}<br>
	주소 : ${vo.adrs1}<br>
	상세주소 : ${vo.adrs2}<br>
	전화번호 : ${vo.phone}<br>
	가입일 : ${vo.signDate}<br>
	기타등록번호 : ${vo.checkSN}<br>
	
	<a href="/member/update?id=${vo.id}"><button>수정</button></a>&nbsp;&nbsp;
	<a href="/member/delete?id=${vo.id}"><button>삭제</button></a>&nbsp;&nbsp;
	<a href="/member/list"><button>목록</button></a><br>
</body>
</html>