<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/login" method="post">
		<div class="mb-3 mt-3">
			<input id="username"
				type="text" class="form-control"
				placeholder="Enter username" name="username">
		</div>
		<div class="mb-3">
			<input id ="password"
				type="password" class="form-control" 
				placeholder="Enter password" name="password">
		</div>
		<button id="btnLogin" type="button" class="btn btn-primary">로그인</button>
	</form>
</div>
<script>
	$("#btnLogin").click(()=>{

		let data = {
				username:$("#username").val(),
				password:$("#password").val()
		}
		
		$.ajax("/login",{
			type:"POST",
			dataType:"json", //응답데이터 타입명
			data:JSON.stringify(data), // 요청데이터 타입명
			headers:{
				"Content-Type":"application/json; charset=utf-8"// spring에게 알려주는 것 - json으로 보내겠다. mime type - 필수
			}
		}).done((res)=>{
			if(res.code == 1){
				location.href="/";
			}else{
				alert("로그인 실패, 아이디 패스워드를 확인해주세요");
			}
		});
			//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
		}); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)

</script>
<%@ include file="../layout/footer.jsp"%>

