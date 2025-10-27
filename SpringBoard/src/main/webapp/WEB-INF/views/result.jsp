<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>/views/result.jsp</h1>
	${paramMap } <hr>
	<h3> 아이디 : ${paramMap.userid }</h3>
	<h3> 이름 : ${paramMap.username }</h3>
	<c:forEach var="fileName" items="${paramMap.fileList }">
		<%-- 파일 : ${fileName } --%>
		<!-- 로컬 폴더에 직접 접근 금지! -->
		<%-- 파일 : <a href="c:/spring/upload/${fileName }">${fileName }</a> --%>
		<h3>파일 : <a href="/download?fileName=${fileName }">${fileName }</a></h3>
		<img alt="" src="/download?fileName=${fileName }" width="100px">
		<img alt="" src="/thumbNail?fileName=${fileName }">
	</c:forEach>
	<a href="/uploadForm">다시 업로드 하기</a>
</body>
</html>