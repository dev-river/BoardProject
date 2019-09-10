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
			<div class="jumbotron">
				<h1>게시글 목록</h1>
			</div>
		</div>
	
		<div class="row" style="margin-bottom: 15px;">
			<div class="col-xs-1">
				<button class="btn btn-info"><a href="/board/create">글쓰기</a></button>
			</div>
			<div class="col-xs-push-9 col-xs-2">
				<select class="form-control">
					<option disabled>페이지당 글 수</option>
					<option ${to.perPage == 5?'selected':''}>5</option>
					<option ${to.perPage == 10?'selected':''}>10</option>
					<option ${to.perPage == 20?'selected':''}>20</option>
				</select>
			</div>
		</div>
		
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${to.list}" var="vo">
						<tr>
							<td>${vo.bno}</td>
							<td><a href="/board/read?bno=${vo.bno}&curPage=${to.curPage}&perPage=${to.perPage}">${vo.title}</a></td>
							<td>${vo.writer}</td>
							<td>${vo.updatedate}</td> <!-- 최종수정일 -->
							<td>${vo.viewcnt}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="row text-center" style="text-align: center">
				<ul class="pagination"> <!-- ul에 pagination 클래스를 주면 예쁘다 -->
				
					<c:if test="${to.curPage>1}">
						<li><a href="list.do?curPage=${to.curPage-1}&perPage=${to.perPage}">&laquo;</a></li>
					</c:if> <!-- 주소창에서 perPage값을 조절하면서 확인할것 -->
					
					<c:forEach begin="${to.bpn}" end="${to.spn}" var="idx">
						<li class="${to.curPage == idx?'active':''}"><a href="/board/list.do?curPage=${idx}&perPage=${to.perPage}">${idx}</a></li>
						<!-- li에 클래스를 active로 주면 현재 페이지에 색이 들어간다 -->
					</c:forEach>
					
					<c:if test="${to.curPage<to.totalPage}">
						<li><a href="list.do?curPage=${to.curPage+1}&perPage=${to.perPage}">&raquo;</a></li>
					</c:if>
					
				</ul>
			</div>

			<%-- <jsp:include page="page.jsp"/>  page.jsp 인클루드로 해보기 --%>
				
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function(){
			$("select").change(function(){
				var perPage = $("select option:selected").val();
				location.assign("/board/list?curPage=${to.curPage}&perPage="+perPage);
			});
		});
	</script>
</body>
</html>