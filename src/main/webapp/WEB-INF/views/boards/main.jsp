<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br />
	<div class="d-flex justify-content-end">
		<div style="width: 300px">
			<form class="d-flex" method="get" action="">
				<input class="form-control me-2" type="text" placeholder="Search" name="keyword">
				<button class="btn btn-primary" type="submit">Search</button>
			</form>
		</div>
	</div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="page" items="${pagingDto.mainDtos}">
			<tr>
				<td>${page.id}</td>
				<td><a href="/boards/${page.id}">${page.title}</a></td>
				<td>${page.username}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class='page-item ${pagingDto.first?"disabled":""}'><a class="page-link" href="/boards?page=${pagingDto.currentPage - 1}&keyword=${pagingDto.keyword}">Prev</a></li>
			<c:forEach var="page" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum - 1}">
			<li class='page-item'><a class='page-link' href="/boards?page=${page-1}&keyword=${pagingDto.keyword}">${page}</a></li>
			</c:forEach>
			<li class='page-item ${pagingDto.last || pagingDto.notResult?"disabled":""}'><a class="page-link" href="/boards?page=${pagingDto.currentPage + 1}&keyword=${pagingDto.keyword}">Next</a></li>
		</ul>
	</div>

</div>

<%@ include file="../layout/footer.jsp"%>

