<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br /> 
        <input id="id" type="hidden" value="${detailDto.id}">
        <input id="lovesId" type="hidden" value="${detailDto.lovesId}">
        <div class="d-flex">
        <input id="page" type="hidden" value="${sessionScope.refferer.page}">
        <input id="keyword" type="hidden" value="${sessionScope.refferer.keyword}">
        <c:if test="${!empty sessionScope.principal}">
			<a href="/s/boards/${detailDto.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
				<button id="btnDelete" type="button" class="btn btn-danger">삭제</button>
			</form>
		</c:if>
		</div>


	<br />
	<div class="d-flex justify-content-between">
		<h3>${detailDto.title}</h3>
		<div> 좋아요 수 : <span id="countLove">${detailDto.loveCount}</span> 
		<i id="iconLove" class='${detailDto.loved ? "fa-solid" : "fa-regular"} fa-heart my_pointer my_red'></i>
		</div>
	</div>
	<hr />

	<div>${detailDto.content}</div>
<script src="/js/boards.js">
</script>

</div>

<%@ include file="../layout/footer.jsp"%>

