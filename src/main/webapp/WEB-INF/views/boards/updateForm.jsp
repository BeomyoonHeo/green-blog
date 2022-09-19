<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp"%>

<div class="container">
  <input id="id" type="hidden" value="${boards.id}" />
  <form>
    <div class="mb-3 mt-3">
      <input
        id="title"
        type="text"
        class="form-control"
        placeholder="Enter title"
        value="${boards.title}"
      />
    </div>
    <div class="mb-3">
      <textarea id="content" class="form-control" rows="8">
${boards.content}</textarea>
    </div>
    <button id="btnUpdate" type="button" class="btn btn-primary">
      수정완료
    </button>
  </form>
</div>
<script>
    

  $("#btnUpdate").click(() => {
      update();
  });

  $("#content").summernote({
	tabsize:1,
    height: 500
  });
    
function update(){
    let id = $("#id").val();
    let body = {
      title: $("#title").val(),
      content: $("#content").val(),
    };

    $.ajax("/boards/" + id, {
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
</script>

<%@ include file="../layout/footer.jsp"%>
