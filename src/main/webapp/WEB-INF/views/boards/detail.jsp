<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br /> 
        <input id="id" type="hidden" value="${detailDto.id}">
        <input id="lovesId" type="hidden" value="${detailDto.lovesId}">
        <div class="d-flex">
        <input id="page" type="hidden" value="${sessionScope.refferer.page}">
        <input id="keyword" type="hidden" value="${sessionScope.refferer.keyword}">
			<a href="/boards/${detailDto.id}/updateForm" class="btn btn-warning">수정하러가기</a>
			<form>
				<button id="btnDelete" type="button" class="btn btn-danger">삭제</button>
			</form>
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
    // 하트 아이콘을 클릭했을때의 로직
	$("#iconLove").click(()=>{
        let isLovedState = $("#iconLove").hasClass("fa-solid");
        if(isLovedState){
            deleteLove();
        }else{
            insertLove();
        }
	});
    
    function deleteLove(){
        let id = $("#id").val();
        let lovesId = $("#lovesId").val();
        
        
        $.ajax("/boards/"+id+"/loves/"+lovesId,{
            type:"DELETE",
            dataType:"json",
        }).done((res)=>{
            if(res.code == 1){ 
                renderCancelLoves();
                let count = $("#countLove").text();
                $("#countLove").text(Number(count)-1);
                }else{
                    alert("좋아요 취소 실패");
                }
        });
        
    }
    
    function insertLove(){
        let id = $("#id").val();
        
        $.ajax("/boards/"+id+"/loves",{
            type:"POST",
            dataType:"json",
        }).done((res)=>{
            if(res.code == 1){ 
                renderLove();
                let count = $("#countLove").text();
                $("#countLove").text(Number(count)+1);
                $("#lovesId").val(res.data);
                }else{
                    alert("좋아요 실패");
                }
        });
    }
    
    function renderLove(){
        $("#iconLove").removeClass("fa-regular");
        $("#iconLove").addClass("fa-solid");
    }
    
    function renderCancelLoves(){
        $("#iconLove").removeClass("fa-solid");
        $("#iconLove").addClass("fa-regular");
    }
</script>

</div>

<%@ include file="../layout/footer.jsp"%>

