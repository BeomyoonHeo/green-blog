<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp"%>

<div class="container">
  <button id="btnDelete" class="btn btn-danger" type="button">회원탈퇴</button>
  <form>
    <input id="id" type="hidden" value="${users.id}" />
    <!-- 바인딩 할 수 있게 input 태그 생성 -->
    <div class="mb-3 mt-3">
      <input
        type="text"
        class="form-control"
        placeholder="Enter username"
        value="${users.username}"
        readonly="readonly"
      />
    </div>
    <div class="mb-3">
      <input
        id="password"
        type="password"
        class="form-control"
        placeholder="Enter password"
        value="${users.password}"
      />
    </div>
    <div class="mb-3">
      <input
        id="email"
        type="email"
        class="form-control"
        placeholder="Enter email"
        value="${users.email}"
      />
    </div>
    <button id="btnUpdate" type="button" class="btn btn-primary">
      회원수정완료
    </button>
  </form>
</div>
<script>
  $("#btnDelete").click(() => {
    let id = $("#id").val();
    //el표현식은 script안에 넣으면 안된다.
    $.ajax("/users/" + id, {
      type: "DELETE",
      dataType: "json" //응답데이터 타입명
    }).done((res) => {
      // pending이 끝나면 발동하는 메서드
      if (res.code == 1) {
        console.log(res.msg);
        alert("회원 탈퇴 완료");
        location.href="/"; //f5
      } else {
        alert("회원탈퇴에 실패했습니다.");
        console.log(res.msg);
      }
    });
    //람다식을 사용하면 코드가 간결해지고, 스코프가 명확해진다.
  }); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)

  $("#btnUpdate").click(() => {
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
  }); //주소, object, done안에는 행위(메서드 - pending이 끝나면 실행된다.)
</script>

<%@ include file="../layout/footer.jsp"%>
