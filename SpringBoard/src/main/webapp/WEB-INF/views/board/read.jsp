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
			<button type="submit" class="btn btn-primary" onclick="location.href='/board/listALL'">목록</button>
			<button type="submit" class="btn btn-primary" onclick="location.href='/board/update'">수정</button>
			<button type="submit" class="btn btn-primary" onclick="location.href='/board/delete'">삭제</button>
		</div>
	</div>
</div>
<%@include file="../include/footer.jsp" %>