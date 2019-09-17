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

	<div class="container">
		<div class="row">
			<h1>게시글 상세보기</h1>
			
			<form action=""> <!-- 정보값 넘기기위해서 -->
				<input type="hidden" name="bno" value="${vo.bno}">
				<input type="hidden" name="curPage" value="${to.curPage}">
				<input type="hidden" name="perPage" value="${to.perPage}">
			</form>
			
			<div class="form-group">
				<label for="bno">글번호</label>
				<input class="form-control" id="bno" value="${vo.bno}" readonly = "readonly">
			</div>
			<div class="form-group">
				<label for="viewcnt">조회수</label>
				<input class="form-control" id="viewcnt" value="${vo.viewcnt}" readonly = "readonly">
			</div>	
			<div class="form-group">
				<label for="title">제목</label>
				<input class="form-control" id="title" value="${vo.title}" readonly = "readonly">
			</div>	
			<div class="form-group">
				<label for="bno">작성자</label>
				<input class="form-control" id="writer" value="${vo.writer}" readonly = "readonly">
			</div>	
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" id="content" rows="3" readonly = "readonly">${vo.content}</textarea>
			</div>
			
			<div class="form-group">
				<button id="reply_form" class="btn btn-primary">댓글</button>&nbsp;&nbsp;
				<button class="btn btn-warning modify">수정</button>&nbsp;&nbsp;
				<button class="btn btn-danger del">삭제</button>&nbsp;&nbsp;
				<button class="btn btn-info list">목록</button>
			</div>
			
		</div>
		<hr>
		<div class="row">
			<div id="myCollapsible" class="collapse">
				<div class="form-group">
					<label for="replyer">작성자</label>
					<input id="replyer" class="form-control">
				</div>
				<div class="form-group">
					<label for="replytext">내용</label>
					<input id="replytext" class="form-control">
				</div>
				<div class="form-group">
					<button id="replyInsertBtn" class="btn btn-default">댓글 등록</button>
					<button id="replyResetBtn" class="btn btn-default">초기화</button>
				</div>
			</div>
		</div>
		
		<div id="replies" class="row">
			<hr>
			
		</div>
		
		<div class="row">
			<div data-backdrop="static" id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-header">
						<button data-dismiss="modal" class="close">&times;</button>
						<p id="modal_rno"></p>
					</div>
					<div class="modal-body">
						<input id="modal_replytext" class="form-control">
					</div>
					<div class="modal-footer">
						<button id="modal_update" data-dismiss="modal" class="btn">수정</button>
						<button id="modal_delete" data-dismiss="modal" class="btn">삭제</button>
						<button id="modal_close" data-dismiss="modal" class="btn">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

		var bno = ${vo.bno}; /* 계속 사용될것이므로 전역변수로 지정 */
		
		$(document).ready(function(){
			
			$("#modal_delete").click(function(){
				var rno = $("#modal_rno").text();
				
				$.ajax({
					type : 'delete',
					url : '/replies/'+rno,
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'delete'
					},
					dataType : 'text',
					success : function(result){
						alert(result);
						getAllList(bno);
					}
				});
			});
			
			$("#modal_update").click(function(){
				var rno = $("#modal_rno").text();
				var replytext = $("#modal_replytext").val();
				
				$.ajax({
					type : 'put',
					url : '/replies/'+rno,
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'put'
					},
					data : JSON.stringify({
						replytext : replytext
					}),
					dataType : 'text',
					success : function(result){
						alert(result);
						getAllList(bno);
					}
				});
			});
			
			/*동적으로 생성된 버튼에서 가장 가까운 정적 부모 아이디를 넣어줘야함(#replies), 동적에는 on 함수를 써야함*/
			$("#replies").on("click", ".callModal", function(){
				var rno = $(this).prev("p").attr("data-rno");
				var replytext = $(this).prev("p").text();
				
				$("#modal_rno").text(rno);
				$("#modal_replytext").val(replytext);
				
				$("#myModal").modal("show");
			});
			
			
			$("#reply_form").click(function(){
				$("#myCollapsible").collapse("toggle");
			});
			
			$("#replyResetBtn").click(function(){
				$("#replyer").val("");
				$("#replytext").val("");
			});
			
			$("#replyInsertBtn").click(function(){
				
				var replyer = $("#replyer").val();
				var replytext = $("#replytext").val();
				
				$.ajax({
					type : 'post',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST',
					},
					url : '/replies',
					data : JSON.stringify({
						bno : bno,
						replyer : replyer,
						replytext : replytext
					}),
					dataType : 'text',
					success : function(result){
						alert(result);
						if(result == 'INSERT_SUCCESS'){
							$("#replyer").val("");
							$("#replytext").val("");
							getAllList(bno);
						}
					}
				});
			});
			
			var $form = $("form");

			$(".modify").click(function(){
				$form.attr("action","/board/modify");
				$form.attr("method","get");
				$form.submit();
			});
			
			$(".del").click(function(){
				$form.attr("action","/board/del");
				$form.attr("method","post");
				$form.submit();
			});
			
			$(".list").click(function(){
				$form.attr("action","/board/list");
				$form.attr("method","get");
				$form.submit();
			});
			
			getAllList(bno);
			
		});
		
		function getAllList(bno) {
			$.getJSON("/replies/"+bno, function(arr){
				/* console.log(result); 관리자모드 - console로 확인하기*/
				var str = '<hr>';
				
				for(var i=0;i<arr.length;i++){ 
					/*
					div부분을 문자열로 인식해야하기 때문에 줄마다 ''로 감싸주고 +로 연결해야한다
					데이터 부분은 배열값으로 되어있기 때문에 arr[i].rno 의 형태로 적용시켜줘야한다
					data-rno를 이용해 rno값을 p에 고정
					button class에 callModal이라는 클래스 추가 (나중에 Modal을 사용하기 위해)
					*/
					str += '<div class="panel panel-info">'+
				'<div class="panel-heading">'+
					'<span>rno:'+arr[i].rno+', 작성자: <span class="glyphicon glyphicon-user"></span>'+arr[i].replyer+'</span>'+
					'<span class="pull-right"><span class="glyphicon glyphicon-time"></span>'+arr[i].updatedate+'</span>'+
				'</div>'+
				'<div class="panel-body">'+
					'<p data-rno="'+arr[i].rno+'">'+arr[i].replytext+'</p>'+
					'<button class="btn callModal"><span class="glyphicon glyphicon-edit"></span>수정/삭제<span class="glyphicon glyphicon-trash"></span></button>'+
				'</div>'+
				'</div>';
				}
				
				$("#replies").html(str);
			});
		}
		
	</script>
</body>
</html>