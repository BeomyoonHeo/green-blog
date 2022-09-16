<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />


		<div class="d-flex">
			<a href="/boards/update/${boards.id}" class="btn btn-warning">수정하러가기</a>
			<form action="/boards/delete/${boards.id}" method="get">
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />  
	<div class="d-flex justify-content-between">
        <div><h3>${boards.title}</h3></div> 
        <div>좋아요 수 : <p style="display: inline-block" id="likeCount" value="${boards.likeCount}">${boards.likeCount != null ? boards.likeCount : 0}</p>개<i id="iconHeart"class="fa-regular fa-heart"></i></div>
		<input id="islike" type="hidden" value="${boards.islike}">
		<input id="boardsid" type="hidden" value="${boards.id}">
		</div> 
	<hr />

	<div>${boards.content}</div>
<script src="/js/boards.js"></script>

</div>

<%@ include file="../layout/footer.jsp"%>

