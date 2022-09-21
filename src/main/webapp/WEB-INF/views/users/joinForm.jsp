<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username" maxlength="20">
			<!-- button은 type을 지정해주지 않으면 자동으로 submit으로 타입이 지정된다. -->
			<!-- 첫글자는 element/표기형식은 camel표기법사용 -->
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control"
				placeholder="Enter password" maxlength="20">
		</div>
		<div class="mb-3">
			<input id="passwordRepeat" type="password" class="form-control"
				placeholder="Enter password">
				<button id="btnUsernameSameCheck" class="btn btn-warning" type="button" maxlength="20">유저네임 중복체크 및 비밀번호 확인</button>
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control"
				placeholder="Enter Email" maxlength="50">
		</div>
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	
</script>

<script src="/js/users.js"> </script>

<%@ include file="../layout/footer.jsp"%>

