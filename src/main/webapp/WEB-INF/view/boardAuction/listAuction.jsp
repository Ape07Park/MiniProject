<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/_head.jspf"%>
<style>
td, th {
	text-align: center;
}
</style>
<script>
	function search() {
		const field = $('#field').val();
		const query = $('#query').val();
		location.href = '/mp/1.mini/boardAuction/listAuction?p=${currentBoardPage}';
	}
</script>
</head>
<body>
	<%@ include file="../common/_top.jspf"%>

	<div class="container" style="margin-top: 150px">
		<div class="row">

			<div class="col-9">
				<table class="table table-sm table-borderless">
					<tr>
						<td style="width: 52%; text-align: left">
							<h3>
								<strong class="me-5">역경매 항목</strong> <span
									style="font-size: 16px"><a
									href="/mp/1.mini/board/insertAuction"><i
										class="fa-solid fa-pen-to-square"></i> 글 쓰기</a></span>
							</h3>
						</td>
						<td style="width: 16%"><select class="form-control"
							id="field">
								<option value="applTime"
									${field eq 'applTime' ? 'selected' : ''}>신청 시간</option>
								<option value="nickName"
									${field eq 'nickName' ? 'selected' : ''}>닉네임</option>
								<option value="processTitle"
									${field eq 'processTitle' ? 'selected' : ''}>제목</option>
								<option value="avgPrice"
									${field eq 'avgPrice' ? 'selected' : ''}>평균 가격</option>
								<option value="numOfCompany"
									${field eq 'numOfCompany' ? 'selected' : ''}>업체 수</option>
								<option value="process" ${field eq 'process' ? 'selected' : ''}>진행
									사항</option>

						</select></td>
						<!-- <td style="width: 24%"><c:if test="${empty query}">
								<input class="form-control" type="text" id="query" placeholder="검색할 내용">
							</c:if> <c:if test="${not empty query}">
								<input class="form-control" type="text" id="query" value="${query}">
							</c:if></td>
						<td style="width: 8%">
							<button class="btn btn-outline-primary" onclick="search()">검색</button>
						</td>
					</tr>
					 -->
				</table>
				<hr>

				<table class="table">
					<tr>
						<th style="width: 20%">신청 시간</th>
						<th style="width: 10%">닉네임</th>
						<th style="width: 40%">제목</th>
						<th style="width: 10%">평균 가격</th>
						<th style="width: 10%">업체 수</th>
						<th style="width: 10%">진행 사항</th>
					</tr>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${fn:substring(fn:replace(board.applTime,"T"," "), 2, 16)}</td>
							<td>${board.nickName}</td>
							<td><a href="/mp/1.mini/board/detailAuction?nickName=${board.nickName}">${board.processTitle}</a>
							<td>${board.avgPrice}</td>
							<td>${board.numOfCompany}</td>
							<td>${board.process}</td>
						</tr>
					</c:forEach>
				</table>
				<!--<%-- pagination --%>
				<ul class="pagination justify-content-center mt-4">
					<li class="page-item"><a class="page-link" href="#"><i
							class="fa-solid fa-less-than"></i></a></li>
					<c:forEach var="page" items="${pageList}">
						<li class="page-item ${currentBoardPage eq page ? 'active' : ''}">
							<a class="page-link"
							href="/mp/1.mini/boardAuction/list?p=${page}&f=${field}&q=${query}">${page}</a>
						</li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="#"><i
							class="fa-solid fa-greater-than"></i></a></li>
				</ul>-->

			</div>
		</div>
	</div>

	<%@ include file="../common/_bottom.jspf"%>

</body>
</html>