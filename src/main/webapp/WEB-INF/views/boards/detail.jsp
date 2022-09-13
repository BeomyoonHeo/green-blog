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
	<div>
		<h3>${boards.title}</h3>
	</div>
	<hr />

	<div>${boards.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

