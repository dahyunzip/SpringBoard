<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--  템플릿 include -->
<%@ include file="../include/header.jsp" %>

<div class="content">
	<h1>/views/board/listALL.jsp</h1>
	<%-- ${requestScope.boardList } --%>
	result : ${result }<br>
	incrementStatus : ${sessionScope.incrementStatus }
	<!-- result는 rttr 이어서, 한번만 쓰고 사라짐. (addFlashAttribute) 했기 때문 -->
	
	<div class="box box-primary">
		<div class="box-header with-border">
        	<h3 class="box-title">게시판</h3>
        </div>
        <div class="box-body">
        	<table border="1" class="table table-bordered">
        		<colgroup>
        			<col width="8%">
        			<col width="40%">
        			<col width="*%">
        			<col width="8%">
        			<col width="12%">
        		</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vo" items="${boardList }">
					<tr>
						<td>${vo.bno }</td>
						<td><a href="/board/read?bno=${vo.bno }">${vo.title }</a></td>
						<td>${vo.writer }</td>
						<td>
							<span class="badge bg-green">${vo.viewcnt }</span>
						</td>
						<td><fmt:formatDate value="${vo.regdate }"/> </td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
        </div>
        <div class="box-footer clearfix">
	        <ul class="pagination pagination-sm no-margin pull-right">
	           <li><a href="#">«</a></li>
	           <li><a href="#">1</a></li>
	           <li><a href="#">2</a></li>
	           <li><a href="#">3</a></li>
	           <li><a href="#">»</a></li>
	        </ul>
       	</div>
	</div>
</div>

<!-- sweetalert 라이브러리 추가(설치) -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script type="text/javascript">
	// EL 표현식에 ' ' 붙여주면, javascript에서 인식 가능함
	// javascript에서 ''는 문자표현식이라 문자로 표현이 되는게 상식상 맞다.
	// JSP 실행 순서
	// Java(JSP) -> JSTL / EL -> HTML -> JavaScript -> Jquery
	// => DB에서 조회된 데이터도 JavaScript / JQuery 에서도 사용 가능
	//alert("result : " + '${result}');
	// alert('createOK');
	
	var result = '${result}'; // 실제로는 'createOK' 값이 저장
							  // 글수정시 'modifyOK'
	if(result == "createOK"){
		Swal.fire({
		  position: "center",
		  icon: "success",
		  title: "글쓰기 성공!",
		  showConfirmButton: false,
		  timer: 1500
		});
	}else if(result == "modifyOK"){
		Swal.fire({
		  position: "center",
		  icon: "success",
		  title: "글 수정 성공!",
		  showConfirmButton: false,
		  timer: 1500
		});
	}else if(result == "removeOK"){
		Swal.fire({
			  position: "center",
			  icon: "success",
			  title: "글 삭제 성공!",
			  showConfirmButton: false,
			  timer: 1500
			});
	}
	
</script>
<!--  템플릿 include -->
<%@ include file="../include/footer.jsp" %>