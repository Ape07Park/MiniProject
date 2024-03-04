<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <%@ include file="../common/_head.jspf" %> <!-- .. : user .. :webapp -->
   <style>
    td { text-align: center; }
   </style>   
</head>
<body class="bg-light">

   <%@ include file="../common/_top.jspf" %> 
  <div class="container" style="margin-top: 50px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title"><h3><strong>회원가입</strong></h3></div>
            <hr>
            <form action="/mp/mini/user/register" method="post">
              <table class="table table-borderless">
                <tr>
                  <td style="width: 45%;"><label class="col-form-label">사용자 ID</label></td>
                  <td style="width: 55%;"><input type="text" name="uid" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">패스워드</label></td>
                  <td><input type="password" name="pwd" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">패스워드 확인</label></td>
                  <td><input type="password" name="pwd2" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">사용자 이름</label></td>
                  <td><input type="text" name="uname" class="form-control"></td>
                </tr>
                 <tr>
                  <td><label class="col-form-label">닉네임</label></td>
                  <td><input type="text" name="nickName" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">이메일</label></td>
                  <td><input type="text" name="email" class="form-control"></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">확인</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3">
              <span class="me-3">이미 사용자 계정이 있으신가요?</span>
              <a href="/mp/mini/user/login">로그인</a>
            </p>
            <div class="mt-3 mb-3">
              <span class="me-3">소셜 계정으로 가입</span>
              <span>
                <a class="ms-2" href="#"><img src="/mp/img/google-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/mp/img/github-logo.png" height="32"></a>
                <a class="ms-2" href="#"><img src="/mp/img/naver-logo.jpg" height="32"></a>
                <a class="ms-2" href="#"><img src="/mp/img/kakao-logo.png" height="32"></a>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  <%@ include file="../common/_bottom.jspf" %>
</body>
</html>