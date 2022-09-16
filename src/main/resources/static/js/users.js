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

function join(){
	if (isUsernameSameCheck == false) {
		alert("아이디 중복 체크를 진행해주세요");
		return;
	}
	// 0. 통신 오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		email: $("#email").val(),
	}

	$.ajax("/join", {
		type: "POST",
		dataType: "json", //돌려 받는 타입명
		data: JSON.stringify(data), // 전달하는 타입명
		headers: {
			"Content-Type": "application/json" // spring에게 알려주는 것 - json으로 보내겠다. mime type
		}
	}).done((res) => {
		console.log(res);
		alert(res.msg);
		location.href = "/loginForm";

	});
}

function checkUsername(){
		// 0. 통신 오브젝트 생성 (Get 요청은 body가 없다.)

	// 1. 사용자가 적은 username값을 가져오기
	let username = $("#username").val();
	let password = $("#password").val();
	let passwordRepeat = $("#passwordRepeat").val();

	// 2. Ajax 통신
	$.ajax(`users/usernameSameCheck?username=${username}`, {
		type: "GET",
		dataType: "json", // 응답받은 데이터(json)를 javascriptobject로 변경(파싱)해준다.
		async: true
	}).done((res) => {
		if (res.code == 1) {
			//alert("통신성공");
			if (res.data == false && password == passwordRepeat) {
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


function login(){
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	$.ajax("/login", {
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

function update(){
		let data = {
		password: $("#password").val(),
		email: $("#email").val(),
	};
	let id = $("#id").val();
	//el표현식은 script안에 넣으면 안된다.
	$.ajax("/users/" + id, {
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
function reSign(){
		let id = $("#id").val();
	//el표현식은 script안에 넣으면 안된다.
	$.ajax("/users/" + id, {
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
