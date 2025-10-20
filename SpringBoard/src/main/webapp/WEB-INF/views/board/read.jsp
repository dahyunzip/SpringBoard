<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="content">
	<h1>/view/board/read.jsp</h1>
	<h1>게시판 상세</h1>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">게시판 본문 읽기</h3>
			조회수 : ${boardVO.viewcnt }<br>
			incrementStatus : ${sessionScope.incrementStatus }
		</div>
		<!-- /.box-header -->
		
		<!-- 페이지 이동시(수정, 삭제) 필요한 정보를 가져가기위한 폼태그 -->
		<form role="form">
			<input type="hidden" name="bno" value=${boardVO.bno }> 
		</form>
		
		<!-- 액션에 속성값이 없을경우 동일한 주소(자기 자신페이지)를 호출 -->
		<div class="box-body">
			<div class="form-group">
				<label for="num">글 번호</label> 
				<input type="text" class="form-control" id="num" name="bno" value="${boardVO.bno }" disabled>
			</div>
			<div class="form-group">
				<label for="title">제 목</label> 
				<input type="text" class="form-control" id="title" name="title" value="${boardVO.title }" disabled>
			</div>
			<div class="form-group">
				<label for="writer">이 름</label> 
				<input type="text" class="form-control" id="writer" name="writer" value="${boardVO.writer }" disabled>
			</div>
			<div class="form-group">
				<label for="content">내 용</label> 
				<textarea class="form-control" name="content" rows="3" disabled>${boardVO.content }</textarea>
			</div>
		</div>
		<!-- /.box-body -->

		<div class="box-footer">
			<button type="submit" class="btn btn-danger" >수정</button>
			<button type="submit" class="btn btn-success" >삭제</button>
			<button type="submit" class="btn btn-primary" >목록</button>
		</div>
	</div>
</div>

<!-- jQuery 코드 추가 -->
<script type="text/javascript">
	$(document).ready(function(){
		//alert(" jquery 실행완료! ");
		var formObj = $("form[role='form']");
		
		// 수정 버튼 클릭시
		$(".btn-danger").click(function(){
			alert("수정 버튼 클릭!");
			// bno 정보를 저장하는 폼태그 정보를 submit() 하기
			formObj.attr("action","/board/modify");
			// GET 방식
			// submit()
			formObj.submit();
		});

		// 삭제 버튼 클릭시
		$(".btn-success").click(function(){
			alert("삭제 버튼 클릭!");
			location.href="/board/delete";
			// bno 가지고 바로 삭제처리 동작 실행
			formObj.attr("action", "/board/remove");
			// POST 방식
			formObj.attr("method", "POST");
			formObj.submit();
			
		});
		
		// 목록 버튼 클릭시
		$(".btn-primary").click(function(){
			//alert("목록 버튼 클릭!");
			location.href="/board/listCri";
		});
	});
</script>

<!-- 템플릿 코드 추가 -->
<%@include file="../include/footer.jsp" %>