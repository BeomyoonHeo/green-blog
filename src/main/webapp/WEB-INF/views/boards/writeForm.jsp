<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include file="../layout/header.jsp"%>

<div class="container">
  <form>
    <div class="mb-3 mt-3">
      <input
        id="title"
        type="text"
        class="form-control"
        placeholder="Enter title"
      />
    </div>
    <div class="mb-3">
		<textarea id="content"></textarea>
    </div>
    <button id="btnSave" type="button" class="btn btn-primary">글쓰기완료</button>
  </form>
</div>
<script src="/js/boards.js"></script>
<script>
	//summernote는 사진을 base64로 변환(인코딩)하여 보낸다.
	//base64(64진법)는 tensor값(또는 파일 형태)을 문자열 형태로 변환하는 기술이다. (맵핑테이블) - json이 문자열로 전송하는 방식이기 때문
	// ASCII 코드보다 2비트 경량화 되어있다.
  $("#content").summernote({
	tabsize:1,
    height: 500
  });
</script>

<%@ include file="../layout/footer.jsp"%>
