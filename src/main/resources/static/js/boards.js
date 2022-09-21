
$("#btnDelete").click(() => {
	deleteClick();
});

// 하트 아이콘을 클릭했을때의 로직
$("#iconLove").click(() => {
	let isLovedState = $("#iconLove").hasClass("fa-solid");
	if (isLovedState) {
		deleteLove();
	} else {
		insertLove();
	}
});

$("#btnUpdate").click(() => {
	update();
});

$("#btnSave").click(() => {
	save();
});

function save() {
	// 0. 통신 오브젝트 생성
	let data = {
		title: $("#title").val(),
		content: $("#content").val()
	}

	$.ajax("/s/api/boards/write", {
		type: "POST",
		dataType: "json", //돌려 받는 타입명
		data: JSON.stringify(data), // 전달하는 타입명
		headers: {
			"Content-Type": "application/json" // spring에게 알려주는 것 - json으로 보내겠다. mime type
		}
	}).done((res) => {
		console.log(res);
		alert(res.msg);
		location.href = "/";
	});
}

function update() {
	let id = $("#id").val();
	let body = {
		title: $("#title").val(),
		content: $("#content").val(),
	};

	$.ajax("/s/api/boards/" + id, {
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
}


function deleteClick() {
	let id = $("#id").val();
	let page = $("#page").val();
	let keyword = $("#keyword").val();
	$.ajax("/s/api/boards/" + id, {
		type: "DELETE",
		dataType: "json",
	}).done((res) => {
		if (res.code == 1) {
			alert("삭제완료");
			location.href = "/boards?page=" + page + "&keyword=" + keyword;
		} else {
			alert("글 삭제 실패");
		}
	});
}

function deleteLove() {
	let id = $("#id").val();
	let lovesId = $("#lovesId").val();


	$.ajax("/s/api/boards/" + id + "/loves/" + lovesId, {
		type: "DELETE",
		dataType: "json",
	}).done((res) => {
		if (res.code == 1) {
			renderCancelLoves();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) - 1);
		} else {
			alert("좋아요 취소 실패");
		}
	});

}

function insertLove() {
	let id = $("#id").val();

	$.ajax("/s/api/boards/" + id + "/loves", {
		type: "POST",
		dataType: "json",
	}).done((res) => {
		if (res.code == 1) {
			renderLove();
			let count = $("#countLove").text();
			$("#countLove").text(Number(count) + 1);
			$("#lovesId").val(res.data.id);
		} else {
			alert("좋아요 실패");
		}
	});
}

function renderLove() {
	$("#iconLove").removeClass("fa-regular");
	$("#iconLove").addClass("fa-solid");
}

function renderCancelLoves() {
	$("#iconLove").removeClass("fa-solid");
	$("#iconLove").addClass("fa-regular");
}
