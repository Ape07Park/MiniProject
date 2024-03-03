<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    function deleteFunc(inum) {
        $('#deleteInum').val(inum);
        $('#deleteModal').modal('show');
    }
</script>
<script>
        // JavaScript를 사용하여 이미지 경로를 동적으로 생성하는 함수
        function getImagePath(index) {
            return `../../equipImg/사진${index}.png`;
        }

        // 이미지 경로를 설정하여 이미지를 가져오는 함수
        function setImage(index) {
            var imagePath = getImagePath(index);
            document.getElementById(`image${index}`).src = imagePath;
        }

        // 이미지를 가져와서 설정하는 예시 (카드 0부터 3까지 설정)
        for (var i = 0; i < 4; i++) {
            setImage(i);
        }
    </script>
</head>
<body>
<%@ include file="../common/_top.jspf"%>
<!-- ============본문영역============ -->
<div class="container" style="margin-top: 10px">
    
    <!-- Loop for each row -->
    <c:forEach var="rowIndex" begin="0" end="4">
    <div class="row">
        <!-- Loop for each card in a row -->
        <c:forEach var="colIndex" begin="${rowIndex*4}" end="${rowIndex*4 + 3}" varStatus="loop">
            <div class="col-sm-3 text-white">
                <div class="card mb-3" style="height: 460px">
                    <!-- 이미지를 동적으로 설정하기 위해 id를 사용하여 이미지 태그를 식별 -->
                    <img class="card-img-top" src="../../equipImg/${loop.index}.png" style="width: 256px; height: 256px">
                    <div class="card-body">
                        <h6 class="card-title" style="text-align: center;">${equipList[colIndex].category}</h6>
                        <hr>
                        <h6 class="card-title" style="text-align: center;">${equipList[colIndex].ename}</h6>
                        <hr>
                        <p class="card-text" style="text-align: center;">${equipList[colIndex].eContent}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:forEach>


</div>



<%@ include file="../common/_bottom.jspf"%>
</body>
</html>