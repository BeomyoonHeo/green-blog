<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />

        <input id="page" type="hidden" value="${sessionScope.refferer.page}">
        <input id="keyword" type="hidden" value="${sessionScope.refferer.keyword}">
		<div class="d-flex">
			<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
			<input id="id" type="hidden" value="${boards.id}">
				<button id="btnDelete" type="button" class="btn btn-danger">삭제</button>
			</form>
		</div>


	<br />
	<div class="d-flex justify-content-between">
		<h3>${boards.title}</h3>
		<div> 좋아요 수 : 10 <i id="iconHeart"class="fa-regular fa-heart"></i></div>
	</div>
	<hr />

	<div>${boards.content}</div>
<script>
    $("#btnDelete").click(()=>{
        let id = $("#id").val();
        let page = $("#page").val();
        let keyword = $("#keyword").val();
        $.ajax("/boards/"+id,{
            type:"DELETE",
            dataType:"json",
        }).done((res)=>{
            if(res.code == 1){
                alert("삭제완료");
                location.href="/boards?page="+page+"&keyword="+keyword;
            }else{
                alert("글 삭제 실패");
            }
        });
    });
	$("#iconHeart").click((event)=>{
		//loves 테이블 생성(id, ,usersid boardsid, createdAt) 복합 유니크 사용(userid랑 boardid)
		let check = $("#iconHeart").hasClass("fa-regular");
		if(check == true){
			$("#iconHeart").removeClass("fa-regular");
			$("#iconHeart").addClass("fa-solid");
			$("#iconHeart").css("color","red");
		}else{
			$("#iconHeart").removeClass("fa-solid");
			$("#iconHeart").addClass("fa-regular");
			$("#iconHeart").css("color","black");
		}
		console.log(check);
	});
</script>

</div>

<%@ include file="../layout/footer.jsp"%>

