<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!-- 템플릿 코드 추가 -->
	<%@ include file="include/header.jsp"%>


	<div class="content">
		<h1>/views/restStart.jsp</h1>
		
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">REST 게시판 글쓰기(비동기방식)</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <!-- action 속성값이 없을경우 동일한 주소(자기 자신페이지)를 호출 -->
            <form role="form"  method="POST">
              <div class="box-body">
                <div class="form-group">
                  <label for="exampleInputEmail1">제 목</label>
                  <input type="text" class="form-control" id="title"
                         name="title"  
                         placeholder="제목을 입력하세요.">
                </div>
                <div class="form-group">
                  <label for="exampleInputEmail1">이 름</label>
                  <input type="text" class="form-control" id="writer" 
                         name="writer"
                         placeholder="이름을 입력하세요.">
                </div>
             	<div class="form-group">
                  <label>내 용</label>
                  <textarea class="form-control" rows="3" 
                            name="content" id="content"
                             placeholder="내용을 입력하세요."></textarea>
                </div>
              
              </div>
              <!-- /.box-body -->

            </form>
           <!-- form end -->
              <div class="box-footer">
                <button type="submit" class="btn btn-primary" 
                        id="btnADD">글쓰기</button>
              </div>
          </div>	
	</div>
	
	<div class="content">
	
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">REST 게시판 본문보기(비동기방식)</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <!-- action 속성값이 없을경우 동일한 주소(자기 자신페이지)를 호출 -->
            <form role="form"  method="POST">
              <div class="box-body">
                <div class="form-group">
                  <label for="exampleInputEmail1">제 목</label>
                  <input type="text" class="form-control" id="title2" name="title" readonly>
                </div>
                <div class="form-group">
                  <label for="exampleInputEmail1">이 름</label>
                  <input type="text" class="form-control" id="writer2" name="writer" readonly>
                </div>
             	<div class="form-group">
                  <label>내 용</label>
                  <textarea class="form-control" rows="3" name="content" id="content2" readonly></textarea>
                </div>
              
              </div>
              <!-- /.box-body -->

            </form>
           <!-- form end -->
              <div class="box-footer">
                <button type="submit" class="btn btn-primary" 
                        id="btnREAD">본문 내용보기</button>
              </div>
          </div>	
	</div>

	<div class="content">
		<div class="box">
            <div class="box-header">
              <h3 class="box-title">REST 게시판 리스트보기(비동기)</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table table-striped">
				<tbody>
					<tr>
						<th style="width: 10px">bno</th>
						<th>Title</th>
						<th>Writer</th>
						<th>ViewCnt</th>
						<th>RegDate</th>
					</tr>
				</tbody>
			</table>
            </div>
            <!-- /.box-body -->
    	</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			//alert("jquery 실행!");

			// 글쓰기 버튼 클릭시 
			$("#btnADD").click(function() {
				// 글쓰기 할 정보(JSON)
				var board = {
					"title" : $("#title").val(),
					"writer" : $("#writer").val(),
					"content" : $("#content").val()
				};

				// 비동기방식(ajax)으로 게시판 글쓰기 처리(REST 컨트롤러 호출)
				alert(" 글쓰기 클릭! ");
				$.ajax({
					type : "post",
					url : "${contextPath}/boards/",
					data : JSON.stringify(board),
					contentType : "application/json",
					success : function(result, statusText, jqXHR) {
						alert(" REST 컨트롤러 실행완료! ");
						if (jqXHR.status == "200") {
							alert(" REST 글쓰기 완료! ");
							alert(" result : " + result);

							// 글작성후 이전데이터 제거
							$("#title").val("");
							$("#writer").val("");
							$("#content").val("");
						}
					}

				});

			});

			// (S) btnREAD 클릭시 해당 데이터 REST컨트롤러에서 가져오기
			$("#btnREAD").click(function() {
				//alert(" btnREAD 클릭! ");
				//var bno = $(요소).val(); / $(요소).text() / $(요소).html();
				var bno = 22;

				$.ajax({
					type : "GET",
					url : "/boards/" + bno,
					success : function(result, statusText, jqXHR) {
						// jquery Xml Http Request
						//alert("REST 컨트롤러 다녀옴");
						//처리 결과 성공시 동작 구현
						//alert(result);
						//console.log(result);
						if (jqXHR.status == 200) { // OK
							$("#title2").val(result.title);
							$("#content2").val(result.content);
							$("#writer2").val(result.writer);

						}
					}
				});
			});
			// (E) btnREAD 클릭시 해당 데이터 REST컨트롤러에서 가져오기
		
			// (S) (페이지 실행시)게시판 목록정보 조회 비동기방식
			// 게시판 조회(list) / GET(조회 - Read)  /  [ /boards ] (list)
			$.ajax({
				type : "GET",
				url : "/boards/",
				success : function(result, statusText, jqXHR){
					//alert(" REST 컨트롤러 다녀옴");
					//console.log(result);
					if(jqXHR.status == "200"){
						$(result).each(function(idx, item){
							
							// * JSON 타입데이터 = 날짜 정보는 "문자"/숫자(ms 변환) -> Date 객체 사용 전환 후 출력
							// 1s = 1000ms
							// 1m = 1000 * 60 ms
							// 1h = 1000 * 60 * 60 ms
							// 1d = 1000 * 60 * 60 * 24 ms
							var resultDate = new Date(item.regdate);
							var year = resultDate.getFullYear()+"년";
							var month = resultDate.getMonth()+1+"월";
							var date = resultDate.getDate()+"일";
							
							var tag = "<tr>";
							tag += "<td>"+item.bno+"</td>";
							tag += "<td>"+item.title+"</td>";
							tag += "<td>"+item.writer+"</td>";
							tag += "<td>"+item.viewcnt+"</td>";
							tag += "<td>"+year + month + date +"</td>";
							//tag += "<td>"+item.regdate+"</td>";
							tag += "</tr>";
								
							$(".table-striped").append(tag);
						});
					}
				}
			});
			// (E) (페이지 실행시)게시판 목록정보 조회 비동기방식
			
		}); //jQuery
	</script>

	
	<!-- 템플릿 코드 추가 -->
	<%@ include file="include/footer.jsp"%>