<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username">
			<!-- button은 type을 지정해주지 않으면 자동으로 submit으로 타입이 지정된다. -->
			<!-- 첫글자는 element/표기형식은 camel표기법사용 -->
			<button id="btnUsernameSameCheck" class="btn btn-warning" type="button">유저네임 중복체크</button>
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control"
				placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control"
				placeholder="Enter Email">
		</div>
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>

	let isUsernameSameCheck = false;
	
	//회원가입
	$("#btnJoin").click(()=>{
		if(isUsernameSameCheck == false){
			alert("아이디 중복 체크를 진행해주세요");
			return;
		}
		// 0. 통신 오브젝트 생성
		let data = {
				username:$("#username").val(),
				password:$("#password").val(),
				email:$("#email").val(),
		}
		
		$.ajax("/join",{
			type:"POST",
			dataType:"json", //돌려 받는 타입명
			data:JSON.stringify(data), // 전달하는 타입명
			headers:{
				"Content-Type":"application/json" // spring에게 알려주는 것 - json으로 보내겠다. mime type
			}
		}).done((res)=>{
			console.log(res);
			alert(res.msg);
			location.href="/loginForm";
		});
	});
	
	//유저네임 중복 체크
	$("#btnUsernameSameCheck").click(()=>{
		// 0. 통신 오브젝트 생성 (Get 요청은 body가 없다.)
		
		// 1. 사용자가 적은 username값을 가져오기
		let username = $("#username").val();
		
		// 2. Ajax 통신
		$.ajax("users/usernameSameCheck?username="+username,{
			type:"GET",
			dataType:"json", // 응답받은 데이터(json)를 javascriptobject로 변경(파싱)해준다.
			async:true
		}).done((res)=>{
			console.log(res);
			if(res.code == 1){
				//alert("통신성공");
				if(res.data == false){
					alert("아이디가 중복되지 않았습니다.");
					isUsernameSameCheck = true;
				}else{
					alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요");
					isUsernameSameCheck = false;
					$("#username").val("");
				}
			}
			//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
		}); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)
	});
</script>

<%@ include file="../layout/footer.jsp"%>

