<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/_head.jspf"%>
<style>
td, th {

}
</style>
</head>
<body>
	<%@ include file="../common/_top.jspf"%>

	<div class="container" style="margin-top: 20px">
		<div class="row">

				<table class="table">
					<c:forEach var="qna" items="${qna}" varStatus="loop">
						<c:if test="${loop.index mod 2 eq 0}"><tr>
							<td style="font-weight: bolder; font-size: 30px;">${qna}</td>
						</tr>
						<tr>
							<td></td>
						</tr></c:if>
									<c:if test="${loop.index mod 2 eq 1}"><tr>
							<td style="font-size: 20px;">${qna}</td>
						</tr>
						<tr>
							<td></td>
						</tr></c:if>
						
					</c:forEach>
				</table>

			</div>
		</div>
	</div>

	<%@ include file="../common/_bottom.jspf"%>

</body>
</html>