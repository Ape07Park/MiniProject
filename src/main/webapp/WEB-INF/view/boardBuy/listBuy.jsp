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
      location.href = '/mp/mini/board/listBuy?p=${currentBoardPage}&f=uid&q=${sessUid}';
   }
</script>
</head>
<body>
	<%@ include file="../common/_top.jspf"%>

	<div class="container" style="margin-top: 50px">
		<div class="row">

			<div class="col-9">
				<table class="table table-sm table-borderless">
					<tr>
						<td style="width: 52%; text-align: left">
							<h3>
								<strong class="me-5"><i class="fa-solid fa-person-snowboarding" style="padding-right: 10px; "></i>역경매 - 구매</strong> 
								<span style="font-size: 16px">
								<a href="/mp/mini/board/listBuy"><i class="fa-solid fa-list"></i></i>전체 리스트</a>
									<a href="/mp/mini/board/insertBuy" style="margin: 15px"><i class="fa-solid fa-pen-to-square"></i>구매 등록</a>
								</span>
							</h3>
						</td>
						<td style="width: 16%"><select class="form-control"
							id="field">
								<option value="uid" ${field eq 'uid' ? 'selected' : ''}>작성자</option>
								<option value="processTitle"
									${field eq 'processTitle' ? 'selected' : ''}>제목</option>
								<option value="processContent"
									${field eq 'processContent' ? 'selected' : ''}>내용</option>

						</select></td>
						<td style="width: 24%"><c:if test="${empty query}">
								<input class="form-control" type="text" id="query"
									placeholder="검색할 내용">
							</c:if> <c:if test="${not empty query}">
								<input class="form-control" type="text" id="query"
									value="${query}">
							</c:if></td>
						<td style="width: 8%">
							<button class="btn btn-outline-primary" onclick="search()">검색</button>
						</td>
					</tr>

				</table>
				<hr>

				<table class="table">
					<tr>
						<th style="width: 10%; color: navy;">보드 번호</th>
						<th style="width: 15%; color: navy;">신청 날짜</th>
						<th style="width: 10%; color: navy;">사용자</th>
						<th style="width: 30%; color: navy;">제목</th>
						<th style="width: 15%; color: navy;">평균 가격</th>
						<th style="width: 10%; color: navy;">업체 수</th>
						<th style="width: 10%; color: navy;">진행 사항</th>
					</tr>
					<c:forEach var="board" items="${boardList}">
						<tr>
							<td>${board.bid}</td>
							<td>${fn:substring(fn:replace(board.applTime,"T"," "), 2, 16)}</td>
							<td>${board.uid}</td>
							<td><a href="/mp/mini/board/detailBuy?bid=${board.bid}">${board.processTitle}</a>
							<td>${board.avgPrice}</td>
							<td>${board.numOfCompany}</td>
							<td><c:if test="${board.process eq 0}">
									<span style="color: black; font-weight: bolder;">입찰 진행</span>
								</c:if> <c:if test="${board.process eq 1}">
									<span style="color: gray; font-weight: bolder;">입찰 취소</span>
								</c:if> <c:if test="${board.process eq 2}">
									<span style="color: purple; font-weight: bolder;">거래 성사</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
				<%-- pagination --%>
				<ul class="pagination justify-content-center mt-4">
					<li class="page-item"><a class="page-link" href="#"><i
							class="fa-solid fa-less-than"></i></a></li>
					<c:forEach var="page" items="${pageList}">
						<li class="page-item ${currentBoardPage eq page ? 'active' : ''}">
							<a class="page-link"
							href="/mp/mini/board/listBuy?p=${page}&f=${field}&q=${query}">${page}</a>
						</li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="#"><i class="fa-solid fa-greater-than"></i></a></li>
				</ul>

			</div>
			<c:if test="${not empty sessUid}">
				<%@ include file="../common/_aside.jspf"%>
			</c:if>
		</div>
	</div>

	<%@ include file="../common/_bottom.jspf"%>

</body>
</html>