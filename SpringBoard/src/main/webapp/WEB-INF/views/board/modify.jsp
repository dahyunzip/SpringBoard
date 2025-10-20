<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="content">
	<h1>/view/board/modify.jsp</h1>
	<h1>게시물 수정하기</h1>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">게시물 수정하기</h3>
			조회수 : ${boardVO.viewcnt }<br>
			incrementStatus : ${sessionScope.incrementStatus }
		</div>
		<!-- /.box-header -->
		
		<!-- 페이지 이동시(수정, 삭제) 필요한 정보를 가져가기위한 폼태그 -->
		<form role="form" method="post">
			<!-- 액션에 속성값이 없을경우 동일한 주소(자기 자신페이지)를 호출 -->
			<div class="box-body">
				<div class="form-group">
					<label for="num">글 번호</label> 
					<input type="text" class="form-control" id="num" name="bno" value="${boardVO.bno }" disabled>
					<!-- input 태그의 disabled 속성은 submit시 정보 전달을 하지 않는다. -->
				</div>
				<div class="form-group">
					<label for="title">제 목</label> 
					<input type="text" class="form-control" id="title" name="title" value="${boardVO.title }" >
				</div>
				<div class="form-group">
					<label for="writer">이 름</label> 
					<input type="text" class="form-control" id="writer" name="writer" value="${boardVO.writer }" >
				</div>
				<div class="form-group">
					<label for="content">내 용</label> 
					<textarea class="form-control" name="content" rows="3" >${boardVO.content }</textarea>
				</div>
			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="submit" class="btn btn-danger" >수정</button>
				<button type="submit" class="btn btn-primary" >목록</button>
			</div>
		</form>
		<!-- form end -->
	</div>
</div>

<!-- jQuery 코드 추가 -->
<script type="text/javascript">
	$(document).ready(function(){
		// alert(" jquery 실행완료! ");
		// console.log(formObj);
		var formObj = $("form[role='form']");
		
		// 목록 버튼 클릭시
		$(".btn-primary").click(function(){
			//alert("목록 버튼 클릭!");
			location.href="/board/listALL";
		});
	});
</script>

<!-- 템플릿 코드 추가 -->
<%@include file="../include/footer.jsp" %>