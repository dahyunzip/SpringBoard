<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--  템플릿 include -->
<%@ include file="../include/header.jsp" %>

<div class="content">
	<h1>/views/board/listCri.jsp</h1>
	<%-- ${requestScope.boardList } --%>
	pageVO : ${pageVO}<br>
	cri : ${pageVO.cri }<br>
	page : ${pageVO.cri.page }, pageSize : ${pageVO.cri.pageSize } <br>
	
	<c:set var="cri" value="${pageVO.cri }"/>
	c-set page : ${cri.page }, pageSize : ${cri.pageSize}<br>
	
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
						<td>
							<!-- param은 최초 접근시 가지고 있지 않기 떄문에 사용 X! -->
							<%-- <a href="/board/read?bno=${vo.bno }&page=${param.page}">${vo.title }</a>--%>
							<!-- 객체에 담아와서 사용하는 것이 안전함! -->
							<%-- <a href="/board/read?bno=${vo.bno }&page=${pageVO.cri.page}">${vo.title }</a>  --%>
							<a href="/board/read?bno=${vo.bno }&page=${cri.page}">${vo.title }</a>
						</td>
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
	        	<c:if test="${pageVO.prev }">
	        		<li><a href="/board/listCri?page=${pageVO.startPage - 1 }">«</a></li>
	        	</c:if>
	           <c:forEach var="i" begin="${pageVO.startPage }" end="${pageVO.endPage }" step="1">
	            <li class="${cri.page == i? 'active' : ''}">
	            	<!-- 글작성시 page 정보 없기 때문에 param은 사용 X  -->
	            	<a href="/board/listCri?page=${i}">${i }</a>
	            </li>
	           </c:forEach>
	           <c:if test="${pageVO.next }">
	           		<li><a href="/board/listCri?page=${pageVO.endPage + 1 }">»</a></li>
	           </c:if>
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