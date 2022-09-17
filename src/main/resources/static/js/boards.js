$("#iconHeart").click((_event) => {
	heartClickEvent();
});

$("#btnDelete").click(() => {
	deleteClickEvent();
});

$("#btnUpdateForm").click(() => {
	updateFormClickEvent();
});

$("#btnUpdate").click(() => {
	updateClickEvent();
});


function updateClickEvent() {
	let id = $("#id").val();
	let body = {
		title: $("#title").val(),
		content: $("#updateContent").val(),
	};

	$.ajax("/boards/update/" + id, {
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(body),
		headers: {
			"Content-Type": "application/json; UTF-8",
		},
	}).done((res) => {
		if (res.code == 1) {
			alert(res.msg);
			location.href = "/boards/" + id;
		} else {
			alert("업데이트 실패");
			history.back();
		}
	});
};

function updateFormClickEvent() {
	let boardsid = $("#boardsid").val();

	$.ajax("/boards/update/" + boardsid, {
		type: "GET",
		dataType: "json"
	});
}

function deleteClickEvent() {
	let boardsid = $("#boardsid").val();

	$.ajax("/boards/delete/" + boardsid, {
		type: "DELETE",
		dataType: "json"
	}).done((_Onres) => {
		if (_Onres.code != 1) {
			alert("잘못된 접근입니다. 작성자 아이디로 로그인 해주세요");
			_Onres.data == null ? location.href = "/loginForm" : location.href = "/loginForm" + _Onres.data;
		} else
			alert("삭제 완료");
		location.href = "/";
	});
}

function heartClickEvent() {
	check = $("#iconHeart").hasClass("fa-regular");
	let boardid = $("#boardsid").val();

	console.log(check);
	let toggleCheck = {
		likeCount: $("#likeCount").val(),
		islike: check
	};
	console.log(toggleCheck);
	//loves 테이블 생성(id, ,usersid boardsid, createdAt) 복합 유니크 사용(userid랑 boardid)
	$.ajax("/boards/getLikeCount/" + boardid, {
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(toggleCheck),
		headers: {
			"Content-Type": "application/json; charset=utf-8",
		},
	}).done((_Onres) => {
		if (_Onres.code == 1) {
			$("#likeCount").text(_Onres.data == null ? 0 : _Onres.data);
			console.log(_Onres);
			if (check == true) {
				$("#iconHeart").removeClass("fa-regular");
				$("#iconHeart").addClass("fa-solid");
				$("#iconHeart").css("color", "red");
			} else {
				$("#iconHeart").removeClass("fa-solid");
				$("#iconHeart").addClass("fa-regular");
				$("#iconHeart").css("color", "black");
			}
		} else {
			alert("잘못된 접근입니다. 로그인을 진행 해주세요");
			location.href = "/loginForm";
		}
	});
}










