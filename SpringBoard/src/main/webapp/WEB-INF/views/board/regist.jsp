<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<div class="content">
	<h1>/view/board/regist.jsp</h1>
	<h1>게시판 글쓰기</h1>

	<div class="box box-primary">
		<div class="box-header with-border">
			<h3 class="box-title">게시판 글쓰기</h3>
		</div>
		<!-- /.box-header -->
		<!-- (S) form start -->
		<!-- 액션에 속성값이 없을경우 동일한 주소(자기 자신페이지)를 호출 -->
		<form role="form" method="post">
			<div class="box-body">
				<div class="form-group">
					<label for="title">제 목</label> 
					<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요.">
				</div>
				<div class="form-group">
					<label for="writer">이 름</label> 
					<input type="text" class="form-control" id="writer" name="writer" placeholder="작성자를 입력하세요.">
				</div>
				<div class="form-group">
					<label for="content">내 용</label> 
					<textarea class="form-control" name="content" rows="3" placeholder="Enter ..."></textarea>
				</div>
				<!-- <div class="form-group">
					<label for="exampleInputPassword1">Password</label>
					<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="exampleInputFile">File input</label>
					<input type="file" id="exampleInputFile">
					<p class="help-block">Example block-level help text here.</p>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> Check me out
					</label>
				</div> -->
			</div>
			<!-- /.box-body -->

			<div class="box-footer">
				<button type="submit" class="btn btn-primary">글쓰기</button>
			</div>
		</form>
		<!-- (E) form start -->
	</div>
</div>
<%@include file="../include/footer.jsp" %>