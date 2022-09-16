let check = JSON.parse($("#islike").val());

if (check == true) {
	$("#iconHeart").removeClass("fa-regular");
	$("#iconHeart").addClass("fa-solid");
	$("#iconHeart").css("color", "red");
}

$("#iconHeart").click((_event) => {
	heartClickEvent();
});

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
			location.href="/loginForm";
		}
	});
}










