<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row text-center" style="text-align: center;">

	<ul class="pagination">
	
	<c:if test="${to.curPage>1}">
		<li><a href="list?curPage=${to.curPage-1}"> &laquo; </a></li>
	</c:if>
	
		<c:forEach begin="${to.bpn}" end="${to.spn}" var="idx">
			<li class="${to.curPage == idx?'active':''}"><a href="list?curPage=${idx}">${idx}</a></li>
			<!-- li에 클래스를 active로 주면 현재 페이지에 색이 들어간다 -->
		</c:forEach>

	<c:if test="${to.curPage<to.totalPage}">
		<li><a href="list?curPage=${to.curPage+1}"> &raquo; </a></li>
	</c:if>
	
	</ul>

</div>