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
	<h3>회원가입창</h3>
	
	<form action="/member/insert" method="post" name="f">
		<input id="acc" name="acc" type="hidden" value="1">
		<label for="id">ID:</label><input required id="id" name="id" type="text" placeholder="ID를 입력하세요"><button id="idcheck">ID중복체크</button><p id="idcheckmsg"></p>
		<label for="pw">PW:</label><input required id="pw" name="pw" type="password" placeholder="비밀번호를 입력하세요"><br>
		<label for="pwcheck">PW확인:</label><input required id="pwcheck" name="pwcheck" type="password" placeholder="비밀번호를 다시 입력해주세요"><p id="pwcheckmsg"></p>
		<label for="name">이름:</label><input required id="name" name="name" type="text" placeholder="이름을 입력하세요"><br>
		<label for="birth">생년월일:</label><input required id="birth" name="birth" type="number" maxlength="7" oninput="numberMaxLength(this);" placeholder="생년월일과 주민번호 앞자리를 입력하세요"/>******<br>
		<label for="email">이메일:</label><input id="email" name="email" type="text" placeholder="e-mail을 입력하세요"><br>
		<label for="adrs1">주소:</label><input required id="adrs1" name="adrs1" type="text" placeholder="주소를 입력하세요"><br>
		<label for="adrs2">상세주소:</label><input required id="adrs2" name="adrs2" type="text" placeholder="상세주소를 입력하세요"><br>
		<label for="phone">전화번호:</label><input required id="phone" name="phone" type="text" placeholder="전화번호를 입력하세요"><br>
		<input type="submit" value="가입">
	</form>
	
<script type="text/javascript">
$(document).ready(function(){
	$("#idcheck").on("click", function(event){
		event.preventDefault();
		
		var id = $("input[name='id']").val();
		
		$.ajax({
			type : 'post',
			url : '/member/idcheck',
			data : {
				id:id
			},
			dataType : 'text',
			success : function(result){
				if(id!=null){
					$("#idcheckmsg").text(result)	
				}else{
					$("#idcheckmsg").text('값을 입력해주세요')
				}
				
				
			}
		});
	});
	
	$("#pwcheck").keyup(function(){
		
		var pw = $("input[name='pw']").val();
		var pwcheck = $("input[name='pwcheck']").val();
		
		if(pw != pwcheck | pw == null | pwcheck == null){
			$("#pwcheckmsg").text('비밀번호를 확인해주세요')
		}else if( pw == pwcheck | pwcheck == pw){
			$("#pwcheckmsg").text('비밀번호 확인 완료')
		}
	});
});

function numberMaxLength(e){
	if(e.value.length > e.maxLength){
		e.value = e.value.slice(0, e.maxLength);
	}
}

</script>

</body>
</html>