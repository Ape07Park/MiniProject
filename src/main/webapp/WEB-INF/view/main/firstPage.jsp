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
		location.href = '/mp/mini/boardAuction/listAuction?p=${currentBoardPage}';
	}
</script>
</head>
<body>
	<%@ include file="../common/_topfirst.jspf"%>

	<img src="/mp/img/mainPageIMG.png" style="width: 100%;">
	
	<%@ include file="../common/_bottom.jspf"%>

</body>
</html>