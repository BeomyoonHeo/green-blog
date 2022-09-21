let isUsernameSameCheck = false;

//회원가입
$("#btnJoin").click(() => { //이벤트 리스너
	join();
});

//유저네임 중복 체크
$("#btnUsernameSameCheck").click(() => {
	checkUsername(); //메서드는 동사가 앞에와야 좋다.
});

$("#btnLogin").click(() => {
	login();
}); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)

$("#btnUpdate").click(() => {
	update();
}); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)

$("#btnDelete").click(() => {
	reSign();
});

function patternCheck(data){
	let check;
	let pattern = /\s/g;
	let upper = /[A-Z]/;
	let korRule =  /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	let checkEmail = /[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+/;
	
	if(korRule.test(data.username)){
		alert("아이디에서 한글이 발견되었습니다 제외해주세요");
		check = 1;
	}
	else if(pattern.test(data.username)){
		alert("아이디에서 공백이 발견되었습니다 제외해주세요");
		check = 2;
	}
	else if(!upper.test(data.username)){
		alert("아이디를한글자 이상 대문자로 입력해주세요");
		check = 3;
		
	}else if(!checkEmail.test(data.email)){
		alert("이메일형식이 올바르지 않습니다.");
		check = 4;
		
	}else if(pattern.test(data.password)){
		alert("비밀번호에서 공백이 발견되었습니다 제외해주세요");
		check = 5;
		
	}else if(korRule.test(data.password)){
		alert("비밀번호에서 한글이 발견되었습니다 제외해주세요");
		check = 6;
		
	}else if(pattern.test(data.email)){
		alert("이메일에서 공백이 발견되었습니다 제외해주세요");
		check = 7;
		
	}else{
		check = 0;
	}
	console.log(data.email);
	return check;
}

function join() {

	// 0. 통신 오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val(),
	}
	let check = patternCheck(data);
	if(check != 0){
		return;
	}
	let passwordRepeat = $("#passwordRepeat").val();
	if (isUsernameSameCheck == false) {
		alert("아이디 중복 체크를 진행해주세요");
		return;
	}
	if(passwordRepeat != data.password){
		alert("패스워드가 동일하지 않습니다.");
		return;
	}
	$.ajax("/api/join", {
		type: "POST",
		dataType: "json", //돌려 받는 타입명
		data: JSON.stringify(data), // 전달하는 타입명
		headers: {
			"Content-Type": "application/json" // spring에게 알려주는 것 - json으로 보내겠다. mime type
		}
	}).done((res) => {
		if(res.code == 1){
			alert(res.msg);
			location.href = "/loginForm";	
		}else{
			alert(res.msg);
			history.back;
		}
	});
}

function checkUsername() {
	// 0. 통신 오브젝트 생성 (Get 요청은 body가 없다.)

	// 1. 사용자가 적은 username값을 가져오기
	let username = $("#username").val();

	// 2. Ajax 통신
	$.ajax(`users/usernameSameCheck?username=${username}`, {
		type: "GET",
		dataType: "json", // 응답받은 데이터(json)를 javascriptobject로 변경(파싱)해준다.
		async: true
	}).done((res) => {
		if (res.code == 1) {
			//alert("통신성공");
			if (res.data == false) {
				alert("아이디가 중복되지 않았습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
		//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
	}); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)
}


function login() {
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/api/login", {
		type: "POST",
		dataType: "json", //응답데이터 타입명
		data: JSON.stringify(data), // 요청데이터 타입명
		headers: {
			"Content-Type": "application/json; charset=utf-8"// spring에게 알려주는 것 - json으로 보내겠다. mime type - 필수
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("로그인 성공");
			location.href = "/";
		} else {
			alert("로그인 실패, 아이디 패스워드를 확인해주세요");
		}
	});
	//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
}

function update() {
	let data = {
		password: $("#password").val(),
		email: $("#email").val(),
	};
	let id = $("#id").val();
	//el표현식은 script안에 넣으면 안된다.
	$.ajax("/s/api/users/" + id, {
		type: "PUT",
		dataType: "json", //응답데이터 타입명
		data: JSON.stringify(data), // 요청데이터 타입명
		headers: {
			"Content-Type": "application/json; charset=utf-8", // spring에게 알려주는 것 - json으로 보내겠다. mime type - 필수
		},
	}).done((res) => {
		// pending이 끝나면 발동하는 메서드
		if (res.code == 1) {
			console.log(res.msg);
			alert("회원 수정 완료");
			location.reload(); //f5
		} else {
			alert("업데이트에 실패했습니다.");
			console.log(res.msg);
		}
	});
	//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
}
function reSign() {
	let id = $("#id").val();
	//el표현식은 script안에 넣으면 안된다.
	$.ajax("/s/api/users/" + id, {
		type: "DELETE",
		dataType: "json" //응답데이터 타입명
	}).done((res) => {
		// pending이 끝나면 발동하는 메서드
		if (res.code == 1) {
			console.log(res.msg);
			alert("회원탈퇴 완료");
			location.href = "/"; //f5
		} else {
			alert("회원탈퇴에 실패했습니다.");
			console.log(res.msg);
		}
	});
	//람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
}
