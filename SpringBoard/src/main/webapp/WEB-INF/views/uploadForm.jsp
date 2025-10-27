<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery CDN ㅊ가 -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("jQuery 실행! ");
		var cnt = 1;
		
		// addBtn 클릭시, fileDiv 영역에 input file을 생성(추가)
		$("#addBtn").click(function(){
			//alert("버튼 클릭!");
			$("#fileDiv").append("<br> <input type='file' name='file"+ (cnt++)+"' accept='image/*'>");
		})
	})
</script>
</head>
<body>
	<h1>/views/uploadForm.jsp</h1>
	<h2> (다중) 파일 업로드</h2>
	<fieldset>
		<form action="/upload" method="post" enctype="multipart/form-data">
			<!-- 우리가 보내는 파일의 정보가 많고 길기 때문에 post사용. -->
			아이디 : <input type="text" name="userid"><br>
			이름 : <input type="text" name="username"><br>
			<hr>
			첨부파일 : <input type="button" value="추가하기" id="addBtn"><br>
			<div id="fileDiv"> <!-- 첨부파일이 추가될 곳 -->
			
			</div>
			<hr>
			<input type="submit" value="파일 업로드"> 
		</form>
	</fieldset>
</body>
</html>