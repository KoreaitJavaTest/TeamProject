<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 부가적인 테마 -->

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="Layout/style.css">
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
<div class="container_fluid" id="main">
	<div class="row" id="header" style="min-height: 50px;">
		<div class="col-xs-8 test01" ></div>	
		<div class="col-xs-4 text-center test01 user">
			<div class="col-xs-3"></div>
			<c:if test = "${sessionScope.session_id eq null }">
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'LoginView.nhn'">로그인</span></div>
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'JoinView.nhn'">회원가입</span></div>
			</c:if>
			<c:if test = "${sessionScope.session_id != null }">
				<div class="col-xs-3"><span class="usercursor">${sessionScope.session_id}님<br/>point: ${sessionScope.session_point}점</span></div>
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'LogoutView.nhn'">로그아웃</span></div>
			</c:if>
			<c:if test = "${sessionScope.session_id != null }">
				<div class="col-xs-3"><span class="usercursor" onclick = "location.href = 'MyEditViewPasswordCheck.nhn'"> 마이페이지</span></div>
			</c:if>
		</div>	
	</div>
	<div class="row" id="main2" style="min-height: 200px;">
		<div class="col-xs-12" align="center"><span id="title" onclick="location.href='index.jsp'">Koreait Team A(img넣거나 그냥글자)</span></div>
	</div>
	<div class="row" id="menuLine" style="min-height:80px; ">
		<div class="col-xs-1"></div>
		<div class="col-xs-10" style="border: 1px solid gray;">
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">전체상품</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">리뷰게시판</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='QAboard.nhn'">묻고 답하기</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">공지사항</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">뭘넣을까</span></div>
			<div class="col-xs-2"><span class="menuitem" onclick="location.href='?'">여기도뭘넣지</span></div>
<!-- 			onclikc은 나중에 JS로 통합해서 코드수를 줄일 예정 -->
		</div>
		<div class="col-xs-1"></div>
	</div>
</div>		

		
		
